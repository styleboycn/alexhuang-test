package com.alexhuang.java8.FutureTask;

import java.util.concurrent.*;

public class FutureTaskTest {

    public static void main(String[] args) {

        MyCallable callable1 = new MyCallable(1000);// 要执行的任务
//        MyCallable callable2 = new MyCallable(2000);

        FutureTask<String> futureTask1 = new FutureTask<>(callable1);// 将Callable写的任务封装到一个由执行者调度的FutureTask对象
//        FutureTask<String> futureTask2 = new FutureTask<>(callable2);
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            Thread.sleep(2000L);
            return Thread.currentThread().getName();
        });

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        while (true) {
            try {
                if (futureTask1.isDone() && futureTask2.isDone()) {//  两个任务都完成
                    System.out.println("Done");
                    executor.shutdown();                          // 关闭线程池和服务
                    return;
                }

                if (!futureTask1.isDone()) { // 任务1没有完成，会等待，直到任务完成
                    System.out.println("FutureTask1 output=" + futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if (s != null) {
                    System.out.println("FutureTask2 output=" + s);
                }

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                //do nothing
            }

        }
    }


}
