package com.xmjd.info.bean.system;

import com.xmjd.spring.data.query.param.PaginParam;

import java.io.Serializable;

/**
 * 系统平台用户的查询的对象
 * Created by Administrator on 2016/5/25.
 */
public class SysUserQuery extends PaginParam implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
