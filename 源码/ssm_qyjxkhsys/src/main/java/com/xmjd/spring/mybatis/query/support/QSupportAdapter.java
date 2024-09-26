package com.xmjd.spring.mybatis.query.support;


import com.xmjd.spring.data.query.param.QParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class QSupportAdapter extends SimpleQSupport{
	//-------------------------------------------------------------------------
	
	
	protected String prepareStmt(String stmtId) {
		return null;
	}
	
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public <T> T selectOne(String stmtId, QParam parameter) {
		return super.selectOne(prepareStmt(stmtId), parameter);
	}
	
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public <T> T selectOne(String stmtId, Serializable id) {
		return super.selectOne(prepareStmt(stmtId), id);
	}
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public <E> List<E> selectList(String stmtId, QParam parameter) {
		return super.selectList(prepareStmt(stmtId), parameter);
	}

	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public <K, V> Map<K, V> selectMap(String stmtId, String mapKey) {
		return super.selectMap(prepareStmt(stmtId), mapKey);
	}

	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public <K, V> Map<K, V> selectMap(String stmtId, QParam parameter, String mapKey) {
		return super.selectMap(prepareStmt(stmtId), parameter, mapKey);
	}
	
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public int insert(String stmtId, QParam parameter) {
		return super.insert(prepareStmt(stmtId), parameter);
	}

	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public int update(String stmtId, QParam parameter) {
		return super.update(prepareStmt(stmtId), parameter);
	}
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public int delete(String stmtId, QParam parameter) {
		return super.delete(prepareStmt(stmtId), parameter);
	}
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public <T> T selectOne(String stmtId) {
		return super.selectOne(prepareStmt(stmtId));
	}
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public <E> List<E> selectList(String stmtId) {
		return super.selectList(prepareStmt(stmtId));
	}
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public int insert(String stmtId) {
		return super.insert(prepareStmt(stmtId));
	}
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public int insert(String stmtId, Object parameter) {
		return super.insert(prepareStmt(stmtId), parameter);
	}
	/** 兼容非接口查询
	 * @param stmtId sql的ID不用添加 namespace前缀为当前DAO实现接口限定名
	 * @param parameter 查询参数
	 * @return t
	 */
	@Override
	public <T> T insertOne(String stmtId, Object parameter) {
		return super.insertOne(prepareStmt(stmtId), parameter);
	}

}
