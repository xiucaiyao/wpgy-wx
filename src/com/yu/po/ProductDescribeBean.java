package com.yu.po;

import java.util.Date;

/**
 * 产品评论信息Bean
 * 
 * @author 金鱼
 */
public class ProductDescribeBean {

	/** 主键 */
	private String productDescribeId;

	/** 商品编号 */
	private String serial;

	/** 评论商品类型 */
	private String productType;

	/** 客户 */
	private String customerId;

	/** 评论日期 */
	private Date describeDate;

	/** 评论内容 */
	private String content;

	/** 是否审核 */
	private Boolean isAudit;

	/** 回复日期 */
	private Date replyDate;

	/** 评论回复 */
	private String replyContent;
	
	/** 系统登录姓名 */
	private String customerLoginName;

	public ProductDescribeBean() {

	}
	
	public ProductDescribeBean(String customerId){
		this.customerId = customerId;
	}
	
	public ProductDescribeBean(String serial, Boolean isAudit){
		this.serial = serial;
		this.isAudit = isAudit;
	}

	public String getProductDescribeId() {
		return productDescribeId;
	}

	public void setProductDescribeId(String productDescribeId) {
		this.productDescribeId = productDescribeId;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getDescribeDate() {
		return describeDate;
	}

	public void setDescribeDate(Date describeDate) {
		this.describeDate = describeDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Boolean isAudit) {
		this.isAudit = isAudit;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getCustomerLoginName() {
		return customerLoginName;
	}

	public void setCustomerLoginName(String customerLoginName) {
		this.customerLoginName = customerLoginName;
	}

}
