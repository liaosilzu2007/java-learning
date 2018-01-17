package com.lzumetal.multithread.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by liaosi on 2017/11/26.
 */
public class Producer implements Runnable {

    private Random random = new Random();
    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("producer:" + Thread.currentThread().getName() + " start make a product");
            int millis = random.nextInt(5000);
            TimeUnit.MILLISECONDS.sleep(millis);
            String product = "A product：made by" + Thread.currentThread().getName() + "---takes " + millis + " milliseconds";
            System.out.println("producer:" + Thread.currentThread().getName() + " has made a product");
            queue.put(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
