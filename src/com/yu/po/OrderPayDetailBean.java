package com.yu.po;

import java.util.Date;

/**
 * 订单支付明细信息Bean
 * 
 * @author 金鱼
 */
public class OrderPayDetailBean {

	/** 主键ID */
	private String orderPayDetailId;

	/** 订单编号 */
	private String orderId;

	/** 礼券号码 */
	private String voucherNo;

	/** 礼券类别 */
	private String voucherType;

	/** 抵扣金额 */
	private Double realMoney;

	/** 备注信息 */
	private String memo;

	/** 创建用户 */
	private String createUser;

	/** 创建时间 */
	private Date createTime;

	/** 修改用户 */
	private String updateUser;

	/** 修改时间 */
	private Date updateTime;

	public OrderPayDetailBean() {

	}

	public String getOrderPayDetailId() {
		return orderPayDetailId;
	}

	public void setOrderPayDetailId(String orderPayDetailId) {
		this.orderPayDetailId = orderPayDetailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public Double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
