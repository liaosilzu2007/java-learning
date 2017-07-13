package com.ddcx.net;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by liaosi on 2017/7/11.
 */
//UDP编程的实现
public class TestUDP {

    @Test
    public void client() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
            //创建一个数据报：每一个数据报不能大于64k，都记录着数据信息，发送端的IP、端口号,以及要发送到的接收端的IP、端口号。
            String msg = "这是客户端发送至服务端的一份数据";
            byte[] bytes = msg.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName("localhost"), 9090);
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    @Test
    public void server() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(9090);
            byte[] bytes = new byte[1024];
            //byte[] bytes = new byte[10];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
            //receive方法会阻塞直到有datagramPacket被接受到，如果去掉这一步则服务器无法阻塞等待客户端信息发过来
            datagramSocket.receive(datagramPacket);

            byte[] data = datagramPacket.getData();
            System.out.println(new String(data, 0, data.length));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }

    }
}
