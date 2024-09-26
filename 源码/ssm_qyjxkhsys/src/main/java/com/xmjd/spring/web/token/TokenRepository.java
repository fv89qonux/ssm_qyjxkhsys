package com.xmjd.spring.web.token;


import com.xmjd.spring.web.servlet.RequestDelegate;
/**
 * token仓库
 * @author zhaojun
 * 2016年2月2日 下午1:43:59
 */
public interface TokenRepository {
	public String retrieve(RequestDelegate reqDelegate);

	/**
	 *  <p>Author:zhaojun;</p>
	 *  <p>Date:2016年5月15日下午3:52:50;</p>
	 *	<p>Description: TODO;</p>
	 *  @param TODO
	 *  @return TokenBind    
	 *  @throws	TODO
	 */
	public TokenBind retriveTokenBind(String token);
}
