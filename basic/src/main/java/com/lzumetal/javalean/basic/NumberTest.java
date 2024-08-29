package com.lzumetal.javalean.basic;

import org.junit.Test;

/**
 * @author liaosi
 */
public class NumberTest {


    public static void main(String[] args) {

    }


    @Test
    public void DoubleTest() {
        double d1 = 10D / 0;
        double d2 = -10D / 0;
        double d3 = 0.0D / 0;
        System.out.println("d1=" + d1 + ", d2=" + d2 + ", d3=" + d3);       //输出结果：d1=Infinity, d2=-Infinity, d3=NaN

        System.out.println(new Double(d1).isNaN());     //输出结果：false
        System.out.println(new Double(d3).isNaN());     //输出结果：true
        System.out.println(d1 == d1);                   //输出结果：true
        System.out.println(d3 == d3);                   //输出结果：false

        System.out.println((int) d1);                   //输出结果：2147483647
        System.out.println((int) d3);                   //输出结果：0

    }


}
