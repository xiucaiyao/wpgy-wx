package com.mobile.vo.wx;

/**
 * 粉丝信息Bean
 * 
 * @author 金金
 */
public class FansBean {

	/** 主键 */
	private String weixinFansId;

	/** 用户ID */
	private String customerId;

	/** 用户类型 */
	private String customerType;
	
	/** 公众号的粉丝的标识 */
	private String openId;

	/** 公众号的粉丝的标识 */
	private String unionId;

	/** 微信平台粉丝的标识 */
	private Boolean isSubcribie;

	public FansBean() {

	}

	public String getWeixinFansId() {
		return weixinFansId;
	}

	public void setWeixinFansId(String weixinFansId) {
		this.weixinFansId = weixinFansId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Boolean getIsSubcribie() {
		return isSubcribie;
	}

	public void setIsSubcribie(Boolean isSubcribie) {
		this.isSubcribie = isSubcribie;
	}

}
