package com.ddcx.json.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liaosi on 2017/10/28.
 */
public class Staff {

    private String name;
    private Integer age;
    private String position;
    private BigDecimal salary;
    private List<String> skills;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
