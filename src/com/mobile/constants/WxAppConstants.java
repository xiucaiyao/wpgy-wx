package com.mobile.constants;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 微信app常量
 */
public class WxAppConstants {
	public static final String MD5_SCRECT = "wpgy.md5.wx";
	public static final String WEIXIN_BACKURI ="/market!wechatPay.action";
	public static final String WEIXIN_NOTIFYURI ="/payWeixinAsync.action";
	
	/** 摊位客户订单该定义的时间不允许修改(60分钟) */
	public static final long VIP_ORDER_UPDATE_EFFECTIVE_TIME = 60 * 60 * 1000L;
	
	public static final String APP_NAME = "王胖果业";
	
	public static final String APP_NAME_EX = "【" + APP_NAME + "】";
	
	public static final String ENV_TEST = "env_test";
	public static final String ENV_PRODUCT = "env_product";
	
	private static String envFlag = null ;
	
	public static String getEnvFlag(){
		try {
			if (StringUtils.isBlank(envFlag)) {
				Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
				envFlag = properties.getProperty("wx.app.env");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ENV_PRODUCT;
		}
		return envFlag;
	}
}
