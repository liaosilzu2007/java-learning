package com.lzumetal.multithread.threadlocal;

public class EnvUtil {

    private final static ThreadLocal<Integer> userIdContext = new ThreadLocal<>();

    public static ThreadLocal<Integer> getUserIdContext() {
        return userIdContext;
    }


}
