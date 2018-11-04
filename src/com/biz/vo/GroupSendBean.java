package com.biz.vo;

import java.util.Date;

/**
 * 套餐配送信息Bean
 * 
 * @author 秀才
 */
public class GroupSendBean {
	
	/** 套餐产品ID */
	private String groupCardDetailId;

	/** 配送开始日期 */
	private Date sendDate;
	
	/** 是否隔周配送 */
	private Integer	blandWeek;
	
	/** 周几配送 */
	private String sendWeek;
	
	/** 备注信息 */
	private String memo;

	public GroupSendBean(){
		
	}

	public String getGroupCardDetailId() {
		return groupCardDetailId;
	}

	public void setGroupCardDetailId(String groupCardDetailId) {
		this.groupCardDetailId = groupCardDetailId;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getBlandWeek() {
		return blandWeek;
	}

	public void setBlandWeek(Integer blandWeek) {
		this.blandWeek = blandWeek;
	}

	public String getSendWeek() {
		return sendWeek;
	}

	public void setSendWeek(String sendWeek) {
		this.sendWeek = sendWeek;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
