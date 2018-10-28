package com.yu.po;

import java.util.Date;

/**
 * 充值消费记录信息Bean
 * 
 * @author 金鱼
 */
public class CardRecordBean {

	/** 编号 */
	private String cardRecordId;

	/** 会员卡类型 */
	private String cardType;

	/** 卡号 */
	private String cardNo;

	/** 日期 */
	private Date recordDate;

	/** 记录类型 */
	private Integer recordType;

	/** 金额 */
	private Double inOutMoney;

	/** 赠送金额 */
	private Double giveMoney;
	
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

	public CardRecordBean(){
		
	}

	public String getCardRecordId() {
		return cardRecordId;
	}

	public void setCardRecordId(String cardRecordId) {
		this.cardRecordId = cardRecordId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public Double getInOutMoney() {
		return inOutMoney;
	}

	public void setInOutMoney(Double inOutMoney) {
		this.inOutMoney = inOutMoney;
	}

	public Double getGiveMoney() {
		return giveMoney;
	}

	public void setGiveMoney(Double giveMoney) {
		this.giveMoney = giveMoney;
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
