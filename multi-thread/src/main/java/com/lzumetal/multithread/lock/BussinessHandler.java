package com.lzumetal.multithread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liaosi on 2017/11/26.
 */
public class BussinessHandler implements Runnable {


    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        try {
            HandleInThread();
        } catch (InterruptedException e) {
            System.out.println(currentThread + " 被中断");
        }
    }

    public void handleBussiness() {
        long startTime = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE) {
                break;
            }
        }
    }

    public void HandleInThread() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        lock.lockInterruptibly();
        System.out.println(currentThread + " 得到了锁");
        try {
            handleBussiness();
        } finally {
            lock.unlock();
            System.out.println(currentThread + " 释放了锁");
        }
    }
}
