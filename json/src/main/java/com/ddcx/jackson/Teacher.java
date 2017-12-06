package com.ddcx.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * Created by liaosi on 2017/12/6.
 */
/*
@JsonIgnoreType标注在类上，表示该类作为其他类的属性时，其他类在对象转Json字符串或者Json字符串转对象时忽略这个属性
 */
@JsonIgnoreType
public class Teacher {

    private int id;
    private String name;
    private String subject; //科目

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
