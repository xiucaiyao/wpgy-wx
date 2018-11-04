package com.mobile.dao;

import java.util.List;
import java.util.Map;

import com.biz.po.DistionaryBean;
import com.biz.po.ProductBean;
import com.biz.vo.ProductQueryBean;
import com.mobile.po.wx.WeixinRefundApplyBean;

/**
 * 用户DAO接口
 * 
 * @author 秀才
 */
public interface MarketDao {

	/**
	 * 查询用户商品列表信息
	 * @param productQueryBean 产品查询Bean
	 */
	public List<ProductBean> queryMarketProduct(ProductQueryBean productQueryBean);
	
	/**
	 * 查询用户产品分类信息列表
	 */
	public List<DistionaryBean> queryMarketProductType(String priceDate);
	
	/**
	 * 批量查询客户选择的产品信息
	 * @param mobileShoppingCartProductList 购物车产品列表
	 */
	public List<ProductBean> bathQueryMarketCustomerProduct(Map<String, Object> shoppingCartProductMap);
	
	/** 
	 * 删除还未支付的订单信息
	 * @param orderId 订单ID
	 */
	public int deleteNoPayOrderById(String orderId);
	
	/** 
	 * 删除订单详细信息
	 * @param orderId 订单ID
	 */
	public int deleteOrderDetailByOrderId(String orderId);
	
	/** 
	 * 删除订单详细信息
	 * @param orderId 订单ID
	 */
	public int deletePayTradeByOrderId(String orderId);
	
	public int insertWeixinRefundApply(WeixinRefundApplyBean weixinRefundApplyBean);
}
