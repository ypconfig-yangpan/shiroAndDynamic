package com.pan.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.pan.service.IUserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>User: yangpan
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {
	
	public static final String CURRENT_USER = "user";
	@Autowired
	private IUserService UserServiceImpl;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(CURRENT_USER, UserServiceImpl.findUserByUsername(username));
        return true;
    }
}
