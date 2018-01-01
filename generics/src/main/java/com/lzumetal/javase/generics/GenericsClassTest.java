package com.lzumetal.javase.generics;

/**
 * 类描述：泛型类测试
 * 创建人：liaosi
 * 创建时间：2017年12月31日
 */
public class GenericsClassTest<A, B> {

    private A first;
    private B second;

    public GenericsClassTest(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static void main(String[] args) {
        GenericsClassTest<String, Integer> genericsClassTest = new GenericsClassTest<>("abc", 666);
        genericsClassTest.first = "modify";
        //genericsClassTest.first = 123;
        System.out.println(genericsClassTest.first);
    }

}
