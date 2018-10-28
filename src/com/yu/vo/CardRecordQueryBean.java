package com.yu.vo;

public class CardRecordQueryBean extends BaseQueryBean {

	/** 卡号(充值卡卡号) */
	private String cardNo;
	
	/** 套餐卡卡号 */
	private String groupCardNo;

	/** 记录类型 */
	private Integer recordType;

	public CardRecordQueryBean() {

	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getGroupCardNo() {
		return groupCardNo;
	}

	public void setGroupCardNo(String groupCardNo) {
		this.groupCardNo = groupCardNo;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

}
