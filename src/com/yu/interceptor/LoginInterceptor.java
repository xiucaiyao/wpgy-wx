package com.yu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.yu.constants.SysConstants;
import com.yu.po.CustomerBean;

/**
 * 验证登录拦截器
 * @author 金鱼
 */
public class LoginInterceptor implements Interceptor{

	private static final long serialVersionUID = -5778106184836821761L;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CustomerBean customerBean = (CustomerBean) request.getSession().getAttribute(SysConstants.LOGIN_USER_SESSION);
		if(customerBean == null){
//			String str = request.getQueryString();
//			String a = request.getRequestURL().toString();
//			Map parameterMap = request.getParameterMap();
			HttpServletResponse response = ServletActionContext.getResponse();
			String flag = request.getParameter("flag");
			if(StringUtils.isBlank(flag)){
				flag = "1";
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>top.location.href='" + request.getContextPath() + "/customer/customer!pageJump.action?pageName=customerLogin&flag="+flag+"';</script>");
			return null;
		}
		return invocation.invoke();
	}

}
