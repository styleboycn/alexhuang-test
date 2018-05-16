/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.hibernate.startup;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexhuang.hibernate.biz.BatchUpdate;
import com.alexhuang.hibernate.biz.HbQuerys;
import com.alexhuang.hibernate.biz.QueryCaches;
import com.alexhuang.hibernate.biz.TestCaches;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月9日      166046         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 166046
 */
public class MainTest {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:META-INF/spring/*.xml")) {
        	//BatchUpdate.class
            BatchUpdate up = context.getBean(BatchUpdate.class);
            up.testSingleInsert();
//            up.testFlush();
//            up.testBatchInert();
//            up.testBatchUpdate();
            
            //HbQuerys.class
//            HbQuerys query = context.getBean(HbQuerys.class);
//            query.hqlQuery();
//            query.criteriaQuery();
//            query.sqlQuery();
            
            //QueryCaches.class
//            QueryCaches queryCaches = context.getBean(QueryCaches.class);
//            queryCaches.testQueryCache2();
//            queryCaches.testQueryCache3();
//            queryCaches.testHqlQueryCache();
//            queryCaches.testSqlQueryCache();
        }
    }
    
    @Test
    public void testCaches() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:META-INF/spring/*.xml")) {
            TestCaches testCaches = context.getBean(TestCaches.class);
            testCaches.loadPeople();
        }
    }
    
}
