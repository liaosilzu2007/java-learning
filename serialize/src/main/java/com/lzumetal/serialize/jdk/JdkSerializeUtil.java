package com.lzumetal.serialize.jdk;

import java.io.*;


public class JdkSerializeUtil {


    /**
     * 将对象序列化为byte数组
     *
     * @param object
     * @return
     */
    public static byte[] serializeToBytes(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将对象序列化到文件
     *
     * @param object
     * @param file
     */
    public static void serializeToFile(Object object, File file) {
        try (OutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从byte数字反序列化为对象
     *
     * @param bytes
     * @return
     */
    public static Object deserializeFromBytes(byte[] bytes) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 从文件反序列化成对象
     *
     * @param file
     * @return
     */
    public static Object deserializeFromFile(File file) {
        try (InputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
