package com.biz.vo;

import java.math.BigDecimal;

/**
 * 购物车产品Bean
 * 
 * @author 秀才
 */
public class ShoppingCartProductBean {

	/** 产品编号 */
	private String productSerial;

	/** 产品名称 */
	private String productName;
	
	/** 产品规格 */
	private String productSpec;

	/** 产品数量 */
	private Integer number;

	/** 产品单价 */
	private Double productPrice;
	
//	/** 非会员价格 */
//	private Double commonPrice;

	/** 小图片 */
	private String smallpic;
	
	/** 品牌 */
	private String brand;
	
	/** 产品营销类型 */
	private String productSaleType;

	public ShoppingCartProductBean() {

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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

//	public Double getCommonPrice() {
//		return commonPrice;
//	}

//	public void setCommonPrice(Double commonPrice) {
//		this.commonPrice = commonPrice;
//	}

	//获取会员商品小计
	public Double getPriceSubtotal(){
		double priceSubtotal = 0d;
		if(number != null && number > 0 && productPrice != null && productPrice > 0d){
			priceSubtotal = number * productPrice;
		}
		return (new BigDecimal(priceSubtotal)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	//获取会员商品小计
//	public Double getCommonPriceSubtotal(){
//		double priceSubtotal = 0d;
//		if(number != null && number > 0 && commonPrice != null && commonPrice > 0d){
//			priceSubtotal = number * commonPrice;
//		}
//		return (new BigDecimal(priceSubtotal)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//	}
	
	public String getSmallpic() {
		return smallpic;
	}

	public void setSmallpic(String smallpic) {
		this.smallpic = smallpic;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductSaleType() {
		return productSaleType;
	}

	public void setProductSaleType(String productSaleType) {
		this.productSaleType = productSaleType;
	}
}
