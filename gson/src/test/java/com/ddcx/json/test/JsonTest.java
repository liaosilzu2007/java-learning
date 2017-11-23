package com.ddcx.json.test;

import com.ddcx.json.entity.LoginResult;
import com.ddcx.json.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaosi on 2017/10/28.
 */
public class JsonTest {


    @Test
    public void test2() {
        LoginResult loginResult = new LoginResult();
        loginResult.setId(983873L);
        loginResult.setToken("uEzf/CX+QSBVWf4UDdTbWnX1d0/8G8iCjrZUBLt4t7cdzjx+kTApyxKeo9Y7TMLQPngpMNuoAH/kLZ9mamh6zs==");

        Gson gson = new Gson();
        System.out.println("测试1：使用默认的Gson对象调用toJson方法");
        System.out.println(gson.toJson(loginResult));

        System.out.println("========================================");

        Gson gson1 = new GsonBuilder().disableHtmlEscaping().create();
        System.out.println("测试1：使用禁用了html转椅的Gson对象调用toJson方法");
        System.out.println(gson1.toJson(loginResult));
    }


    @Test
    public void test() {

        Staff staff = createDummyObject();

//        Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(staff);
        System.out.println(json);

    }

    private Staff createDummyObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(35);
        staff.setPosition("Founder");
        staff.setSalary(new BigDecimal("10000"));

        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        skills.add("shell");

        staff.setSkills(skills);

        return staff;

    }

}
