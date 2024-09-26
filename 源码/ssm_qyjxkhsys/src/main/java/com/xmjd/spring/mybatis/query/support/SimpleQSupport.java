package com.xmjd.spring.mybatis.query.support;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.xmjd.spring.data.query.param.QParam;
/**
 * @author zhaojun
 * 2015年12月4日 上午11:57:53
 */
public class SimpleQSupport extends SqlSessionDaoSupport {
	
	public <T> T selectOne(String statement, QParam parameter) {
		return this.getSqlSession().selectOne(statement, parameter);
	}
	public <T> T selectOne(String statement, Serializable id) {
		return this.getSqlSession().selectOne(statement, id);
	}

	public <E> List<E> selectList(String statement, QParam parameter) {
		return this.getSqlSession().selectList(statement, parameter);
	}

	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return this.getSqlSession().selectMap(statement, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, QParam parameter, String mapKey) {
		return this.getSqlSession().selectMap(statement, parameter, mapKey);
	}

	public int insert(String statement, QParam parameter) {
		return this.getSqlSession().insert(statement, parameter);
	}

	public int update(String statement, QParam parameter) {
		return this.getSqlSession().update(statement, parameter);
	}
	public int update(String statement,  Serializable serializble) {
		return this.getSqlSession().update(statement, serializble);
	}

	public int delete(String statement, QParam parameter) {
		return this.getSqlSession().delete(statement, parameter);
	}

	public <T> T selectOne(String statement) {
		return this.getSqlSession().selectOne(statement);
	}

	public <E> List<E> selectList(String statement) {
		return this.getSqlSession().selectList(statement);
	}

	
	public int insert(String statement) {
		return this.getSqlSession().insert(statement);
	}
	
	public int insert(String statement, Object parameter){
		 return this.getSqlSession().insert(statement, parameter);
	}
	
	public <T> T insertOne(String statement, Object parameter){
		this.getSqlSession().insert(statement, parameter);
		try {
			return (T) PropertyUtils.getProperty(parameter, "id");
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	public int update(String statement) {
		return this.getSqlSession().update(statement);
	}

	public int delete(String statement) {
		return this.getSqlSession().delete(statement);
	}

}
