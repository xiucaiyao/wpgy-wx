package com.mobile.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.dao.CustomerDao;
import com.biz.dao.OrderDao;
import com.biz.dao.ProductDao;
import com.biz.po.CustomerAddressBean;
import com.biz.po.CustomerBean;
import com.biz.po.OrderBean;
import com.biz.po.OrderDetailBean;
import com.biz.po.PayTradeBean;
import com.biz.po.ProductBean;
import com.biz.utils.SysUtils;
import com.biz.vo.CustomerOrderBean;
import com.biz.vo.OrderQueryBean;
import com.biz.vo.ProductQueryBean;
import com.biz.vo.ReturnResultBean;
import com.mobile.constants.SysMobileConstants;
import com.mobile.constants.WeixinConstants;
import com.mobile.dao.MarketDao;
import com.mobile.po.wx.WeixinRefundApplyBean;
import com.mobile.service.MarketService;
import com.mobile.vo.wx.FansBean;
import com.mobile.vo.wx.MobileShoppingCartBean;
import com.mobile.vo.wx.MobileShoppingCartProductBean;
import com.mobile.vo.wx.WechatPayDto;
import com.wechat.utils.ClientCustomSSL;

/**
 * 用户一系列的Service操作类
 * 
 * @author 秀才
 */
@Service("marketServiceImpl")
public class MarketServiceImpl implements MarketService{
	
	private static Log log = LogFactory.getLog(MarketServiceImpl.class);
	
	@Resource
	private MarketDao marketDao;
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private ProductDao productDao;
	
	@Resource
	private CustomerDao customerDao;
	
