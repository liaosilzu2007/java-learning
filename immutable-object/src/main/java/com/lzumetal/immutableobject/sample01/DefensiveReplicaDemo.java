package com.lzumetal.immutableobject.sample01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liaosi
 * @date 2021-08-21
 */
public class DefensiveReplicaDemo {

    private final List<Integer> data = new ArrayList<>();

    public DefensiveReplicaDemo() {
        data.add(1);
        data.add(2);
        data.add(3);
    }

    public List<Integer> getData() {
        return Collections.unmodifiableList(data);
    }

    public static void main(String[] args) {
        DefensiveReplicaDemo defensiveReplicaDemo = new DefensiveReplicaDemo();
        List<Integer> data = defensiveReplicaDemo.getData();
        data.add(4);
    }
}
