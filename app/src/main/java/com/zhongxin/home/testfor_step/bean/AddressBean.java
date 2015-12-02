package com.zhongxin.home.testfor_step.bean;

/**
 * Created by Walter on 2015/10/24.
 */
public class AddressBean {
    private String province;
    private String country;
    private String town;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "[province:"+province+",country:"+country+",town:"+town+"]";
    }
}
