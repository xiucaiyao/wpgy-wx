package com.yu.po;

import java.util.Date;

/**
 * 商品券Bean
 * 
 * @author 金鱼
 */
public class VoucherBean {

	/** 主键 */
	private String voucherId;

	/** 礼券号码 */
	private String voucherNo;

	/** 礼券类别 */
	private String voucherType;

	/** 面值 */
	private Double parValue;
	
	/** 购买价格 */
	private Double salePrice;
	
	/** 是否赠送 */
	private Boolean isGive;

	/** 充值密码 */
	private String rechangePassword;

	/** 状态 */
	private String state;

	/** 备注信息 */
	private String memo;

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
	
	/** 充值卡类型 */
	private String cardTypeId;
	
	/** 购买人 */
	private String buyMan;
	
	/** 购买日期 */
	private Date saleDate;
	
	/** 充值日期 */
	private Date rechangeDate;
	
	/** 充值到充值卡的客户ID */
	private String rechargeCustomerId;

	public VoucherBean() {

	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
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

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Boolean getIsGive() {
		return isGive;
	}

	public void setIsGive(Boolean isGive) {
		this.isGive = isGive;
	}

	public String getRechangePassword() {
		return rechangePassword;
	}

	public void setRechangePassword(String rechangePassword) {
		this.rechangePassword = rechangePassword;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(String cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getBuyMan() {
		return buyMan;
	}

	public void setBuyMan(String buyMan) {
		this.buyMan = buyMan;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Date getRechangeDate() {
		return rechangeDate;
	}

	public void setRechangeDate(Date rechangeDate) {
		this.rechangeDate = rechangeDate;
	}

	public String getRechargeCustomerId() {
		return rechargeCustomerId;
	}

	public void setRechargeCustomerId(String rechargeCustomerId) {
		this.rechargeCustomerId = rechargeCustomerId;
	}
}
