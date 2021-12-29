package com.lzumetal.java.learn.proxy.service;

import com.lzumetal.java.learn.proxy.User;
import lombok.extern.slf4j.Slf4j;


/**
 * @author liaosi
 * @date 2020-07-08
 */
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public void add(User user) {
        log.info("新增用户|succ|id={}", user.getId());
    }

}
