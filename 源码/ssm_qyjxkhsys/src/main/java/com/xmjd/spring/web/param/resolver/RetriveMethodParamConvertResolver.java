/**
 * Project Name:ibetter-spring
 * File Name:RetriveMethodParamConvertResolver.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.spring.web.param.resolver;

import com.xmjd.spring.web.annotation.RetrivedParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <p>Title:TODO</p>
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年5月13日下午1:25:04</p>
 * <p>Description:TODO</p>
 */
public class RetriveMethodParamConvertResolver implements HandlerMethodArgumentResolver {

	private ParameterConverter parameterConverter;
	
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(RetrivedParam.class);
	}
	 

 
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		RetrivedParam retrivedParam=parameter.getParameterAnnotation(RetrivedParam.class);
		String name=retrivedParam.value();
		name=StringUtils.isNotBlank(name)?name:parameter.getParameterName();
		return this.parameterConverter.convert(name,webRequest);
		 
	}



	public void setParameterConverter(ParameterConverter parameterConverter) {
		this.parameterConverter = parameterConverter;
	}

}
