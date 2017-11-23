package com.ddcx.netprogram.example;

import java.io.*;
import java.net.Socket;

/**
 * Created by liaosi on 2017/11/23.
 */
public class Client {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 9000;


    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;

        try {
            socket = new Socket(IP, PORT);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("我是客户端\n我发送了一个请求");

            //shutdownOutput这个方法必须掉用，告诉服务端数据已经发送完毕，否则服务器一直会等待客户端，导致阻塞，也就不会向客户端发送返回数据
            socket.shutdownOutput();

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder resp = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resp.append(line).append("\n");
            }
            System.out.println("Client接收到服务端返回：\n" + resp);
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
