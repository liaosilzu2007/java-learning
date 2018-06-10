package com.lzumetal.multithread.volatiletest;

public class IncreaseTest implements Runnable {

    private volatile static int i = 0;


    @Override
    public void run() {
        sleep(2);
        while (i < 1000) {
            i++;
        }
    }

    public static void main(String[] args) {
        for (int j = 0; j < 20; j++) {
            new Thread(new IncreaseTest()).start();
        }
        sleep(1000);
        System.out.println("result is :" + i);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
