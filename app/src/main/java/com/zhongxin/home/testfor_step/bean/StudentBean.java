package com.zhongxin.home.testfor_step.bean;

/**
 * Created by Walter on 2015/10/24.
 */
public class StudentBean {
    private String[] nums;
    private String name;
    private int age;
    private AddressBean addressBean;
    private boolean married;

    public String[] getNums() {
        return nums;
    }

    public void setNums(String[] nums) {
        this.nums = nums;
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

    public AddressBean getAddressBean() {
        return addressBean;
    }

    public void setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "[name:"+name+",age:"+age+",married:"+married+",nums:"+nums.toString()+",addressBean:"+addressBean+"]";
    }
}
