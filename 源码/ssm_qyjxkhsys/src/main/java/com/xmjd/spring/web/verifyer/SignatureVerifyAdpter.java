package com.xmjd.spring.web.verifyer;

import java.util.Comparator;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.xmjd.spring.web.exception.GrantException;
import com.xmjd.spring.web.servlet.RequestDelegate;
import com.xmjd.spring.web.token.TokenRepository;

public abstract class SignatureVerifyAdpter implements SignatureVerifyDelegate {
	
	protected static final Logger logger = LoggerFactory.getLogger(SignatureVerifyAdpter.class);

	protected final static String REQUEST_SIGN = "sign";
	protected final static String REQUEST_TIMESTAMP = "timestamp";
	protected final static String REQUEST_NONCE = "nonce";
	protected final static String REQUEST_TOKEN = "token";
	protected final static String APPID = "appId";
	
	/**
	 *  <p>Author:zhaojun;</p>
	 *  <p>Date:2016年4月1日下午2:17:39;</p>
	 *	<p>Description: 可复写可跳过验证，用于项目定制;</p>
	 *  @param TODO
	 *  @return boolean    
	 */
	public boolean skipVerify(RequestDelegate reqDelegate, String[] properties, long time,TokenRepository tokenRepository){
		return false;
	}
	
	/**
	 *  <p>Author:zhaojun;</p>
	 *  <p>Date:2016年4月1日下午2:18:43;</p>
	 *	<p>Description: 验证参数过滤，可复写，用于项目定制;</p>
	 *  @param verifyRequestMap
	 *  @return TreeMap<String,Object>    
	 */
	public TreeMap<String,Object> filteVerifyInput(TreeMap<String,Object> verifyRequestMap){
		return verifyRequestMap;
	}
	
	
	@Override
	public abstract boolean doVerify(TreeMap<String, Object> verifyDataMap, String token, String signature)throws GrantException ;

	@Override
	public boolean doVerify(RequestDelegate reqDelegate, String[] properties, long time,TokenRepository tokenRepository) throws GrantException{
	 
		//跳过验证
		if(this.skipVerify(reqDelegate, properties, time, tokenRepository)){
			return true;
		}
		
		//开始签名
		String signature = resolveSign(reqDelegate);//签名

		if (!StringUtils.isNotBlank(signature)) {
			throw new GrantException("Signature must be required!");
		}
		
		long timestamp = checkTimestamp(reqDelegate, time);
	
		long nonce = checkNonce(reqDelegate);// 请求随机数
		 
		String token = tokenRepository.retrieve(reqDelegate);//获取token
		
		TreeMap<String,Object> verifyRequestMap=resolveProperties(properties, reqDelegate);
		verifyRequestMap.put(REQUEST_NONCE, nonce);
		verifyRequestMap.put(REQUEST_TIMESTAMP, timestamp);
		verifyRequestMap.put(REQUEST_TOKEN, token);
		filteVerifyInput(verifyRequestMap);
		
		return this.doVerify(verifyRequestMap,token,signature);
	}

	/**
	 *  <p>Author:zhaojun;</p>
	 *  <p>Date:2016年4月1日下午1:35:08;</p>
	 *	<p>Description: TODO;</p>
	 *  @param TODO
	 *  @return long    
	 *  @throws	TODO
	 */
	public long checkNonce(RequestDelegate reqDelegate) {
		long nonce = resolveNonce(reqDelegate);
		
		if (nonce <= 0) {
			throw new GrantException("Nonce is error!");
		}
		return nonce;
	}

	/**
	 *  <p>Author:zhaojun;</p>
	 *  <p>Date:2016年4月1日下午1:34:26;</p>
	 *	<p>Description: TODO;</p>
	 *  @param TODO
	 *  @return long    
	 *  @throws	TODO
	 */
	public long checkTimestamp(RequestDelegate reqDelegate, long time) {
		
		long timestamp = resolveTimeStamp(reqDelegate);//时间戳

		if (timestamp <= 0) {
			throw new GrantException("Timestamp must be required!");
		}

		long timelimit = time;//接口过期时间配置

		long currentTime = System.currentTimeMillis();

		// 请求控制在 当前时间的前后2分钟以内	一定时间内重放攻击
		if (Math.abs(currentTime - timestamp) > timelimit) {
			String uri=reqDelegate.getURI();
			logger.error("requestURI->"+uri+",crrentTime->" + currentTime + ",timestamp->" + timestamp);
			throw new GrantException("Request is expired!");
		}
		return timestamp;
	}
	
	
	public TreeMap<String, Object> resolveProperties(String[] properties,RequestDelegate reqDelegate) {
		
		TreeMap<String,Object> verifyMap=Maps.newTreeMap(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		if(ArrayUtils.isNotEmpty(properties)){
			for (int i = 0; i < properties.length; i++) {
				verifyMap.put(properties[i], reqDelegate.getParamValue(properties[i]));
			}
		}
		return verifyMap;
	}
	 
	private long resolveNonce(RequestDelegate reqDelegate) {
		String nonce = reqDelegate.getParamValue(REQUEST_NONCE);
		if (StringUtils.isNotBlank(nonce)) {
			return Long.parseLong(StringUtils.trim(nonce));
		}
		return -1;
	}

	private long resolveTimeStamp(RequestDelegate reqDelegate) {
		String strTimeStamp = reqDelegate.getParamValue(REQUEST_TIMESTAMP);
		if (StringUtils.isNotBlank(strTimeStamp)) {
			return Long.parseLong(StringUtils.trim(strTimeStamp));
		}
		return -1;
	}

	private String resolveSign(RequestDelegate reqDelegate) {
		String sign =reqDelegate.getParamValue(REQUEST_SIGN);
		if (StringUtils.isNotBlank(sign)) {
			return sign;
		}
		sign = reqDelegate.getHeader(REQUEST_SIGN);
		return sign;
	}


}
