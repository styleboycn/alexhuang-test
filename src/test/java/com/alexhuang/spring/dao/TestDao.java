package com.alexhuang.spring.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.spring.dao.domain.TestDomain;

@Component
public class TestDao implements ITestDao {

	@Autowired(required = false)
	protected SessionFactory sessionFactory;

	public void modifyData() {
		Session currentSession = sessionFactory.getCurrentSession();
		TestDomain td = (TestDomain) currentSession.load(TestDomain.class, 1L);
		td.txt = "xx:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
