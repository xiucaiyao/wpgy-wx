package com.yu.po;

import java.util.Date;

/**
 * 会员信息Bean
 * 
 * @author 金鱼
 */
public class CustomerBean {

	/** 主键 */
	private String customerId;

	/** 名称 */
	private String name;

	/** 套餐会员卡 */
	private String groupCard;

	/** 点餐会员卡 */
	private String orderCard;

	/** 电话 */
	private String phone;

	/** 联系人 */
	private String linkman;

	/** 联系人电话 */
	private String linkmanTel;

	/** 客户类型 */
	private String customerType;

	/** 大客户类型 */
	private String vipCustomerType;

	/** 出生日期 */
	private Date birthday;

	/** 登录名 */
	private String loginName;

	/** 登陆密码 */
	private String password;

	/** 周几配送 */
	private String sendWeek;

	/** 邮箱 */
	private String email;

	/** 拼音缩写 */
	private String nameFirstHeadLetter;

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
	
	/** 大客户价格级别 */
	private String priceGrade;

	public CustomerBean() {

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupCard() {
		return groupCard;
	}

	public void setGroupCard(String groupCard) {
		this.groupCard = groupCard;
	}

	public String getOrderCard() {
		return orderCard;
	}

	public void setOrderCard(String orderCard) {
		this.orderCard = orderCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmanTel() {
		return linkmanTel;
	}

	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getVipCustomerType() {
		return vipCustomerType;
	}

	public void setVipCustomerType(String vipCustomerType) {
		this.vipCustomerType = vipCustomerType;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLoginName() {
		if (loginName != null) {
			loginName = loginName.trim();
		}
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSendWeek() {
		return sendWeek;
	}

	public void setSendWeek(String sendWeek) {
		this.sendWeek = sendWeek;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameFirstHeadLetter() {
		return nameFirstHeadLetter;
	}

	public void setNameFirstHeadLetter(String nameFirstHeadLetter) {
		this.nameFirstHeadLetter = nameFirstHeadLetter;
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

	public String getPriceGrade() {
		return priceGrade;
	}

	public void setPriceGrade(String priceGrade) {
		this.priceGrade = priceGrade;
	}

}
