/**
 * Project Name:ibetter-spring
 * File Name:TokenBinding.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.spring.web.token;

import java.io.Serializable;

/**
 * <p>Title:TODO</p>
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年5月15日下午3:51:19</p>
 * <p>Description:TODO</p>
 */
public class TokenBind implements Serializable {
	
	private String appKey;
	private String token;
	private long timestamp;
	private Object uid;
	
	public String getAppKey() {
		return appKey;
	}
	public String getToken() {
		return token;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public Object getUid() {
		return uid;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public void setUid(Object uid) {
		this.uid = uid;
	}
	
	
	
}
