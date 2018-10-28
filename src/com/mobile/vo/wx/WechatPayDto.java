package com.mobile.vo.wx;

import java.util.SortedMap;
import java.util.TreeMap;

import com.mobile.constants.WeixinConstants;
import com.mobile.constants.WxAppConstants;
import com.wechat.utils.TenpayUtil;

public class WechatPayDto {

	// 商品描述
	public String body;// = "美食";
	// 附加数据
	public String attach;
	// 商户订单号
	public String out_trade_no;// /orderNo;
	public String money;// 总金额以元为单位，带小数点
	// 订单生成的机器 IP
	public String spbill_create_ip;

	public String nonce_str;
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String openid;

	// 订 单 生 成 时 间 非必输
	// String time_start ="";
	// 订单失效时间 非必输
	// String time_expire = "";
	// 商品标记 非必输
	// String goods_tag = "";
	// 子商户号 非必输
	// String sub_mch_id="";
	// 设备号 非必输
	// String device_info = "";

	/**
	 * 获取独享
	 * 
	 * @param dto
	 * @return
	 */
	public static SortedMap<String, String> getPackageParams(WechatPayDto dto, String projectUrl) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", WeixinConstants.WEIXIN_APP_ID);
		packageParams.put("mch_id", WeixinConstants.WEIXIN_MCH_ID);
		packageParams.put("nonce_str",dto.nonce_str);
		packageParams.put("body", dto.body);
		packageParams.put("attach", dto.attach);
		packageParams.put("out_trade_no", dto.out_trade_no);
		packageParams.put("total_fee", WechatPayDto.returnFinalmoney(dto.money)+"");
		packageParams.put("spbill_create_ip", dto.spbill_create_ip);
		packageParams.put("notify_url", projectUrl + WxAppConstants.WEIXIN_NOTIFYURI);// 这里notify_url是
		// 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		packageParams.put("trade_type", "JSAPI");
		packageParams.put("openid", dto.openid);
		return packageParams;
	}

	

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 随机数
	 * 
	 * @return
	 */
	public static String getNonce_str() {
		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 随机数10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		return nonce_str;
	}

	/**
	 * 金额转化为分为单位
	 * 总金额以分为单位，不带小数点
	 * @return
	 */
	private static int returnFinalmoney(String money) {
		//String money = "0.01";
		float sessionmoney = Float.parseFloat(money);
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		return Integer.parseInt(finalmoney);
	}

}
