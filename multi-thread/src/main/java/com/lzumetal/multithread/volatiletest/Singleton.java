package com.lzumetal.multithread.volatiletest;

public class Singleton {

    private volatile static Singleton instance;
    private static int a;
    private static int b;
    private static int c;


    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


}
