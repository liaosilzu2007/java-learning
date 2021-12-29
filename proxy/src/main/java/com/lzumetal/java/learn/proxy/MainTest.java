package com.lzumetal.java.learn.proxy;

import com.lzumetal.java.learn.proxy.service.UserService;
import com.lzumetal.java.learn.proxy.service.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * @author liaosi
 * @date 2020-07-08
 */
public class MainTest {


    @Test
    public void test() {
        User user = new User();
        user.setId(2L);
        user.setName("Jack");
        user.setAge(8);
        UserService proxyUserService = (UserService) new DymicProxyImpl().getProxyInstance(new UserServiceImpl());
        proxyUserService.add(user);
    }

    @Test
    public void test2() {
        UserService service = new UserServiceImpl();
        UserService serviceProxy = (UserService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new IServiceProxy(service));
        User user = new User();
        user.setId(2L);
        user.setName("Jack");
        user.setAge(8);
        serviceProxy.add(user);
    }

}
