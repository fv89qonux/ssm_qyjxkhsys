package com.xmjd.spring.data.query.param;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.xmjd.info.commons.util.MapConvertUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * @author zhaojun
 * 2015年10月31日 下午1:23:55
 * 查询参数封装
 */
public class QParam implements Map<String, Object>{
	
	protected static final Logger logger=LoggerFactory.getLogger(QParam.class);
	protected Map<String,Object> param=new HashMap<String, Object>();
	
	
	public QParam() {
		 
	}
	public QParam(Object...kv) {
		if(kv.length%2==0){
			for (int i = 0; i < kv.length; ) {
				this.addParam((String)kv[i], kv[i+1]);
				i+=2;
			}
		}else{
			logger.error(StringUtils.join(kv, ","));
			throw new RuntimeException("QParam is kv is error");
		}
	}
	/**
	 * 添加查询参数
	 * @param name	参数名称
	 * @param val	参数值
	 * @return 
	 */
	public QParam addParam(String name,Object val){
		this.put(name, val);
		return this;
	}
	
	/**
	 * 添加对象参数
	 * @param obj
	 * @return 
	 */
	public QParam addObject(Object obj){
		Map<String,Object> mapdata= MapConvertUtils.object2Map(obj);
		if(!MapUtils.isEmpty(mapdata)){
			this.putAll(mapdata);
		}
		return this;
	}
	
	public QParam removeObject(Object obj){
		Map<String,Object> mapdata=MapConvertUtils.object2Map(obj);
		if(!MapUtils.isEmpty(mapdata)){
			  Set<String> keys = mapdata.keySet();
			  for (String key : keys) {
				 this.remove(key);
			}
		}
		return this;
	}

	/**
	 * 删除一个参数
	 * @param param
	 * @return 
	 */
	public QParam removeParam(String param){
		this.remove(param);
		return this;
	}
	
	/**
	 * 获取查询参数内容
	 * @return
	 */
	public Map<String,Object> getQParam(){
		return param;
	}
	
	public boolean containAnyNotNull(String...param){
		Set<String> keySet = this.keySet();
		for (int i = 0; i < param.length; i++) {
		
			if( keySet.contains(param[i])){
				Object val=MapUtils.getObject(this.param, param[i]);
				if(val!=null){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return param.toString();
	}
	
	public int size(){
		return param.size();
	}
	@Override
	public boolean isEmpty() {
		return param.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		return param.containsKey(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return param.containsValue(value);
	}
	@Override
	public Object get(Object key) {
		return param.get(key);
	}
	@Override
	public Object put(String key, Object value) {
		return param.put(key, value);
	}
	@Override
	public Object remove(Object key) {
		return param.remove(key);
	}
	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		param.putAll(m);
	}
	@Override
	public void clear() {
		param.clear();
	}
	@Override
	public Set<String> keySet() {
		return param.keySet();
	}
	@Override
	public Collection<Object> values() {
		return param.values();
	}
	@Override
	public Set<Entry<String, Object>> entrySet() {
		return param.entrySet();
	}
	
}
