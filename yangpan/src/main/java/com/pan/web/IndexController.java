package com.pan.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.runners.Parameterized.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pan.entity.Menu;
import com.pan.result.YangPanResult;
import com.pan.service.IPermissionService;
import com.pan.service.IRoleService;
import com.pan.service.IUserService;
import com.pan.shiro.MyRealm;
/**
 * 进入跳转controller
 * @ClassName:  IndexController   
 * @Description:
 * @author: 杨攀
 */
@Controller
public class IndexController {
	
	private final Logger logger = LoggerFactory.getLogger(MyRealm.class);
	@Autowired
	private  IUserService userServiceImpl;
	@Autowired
	private IRoleService roleServiceImpl;
	@Autowired
	private IPermissionService permissionServiceImpl;
	


	@RequestMapping("/")
	public String forwardToIndex(){
		return "login";
	}
	@RequestMapping("/index")
	public String forwardToIndex11(){
		return "index";
	}
	
	@RequiresPermissions("delete")
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(){
		return "删除";
	}
	
	@RequiresPermissions("select")
	@ResponseBody
	@RequestMapping("/select")
	public String select(){
		return "查看";
	}
}
