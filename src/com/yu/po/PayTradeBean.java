package com.yu.po;

import java.util.Date;

/**
 * 交易支付信息Bean
 * 
 * @author 金鱼
 */
public class PayTradeBean {

	/** PK，自动增加 */
	private String payTradeId;

	/** 充值消费记录表ID */
	private String cardRecordId;

	/** 系统支付订单号 */
	private String outTradeNo;

	/** 第三方交易号 */
	private String tradeNo;

	/** 用户系统登陆名 */
	private String loginName;

	/** 点餐会员卡号 */
	private String cardNo;

	/** 开始支付时间 */
	private Date beginPayTime;

	/** 结束支付时间 */
	private Date endPayTime;

	/** 支付金额 */
	private Double payMoney;

	/** 支付状态 */
	private Integer payStatus;

	/** 返回结果 */
	private String payResult;

	/** 客户姓名 */
	private String customerName;

	/** 备注 */
	private String memo;

	/** 创建时间 */
	private Date createTime;

	public PayTradeBean() {

	}
	
	public PayTradeBean(String payTradeId) {
		this.payTradeId = payTradeId;
	}

	public String getPayTradeId() {
		return payTradeId;
	}

	public void setPayTradeId(String payTradeId) {
		this.payTradeId = payTradeId;
	}

	public String getCardRecordId() {
		return cardRecordId;
	}

	public void setCardRecordId(String cardRecordId) {
		this.cardRecordId = cardRecordId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getBeginPayTime() {
		return beginPayTime;
	}

	public void setBeginPayTime(Date beginPayTime) {
		this.beginPayTime = beginPayTime;
	}

	public Date getEndPayTime() {
		return endPayTime;
	}

	public void setEndPayTime(Date endPayTime) {
		this.endPayTime = endPayTime;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayResult() {
		return payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
