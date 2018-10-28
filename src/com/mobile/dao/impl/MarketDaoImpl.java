package com.mobile.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mobile.dao.MarketDao;
import com.mobile.po.wx.WeixinRefundApplyBean;
import com.yu.dao.impl.BaseDaoImpl;
import com.yu.po.DistionaryBean;
import com.yu.po.ProductBean;
import com.yu.vo.ProductQueryBean;

/**
 * 菜场用户DAO操作实现类
 * 
 * @author 金鱼
 */
@Repository("marketDao")
public class MarketDaoImpl extends BaseDaoImpl implements MarketDao{

	private static Log log = LogFactory.getLog(MarketDaoImpl.class);
	
	/**
	 * 查询菜场用户商品列表信息
	 * @param productQueryBean 产品查询Bean
	 */
	public List<ProductBean> queryMarketProduct(ProductQueryBean productQueryBean){
		log.info("查询菜场用户商品列表信息");
		return getSqlSession().selectList("weixin.queryMarketProduct", productQueryBean);
	}
	
	/**
	 * 查询菜场用户产品分类信息列表
	 */
	public List<DistionaryBean> queryMarketProductType(String priceDate){
		log.info("查询菜场用户产品分类信息列表");
		return getSqlSession().selectList("weixin.queryMarketProductType", priceDate);
	}
	
	/**
	 * 批量查询菜摊客户选择的产品信息
	 * @param mobileShoppingCartProductList 购物车产品列表
	 */
	public List<ProductBean> bathQueryMarketCustomerProduct(Map<String, Object> shoppingCartProductMap){
		log.info("批量查询菜摊客户选择的产品信息");
		return getSqlSession().selectList("weixin.bathQueryMarketCustomerProduct", shoppingCartProductMap);
	}
	
	/** 
	 * 删除还未支付的订单信息
	 * @param orderId 订单ID
	 */
	public int deleteNoPayOrderById(String orderId) {
		log.info("删除还未支付的订单信息");
		return getSqlSession().delete("weixin.deleteNoPayOrderById", orderId);
	}

	/** 
	 * 删除订单详细信息
	 * @param orderId 订单ID
	 */
	public int deleteOrderDetailByOrderId(String orderId) {
		log.info("删除订单详细信息");
		return getSqlSession().delete("weixin.deleteOrderDetailByOrderId", orderId);
	}

	/** 
	 * 删除订单详细信息
	 * @param orderId 订单ID
	 */
	public int deletePayTradeByOrderId(String orderId) {
		log.info("删除订单详细信息");
		return getSqlSession().delete("weixin.deletePayTradeByOrderId", orderId);
	}
	
	public int insertWeixinRefundApply(WeixinRefundApplyBean weixinRefundApplyBean) {
		return getSqlSession().delete("weixin.insertWeixinRefundApply", weixinRefundApplyBean);
	}
}
