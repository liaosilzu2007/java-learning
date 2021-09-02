package com.lzumetal.multithread.threadpool;

public class ShortTask implements Runnable {

    @Override
    public void run() {
        System.out.println("complete a short task...");
    }

}
