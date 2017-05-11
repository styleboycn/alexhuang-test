package com.alexhuang.spring.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alexhuang.spring.tx.ITransaction;

public class SgsMethodInterceptor implements MethodInterceptor, ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(SgsMethodInterceptor.class);
    private static ThreadLocal<Counter> stackCounter = new ThreadLocal<Counter>();
    private boolean initialize = false;
    private List<ITransaction> transactions;
    /** 注入的事务拦截Bean id列表，以","分割 */
    private String transactionBeanNames;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        BeanFactory beanFactory = contextRefreshedEvent.getApplicationContext();

        this.transactions = new ArrayList<ITransaction>();
        for (String beanName : transactionBeanNames.split(",")) {
            ITransaction bean = (ITransaction) beanFactory.getBean(beanName);
            transactions.add(bean);
        }
        this.initialize = true;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (initialize) {
            Counter invokeStack = stackCounter.get();
            if (invokeStack == null) {
                invokeStack = new Counter();
                stackCounter.set(invokeStack);
            }

            if (invokeStack.inc()) {
                invokeStack.setMethod(invocation.getMethod());
            }

            boolean error = false;
            try {
                return invocation.proceed();
            } catch (Throwable e) {
                error = true;
                throw e;
            } finally {
                if (invokeStack.dec()) {
                    if (error) {
                        rollback();
                    } else {
                        commit();
                    }
                    invokeStack.printInvokeInfo(error);
                }
            }
        } else {
            return invocation.proceed();
        }
    }

    private void rollback() {
        transactions.forEach(tx -> {
            try {
                tx.rollback();
            } catch (Throwable e) {
                logger.error(tx.getClass().getCanonicalName() + " rollback error.", e);
            }
        });
    }

    private void commit() throws Throwable {
        Throwable t = null;
        for (ITransaction tx : transactions) {
            try {
                tx.commit();
            } catch (Throwable e) {
                logger.error(tx.getClass().getCanonicalName() + " commit error.", e);
                t = e;
                break;
            }
        }

        if (t != null) {
            rollback();
            throw t;
        }
    }

    public static boolean isTransaction() {
        Counter invokeStack = stackCounter.get();
        return (invokeStack != null) && (invokeStack.value > 0);
    }

    public void setTransactionBeanNames(String transactionBeanNames) {
        this.transactionBeanNames = transactionBeanNames;
    }

    static class Counter {

        private long startTime;
        private int value = 0;
        private Method method;

        public boolean inc() {
            boolean isStart = value == 0;
            value++;
            return isStart;
        }

        public boolean dec() {
            value--;
            boolean isFinish = value == 0;
            return isFinish;
        }

        public void setMethod(Method method) {
            this.method = method;
            this.startTime = System.currentTimeMillis();
        }

        public void printInvokeInfo(boolean error) {
            if (logger.isInfoEnabled()) {
                long time = System.currentTimeMillis() - startTime;
                String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
                logger.info("invoke [" + methodName + "] " + (error ? "fail" : "ok") + ", time: " + time);
            }
        }
    }
}
