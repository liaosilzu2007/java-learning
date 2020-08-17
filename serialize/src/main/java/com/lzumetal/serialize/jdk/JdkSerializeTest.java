package com.lzumetal.serialize.jdk;

import com.google.gson.Gson;
import com.lzumetal.serialize.entity.Student;
import org.junit.Test;

import java.io.*;

public class JdkSerializeTest {

    /**
     * 序列化（为byte数组）
     */
    @Test
    public void serializeToByteArray() {
        Student student = new Student(1301, "xiaoming", 12);
        byte[] bytes = JdkSerializeUtil.serializeToBytes(student);
        System.out.println("序列化完成，bytes的长度是：" + bytes.length + " 字节");
        for (byte aByte : bytes) {
            System.out.print(aByte + " ");
        }
    }

    /**
     * 序列化（到文件）
     */
    @Test
    public void serializeToFile() {
        Student student = new Student(1301, "xiaoming", 12);
        File file = new File("d:/jdk_serialize_test");
        JdkSerializeUtil.serializeToFile(student, file);
        System.out.println("序列化完成，文件的大小是：" + file.length() + " 字节");

    }

    /**
     * 反序列化（读取文件字节内容转对象）
     */
    @Test
    public void deserializeFromFile() {
        File file = new File("d:/jdk_serialize_test");
        Object object = JdkSerializeUtil.deserializeFromFile(file);
        Student student = (Student) object;
        System.out.println(new Gson().toJson(student));
    }

    /**
     * 反序列化：字节数组转对象
     */
    @Test
    public void deserializeFromByteArray() {
        Student student = new Student(1301, "xiaoming", 12);
        byte[] bytes = JdkSerializeUtil.serializeToBytes(student);
        Student fromBytes = (Student) JdkSerializeUtil.deserializeFromBytes(bytes);
        System.out.println(new Gson().toJson(fromBytes));
    }



}


