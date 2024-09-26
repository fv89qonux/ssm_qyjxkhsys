/**
 * Project Name:ibetter-spring
 * File Name:SignatureParameterUtil.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.spring.web.verifyer;

import java.util.Comparator;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.xmjd.spring.web.servlet.RequestDelegate;

/**
 * <p>Title:TODO</p>
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年5月15日下午3:19:53</p>
 * <p>Description:TODO</p>
 */
public class SignParamUtil {

	public static TreeMap<String, Object>  getSignParam(RequestDelegate requestDelegate) {
		TreeMap<String,Object> vRequestMap=Maps.newTreeMap(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		Enumeration<String> parameters = requestDelegate.getParameters();
		while (parameters.hasMoreElements()) {
			String parameter=parameters.nextElement();
			String parameterVal=requestDelegate.getParamValue(parameter);
			vRequestMap.put(parameter, parameterVal);
		}
		return vRequestMap;
	}

	public static TreeMap<String, Object> getSignParam(RequestDelegate requestDelegate, String...properties) {
		if (ArrayUtils.isNotEmpty(properties)) {
			TreeMap<String,Object> vRequestMap=Maps.newTreeMap(new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			for (int i = 0; i < properties.length; i++) {
				String paramVal=requestDelegate.getParamValue(properties[i]);
				vRequestMap.put(properties[i], paramVal);
			}
			return vRequestMap;
		}
		return getSignParam(requestDelegate);
	}

	public static String flat(TreeMap<String, Object> paramMap) {
		Set<Entry<String, Object>> entrySet = paramMap.entrySet();
		int size=CollectionUtils.size(entrySet);
		int i=0;
		StringBuilder queryStr=new StringBuilder();
		for (Entry<String, Object> entry : entrySet) {
			String key=entry.getKey();
			Object val=entry.getValue();
			queryStr.append(key).append("=").append(val);
			if (i<size-1) {
				queryStr.append("&");
			}
			i++;
		}
		return queryStr.toString();
	}

}
