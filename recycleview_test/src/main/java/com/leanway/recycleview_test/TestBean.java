package com.leanway.recycleview_test;

/**
 * @author Xibo_Yue
 * @time 2017/4/18 14:55
 */

public class TestBean {
    private String name;
    private String age;
    private String city;
    private String idCode;
    private boolean isCheck;


    public TestBean(String name, String age, String city, String idCode,boolean isCheck) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.idCode = idCode;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
}
