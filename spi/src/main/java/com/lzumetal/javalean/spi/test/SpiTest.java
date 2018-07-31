package com.lzumetal.javalean.spi.test;

import com.lzumetal.javalean.spi.service.HelloService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * <p>Description: </p>
 *
 * @author liaosi
 * @date 2018-07-31
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<HelloService> serviceLoader = ServiceLoader.load(HelloService.class);
        Iterator<HelloService> serviceIterator = serviceLoader.iterator();
        while (serviceIterator != null && serviceIterator.hasNext()) {
            HelloService helloService = serviceIterator.next();
            System.out.println("calss:" + helloService.getClass().getName() +
                    "|sayHello method:" + helloService.sayHello("world"));
        }
    }
}
