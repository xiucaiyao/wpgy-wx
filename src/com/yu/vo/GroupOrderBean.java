package com.yu.vo;

import java.util.List;

/**
 * 套餐订单Bean
 * 
 * @author 金鱼
 */
public class GroupOrderBean {

	/** 自提点ID */
	private String ownReceivePlaceId;

	/** 客户的收货地址ID */
	private String customerAddressId;

	/** 联系电话 */
	private String phone;

	/** 收货人 */
	private String receiveMan;

	/** 备注 */
	private String memo;

	/** 套餐选择产品信息List */
	private List<GroupProductBean> groupProductList;

	public GroupOrderBean() {

	}

	public String getOwnReceivePlaceId() {
		return ownReceivePlaceId;
	}

	public void setOwnReceivePlaceId(String ownReceivePlaceId) {
		this.ownReceivePlaceId = ownReceivePlaceId;
	}

	public String getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(String customerAddressId) {
		this.customerAddressId = customerAddressId;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<GroupProductBean> getGroupProductList() {
		return groupProductList;
	}

	public void setGroupProductList(List<GroupProductBean> groupProductList) {
		this.groupProductList = groupProductList;
	}

}
