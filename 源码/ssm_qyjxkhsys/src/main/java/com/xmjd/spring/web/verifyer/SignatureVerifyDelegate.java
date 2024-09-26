package com.xmjd.spring.web.verifyer;

import java.util.TreeMap;

import com.xmjd.spring.web.exception.GrantException;
import com.xmjd.spring.web.servlet.RequestDelegate;
import com.xmjd.spring.web.token.TokenRepository;

public interface SignatureVerifyDelegate {
	public boolean doVerify(TreeMap<String, Object> verifyDataMap, String token, String signature)throws GrantException;
	public boolean doVerify(RequestDelegate reqDelegate, String[] properties, long time, TokenRepository tokenRepository)throws GrantException;
}
