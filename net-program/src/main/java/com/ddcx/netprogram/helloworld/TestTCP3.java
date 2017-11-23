package com.ddcx.netprogram.helloworld;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaosi on 2017/7/11.
 */
//TCP编程例三：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的连接。
public class TestTCP3 {

    @Test
    public void client() {
        Socket socket = null;
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket(InetAddress.getByName("localhost"), 9092);
            //从本地获取一个文件发送给服务端
            fileInputStream = new FileInputStream("D:/temp/1.jpg");
            outputStream = socket.getOutputStream();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
            socket.shutdownOutput();
            //接收来自于服务端的信息
            InputStream inputStream = socket.getInputStream();
            while ((length = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
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
        }
    }

    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(9092);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            //将从客户端发送来的信息保存到本地
            fileOutputStream = new FileOutputStream("D:/temp/2.jpg");
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            System.out.println("接受到来自" + socket.getInetAddress().getHostName() + "的文件，接受完毕！");
            //socket.shutdownInput();
            //发送"接收成功"的信息反馈给客户端
            outputStream = socket.getOutputStream();
            String response = "服务端已接收完毕";
            outputStream.write(response.getBytes());
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
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
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
