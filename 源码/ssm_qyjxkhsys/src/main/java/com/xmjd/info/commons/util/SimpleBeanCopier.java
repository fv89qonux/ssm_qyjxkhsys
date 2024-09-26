/**
 * Project Name:ci-common
 * File Name:SimpleBeanCopier.java
 * Copyright (c) 2016, www.zm0618.com All Rights Reserved.
 */
package com.xmjd.info.commons.util;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.collections4.map.LRUMap;
import org.apache.commons.lang3.StringUtils;

import net.sf.cglib.beans.BeanCopier;

/**
 * <p>Title:Bean Copier</p>
 * @author zhaojun
 * @version	v1.0
 * <p>Date:2016年4月6日下午2:31:18</p>
 * <p>Description:使用CGLIB的BeanCopier</p>
 */
public class SimpleBeanCopier {
	
	public static Map<String,BeanCopier> beanCopierMap=new LRUMap<String, BeanCopier>();
	
	public static Lock lock=new ReentrantLock();
	
	public static BeanCopier getCopier(Class<?> fromclzz,Class<?>toclzz){
		
		String copierKey=StringUtils.join(fromclzz.getName(),":",toclzz.getName());
		
		BeanCopier beanCopier = beanCopierMap.get(copierKey);
		
		if(beanCopier==null){
			if (lock.tryLock()) {
				beanCopier = beanCopierMap.get(copierKey);
				if( beanCopier==null){
					beanCopier = BeanCopier.create(fromclzz, toclzz, false);
					beanCopierMap.put(copierKey, beanCopier);
				}
				lock.lock();
			}else{
				beanCopier = BeanCopier.create(fromclzz, toclzz, false);
			}
		}
		
		return beanCopier;
	}
	
	public static void copy(BeanCopier copier, Object from,Object to){
		 copier.copy(from, to, null);
	}
	public static void copy(Object from,Object to){
		BeanCopier copier=getCopier(from.getClass(), to.getClass());
		copy(copier, from, to);
	}

}