	/**
	 * 分页查询用户产品信息列表
	 * 
	 * @param productQueryBean 产品信息查询Bean
	 */
	public ReturnResultBean queryMarketProduct(ProductQueryBean productQueryBean){
		log.info("分页查询用户产品信息列表");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if(productQueryBean == null){
			productQueryBean = new ProductQueryBean();
		}
		productQueryBean.setPageItems(SysMobileConstants.PAGE_ITEMS);
		String searchKeywords = productQueryBean.getSearchKeywords();
		if (StringUtils.isNotBlank(searchKeywords)) {
			productQueryBean.setSearchKeywords("%" + searchKeywords + "%");
		}
		returnResultBean.addReturnData("productList", marketDao.queryMarketProduct(productQueryBean));
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	/**
	 * 查询用户产品分类信息列表
	 */
	public ReturnResultBean queryMarketProductType(){
		log.info("查询用户产品分类信息列表");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		returnResultBean.addReturnData("productTypeList", marketDao.queryMarketProductType(SysUtils.getSendDateStr()));
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	/**
	 * 查询用户产品详细信息
	 * @param productSerial 产品信息编号
	 * @param productSpec 产品规格
	 */
	public ReturnResultBean productDetailBySerial(String productSerial, String productSpec){
		log.info("查询用户产品详细信息");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if (StringUtils.isBlank(productSerial)) {
			returnResultBean.setMessage("非法操作！");
			return returnResultBean;
		}
		ProductBean productBean = productDao.productDetailBySerial(productSerial);
		if (productBean == null) {
			returnResultBean.setMessage("很抱歉，该产品信息已经下架！");
			return returnResultBean;
		}
		ProductQueryBean productQueryBean = new ProductQueryBean();
		productQueryBean.setSerial(productSerial);
		List<ProductBean> productList = marketDao.queryMarketProduct(productQueryBean);
		if(productList == null || productList.isEmpty()){
			returnResultBean.setMessage("很抱歉，该产品信息已经下架！");
			return returnResultBean;
		}
		//查找该规格下的商品价格信息
		if(productList.size() == 1 || StringUtils.isBlank(productSpec)){
			productBean.setPrice(productList.get(0).getPrice());
			productBean.setSpec(productList.get(0).getSpec());
		} else {
			for(ProductBean dbProductBean : productList){
				if(productSpec.equals(dbProductBean.getSpec())){
					productBean.setPrice(productList.get(0).getPrice());
					productBean.setSpec(productList.get(0).getSpec());
					break;
				}
			}
		}
		returnResultBean.operationSuccess();
		returnResultBean.addReturnData("productBean", productBean);
		return returnResultBean;
	}
	
	/**
	 * 加入商品到购物车
	 * 
	 * @param shoppingCartProductBean 购物车产品信息
	 * @param shoppingCartBean 商品购物车Bean
	 */
//	public ReturnResultBean addProduct(ShoppingCartProductBean shoppingCartProductBean, ShoppingCartBean shoppingCartBean) {
//		log.info("加入商品到购物车");
//		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
//		if (shoppingCartBean == null) {
//			returnResultBean.setMessage("系统出错！");
//			return returnResultBean;
//		}
//		if (!shoppingCartBean.addProduct(shoppingCartProductBean)) {
//			returnResultBean.setMessage("购物车加入商品失败...");
//			return returnResultBean;
//		}
//		returnResultBean.addReturnData("shoppingCartProductList", shoppingCartBean.getShoppingCartProductList());
//		returnResultBean.operationSuccess();
//		return returnResultBean;
//	}
	
	/**
	 * 修改商品到购物车
	 * 
	 * @param shoppingCartProductBean 购物车产品信息
	 * @param shoppingCartBean 商品购物车Bean
	 */
//	public ReturnResultBean updateProduct(ShoppingCartProductBean shoppingCartProductBean, ShoppingCartBean shoppingCartBean) {
//		log.info("修改商品到购物车");
//		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
//		if (shoppingCartBean == null) {
//			returnResultBean.setMessage("系统出错！");
//			return returnResultBean;
//		}
//		if (!shoppingCartBean.updateProduct(shoppingCartProductBean)) {
//			returnResultBean.setMessage("购物车修改商品失败...");
//			return returnResultBean;
//		}
//		returnResultBean.addReturnData("shoppingCartProductList", shoppingCartBean.getShoppingCartProductList());
//		returnResultBean.operationSuccess();
//		return returnResultBean;
//	}

	/**
	 * 删除购物车中的商品
	 * 
	 * @param productSerial 产品编号
	 * @param productSpec 产品规格
	 * @param productPrice 产品单价
	 */
//	public ReturnResultBean deleteProduct(String productSerial, String productSpec, ShoppingCartBean shoppingCartBean) {
//		log.info("删除购物车中的商品");
//		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
//		if (shoppingCartBean == null) {
//			returnResultBean.setMessage("系统出错！");
//			return returnResultBean;
//		}
//		shoppingCartBean.deleteProduct(productSerial, productSpec);
//		returnResultBean.addReturnData("shoppingCartProductList", shoppingCartBean.getShoppingCartProductList());
//		returnResultBean.operationSuccess();
//		return returnResultBean;
//	}

	
	/**
	 * 查看购物车
	 * 
	 * @param mobileShoppingCartBean 购物车信息Bean
	 * @param fansBean 购物车Bean
	 */
	public ReturnResultBean viewShoppingCart(MobileShoppingCartBean mobileShoppingCartBean, FansBean fansBean) {
		log.info("查看购物车");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
//		Date receiveDate = SysUtils.getReceiveDate();
//		if(receiveDate == null){
//			returnResultBean.setMessage("亲，10:30-17:00这个时间内是不能下单的哦！");
//			return returnResultBean;
//		}
		if(mobileShoppingCartBean == null){
			returnResultBean.setMessage("您的购物车还什么都没有！");
			return returnResultBean;
		}
		List<MobileShoppingCartProductBean> mobileShoppingCartProductList = mobileShoppingCartBean.getMobileShoppingCartProductList();
		if(mobileShoppingCartProductList == null || mobileShoppingCartProductList.isEmpty()){
			returnResultBean.setMessage("您的购物车里什么产品都没有！");
			return returnResultBean;
		}
		Map<String, Object> shoppingCartProductMap = new HashMap<String, Object>();
		shoppingCartProductMap.put("priceDate", SysUtils.getSendDateStr());
		shoppingCartProductMap.put("productList", mobileShoppingCartProductList);
		List<ProductBean> productList = marketDao.bathQueryMarketCustomerProduct(shoppingCartProductMap);
		if(productList == null || productList.isEmpty()){
			returnResultBean.setMessage("您的购物车里的产品已经失效！");
			return returnResultBean;
		}
		CustomerAddressBean queryCustomerAddressBean = new CustomerAddressBean(null, fansBean.getCustomerId());
		queryCustomerAddressBean.setIsDefaultAddress(true);
		List<CustomerAddressBean> customerAddressList = customerDao.queryCustomerAddress(queryCustomerAddressBean);
		if (customerAddressList == null || customerAddressList.isEmpty()) {
			returnResultBean.setMessage("您还没有地址信息，请联系客服处理！");
			return returnResultBean;
		}
		CustomerAddressBean dbCustomerAddressBean = customerAddressList.get(0);
		returnResultBean.addReturnData("receiveMan", dbCustomerAddressBean.getLinkman());
		returnResultBean.addReturnData("phone", dbCustomerAddressBean.getLinkmanTel());
		String address = "";
		if(StringUtils.isNotBlank(dbCustomerAddressBean.getProvince())){
			address += (dbCustomerAddressBean.getProvince()+" ");
		}
		if(StringUtils.isNotBlank(dbCustomerAddressBean.getCity())){
			address += (dbCustomerAddressBean.getCity()+" ");
		}
		if(StringUtils.isNotBlank(dbCustomerAddressBean.getCounty())){
			address += (dbCustomerAddressBean.getCounty()+" ");
		}
		if(StringUtils.isNotBlank(dbCustomerAddressBean.getTown())){
			address += (dbCustomerAddressBean.getTown()+" ");
		}
		if(StringUtils.isNotBlank(dbCustomerAddressBean.getDetailAddress())){
			address += (dbCustomerAddressBean.getDetailAddress()+" ");
		}
		returnResultBean.addReturnData("address", address);
		boolean isValid = true;
		for(MobileShoppingCartProductBean mobileShoppingCartProductBean : mobileShoppingCartProductList){
			if(mobileShoppingCartProductBean == null || mobileShoppingCartProductBean.getNumber() <= 0 || mobileShoppingCartProductBean.getProductPrice() <= 0){
				returnResultBean.setMessage("非法操作！");
				return returnResultBean;
			}
			mobileShoppingCartProductBean.setValid(false);
			for(ProductBean productBean : productList){
				if(productBean.getSerial().equals(mobileShoppingCartProductBean.getProductSerial()) && productBean.getSpec().equals(mobileShoppingCartProductBean.getProductSpec())){
					if(productBean.getPrice() == mobileShoppingCartProductBean.getProductPrice()){
						mobileShoppingCartProductBean.setValid(true);
					}
					break;
				}
			}
			if(!mobileShoppingCartProductBean.getValid() && isValid){
				isValid = false;
			}
		}
		returnResultBean.addReturnData("mobileShoppingCartBean", mobileShoppingCartBean);
		if(isValid && productList.size() == mobileShoppingCartProductList.size()){
			returnResultBean.operationSuccess();
		} else {
			returnResultBean.setMessage("部分产品已经失效，请重新选择！");
		}
		return returnResultBean;
	}
	
	/**
	 * 增加用户订单
	 * 
	 * @param loginCustomerBean 当前登录的客户信息Bean
	 * @param mobileShoppingCartBean 购物车Bean
	 * @param customerOrderBean 用户填写的订单信息
	 */
	@Transactional(rollbackFor = { Exception.class })
	public ReturnResultBean generateMarketOrder(FansBean fansBean, MobileShoppingCartBean mobileShoppingCartBean, CustomerOrderBean customerOrderBean) {
		log.info("增加用户订单");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		Date sendDate = SysUtils.getSendDate();
		if(sendDate == null){
			returnResultBean.setMessage("亲，当前时间内是不能下单的哦！");
			return returnResultBean;
		}
		if (fansBean == null) {
			returnResultBean.setMessage("登录超时，请重新登录！");
			return returnResultBean;
		}
		if (!"3".equals(fansBean.getCustomerType())) {
			returnResultBean.setMessage("很抱歉，您还不是我们的客户，不能通过在此窗口选择！");
			return returnResultBean;
		}
		if(customerOrderBean == null){
			returnResultBean.setMessage("您还没有填写订单信息！");
			return returnResultBean;
		}
		customerOrderBean.setSendDate(sendDate);
//		if (customerOrderBean.getSendDate() == null) {
//			returnResultBean.setMessage("您还没有填写订单配货日期信息！");
//			return returnResultBean;
//		}
//		String message = SysUtils.validateSendDate(customerOrderBean.getSendDate());
//		if (message != null) {
//			returnResultBean.setMessage(message);
//			return returnResultBean;
//		}
		if(mobileShoppingCartBean == null){
			returnResultBean.setMessage("您的购物车还什么都没有！");
		}
		List<MobileShoppingCartProductBean> mobileShoppingCartProductList = mobileShoppingCartBean.getMobileShoppingCartProductList();
		if(mobileShoppingCartProductList == null || mobileShoppingCartProductList.isEmpty()){
			returnResultBean.setMessage("您的购物车里什么产品都没有！");
			return returnResultBean;
		}
		Map<String, Object> shoppingCartProductMap = new HashMap<String, Object>();
		shoppingCartProductMap.put("priceDate", SysUtils.getSendDateStr());
		shoppingCartProductMap.put("productList", mobileShoppingCartProductList);
		List<ProductBean> productList = marketDao.bathQueryMarketCustomerProduct(shoppingCartProductMap);
		if(productList == null || productList.isEmpty()){
			returnResultBean.setMessage("您的购物车里的产品已经失效！");
			return returnResultBean;
		}
		boolean isValid = true;
		for(MobileShoppingCartProductBean mobileShoppingCartProductBean : mobileShoppingCartProductList){
			if(mobileShoppingCartProductBean == null || mobileShoppingCartProductBean.getNumber() <= 0 || mobileShoppingCartProductBean.getProductPrice() <= 0){
				returnResultBean.setMessage("非法操作！");
				return returnResultBean;
			}
			mobileShoppingCartProductBean.setValid(false);
			for(ProductBean productBean : productList){
				if(productBean.getSerial().equals(mobileShoppingCartProductBean.getProductSerial()) && productBean.getSpec().equals(mobileShoppingCartProductBean.getProductSpec())){
					if(productBean.getPrice() == mobileShoppingCartProductBean.getProductPrice()){
						mobileShoppingCartProductBean.setValid(true);
					}
					break;
				}
			}
			if(!mobileShoppingCartProductBean.getValid() && isValid){
				isValid = false;
			}
		}
		if(!isValid || productList.size() != mobileShoppingCartProductList.size()){
			returnResultBean.setMessage("部分产品已经失效，请重新选择！");
			return returnResultBean;
		}
		CustomerAddressBean queryCustomerAddressBean = new CustomerAddressBean(null, fansBean.getCustomerId());
		queryCustomerAddressBean.setIsDefaultAddress(true);
		List<CustomerAddressBean> customerAddressList = customerDao.queryCustomerAddress(queryCustomerAddressBean);
		if (customerAddressList == null || customerAddressList.isEmpty()) {
			returnResultBean.setMessage("您还没有地址信息，请联系客服处理！");
			return returnResultBean;
		}
		CustomerAddressBean dbCustomerAddressBean = customerAddressList.get(0);
		OrderBean orderBean = new OrderBean();
		String orderId = orderDao.getTableLongId("TB_ORDER");
		orderBean.setOrderId(orderId);
		orderBean.setOrderNo(orderId);
		orderBean.setOrderDate(new Date());
		orderBean.setCustomerId(fansBean.getCustomerId());
		orderBean.setSendDate(customerOrderBean.getSendDate());
		orderBean.setOrderState("未支付");
		orderBean.setProvince(dbCustomerAddressBean.getProvince());
		orderBean.setCity(dbCustomerAddressBean.getCity());
		orderBean.setCounty(dbCustomerAddressBean.getCounty());
		orderBean.setTown(dbCustomerAddressBean.getTown());
		orderBean.setAddress(dbCustomerAddressBean.getDetailAddress());
		orderBean.setOwnReceivePlaceId(null);
		orderBean.setName(null);
		orderBean.setLine(dbCustomerAddressBean.getLina());
		orderBean.setReceiveMan(customerOrderBean.getReceiveMan());
		orderBean.setPhone(customerOrderBean.getPhone());
//		orderBean.setReceiveMan(dbCustomerAddressBean.getLinkman());
//		orderBean.setPhone(dbCustomerAddressBean.getLinkmanTel());
		orderBean.setCustomerType("大客户");
		orderBean.setGroupId(null);
		orderBean.setGroupNumber(null);
		orderBean.setOrderPackingType(null);
		orderBean.setOrderPackingPrice(null);
		orderBean.setOrderType("非套餐");
		orderBean.setSendMoney(null);
		orderBean.setOrderSource("微信商城");
		orderBean.setMemo(customerOrderBean.getMemo());
		orderBean.setCreateUser("system");
		orderBean.setCreateTime(new Date());
		double orderMoney = 0d;
		for (MobileShoppingCartProductBean mobileShoppingCartProductBean : mobileShoppingCartProductList) {
			OrderDetailBean orderDetailBean = new OrderDetailBean();
			orderDetailBean.setOrderDetailId(UUID.randomUUID().toString());
			orderDetailBean.setOrderId(orderId);
			orderDetailBean.setSerial(mobileShoppingCartProductBean.getProductSerial());
			orderDetailBean.setSpec(mobileShoppingCartProductBean.getProductSpec());
			orderDetailBean.setPrice(mobileShoppingCartProductBean.getProductPrice());
			orderDetailBean.setOrderNum((double)mobileShoppingCartProductBean.getNumber());
			orderMoney += (mobileShoppingCartProductBean.getNumber() * mobileShoppingCartProductBean.getProductPrice());
			orderDetailBean.setIsGroupProduct(false);
			orderDetailBean.setProductName(mobileShoppingCartProductBean.getProductName());
			orderDao.insertOrderDetail(orderDetailBean);
		}
		orderBean.setOrderMoney(orderMoney);
		orderDao.insertOrder(orderBean);
		returnResultBean.addReturnData("orderBean", orderBean);
		returnResultBean.setMessage("尊敬的VIP客户，您网上选餐成功，订单号：" + orderBean.getOrderId() + "，请等待我们的处理...");
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	/**
	 * 微信支付订单
	 */
	@Transactional(rollbackFor = { Exception.class })
	public ReturnResultBean payOrderByWeixin(FansBean fansBean, String orderId) {
		log.info("支付宝支付订单...");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		/**** 验证订单信息的存在 ****/
		if (StringUtils.isBlank(orderId)) {
			returnResultBean.setMessage("没有该订单信息，不能支付！");
			return returnResultBean;
		}
		List<OrderBean> orderList = orderDao.queryOrder(new OrderBean(orderId, fansBean.getCustomerId()));
		if (orderList == null || orderList.isEmpty()) {
			returnResultBean.setMessage("没有该订单信息，不能支付！");
			return returnResultBean;
		}
		OrderBean orderBean = orderList.get(0);
		/** 该订单已经支付 **/
		if (!"未支付".equals(orderBean.getOrderState())) {
			returnResultBean.setMessage("该订单已经支付过了！");
			return returnResultBean;
		}
		/** 验证订单日期信息 **/
		String message = SysUtils.validateSendDate(orderBean.getSendDate());
		if (message != null) {
			returnResultBean.setMessage("该订单已经过期了!");
			return returnResultBean;
		}
		CustomerBean customerBean = customerDao.queryCustomerById(fansBean.getCustomerId());
		List<PayTradeBean> payTradeList = orderDao.queryPayTradeByOrderId(orderId);
		PayTradeBean payTradeBean = null;
		if(payTradeList != null && !payTradeList.isEmpty()){
			payTradeBean = payTradeList.get(0);
		} else {
			payTradeBean = new PayTradeBean(SysUtils.generateOutTradeNo());
			payTradeBean.setOutTradeNo(orderBean.getOrderId());
			payTradeBean.setLoginName(customerBean.getLoginName());
			payTradeBean.setCardNo(customerBean.getOrderCard());
			payTradeBean.setBeginPayTime(new Date());
			payTradeBean.setPayMoney(orderBean.getOrderMoney());
			payTradeBean.setPayStatus(1);
			payTradeBean.setCustomerName(customerBean.getName());
			payTradeBean.setMemo("微信订单支付信息，订单编号：" + orderBean.getOrderId());
			payTradeBean.setCreateTime(new Date());
			orderDao.insertPayTrade(payTradeBean);
		}
		returnResultBean.addReturnData("outTradeNo", payTradeBean.getPayTradeId());
		returnResultBean.addReturnData("orderName", "" + customerBean.getName() + "的订单");
		returnResultBean.addReturnData("orderMoney", String.valueOf(orderBean.getOrderMoney()));
		returnResultBean.addReturnData("orderDescribe", "欢迎到天蓝地绿农庄购买商品，你的订单号：" + orderBean.getOrderId());
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	/**
	 * 微信同步操作返回信息
	 * 
	 * @param out_trade_no 支付宝对应的商户订单号
	 * @param trade_status 支付宝返回的状态
	 * @throws Exception
	 */
	public ReturnResultBean payWeixinReturn(String out_trade_no, String trade_status) throws UnsupportedEncodingException {
		log.info("支付宝支付商品同步操作");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		PayTradeBean payTradeBean = orderDao.queryPayTradeByOutTradeNo(out_trade_no);
		if (payTradeBean == null) {
			returnResultBean.setMessage("未查到该订单信息，请联系客服！");
			return returnResultBean;
		}
		if (!trade_status.equals("TRADE_FINISHED") && !trade_status.equals("TRADE_SUCCESS")) {
			returnResultBean.setMessage("支付信息出错，请联系客服人！");
			return returnResultBean;
		}
		List<OrderBean> orderList = orderDao.queryOrder(new OrderBean(payTradeBean.getOutTradeNo(), null));
		if (orderList == null || orderList.isEmpty()) {
			returnResultBean.setMessage("支付宝支付的订单信息未找到，请联系客服人员！");
			return returnResultBean;
		}
		OrderBean orderBean = orderList.get(0);
		if (!"初始".equals(orderBean.getOrderState())) {
			returnResultBean.setMessage("微信支付金额到账可能还需要一些时间，请稍后，如果需处理请联系客服！");
			return returnResultBean;
		}
		
		returnResultBean.addReturnData("orderBean", orderBean);
		// returnResultBean.setMessage("您此次购买商品成功，订单编号为："+orderBean.getOrderId());
		returnResultBean.operationSuccess();
		return returnResultBean;
	}

	
	
	/**
	 * 微信退款
	 * 
	 * @param customerBean 登录的商户信息
	 * @param payTradeId 支付编号ID
	 */
	@Transactional(rollbackFor = { Exception.class })
	public ReturnResultBean weixinRefund(FansBean fansBean, String payTradeId){
		log.info("微信退款");
		exeWechatRefund(null);
		
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if (StringUtils.isBlank(payTradeId)) {
			returnResultBean.setMessage("支付订单号不能为空！");
			return returnResultBean;
		}
		PayTradeBean payTradeBean = orderDao.queryPayTradeByOutTradeNo(payTradeId);
		if (payTradeBean == null) {
			returnResultBean.setMessage("微信支付的订单信息未找到，请联系客服人员！");
			return returnResultBean;
		}
		if(payTradeBean.getPayStatus() == null || payTradeBean.getPayStatus() != 2){
			returnResultBean.setMessage("该订单还未付款！");
			return returnResultBean;
		}
		List<OrderBean> orderList = orderDao.queryOrder(new OrderBean(payTradeBean.getOutTradeNo(), null));
		if (orderList == null || orderList.isEmpty()) {
			returnResultBean.setMessage("微信支付的订单信息未找到，请联系客服人员！");
			return returnResultBean;
		}
		OrderBean dbOrderBean = orderList.get(0);
		//TODO:eliya 需要在OrderBean里面添加一个退款单号字段
		
		// 删除现在的订单产品信息
		orderDao.deleteOrder(dbOrderBean.getOrderId());
		orderDao.deleteOrderDetail(dbOrderBean.getOrderId());
		payTradeBean.setOutTradeNo(null);
		payTradeBean.setPayStatus(5);
		orderDao.updatePayTradeStatus(payTradeBean);
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	
	
	public void exeWechatRefund(OrderBean dbOrderBean) {
		String out_refund_no =  WechatPayDto.getNonce_str();// 退款单号
		String out_trade_no = "wxb5cdd9028d9a5b131442973970";//WechatPayDto.getNonce_str();//dbOrderBean.getOrderNo();// 订单号
		String total_fee = "1";//dbOrderBean.getOrderMoney()*(double)100+"";// 总金额
		String refund_fee = "1";//dbOrderBean.getOrderMoney()*(double)100+""; 退款金额
		String nonce_str = WechatPayDto.getNonce_str();// 随机字符串
		String appid = WeixinConstants.WEIXIN_APP_ID;
		String appsecret = WeixinConstants.WEIXIN_APP_SECRET;
		String mch_id = WeixinConstants.WEIXIN_MCH_ID;
		String op_user_id = WeixinConstants.WEIXIN_MCH_ID;//就是MCHID
		String partnerkey = WeixinConstants.WEIXIN_API_KEY;//商户平台上的那个KEY
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", total_fee);
		packageParams.put("refund_fee", refund_fee);
		packageParams.put("op_user_id", op_user_id);

		com.wechat.utils.RequestHandler reqHandler = new com.wechat.utils.RequestHandler(
				null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "<out_refund_no>" + out_refund_no + "</out_refund_no>"
				+ "<total_fee>" + total_fee + "</total_fee>"
				+ "<refund_fee>" + refund_fee + "</refund_fee>"
				+ "<op_user_id>" + op_user_id + "</op_user_id>" + "</xml>";
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
			String s= ClientCustomSSL.doRefund(createOrderURL, xml);
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询订单信息
	 * @param customerBean 当前登录用户信息Bean
	 * @param orderBean 订单信息Bean
	 */
	public ReturnResultBean queryOrder(FansBean fansBean, OrderQueryBean orderQueryBean){
		log.info("查询订单信息");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if(orderQueryBean == null){
			orderQueryBean = new OrderQueryBean();
		}
//		已支付、未支付、收货三个页面
//	    已支付显示初始、汇总、送货状态的订单；
//	    未支付显示未支付的订单；
//	    收货显示收货和审核状态的订单；
		String orderState = orderQueryBean.getOrderState();
		orderQueryBean.setOrderState(null);
		if("已支付".equals(orderState)){
			orderQueryBean.setOrderState2("1");
		} else if("未支付".equals(orderState)){
			orderQueryBean.setOrderState2("2");
		} else if("收货".equals(orderState)){
			orderQueryBean.setOrderState2("3");
		} else {
			orderQueryBean.setOrderState2(null);
		}
		orderQueryBean.setCustomerId(fansBean.getCustomerId());
		orderQueryBean.setPageItems(SysMobileConstants.PAGE_ITEMS);
		List<OrderBean> orderList = orderDao.queryOrderForPage(orderQueryBean);
		if (orderList != null && !orderList.isEmpty()) {
			//超过1个小时未支付就算过期
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR_OF_DAY, -1);
			Date date = calendar.getTime();
			for (OrderBean dbOrderBean : orderList) {
				if ("未支付".equals(dbOrderBean.getOrderState()) && (dbOrderBean.getOrderDate() == null || dbOrderBean.getOrderDate().before(date))) {
					dbOrderBean.setOrderState("已过期");
				}
			}
		}
		returnResultBean.addReturnData("orderList", orderList);
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	/**
	 * 查询订单详细
	 * 
	 * @param orderId 订单ID
	 * @param customerId 当前登录用户ID
	 */
	public ReturnResultBean queryOrderDetail(String orderId, String customerId) {
		log.info("查询订单详细");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if (StringUtils.isBlank(orderId)) {
			returnResultBean.setMessage("没有该订单信息，请刷新页面重新查询!");
			return returnResultBean;
		}
		OrderDetailBean orderDetailBean = new OrderDetailBean();
		orderDetailBean.setOrderId(orderId);
		List<OrderBean> orderList = orderDao.queryOrder(new OrderBean(orderId, customerId));
		if (orderList == null || orderList.isEmpty()) {
			returnResultBean.setMessage("没有该订单信息，请刷新页面重新查询!");
			return returnResultBean;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		Date date = calendar.getTime();
		OrderBean dbOrderBean = orderList.get(0);
		if ("未支付".equals(dbOrderBean.getOrderState()) && (dbOrderBean.getOrderDate() == null || dbOrderBean.getOrderDate().before(date))) {
			dbOrderBean.setOrderState("已过期");
		}
		returnResultBean.addReturnData("orderBean", orderList.get(0));
		returnResultBean.addReturnData("orderDetailList", orderDao.queryOrderDetail(orderDetailBean));
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	/**
	 * 删除未支付的订单
	 * 
	 * @param orderId 订单ID
	 * @param fansBean 当前登录粉丝
	 * @throws Exception 
	 */
	@Transactional(rollbackFor = { Exception.class })
	public ReturnResultBean deleteNoPayOrder(String orderId, FansBean fansBean) throws Exception {
		log.info("查询订单详细");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if (StringUtils.isBlank(orderId)) {
			returnResultBean.setMessage("没有该订单信息，请刷新页面重新查询!");
			return returnResultBean;
		}
		OrderDetailBean orderDetailBean = new OrderDetailBean();
		orderDetailBean.setOrderId(orderId);
		List<OrderBean> orderList = orderDao.queryOrder(new OrderBean(orderId, fansBean.getCustomerId()));
		if (orderList == null || orderList.isEmpty()) {
			returnResultBean.setMessage("没有该订单信息，请刷新页面重新查询!");
			return returnResultBean;
		}
		OrderBean orderBean = orderList.get(0);
		if(!"未支付".equals(orderBean.getOrderState())){
			returnResultBean.setMessage("订单信息，请刷新页面重新查询!");
			return returnResultBean;
		}
		int result = marketDao.deleteNoPayOrderById(orderId);
		if(result != 1){
			returnResultBean.setMessage("订单删除失败!");
			throw new Exception("订单删除失败!");
		}
		marketDao.deleteOrderDetailByOrderId(orderId);
		marketDao.deletePayTradeByOrderId(orderId);
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public ReturnResultBean refundApply(WeixinRefundApplyBean weixinRefundApplyBean, FansBean fansBean) throws Exception {
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if (weixinRefundApplyBean == null || StringUtils.isBlank(weixinRefundApplyBean.getOrderId())) {
			returnResultBean.setMessage("非法操作!");
			return returnResultBean;
		}
		String orderId = weixinRefundApplyBean.getOrderId();
		List<OrderBean> orderList = orderDao.queryOrder(new OrderBean(orderId, fansBean.getCustomerId()));
		if (orderList == null || orderList.isEmpty()) {
			returnResultBean.setMessage("没有该订单信息，请刷新页面重新查询!");
			return returnResultBean;
		}
		OrderBean orderBean = orderList.get(0);
		if(!"收货".equals(orderBean.getOrderState())){
			returnResultBean.setMessage("当前订单还不能做退款申请!");
			return returnResultBean;
		}
		weixinRefundApplyBean.setWeixinRefundApplyId(UUID.randomUUID().toString());
		weixinRefundApplyBean.setCustomerId(fansBean.getCustomerId());
		//weixinRefundApplyBean.setRefundMoney(orderBean.getOrderMoney());
		weixinRefundApplyBean.setCreateTime(new Date());
		marketDao.insertWeixinRefundApply(weixinRefundApplyBean);
		returnResultBean.setMessage("退款申请发送成功，请耐心我们联系你!");
		returnResultBean.operationSuccess();
		return returnResultBean;
	}

}
