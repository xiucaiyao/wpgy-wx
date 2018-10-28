package com.mobile.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yu.dao.impl.BaseDaoImpl;

/**
 * 微信DAO操作类(目前没有方法，主要用于以后扩展)
 * 
 * @author 金鱼
 */
@Repository("mobileBaseDao")
public class MobileBaseDaoImpl extends BaseDaoImpl {

	private static Log log = LogFactory.getLog(MobileBaseDaoImpl.class);

}
