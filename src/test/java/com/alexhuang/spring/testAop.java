package com.alexhuang.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alexhuang.spring.biz.ITestBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:interceptor/*.xml"})
public class testAop {

	private @Autowired ITestBiz testBiz;

	@Test
	public void testBeanBiz() {
		testBiz.test1();//事务回滚，执行失败
	}	
	
	@Test
	public void testBeanService() throws Exception {
		testBiz.test2();//事务不回滚，执行成功
	}
	
	@Test
	public void testBeanService2() throws Exception {
		testBiz.test3();//try/catch后继续抛出异常，事务回滚，执行失败
	}

}
