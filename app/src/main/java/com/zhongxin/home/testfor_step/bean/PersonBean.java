package com.zhongxin.home.testfor_step.bean;

/**
 * Created by Walter on 2015/10/21.
 */
public class PersonBean {
    public static String address;

    public static String getAddress(){
        return address;
    }

    private String name;
    private int age;

    public String info = "she is beatyfil and she is kindfull and she is angle,and she is lover to everyone";

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

    public int getSum(String a,String b){
        return Integer.parseInt(a) + Integer.parseInt(b);
    }

    @Override
    public String toString() {
        return "address:" + address +"  name:" + this.name + "  age:" + this.age;
    }
}
