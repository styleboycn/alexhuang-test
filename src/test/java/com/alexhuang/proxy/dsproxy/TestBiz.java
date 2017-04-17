package com.alexhuang.proxy.dsproxy;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TestBiz {

	//private @Autowired TestDao testDao;

	public void test() {
		//testDao.test();
	}

}
