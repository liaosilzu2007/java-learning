package com.ddcx.netprogram.example;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaosi on 2017/11/23.
 */
public class Example {

    @Test
    public void server() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("服务器启动了");
            socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readLine = bufferedReader.readLine();
            String line;
            StringBuilder info = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                info.append(line).append("\n");
            }
            System.out.println("服务端接收到了客户端发送的信息：" + readLine);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.write("我是服务端\n我已接收到了请求...");
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
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @Test
    public void client() {
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            socket = new Socket("127.0.0.1", 9000);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.write("我是客户端\n我发送了一个请求");
            socket.shutdownOutput();

            /*StringBuilder resp = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resp.append(line).append("\r");
            }*/
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("接收到服务端返回：" + bufferedReader.readLine());
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
