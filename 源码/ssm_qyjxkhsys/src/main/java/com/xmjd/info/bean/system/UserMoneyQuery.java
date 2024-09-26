package com.xmjd.info.bean.system;

import com.xmjd.spring.data.query.param.PaginParam;

import java.io.Serializable;

/**
 * 系统平台用户的查询的对象
 * Created by Administrator on 2016/5/25.
 */
public class UserMoneyQuery extends PaginParam implements Serializable {

    private String userId;

    private String month;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
