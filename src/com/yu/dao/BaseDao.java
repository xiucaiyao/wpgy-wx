package com.yu.dao;

import java.util.List;
import java.util.Map;

import com.yu.po.DistionaryBean;

/**
 * 基础DAO接口
 * 
 * @author 金鱼
 */
public interface BaseDao {

	/**
	 * 插入记录
	 * 
	 * @param obj
	 */
	public <T> int insert(String _mybitsId, T obj);

	/**
	 * 更新单表
	 * 
	 * @param obj
	 */
	public <T> int update(String _mybitsId, T obj);

	/**
	 * 删除记录
	 * 
	 * @param clz
	 * @param id
	 */
	public <T> int delete(String _mybitsId, T obj);

	/**
	 * 
	 * 返回查询一览表的信息
	 * 
	 * @param <T>
	 * @param _mybitsId mybatis中对应业务标识
	 * @param _params
	 * @return
	 */
	public <T> List<T> query(String _mybitsId, Map<String, Object> _params);

	/**
	 * 查询相关列表信息
	 * 
	 * @param <T> 返回数据
	 * @param id mybatis中对应业务标识
	 * @param _params
	 * @return
	 */
	public <T> List<T> query(String _mybitsId, Object _params);

	/**
	 * 查询单个数据
	 * 
	 * @param queryString
	 * @param object
	 * @return
	 */
	public Object queryOne(String queryString, Object object);
	
	/**
	 * 数据字典表代码值查询
	 * @param code 代码
	 */
	public List<DistionaryBean> queryDictionaryByCode(String code);
	
	/**
	 * 数据字典表代码值查询
	 * @param parentCode 父键
	 */
	public List<DistionaryBean> queryDictionaryByParentCode(String parentCode);
	
	/**
	 * 获取系统数据表主键 
	 * @param tableName 数据表名称
	 */
	public String getTableId(String tableName);
	
	/**
	 * 获取系统数据表主键(根据时间算为了不重复)
	 * @param tableName 数据表名称
	 */
	public String getTableLongId(String tableName);

}
