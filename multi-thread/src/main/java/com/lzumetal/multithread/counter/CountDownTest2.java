package com.lzumetal.multithread.counter;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2018-10-03
 */
public class CountDownTest2 {

    public static void main(String[] args) throws InterruptedException {
        int COUNT = 5;
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT);
        for (int i = 0; i < COUNT; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    begin.await();
                    System.out.println("运动员" + finalI + "开跑");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println("运动员" + finalI + "已到达终点！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }

            });
        }
        System.out.println("倒计时3秒.....");
        TimeUnit.SECONDS.sleep(3);
        begin.countDown();
        System.out.println("比赛开始......");
        end.await();
        System.out.println("比赛结束......");
    }

}
