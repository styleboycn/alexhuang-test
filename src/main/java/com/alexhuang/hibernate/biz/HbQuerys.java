/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.hibernate.biz;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.dao.domain.PeopleDomain;

/**
 * 描述：
 * 
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月11日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
@Component
public class HbQuerys {
    @Autowired private SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(HbQuerys.class);
    
    public void hqlQuery() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from test.yc.domain.People as p where p.age > 18");
        List list = query.list();
        logger.info("hqlQuery size {}", list.size());
        session.close();
    }

    public void criteriaQuery() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(PeopleDomain.class).add(Restrictions.gt("age", 18)).list();
        logger.info("criteriaQuery size {}", list.size());
        session.close();
    }

    public void sqlQuery() {
        Session session = sessionFactory.openSession();
        List list = session.createSQLQuery("select * from DEMO_PEOPLE p where p.AGE > 18")
            .addEntity(PeopleDomain.class).list();
        logger.info("sqlQuery size {}", list.size());
        session.close();
    }
}
