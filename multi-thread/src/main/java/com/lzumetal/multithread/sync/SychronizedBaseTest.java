package com.lzumetal.multithread.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liaosi
 * @date 2022-03-20
 */
@Slf4j
public class SychronizedBaseTest {

    private static final Object obj = new Object();


    public synchronized void method1() {
        log.info("线程{}获取到了锁", Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static synchronized void method2() {
        log.info("线程{}获取到了锁", Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void blockTest1() {
        synchronized (this) {
            log.info("线程{}获取到了锁", Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void blockTest2() {
        synchronized (obj) {
            log.info("线程{}获取到了锁", Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void blockTest3() {
        synchronized (SychronizedBaseTest.class) {
            log.info("线程{}获取到了锁", Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
