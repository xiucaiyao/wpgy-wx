package com.mobile.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mobile.dao.FansDao;
import com.mobile.po.wx.WeixinFansBean;

/**
 * 粉丝DAO操作实现类
 * 
 * @author 秀才
 */
@Repository("fansDao")
public class FansDaoImpl extends MobileBaseDaoImpl implements FansDao {
	
	private static Log log = LogFactory.getLog(FansDaoImpl.class);
	
	/** 
	 * 根据粉丝唯一标识获取电商平台用户信息
	 * @param openId 粉丝唯一标识
	 */
	public List<WeixinFansBean> queryFansByOpenId(String openId) {
		log.info("根据粉丝唯一标识获取电商平台用户信息");
		return getSqlSession().selectList("weixin.queryFansByOpenId", openId);
	}

	/** 
	 * 插入微信粉丝用户信息
	 * @param weixinFansBean 粉丝用户Bean
	 */
	public int insertWeixinFans(WeixinFansBean weixinFansBean) {
		log.info("插入微信粉丝用户信息");
		return getSqlSession().insert("weixin.insertWeixinFans", weixinFansBean);
	}


}
