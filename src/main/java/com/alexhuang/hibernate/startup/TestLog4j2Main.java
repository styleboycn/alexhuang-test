/* 
 * Copyright (c) 2017, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.hibernate.startup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class TestLog4j2Main {
    private static final Logger logger = LogManager.getLogger(TestLog4j2Main.class);

    public static void main(String[] args) throws Exception {
        int i = 0;
        while (true) {
            logger.info("info msg");
            if (++i % 1000 == 0) {
                System.out.println("done");
                Thread.sleep(1000L);
            }
        }
    }
}
