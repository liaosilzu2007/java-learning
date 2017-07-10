package com.ddcx.net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaosi on 2017/7/10.
 */
public class TestTCP1 {

    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
            outputStream = socket.getOutputStream();
            String msg = "服务端你好，我是客户端，请多关照！";
            outputStream.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        try {
            serverSocket = new ServerSocket(9090);
            socket = serverSocket.accept();

            inputStream = socket.getInputStream();
            byte[] bytes = new byte[100];
            //inputStream.read(bytes)这个方法在没有接受到输入数据时，会一直在阻塞状态
            while (inputStream.read(bytes) != -1) {
                System.out.print(new String(bytes, 0, bytes.length));
            }
            System.out.println("\n服务端接受来自" + socket.getInetAddress().getHostName() + "的数据完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
