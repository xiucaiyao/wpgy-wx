package com.mobile.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mobile.constants.SysMobileConstants;
import com.mobile.vo.wx.FansBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yu.vo.ReturnResultBean;

/**
 * 移动端Action基类
 * 
 * @author 金鱼
 */
@Controller
@Scope("prototype")
public class MobileBaseAction extends ActionSupport {

	private static final long serialVersionUID = -7172645145446524343L;

	private static Log log = LogFactory.getLog(MobileBaseAction.class);

	/**
	 * 输出到前端html格式内容
	 * 
	 * @param printContents
	 *            输出的内容
	 */
	protected void outPrint(String printContents) {
		outPrint(printContents, null);
	}

	/**
	 * 输出到前端json格式内容
	 * 
	 * @param printContents
	 *            输出的内容
	 */
	protected void outPrintJson(String printContents) {
		outPrint(printContents, "json");
	}

	/**
	 * 把returnResultBean输出到前端json格式内容
	 * 
	 * @param returnResultBean
	 *            返回结果Bean
	 */
	protected void outPrintJson(ReturnResultBean returnResultBean) {
		outPrint(JSONObject.fromObject(returnResultBean).toString(), "json");
	}

	/**
	 * 输出到前端xml格式内容
	 * 
	 * @param printContents
	 *            输出的内容
	 */
	protected void outPrintXml(String printContents) {
		outPrint(printContents, "xml");
	}

	/**
	 * 输出到前端内容信息
	 * 
	 * @param printContents
	 *            输出的内容
	 * @param outType
	 *            输入的类型
	 */
	protected void outPrint(String printContents, String outType) {
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		if (StringUtils.isBlank(outType) || "html".equals(outType)) {
			response.setContentType("text/html;charset=utf-8");// 告诉浏览器用utf-8解码
		} else if ("text".equals(outType)) {
			response.setContentType("text/plain; charset=utf-8");
		} else if ("xml".equals(outType)) {
			response.setContentType("text/xml; charset=utf-8");
		} else if ("json".equals(outType)) {
			response.setContentType("text/json; charset=utf-8");
		}
		response.setCharacterEncoding("UTF-8"); // 用于response.getWriter()输出的字符流的乱码问题，如果是response.getOutputStream()是不需要
		try {
			PrintWriter out = response.getWriter();
			out.print(printContents);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	/**
	 * 纯粹的JSP页面跳转
	 */
	@SuppressWarnings("unchecked")
	public String pageJump() {
		log.info("纯粹的JSP页面跳转");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String pageName = request.getParameter("pageName");
		if (StringUtils.isBlank(pageName)) {
			pageName = "error";
			request.setAttribute("errorMsg", "无法找到页面!");
		} else {
			Enumeration<String> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String parameterName = enumeration.nextElement();
				if (!"pageName".equals(parameterName)) {
					request.setAttribute(parameterName, request.getParameter(parameterName));
				}
			}
		}
		return pageName;
	}

	/**
	 * 获取当前登录用户信息
	 */
	protected FansBean getLoginUser() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		return (FansBean) request.getSession().getAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION);
	}

	/**
	 * 获取当前登录用户的购物车
	 */
	// protected ShoppingCartBean getShoppingCart() {
	// HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	// return (ShoppingCartBean) request.getSession().getAttribute(SysMobileConstants.WEIXIN_SHOPPING_CART_SESSION);
	// }

	/**
	 * 获取当前登录用户信息的ID
	 */
	protected String getLoginUserId() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		FansBean loginUser = (FansBean) request.getSession().getAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION);
		if (loginUser != null) {
			return loginUser.getCustomerId();
		}
		return null;
	}

	/** 默认设置request错误类型信息提示 */
	protected void setRequestMessage(String message) {
		setRequestMessage("errorMsg", message);
	}

	/**
	 * 设置Request信息提示
	 * 
	 * @param messageType
	 *            错误信息类型
	 * @param message
	 *            错误信息
	 */
	protected void setRequestMessage(String messageType, String message) {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute(messageType, message);
	}

	/**
	 * 设置Request属性(如果数据名称，或者数据为null就不设置)
	 * 
	 * @param dataName
	 *            数据类型名称
	 * @param data
	 *            数据
	 */
	protected void setRequestAttribute(String dataName, Object data) {
		if (StringUtils.isNotBlank(dataName) && data != null) {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			request.setAttribute(dataName, data);
		}
	}

	/** 获得系统Http全路径 */
	protected String getProjectPath() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		return request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath();
	}

	/**
	 * 获取request
	 */
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	}

	/**
	 * 获取response
	 */
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);

	}

	/**
	 * 打印一个普通信息的Bean
	 * 
	 * @return message 输出到前端的消息
	 */
	protected void outPrintMessageJson(String message) {
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		returnResultBean.setMessage(message);
		outPrintJson(JSONObject.fromObject(returnResultBean).toString());
	}

	/**
	 * 打印一个普通信息的Bean
	 * 
	 * @return message 输出到前端的消息
	 * @return flag 输出到前端的业务逻辑标识
	 */
	protected void outPrintMessageJson(String message, String flag) {
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		returnResultBean.setMessage(message);
		returnResultBean.addReturnData("flag", flag);
		outPrintJson(JSONObject.fromObject(returnResultBean).toString());
	}

	/**
	 * 打印一个异常信息的Bean
	 * 
	 * @return exceptionMessage throwable的详细消息字符串
	 */
	protected void outPrintExceptionJson(String exceptionMessage) {
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		returnResultBean.setExceptionMessage(exceptionMessage);
		outPrintJson(JSONObject.fromObject(returnResultBean).toString());
	}

	/**
	 * 打印一个异常信息的Bean
	 * 
	 * @return exceptionMessage throwable的详细消息字符串
	 */
	protected void outPrintException(String exceptionMessage, String outType) {
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		returnResultBean.setExceptionMessage(exceptionMessage);
		outPrint(JSONObject.fromObject(returnResultBean).toString(), outType);
	}

	/**
	 * 获取cookie的值根据cookie名称
	 * 
	 * @param cookieName
	 *            cookie名称
	 */
	protected String getCookieByName(String cookieName) {
		if (StringUtils.isBlank(cookieName)) {
			return null;
		}
		HttpServletRequest req = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

	/**
	 * 设置cookie
	 * 
	 * @param cookieName
	 *            cookie名字
	 * @param cookieValue
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	protected void setCookieByName(String cookieName, String cookieValue, Integer maxAge) {
		if (StringUtils.isBlank(cookieName) || StringUtils.isBlank(cookieValue)) {
			return;
		}
		if (maxAge == null) {
			maxAge = 24 * 60 * 60;
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		response.addCookie(cookie);
		log.info("成功设置Cookie信息:" + cookieName + "--" + cookieValue);
	}

	/**
	 * 清空cookie的值根据cookie名称
	 * 
	 * @param cookieName
	 *            cookie名称
	 */
	protected void clearCookieByName(String cookieName) {
		if (StringUtils.isBlank(cookieName)) {
			return;
		}
		HttpServletRequest req = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			return;
		}
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				cookie = new Cookie(cookieName, null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
				response.addCookie(cookie);
				break;
			}
		}
		return;
	}
}
