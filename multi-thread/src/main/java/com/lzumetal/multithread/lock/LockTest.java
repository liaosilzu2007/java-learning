package com.lzumetal.multithread.lock;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liaosi on 2017/11/26.
 */
public class LockTest implements Runnable {

    private static Integer num = 100;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new LockTest()).start();

        new Thread(new LockTest()).start();

        TimeUnit.SECONDS.sleep(5L);
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            //lock.lock();    //获取锁
            //System.out.println(threadName + " get lock");

            for (int i = 0; i < 5; i++) {
                System.out.println("sell the num = " + (num--));
                Thread.sleep(500L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //lock.unlock();
            //System.out.println(threadName + " realse lock");
        }
    }
}
