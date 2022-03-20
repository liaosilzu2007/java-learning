package com.lzumetal.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author liaosi
 * @date 2022-03-20
 */
public class ObjHeaderTest {

    private int i;

    public static void main(String[] args) {
        ObjHeaderTest objHeaderTest = new ObjHeaderTest();
        System.out.println(ClassLayout.parseInstance(objHeaderTest).toPrintable());
    }

}
