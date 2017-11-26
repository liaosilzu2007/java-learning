package com.ddcx.multithread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liaosi on 2017/11/26.
 */
public class LockTest implements Runnable {

    private static ArrayList<Integer> list = new ArrayList<>();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(new LockTest()).start();

        new Thread(new LockTest()).start();
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        try {
            lock.lock();    //获取锁
            System.out.println(currentThread.getName() + " 得到了锁");

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
