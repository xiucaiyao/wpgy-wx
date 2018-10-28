package com.mobile.vo.wx;

import java.io.Serializable;

public class RequestPostData implements Serializable {

	private static final long serialVersionUID = -3569880334829943148L;
	/**
	 * <xml><appid><![CDATA[wxd4de20750a93fc3c]]></appid><bank_type><![CDATA[ CMB_CREDIT ]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA [CNY]]><
	 * /fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA [10017855 ]]></mch_id><nonce_str><![CDATA[dr4kyog76jn4vu3owmw7570coakzlv75]] ></nonce_str
	 * ><openid><![CDATA[o9Klkt10mPo6EIOg1rJOrDNWFlso]]></openid><out_trade_no ><![CDATA[91047041542]]></out_trade_no><result_code><![CDATA[SUCCESS]]></
	 * result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[3 A3A764606F404E6012B62A35D6CB097 ]]></sign><time_end><![CDATA[20141209104714] ]></time_end><total_fee>1</total_fee
	 * ><trade_type><![CDATA[JSAPI]]></trade_type ><transaction_id><![CDATA[1000950063201412090006827556 ]]></transaction_id></xml>
	 */
	// 商户订单号,商户系统的订单号，与请求一致
	private String out_trade_no;
	// 微信支付订单号
	private String transaction_id;
	// 金额,以分为单位
	private String total_fee;
	// 微信支付签名
	private String sign;
	// 业务结果SUCCESS/FAIL
	private String result_code;
	// 对业务结果为FAIL时结果信息描述
	private String err_code_des;
	// 商家数据包，原样返回
	private String attach;

	private String openid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

}
