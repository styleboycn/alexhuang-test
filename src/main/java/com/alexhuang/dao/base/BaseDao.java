/* 
 * Copyright (c) 2015, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.dao.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.util.Assert;


/**
 * 描述：Dao的基类，提供基础的方法和属性，不要求一定添加事务
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015年2月11日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
public class BaseDao implements BeanFactoryAware {

    /** 在Query执行之前调用的接口 */
    @FunctionalInterface
    public static interface ICallInQuery {
    
    	/**
    	 * 在执行前，设置其它参数或者处理
    	 * 
    	 * @param upQry
    	 *            传入的Hibernate执行Query对象
    	 */
    	void callBeforeExecute(Query upQry);
    }

    /** 内部实行使用，在Session内执行 */
    @FunctionalInterface
    protected static interface ICallInSession<T> {
        T doInSession(Session session);
    }

    /** 在Query返回时调用的接口 */
    @FunctionalInterface
    public static interface IQueryCall<E> {
    	E call(Query upQry);
    }

    private static final IQueryCall<Integer> UPDATE_QUERY_CALL = new IQueryCall<Integer>() {
    	@Override
    	public Integer call(Query upQry) {
    		return upQry.executeUpdate();
    	}
    };

    /**
     * 数据库每次执行关于有in语句的SQL，params的个数，必须小于或者等于 size的大小
     * 
     * @param upQry
     *            Hibernate执行Query对象
     * @param paramKey
     *            参数的key值，占位符 :key
     * @param params
     *            参数的值列表
     * @param size
     *            每次执行in语句的个数
     * @param call
     *            设置其它参数或者处理的调用句柄
     * @param queryCall
     *            设置返回调用句柄
     * @return 执行更新条数
     */
    protected static <T, E> E executeQueryIn(Query upQry, String paramKey, Collection<T> params, int size, ICallInQuery call, IQueryCall<E> queryCall) {
    	Collection<T> paramList = params;
    	if (paramList.size() < size) {
    		paramList = new ArrayList<T>(params);
    		T ele = ((List<T>) paramList).get(0);
    		while (paramList.size() < size) {
    			paramList.add(ele);
    		}
    	}
    	if (null != call) {
    		call.callBeforeExecute(upQry);
    	}
    	upQry.setParameterList(paramKey, paramList);
    	E rs = queryCall.call(upQry);
    	paramList.clear();
    	return rs;
    }

    /**
     * 执行Hibernate的Query，该Query特点是：包含有in语句，参数值列表不固定 <br>
     * 这个方法好处是，每次执行的in语句个数固定，从而使得hibernate的性能更加优化
     * 
     * @param upQry
     *            Hibernate执行Query对象
     * @param paramKey
     *            参数的key值，占位符 :key
     * @param params
     *            参数的值列表
     * @param eachSize
     *            每次执行in语句的个数
     * @param call
     *            设置其它参数或者处理的调用句柄
     * @param queryCall
     *            设置执行调用句柄
     */
    public static <T, E> void executeQueryInAll(Query upQry, String paramKey, Collection<T> params, int eachSize, ICallInQuery call, IQueryCall<E> queryCall) {
    	if (params.size() <= eachSize) {
    		executeQueryIn(upQry, paramKey, params, eachSize, call, queryCall);
    		return;
    	}
    	int size = 0;
    	Set<T> paramList = new HashSet<T>(eachSize);
    	Iterator<T> delIt = params.iterator();
    	while (delIt.hasNext()) {
    		if (paramList.add(delIt.next())) {
    			size++;
    			if (size == eachSize) {
    				executeQueryIn(upQry, paramKey, paramList, eachSize, call, queryCall);
    				size = 0;
    				paramList.clear();
    			}
    		}
    	}
    	if (size > 0) {
    		executeQueryIn(upQry, paramKey, paramList, eachSize, call, queryCall);
    	}
    }

    /**
     * 数据库每次执行关于有in语句的SQL，params的个数，必须小于或者等于 size的大小
     * 
     * @param upQry
     *            Hibernate执行Query对象
     * @param paramKey
     *            参数的key值，占位符 :key
     * @param params
     *            参数的值列表
     * @param size
     *            每次执行in语句的个数
     * @param call
     *            设置其它参数或者处理的调用句柄
     * @return 执行更新条数
     */
    protected static <T> int executeUpdateIn(Query upQry, String paramKey, Collection<T> params, int size, ICallInQuery call) {
    	return executeQueryIn(upQry, paramKey, params, size, call, UPDATE_QUERY_CALL);
    }

    /**
     * 执行Hibernate的Query，该Query特点是：包含有in语句，参数值列表不固定 <br>
     * 这个方法好处是，每次执行的in语句个数固定，从而使得hibernate的性能更加优化
     * 
     * @param upQry
     *            Hibernate执行Query对象
     * @param paramKey
     *            参数的key值，占位符 :key
     * @param params
     *            参数的值列表
     * @param eachSize
     *            每次执行in语句的个数
     * @return 执行更新条数
     */
    public static <T> int executeUpdateInAll(Query upQry, String paramKey, Collection<T> params, int eachSize) {
    	return executeUpdateInAll(upQry, paramKey, params, eachSize, null);
    }

    /**
     * 执行Hibernate的Query，该Query特点是：包含有in语句，参数值列表不固定 <br>
     * 这个方法好处是，每次执行的in语句个数固定，从而使得hibernate的性能更加优化
     * 
     * @param upQry
     *            Hibernate执行Query对象
     * @param paramKey
     *            参数的key值，占位符 :key
     * @param params
     *            参数的值列表
     * @param eachSize
     *            每次执行in语句的个数
     * @param call
     *            设置其它参数或者处理的调用句柄
     * @return 执行更新条数
     */
    public static <T> int executeUpdateInAll(Query upQry, String paramKey, Collection<T> params, int eachSize, ICallInQuery call) {
    	if (params.size() <= eachSize) {
    		return executeUpdateIn(upQry, paramKey, params, eachSize, call);
    	}
    	int size = 0, rs = 0;
    	Set<T> paramList = new HashSet<T>(eachSize);
    	Iterator<T> delIt = params.iterator();
    	while (delIt.hasNext()) {
    		if (paramList.add(delIt.next())) {
    			size++;
    			if (size == eachSize) {
    				rs += executeUpdateIn(upQry, paramKey, paramList, eachSize, call);
    				size = 0;
    				paramList.clear();
    			}
    		}
    	}
    	if (size > 0) {
    		rs += executeUpdateIn(upQry, paramKey, paramList, eachSize, call);
    	}
    	return rs;
    }

    public static <T> int executeUpdateInAll(Query upQry, String paramKey, Collection<T> params, int eachSize, final String secondParam, final Long secondValue) {
    	return executeUpdateInAll(upQry, paramKey, params, eachSize, (query) -> query.setLong(secondParam, secondValue));
    }

    public int executeSql(String sql, Object... args) throws HibernateException {
        Query query = currentSession().createSQLQuery(sql);
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(String.valueOf(i), args[i]);
            }
        }
        return query.executeUpdate();
    }
    
    public void flush() {
        this.currentSession().flush();
    }
    
    protected BeanFactory beanFactory;

    /**
     * slf4j的日志统一接口，slf4j日志，可以使用带参数的日志内容，具体参考slf4j的相关文档
     * 本框架使用logback来记录日志，性能更加优化
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    protected SessionFactory sessionFactory;
    
    public void closeSession(Session session) {
        SessionFactoryUtils.closeSession(session);
    }

    protected Session currentSession() {
        return getSessionFactory().getCurrentSession();
    }

    protected SQLQuery getNamedSQLQuery(String name) {
        return (SQLQuery) currentSession().getNamedQuery(name);
    }
    
    /**
     * 本方法不要求一定添加事务，如果没有事务，会打开新的连接，执行，然后关闭连接
     * 此代码修改自HibernateTemplate的doExecute
     * @param sessionCall
     *        在打开的session连接内执行
     * @return 执行sessionCall返回的内容
     * @throws DataAccessException
     *         执行出错抛出
     */
    protected <T> T doExecuteWithSession(ICallInSession<T> sessionCall) throws DataAccessException {
        Assert.notNull(sessionCall, "SessionCall object must not be null");
        Session session = null;
        boolean isNew = false;
        try {
            session = currentSession();
        } catch (HibernateException ex) {
            logger.debug("Could not retrieve pre-bound Hibernate session", ex);
        }
        if (session == null) {
            session = openSession();
            session.setFlushMode(FlushMode.MANUAL);
            isNew = true;
        }

        try {
            return sessionCall.doInSession(session);
        } catch (HibernateException ex) {
            throw SessionFactoryUtils.convertHibernateAccessException(ex);
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            if (isNew) {
                closeSession(session);
            }
        }
    }

    protected BeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session openSession() {
        return getSessionFactory().openSession(); //NOPMD
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
