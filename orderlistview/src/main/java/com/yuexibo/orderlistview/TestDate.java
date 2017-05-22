package com.yuexibo.orderlistview;

/**
 * @author Xibo_Yue
 * @time 2017/5/22 17:04
 */

public class TestDate {
    private String date;
    private String name;
    private String drawNum;
    private String city;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(String drawNum) {
        this.drawNum = drawNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public TestDate(String date, String name) {
        this.date = date;
        this.name = name;
    }

    public TestDate(String date, String name, String drawNum, String city) {
        this.date = date;
        this.name = name;
        this.drawNum = drawNum;
        this.city = city;
    }
}
