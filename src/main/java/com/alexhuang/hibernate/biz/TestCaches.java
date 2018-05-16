/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.hibernate.biz;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.dao.domain.PeopleDomain;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月17日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
@Component
public class TestCaches {
    @Autowired private SessionFactory sessionFactory;
    
    public long testQueryFirst() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from test.yc.domain.People as p where p.age > 18").setMaxResults(1);
        List list = query.list();
        PeopleDomain p = (PeopleDomain) list.get(0);
        session.close();
        return p.getId();
    }
    
    public PeopleDomain loadPeople() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        PeopleDomain p2 = (PeopleDomain) session.get(PeopleDomain.class, 2L);
        p2.setName("1234");
        session.update(p2);
        tx.commit();
        session.close();
        return p2;
    }
    
}
