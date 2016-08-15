package com.alexhuang.spring.factorybean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:sgs-biz-test-bean.xml")
public class RunApp {
	
	@Test
	public void test() throws Exception {
		System.out.println("xxxx");
	}
	
//	public static void main(String[] args) {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//        		"classpath:factorybean.xml");
//        ctx.start();
//        synchronized (RunApp.class) {
//            try {
//            	RunApp.class.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//	}
}
