package com.lzumetal.java.learn.proxy;

import com.lzumetal.java.learn.proxy.service.IService;
import com.lzumetal.java.learn.proxy.service.ServiceImpl;

import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * @author liaosi
 * @date 2020-07-08
 */
public class MainTest {

    public static void main(String[] args) throws SQLException {
        IService service = new ServiceImpl();
        IService serviceProxy = (IService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new IServiceProxy(service));
        serviceProxy.foo();
    }

}
