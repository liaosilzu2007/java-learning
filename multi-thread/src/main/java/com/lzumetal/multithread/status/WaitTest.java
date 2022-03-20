package com.lzumetal.multithread.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-09-05
 */
@Slf4j
public class WaitTest {

    private static final Object obj = new Object();

    @Test
    public void testWait() throws InterruptedException {
        new Thread(() -> {
            synchronized (obj) {
                log.info("线程获取到锁");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程任务执行完成");
            }
        }, "wait线程").start();

        TimeUnit.SECONDS.sleep(1L);
        new Thread(() -> {
            synchronized (obj) {
                try {
                    log.info("线程获取到锁");
                    TimeUnit.SECONDS.sleep(5L);
                    obj.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "notify线程").start();

        //睡眠6秒，保证两个线程都执行完
        TimeUnit.SECONDS.sleep(6L);
    }
}
