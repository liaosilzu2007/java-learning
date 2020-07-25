package com.lzumetal.java.learn.proxy.service;

import java.sql.SQLException;

/**
 * @author liaosi
 * @date 2020-07-08
 */
public class ServiceImpl implements IService {

    @Override
    public void foo() throws SQLException {
        throw new SQLException("throw an unchecked exception");
    }

}
