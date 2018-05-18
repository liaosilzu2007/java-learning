package com.lzumetal.multithread.threadpoolexer01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPoolUtils {


    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    public static void execute(Runnable command) {
        fixedThreadPool.execute(command);
    }

    public static ExecutorService getFixedThreadPool() {
        return fixedThreadPool;
    }


}
