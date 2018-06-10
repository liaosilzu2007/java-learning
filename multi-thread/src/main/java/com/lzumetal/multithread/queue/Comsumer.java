package com.lzumetal.multithread.queue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by liaosi on 2017/11/26.
 */
public class Comsumer implements Runnable {

    private BlockingQueue<String> queue;

    public Comsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    /**
     * 消费者一直不停地消费
     */
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("----comsumer:" + Thread.currentThread().getName() + " try to comsume a product");
                String takedProduct = queue.take();
                System.out.println("----comsumer:" + Thread.currentThread().getName() + " get a product = \"" + takedProduct + "\"");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
