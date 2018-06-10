package com.lzumetal.multithread.threadpooltest;

public class ShortTask implements Runnable {

    @Override
    public void run() {
        System.out.println("complete a short task...");
    }

}
