package com.yu.vo;

import com.yu.utils.SysUtils;


/**
 * <b>文件名：</b>ProductQueryBean.java <br>
 * <b>功能描述:</b>产品查询<br>
 * 
 * @author 金鱼
 */
public class ProductQueryBean extends BaseQueryBean {
	
	/** 商品编号 */
	private String serial;
	
	/** 品牌 */
	private String brand;
	
	/** 产品信息 */
	private String productSpec;
	
	/** 产品类别 */
	private String productType;
	
	/** 价格范围类型-1:特价菜 0:0-5元  1:5-10元 2:10-50元 3:50-100元 4:100-500元 5:500元以上 */
	private Integer priceRangeType;
	
	/** 0:默认排序 1：销量排序 2：最新： 3：价格升序 4：价格降序  */
	private Integer orderType;

	/** 客户ID */
	private String customerId;
	
	/** 价格级别 */
	private String priceGrade;
	
	private String priceDate;
	
	public ProductQueryBean(){
		this.priceDate = SysUtils.getSendDateStr();
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

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPriceRangeType() {
		return priceRangeType;
	}

	public void setPriceRangeType(Integer priceRangeType) {
		this.priceRangeType = priceRangeType;
	}
	
	/** 获得价格开始范围 */
	public Double getPriceStartRange(){
		if(priceRangeType == null){
			return null;
		} else if(priceRangeType == 0){
			return 0d;
		} else if(priceRangeType == 1){
			return 5d;
		} else if(priceRangeType == 2){
			return 10d;
		} else if(priceRangeType == 3){
			return 50d;
		} else if(priceRangeType == 4){
			return 100d;
		} else if(priceRangeType == 5){
			return 500d;
		}
		return null;
	}
	
	/** 获得价格结束范围 */
	public Double getPriceEndRange(){
		if(priceRangeType == null){
			return null;
		} else if(priceRangeType == 0){
			return 5d;
		} else if(priceRangeType == 1){
			return 10d;
		} else if(priceRangeType == 2){
			return 50d;
		} else if(priceRangeType == 3){
			return 100d;
		} else if(priceRangeType == 4){
			return 500d;
		}
		return null;
	}
	
	/** 如果价格类型是-1就代表是特价菜查询 */
	public String getProductSaleType(){
		if(priceRangeType != null && priceRangeType == -1){
			return "特价商品";
		}
		return null;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPriceGrade() {
		return priceGrade;
	}

	public void setPriceGrade(String priceGrade) {
		this.priceGrade = priceGrade;
	}

	public String getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}
	
}
