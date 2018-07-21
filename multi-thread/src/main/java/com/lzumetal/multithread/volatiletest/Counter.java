package com.lzumetal.multithread.volatiletest;

public class Counter {

    private volatile static int count = 0;

    private static void inc() {
        //延迟1毫秒，使得结果明显
        sleep(1);
        count++;
    }

    public static void main(String[] args) {

        //同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 1000; i++) {
            new Thread(Counter::inc).start();
        }

        sleep(1000);
        System.out.println("运行结果:Counter.count=" + Counter.count); //结果很可能<1000
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
