package com.mobile.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.biz.vo.ReturnResultBean;
import com.mobile.constants.SysMobileConstants;
import com.mobile.po.wx.WeixinFansBean;
import com.mobile.service.FansService;
import com.mobile.vo.wx.FansBean;
import com.mobile.vo.wx.LoginFansBean;
import com.mobile.vo.wx.RegistFansBean;

/**
 * 粉丝操作 Action类
 * 
 * @author 秀才
 */
@Controller
@Scope("prototype")
public class FansAction extends MobileBaseAction {

	private static final long serialVersionUID = -2198807526011120922L;

	private static Log log = LogFactory.getLog(FansAction.class);

	@Resource
	private FansService fansService;

	/** 粉丝注册信息Bean */
	private RegistFansBean registFansBean;

	/** 绑定用户(登录) */
	public String execute() throws Exception {
		log.info("绑定用户");
		HttpServletRequest request = getRequest();
		ReturnResultBean returnResultBean = null;
		if (request.getSession().getAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION) != null) {
			returnResultBean = new ReturnResultBean();
			returnResultBean.setMessage("您已经绑定我们的电商平台无需登录！");
			returnResultBean.operationSuccess();
			outPrintJson(returnResultBean);
			return null;
		}
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String fansInfo = getCookieByName(SysMobileConstants.WEIXIN_FANS_INFO_COOKIE);
		LoginFansBean loginFansBean = new LoginFansBean();
		loginFansBean.setLoginName(userName);
		loginFansBean.setPassword(password);
		loginFansBean.setOpenId(fansInfo);
		// if(StringUtils.isNotBlank(fansInfo)){
		// String[] fansArray = fansInfo.split("|");
		// if(fansArray.length == 2){
		// loginFansBean.setOpenId(fansArray[0]);
		// loginFansBean.setUnionId(unionId);
		// }
		// }
		returnResultBean = fansService.bindFans(loginFansBean);
		if (returnResultBean.isSuccess()) {
			WeixinFansBean weixinFansBean = (WeixinFansBean) returnResultBean.getReturnData("weixinFansBean");
			FansBean fansBean = new FansBean();
			fansBean.setWeixinFansId(weixinFansBean.getWeixinFansId());
			fansBean.setCustomerId(weixinFansBean.getCustomerId());
			fansBean.setCustomerType(weixinFansBean.getCustomerType());
			fansBean.setIsSubcribie(weixinFansBean.getIsSubscribe() == null ? false : weixinFansBean.getIsSubscribe());
			fansBean.setOpenId(fansInfo);
			fansBean.setUnionId(weixinFansBean.getUnionId());
			getRequest().getSession().setAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION, fansBean);
		}
		outPrintJson(returnResultBean);
		return null;
	}

	/** 进入登录页面 */
	public String gotoLogin() throws Exception {
		log.info("进入登录页面");
		if (getRequest().getSession().getAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION) != null) {
			return "marketIndex";
		}
		String fansInfoCookie = getCookieByName(SysMobileConstants.WEIXIN_FANS_INFO_COOKIE);
		ReturnResultBean returnResultBean = fansService.queryFansByOpenId(fansInfoCookie);
		if (!returnResultBean.isSuccess()) {
			return "login";
		}
		WeixinFansBean weixinFansBean = (WeixinFansBean) returnResultBean.getReturnData("weixinFansBean");
		if (weixinFansBean == null) {
			return "login";
		}
		FansBean fansBean = new FansBean();
		fansBean.setWeixinFansId(weixinFansBean.getWeixinFansId());
		fansBean.setCustomerId(weixinFansBean.getCustomerId());
		fansBean.setCustomerType(weixinFansBean.getCustomerType());
		fansBean.setIsSubcribie(weixinFansBean.getIsSubscribe() == null ? false : weixinFansBean.getIsSubscribe());
		fansBean.setOpenId(fansInfoCookie);
		fansBean.setUnionId(weixinFansBean.getUnionId());
		getRequest().getSession().setAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION, fansBean);
		return "marketIndex";
	}

	/** 去注册信息 */
	public String gotoRegist() throws Exception {
		log.info("去注册信息页面");
		if (getRequest().getSession().getAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION) != null) {
			return "marketIndex";
		}
		String fansInfoCookie = getCookieByName(SysMobileConstants.WEIXIN_FANS_INFO_COOKIE);
		ReturnResultBean returnResultBean = fansService.queryFansByOpenId(fansInfoCookie);
		if (!returnResultBean.isSuccess()) {
			return "regist";
		}
		WeixinFansBean weixinFansBean = (WeixinFansBean) returnResultBean.getReturnData("weixinFansBean");
		if (weixinFansBean == null) {
			return "regist";
		}
		FansBean fansBean = new FansBean();
		fansBean.setWeixinFansId(weixinFansBean.getWeixinFansId());
		fansBean.setCustomerId(weixinFansBean.getCustomerId());
		fansBean.setCustomerType(weixinFansBean.getCustomerType());
		fansBean.setIsSubcribie(weixinFansBean.getIsSubscribe() == null ? false : weixinFansBean.getIsSubscribe());
		fansBean.setOpenId(fansInfoCookie);
		fansBean.setUnionId(weixinFansBean.getUnionId());
		getRequest().getSession().setAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION, fansBean);
		return "marketIndex";
	}

	/**
	 * 验证用户名是否存在
	 * 
	 * @param customerBean
	 *            用户信息Bean
	 */
	public String validateNameIsExist() {
		log.info("验证用户名是否存在");
		try {
			HttpServletRequest reuqest = getRequest();
			String name = reuqest.getParameter("loginName");
			outPrintJson(fansService.validateNameIsExist(name));
		} catch (Exception e) {
			log.info(e);
			outPrintExceptionJson(e.toString());
		}
		return null;
	}

	/** 注册信息 */
	public String regist() throws Exception {
		log.info("粉丝注册信息");
		ReturnResultBean returnResultBean = null;
		HttpServletRequest request = getRequest();
		if (request.getSession().getAttribute(SysMobileConstants.WEIXIN_LOGIN_USER_SESSION) != null) {
			returnResultBean = new ReturnResultBean();
			returnResultBean.setMessage("您已经绑定我们的电商平台无需登录！");
			returnResultBean.operationSuccess();
			outPrintJson(returnResultBean);
			return null;
		}
		String fansInfoCookie = getCookieByName(SysMobileConstants.WEIXIN_FANS_INFO_COOKIE);
		outPrintJson(fansService.regist(registFansBean, fansInfoCookie));
		return null;
	}

	/** 注册成功信息提示页面 */
	public String gotoRegistSuccessMessage() throws Exception {
		log.info("注册成功信息提示页面");
		return "registSuccessMessage";
	}

	public RegistFansBean getRegistFansBean() {
		return registFansBean;
	}

	public void setRegistFansBean(RegistFansBean registFansBean) {
		this.registFansBean = registFansBean;
	}

}
