package com.lzumetal.multithread.counter;

import com.lzumetal.multithread.threadpool.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author liaosi
 * @date 2018-09-27
 */
public class CountDownTest1 {


    public static void main(String[] args) {
        List<String> mobiles = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            mobiles.add(StringUtil.getMobile());
        }

        ExecutorService threadPool = ThreadPoolUtil.getCustomThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(mobiles.size());

        for (String mobile : mobiles) {
            threadPool.submit(() -> {
                sleep();
                System.out.println(Thread.currentThread().getName() + "发送短信----" + mobile);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
            System.out.println("===========发送任务已全部完成===========");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
