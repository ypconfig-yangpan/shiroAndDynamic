package com.pan.shiro;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.shiro.authc.AuthenticationException;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.subject.Subject;  
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
/**
 * 登陆过滤器
 * @ClassName:  MyFormaAuthenticationFilter   
 * @Description:
 * @author: 杨攀
 */
public class MyFormaAuthenticationFilter extends FormAuthenticationFilter {  
  
    private static final Logger logger = LoggerFactory  
            .getLogger(MyFormaAuthenticationFilter.class);  
  
    /* 
     *  主要是针对登入成功的处理方法。对于请求头是AJAX的之间返回JSON字符串。 
     */  
    @Override  
    protected boolean onLoginSuccess(AuthenticationToken token,   
            Subject subject, ServletRequest request, ServletResponse response)  
            throws Exception {  
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
        logger.info("---------------登录的成功方法--------------");
        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest  
                .getHeader("X-Requested-With"))) {// 不是ajax请求  
            issueSuccessRedirect(request, response);  
        } else {  
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse(response).setContentType("application/json");
            PrintWriter out = httpServletResponse.getWriter();  
            out.println("{status:200,message:'登入成功'}");  
            out.flush();  
            out.close();  
        }  
        return false;  
    }  
  
    /** 
     * 主要是处理登入失败的方法 
     */  
    @Override  
    protected boolean onLoginFailure(AuthenticationToken token,  
            AuthenticationException e, ServletRequest request,  
            ServletResponse response) {  
    	 logger.info("---------------登录的失败方法--------------");
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)  
                .getHeader("X-Requested-With"))) {// 不是ajax请求  
            setFailureAttribute(request, e);  
            return true;  
        }  
        try {
            response.setCharacterEncoding("UTF-8"); 
            httpServletResponse(response).setContentType("application/json");
            PrintWriter out = response.getWriter();  
            String message = e.getClass().getSimpleName();  
            if ("IncorrectCredentialsException".equals(message)) {  
                out.println("{status:200,message:'密码错误'}");  
            } else if ("UnknownAccountException".equals(message)) {  
                out.println("{status : 300,message:'账号不存在'}");  
            } else if ("LockedAccountException".equals(message)) {  
                out.println("{status:400,message:'账号被锁定'}");  
            } else {  
                out.println("{status:500,message:'未知错误'}");  
            }  
            out.flush();  
            out.close();  
        } catch (IOException e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        }  
        return false;  
    }  
    
    private ServletResponse httpServletResponse(ServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * 记住我
     */
    protected boolean isRememberMe(ServletRequest request) {
        return WebUtils.isTrue(request, getRememberMeParam());
    }

}