package com.lzumetal.multithread.queue;

import com.lzumetal.multithread.threadpool.ThreadPoolUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liaosi on 2017/11/26.
 */
public class QueueTest {


    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        ExecutorService threadPool = ThreadPoolUtil.getCustomThreadPool();
        //创建3个生产者和1个消费
        threadPool.submit(new Producer(queue));
        threadPool.submit(new Producer(queue));
        threadPool.submit(new Producer(queue));
        threadPool.submit(new Comsumer(queue));
    }

}
