package com.xmjd.info.commons.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title:TODO</p>
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年3月23日上午10:46:37</p>
 * <p>Description:TODO</p>
 */
public class ProcessContextManagerUtil {
	
	 public static final ThreadLocal<Map<String,Object>> requestThreadLocal = new ThreadLocal<Map<String,Object>>();
	 
	 public static void init(){
		 Map<String,Object> requestMap= requestThreadLocal.get();
		 if(requestMap==null){
			 requestMap=new ConcurrentHashMap<String, Object>();
			 requestThreadLocal.set(requestMap);
		 }
	 }
	 
	 public static void putAttribute(String name,Object value){
		 Map<String,Object> requestMap= requestThreadLocal.get();
		 if(requestMap==null){
			 throw new RuntimeException("RequestManagerUtil is not initialized");
		 }
		 requestMap.put(name, value);
	 }
	 public static Object getAttribute(String name){
		 Map<String,Object> requestMap= requestThreadLocal.get();
		 if(requestMap==null){
			return null;
		 }
		 return requestMap.get(name);
	 }
	 
	 public static Object getContext(){
		 Map<String,Object> requestMap= requestThreadLocal.get();
		 return requestMap;
	 }
}
