package com.yu.po;

import java.util.Date;


/**
 * 会员地址信息Bean
 * 
 * @author 金鱼
 */
public class CustomerAddressBean {

	/** 主键ID */
	private String customerAddressId;

	/** 客户ＩＤ */
	private String customerId;

	/** 省 */
	private String province;

	/** 市 */
	private String city;

	/** 区（县） */
	private String county;

	/** 乡（镇） */
	private String town;

	/** 联系人 */
	private String linkman;

	/** 联系电话 */
	private String linkmanTel;

	/** 详细地址 */
	private String detailAddress;

	/** 是否默认地址 */
	private Boolean isDefaultAddress;

	/** 线路 */
	private String lina;

	/** 线路排序 */
	private Integer lineOrder;

	/** 是否审核通过 */
	private Boolean isAudit;

	/** 是否配送范围 */
	private Boolean isSendScope;

	/** 创建用户 */
	private String createUser;

	/** 创建时间 */
	private Date createTime;

	public CustomerAddressBean() {

	}

	public CustomerAddressBean(String customerAddressId, String customerId) {
		this.customerAddressId = customerAddressId;
		this.customerId = customerId;
	}

	public String getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(String customerAddressId) {
		this.customerAddressId = customerAddressId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
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

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Boolean getIsDefaultAddress() {
		return isDefaultAddress;
	}

	public void setIsDefaultAddress(Boolean isDefaultAddress) {
		this.isDefaultAddress = isDefaultAddress;
	}

	public String getLina() {
		return lina;
	}

	public void setLina(String lina) {
		this.lina = lina;
	}

	public Integer getLineOrder() {
		return lineOrder;
	}

	public void setLineOrder(Integer lineOrder) {
		this.lineOrder = lineOrder;
	}

	public Boolean getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Boolean isAudit) {
		this.isAudit = isAudit;
	}

	public Boolean getIsSendScope() {
		return isSendScope;
	}

	public void setIsSendScope(Boolean isSendScope) {
		this.isSendScope = isSendScope;
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

}
