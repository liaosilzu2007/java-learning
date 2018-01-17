package com.lzumetal.multithread.threadpool;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by liaosi on 2017/11/26.
 */
public class ThreadPoolWithCallable {

    @Test
    public void test1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            //提交10个任务到线程池
            for (int i = 0; i < 10; i++) {

                Future<Integer> result = threadPool.submit(new Callable<Integer>() {

                    @Override
                    public Integer call() throws Exception {
                        System.out.print(Thread.currentThread().getName() + "\t");
                        TimeUnit.SECONDS.sleep(1);
                        return new Random().nextInt(10);
                    }
                });

                System.out.println("--------" + result.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }


    @Test
    public void test2() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        ScheduledFuture<Integer> result = null;
        try {

            for (int i = 1; i <= 20; i++) {
                result = service.schedule(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.print(Thread.currentThread().getName() + "\t");
                        return new Random().nextInt(10);
                    }
                }, 3, TimeUnit.SECONDS);

                System.out.println("*****: " + result.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }


    }


}
