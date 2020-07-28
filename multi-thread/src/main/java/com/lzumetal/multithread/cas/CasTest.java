package com.lzumetal.multithread.cas;

import java.util.concurrent.CountDownLatch;

/**
 * @author liaosi
 * @date 2020-07-28
 */
public class CasTest {


    private static volatile int race = 0;

    private static final int THREADS_COUNT = 20;

    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);


    private static void increase() {
        race++;
    }


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("result=================>" + race);

    }
}
