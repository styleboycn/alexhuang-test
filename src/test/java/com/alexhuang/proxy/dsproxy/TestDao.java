package com.alexhuang.proxy.dsproxy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDao {

	@Autowired(required = false)
	protected SessionFactory sessionFactory;

	public void test() {

		Session currentSession = sessionFactory.getCurrentSession();
		TestDomain td = (TestDomain) currentSession.load(TestDomain.class, 1L);
		td.txt = "d";

	}

}
