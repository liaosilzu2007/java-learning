package com.lzumetal.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author liaosi
 * @date 2022-03-20
 */
public class BiasedLockTest {


    public static void main(String[] args) {
        BiasedLockTaskThread thread = new BiasedLockTaskThread();
        thread.start();
    }


    static class BiasedLockTaskThread extends Thread {

        static final Object obj = new Object();

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                synchronized (obj) {
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
            }
        }
    }
}
