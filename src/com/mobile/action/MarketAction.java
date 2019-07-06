package com.mobile.action;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.biz.vo.CustomerOrderBean;
import com.biz.vo.OrderQueryBean;
import com.biz.vo.ProductQueryBean;
import com.biz.vo.ReturnResultBean;
import com.mobile.constants.SysMobileConstants;
import com.mobile.constants.WeixinConstants;
import com.mobile.po.wx.WeixinRefundApplyBean;
import com.mobile.service.MarketService;
import com.mobile.vo.wx.FansBean;
import com.mobile.vo.wx.MobileShoppingCartBean;
import com.mobile.vo.wx.WechatPayDto;
import com.wechat.utils.GetWxOrderno;
import com.wechat.utils.RequestHandler;
import com.wechat.utils.Sha1Util;

/**
 * 用户 Action类
 * 
 * @author 秀才
 */
@Controller
@Scope("prototype")
public class MarketAction extends MobileBaseAction {

	private static final long serialVersionUID = 250985837696537318L;

	private static Log log = LogFactory.getLog(MarketAction.class);

	@Resource
	private MarketService marketService;

	// 产品查询Bean
	private ProductQueryBean productQueryBean;

	// 购物车产品信息Bean
	private MobileShoppingCartBean mobileShoppingCartBean;
	// private ShoppingCartProductBean shoppingCartProductBean;

	// 用户订单信息Bean
	private CustomerOrderBean customerOrderBean;

	// 订单查询信息Bean
	private OrderQueryBean orderQueryBean;
	
	private WeixinRefundApplyBean weixinRefundApplyBean;
	
	/**
	 * 进入果品市场
	 * 
	 * 需求背景：让公众号用户在未登陆前，就可以浏览果品市场
	 * 
	 * @return
	 * @throws Exception
	 */
	public String gotoMarket() throws Exception {
		log.info("进入果品市场。。。");
		return "marketIndex";
	}

	// 分页查询用户产品信息列表
	public String queryMarketProduct() throws Exception {
		log.info("分页查询用户产品信息列表");
		outPrintJson(marketService.queryMarketProduct(productQueryBean));
		return null;
	}

	// 查询用户产品分类信息列表
	public String queryMarketProductType() throws Exception {
		log.info("查询用户产品分类信息列表");
		outPrintJson(marketService.queryMarketProductType());
		return null;
	}

	// 查询用户产品详细信息
	public String productDetailBySerial() throws Exception {
		log.info("查询用户产品详细信息");
		String productSerial = null;
		String productSpec = null;
		if (productQueryBean != null) {
			productSerial = productQueryBean.getSerial();
			productSpec = productQueryBean.getProductSpec();
		}
		outPrintJson(marketService.productDetailBySerial(productSerial, productSpec));
		return null;
	}

	// 加入商品到购物车
	// public String addProduct() throws Exception {
	// log.info("加入商品到购物车");
	// outPrintJson(marketService.addProduct(shoppingCartProductBean,
	// getShoppingCart()));
	// return null;
	// }

	// 修改商品到购物车
	// public String updateProduct() throws Exception {
	// log.info("修改商品到购物车");
	// outPrintJson(marketService.addProduct(shoppingCartProductBean,
	// getShoppingCart()));
	// return null;
	// }

	// 删除购物车中的商品
	// public String deleteProduct() throws Exception {
	// log.info("删除购物车中的商品");
	// HttpServletRequest request = getRequest();
	// String productSerial = request.getParameter("productSerial");
	// String productSpec = request.getParameter("productSpec");
	// outPrintJson(marketService.deleteProduct(productSerial, productSpec,
	// getShoppingCart()));
	// return null;
	// }

	// 去购物车查看
	// public String queryShoppingCart() throws Exception {
	// log.info("去购物车查看");
	// outPrintJson(marketService.queryShoppingCart(getShoppingCart(),
	// getLoginUser()));
	// return null;
	// }

