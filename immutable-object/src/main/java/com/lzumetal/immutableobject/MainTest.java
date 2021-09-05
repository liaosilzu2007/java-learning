package com.lzumetal.immutableobject;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liaosi
 * @date 2021-08-21
 */
public class MainTest {

    @Test
    public void test() {
        String s = "Hello World";
        String s1 = s;

        System.out.println("after replace s:" + s.replace("World", "Bejing"));
        System.out.println("after replace s1:" + s1);
    }


    @Test
    public void test2() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        System.out.println(list);

        List unmodifiableList = Collections.unmodifiableList(list);
        ImmutableList immutableList = ImmutableList.copyOf(list);

        list.add(2);
        System.out.println(unmodifiableList);
        System.out.println(immutableList);

    }


    @Test
    public void test03() throws NoSuchFieldException, IllegalAccessException {
        String s = "Hello World";
        System.out.println("s = " + s);

        Field valueFieldOfString = String.class.getDeclaredField("value");
        valueFieldOfString.setAccessible(true);

        char[] value = (char[]) valueFieldOfString.get(s);
        value[5] = '_';
        System.out.println("s = " + s);
    }
}
