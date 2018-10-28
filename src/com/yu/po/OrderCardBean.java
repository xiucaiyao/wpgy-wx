package com.yu.po;

import java.util.Date;

/**
 * 点餐会员卡Bean
 * 
 * @author 金鱼
 */
public class OrderCardBean {

	/** 主键 */
	private String orderCardId;

	/** 卡号 */
	private String orderCardNo;

	/** 卡支付密码 */
	private String payPassword;

	/** 初始金额 */
	private Double beginMoney;

	/** 现在金额 */
	private Double nowMoney;

	/** 卡状态 */
	private String cardState;

	/** 备注信息 */
	private String memo;

	/** 临时卡标志 */
	private Boolean temporaryFlag;

	/** 删除标志 */
	private Boolean delteFlag;

	/** 创建用户 */
	private String createUser;

	/** 创建时间 */
	private Date createTime;

	/** 修改用户 */
	private String updateUser;

	/** 修改时间 */
	private Date updateTime;

	public OrderCardBean() {

	}

	public OrderCardBean(String orderCardNo, String payPassword){
		this.orderCardNo = orderCardNo;
		this.payPassword = payPassword;
	}
	
	public String getOrderCardId() {
		return orderCardId;
	}

	public void setOrderCardId(String orderCardId) {
		this.orderCardId = orderCardId;
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

	public Double getBeginMoney() {
		return beginMoney;
	}

	public void setBeginMoney(Double beginMoney) {
		this.beginMoney = beginMoney;
	}

	public Double getNowMoney() {
		return nowMoney;
	}

	public void setNowMoney(Double nowMoney) {
		this.nowMoney = nowMoney;
	}

	public String getCardState() {
		return cardState;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getTemporaryFlag() {
		return temporaryFlag;
	}

	public void setTemporaryFlag(Boolean temporaryFlag) {
		this.temporaryFlag = temporaryFlag;
	}

	public Boolean getDelteFlag() {
		return delteFlag;
	}

	public void setDelteFlag(Boolean delteFlag) {
		this.delteFlag = delteFlag;
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
