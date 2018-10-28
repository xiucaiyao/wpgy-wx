package com.yu.vo;

import java.util.Date;
import java.util.List;

/**
 * 企业订单信息Bean
 * 
 * @author 金鱼
 */
public class EnterpriseOrderBean {

	/** 订单ID */
	private String orderId;

	/** 配送日期 */
	private Date receiveDate;

	/** 企业产品信息Bean List */
	private List<EnterpriseProductBean> enterpriseProductList;

	/** 备注 */
	private String memo;

	public EnterpriseOrderBean() {

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<EnterpriseProductBean> getEnterpriseProductList() {
		return enterpriseProductList;
	}

	public void setEnterpriseProductList(List<EnterpriseProductBean> enterpriseProductList) {
		this.enterpriseProductList = enterpriseProductList;
	}
}
