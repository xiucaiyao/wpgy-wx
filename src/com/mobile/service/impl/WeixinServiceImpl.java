package com.mobile.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.dao.OrderDao;
import com.biz.po.OrderBean;
import com.biz.po.PayTradeBean;
import com.biz.vo.ReturnResultBean;
import com.mobile.constants.WxAppConstants;
import com.mobile.service.WeixinService;
import com.mobile.utils.MakeReplyMessageUtil;
import com.mobile.vo.wx.ReplyArticleBean;
import com.mobile.vo.wx.RequestPostData;

/**
 * 微信接口Service类
 * 
 * @author 秀才
 */
@Service("weixinService")
public class WeixinServiceImpl implements WeixinService{

	private static Log log = LogFactory.getLog(WeixinServiceImpl.class);
	
	@Resource
	private OrderDao orderDao;
	
	/**
	 * 被动回复用户消息
	 * 
	 * @param messageMap 微信服务器发送的消息Map
	 * @param projectPath 系统全路径
	 */
	public ReturnResultBean weixinReplayMessage(Map<String, String> messageMap, String projectPath){
		log.info("被动回复用户消息");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if(messageMap == null || messageMap.isEmpty() || StringUtils.isBlank(messageMap.get("MsgType"))){
			return returnResultBean;
		}
		String messageType = messageMap.get("MsgType");
		String fromusername = messageMap.get("ToUserName");
		String tousername = messageMap.get("FromUserName");
		String xmlMessage = null;
		if("text".equals(messageType)){
			//粉丝发送消息给公众号
		} else if("event".equals(messageType)){
			//微信服务器事件推送通知开发者中心，微信用户和公众号产生交互信息
			String eventType = messageMap.get("Event");
			if("subscribe".equals(eventType)){
				// 关注时回复信息
				List<ReplyArticleBean> replyArticleList = new ArrayList<ReplyArticleBean>();
				ReplyArticleBean replyArticleBean = new ReplyArticleBean();
				replyArticleBean.setTitle("感谢您关注" + WxAppConstants.APP_NAME_EX);
				replyArticleBean.setPicUrl(projectPath+"/images/mobile/replay.jpg");
				replyArticleBean.setUrl(projectPath+"/fans!gotoLogin.action"); 
				replyArticleList.add(replyArticleBean);
				xmlMessage = MakeReplyMessageUtil.sendNewsmessage(tousername, fromusername, replyArticleList);
			} else if("SCAN".equals(eventType)){
				// 用户已关注时的事件推送
				List<ReplyArticleBean> replyArticleList = new ArrayList<ReplyArticleBean>();
				ReplyArticleBean replyArticleBean = new ReplyArticleBean();
				replyArticleBean.setTitle("您已经关注了" + WxAppConstants.APP_NAME_EX);
				replyArticleBean.setPicUrl(projectPath+"/images/mobile/replay.jpg");
				replyArticleBean.setUrl(projectPath+"/fans!gotoLogin.action"); 
				replyArticleList.add(replyArticleBean);
				xmlMessage = MakeReplyMessageUtil.sendNewsmessage(tousername, fromusername, replyArticleList);
			} else if("unsubscribe".equals(eventType)){
				// 粉丝取消关注时回复信息
				List<ReplyArticleBean> replyArticleList = new ArrayList<ReplyArticleBean>();
				ReplyArticleBean replyArticleBean = new ReplyArticleBean();
				replyArticleBean.setTitle(WxAppConstants.APP_NAME_EX);
//				replyArticleBean.setDescription("一直以来感谢您对我们的大力支持，我们会更加努力让您满意！");
				replyArticleBean.setPicUrl(projectPath+"/images/mobile/replay.jpg");
				replyArticleBean.setUrl(projectPath+"/fans!gotoLogin.action"); 
				replyArticleList.add(replyArticleBean);
				xmlMessage = MakeReplyMessageUtil.sendNewsmessage(tousername, fromusername, replyArticleList);
			}
		}
		if(xmlMessage == null){
			//目前该功能没有要求，不管粉丝发什么信息或什么其他事件推送只做一个默认图文推送信息
			List<ReplyArticleBean> replyArticleList = new ArrayList<ReplyArticleBean>();
			ReplyArticleBean replyArticleBean = new ReplyArticleBean();
			replyArticleBean.setTitle( WxAppConstants.APP_NAME_EX);
//			replyArticleBean.setDescription("一直以来感谢您对我们的大力支持，我们会更加努力让您满意！");
			replyArticleBean.setPicUrl(projectPath+"/images/mobile/replay.jpg");
			replyArticleBean.setUrl(projectPath+"/fans!gotoLogin.action");
			replyArticleList.add(replyArticleBean);
			xmlMessage = MakeReplyMessageUtil.sendNewsmessage(tousername, fromusername, replyArticleList);
		}
		returnResultBean.operationSuccess();
		returnResultBean.addReturnData("message", xmlMessage);
		return returnResultBean;
	}
	
	/**
	 * 微信支付商品异步操作
	 * 
	 * @param parameterMap request参数Map
	 * @throws UnsupportedEncodingException
	 */
	@Transactional(rollbackFor = { Exception.class })
	public ReturnResultBean payWeixinAsyn(RequestPostData data) throws UnsupportedEncodingException {
		log.info("微信支付商品异步操作");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		String out_trade_no = data.getOut_trade_no(); //商家交易号
		String trade_no = data.getTransaction_id(); //微信订单号
		PayTradeBean payTradeBean = orderDao.queryPayTradeByOutTradeNo(out_trade_no);
		if (payTradeBean == null) {
			return returnResultBean;
		}
		String trade_status = data.getResult_code();
		if(!"SUCCESS".equals(trade_status)){
			payTradeBean.setTradeNo(trade_no);
			payTradeBean.setPayStatus(3);
			payTradeBean.setPayResult(trade_status);
			orderDao.updatePayTrade(payTradeBean);
			return returnResultBean;
		}
		if (payTradeBean.getPayStatus() == 2) {
			returnResultBean.operationSuccess();
			return returnResultBean;
		}
		List<OrderBean> orderList = orderDao.queryOrder(new OrderBean(payTradeBean.getOutTradeNo(), null));
		if (orderList == null || orderList.isEmpty()) {
			returnResultBean.setMessage("微信支付的订单信息未找到，请联系客服人员！");
			return returnResultBean;
		}
		/** 修改订单状态信息 **/
		OrderBean orderBean = orderList.get(0);
		orderBean.setOrderState("初始");
		orderBean.setUpdateUser(orderBean.getCustomerId());
		orderBean.setUpdateTime(new Date());
		orderBean.setPayType("5");
		orderDao.updateOrderState(orderBean);
		/** 修改第三方交易信息 **/
		payTradeBean.setTradeNo(trade_no);
		payTradeBean.setPayResult(trade_status);
		payTradeBean.setEndPayTime(new Date());
		payTradeBean.setPayStatus(2);
		payTradeBean.setMemo("订单网上微信支付成功,订单编号：" + orderBean.getOrderId());
		orderDao.updatePayTrade(payTradeBean);
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
}
