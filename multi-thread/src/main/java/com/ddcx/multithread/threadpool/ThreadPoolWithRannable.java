package com.ddcx.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liaosi on 2017/11/26.
 */
public class ThreadPoolWithRannable {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        //提交10个任务到线程池
        for (int i = 1; i <= 10; i++) {
            final int task = i;
            //TimeUnit.SECONDS.sleep(1);
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println("线程名字： " + Thread.currentThread().getName() + "  任务名为： " + task);
                }
            });
        }
    }
}
