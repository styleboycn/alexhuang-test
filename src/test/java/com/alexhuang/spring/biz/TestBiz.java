package com.alexhuang.spring.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.spring.dao.ITestDao;

@Component
public class TestBiz implements ITestBiz {

//	private @Autowired ITestDao testDao;

	@Override
	public void test1() {
		// TODO Auto-generated method stub
//		testDao.modifyData();
	}

}
