package com.lzumetal.serialize.entity;

import java.io.Serializable;

/**
 * @author liaosi
 * @date 2018-08-18
 */
public class Student implements Serializable {

    private static final long serialVersionUID = -4963266899668807475L;

    private long id;

    private String name;

    private Integer age;

    public Student() {
    }

    public Student(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
