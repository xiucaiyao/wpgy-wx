package com.mobile.vo.wx;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.mobile.constants.WeixinConstants;
import com.mobile.utils.WeixinAPIUtils;

/**
 * 微信SDK配置Bean
 * 
 * @author 金鱼
 */
public class WeiXinSdkConfigBean {

	private static WeiXinSdkConfigBean weiXinSdkConfigBean = new WeiXinSdkConfigBean();

	/** 应用ID */
	private String weixinAppid;

	/** 公众号应用密钥 */
	private String weixinAppSecret;

	/** 公众号的全局唯一票据 */
	private String accessToken;

	/** 公众号用于调用微信JS接口的临时票据 */
	private String jsapiTicket;

	/** 公众号SDK 签名 */
	private String signature;

	/** 签名随机字符串 */
	private String nonceStr;

	/** 签名时间戳 */
	private String timestamp;

	/** 有效结束时间 */
	private Long endExpiresTime;

	/** SDK配置是否成功 */
	private boolean isSuccess;

	private WeiXinSdkConfigBean() {
		weixinAppid = WeixinConstants.WEIXIN_APP_ID;
		weixinAppSecret = WeixinConstants.WEIXIN_APP_SECRET;
	}

	public static WeiXinSdkConfigBean getInstance(String currPageUrl) {
		weiXinSdkConfigBean.WeiXinSdkConfig(currPageUrl);
		return weiXinSdkConfigBean;
	}

	/**
	 * 初始化微信SDK配置
	 */
	public void WeiXinSdkConfig(String currPageUrl) {
		if(StringUtils.isBlank(currPageUrl) ||! currPageUrl.startsWith("http://")){
			isSuccess = false;
			return;
		}
		isSuccess = true;
		if (endExpiresTime == null || endExpiresTime < System.currentTimeMillis()) {
			try {
				accessToken = null;
				jsapiTicket = null;
				endExpiresTime = System.currentTimeMillis();
				accessToken = WeixinAPIUtils.getAccessToken(weixinAppid, weixinAppSecret);
				JSONObject jsonObject = WeixinAPIUtils.getJsapiTicket(accessToken);
				if ("0".equals(jsonObject.getString("errcode"))) {
					jsapiTicket = jsonObject.getString("ticket");
				}
				endExpiresTime += 7200000L;
			} catch (Exception e) {
				e.printStackTrace();
				isSuccess = false;
				return;
			}
		}
		nonceStr = WeixinAPIUtils.generateNonceStr();
		timestamp = WeixinAPIUtils.generateTimestamp();
		signature = WeixinAPIUtils.getSignature(jsapiTicket, currPageUrl, nonceStr, timestamp);
	}

	public String getWeixinAppid() {
		return weixinAppid;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public String getSignature() {
		return signature;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

}
