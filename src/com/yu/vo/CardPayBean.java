package com.yu.vo;

/**
 * 会员卡支付订单信息
 * 
 * @author 金鱼
 */
public class CardPayBean {

	/** 订单ID */
	private String orderId;
	
	/** 订单金额 */
	private String orderPrice;
	
	/** 订餐会员卡号 */
	private String orderCardNo;
	
	/** 订餐会员卡支付密码 */
	private String payPassword;
	
	public CardPayBean(){
		
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderCardNo() {
		return orderCardNo;
	}

	public void setOrderCardNo(String orderCardNo) {
		this.orderCardNo = orderCardNo;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	
}
