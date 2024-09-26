package com.xmjd.spring.data.query.util;


import com.xmjd.spring.data.query.param.PaginParam;
import com.xmjd.spring.data.query.result.Pagination;

/**
 * 分页返回模型 分页参数模型创建工具类
 * @author zhaojun
 * 2015年10月29日 下午2:01:23
 */
public class PaginUtils {

	public static PaginParam createPaginParam(long offset, int limit){
		PaginParam paginParam=new PaginParam(offset,limit);
		return paginParam;
	}
	
	public static <T>Pagination<T> createPagination(long offset, int limit){
		Pagination<T> pagination=new Pagination<T>(offset, limit);
		return pagination;
	}

	/**
	 * 通过paginPara创建分页模型
	 * @param param 参数
	 * @return 模型
     */
	public static  <T>Pagination<T> createPagination(PaginParam param){
		long offset = param.getOffset();
		int limit = param.getLimit();
		Pagination<T> pagination=new Pagination<T>(offset, limit);
		return pagination;
	}
 
}
