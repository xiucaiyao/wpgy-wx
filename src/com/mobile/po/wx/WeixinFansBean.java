package com.mobile.po.wx;

import java.util.Date;

/**
 * 微信粉丝 Bean
 * 
 * @author 金鱼
 */
public class WeixinFansBean {

	/** 主键 */
	public String weixinFansId;

	/** 用户ID */
	public String customerId;

	/** 用户类型 */
	public String customerType;

	/** 公众号的粉丝的标识 */
	public String openId;

	/** 微信平台粉丝的标识 */
	public String unionId;

	/** 最后关注时间 */
	public Date lastSubscribeTime;

	/** 最后解绑时间 */
	public Date lastUnsubscribeTime;

	/** 是否关注 */
	public Boolean isSubscribe;

	/** 粉丝昵称 */
	public String nickname;

	/** 性别 */
	public String gender;

	/** 头像 */
	public String headerimage;

	/** 国家 */
	public String country;

	/** 省份 */
	public String province;

	/** 城市 */
	public String city;

	/** 粉丝所在的分组ID */
	public String groupId;

	/** 备注 */
	public String remark;

	/** 创建日期 */
	public Date createTime;

	/** 创建用户 */
	public String createUser;

	public WeixinFansBean() {

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

	public Date getLastSubscribeTime() {
		return lastSubscribeTime;
	}

	public void setLastSubscribeTime(Date lastSubscribeTime) {
		this.lastSubscribeTime = lastSubscribeTime;
	}

	public Date getLastUnsubscribeTime() {
		return lastUnsubscribeTime;
	}

	public void setLastUnsubscribeTime(Date lastUnsubscribeTime) {
		this.lastUnsubscribeTime = lastUnsubscribeTime;
	}

	public Boolean getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeaderimage() {
		return headerimage;
	}

	public void setHeaderimage(String headerimage) {
		this.headerimage = headerimage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}
