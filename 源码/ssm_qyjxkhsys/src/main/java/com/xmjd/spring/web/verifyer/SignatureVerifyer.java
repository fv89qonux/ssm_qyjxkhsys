package com.xmjd.spring.web.verifyer;

import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.xmjd.info.commons.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SignatureVerifyer {
	protected static final Logger logger=LoggerFactory.getLogger(SignatureVerifyer.class);
	
	private TreeMap<String, Object> verifyDataMap;
	private String token;
	private String signature;
	
	
	public SignatureVerifyer(TreeMap<String, Object> verifyDataMap, String token, String signature) {
	  this.verifyDataMap=verifyDataMap;
	  this.token=token;
	  this.signature=signature;
	}

	public static SignatureVerifyer create(TreeMap<String, Object> verifyDataMap, String token, String signature) {
		return new SignatureVerifyer(verifyDataMap,token,signature);
	}

	public boolean execute() {
		Set<Entry<String, Object>> entrySet = verifyDataMap.entrySet();
		StringBuilder signatureCrypt=new StringBuilder();
		for (Entry<String, Object> entry : entrySet) {
			String key=entry.getKey();
			Object val=entry.getValue();
			signatureCrypt.append(key).append("=").append(val).append("&");
		}
		String cryptStr=StringUtils.removeEnd(signatureCrypt.toString(), "&");
		String oSign= MD5Util.MD5(cryptStr);
		return StringUtils.equals(signature, oSign);
	}
	
	
	
}
