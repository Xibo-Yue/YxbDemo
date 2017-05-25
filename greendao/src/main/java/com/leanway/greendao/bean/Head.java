package com.leanway.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Xibo_Yue
 * @time 2017/5/25 9:42
 */
@Entity
public class Head {
    @Id(autoincrement = true)
    private Long hid;
    private String headUrl;
    public String getHeadUrl() {
        return this.headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public Long getHid() {
        return this.hid;
    }
    public void setHid(Long hid) {
        this.hid = hid;
    }
    @Generated(hash = 1651461413)
    public Head(Long hid, String headUrl) {
        this.hid = hid;
        this.headUrl = headUrl;
    }
    @Generated(hash = 1745729831)
    public Head() {
    }
}
