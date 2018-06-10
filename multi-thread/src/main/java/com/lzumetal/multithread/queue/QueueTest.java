package com.lzumetal.multithread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liaosi on 2017/11/26.
 */
public class QueueTest {


    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        ExecutorService threadPool = null;
        try {
            threadPool = Executors.newCachedThreadPool();
            threadPool.submit(new Producer(queue));
            threadPool.submit(new Producer(queue));
            threadPool.submit(new Producer(queue));
            threadPool.submit(new Comsumer(queue));
        } finally {
            if (threadPool != null) {
                threadPool.shutdown();
            }
        }

    }

}
