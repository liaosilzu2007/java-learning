package com.ddcx.nio.channel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by liaosi on 2017/11/24.
 */
public class FileChannalTest {

    //利用通道完成文件的复制（非直接缓冲区）
    @Test
    public void test1() {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("C:/Users/liaosi/Desktop/1.jpg");
            fos = new FileOutputStream("C:/Users/liaosi/Desktop/2.jpg");

            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //将通道中的数据读取写到缓冲区中
            while (inChannel.read(buffer) != 1) {
                //切换缓冲区为读模式
                buffer.flip();
                //将缓冲区中的数据写入通道中
                outChannel.write(buffer);
                //清空缓冲区
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inChannel != null) {
                    inChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (outChannel != null) {
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //使用直接缓冲区完成文件的复制（内存映射文件，只有 ByteBuffer 支持内存映射文件）
    @Test
    public void test2() throws IOException {

        FileChannel inChannel = FileChannel.open(Paths.get("C:/Users/liaosi/Desktop/1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("C:/Users/liaosi/Desktop/3.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] bytes = new byte[inMapBuffer.limit()];
        inMapBuffer.get(bytes);
        outMapBuffer.put(bytes);

        inChannel.close();
        outChannel.close();
    }

    //通道之间直接传输数据（直接缓冲区）
    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("C:/Users/liaosi/Desktop/1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("C:/Users/liaosi/Desktop/4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //数据从一个通道传输到另外一个通道
        //inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();

    }

}
