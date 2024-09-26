package com.xmjd.spring.web.servlet;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 待扩展处理request.... 	
 * @author zhaojun
 * 2016年3月23日 上午11:38:33
 */
public class RequestDelegate {
	private HttpServletRequest request;
	
	public RequestDelegate(HttpServletRequest request) {
		this.request=request;
	}
	public static RequestDelegate create(HttpServletRequest request){
		RequestDelegate requestDelegate=new RequestDelegate(request);
		return requestDelegate;
	}
	public String getParamValue(String param) {
		String value =this.request.getParameter(param);
		return value;
	}
	
	public String getHeader(String header) {
		String head=this.request.getHeader(header);
		return head;
	}
	public Enumeration<String> getHeaders(){
		return this.request.getHeaderNames();
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public String getURI(){
		return request.getRequestURI();
	}
	
	public String getRemoteAddr(){
		return request.getRemoteAddr();
	}
	
	public StringBuffer getRequestURL(){
		return request.getRequestURL();
	}
	
	public String getMethod(){
		return request.getMethod();
	}
	public String getQueryString(){
		return request.getQueryString();
	}	
	public  Map<String, String[]> getParameterMap(){
		  Map<String, String[]> parameterMap = request.getParameterMap();
		  return parameterMap;
	}
	
	public Enumeration<String> getParameters(){
		Enumeration<String> paramterNames = request.getParameterNames();
		return paramterNames;
	}
}
