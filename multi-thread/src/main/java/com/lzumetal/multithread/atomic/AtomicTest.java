package com.lzumetal.multithread.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-01-17
 */
public class AtomicTest implements Runnable {

    private AtomicInteger atomicInteger = new AtomicInteger(100);
    private Integer normalInteger = 100;

    @Test
    public void test() throws Exception {
        AtomicTest atomicTest = new AtomicTest();
        for (int i = 0; i < 4; i++) {
            new Thread(atomicTest).start();
        }
        sleep(1000);
    }

    @Test
    public void testAtomic() {
        AtomicTest atomicTest = new AtomicTest();
        for (int i = 0; i < 4; i++) {
            new Thread(atomicTest).start();
        }
        sleep(1000);
    }


    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (atomicInteger.intValue() > 0) {
            System.out.println(Thread.currentThread().getName() + " atomicInteger ----> " + atomicInteger.decrementAndGet());
            sleep(10);
        }

       /* while (normalInteger > 0) {
            System.out.println(Thread.currentThread().getName() + " normalInteger ----> " + --normalInteger);
            sleep(10);
        }*/
    }
}
