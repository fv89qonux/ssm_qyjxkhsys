package com.xmjd.info.bean.system;

/**
 * @author shenyt
 * @time 2017/4/12
 */
public class UserMoney {
    private String id;

    private String userId;

    private String userName;

    private double jbgz;

    private double jj;

    private double yfgz;

    private double ykgz;

    private String month;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getJbgz() {
        return jbgz;
    }

    public void setJbgz(double jbgz) {
        this.jbgz = jbgz;
    }

    public double getJj() {
        return jj;
    }

    public void setJj(double jj) {
        this.jj = jj;
    }

    public double getYfgz() {
        return yfgz;
    }

    public void setYfgz(double yfgz) {
        this.yfgz = yfgz;
    }

    public double getYkgz() {
        return ykgz;
    }

    public void setYkgz(double ykgz) {
        this.ykgz = ykgz;
    }
}
