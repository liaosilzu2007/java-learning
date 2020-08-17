package com.lzumetal.multithread.counter;

import java.util.Random;

/**
 * @author liaosi
 * @date 2018-10-03
 */
public class StringUtil {


    public static String getMobile() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("173");
        for (int i = 0; i < 8; i++) {
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }


}
