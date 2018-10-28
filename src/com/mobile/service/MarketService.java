package com.mobile.service;

import java.io.UnsupportedEncodingException;

import org.springframework.transaction.annotation.Transactional;

import com.mobile.po.wx.WeixinRefundApplyBean;
import com.mobile.vo.wx.FansBean;
import com.mobile.vo.wx.MobileShoppingCartBean;
import com.yu.vo.CustomerOrderBean;
import com.yu.vo.OrderQueryBean;
import com.yu.vo.ProductQueryBean;
import com.yu.vo.ReturnResultBean;

/**
 * 菜场用户一系列的Service操作类接口
 * 
 * @author 金金
 */
public interface MarketService {

	/**
	 * 分页查询菜场用户产品信息列表
	 * 
	 * @param productQueryBean 产品信息查询Bean
	 */
	public ReturnResultBean queryMarketProduct(ProductQueryBean productQueryBean);
	
	/**
	 * 查询菜场用户产品分类信息列表
	 */
	public ReturnResultBean queryMarketProductType();
	
	/**
	 * 查询菜场用户产品详细信息
	 * @param productSerial 产品信息编号
	 * @param productSpec 产品规格
	 */
	public ReturnResultBean productDetailBySerial(String productSerial, String productSpec);
	
	/**
	 * 加入商品到购物车
	 * 
	 * @param shoppingCartProductBean 购物车产品信息
	 * @param shoppingCartBean 商品购物车Bean
	 */
//	public ReturnResultBean addProduct(ShoppingCartProductBean shoppingCartProductBean, ShoppingCartBean shoppingCartBean);
	
	/**
	 * 修改商品到购物车
	 * 
	 * @param shoppingCartProductBean 购物车产品信息
	 * @param shoppingCartBean 商品购物车Bean
	 */
//	public ReturnResultBean updateProduct(ShoppingCartProductBean shoppingCartProductBean, ShoppingCartBean shoppingCartBean);
	
	/**
	 * 删除购物车中的商品
	 * 
	 * @param productSerial 产品编号
	 * @param productSpec 产品规格
	 * @param productPrice 产品单价
	 */
//	public ReturnResultBean deleteProduct(String productSerial, String productSpec, ShoppingCartBean shoppingCartBean);
	
	/**
	 * 去购物车查看
	 * 
	 * @param shoppingCartBean 购物车Bean
	 * @param fansBean 登录的客户信息Bean
	 */
//	public ReturnResultBean queryShoppingCart(ShoppingCartBean shoppingCartBean, FansBean fansBean);
	
	/**
	 * 查看购物车
	 * 
	 * @param mobileShoppingCartBean 购物车信息Bean
	 * @param fansBean 购物车Bean
	 */
	public ReturnResultBean viewShoppingCart(MobileShoppingCartBean mobileShoppingCartBean, FansBean fansBean);
	
	/**
	 * 增加菜摊用户订单
	 * 
	 * @param loginCustomerBean 当前登录的客户信息Bean
	 * @param mobileShoppingCartBean 购物车Bean
	 * @param customerOrderBean 用户填写的订单信息
	 */
	public ReturnResultBean generateMarketOrder(FansBean fansBean, MobileShoppingCartBean mobileShoppingCartBean, CustomerOrderBean customerOrderBean);
	
	/**
	 * 微信支付订单
	 */
	public ReturnResultBean payOrderByWeixin(FansBean fansBean, String orderId);
	
	/**
	 * 微信同步操作返回信息
	 * 
	 * @param out_trade_no 支付宝对应的商户订单号
	 * @param trade_status 支付宝返回的状态
	 * @throws Exception
	 */
	public ReturnResultBean payWeixinReturn(String out_trade_no, String trade_status) throws UnsupportedEncodingException;
	
	
	/**
	 * 微信退款
	 * 
	 * @param customerBean 登录的商户信息
	 * @param payTradeId 支付编号ID
	 */
	public ReturnResultBean weixinRefund(FansBean fansBean, String payTradeId);
	
	/**
	 * 查询订单信息
	 * @param customerBean 当前登录用户信息Bean
	 * @param orderBean 订单信息Bean
	 */
	public ReturnResultBean queryOrder(FansBean fansBean, OrderQueryBean orderQueryBean);
	
	/**
	 * 查询订单详细
	 * 
	 * @param orderId 订单ID
	 * @param customerId 当前登录用户ID
	 */
	public ReturnResultBean queryOrderDetail(String orderId, String customerId);
	
	/**
	 * 删除未支付的订单
	 * 
	 * @param orderId 订单ID
	 * @param fansBean 当前登录粉丝
	 * @throws Exception 
	 */
	public ReturnResultBean deleteNoPayOrder(String orderId, FansBean fansBean) throws Exception;
	
	public ReturnResultBean refundApply(WeixinRefundApplyBean weixinRefundApplyBean, FansBean fansBean) throws Exception;
	
}
