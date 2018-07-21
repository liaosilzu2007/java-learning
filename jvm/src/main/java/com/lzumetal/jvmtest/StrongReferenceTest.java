package com.lzumetal.jvmtest;

import java.util.ArrayList;
import java.util.List;

public class StrongReferenceTest {

    static class BigObject {
        private Byte[] bytes = new Byte[1024 * 1024];
    }


    public static void main(String[] args) {
        List<BigObject> list = new ArrayList<>();
        while (true) {
            BigObject obj = new BigObject();
            list.add(obj);
        }
    }
}
