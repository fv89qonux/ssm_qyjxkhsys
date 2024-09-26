package com.xmjd.spring.data.query.result;

import java.io.Serializable;

/**
 * 分页返回模型
 * @author zhaojun
 * 2015年10月27日 上午10:18:41
 */
public class Pagination<T> implements Serializable {
	
	
	private long offset; 
	private int limit;  
	private long total;
	private T data;
	
	
	public Pagination() {
		super();
	}
	
	
	public Pagination(long offset, int limit) {
		super();
		this.offset = offset<=0?1:offset;
		this.limit = limit;
	}


	public Pagination(long offset, int limit, long total, T data) {
		this(offset,limit);
		this.total = total;
		this.data = data;
	}


	/**
	 * 起始记录数
	 * @return int
	 */
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	/**
	 * 页面大小
	 * @return int
	 */
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/**
	 * 总条数
	 * @return int
	 */
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}
	
	/**
	 * 分页数据
	 * @return
	 */
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public static final String _offset="offset";
	public static final String _limit="limit";
}
