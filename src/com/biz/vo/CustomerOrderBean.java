package com.biz.vo;

import java.util.Date;

/**
 * 用户填写的订单信息
 * 
 * @author 秀才
 */
public class CustomerOrderBean {

	/** 客户的收货地址ID */
	private String customerAddressId;

	/** 省 */
	private String province;

	/** 市 */
	private String city;

	/** 区（县） */
	private String county;

	/** 乡（镇） */
	private String town;

	/** 送货详细地址 */
	private String address;

	/** 自提点信息ID */
	private String ownReceivePlaceId;

	/** 店名 */
	private String name;

	/** 线路 */
	private String line;
	
	/** 线路排序 */
	private Integer lineOrder;

	/** 联系电话 */
	private String phone;

	/** 收货人 */
	private String receiveMan;

	/** 配送日期 */
	private Date sendDate;

	/** 配送金额 */
	private Double sendMoney;

	/** 是否配送范围内 */
	private Boolean isSendScope;

	/** 订单包装ID */
	private String packagePriceId;

	/** 订单包装类型 */
	private String orderPackingType;

	/** 订单包装价格 */
	private Double orderPackingPrice;

	/** 商品券编号 */
	private String voucherNo;

	/** 备注 */
	private String memo;

	public CustomerOrderBean() {

	}

	public String getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(String customerAddressId) {
		this.customerAddressId = customerAddressId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOwnReceivePlaceId() {
		return ownReceivePlaceId;
	}

	public void setOwnReceivePlaceId(String ownReceivePlaceId) {
		this.ownReceivePlaceId = ownReceivePlaceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Integer getLineOrder() {
		return lineOrder;
	}

	public void setLineOrder(Integer lineOrder) {
		this.lineOrder = lineOrder;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceiveMan() {
		return receiveMan;
	}

	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Double getSendMoney() {
		return sendMoney;
	}

	public void setSendMoney(Double sendMoney) {
		this.sendMoney = sendMoney;
	}

	public Boolean getIsSendScope() {
		return isSendScope;
	}

	public void setIsSendScope(Boolean isSendScope) {
		this.isSendScope = isSendScope;
	}

	public String getPackagePriceId() {
		return packagePriceId;
	}

	public void setPackagePriceId(String packagePriceId) {
		this.packagePriceId = packagePriceId;
	}

	public String getOrderPackingType() {
		return orderPackingType;
	}

	public void setOrderPackingType(String orderPackingType) {
		this.orderPackingType = orderPackingType;
	}

	public Double getOrderPackingPrice() {
		return orderPackingPrice;
	}

	public void setOrderPackingPrice(Double orderPackingPrice) {
		this.orderPackingPrice = orderPackingPrice;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
