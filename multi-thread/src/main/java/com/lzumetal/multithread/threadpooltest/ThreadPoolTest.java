package com.lzumetal.multithread.threadpooltest;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {


    @Test
    public void testShutdown() throws InterruptedException {
        ExecutorService threadpool = Executors.newFixedThreadPool(4);
        //将4个任务提交到有4个线程的线程池
        threadpool.submit(new ShortTask());
        threadpool.submit(new ShortTask());
        threadpool.submit(new LongTask());
        threadpool.submit(new ShortTask());

        //关闭线程池
        threadpool.shutdown();

        boolean isShutdown = threadpool.isShutdown();
        System.out.println("线程池是否已经关闭：" + isShutdown);

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while (!threadpool.awaitTermination(1L, TimeUnit.SECONDS)) {
            System.out.println("线程池中还有任务在执行，当前时间：" + sdf.format(new Date()));
        }

        System.out.println("线程池中已经没有在执行的任务，线程池已完全关闭！");
    }


    @Test
    public void testShutdownNow() throws InterruptedException {
        ExecutorService threadpool = Executors.newFixedThreadPool(3);
        //将5个任务提交到有3个线程的线程池
        threadpool.submit(new LongTask());
        threadpool.submit(new LongTask());
        threadpool.submit(new LongTask());
        threadpool.submit(new LongTask());
        threadpool.submit(new LongTask());

        //主线程睡眠2秒钟，让3个线程池的任务都开始执行
        TimeUnit.SECONDS.sleep(1L);

        //关闭线程池
        List<Runnable> waiteRunnables = threadpool.shutdownNow();
        System.out.println("还没有执行的任务数：" + waiteRunnables.size());

        boolean isShutdown = threadpool.isShutdown();
        System.out.println("线程池是否已经关闭：" + isShutdown);

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while (!threadpool.awaitTermination(1L, TimeUnit.SECONDS)) {
            System.out.println("线程池中还有任务在执行，当前时间：" + sdf.format(new Date()));
        }

        System.out.println("线程池中已经没有在执行的任务，线程池已完全关闭！");
    }

    @Test
    public void testSubmitCallable() {

        ExecutorService threadpool = null;
        try {

            threadpool = Executors.newFixedThreadPool(3);

            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            System.out.println("提交一个callable任务到线程池，现在时间是：" + sdf.format(new Date()));

            Future<String> future = threadpool.submit(new CallableTask());

            System.out.println("获取callable任务的结果：" + future.get() + "，现在时间是：" + sdf.format(new Date()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (threadpool != null) {
                threadpool.shutdown();
            }
        }

    }


    @Test
    public void testSubmitCallable2() {

        ExecutorService threadpool = null;
        try {
            threadpool = Executors.newFixedThreadPool(3);

            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            System.out.println("提交一个callable任务到线程池，现在时间是：" + sdf.format(new Date()));

            String result = null;
            Future<String> future = threadpool.submit(new ShortTask(), result);

            System.out.println("获取callable任务的结果：" + future.get() + "，现在时间是：" + sdf.format(new Date()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (threadpool != null) {
                threadpool.shutdown();
            }
        }
    }


}
