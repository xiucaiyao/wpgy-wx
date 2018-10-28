package com.mobile.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom.input.SAXBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mobile.constants.SysMobileConstants;
import com.mobile.constants.WeixinConstants;
import com.mobile.service.WeixinService;
import com.mobile.utils.WeixinAPIUtils;
import com.mobile.vo.wx.RequestPostData;
import com.mobile.vo.wx.WeiXinSdkConfigBean;
import com.yu.vo.ReturnResultBean;

/**
 * 移动端 微信Action基类
 * 
 * @author 金鱼
 */
@Controller
@Scope("prototype")
public class WeixinAction extends MobileBaseAction {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(WeixinAction.class);

	@Resource
	private WeixinService weixinService;

	// 微信JS-SDK 配置信息获取
	public String weixinSdk() throws Exception {
		log.info("微信JS-SDK 配置信息获取");
		String currPageUrl = getRequest().getParameter("currPageUrl");
		WeiXinSdkConfigBean weiXinSdkConfigBean = WeiXinSdkConfigBean.getInstance(currPageUrl);
		if (!weiXinSdkConfigBean.isSuccess()) {
			outPrintExceptionJson("获取SDK接口失败,请刷新页面重新获取！");
		}
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		returnResultBean.addReturnData("appId", weiXinSdkConfigBean.getWeixinAppid());
		returnResultBean.addReturnData("nonceStr", weiXinSdkConfigBean.getNonceStr());
		returnResultBean.addReturnData("timestamp", weiXinSdkConfigBean.getTimestamp());
		returnResultBean.addReturnData("signature", weiXinSdkConfigBean.getSignature());
		returnResultBean.operationSuccess();
		outPrintJson(returnResultBean);
		return null;
	}

	// 微信接口信息
	public String weixinApi() throws Exception {
		log.info("微信接口信息");
		String method = getRequest().getMethod();
		if ("GET".equalsIgnoreCase(method)) {
			weixinValidate();
		} else {
			weixinReplayMessage();
		}
		return null;
	}

	// 接受微信公众平台的验证
	public void weixinValidate() throws Exception {
		log.info("接受微信公众平台的验证");
		String signature = getRequest().getParameter("signature");
		String timestamp = getRequest().getParameter("timestamp");
		String nonce = getRequest().getParameter("nonce");
		if (WeixinAPIUtils.checkSignature(signature, timestamp, nonce)) {
			log.info("验证成功：signature:" + signature + " timestamp:" + timestamp + " nonce:" + nonce);
			String echostr = getRequest().getParameter("echostr");
			if (echostr == null) {
				echostr = "";
			}
			outPrint(echostr, "text");
		} else {
			log.info("验证失败....");
		}

	}

