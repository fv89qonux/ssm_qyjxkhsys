package com.xmjd.spring.mybatis.query.support;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xmjd.spring.data.query.param.QParam;
import static com.xmjd.spring.mybatis.holder.StmtContextHolder.*;
/** 兼容非接口查询
 * @author zhaojun
 * 2015年12月4日 上午11:57:53
 */
public class SessionQSupport extends QSupportAdapter {
	
	protected final Logger logger=LoggerFactory.getLogger(SessionQSupport.class);
	
	protected volatile String baseNameSpace=null;
	{
		Class<?>[] interfaces = this.getClass().getInterfaces();
		int len=ArrayUtils.getLength(interfaces);
		if(len==0){
			logger.error("无实现DAO接口");
		}
		if(len>1){
			logger.error("只能实现一个DAO接口>>>>>>");
			for (Class<?> class1 : interfaces) {
				logger.error(class1.getName());
			}
		}
		baseNameSpace=interfaces[0].getName();
		
		if(logger.isDebugEnabled()){
			logger.debug("namespace-->"+baseNameSpace);
		}
	}
	public String getCurrentStatement(){
		
		String statement =  getCurrentStmt();
		if(logger.isDebugEnabled()){
			logger.debug("statement -->"+statement);
		}
		return statement;
	}
	
	public void reset(String stmtId){
		resetStmt(stmtId);
	}
	public <T> T selectOne(QParam parameter) {
		return this.getSqlSession().selectOne(getCurrentStatement(), parameter);
	}
	public <T> T selectOne(Serializable id) {
		return this.getSqlSession().selectOne(getCurrentStatement(), id);
	}

	public <E> List<E> selectList(QParam parameter) {
		return this.getSqlSession().selectList(getCurrentStatement(), parameter);
	}

	public <K, V> Map<K, V> selectMap(String mapKey) {
		return this.getSqlSession().selectMap(getCurrentStatement(), mapKey);
	}

	public <K, V> Map<K, V> selectMap(QParam parameter, String mapKey) {
		return this.getSqlSession().selectMap(getCurrentStatement(), parameter, mapKey);
	}

	public int insert( QParam parameter) {
		return this.getSqlSession().insert(getCurrentStatement(), parameter);
	}

	public int update(QParam parameter) {
		return this.getSqlSession().update(getCurrentStatement(), parameter);
	}
	public int update(Serializable serializble) {
		return this.getSqlSession().update(getCurrentStatement(), serializble);
	}

	public int delete( QParam parameter) {
		return this.getSqlSession().delete(getCurrentStatement(), parameter);
	}

	public <T> T selectOne() {
		return this.getSqlSession().selectOne(getCurrentStatement());
	}

	public <E> List<E> selectList() {
		return this.getSqlSession().selectList(getCurrentStatement());
	}

	public int insert(Object parameter){
		 return this.getSqlSession().insert(getCurrentStatement(), parameter);
	}


	@Override
	protected String prepareStmt(String stmtId) {
		String stmtFullPath=createStmt(stmtId);
		if(stmtFullPath==null){
			stmtFullPath=StringUtils.join(this.baseNameSpace,".",stmtId);
		}
		return stmtFullPath;  
	}

}
