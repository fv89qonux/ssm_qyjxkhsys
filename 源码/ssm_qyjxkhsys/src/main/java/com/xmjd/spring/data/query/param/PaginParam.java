package com.xmjd.spring.data.query.param;


/**
 * 分页查询参数
 *
 * @author shenyuting
 *         2015年10月29日 下午2:01:01
 */
public class PaginParam {

    private long offset;

    private int limit;

    private Integer pageSize;

    private Long pageNo;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public PaginParam() {
    }

    public PaginParam(long offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
