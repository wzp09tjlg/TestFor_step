package com.zhongxin.home.testfor_step.bean;

/**
 * Created by Walter on 2015/10/25.
 */
public class AnimalBean {
    private int id;
    private String name;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id:" + id + " name:" + name + " age:" + age;
    }
}