	// 微信粉丝传送消息
	@SuppressWarnings("unchecked")
	public void weixinReplayMessage() throws Exception {
		log.info("微信粉丝传送消息");
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流
		InputStream inputStream = getRequest().getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());
		// 释放资源
		inputStream.close();
		inputStream = null;
		ReturnResultBean returnResultBean = weixinService.weixinReplayMessage(map, getProjectPath());
		if (returnResultBean.isSuccess()) {
			String message = (String) returnResultBean.getReturnData("message");
			log.info("发送消息：" + message);
			outPrint(message, "xml");
		}
	}

	// 网页授权获取粉丝ID通过CODE
	public String getOpenIdByCode() throws Exception {
		log.info("网页授权获取粉丝ID通过CODE");
		HttpServletRequest request = getRequest();
		String code = request.getParameter("code");
		String redirect_uri = request.getParameter("goto_uri");
		if (StringUtils.isBlank(code) || StringUtils.isBlank(redirect_uri)) {
			log.info("粉丝账号信息无法获取");
			request.setAttribute("error_message", "系统正在维护，请稍后操作！");
			return "error";
		}
		log.info(WeixinConstants.WEIXIN_APP_ID + "============" + WeixinConstants.WEIXIN_APP_SECRET);
		JSONObject jsonObject = WeixinAPIUtils.getAuthorizeOpenIdAPI(WeixinConstants.WEIXIN_APP_ID,
				WeixinConstants.WEIXIN_APP_SECRET, code);
		if (jsonObject == null || StringUtils.isBlank(jsonObject.getString("openid"))) {
			log.info("粉丝账号信息无法获取");
			request.setAttribute("error_message", "系统正在维护，请稍后操作！");
			return "error";
		}
		String openid = jsonObject.getString("openid");
		// String unionid = jsonObject.getString("unionid");
		// setCookieByName(SysMobileConstants.WEIXIN_FANS_INFO_COOKIE,
		// openid+"|"+unionid, 60 * 24 * 60 * 60);
		setCookieByName(SysMobileConstants.WEIXIN_FANS_INFO_COOKIE, openid, 60 * 24 * 60 * 60);
		HttpServletResponse response = getResponse();
		response.sendRedirect(URLDecoder.decode(redirect_uri, "UTF-8"));
		return null;
	}

	// 微信支付商品异步操作
	public String payWeixinAsync() throws Exception {
		log.info("微信支付商品异步操作");
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		RequestPostData data = _getPostData(request);
		ReturnResultBean bean = weixinService.payWeixinAsyn(data);
		if (bean.isSuccess()) {
			// 通知微信
			response.getWriter().write(setXML("SUCCESS", ""));
			System.out.println("-------------" + setXML("SUCCESS", ""));
		} else {
			// 通知微信
			response.getWriter().write(setXML("Fail", ""));
			System.out.println("-------------" + setXML("SUCCESS", ""));
		}
		// TODO: 修改
		return null;
	}

	private static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";

	}

	/**
	 * 将通知返回的参数（现只取部分有使用的参数）转换成实体对象
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private RequestPostData _getPostData(HttpServletRequest request) throws IOException {
		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		StringBuffer sb = new StringBuffer(1000);
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine());
		}
		ByteArrayInputStream baisArrayInputStream = null;
		if (StringUtils.isNotBlank(sb.toString())) {

			try {
				baisArrayInputStream = new ByteArrayInputStream(sb.toString().getBytes("utf-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SAXBuilder builder = new SAXBuilder();
			try {
				org.jdom.Document doc = builder.build(baisArrayInputStream);
				org.jdom.Element root = doc.getRootElement();

				String out_trade_no = root.getChild("out_trade_no").getValue();

				String transaction_id = root.getChild("transaction_id").getValue();

				String total_fee = root.getChild("total_fee").getValue();

				String sign = root.getChild("sign").getValue();
				String result_code = root.getChild("result_code").getValue();
				String err_code_des = "";
				if (("FAIL").equals(result_code)) {
					err_code_des = root.getChild("err_code_des").getValue();
				}
				String attach = root.getChild("attach").getValue();
				String openid = root.getChild("openid").getValue();

				RequestPostData post = new RequestPostData();
				post.setOut_trade_no(out_trade_no);
				post.setTransaction_id(transaction_id);
				post.setTotal_fee(total_fee);
				post.setSign(sign);
				post.setResult_code(result_code);
				post.setErr_code_des(err_code_des);
				post.setAttach(attach);
				post.setOpenid(openid);
				return post;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// add by Xiucai at 2016-01-10 for "m2redirect - 响应宋的跳转", begin:
	public String m2redirect() throws Exception {
		log.info("m2redirect ...");
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (StringUtils.isBlank(code)) {
			log.info("粉丝账号信息无法获取");
			request.setAttribute("error_message", "系统正在维护，请稍后操作！");
			return "error";
		}
		//String redirect_uri = "http://m2.yaochicai.com:8081/getOpenIdByCode.aspx?code=" + code + "&state=" + state;
		String redirect_uri = "http://m2.yaochicai.com:8081/getOpenIdByCode.aspx?code=" + code;
		log.info("redirect_uri:" + redirect_uri);
		//log.info("APP_ID:" + WeixinConstants.WEIXIN_APP_ID);
		String url = WeixinAPIUtils.getAuthorizeCodeAPI(WeixinConstants.WEIXIN_APP_ID, redirect_uri, state);
		log.info("url:" + url);
		
		response.sendRedirect(URLDecoder.decode(url, "UTF-8"));
		return null;

	}
	//end;

}
