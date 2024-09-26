package com.xmjd.spring.mybatis.query.cache;

public interface QueryCache {


	public Object getFromCache(CacheKey cacheKey, Class<?> rtvClazz);

	public void putCache(CacheKey cacheKey, Object rtv);
}
