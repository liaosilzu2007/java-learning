package com.lzumetal.javalean.spi.service.impl;

import com.lzumetal.javalean.spi.service.HelloService;

/**
 * <p>Description: </p>
 *
 * @author liaosi
 * @date 2018-07-31
 */
public class EnglishHelloServiceImpl implements HelloService{

    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }

}
