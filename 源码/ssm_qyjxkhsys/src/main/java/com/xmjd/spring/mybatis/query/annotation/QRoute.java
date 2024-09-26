package com.xmjd.spring.mybatis.query.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * DAO方法查询数据源路由
 * @author zhaojun
 * 2016年1月23日 上午11:27:50
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface QRoute {
	/**
	 * 数据源
	 * @return
	 */
	String value() default "rw";	
}
