package com.biz.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biz.po.DistionaryBean;

/**
 * 基础DAO操作类
 * 
 * @author 秀才
 */
@Repository("baseDao")
public class BaseDaoImpl extends SqlSessionDaoSupport {

	private static Log log = LogFactory.getLog(BaseDaoImpl.class);

	@Autowired
	public void setSqlSessionFactoryOverride(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * 获取相关的数据库连接
	 */
	public Connection getConnection() {
		log.info("获取数据库连接...");
		return getSqlSession().getConnection();
	}

	/**
	 * 删除记录
	 * 
	 * @param clz
	 * @param id
	 */
	public <T> int delete(String _mybitsId, T obj) {
		return getSqlSession().delete(_mybitsId, obj);
	}

	/**
	 * 插入记录
	 * 
	 * @param obj
	 */
	public <T> int insert(String _mybitsId, T obj) {
		return getSqlSession().insert(_mybitsId, obj);
	}

	/**
	 * 更新单表
	 * 
	 * @param obj
	 */
	public <T> int update(String _mybitsId, T obj) {
		return getSqlSession().update(_mybitsId, obj);
	}

	/**
	 * 
	 * 返回查询一览表的信息
	 * 
	 * @param <T>
	 * @param _mybitsId mybatis中对应业务标识
	 * @param _params
	 * @return
	 */
	public <T> List<T> query(String _mybitsId, Map<String, Object> _params) {
		return getSqlSession().selectList(_mybitsId, _params);
	}

	/**
	 * 查询相关列表信息
	 * 
	 * @param <T> 返回数据
	 * @param id mybatis中对应业务标识
	 * @param _params
	 * @return
	 */
	public <T> List<T> query(String _mybitsId, Object _params) {
		return getSqlSession().selectList(_mybitsId, _params);
	}

	/**
	 * 查询单个数据
	 * 
	 * @param queryString
	 * @param object
	 * @return
	 */
	public Object queryOne(String _mybitsId, Object object) {
		return getSqlSession().selectOne(_mybitsId, object);
	}
	
	/**
	 * 数据字典表代码值查询
	 * @param code 代码
	 */
	public List<DistionaryBean> queryDictionaryByCode(String code){
		log.info("绿和数据字典表代码值查询");
		return getSqlSession().selectList("system.queryDictionaryByCode", code);
	}
	
	/**
	 * 数据字典表代码值查询
	 * @param parentCode 父键
	 */
	public List<DistionaryBean> queryDictionaryByParentCode(String parentCode){
		log.info("数据字典表代码值查询");
		return getSqlSession().selectList("system.queryDictionaryByParentCode", parentCode);
	}
	
	/**
	 * 获取系统数据表主键 
	 * @param tableName 数据表名称
	 */
	public String getTableId(String tableName){
		log.info("获取系统数据表主键 ");
		return getSqlSession().selectOne("system.getTableId", tableName);
	}
	
	/**
	 * 获取系统数据表主键(根据时间算为了不重复)
	 * @param tableName 数据表名称
	 */
	public String getTableLongId(String tableName){
		log.info("获取系统数据表主键(根据时间算为了不重复)");
		return getSqlSession().selectOne("system.getTableLongId", tableName);
	}
}
