package com.ddcx.multithread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liaosi on 2017/11/26.
 */
public class TryLock implements Runnable {

    private static ArrayList<Integer> list = new ArrayList<>();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new TryLock()).start();
        sleep(100);
        new Thread(new TryLock()).start();
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        boolean tryLockResult = lock.tryLock();
        System.out.println(String.format(currentThread.getName() + " tryLock方法结果：%s", tryLockResult));

        if (tryLockResult) {
            System.out.println(currentThread.getName() + " 得到了锁");
            try {
                for (int i = 0; i < 5; i++) {
                    list.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(currentThread.getName() + " 释放了锁");
            }
        }

    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
