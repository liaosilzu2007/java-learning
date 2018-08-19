package com.lzumetal.serialize.protostuff;

import com.google.gson.Gson;
import com.lzumetal.serialize.entity.Student;
import org.junit.Test;

/**
 * @author liaosi
 * @date 2018-08-18
 */
public class ProtostuffSerialize {

    private byte[] byteArray;

    /**
     * 序列化：对象转byte数组
     */
    @Test
    public void serializeTest() {
        byteArray = ProtostuffUtil.serializer(new Student(1301, "xiaoming", 12));
        //byte[] bytes = ProtostuffUtil.serializer(new Country("法国", "巴黎"));
        System.out.println("序列化后字节数组的长度：" + byteArray.length + " 字节");
        for (byte aByte : byteArray) {
            System.out.print(aByte + " ");
        }
        System.out.println();
    }

    /**
     * byte数组转java对象
     */
    @Test
    public void deserializeTest() {
        this.serializeTest();
        Student student = ProtostuffUtil.deserializer(byteArray, Student.class);
        System.out.println(new Gson().toJson(student));
    }



}
