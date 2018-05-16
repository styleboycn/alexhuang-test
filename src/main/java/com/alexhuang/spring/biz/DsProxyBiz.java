package com.alexhuang.spring.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.dao.IDsProxyDao;

/**
 * DsProxyBiz是以Biz结尾的spring bean，会进入BeanNameAutoProxyCreator切面
 *
 */
@Component
public class DsProxyBiz implements IDsProxyBiz {

	private @Autowired IDsProxyDao dsProxyDao;
	
	private @Autowired IBeanBiz beanBiz;
	
	private @Autowired IBeanService beanService;
	
	@Override
	public void test1() {
		dsProxyDao.modifyData();
		try {
			String beanBizValue = beanBiz.test();
			System.out.println("beanBiz 's Value : " + beanBizValue);
		} catch (Exception ex) {
			//try/catch住，不向上抛出异常
		}
	}
	
	@Override
	public void test2() {
		dsProxyDao.modifyData();
		try {
			String beanServiceValue = beanService.test();
			System.out.println("beanService 's Value : " + beanServiceValue);
		} catch (Exception ex) {
			//try/catch住，不向上抛出异常
		}
	}

	@Override
	public void test3() {
		dsProxyDao.modifyData();
		try {
			String beanServiceValue = beanService.test();
			System.out.println("beanService 's Value : " + beanServiceValue);
		} catch (Exception ex) {
			//try/catch后，继续抛出异常
			throw ex;
		}
	}
}
