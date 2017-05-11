package com.alexhuang.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alexhuang.spring.biz.ITestBiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config-datasource-tx.xml", "classpath*:config-datasource.xml"})
public class Test1 {

	private @Autowired ITestBiz testBiz;

	@Test
	public void test() throws Exception {
		System.out.println();
	}

}
