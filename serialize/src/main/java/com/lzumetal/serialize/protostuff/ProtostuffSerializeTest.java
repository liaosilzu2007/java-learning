package com.lzumetal.serialize.protostuff;

import com.google.gson.Gson;
import com.lzumetal.serialize.entity.Country;
import com.lzumetal.serialize.entity.Student;
import org.junit.Test;

/**
 * @author liaosi
 * @date 2018-08-18
 */
public class ProtostuffSerializeTest {


    /**
     * 序列化：对象转byte数组
     */
    @Test
    public void serializeTest() {
        Student student = new Student(1301, "xiaoming", 12);
        byte[] byteArray = ProtostuffUtil.serializer(student);
        System.out.println("序列化后字节数组的长度：" + byteArray.length + " 字节");
        for (byte aByte : byteArray) {
            System.out.print(aByte + " ");
        }
        System.out.println();
    }


    /**
     * 即使类没有实现Serializable接口，Protostuff也可以序列化类的对象
     */
    @Test
    public void serializeTest2() {
        //Country类没有实现Serializable接口
        Country country = new Country("法国", "巴黎");
        byte[] byteArray = ProtostuffUtil.serializer(country);
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
        Student student = new Student(1301, "xiaoming", 12);
        byte[] byteArray = ProtostuffUtil.serializer(student);
        Student deserializer = ProtostuffUtil.deserializer(byteArray, Student.class);
        System.out.println(new Gson().toJson(deserializer));
    }


}
