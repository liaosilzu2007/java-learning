package com.lzumetal.multithread.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-11-11
 */
@Slf4j
public class DelayQueueTest {


    @Test
    public void orderDelayTest() throws InterruptedException {
        //声明一个延时队列
        DelayQueue<OrderDelay> queue = new DelayQueue<>();

        //启动一个消费者从延时队列里不断地拉取元素
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        OrderDelay orderDelay = queue.take();
                        orderDelay.dealTimeOutOrder();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "延时队列消费线程").start();

        //将订单延时任务放入队列
        for (int i = 1; i <= 5; i++) {
            queue.put(new OrderDelay(String.valueOf(i), TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS)));
            log.info("将订单延时任务放入队列|orderId={}", i);

            TimeUnit.SECONDS.sleep(new Random().nextInt(4));
        }

        //主线程睡眠，等待消费线程拉取任务完毕
        TimeUnit.SECONDS.sleep(20L);
    }


}
