package com.ddcx.nio;

import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by liaosi on 2017/12/4.
 */
public class TestSocketChannel {


    @Test
    public void nonBlockingIOClient() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 1234));
            socketChannel.configureBlocking(false);

            if (socketChannel.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(3);
                    String msg = "I'm the " + (i++) + "-th message from the client";
                    buffer.clear();
                    buffer.put(msg.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
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


    @Test
    public void blockingIOServer() {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            byte[] byteArray = new byte[1024];
            while (true) {
                Socket socket = serverSocket.accept();
                SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
                System.out.println("handle the request from client ----> " + remoteSocketAddress);
                InputStream inputStream = socket.getInputStream();
                int length;
                while ((length = inputStream.read(byteArray)) != -1) {
                    System.out.println(new String(byteArray, 0, length));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
