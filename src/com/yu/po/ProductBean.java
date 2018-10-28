package com.yu.po;

import java.util.Date;

/**
 * 产品信息Bean
 * 
 * @author 金鱼
 */
public class ProductBean {

	/** 产品ID */
	private String productId;

	/** 名称 */
	private String name;

	/** 别名 */
	private String aliasName;

	/** 客户ID */
	private String customerId;

	/** 商品编号 */
	private String serial;

	/** 所属类别 */
	private String productType;

	/** 品牌 */
	private String brand;

	/** 产品营销类型 */
	private String productSaleType;

	/** 小图片 */
	private String smallpic;

	/** 会员价 */
	private Double price;

	// /** 非会员价格 */
	// private Double commonPrice;

	/** 产品销量 */
	private Double salesVolume;

	/** 上线时间 */
	private Date onlineTime;

	/** 规格 */
	private String spec;

	/** 保质期 */
	private Integer shelfLife;

	/** 大图片 */
	private String bigPic;

	/** 产品介绍描述HTML * */
	private String memoCpjs;

	/** 营养百科描述HTML */
	private String memoYybk;

	/** 搭配推荐描述HTML */
	private String memoDptj;

	/** 产品认证描述HTML */
	private String memoCprz;

	/** 生产基地描述HTML */
	private String memoScjd;

	/** 产品加工描述HTML */
	private String memoCpjg;

	/** 产品配送描述HTML */
	private String memoCpps;

	public ProductBean() {

	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getSmallpic() {
		return smallpic;
	}

	public void setSmallpic(String smallpic) {
		this.smallpic = smallpic;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	// public Double getCommonPrice() {
	// return commonPrice;
	// }

	// public void setCommonPrice(Double commonPrice) {
	// this.commonPrice = commonPrice;
	// }

	public Double getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Double salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(Integer shelfLife) {
		this.shelfLife = shelfLife;
	}

	public String getBigPic() {
		return bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	public String getMemoCpjs() {
		return memoCpjs;
	}

	public void setMemoCpjs(String memoCpjs) {
		this.memoCpjs = memoCpjs;
	}

	public String getMemoYybk() {
		return memoYybk;
	}

	public void setMemoYybk(String memoYybk) {
		this.memoYybk = memoYybk;
	}

	public String getMemoDptj() {
		return memoDptj;
	}

	public void setMemoDptj(String memoDptj) {
		this.memoDptj = memoDptj;
	}

	public String getMemoCprz() {
		return memoCprz;
	}

	public void setMemoCprz(String memoCprz) {
		this.memoCprz = memoCprz;
	}

	public String getMemoScjd() {
		return memoScjd;
	}

	public void setMemoScjd(String memoScjd) {
		this.memoScjd = memoScjd;
	}

	public String getMemoCpjg() {
		return memoCpjg;
	}

	public void setMemoCpjg(String memoCpjg) {
		this.memoCpjg = memoCpjg;
	}

	public String getMemoCpps() {
		return memoCpps;
	}

	public void setMemoCpps(String memoCpps) {
		this.memoCpps = memoCpps;
	}

}
