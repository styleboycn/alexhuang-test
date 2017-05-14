package com.alexhuang.spring.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.spring.dao.ITestDao;

/**
 * TestBiz是以Biz结尾的spring bean，会进入BeanNameAutoProxyCreator切面
 *
 */
@Component
public class TestBiz implements ITestBiz {

	private @Autowired ITestDao testDao;
	
	private @Autowired IBeanBiz beanBiz;
	
	private @Autowired IBeanService beanService;
	
	@Override
	public void test1() {
		testDao.modifyData();
		try {
			String beanBizValue = beanBiz.test();
			System.out.println("beanBiz 's Value : " + beanBizValue);
		} catch (Exception ex) {
			//try/catch住，不向上抛出异常
		}
	}
	
	@Override
	public void test2() {
		testDao.modifyData();
		try {
			String beanServiceValue = beanService.test();
			System.out.println("beanService 's Value : " + beanServiceValue);
		} catch (Exception ex) {
			//try/catch住，不向上抛出异常
		}
	}

	@Override
	public void test3() {
		testDao.modifyData();
		try {
			String beanServiceValue = beanService.test();
			System.out.println("beanService 's Value : " + beanServiceValue);
		} catch (Exception ex) {
			//try/catch后，继续抛出异常
			throw ex;
		}
	}
}
