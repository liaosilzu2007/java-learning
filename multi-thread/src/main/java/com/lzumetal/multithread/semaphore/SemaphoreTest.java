package com.lzumetal.multithread.semaphore;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-08-17
 */
public class SemaphoreTest {

    ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Test
    public void test() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            threadPool.submit(() -> {
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(new Random(5).nextInt());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
    }
}
