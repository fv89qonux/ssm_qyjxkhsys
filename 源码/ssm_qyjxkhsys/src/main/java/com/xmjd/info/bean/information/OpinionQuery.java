package com.xmjd.info.bean.information;

import com.xmjd.info.bean.common.BaseQuery;

/**
 * Created by admin on 2021/7/26.
 */
public class OpinionQuery extends BaseQuery {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
