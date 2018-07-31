package com.lzumetal.javalean.spi.service.impl;

import com.lzumetal.javalean.spi.service.HelloService;

/**
 * <p>Description: </p>
 *
 * @author liaosi
 * @date 2018-07-31
 */
public class ChineseHelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "你好，" + name;
    }

}
