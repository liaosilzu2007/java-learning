package com.lzumetal.java.learn.proxy;

import com.lzumetal.java.learn.proxy.service.ServiceImpl;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author liaosi
 * @date 2020-07-08
 */
public class ProxyGeneratorUtil {

    private static String DEFAULT_CLASS_NAME = "$Proxy";

    public static byte[] saveGenerateProxyClass(String path, Class<?>[] interfaces) {

        byte[] classFile = ProxyGenerator.generateProxyClass(DEFAULT_CLASS_NAME, interfaces);
        String filePath = path + "/" + DEFAULT_CLASS_NAME + ".class";
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classFile;
    }

    public static void main(String[] args) {

        Class<?> interfaces[] = {ServiceImpl.class};
        //运行时，确保目录存在
        saveGenerateProxyClass("d:\\", interfaces);


    }

}
