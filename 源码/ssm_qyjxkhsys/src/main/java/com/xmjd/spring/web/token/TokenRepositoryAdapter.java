package com.xmjd.spring.web.token;


import com.xmjd.spring.web.servlet.RequestDelegate;

/**
 * 提供子类实现token获取仓库实现适配器
 * @author zhaojun
 * 2016年2月2日 下午1:43:01
 */
public abstract class TokenRepositoryAdapter implements TokenRepository {
	@Override
	public String retrieve(RequestDelegate reqDelegate) {
		initRetrieve();
		String token=handle(reqDelegate);
		completeRetrieve(reqDelegate);
		return token;
	}
	public void initRetrieve() {}
	public void completeRetrieve(RequestDelegate reqDelegate) {}
	public abstract String handle(RequestDelegate request) ;
}
