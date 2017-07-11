package com.ddcx.net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaosi on 2017/7/11.
 */
//TCP编程例二：客户端给服务端发送信息，服务端将信息打印到控制台上，同时发送“已收到信息”给客户端
public class TestTCP2 {

    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9091);
            outputStream = socket.getOutputStream();
            String msg = "服务端你好，我是客户端！";
            outputStream.write(msg.getBytes());
            //shutdownOutput():执行此方法，显式的告诉服务端发送完毕！
            socket.shutdownOutput();

            inputStream = socket.getInputStream();
            byte[] bytes = new byte[10];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                //如果byte数组的长度太小，有可能会打印乱码，因为有可能个别字符在没有读取完毕的时候就进行打印
                System.out.print(new String(bytes, 0, length));
            }
            System.out.println("\n接受到来自" + socket.getInetAddress().getHostName() + "的信息完毕！");
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
        }
    }

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(9091);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[100];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, length));
            }
            System.out.println("\n接受来自客户端" + socket.getInetAddress().getHostName() + "的信息！");

            outputStream = socket.getOutputStream();
            String response = "你好，我是服务端";
            outputStream.write(response.getBytes("utf-8"));
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
