package com.lzumetal.multithread.volatiletest;

import java.util.concurrent.CountDownLatch;

public class Counter {

    private volatile static int count = 0;

    private static final int THREADS_COUNT = 10;

    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

    private static void increase() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        //同时启动10个线程，每个线程进行1万次i++计算
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("运行结果:Counter.count=" + Counter.count); //结果会<100000
    }



}
