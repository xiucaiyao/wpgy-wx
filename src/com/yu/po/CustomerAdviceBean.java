package com.yu.po;

import java.util.Date;

/**
 * 会员建议Bean
 * 
 * @author 金鱼
 */
public class CustomerAdviceBean {

	/** 主键 */
	private String customerAdviceId;

	/** 客户 */
	private String customerId;
	
	/** 类型 */
	private String adviceType;

	/** 涉及部门 */
	private String adviceDept;

	/** 内容 */
	private String content;

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

	public CustomerAdviceBean() {

	}

	public String getCustomerAdviceId() {
		return customerAdviceId;
	}

	public void setCustomerAdviceId(String customerAdviceId) {
		this.customerAdviceId = customerAdviceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getAdviceType() {
		return adviceType;
	}

	public void setAdviceType(String adviceType) {
		this.adviceType = adviceType;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAdviceDept() {
		return adviceDept;
	}

	public void setAdviceDept(String adviceDept) {
		this.adviceDept = adviceDept;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
