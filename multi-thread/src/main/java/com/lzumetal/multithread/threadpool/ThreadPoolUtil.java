package com.lzumetal.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-09-02
 */
public class ThreadPoolUtil {

    private static ExecutorService cachedThreadPool = new ThreadPoolExecutor(20, 50,
            5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static ExecutorService getCustomThreadPool() {
        return cachedThreadPool;
    }

}
