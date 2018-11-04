package com.mobile.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.biz.vo.ReturnResultBean;
import com.mobile.vo.wx.RequestPostData;

/**
 * 微信接口Service类
 * 
 * @author 秀才
 */
public interface WeixinService {

	/**
	 * 被动回复用户消息
	 * 
	 * @param messageMap 微信服务器发送的消息Map
	 * @param projectPath 系统全路径
	 */
	public ReturnResultBean weixinReplayMessage(Map<String, String> messageMap, String projectPath);
	
	/**
	 * 微信支付商品异步操作
	 * 
	 * @param parameterMap request参数Map
	 * @throws UnsupportedEncodingException
	 */
	public ReturnResultBean payWeixinAsyn(RequestPostData data) throws UnsupportedEncodingException;
	
}
