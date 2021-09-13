package com.ddcx.netprogram.exer;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by liaosi on 2017/7/12.
 */
//客户端给服务端发送文本，服务端会将文本转成大写再发给客户端
public class TCPexer {

    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
//        Scanner scanner = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
            outputStream = socket.getOutputStream();
            /*System.out.println("请输入要发送的文本：");
            scanner = new Scanner(System.in);
            String msg = scanner.next();*/
            String msg = "abcdefg";
            outputStream.write(msg.getBytes());
            socket.shutdownOutput();

            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }
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
//            if (scanner != null) {
//                scanner.close();
//            }
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
            serverSocket = new ServerSocket(9090);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int length;
            StringBuilder response = new StringBuilder();
            while ((length = inputStream.read(bytes)) != -1) {
                response.append(new String(bytes, 0, length));
            }
            outputStream = socket.getOutputStream();
            if (StringUtils.isNotBlank(response.toString())) {
                outputStream.write(response.toString().toUpperCase().getBytes());
            }
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


    /**
     * IDEA中junit测试中无法获取键盘输入，但main方法中可以
     */
    @Test
    public void testScanner() {
        System.out.println("请输入字符串：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(str);
    }

    public static void main(String[] args) {
        System.out.println("请输入字符串：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(str);
    }
}
