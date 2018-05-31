package com.lzumetal.multithread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class IncrementTest {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.submit(new SyncronizedIncre());
        threadPool.submit(new AtomicIncre());
        threadPool.shutdown();
    }

}

class SyncronizedIncre implements Runnable {


    @Override
    public void run() {
        long val = 0L;
        long start = System.currentTimeMillis();
        synchronized (this) {
            while (val < 100000000L) {
                val++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("use synchronized cost: " + (end - start) + "ms");
    }
}


class AtomicIncre implements Runnable {

    @Override
    public void run() {
        AtomicLong val = new AtomicLong(0L);
        long start = System.currentTimeMillis();
        synchronized (this) {
            while (val.incrementAndGet() < 100000000L) {
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("use AtomicIncre cost: " + (end - start) + "ms");
    }
}
