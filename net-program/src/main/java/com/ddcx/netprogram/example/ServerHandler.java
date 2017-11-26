package com.ddcx.netprogram.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by liaosi on 2017/11/23.
 */
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            //SoketInputStream 读数据的方式都是阻塞的
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            String line;
            StringBuilder info = new StringBuilder();
            //注意：如果客户端发送的一行字符串末尾没有换行符，则readline方法将一直阻塞
            while ((line = bufferedReader.readLine()) != null) {
                info.append(line).append("\n");
            }
            System.out.println("Server接收到了客户端发送的信息：\n" + info);
            printWriter.println("我是服务端\n我已接收到了请求...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (printWriter != null) {
                printWriter.close();
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
}
