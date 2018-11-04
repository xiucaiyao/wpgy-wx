package com.biz.po;

import java.util.Date;

/**
 * 订单信息Bean
 * 
 * @author 秀才
 */
public class OrderBean {

	/** 主键 */
	private String orderId;

	/** 订单编号 */
	private String orderNo;

	/** 客户 */
	private String customerId;

	/** 订单日期 */
	private Date orderDate;

	/** 配送日期 */
	private Date sendDate;

	/** 配货人 */
	private String sendMan;

	/** 送货人 */
	private String deliverMan;

	/** 收货人 */
	private String receiveMan;

	/** 订单状态 */
	private String orderState;

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

	/** 客户类型 */
	private String customerType;

	/** 套餐ID */
	private String groupId;

	/** 套餐数量 */
	private Integer groupNumber;

	/** 订单包装类型 */
	private String orderPackingType;

	/** 订单包装价格 */
	private Double orderPackingPrice;

	/** 订单金额 */
	private Double orderMoney;

	/** 订单类型 */
	private String orderType;

	/** 送货方式 */
	private String sendType;

	/** 支付方式 */
	private String payType;

	/** 配送金额 */
	private Double sendMoney;

	/** 订单来源 */
	private String orderSource;

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

	public OrderBean() {
		this.orderSource = "电子商城";
	}

	public OrderBean(String orderId, String customerId) {
		this.orderId = orderId;
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendMan() {
		return sendMan;
	}

	public void setSendMan(String sendMan) {
		this.sendMan = sendMan;
	}

	public String getDeliverMan() {
		return deliverMan;
	}

	public void setDeliverMan(String deliverMan) {
		this.deliverMan = deliverMan;
	}

	public String getReceiveMan() {
		return receiveMan;
	}

	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
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

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
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

	public Double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Double getSendMoney() {
		return sendMoney;
	}

	public void setSendMoney(Double sendMoney) {
		this.sendMoney = sendMoney;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
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
