package com.lzumetal.javalearn.struct.list;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * @author liaosi
 * @date 2022-01-15
 */
public class SkipList {

    private int levelCount;

    private Random random;



    public static void main(String[] args) {
        System.out.println(new Random().nextDouble());
    }

    @Getter
    @Setter
    static class SkipListNode<E extends Comparable<? super E>> {

        /* 节点数据 */
        private E value;

        private SkipListNode<E> next, down;

        public SkipListNode(E value) {
            this.value = value;
        }

    }

}
