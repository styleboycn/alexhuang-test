/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.hibernate.biz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
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
 *  1    2017年10月10日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
@Component
public class BatchUpdate {
    private static final Logger logger = LogManager.getLogger(BatchUpdate.class);

    @Autowired private SessionFactory sessionFactory;

    public void testSingleInsert() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        PeopleDomain people = new PeopleDomain();
        people.setName("Single insert");
        session.save(people);
        
        tx.commit();
        session.close();
    }
    
    public void testFlush() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        PeopleDomain people = new PeopleDomain();
        people.setName("testFlush02");
        people.setAge(1 + (int) (99 * Math.random()));
        session.save(people);
        session.flush();
        
        tx.commit();
        session.close();
    }
    
    public void testBatchInert() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        for (int i = 0; i < 500; i++) {
        	PeopleDomain people = new PeopleDomain();
            people.setId(100000L + i);
            people.setName("test" + (i + 1));
            people.setAge(1 + (int) (99 * Math.random()));
            session.save(people);
            if (i % 50 == 0) { //20, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }
        
        tx.commit();
        session.close();
        logger.info("testBatchInert done");
    }
    
    public void testBatchUpdate() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
           
        ScrollableResults peoples = session.getNamedQuery("GetPeoples")
            .setCacheMode(CacheMode.IGNORE)
            .scroll(ScrollMode.FORWARD_ONLY);
        int count=0;
        while (peoples.next()) {
        	PeopleDomain people = (PeopleDomain) peoples.get(0);
            people.setName("_" + people.getName() + "_");
            people.setGender((byte) 2);
            if (++count % 50 == 0) {
                //flush a batch of updates and release memory:
                session.flush();
                session.clear();
            }
        }
           
        tx.commit();
        session.close();
        logger.info("testBatchUpdate done, size {}", count);
    }
}
