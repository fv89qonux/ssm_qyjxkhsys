/**
 * Project Name:ibetter-spring
 * File Name:TokenParamterConverter.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.spring.web.param.resolver;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>Title:TODO</p>
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年5月13日下午1:36:48</p>
 * <p>Description:TODO</p>
 */
public abstract class RetriveMethodParamterConverter implements ParameterConverter {
	
	protected Logger logger=LoggerFactory.getLogger(RetriveMethodParamterConverter.class);
	//从HTTP头中获取转换值
	private String headerName;
	
	@Override
	public Object convert(String name/*拦截参数名*/, NativeWebRequest webRequest) {
		String headerVal = getTargetHeaderVal(webRequest);
		return retrive(name,headerVal);
	}

	@Override
	public void convert(String name, NativeWebRequest webRequest, Object result) {
		String  headerVal=this.getTargetHeaderVal(webRequest);
		Object propertyVal=retrive(name, headerVal);
		String[] deepProperties = StringUtils.split(name, ".");
		if (deepProperties.length>0) {
		    Object object =result;
		    Field field =null;
		    for (int i = 0; i < deepProperties.length; i++) {
		    	String property=deepProperties[i];
				if(i==deepProperties.length-1){
				   try {
					String methodName=StringUtils.join("set",property.toUpperCase().substring(0, 1),property.substring(1));
					   MethodUtils.invokeMethod(object, methodName, propertyVal);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				}else{
					 field = ReflectionUtils.findField(object.getClass(), property);
					 ReflectionUtils.makeAccessible(field);
					 object = ReflectionUtils.getField(field, object);
				}
			}
		}
	}
	
	/**
	 *  <p>Author:zhaojun;</p>
	 *  <p>Date:2016年5月13日下午2:46:21;</p>
	 *	<p>Description: TODO;</p>
	 *  @param TODO
	 *  @return String    
	 *  @throws	TODO
	 */
	private String getTargetHeaderVal(NativeWebRequest webRequest) {
		String headerVal=webRequest.getHeader(headerName);
		if(StringUtils.isBlank(headerVal)){
			throw new RuntimeException(String.format("header[%s] does not exist!", headerName));
		}
		return headerVal;
	}

	public abstract Object retrive(String reqParam,String headVal);
	
	
	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	

 
}
