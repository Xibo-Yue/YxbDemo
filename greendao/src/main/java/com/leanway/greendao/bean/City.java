package com.leanway.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Xibo_Yue
 * @time 2017/5/24 10:31
 */
@Entity
public class City {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private int num;

    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1310450639)
    public City(Long id, String name, int num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }
    @Generated(hash = 750791287)
    public City() {
    }

    public City(String name, int num) {
        this.name = name;
        this.num = num;
    }
}
