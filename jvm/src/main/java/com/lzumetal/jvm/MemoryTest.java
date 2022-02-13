package com.lzumetal.jvm;

import java.io.PrintStream;

public class MemoryTest {

    private static PrintStream printStream = System.out;

    public static void main(String[] args) {
        //JVM试图使用的最大内存，默认是总内存的1/4
        long maxMemory = Runtime.getRuntime().maxMemory();
        printStream.println("maxMemory:" + maxMemory / 1024 / 1024 + "MB");

        //当前JVM使用的内存总量，默认总内存的1/64
        long totalMemory = Runtime.getRuntime().totalMemory();
        printStream.println("totalMemory:" + totalMemory / 1024 / 1024 + "MB");
    }
}
