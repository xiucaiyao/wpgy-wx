package com.biz.po;

import java.util.Date;

/**
 * 订单明细信息Bean
 * 
 * @author 秀才
 */
public class OrderDetailBean {

	/** ID主键 */
	private String orderDetailId;

	/** 订单编号 */
	private String orderId;

	/** 商品编号 */
	private String serial;

	/** 规格 */
	private String spec;

	/** 单价 */
	private Double price;

	/** 订单量 */
	private Double orderNum;

	/** 配送量 */
	private Double sendNum;

	/** 实收量 */
	private Double receiveNum;

	/** 配送金额 */
	private Double sendMoney;

	/** 缺货金额 */
	private Double lackMoney;

	/** 退货金额 */
	private Double backMoney;

	/** 实收金额 */
	private Double realMoney;

	/** 加工说明 */
	private String machiningMemo;

	/** 产品名称 */
	private String productName;

	/** 是否套餐产品 */
	private Boolean isGroupProduct;

	/** 备注 */
	private String memo;

	/** 修改用户 */
	private String updateUser;

	/** 修改时间 */
	private Date updateTime;

	/** 商品信息 */
	private ProductBean productBean;

	public OrderDetailBean() {

	}

	public OrderDetailBean(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Double orderNum) {
		this.orderNum = orderNum;
	}

	public Double getSendNum() {
		return sendNum;
	}

	public void setSendNum(Double sendNum) {
		this.sendNum = sendNum;
	}

	public Double getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(Double receiveNum) {
		this.receiveNum = receiveNum;
	}

	public Double getSendMoney() {
		return sendMoney;
	}

	public void setSendMoney(Double sendMoney) {
		this.sendMoney = sendMoney;
	}

	public Double getLackMoney() {
		return lackMoney;
	}

	public void setLackMoney(Double lackMoney) {
		this.lackMoney = lackMoney;
	}

	public Double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(Double backMoney) {
		this.backMoney = backMoney;
	}

	public Double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public String getMachiningMemo() {
		return machiningMemo;
	}

	public void setMachiningMemo(String machiningMemo) {
		this.machiningMemo = machiningMemo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getIsGroupProduct() {
		return isGroupProduct;
	}

	public void setIsGroupProduct(Boolean isGroupProduct) {
		this.isGroupProduct = isGroupProduct;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

}
