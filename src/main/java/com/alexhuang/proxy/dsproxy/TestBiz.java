package com.alexhuang.proxy.dsproxy;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexhuang.dao.ITestDao;

@Service
@Transactional
public class TestBiz {

	private @Autowired ITestDao testDao;

	public void test() {
		testDao.modifyData();
	}

}
