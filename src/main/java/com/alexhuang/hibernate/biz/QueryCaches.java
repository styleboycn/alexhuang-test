/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.hibernate.biz;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Query;
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
 *  1    2017年10月16日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
@Component
public class QueryCaches {
    @Autowired private SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(QueryCaches.class);
    
    public void updatePeople2() {
        String cacheRegion = "peopleGT18";
        Session session = sessionFactory.openSession();
        
        Transaction tx = session.beginTransaction();
        Query query = session.getNamedQuery("UpPeoples2").setCacheMode(CacheMode.NORMAL).setCacheRegion(cacheRegion);
        query.setLong("id", 2L);
        query.executeUpdate();
        
        tx.commit();
        session.close();
    }
    
    public void testQueryCache3() {
        String cacheRegion = "peopleGT18";
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        List list = query.list();
        logger.info("hqlQuery size {}", list.size());
        
        // sessionFactory.getCache().evictEntityRegions();
        
        Query query1 = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        list = query1.list();
        logger.info("hqlQuery size {}", list.size());
        
        query = session.getNamedQuery("UpPeoples2").setCacheMode(CacheMode.NORMAL).setCacheRegion(cacheRegion);
        query.setLong("id", 2L);
        query.executeUpdate();
        
        Query query2 = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        list = query2.list();
        logger.info("hqlQuery size2 {}", list.size());
        
        Query query3 = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        list = query3.list();
        logger.info("hqlQuery size3 {}", list.size());
        
        tx.rollback();
        session.close();
        
    }
    public void testQueryCache2() {
        String cacheRegion = "peopleGT18";
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        Query query = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        List list = query.list();
        logger.info("hqlQuery size {}", list.size());
        
        query = session.getNamedQuery("UpPeoples1").setCacheMode(CacheMode.NORMAL).setCacheRegion(cacheRegion);
        query.setLong("id", 2L);
        query.executeUpdate();
        
        Query query2 = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        list = query2.list();
        logger.info("hqlQuery size2 {}", list.size());
        
        tx.rollback();
        session.close();
        
    }
    
    public void testHqlQueryCache() {
        String cacheRegion = "peopleGT18";
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        List list = query.list();
        logger.info("hqlQuery size {}", list.size());

        Query query2 = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        list = query2.list();
        logger.info("hqlQuery size2 {}", list.size());
        
        Transaction tx = session.beginTransaction();
        PeopleDomain people = (PeopleDomain) list.get(0);
        people.setName("_" + people.getName() + "_");
        people.setAge(17);
        session.flush();
        
        Query query3 = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        list = query3.list();
        logger.info("hqlQuery size3 {}", list.size());
        
//        tx.rollback();
        tx.commit();
        
        Query query4 = session.getNamedQuery("GetPeoples2").setCacheable(true)
            .setCacheRegion(cacheRegion);
        list = query4.list();
        logger.info("hqlQuery size4 {}", list.size());
        
        session.close();
    }
    
    public void testSqlQueryCache() {
        Session session = sessionFactory.openSession();
        List list = session.createSQLQuery("select * from DEMO_PEOPLE p where p.AGE > 18")
            .addEntity(PeopleDomain.class).setCacheable(true).list();
        logger.info("sqlQuery size {}", list.size());
        
        list = session.createSQLQuery("select * from DEMO_PEOPLE p where p.AGE > 18")
            .addEntity(PeopleDomain.class).setCacheable(true).list();
        logger.info("sqlQuery size {}", list.size());
        
        sessionFactory.getCache().evictQueryRegions();
        session.close();
    }
}
