package com.alexhuang.spring.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexhuang.spring.dao.ITestDao;

@Service
public class TestBiz implements ITestBiz {

	private @Autowired ITestDao testDao;

	@Override
	public void test1() {
		// TODO Auto-generated method stub
		testDao.modifyData();
	}

}
