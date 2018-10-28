package com.yu.po;

/**
 * 自提点信息Bean
 * 
 * @author 金鱼
 */
public class OwnReceivePlaceBean {

	/** 主键 */
	private String ownReceivePlaceId;

	/** 店名 */
	private String name;

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

	/** 线路 */
	private String line;
	
	/** 线路 */
	private Integer lineOrder;

	/** 备注信息 */
	private String memo;

	public OwnReceivePlaceBean() {

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
