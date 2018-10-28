package com.mobile.vo.wx;

import java.math.BigDecimal;
import java.util.List;

/**
 * 移动端购物车信息Bean
 * 
 * @author 金金
 */
public class MobileShoppingCartBean {

	/** 移动端购物车产品信息Bean */
	private List<MobileShoppingCartProductBean> mobileShoppingCartProductList;
	
	public MobileShoppingCartBean(){
		
	}
	
	/** 获取购物车商品数量 */
	public int getNumber() {
		int number = 0;
		if (mobileShoppingCartProductList != null && !mobileShoppingCartProductList.isEmpty()) {
			for (MobileShoppingCartProductBean mobileShoppingCartProductBean : mobileShoppingCartProductList) {
				number += mobileShoppingCartProductBean.getNumber();
			}
		}
		return number;
	}

	/** 获取购物车商品会员总价格 */
	public double getPrice() {
		double price = 0d;
		if (mobileShoppingCartProductList != null && !mobileShoppingCartProductList.isEmpty()) {
			for (MobileShoppingCartProductBean mobileShoppingCartProductBean : mobileShoppingCartProductList) {
				price += (mobileShoppingCartProductBean.getProductPrice() * mobileShoppingCartProductBean.getNumber());
			}
		}
		return (new BigDecimal(price)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 清空购物车
	 */
	public void clear() {
		if(mobileShoppingCartProductList != null && !mobileShoppingCartProductList.isEmpty()){
			mobileShoppingCartProductList.clear();
		}
	}
	
	public List<MobileShoppingCartProductBean> getMobileShoppingCartProductList() {
		return mobileShoppingCartProductList;
	}

	public void setMobileShoppingCartProductList(List<MobileShoppingCartProductBean> mobileShoppingCartProductList) {
		this.mobileShoppingCartProductList = mobileShoppingCartProductList;
	}
}
