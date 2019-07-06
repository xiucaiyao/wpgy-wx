package com.mobile.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.biz.vo.ReturnResultBean;
import com.mobile.action.FansAction;
import com.mobile.action.WeixinAction;
import com.mobile.constants.SysMobileConstants;
import com.mobile.constants.WeixinConstants;
import com.mobile.constants.WxAppConstants;
import com.mobile.po.wx.WeixinFansBean;
import com.mobile.service.FansService;
import com.mobile.utils.WeixinAPIUtils;
import com.mobile.vo.wx.FansBean;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import net.sf.json.JSONObject;

public class AuthInterceptor implements Interceptor {

	private static final long serialVersionUID = 1625485026040874868L;

	private static Log log = LogFactory.getLog(AuthInterceptor.class);

	/** 不拦截的URL */
	public static String NOT_CHECK_URI;

	/** 不拦截的登录信息的URL */
	public static String NOT_CHECK_LOGIN_URI;

	@Resource
	private FansService fansService;

	public void destroy() {
		// 与微信交互的URL
		NOT_CHECK_URI = "";
		// 登录 注册 错误信息
	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation actioninvocation) throws Exception {
		log.info("拦截器权限拦截...");
		Object object = actioninvocation.getAction();
		if (object instanceof WeixinAction) {
			return actioninvocation.invoke();
		}
		
		HttpServletRequest request = (HttpServletRequest) actioninvocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actioninvocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		
		//需求[20190706]：让公众号用户在未登陆前，就可以浏览果品市场
		String uri = request.getRequestURI();
		log.info("uri:" + uri);
		if (uri.contains("market!gotoMarket") 
				|| uri.contains("market!queryMarketProduct") 
				|| uri.contains("market!queryMarketProductType")) {
			log.info("免拦截的方法：" + uri);
			return actioninvocation.invoke();
		}
		
		//add by yaoxc at 20181028 for "测试-跳过登陆认证"
		if (WxAppConstants.ENV_TEST.equals(WxAppConstants.getEnvFlag())) {
			request.getSession().setAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION, new FansBean());
		}
		//end
		if (request.getSession().getAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION) == null) {
			// 如果当前用户信息没有登录session就从Cookie里取粉丝信息
			Cookie[] cookies = request.getCookies();
			if (cookies == null) {
				// 如果没有cookie，就从网页授权获取用户基本信息
				return gotoAuthorize(request, response);
			}
			String fansInfoCookie = null;
			for (Cookie cookie : cookies) {
				if (SysMobileConstants.WEIXIN_FANS_INFO_COOKIE.equals(cookie.getName())) {
					fansInfoCookie = cookie.getValue();
					break;
				}
			}
			if (StringUtils.isBlank(fansInfoCookie)) {
				return gotoAuthorize(request, response);
			}

			if (!(object instanceof FansAction)) {
				// 如果是微信API操作，或者是粉丝绑定操作就不验证登录信息
				return loginByOpenId(fansInfoCookie, request, response, actioninvocation);
			}
		}
		return actioninvocation.invoke();
	}

	/** 进入微信网页授权获取用户基本信息 */
	public String gotoAuthorize(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("进入微信网页授权获取用户基本信息");
		boolean isAjax = isAjaxRequest(request);
		if (isAjax) {
			ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
			returnResultBean.setMessage("非法操作！");
			outJsonPrint(returnResultBean, response);
			return null;
		}
		// response.setContentType("text/html;charset=utf-8");// 告诉浏览器用utf-8解码
		// response.setCharacterEncoding("UTF-8");
		String current_url = request.getRequestURI() + (request.getQueryString() == null ? "" : ("?" + request.getQueryString()));
		// TODO: 跳转页面
		String redirect_uri = request.getScheme() + "://" + request.getServerName() + ((request.getServerPort() == 80 || request.getServerPort() == 443) ? "" : (":" + request.getServerPort())) + request.getContextPath() + "/getOpenIdByCode.action?goto_uri=" + URLEncoder.encode(current_url, "UTF-8");
		log.info("redirect_uri:" + redirect_uri);
		String state = String.valueOf(System.currentTimeMillis());
		//System.out.println("APP_ID" + WeixinConstants.WEIXIN_APP_ID);
		String url = WeixinAPIUtils.getAuthorizeCodeAPI(WeixinConstants.WEIXIN_APP_ID, redirect_uri, state);
		log.info("url:" + url);
		// response.getWriter().print("<script>window.location.href='" + url + ";</script>");
		response.sendRedirect(url);
		return null;
	}

	/**
	 * 登录电商系统通过openId
	 * 
	 * @param openId
	 *            粉丝在微信公众号的唯一标识
	 * @return 返回登录是否成功
	 * @throws Exception
	 */
	public String loginByOpenId(String openId, HttpServletRequest request, HttpServletResponse response, ActionInvocation actioninvocation) throws Exception {
		log.info("登录电商系统通过openId");
		// 查询粉丝在电商平台的用户信息
		ReturnResultBean returnResultBean = fansService.queryFansByOpenId(openId);
		boolean isAjax = isAjaxRequest(request);
		if (!returnResultBean.isSuccess() || returnResultBean.getReturnData("weixinFansBean") == null) {
			if (isAjax) {
				outJsonPrint(returnResultBean, response);
			}
			return "login";
		}
		WeixinFansBean weixinFansBean = (WeixinFansBean) returnResultBean.getReturnData("weixinFansBean");
		FansBean fansBean = new FansBean();
		fansBean.setWeixinFansId(weixinFansBean.getWeixinFansId());
		fansBean.setCustomerId(weixinFansBean.getCustomerId());
		fansBean.setCustomerType(weixinFansBean.getCustomerType());
		fansBean.setIsSubcribie(weixinFansBean.getIsSubscribe() == null ? false : weixinFansBean.getIsSubscribe());
		fansBean.setOpenId(openId);
		fansBean.setUnionId(weixinFansBean.getUnionId());
		request.getSession().setAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION, fansBean);
		return actioninvocation.invoke();
	}

	/**
	 * 判断ajax请求
	 * 
	 * @param request
	 * @return
	 */
	protected boolean isAjaxRequest(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

	/** 输出JSON */
	protected void outJsonPrint(ReturnResultBean returnResultBean, HttpServletResponse response) {
		try {
			response.setContentType("text/json; charset=utf-8");
			response.setCharacterEncoding("UTF-8"); // 用于response.getWriter()输出的字符流的乱码问题，如果是response.getOutputStream()是不需要
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(returnResultBean).toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e);
		}

	}

}
