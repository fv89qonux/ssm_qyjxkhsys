package com.xmjd.info.dao.information;

import java.util.Date;

/**
 * 利润统计
 *
 * @author admin
 * @create 2016/12/13
 */
public class ProfitStatistics {

    /**
     * 统计时间
     */
    private Date date;

    /**
     * 利润
     */
    private Double profit;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit == null ? 0d : profit;
    }
}
