/**
 * Project Name:zfono-spring
 * File Name:TokenRetriveParamMethodRrocessor.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.spring.web.param.resolver;

import com.xmjd.spring.web.annotation.RetrivedModelParam;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletRequest;
import java.util.Collections;
import java.util.Map;


/**
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年5月13日上午11:32:03</p>
 * <p>Description:TODO</p>
 */
public class RetriveMethodModelParamRrocessor extends RetriveMethodParamConvertRrocessor {
	
	private ParameterConverter parameterConverter;
	
	public RetriveMethodModelParamRrocessor() {
		super(false);
	}
 
	@Override
	protected final Object createAttribute(String attributeName, MethodParameter parameter,
			WebDataBinderFactory binderFactory, NativeWebRequest request) throws Exception {

		String value = getRequestValueForAttribute(attributeName, request);
		if (value != null) {
			Object attribute = createAttributeFromRequestValue(value, attributeName, parameter, binderFactory, request);
			if (attribute != null) {
				return attribute;
			}
		}

		return super.createAttribute(attributeName, parameter, binderFactory, request);
	}

 
	protected String getRequestValueForAttribute(String attributeName, NativeWebRequest request) {
		Map<String, String> variables = getUriTemplateVariables(request);
		if (StringUtils.hasText(variables.get(attributeName))) {
			return variables.get(attributeName);
		}
		else if (StringUtils.hasText(request.getParameter(attributeName))) {
			return request.getParameter(attributeName);
		}
		else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected final Map<String, String> getUriTemplateVariables(NativeWebRequest request) {
		Map<String, String> variables =
				(Map<String, String>) request.getAttribute(
						HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
		return (variables != null ? variables : Collections.<String, String>emptyMap());
	}
 
	protected Object createAttributeFromRequestValue(String sourceValue, String attributeName,
			MethodParameter parameter, WebDataBinderFactory binderFactory, NativeWebRequest request)
			throws Exception {

		DataBinder binder = binderFactory.createBinder(request, null, attributeName);
		ConversionService conversionService = binder.getConversionService();
		if (conversionService != null) {
			TypeDescriptor source = TypeDescriptor.valueOf(String.class);
			TypeDescriptor target = new TypeDescriptor(parameter);
			if (conversionService.canConvert(source, target)) {
				return binder.convertIfNecessary(sourceValue, parameter.getParameterType(), parameter);
			}
		}
		return null;
	}

	 
	@Override
	protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
		ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
		ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
		servletBinder.bind(servletRequest);
	}
	
	public void setParameterConverter(ParameterConverter parameterConverter) {
		this.parameterConverter = parameterConverter;
	}

	@Override
	public void resultHook(MethodParameter parameter, NativeWebRequest webRequest, Object result) {
		RetrivedModelParam modelParam = parameter.getParameterAnnotation(RetrivedModelParam.class);
		String modelProperty = modelParam.property();
		if(org.apache.commons.lang3.StringUtils.isNoneBlank(modelProperty)){
			this.parameterConverter.convert(modelProperty,webRequest,result);
		}
		super.resultHook(parameter, webRequest, result);
		
	}
 
}
