package com.lzumetal.serialize.jdk;

import com.google.gson.Gson;
import com.lzumetal.serialize.entity.Student;
import org.junit.Test;

import java.io.*;

public class JdkSerialize {

    private byte[] byteArray;

    //序列化（对象转byte数组）
    @Test
    public void serializeToByteArray() {
        Student student = new Student(1301, "xiaoming", 12);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            oos.flush();

            byte[] bytes = baos.toByteArray();
            byteArray = bytes;
            System.out.println("序列化完成，bytes的长度是：" + byteArray.length + " 字节");

            for (byte aByte : bytes) {
                System.out.print(aByte + " ");
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化（到文件）
     */
    @Test
    public void serializeToFile() {

        Student student = new Student(1301, "xiaoming", 12);
        File file = new File("d:/jdk_serialize_test");
        try (OutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(student);
            oos.flush();
            System.out.println("序列化完成，文件的大小是：" + file.length() + " 字节");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 反序列化（读取文件字节内容转对象）
     */
    @Test
    public void deserializeFromFile() {
        try (InputStream fis = new FileInputStream("d:/jdk_serialize_test");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Student obj = (Student) ois.readObject();
            System.out.println(new Gson().toJson(obj));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化：字节数组转对象
     */
    @Test
    public void deserializeFromByteArray() {
        this.serializeToByteArray();
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
             ObjectInputStream ois = new ObjectInputStream(inputStream)) {

            Student student = (Student) ois.readObject();
            System.out.println(new Gson().toJson(student));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}


