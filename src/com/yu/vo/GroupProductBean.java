package com.yu.vo;

/**
 * 套餐产品Bean
 * 
 * @author 金鱼
 */
public class GroupProductBean {
	
	/** 产品编号 */
	private String productSerial;
	
	/** 产品编号 */
	private String spec;

	/** 产品数量 */
	private int number;

	public GroupProductBean() {

	}

	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
