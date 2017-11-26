package com.ddcx.multithread.lock;

/**
 * Created by liaosi on 2017/11/26.
 */
public class LockInterruptiblyTest {

    public static void main(String[] args) {
        BussinessHandler bussinessHandler = new BussinessHandler();
        Thread thread1 = new Thread(bussinessHandler);
        thread1.setName("线程1");
        Thread thread2 = new Thread(bussinessHandler);
        thread2.setName("线程2");

        thread1.start();
        thread2.start();    //有一种可能是线程2拿到了锁

        sleep(2000);

        System.out.println("尝试去打断线程2...");
        thread2.interrupt();
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
