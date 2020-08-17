package com.lzumetal.serialize.jdk;

import com.lzumetal.serialize.entity.User;
import org.junit.Test;

import java.io.Serializable;

/**
 * @author liaosi
 * @date 2020-08-16
 */
public class TransientTest implements Serializable {


    @Test
    public void test() {
        User user = new User();
        user.setId(10000L);
        user.setUsername("admin");
        user.setPassword("123456");
        byte[] bytes = JdkSerializeUtil.serializeToBytes(user);
        Object object = JdkSerializeUtil.deserializeFromBytes(bytes);
        System.out.println(object);
    }



}
