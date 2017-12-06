package com.ddcx.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by liaosi on 2017/12/6.
 */
/*
@JsonIgnoreProperties标注在类上，作用和@JsonIgore类似，但是@JsonIgnoreProperties可以指定多个属性
 */
@JsonIgnoreProperties(value = {"age"})
public class Student {

    /*
    @JsonIgore注解标注在属性上，表示在将对象转成Json字符串，或者Json字符串转成对象时，忽略掉该属性
     */

    @JsonIgnore
    private Integer id;

    private String name;

    private int age;

    private Teacher teacher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", teacher=" + teacher +
                '}';
    }
}
