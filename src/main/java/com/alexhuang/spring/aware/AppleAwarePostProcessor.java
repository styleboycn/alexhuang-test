package com.alexhuang.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class AppleAwarePostProcessor implements BeanPostProcessor {
	
	private Apple a;

	public AppleAwarePostProcessor(Apple a) {
        this.a = a;
    }

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		// TODO Auto-generated method stub
		if (bean instanceof AppleAware) {
			((AppleAware) bean).setApple(a);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

}
