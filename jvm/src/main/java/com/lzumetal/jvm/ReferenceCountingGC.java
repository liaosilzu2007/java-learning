package com.lzumetal.jvm;

/**
 * @author liaosi
 * @date 2022-02-08
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int SIZE_1MB = 1024 * 1024;

    /*
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * SIZE_1MB];


    /**
     * testGC方法执行后，objA和objB会不会被GC呢？
     */
    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //假设在这行发生GC，objA和objB是否能被回收
        System.gc();
    }


    public static void main(String[] args) {
        testGC();
    }


}
