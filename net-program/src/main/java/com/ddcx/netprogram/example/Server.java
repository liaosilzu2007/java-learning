package com.ddcx.netprogram.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaosi on 2017/11/23.
 */
public class Server {

    private static final int PORT = 9000;


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器启动了...");
            //服务器在此阻塞
            Socket socket = serverSocket.accept();

            //启动一个新的线程来处理请求
            new Thread(new ServerHandler(socket)).start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
