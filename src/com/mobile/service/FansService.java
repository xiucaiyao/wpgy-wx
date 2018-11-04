package com.mobile.service;

import com.biz.vo.ReturnResultBean;
import com.mobile.vo.wx.LoginFansBean;
import com.mobile.vo.wx.RegistFansBean;

/**
 * 粉丝绑定 注册等Service操作类
 * 
 * @author 秀才
 */
public interface FansService {

	/**
	 * 查询粉丝用户对应的用户信息
	 * @param openId 粉丝在微信公众号的唯一标识
	 */
	public ReturnResultBean queryFansByOpenId(String openId);
	
	
	/**
	 * 绑定粉丝用户信息
	 * @param loginFansBean 粉丝登录信息Bean
	 */
	public ReturnResultBean bindFans(LoginFansBean loginFansBean);
	
	/**
	 * 验证用户名是否存在
	 * @param customerBean 用户信息Bean
	 */
	public ReturnResultBean validateNameIsExist(String loginName);
	
	/**
	 * 粉丝注册
	 * 
	 * @param registFansBean 粉丝注册Bean
	 * @param openId 粉丝唯一标识
	 */
	public ReturnResultBean regist(RegistFansBean registFansBean, String openId);
}
