package com.mobile.po.wx;

import java.util.Date;

public class WeixinRefundApplyBean {

	private String weixinRefundApplyId;
	
	private String customerId;
	
	private String orderId;
	
	private Double refundMoney;
	
	private Date createTime;
	
	private String refundReason;
	
	public WeixinRefundApplyBean(){
		
	}

	public String getWeixinRefundApplyId() {
		return weixinRefundApplyId;
	}

	public void setWeixinRefundApplyId(String weixinRefundApplyId) {
		this.weixinRefundApplyId = weixinRefundApplyId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	
}
