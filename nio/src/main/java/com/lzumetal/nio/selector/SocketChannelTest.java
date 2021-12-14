package com.lzumetal.nio.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.lzumetal.nio.selector.Constants.*;

/**
 * 类描述：NIO客户端
 * 创建人：liaosi
 * 创建时间：2017年12月08日
 */
@Slf4j
public class SocketChannelTest {


    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer;


    private void connectServer() {
        try {

            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(HOST, PORT));

            byteBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);

            if (socketChannel.finishConnect()) {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                sendData(("send data to server, from client " + Thread.currentThread().getName()).getBytes());
                log.info("send data end...");
                receive();
            } else {
                System.out.println("连接服务器失败...");
                System.exit(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void receive() throws IOException {
        int count;
        byteBuffer.clear();
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes, 0, count);
            log.info(Thread.currentThread().getName() + " receive from server:" + new String(bytes, StandardCharsets.UTF_8));
            byteBuffer.clear();
        }
    }

    private void sendData(byte[] bytes) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(bytes);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            socketChannel.write(byteBuffer);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new SocketChannelTest().connectServer();
                    }
                }, "client-" + i).start();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
        countDownLatch.await();
        log.info("all client connection completed !");
    }

}
