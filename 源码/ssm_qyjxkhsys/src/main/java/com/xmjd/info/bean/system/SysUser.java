package com.xmjd.info.bean.system;

import com.xmjd.info.bean.common.User;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统平台用户对象
 * Created by Administrator on 2016/5/25.
 */
public class SysUser extends User implements Serializable {

    /**
     * 系统平台普通用户
     */
    public static final int NORMAL = 0;

    /**
     * 系统平台超级管理员.
     */
    public static final int ADMINISTRATOR = 1;

    private int admin = NORMAL;

    //角色ID
    private String roleId;

    //角色名称
    private String roleName;

    private Integer sex;

    private Date joinTime;

    private String phone;

    private String address;

    private Date gmtTime;
    private String job;

    private String dept;

    private String deptName;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public Date getGmtTime() {
        return gmtTime;
    }

    @Override
    public void setGmtTime(Date gmtTime) {
        this.gmtTime = gmtTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

}
