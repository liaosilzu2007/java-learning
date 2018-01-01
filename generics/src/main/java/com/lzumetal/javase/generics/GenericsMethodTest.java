package com.lzumetal.javase.generics;

import java.util.Arrays;

/**
 * 类描述：泛型方法测试
 * 创建人：liaosi
 * 创建时间：2017年12月31日
 */
public class GenericsMethodTest {


    public static <T> T get(T t) {
        return t;
    }

    public static void main(String[] args) {
        System.out.println(GenericsMethodTest.get(Arrays.asList(1, 3, 5)));
        System.out.println(GenericsMethodTest.get("test..."));
    }

}
