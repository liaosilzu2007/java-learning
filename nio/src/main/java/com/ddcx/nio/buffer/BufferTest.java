package com.ddcx.nio.buffer;


import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Created by liaosi on 2017/7/2.
 */
public class BufferTest {

    @Test
    public void test1() {

        String str = "abcde";

        //1.分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("---------allocate---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //2.使用put()方法存入数据到缓冲区
        byteBuffer.put(str.getBytes());
        System.out.println("---------put---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //3.flip()方法切换读取数据模式
        byteBuffer.flip();
        System.out.println("---------flip---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //4.利用get()方法读取缓冲区中的数据
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("---------get---------");
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //5.利用rewind()方法可以再次重新读取
        byteBuffer.rewind();
        System.out.println("---------rewind---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //6.clear()方法，清空缓冲区，但是缓冲区中的数据依然存在，处于一个“被遗忘”的状态
        byteBuffer.clear();
        System.out.println("---------clear---------");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println((char) byteBuffer.get());
    }

    @Test
    public void test2() {
        String str = "abcde";

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];

        /*
        get(byte[] dst, int offset, int length)方法：
            从缓冲区读取length个元素，写入到byte数组索引为offset的位置。
            如果缓冲区中的元素数量不够（读取时会超过limit值），或者byte数组的容量不够，都会报错。
         */

        byteBuffer.get(bytes, 0, 2);
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.position());

        //mark()方法：标记
        byteBuffer.mark();
        byteBuffer.get(bytes, 1, 3);
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.position());

        //reset()方法：恢复到mark的位置
        byteBuffer.reset();
        System.out.println(byteBuffer.position());
    }

    @Test
    public void test3() {
        //创建直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        //isDrect()方法可以判断是否是直接缓冲区
        System.out.println(byteBuffer.isDirect());

    }
}
