/**
 * Project Name:ibetter-spring
 * File Name:BindToken.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.spring.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title:TODO</p>
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年5月10日上午1:06:11</p>
 * <p>Description:TODO</p>
 */
@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RetrivedParam {
	String value();//要转换的参数名称
}
