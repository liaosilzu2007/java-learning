package com.lzumetal.multithread.threadpoolexer01;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestShutdown {

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        fixedThreadPool.execute(new MyTask());

        TimeUnit.SECONDS.sleep(1L);
        System.out.println("即将开始关闭连接池...");
        List<Runnable> runnables = fixedThreadPool.shutdownNow();
        System.out.println(runnables.size());
        System.out.println("连接池已关闭!");
    }

}
