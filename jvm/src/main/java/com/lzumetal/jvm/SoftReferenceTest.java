package com.lzumetal.jvm;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftReferenceTest {

    static class Person {

        private String name;
        private Byte[] bytes = new Byte[1024 * 1024];

        public Person(String name) {
            this.name = name;
        }
    }

    @Test
    public void test() throws InterruptedException {
        Person person = new Person("张三");
        SoftReference<Person> softReference = new SoftReference<>(person);

        person = null;  //去掉强引用，new Person("张三")的这个对象就只有软引用了

        System.gc();
        Thread.sleep(1000);

        System.err.println("软引用的对象 ------->" + softReference.get());
    }

    @Test
    public void test2() throws InterruptedException {
        Person person = new Person("张三");
        SoftReference<Person> softReference = new SoftReference<>(person);

        person = null;//去掉强引用，new Person("张三")的这个对象就只有软引用了

        Person anotherPerson = new Person("李四");
        Thread.sleep(1000);

        System.err.println("软引用的对象 ------->" + softReference.get());
    }

    @Test
    public void test3() throws InterruptedException {
        Person person = new Person("张三");
        ReferenceQueue<Person> queue = new ReferenceQueue<>();
        SoftReference<Person> softReference = new SoftReference<Person>(person, queue);

        person = null;//去掉强引用，new Person("张三")的这个对象就只有软引用了

        Person anotherPerson = new Person("李四");
        Thread.sleep(1000);

        System.err.println("软引用的对象 ------->" + softReference.get());

        Reference softPollRef = queue.poll();
        if (softPollRef != null) {
            System.err.println("SoftReference对象中保存的软引用对象已经被GC，准备清理SoftReference对象");
            //清理softReference
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Person person = new Person("张三");
        ReferenceQueue<Person> queue = new ReferenceQueue<>();
        WeakReference<Person> weakReference = new WeakReference<Person>(person, queue);

        person = null;//去掉强引用，new Person("张三")的这个对象就只有软引用了

        System.gc();
        Thread.sleep(1000);
        System.err.println("弱引用的对象 ------->" + weakReference.get());

        Reference weakPollRef = queue.poll();   //poll()方法是有延迟的
        if (weakPollRef != null) {
            System.err.println("WeakReference对象中保存的弱引用对象已经被GC，下一步需要清理该Reference对象");
            //清理softReference
        } else {
            System.err.println("WeakReference对象中保存的软引用对象还没有被GC，或者被GC了但是获得对列中的引用对象出现延迟");
        }
    }
}