	// 查看购物车
	// 由于移动端购物车都是放在前端来处理的，这里是最后的后端验证
	public String viewShoppingCart() throws Exception {
		log.info("查看购物车");
		outPrintJson(marketService.viewShoppingCart(mobileShoppingCartBean, getLoginUser()));
		return null;
	}

	// 增加用户订单
	// 由于移动端购物车都是放在前端来处理的，这里是最后的后端验证
	public String generateMarketOrder() throws Exception {
		log.info("增加用户订单");
		outPrintJson(marketService.generateMarketOrder(getLoginUser(), mobileShoppingCartBean, customerOrderBean));
		return null;
	}

	// 微信退款
	public String weixinRefund() throws Exception {
		log.info("微信退款");
		ReturnResultBean returnResultBean = marketService.weixinRefund(null, "wxb5cdd9028d9a5b131442941723");
		outPrintJson(returnResultBean);
		return null;
	}

	// 查询订单信息
	public String queryOrder() throws Exception {
		log.info("查询订单信息");
		outPrintJson(marketService.queryOrder(getLoginUser(), orderQueryBean));
		return null;
	}

	// 查询订单详细
	public String queryOrderDetail() throws Exception {
		log.info("查询订单详细");
		outPrintJson(marketService.queryOrderDetail(getRequest().getParameter("orderId"), getLoginUserId()));
		return null;
	}
	
	// 删除未支付的订单
	public String deleteNoPayOrder() throws Exception {
		log.info("删除未支付的订单");
		try{
			ReturnResultBean returnResultBean = marketService.deleteNoPayOrder(getRequest().getParameter("orderId"), getLoginUser());
			outPrintJson(returnResultBean);
		} catch(Exception e){
			e.printStackTrace();
			ReturnResultBean returnResultBean = new ReturnResultBean();
			returnResultBean.setMessage(e.getMessage());
			outPrintJson(returnResultBean);
		}
		return null;
	}
	
	public String refundApply() throws Exception {
		try{
			ReturnResultBean returnResultBean = marketService.refundApply(weixinRefundApplyBean, getLoginUser());
			outPrintJson(returnResultBean);
		} catch(Exception e){
			e.printStackTrace();
			ReturnResultBean returnResultBean = new ReturnResultBean();
			returnResultBean.setMessage(e.getMessage());
			outPrintJson(returnResultBean);
		}
		return null;
	}

	// ------------------------------------------支付----------------------------------

	public String wechatPay() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse responses = getResponse();

