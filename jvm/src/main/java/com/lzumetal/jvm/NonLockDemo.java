package com.lzumetal.jvm;

/**
 * @author liaosi
 * @date 2022-03-20
 */
public class NonLockDemo {


    public static void main(String[] args) {
        contactString("aa", "bb", "cc");
    }


    public static String contactString(String s1, String s2, String s3) {
        return new StringBuffer().append(s1).append(s2).append(s3).toString();
    }


}
