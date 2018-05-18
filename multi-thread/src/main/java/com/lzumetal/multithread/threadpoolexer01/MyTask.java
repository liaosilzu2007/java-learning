package com.lzumetal.multithread.threadpoolexer01;

import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("开始执行我的任务...");
        for (int i = 10; i > 0; i--) {
            System.out.println("任务执行还剩：" + i + "秒");
            sleep(1);
        }
        System.out.println("我的任务执行完毕！");
    }


    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
