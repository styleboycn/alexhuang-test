package com.alexhuang.algo;

//import java.util.logging.LogManager;-->this is in rt.jar
//import org.apache.log4j.LogManager;-->this is in log4j-1.2.17.jar
import org.apache.logging.log4j.LogManager;//->this class in log4j-api-2.8.2.jar
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/*** 日志限流测试
 * @author Alex Huang
 * @date 2019/8/23
 */
public class testBurstFilter {

    //LogManager 和 Logger 都在 log4j-api-.2.8.2.jar 中，默认加载的配置文件名log4j2.xml
    private static Logger logger = LogManager.getLogger(testBurstFilter.class);

    public static void main(String[] args) {
//        logger.info("Hello World!");
        logger.error("Hello World!");
        logger.warn("Hello World!");
        testBurstFilter();
    }

    /**
     * "<BurstFilter level="INFO" rate="2" maxBurst="10"/>"
     *  rate：每秒允许的log事件的"平均值"
     *  maxBurst：当BurstFilter过滤的事件超过rate值，排队的log事件上限。超过此上限的log，将被丢弃。默认情况下maxBurst = rate * 100
     *  ###############################################################
     *  ###############################################################
     *  private final DelayQueue<LogDelay> history = new DelayQueue<>();
     *  private final Queue<LogDelay> available = new ConcurrentLinkedQueue<>();
     *  按照上面配置，burst==10，rate==2，整个available可以在5秒（10/2）内消费使用完
     *  当每秒2个slot(available中)消费完后，都会放到history中，并设置延时5秒钟
     *  等到第6秒时，会把到期的slot，从history->available后，又可以循环消费这两个slot
     *  所以，maxBurst可以理解为三层意思：
     *  1、能够提前消费slot的最大值（刚启动时avaiable中的数量）
     *  2、提前（超限）消费slot后，要承担后果，即时间段内剩余时间，available中就没有可消费的slot了
     *  3、确定时间段内的消费量，即5秒内最多可以消费10个slot
     */
    public static void testBurstFilter() {
        int runSecs = 6;
        int count = 0;
        long targetTime = System.currentTimeMillis() + runSecs * 1000;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String st = sdf.format(new Date());
        System.out.println("currentTime : " + st);
        while (true) {
            if (System.currentTimeMillis() > targetTime) {
                break;
            }
            logger.info("abc");
            count++;
        }
        System.out.println("count : " + count);
        String et = sdf.format(new Date());
        System.out.println("finishTime : " + et);
    }
}
