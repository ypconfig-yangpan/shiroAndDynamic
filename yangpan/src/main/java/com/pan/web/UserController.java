package com.pan.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.pan.entity.CustomPage;
import com.pan.entity.User;
import com.pan.result.YangPanResult;
import com.pan.service.IUserService;


/**
 * 
 * @ClassName:  UserController   
 * @Description:
 * @author: 杨攀  用户注册登录
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userServiceImpl;
	
	
	// 跳转树
	@RequestMapping(value = "/tree")
	public String tree(String edit, Model modle) {
		modle.addAttribute("edit", edit);
		// edit判断是否编辑成功
		return "/user/authorzication";
	}
	// 跳转到用户管理页面
	@RequestMapping(value = "userPage")
	public String userPage(String edit, Model modle) {
		// edit判断是否编辑成功
		modle.addAttribute("edit", edit);
		return "user/user";
	}
	
	// 跳转到编辑页面edit
	@RequestMapping(value = "editPage/{Id}")
	public String editPage(@PathVariable("Id") String Id, Model model) {
		if (Id.equals("add")) {
		} else {
			User user = userServiceImpl.selectById(Id);
			model.addAttribute("user", user);
		}
		return "user/edit";
	}
	// 增加和修改
	@RequestMapping(value = "/edit/add")
	public String add(User user) {
		UUID randomUUID = UUID.randomUUID();
		user.setUserId(randomUUID.toString().replace("-", ""));
		boolean insertOrUpdate = userServiceImpl.insertOrUpdate(user);
			return "forward:userPage?edit=false";
	}
	// 增加和修改
	@RequestMapping(value = "edit")
	public String edit(User user,String isEffective, Model model) {
		if(isEffective==null||isEffective==""){
			user.setStatus("0");
		}else{
			user.setStatus("1");
		}
		if (userServiceImpl.insertOrUpdate(user)) {
			return "forward:userPage?edit=true";
		} else {
			return "forward:userPage?edit=false";
		}
	}
	// 用户列表分页json
	@RequestMapping(value = "getUserListWithPager")
	@ResponseBody
	public   CustomPage<User> getUserListWithPager() {
		
         Page<User> findUserList = userServiceImpl.findUserList();
         CustomPage<User> customPage = new CustomPage<User>(findUserList);
        return customPage;

	}
	
	// 用户列表分页json
	@RequestMapping(value = "/permissionTree")
	@ResponseBody
	public   List<User> getPermissionTree() {
		
		 List<User> list = userServiceImpl.selectAllUser();
		return list;
	}

	/**
	 * 
	 * @Title: userLogin 
	 * @Description: TODO 用户登录的方法
	 * @param @param username
	 * @param @param password
	 * @param @return
	 * @param @throws ExcessiveAttemptsException    设定文件 
	 * @return YangPanResult    返回类型 
	 * @throws
	 */
	//@RequestMapping(value="/login",consumes="application/json",produces="application/json")
	@RequestMapping(value="/login")
	@ResponseBody
	public YangPanResult userLogin(String username ,String pswd ) throws ExcessiveAttemptsException{
		if(StringUtils.isBlank(username)){
			
			return YangPanResult.build(300,  "用户名或者密码错误!");
		}
		
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,pswd);
			usernamePasswordToken.setRememberMe(false);
			Subject subject = SecurityUtils.getSubject();
			subject.login(usernamePasswordToken);
			SecurityUtils.getSubject().getSession().getId();
			System.out.println(SecurityUtils.getSubject().getSession().getId());
			logger.info("Session过期时间 = " +  SecurityUtils.getSubject().getSession().getTimeout());
			logger.info("SessionId = " +SecurityUtils.getSubject().getSession().getId());
			System.out.println(YangPanResult.build(200,  "登录成功！"));
			return YangPanResult.build(200,  "登录成功！");
		} catch (IncorrectCredentialsException e1) {
			e1.printStackTrace();
			return YangPanResult.build(300,  "用户名或者密码错误!");
		}catch (ExcessiveAttemptsException e2) {
			return YangPanResult.build(400,  "登录次数过多，请稍后重试！");
		}
		
		catch (Exception e) {
			logger.error(e.getMessage(),e);
			return YangPanResult.build(500,  "登录失败！");
		}
		
	}
	
	
	

	
	/**
	 * 
	* @Title: logOut 
	* @Description: TODO 退出登录的方法
	* @param @param req
	* @param @param session
	* @param @param resp
	* @param @return    设定文件 
	* @return YangPanResult    返回类型 
	* @throws
	 */
	@RequestMapping("/logout")
	public YangPanResult logOut(HttpServletRequest req,HttpSession session,HttpServletResponse resp){
		
		try {
			Subject subject = SecurityUtils.getSubject();
			// 这段代码会调用 session 的销毁方法
			subject.logout();
		} catch (Exception e) {
			e.printStackTrace();
			return YangPanResult.build(300, "退出失败!");
		}
		 return YangPanResult.build(200, "退出成功!");
		
		

			
	}



}
