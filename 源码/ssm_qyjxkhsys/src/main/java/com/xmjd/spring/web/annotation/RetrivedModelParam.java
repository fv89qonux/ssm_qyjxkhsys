/**
 * Project Name:ibetter-spring
 * File Name:MatrixParam.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.spring.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title:TODO</p>
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年5月11日上午1:08:39</p>
 * <p>Description:TODO</p>
 */
@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)

public @interface RetrivedModelParam {
	public String value() default "access";
	public String property();
}
