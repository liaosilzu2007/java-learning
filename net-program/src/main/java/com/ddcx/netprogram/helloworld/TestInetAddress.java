package com.ddcx.netprogram.helloworld;

import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by liaosi on 2017/7/9.
 */
/*
 * 网络通信的第一个要素：IP地址。通过IP地址，唯一的定位互联网上一台主机
 * InetAddress:位于java.net包下
 * 1.InetAddress用来代表IP地址。一个InetAdress的对象就代表着一个IP地址
 * 2.如何创建InetAddress的对象：getByName(String host)
 * 3.getHostName(): 获取IP地址对应的域名
 *   getHostAddress():获取IP地址
 */
public class TestInetAddress {

    @Test
    public void test1() throws Exception {
        //创建一个InetAddress对象：getByName()
        InetAddress inetAddresses = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddresses);
        //两个方法
        System.out.println(inetAddresses.getHostName());
        System.out.println(inetAddresses.getHostAddress());

        System.out.println("--------------------------");

        //获取本机的IP：getLocalHost()
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getHostAddress());
    }

}
