package com.alexhuang.algo;

//import java.util.logging.LogManager;-->this is in rt.jar
//import org.apache.log4j.LogManager;-->this is in log4j-1.2.17.jar
import org.apache.logging.log4j.LogManager;//->this class in log4j-api-2.8.2.jar
import org.apache.logging.log4j.Logger;

public class testBurstFilter {

    private static Logger logger = LogManager.getLogger(testBurstFilter.class);

    public static void main(String[] args) {

        logger.info("Hello World!");
        logger.error("Hello World!");
        logger.warn("Hello World!");
        testBurstFilter();

    }

    /**
     * "<BurstFilter level="INFO" rate="2" maxBurst="10"/>"
     *  this setting in log4j2.xml
     *  rate means that set the average number of events per second to allow
     *  maxBurst ??
     */

    public static void testBurstFilter() {
        int runSecs = 20;
        int count = 0;
        long targetTime = System.currentTimeMillis() + runSecs * 1000;
        while (true) {
            if (System.currentTimeMillis() > targetTime) {
                break;
            }
            logger.info("abc");
            count++;
        }
        System.out.println("count : " + count);
    }
}
