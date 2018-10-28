package com.yu.po;

/**
 * 数据字典Bean
 * 
 * @author 金鱼
 */
public class DistionaryBean {

	/** 父键 */
	private String vcParentCode;
	
	/** 代码 */
	private String vcCode;
	
	/** 值 */
	private String vcValue;
	
	/** 名称 */
	private String vcName;
	
	/** 显示顺序 */
	private Integer displayOrder;
	
	/** 备注信息 */
	private String memo;
	
	public DistionaryBean(){
		
	}

	public String getVcParentCode() {
		return vcParentCode;
	}

	public void setVcParentCode(String vcParentCode) {
		this.vcParentCode = vcParentCode;
	}

	public String getVcCode() {
		return vcCode;
	}

	public void setVcCode(String vcCode) {
		this.vcCode = vcCode;
	}

	public String getVcValue() {
		return vcValue;
	}

	public void setVcValue(String vcValue) {
		this.vcValue = vcValue;
	}

	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
