package com.biz.po;

/**
 * 卡类型信息Bean
 * 
 * @author 秀才
 */
public class CardTypeBean {

	/** 主键 */
	public String cardTypeId;

	/** 卡名称 */
	public String name;

	/** 面值 */
	public Double parValue;
	
	/** 销售价格 */
	public Double salePrice;

	/** 是否是套餐会员卡类型 */
	public Integer cardType;

	/** 小图片 */
	public String smallPic;

	/** 大图片 */
	public String bigPic;

	/** 是否上线 */
	public String isOnline;

	/** 描述 */
	public String describe;

	/** 备注信息 */
	public String memo;

	/** 卡数量 */
	public int cardNumber;

	public CardTypeBean() {

	}

	public String getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(String cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getBigPic() {
		return bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

}
