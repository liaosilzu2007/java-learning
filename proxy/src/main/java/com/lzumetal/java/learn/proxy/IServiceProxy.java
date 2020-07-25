package com.lzumetal.java.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 动态代理类
 *
 * @author liaosi
 * @date 2020-07-08
 */
public class IServiceProxy implements InvocationHandler {
    private Object target;

    IServiceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("入参：" + Arrays.toString(args));
        try {
            return method.invoke(target, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}