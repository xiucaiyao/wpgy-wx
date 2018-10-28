package com.mobile.constants;

/**
 * 微信app常量
 */
public class WxAppConstants {
	public static final String WEIXIN_BACKURI ="/market!wechatPay.action";
	public static final String WEIXIN_NOTIFYURI ="/payWeixinAsync.action";
	
	/** 摊位客户订单该定义的时间不允许修改(60分钟) */
	public static final long VIP_ORDER_UPDATE_EFFECTIVE_TIME = 60 * 60 * 1000L;
	
	public static final String APP_NAME = "王胖果业";
	
	public static final String APP_NAME_EX = "【" + APP_NAME + "】";
}
