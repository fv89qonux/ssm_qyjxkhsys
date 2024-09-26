/**
 * Project Name:ibetter-spring
 * File Name:ParameterConverter.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.spring.web.param.resolver;

import org.springframework.web.context.request.NativeWebRequest;

 
public interface ParameterConverter {
	public Object convert(String name, NativeWebRequest webRequest);
	public void convert(String name, NativeWebRequest webRequest, Object result);
}
