package com.xmjd.spring.mybatis.holder;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

public class StmtContextHolder {
	
	protected static final Logger logger=LoggerFactory.getLogger(StmtContextHolder.class);
	
	public static final ThreadLocal<String> holdThreadLocal=new ThreadLocal<String>();
	
	public static final ThreadLocal<String>  stmtContextLocal=new ThreadLocal<String>();
	
	public static final Map<String,Map<String,StmtUnit>> stmtCacheMap=Maps.newHashMap();
	
	public static void initStmtContext(String basectx){
		stmtContextLocal.set(basectx);
	}
	public static void initStmt(String statement){
		holdThreadLocal.set(statement);
	}
	
	public static String getStmtContext(){
		String ctx = stmtContextLocal.get();
		return ctx;
	}
	
	public static void resetStmt(String stmtId){
		String statement = createStmt(stmtId);
		initStmt(statement);
	}
	public static String createStmt(String stmtId) {
		String ctx=getStmtContext();
		
		if(ctx==null){
			throw new RuntimeException("There is no statement context in current Thread!");
		}
	 
		String statement=getStmtFromCache(stmtId, ctx);
		
		return statement;
	}
	
	/**
	 * @param stmtId
	 * @param ctx
	 * @return
	 */
	private static String getStmtFromCache(String stmtId, String ctx) {
		
		String fullStmtPath=null;
		Map<String, StmtUnit> stmtUnitCacheMap = stmtCacheMap.get(ctx);
		
		if(stmtUnitCacheMap==null){//
			fullStmtPath=StringUtils.join(ctx,".",stmtId);
			synchronized (stmtCacheMap) {//
				if(stmtCacheMap.get(ctx)==null){
					stmtUnitCacheMap=Maps.newHashMap();
					stmtUnitCacheMap.put(stmtId, new StmtUnit(stmtId, fullStmtPath));
					stmtCacheMap.put(ctx, stmtUnitCacheMap );
				}
			}
		}else{
			StmtUnit stmtUnit=stmtUnitCacheMap.get(stmtId);
			if(stmtUnit==null){
				synchronized (stmtUnitCacheMap) {
					stmtUnit=stmtUnitCacheMap.get(stmtId);
					if(stmtUnit==null){
						fullStmtPath=StringUtils.join(ctx,".",stmtId);
						stmtUnitCacheMap.put(stmtId, new StmtUnit(stmtId, fullStmtPath));
					}else{
						fullStmtPath=stmtUnit.getFullStmtPath();
					}
				}
			}else{
				fullStmtPath=stmtUnit.getFullStmtPath();
			}
		}
		return fullStmtPath;
	}
	
	public static String getCurrentStmt(){
		String statement = holdThreadLocal.get();
		if(StringUtils.isNotBlank(statement)){
			return statement;
		}
		throw new RuntimeException("There is no statement in current Thread!");
	}
	
 	static class StmtUnit{
		
		private String stmtId;
		private String fullStmtPath;
		
		
		public StmtUnit() {
			super();
		}
		
		
		public StmtUnit(String stmtId, String fullStmtPath) {
			super();
			this.stmtId = stmtId;
			this.fullStmtPath = fullStmtPath;
		}


		public String getStmtId() {
			return stmtId;
		}
		public void setStmtId(String stmtId) {
			this.stmtId = stmtId;
		}
		public String getFullStmtPath() {
			return fullStmtPath;
		}
		public void setFullStmtPath(String fullStmtPath) {
			this.fullStmtPath = fullStmtPath;
		}
		 
	}
}
