package com.ddcx.net;

import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by liaosi on 2017/7/12.
 */
/*
 URL:统一资源定位符，一个URL的对象，对应着互联网上一个资源。
 我们可以通过URL的对象调用其相应的方法，将此资源读取（“下载”）
 */
public class TestURL {

    @Test
    public void test1() throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:8080/examples/exer.txt?id=3456");
        /*
		 *  public String getProtocol(  )     获取该URL的协议名
			public String getHost(  )           获取该URL的主机名
			public String getPort(  )            获取该URL的端口号
			public String getPath(  )           获取该URL的文件路径
			public String getFile(  )             获取该URL的文件名
			public String getRef(  )             获取该URL在文件中的相对位置
			public String getQuery(   )        获取该URL的查询名
		 */
        System.out.println(url.getProtocol());      //  http
        System.out.println(url.getHost());      //  127.0.0.1
        System.out.println(url.getPort());      //  8080
        System.out.println(url.getFile());      //  /examples/exer.txt?id=3456
        System.out.println(url.getPath());      //  /examples/exer.txt
        System.out.println(url.getRef());       //  null
        System.out.println(url.getQuery());     //  id=3456
    }

    //如何将服务端的资源读取进来:openStream()
    @Test
    public void test2() {
        InputStream inputStream = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/examples/exer.txt?id=3456");
            inputStream = url.openStream();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
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
        }
    }

    //如果既有数据的输入，又有数据的输出，则考虑使用URLConnection
    @Test
    public void test3() {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/examples/exer.txt?id=3456");
            URLConnection urlConnection = url.openConnection();
            inputStream = urlConnection.getInputStream();
            fileOutputStream = new FileOutputStream("d:/copy.txt");
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
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
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
