package com.xmjd.info.commons.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class ParamExceptionUtils {
	
	 public static String buildParamMsg(String[] params,String message){
		 
		 StringBuilder msgBuilder=new StringBuilder();
		 msgBuilder.append("Parameters{");
		 if(ArrayUtils.isNotEmpty(params)){
			 msgBuilder.append(StringUtils.join(params, ",")); 
		 }
		 msgBuilder.append("}:[");
		 msgBuilder.append(message);
		 msgBuilder.append("]!");
		 return msgBuilder.toString();
	 }
	 
	 public static String buildParamMsg(String param,String message){
		 return buildParamMsg(new String[]{param},message);
	 }
	 public static String buildParamErrorMsg(String param){
		 return  buildParamMsg(new String[]{param},"parameter is error");
	 }
	 
	 public static String buildParamMsg(String[] params){
		 return buildParamMsg(params,"parameter is error");
	 }
	 
}
