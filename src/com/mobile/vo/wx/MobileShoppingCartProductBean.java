package com.mobile.vo.wx;

/**
 * 移动端购物车产品Bean
 * 
 * @author 金鱼
 */
public class MobileShoppingCartProductBean {

	/** 产品编号 */
	private String productSerial;

	/** 产品名称 */
	private String productName;
	
	/** 产品规格 */
	private String productSpec;

	/** 产品数量 */
	private int number;

	/** 产品单价 */
	private double productPrice;
	
	/** 小图片 */
	private String smallpic;
	
	/** 是否有效(主要用于服务端验证当前产品是否过期) */
	private boolean isValid;
	
	public MobileShoppingCartProductBean(){
		
	}

	public String getProductSerial() {
		return productSerial;
	}

	public void setProductSerial(String productSerial) {
		this.productSerial = productSerial;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getSmallpic() {
		return smallpic;
	}

	public void setSmallpic(String smallpic) {
		this.smallpic = smallpic;
	}

	public boolean getValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
