package com.xmjd.info.bean.information;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 成品
 *
 * @author admin
 */
public class Product {

    private Long id;

    /**
     * 编号
     */
    private String number;

    /**
     * 名称
     */
    private String name;

    /**
     * 批次
     */
    private String batch;

    /**
     * 总数
     */
    private BigDecimal count;

    /**
     * 最低
     */
    private BigDecimal low;

    /**
     * 最高
     */
    private BigDecimal high;

    /**
     * 发运数量
     */
    private Double fysl;

    /**
     * 发运日期
     */
    private Date fydate;

    /**
     * 已发运数量
     */
    private Double yfysl;

    /**
     * 零件编号
     */
    private String model;


    /**
     * 预计到达时间
     */
    private Date arriveTime;

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getYfysl() {
        return yfysl;
    }

    public void setYfysl(Double yfysl) {
        this.yfysl = yfysl;
    }

    public Double getFysl() {
        return fysl;
    }

    public void setFysl(Double fysl) {
        this.fysl = fysl;
    }

    public Date getFydate() {
        return fydate;
    }

    public void setFydate(Date fydate) {
        this.fydate = fydate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }
}
