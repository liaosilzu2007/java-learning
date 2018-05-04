package com.lzumetal.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：NIO客户端
 * 创建人：liaosi
 * 创建时间：2017年12月08日
 */
public class SocketChannelTest {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9090;
    private static final int BUFFER_SIZE = 1024;
    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer;


    private void connectServer() {
        try {

            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(HOST, PORT));

            byteBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);

            TimeUnit.SECONDS.sleep(1);
            if (socketChannel.finishConnect()) {
                sendData("send data to server".getBytes());
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
            System.out.println("receive from server:" + new String(bytes, "utf-8"));
            //System.out.println("receive from server:" + StandardCharsets.UTF_8.decode(byteBuffer));
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

    public static void main(String[] args) {
        try {
            new SocketChannelTest().connectServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