		// ------TODO:获取订单信息------------
		String orderId = request.getParameter("orderNo");
		FansBean fansBean = getLoginUser();
		ReturnResultBean bean = marketService.payOrderByWeixin(fansBean, orderId);
		if (!bean.isSuccess()) {
			request.setAttribute("massage", "找不到订单记录！");
			return "error";
		}
		String orderNo = bean.getReturnData("outTradeNo").toString();
		String orderBody = bean.getReturnData("orderName").toString();
		String attach = "附加数据";
		String amount = bean.getReturnData("orderMoney").toString();// 元
		WechatPayDto wechatPayDto = new WechatPayDto();
		wechatPayDto.setBody(orderBody);
		wechatPayDto.setAttach(attach);
		wechatPayDto.setOut_trade_no(orderNo);
		wechatPayDto.setMoney(amount);
		wechatPayDto.setSpbill_create_ip(request.getRemoteAddr());
		wechatPayDto.setOpenid(fansBean.getOpenId());
		wechatPayDto.setNonce_str(WechatPayDto.getNonce_str());
		SortedMap<String, String> packageParams = WechatPayDto.getPackageParams(wechatPayDto, getProjectPath());
		RequestHandler reqHandler = new RequestHandler(request, responses);
		String prepayId = this._getPrepayId(reqHandler, packageParams);
		String timestamp = Sha1Util.getTimeStamp();
		String nonceStr = packageParams.get("nonce_str");
		String packages = "prepay_id=" + prepayId;
		SortedMap<String, String> signParam = this._getSign(timestamp, nonceStr, packages);
		String finalsign = reqHandler.createSign(signParam);
		request.setAttribute("appid", WeixinConstants.WEIXIN_APP_ID);
		request.setAttribute("timeStamp", timestamp);
		request.setAttribute("nonceStr", packageParams.get("nonce_str"));
		request.setAttribute("package", packages);
		request.setAttribute("sign", finalsign);
		request.setAttribute("payAmount", amount);
		request.setAttribute("payOrderNo", orderNo);
		return "pay";
	}

	/**
	 * 得到PrepayId
	 * 
	 * @param reqHandler
	 * @param packageParams
	 * @return
	 */
	private String _getPrepayId(RequestHandler reqHandler, SortedMap<String, String> packageParams) {
		log.info("_getPrepayId");
		reqHandler.init(WeixinConstants.WEIXIN_APP_ID, WeixinConstants.WEIXIN_APP_SECRET, WeixinConstants.WEIXIN_API_KEY);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + WeixinConstants.WEIXIN_APP_ID + "</appid>" + "<mch_id>" + WeixinConstants.WEIXIN_MCH_ID + "</mch_id>" + "<nonce_str>" + packageParams.get("nonce_str") + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + packageParams.get("body") + "]]></body>" + "<attach>" + packageParams.get("attach") + "</attach>" + "<out_trade_no>"
				+ packageParams.get("out_trade_no") + "</out_trade_no>" +
				// 金额，这里写的1 分到时修改
				"<total_fee>" + packageParams.get("total_fee") + "</total_fee>" + "<spbill_create_ip>" + packageParams.get("spbill_create_ip") + "</spbill_create_ip>" + "<notify_url>" + packageParams.get("notify_url") + "</notify_url>" + "<trade_type>" + packageParams.get("trade_type") + "</trade_type>" + "<openid>" + packageParams.get("openid") + "</openid>" + "</xml>";
		log.info(xml);
		String allParameters = "";
		try {
			allParameters = reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = "";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
			if (StringUtils.isBlank(prepay_id)) {
				System.out.println("统一支付接口报错");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return prepay_id;
	}

	/**
	 * 获取签名
	 * 
	 * @param timestamp
	 * @param nonceStr2
	 * @param packages
	 * @return
	 */
	private SortedMap<String, String> _getSign(String timestamp, String nonceStr2, String packages) {
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		finalpackage.put("appId", WeixinConstants.WEIXIN_APP_ID);
		finalpackage.put("timeStamp", timestamp);
		finalpackage.put("nonceStr", nonceStr2);
		finalpackage.put("package", packages);
		finalpackage.put("signType", "MD5");
		return finalpackage;
	}

	public ProductQueryBean getProductQueryBean() {
		return productQueryBean;
	}

	public void setProductQueryBean(ProductQueryBean productQueryBean) {
		this.productQueryBean = productQueryBean;
	}

	public MobileShoppingCartBean getMobileShoppingCartBean() {
		return mobileShoppingCartBean;
	}

	public void setMobileShoppingCartBean(MobileShoppingCartBean mobileShoppingCartBean) {
		this.mobileShoppingCartBean = mobileShoppingCartBean;
	}

	public CustomerOrderBean getCustomerOrderBean() {
		return customerOrderBean;
	}

	public void setCustomerOrderBean(CustomerOrderBean customerOrderBean) {
		this.customerOrderBean = customerOrderBean;
	}

	public OrderQueryBean getOrderQueryBean() {
		return orderQueryBean;
	}

	public void setOrderQueryBean(OrderQueryBean orderQueryBean) {
		this.orderQueryBean = orderQueryBean;
	}

	public WeixinRefundApplyBean getWeixinRefundApplyBean() {
		return weixinRefundApplyBean;
	}

	public void setWeixinRefundApplyBean(WeixinRefundApplyBean weixinRefundApplyBean) {
		this.weixinRefundApplyBean = weixinRefundApplyBean;
	}

	// 微信同步操作返回信息
	// public String payWeixinReturn() throws Exception {
	// log.info("微信同步操作返回信息");
	// TODO: 修改
	// outPrintJson(marketService.generateMarketOrder(getLoginUser(),
	// getShoppingCart(), customerOrderBean));
	// return null;
	// }
}
