package com.biz.constants;

/**
 * 系统静态变量
 * 
 * @author 秀才
 * 
 */
public class SysConstants {

	/** 网站用户最近浏览的产品Cookie名称 */
	public static final String RECENTLY_BROWSE_COOKIE = "recently_browse_cookie";

	/** 网站用户最近浏览的产品Cookie存放时间 */
	public static final int RECENTLY_BROWSE_COOKIE_MAX_TIME = 7 * 24 * 60 * 60;

	/** 网站用户最近浏览的Cookie存放产品产品的个数 */
	public static final int RECENTLY_BROWSE_COOKIE_MAX_NUMBER = 5;

	/** 电子商务网站登录的session名 */
	public static final String LOGIN_USER_SESSION = "tldl_user";

	/** 电子商务网站充值卡用户购物车的session名 */
	public static final String SHOPPING_CART_SESSION = "tldl_shoping_cart";
	
	/** 电子商务网站市场用户购物车的session名 */
	public static final String SHOPPING_MARKET_CART_SESSION = "tldl_market_shoping_cart";

	/** 未在配送地址范围内或者未审核的地址，配送金额为10元 */
	public static final double OUT_RANGE_SEND_MONEY = 10d;

	/** 支付宝订单异步通知URL */
	public static final String ALIPAY_NOTIFY_URL = "/order/order!recharge.action";

	/** 支付宝订单同步通知URL */
	public static final String ALIPAY_RETURN_URL = "/order/orderRechargeReturn.action";

	/** 支付宝充值卡异步通知URL */
	public static final String ALIPAY_RECHARGE_CARD_NOTIFY_URL = "/card!buyRechargeCardAliAsync.action";

	/** 支付宝充值卡同步通知URL */
	public static final String ALIPAY_RECHARGE_CARD_RETURN_URL = "/cardBuyRechargeCardAliReturn.action";

	/** 支付宝套餐卡异步通知URL */
	public static final String ALIPAY_GROUP_CARD_NOTIFY_URL = "/card!buyGroupCardAliAsync.action";

	/** 支付宝套餐卡同步通知URL */
	public static final String ALIPAY_GROUP_CARD_RETURN_URL = "/cardBuyGroupCardAliReturn.action";

	/** 卖家支付宝帐户 */
//	public static final String ALIPAY_SELLER_EMAIL = "349250347@qq.com";
	public static final String ALIPAY_SELLER_EMAIL = "joyjiajia@qq.com";
	
	/** 订单超过价格免运费 */
	public static final double FREE_POSTAGE_MONEY = 88d;

	/** 支付宝网银支付默认银行简码 */
	public static final String ALIPAY_DEFAULT_BANK = "ABCBTB";

	/** 大客户订单该定义的时间不允许修改(30分钟) */
	public static final long VIP_ORDER_UPDATE_EFFECTIVE_TIME = 30 * 60 * 1000L;

	// BOCB2C 中国银行
	// ICBCB2C 中国工商银行
	// ICBCBTB 中国工商银行(B2B)
	// CMB 招商银行
	// CCB 中国建设银行
	// CCBBTB 中国建设银行(B2B)
	// ABC 中国农业银行
	// ABCBTB 中国农业银行(B2B)
	// SPDB 上海浦东发展银行
	// SPDBB2B 上海浦东发展银行(B2B)
	// CIB 兴业银行
	// GDB 广东发展银行
	// SDB 深圳发展银行
	// CMBC 中国民生银行
	// COMM 交通银行
	// CITIC 中信银行
	// CEBBANK 光大银行
	// NBBANK 宁波银行
	// HZCBB2C 杭州银行
	// SHBANK 上海银行
	// SPABANK 平安银行
	// BJRCB 北京农村商业银行
	// fdb101 富滇银行
	// PSBC-DEBIT 中国邮政储蓄银行
	// BJBANK 北京银行
}
