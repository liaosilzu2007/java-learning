package com.ddcx.multithread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liaosi on 2017/11/26.
 */
public class QueueTest {


    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Producer producer = new Producer(queue);
        Comsumer comsumer = new Comsumer(queue);

        for (int i = 0; i < 3; i++) {
            new Thread(producer).start();
        }

        //消费线程比生产线程多，会有两个线程取不到值导致一直阻塞
        for (int i = 0; i < 5; i++) {
            new Thread(comsumer).start();
        }

    }

}
