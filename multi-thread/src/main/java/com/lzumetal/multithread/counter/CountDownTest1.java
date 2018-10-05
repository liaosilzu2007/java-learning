package com.lzumetal.multithread.counter;

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

        ExecutorService threadPool = new ThreadPoolExecutor(2, 10,
                3000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        CountDownLatch countDownLatch = new CountDownLatch(mobiles.size());
        try {
            for (String mobile : mobiles) {
                threadPool.submit(() -> {
                    sleep();
                    System.out.println(Thread.currentThread().getName() + "发送短信。。。" + mobile);
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
            System.out.println("===========已全部添加到线程池===========");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
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
