package com.yu.vo;

import java.util.Date;

/**
 * <b>文件名：</b>OrderQueryBean.java <br>
 * <b>功能描述:</b>订单信息查询Bean<br>
 * 
 * @author 金鱼
 */
public class OrderQueryBean extends BaseQueryBean {

	/** 主键 */
	private String orderId;

	/** 客户 */
	private String customerId;

	/** 订单状态 */
	private String orderState;
	
	/** 订单状态2*/
	private String orderState2;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	public OrderQueryBean() {

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOrderState2() {
		return orderState2;
	}

	public void setOrderState2(String orderState2) {
		this.orderState2 = orderState2;
	}

}
