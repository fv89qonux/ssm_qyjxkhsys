package com.xmjd.spring.mybatis.query.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface QIntercept {
	String value() default "default";
}
