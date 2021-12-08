package com.ddcx.netprogram.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liaosi on 2017/11/23.
 */
public class Server {

    private static final int PORT = 9000;
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器启动了...");
            while (true) {
                //服务器在此阻塞
                Socket socket = serverSocket.accept();
                //提交给线程池中去处理请求
                threadPool.execute(new ServerSocketHandler(socket));
            }
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
