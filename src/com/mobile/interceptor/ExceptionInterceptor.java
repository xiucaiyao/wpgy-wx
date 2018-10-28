package com.mobile.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.yu.vo.ReturnResultBean;

/**
 * Struts异常拦截器信息处理
 * 
 * @author 金鱼
 */
public class ExceptionInterceptor implements Interceptor {

	private static final long serialVersionUID = -8405642698383112644L;
	
	private static Log log = LogFactory.getLog(ExceptionInterceptor.class);

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation actioninvocation) throws Exception {
		try {  
            // 运行被拦截的Action,期间如果发生异常会被catch住  
            String result = actioninvocation.invoke();  
            return result;  
        } catch (Exception e) {
        	String errorMsg = "您访问的页面出错了！";
        	if(StringUtils.isNotBlank(e.getMessage())){
        		errorMsg = e.getMessage();
        	}
        	HttpServletRequest request = (HttpServletRequest) actioninvocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
        	HttpServletResponse response = (HttpServletResponse) actioninvocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
        	if((request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()))){
        		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
        		returnResultBean.setExceptionMessage(errorMsg);
        		response.setContentType("text/json; charset=utf-8");
    			response.setCharacterEncoding("UTF-8"); // 用于response.getWriter()输出的字符流的乱码问题，如果是response.getOutputStream()是不需要
    			PrintWriter out = response.getWriter();
    			out.print(JSONObject.fromObject(returnResultBean).toString());
    			out.flush();
    			out.close();
        		return null;
        	}
            request.setAttribute("errorMsg", errorMsg);
            e.printStackTrace();
            log.error("错误的URL:"+request.getRequestURI());
            log.error("参数列表："+request.getQueryString());
            log.error(e);
            return "error";
        }
	}

}
