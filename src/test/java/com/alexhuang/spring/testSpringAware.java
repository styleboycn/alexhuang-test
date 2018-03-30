package com.alexhuang.spring;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.alexhuang.spring.aware.Apple;
import com.alexhuang.spring.aware.AppleAwarePostProcessor;
import com.alexhuang.spring.aware.Market;

public class testSpringAware {
	public static void main(String args[]) {
        Resource resource = new FileSystemResource("target//test-classes//spring-aware.xml");  
        ConfigurableBeanFactory factory = new XmlBeanFactory(resource);
        BeanPostProcessor bpp = new AppleAwarePostProcessor((Apple)factory.getBean("apple"));
        factory.addBeanPostProcessor(bpp);
        
        Market market = (Market) factory.getBean("market");
        System.out.println(market.getName());
        System.out.println(market.getSize());
    }
}
