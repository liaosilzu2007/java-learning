package com.lzumetal.multithread.threadpool;

import java.util.concurrent.TimeUnit;

public class LongTask implements Runnable {


    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("complete a long task");
    }


}
