package com.lzumetal.multithread.sync;

/**
 * @author liaosi
 * @date 2022-03-20
 */
public class SychronizedDemo1 {

    private static final Object obj = new Object();

    public static void main(String[] args) {
        synchronized (obj){
            System.out.println("SychronizedDemo1...");
        }
    }





}
