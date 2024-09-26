package com.xmjd.spring.web.servlet;

import org.apache.commons.lang3.StringUtils;

/**
 * 接口返回创建
 * @author zhaojun
 * 2015年10月26日 下午6:26:45
 */
public class ResultUtils {

	/**
	 * 默认结构为成功消息
	 * @param data 需要返回的数据
	 * @return RemoteResult  返回数据结构 
	 */
	public static RemoteResult createDefResult(Object data) {
		RemoteResult remoteResult=new RemoteResult();
		remoteResult.setData(data);
		remoteResult.setMessage(ResultStatus.SUCCESS_MSG);
		return remoteResult;
	}
	
	public static RemoteResult createNullResult(){
		return createDefResult(null);
	}
	
	public static RemoteResult createResult(int status,Object data,String message,String error) {
		RemoteResult remoteResult=new RemoteResult();
		remoteResult.setStatus(status);
		remoteResult.setData(data);
		remoteResult.setMessage(message);
		remoteResult.setError(error);
		return remoteResult;
	}
	
	public static RemoteResult createResult(int status,Object data,String message) {
		return createResult(  status,  data,  message,  null);
	}
	public static RemoteResult createErrorResult(Object data) {
		return createResult(ResultStatus.ERROR, data, ResultStatus.ERROR_MSG, "");
	}
	public static RemoteResult createParamErrorResult(String message){
		return createResult(ResultStatus.PARAM_ERROR, null, message, "");
	}
	public static RemoteResult createSysErrorResult(String error){
		if(StringUtils.isNotBlank(error)){
			return createResult(ResultStatus.INNER_ERROR, null,error, null);
		}
		return createResult(ResultStatus.INNER_ERROR, null,"系统繁忙中，请稍后再试", ResultStatus.SYSTEM_ERROR_MSG);
	}
	public static RemoteResult createExceptionResult(String message){
		return createResult(ResultStatus.EXCEPTION, null, message, "");
	}
	public static RemoteResult createVersionResult(Integer status,String version,String url,String content){
		RemoteResult remoteResult=createNullResult();
		remoteResult.setUrl(url);
		remoteResult.setVersion(version);
		remoteResult.setContent(content);
		remoteResult.setStatus(status);
		return remoteResult;
	}
	 
}
