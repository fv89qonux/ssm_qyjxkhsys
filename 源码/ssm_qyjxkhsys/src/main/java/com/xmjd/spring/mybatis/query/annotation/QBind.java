package com.xmjd.spring.mybatis.query.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 方法别名操作
 * @author zhaojun
 * 2016年1月26日 下午12:15:02
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface QBind {
	public String value();
}
