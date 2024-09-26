package com.xmjd.info.bean.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用的登录用户对象
 * Created by admin on 2021/7/8.
 */
public class User implements Serializable {

    /**
     * 已删除
     */
    public static final int DELETED_Y = 1;

    /**
     * 未删除，正常状态
     */
    public static final int DELETED_N = 0;


    /**
     * 已冻结
     */
    public static final int FREEZE_YES = 1;

    /**
     * 未冻结，正常
     */
    public static final int FREEZE_NO = 0;


    //主键ID
    private String id;

    //用户姓名
    private String name;

    //登录账号
    private String account;

    //密码
    private String password;

    //登录次数
    private long loginCount = 0;

    //最后登录时间
    private Date lastLoginTime;

    //用户创建时间
    private Date gmtTime = new Date();

    //是否删除，1-是，0-否
    private int deleted = DELETED_N;

    //是否被冻结
    private int freeze = FREEZE_NO;

    //最后一次登录的IP
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(long loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getGmtTime() {
        return gmtTime;
    }

    public void setGmtTime(Date gmtTime) {
        this.gmtTime = gmtTime;
    }
}
