package com.xmjd.spring.web.servlet;

/**
 * 接口逻辑编码
 * @author zhaojun
 * 2015年10月26日 下午6:13:50
 */
public class ResultStatus {
	
	//------------------消息码-----------------------------
	/**
	 * 返回成功
	 */
	public static final int SUCCESS=100; //返回成功
	
	/**
	 * 返回错误
	 */
	public static final int ERROR=101;//返回失败
	
	/**
	 * 内部逻辑异常
	 */
	public static final int EXCEPTION=102;//内部运行逻辑错误
	
	public static final int PARAM_ERROR=103;//参数错误
			
	public static final int INNER_ERROR=104;//系统错误
	
	public static final int VERSION_UPDATE=1001;//强制更新
	
	public static final int VERSION_FORCE_UPDATE=1002;//强制更新
	
	public static final int ACCESS_ERROR=1021;//token错误
	//-----------------消息---------------------------
	
	/**
	 * 返回成功默认消息
	 */
	public static final String SUCCESS_MSG="OK";
	/**
	 * 返回错误默认消息
	 */
	public static final String ERROR_MSG="ERROR";
	
	/**
	 * 运行异常
	 */
	public static final String EXCEPTION_MSG="EXCEPTION";
	
	/**
	 * 系统内部错误
	 */
	public static final String SYSTEM_ERROR_MSG="系统繁忙中，请稍后再试~";
}
