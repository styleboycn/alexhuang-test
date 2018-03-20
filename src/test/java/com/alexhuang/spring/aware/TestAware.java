package com.alexhuang.spring.aware;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class TestAware {
	public static void main(String args[]) {
        Resource resource = new FileSystemResource("E:\\EclipseWS\\git\\alexhuang-test\\target\\test-classes\\spring-aware-test.xml");  
        ConfigurableBeanFactory factory = new XmlBeanFactory(resource);
        BeanPostProcessor bpp = new AppleAwarePostProcessor((Apple)factory.getBean("apple"));
        factory.addBeanPostProcessor(bpp);
        
        Market market = (Market) factory.getBean("market");
        System.out.println(market.getName());
        System.out.println(market.getSize());
    }
}
