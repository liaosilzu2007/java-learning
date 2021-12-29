package com.lzumetal.java.learn.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liaosi
 * @date 2021-12-28
 */
@Slf4j
public class DymicProxyImpl implements InvocationHandler {

    private Object targetObject;

    public Object getProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logMethodParams(args); //在执行目标方法时，先执行一段指定的代码
        return method.invoke(targetObject, args);
    }

    //记录方法参数
    private void logMethodParams(Object[] args) {
        log.info("方法入参|{}", args);
    }
}
