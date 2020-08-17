package com.ddcx.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Jackson 使用演示
 * <p>
 * Created by liaosi on 2017/12/6.
 */
public class JacksonTest {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String STUDENT_JSON_STR = "{\"id\":2,\"name\":\"xiaoming\",\"age\":10}";

    @Test
    public void ignorePropertiesTest() throws IOException {

        //1.java对象 -> json 字符串
        Student student = new Student();
        student.setId(1);
        student.setName("zhangsan");
        student.setAge(12);

        System.out.println(objectMapper.writeValueAsString(student));
        //2.json字符串 -> java对象

        Student fromJson = objectMapper.readValue(STUDENT_JSON_STR, Student.class);
        System.out.println(fromJson);
    }

    @Test
    public void ignoreTypeTest() throws JsonProcessingException {

        Student student = new Student();
        student.setId(3);
        student.setName("xiaoming");
        student.setAge(12);

        Teacher teacher = new Teacher();
        teacher.setId(100);
        teacher.setName("wangwu");
        teacher.setSubject("math");
        student.setTeacher(teacher);

        System.out.println(objectMapper.writeValueAsString(student));
    }

    @Test
    public void includeTest() throws JsonProcessingException {
        Order order = new Order();
        order.setOrderNo("FEDI343539");
        order.setType((byte) 4);
        System.out.println(objectMapper.writeValueAsString(order));
    }
}
