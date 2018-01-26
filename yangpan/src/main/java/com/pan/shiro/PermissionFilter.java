package com.pan.shiro;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName:  PermissionFilter   
 * @Description:
 * @author: 杨攀
 */ 
public class PermissionFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
        if (StringUtils.isNotEmpty(requestedWith) &&
                StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定格式数据
            httpServletResponse.setCharacterEncoding("UTF-8");
            // 设置返回的map
            Map<String,String> map= new HashMap<String,String>();
            map.put("success", "false");
            map.put("msg", "没有权限访问!");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
        } else {//如果是普通请求进行重定向
//        	Class<? extends ShiroFilterFactoryBean> clazz = new ShiroFilterFactoryBean().getClass();
//        	Method method = clazz.getMethod("getUnauthorizedUrl");
//        	String htmlAddress = (String) method.invoke(method);
//        	httpServletResponse.sendRedirect(htmlAddress);
            httpServletResponse.sendRedirect("/error-404.html");
        }
        return false;
	}

}
