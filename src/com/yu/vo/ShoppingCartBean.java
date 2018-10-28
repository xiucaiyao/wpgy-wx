package com.yu.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 购物车Bean
 * 
 * @author 金鱼
 */
public class ShoppingCartBean {

	private static Log log = LogFactory.getLog(ShoppingCartBean.class);

	/** 购物车产品信息Bean */
	private List<ShoppingCartProductBean> shoppingCartProductList;

	/** 获取购物车商品数量 */
	public int getNumber() {
		int number = 0;
		if (shoppingCartProductList != null && !shoppingCartProductList.isEmpty()) {
			for (ShoppingCartProductBean shoppingCartProductBean : shoppingCartProductList) {
				number += shoppingCartProductBean.getNumber();
			}
		}
		return number;
	}

	/** 获取购物车商品会员总价格 */
	public double getPrice() {
		double price = 0d;
		if (shoppingCartProductList != null && !shoppingCartProductList.isEmpty()) {
			for (ShoppingCartProductBean shoppingCartProductBean : shoppingCartProductList) {
				price += (shoppingCartProductBean.getProductPrice() * shoppingCartProductBean.getNumber());
			}
		}
		return (new BigDecimal(price)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/** 获取购物车商品会员总价格 */
//	public double getCommonPrice() {
//		double commonPrice = 0d;
//		if (shoppingCartProductList != null && !shoppingCartProductList.isEmpty()) {
//			for (ShoppingCartProductBean shoppingCartProductBean : shoppingCartProductList) {
//				commonPrice += (shoppingCartProductBean.getCommonPrice() * shoppingCartProductBean.getNumber());
//			}
//		}
//		return (new BigDecimal(commonPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}

	/**
	 * 查找购物车产品根据产品编号、规格
	 * 
	 * @param productSerial 产品编号
	 * @param productSpec 产品规格
	 */
	public ShoppingCartProductBean findProduct(String productSerial, String productSpec) {
		if (StringUtils.isNotBlank(productSerial) && StringUtils.isNotBlank(productSpec) && shoppingCartProductList != null && !shoppingCartProductList.isEmpty()) {
			for (ShoppingCartProductBean shoppingCartProductBean : shoppingCartProductList) {
				if (productSerial.equals(shoppingCartProductBean.getProductSerial()) && productSpec.equals(shoppingCartProductBean.getProductSpec())) {
					return shoppingCartProductBean;
				}
			}
		}
		return null;
	}

	/**
	 * 加入购物车产品，并设置数量
	 * 
	 * @param productSerial 产品编号
	 * @param productName 产品名称
	 * @param productSpec 产品规格
	 * @param number 产品数量
	 * @param productPrice 产品单价
	 */
	public boolean updateProduct(ShoppingCartProductBean shoppingCartProductBean) {
		if (shoppingCartProductBean == null) {
			return false;
		}
		String productSerial = shoppingCartProductBean.getProductSerial();
		String productName = shoppingCartProductBean.getProductName();
		String productSpec = shoppingCartProductBean.getProductSpec();
		Integer number = shoppingCartProductBean.getNumber();
		Double productPrice = shoppingCartProductBean.getProductPrice();
		// Double commonPrice = shoppingCartProductBean.getCommonPrice();
		if (StringUtils.isBlank(productSerial) || StringUtils.isBlank(productSpec) || number == null || number < 0 || productPrice == null || productPrice <= 0d) {
			// || commonPrice == null || commonPrice <= 0d
			return false;
		}
		log.info("修改商品到购物车:productSerial->:" + productSerial + "  productName->" + productName + "  productSpec->" + productSpec + "  price->" + productPrice + "  productNumber->" + number);
		shoppingCartProductBean = findProduct(productSerial, productSpec);
		if (shoppingCartProductBean == null) {
			if (number == 0) {
				return false;
			}
			shoppingCartProductBean = new ShoppingCartProductBean();
			shoppingCartProductBean.setProductSerial(productSerial);
			shoppingCartProductBean.setProductName(productName);
			shoppingCartProductBean.setProductSpec(productSpec);
			shoppingCartProductBean.setNumber(number);
			shoppingCartProductBean.setProductPrice(productPrice);
			// shoppingCartProductBean.setCommonPrice(commonPrice);
			if (shoppingCartProductList == null) {
				shoppingCartProductList = new ArrayList<ShoppingCartProductBean>();
			}
			shoppingCartProductList.add(shoppingCartProductBean);
			return true;
		}
		if (number == 0) {
			shoppingCartProductList.remove(shoppingCartProductBean);
		} else {
			shoppingCartProductBean.setNumber(number);
		}

		return true;
	}

	/**
	 * 默认加入购物车产品，如果没有该产品就设置该产品数量为1，否则该产品数量就加1
	 * 
	 * @param productSerial 产品编号
	 * @param productName 产品名称
	 * @param productSpec 产品规格
	 * @param productPrice 产品单价
	 * @param number 产品数量
	 */
	public boolean addProduct(ShoppingCartProductBean shoppingCartProductBean) {
		if (shoppingCartProductBean == null) {
			return false;
		}
		String productSerial = shoppingCartProductBean.getProductSerial();
		String productName = shoppingCartProductBean.getProductName();
		String productSpec = shoppingCartProductBean.getProductSpec();
		Double productPrice = shoppingCartProductBean.getProductPrice();
		// Double commonPrice = shoppingCartProductBean.getCommonPrice();
		String smallpic = shoppingCartProductBean.getSmallpic();
		Integer number = shoppingCartProductBean.getNumber();
		if (StringUtils.isBlank(productSerial) || StringUtils.isBlank(productSpec) || productPrice == null || productPrice <= 0d) {
			// || commonPrice == null || commonPrice <= 0d
			return false;
		}
		if (number == null) {
			number = 1;
		}
		shoppingCartProductBean = findProduct(productSerial, productSpec);
		if (shoppingCartProductBean == null) {
			shoppingCartProductBean = new ShoppingCartProductBean();
			shoppingCartProductBean.setProductSerial(productSerial);
			shoppingCartProductBean.setProductName(productName);
			shoppingCartProductBean.setProductSpec(productSpec);
			shoppingCartProductBean.setProductPrice(productPrice);
			// shoppingCartProductBean.setCommonPrice(commonPrice);
			shoppingCartProductBean.setNumber(number);
			shoppingCartProductBean.setSmallpic(smallpic);
			if (shoppingCartProductList == null) {
				shoppingCartProductList = new ArrayList<ShoppingCartProductBean>();
			}
			shoppingCartProductList.add(shoppingCartProductBean);
		} else {
			if (StringUtils.isBlank(shoppingCartProductBean.getSmallpic())) {
				shoppingCartProductBean.setSmallpic(smallpic);
			}
			shoppingCartProductBean.setNumber(shoppingCartProductBean.getNumber() + number);
		}
		log.info("加入商品到购物车:productSerial->:" + productSerial + "  productName->" + productName + "  productSpec->" + productSpec + "  price->" + productPrice);
		return true;
	}

	/**
	 * 当用户不想要产品的时候，就把它删除
	 * 
	 * @param productSerial 产品编号
	 * @param productSpec 产品规格
	 * @param productPrice 产品单价
	 */
	public void deleteProduct(String productSerial, String productSpec) {
		if (StringUtils.isNotBlank(productSerial) && StringUtils.isNotBlank(productSpec)) {
			ShoppingCartProductBean shoppingCartProductBean = findProduct(productSerial, productSpec);
			if (shoppingCartProductBean != null) {
				log.info("删除购物车中的商品:productSerial->:" + productSerial + "  productSpec->" + productSpec);
				shoppingCartProductList.remove(shoppingCartProductBean);
			}
		}
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		log.info("清空购物车");
		shoppingCartProductList.clear();
	}

	public List<ShoppingCartProductBean> getShoppingCartProductList() {
		return shoppingCartProductList;
	}

}
