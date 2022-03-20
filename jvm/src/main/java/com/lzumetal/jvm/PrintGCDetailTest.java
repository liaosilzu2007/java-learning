package com.lzumetal.jvm;

import org.junit.Test;

/**
 * @author liaosi
 * @date 2022-03-19
 */
public class PrintGCDetailTest {

    private static final int SIZE_1_MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * SIZE_1_MB];
        allocation2 = new byte[2 * SIZE_1_MB];
        allocation3 = new byte[2 * SIZE_1_MB];
        allocation4 = new byte[4 * SIZE_1_MB];
    }


    /**
     * JVM运行参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    @Test
    public void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * SIZE_1_MB];
        allocation2 = new byte[2 * SIZE_1_MB];
        allocation3 = new byte[2 * SIZE_1_MB];
        allocation4 = new byte[4 * SIZE_1_MB];
    }

}
