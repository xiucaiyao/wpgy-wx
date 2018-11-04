package com.mobile.dao;

import java.util.List;

import com.mobile.po.wx.WeixinFansBean;

/**
 * 粉丝DAO接口
 * 
 * @author 秀才
 */
public interface FansDao extends MobileBaseDao{

	/** 
	 * 根据粉丝唯一标识获取电商平台用户信息
	 * @param openId 粉丝唯一标识
	 */
	public List<WeixinFansBean> queryFansByOpenId(String openId);
	
	/** 
	 * 插入微信粉丝用户信息
	 * @param weixinFansBean 粉丝用户Bean
	 */
	public int insertWeixinFans(WeixinFansBean weixinFansBean);
	
}
