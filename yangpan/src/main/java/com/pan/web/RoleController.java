package com.pan.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.pan.entity.CustomPage;
import com.pan.entity.Role;
import com.pan.service.IRoleService;

/**
 * <p>
 * 角色表SYS_ROLE 前端控制器
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService roleServiceImpl;
	
	
	//跳轉到編輯頁面edit
	@RequestMapping(value="editPage/{Id}")
	public String editPage(@PathVariable("Id") String Id,Model model) {
		if(Id.equals("add")){
		}else{
			Role role = roleServiceImpl.selectById(Id);
			model.addAttribute("role", role);
		}
		return "role/edit";
	}
	//跳转到role管理页面
	@RequestMapping(value="rolePage")
	public String role(String edit,Model modle) {
		//edit判断是否编辑成功
		modle.addAttribute("edit", edit);
		return "role/role";
	}
	
	
	//获取角色分页对象
	@RequestMapping(value="getRoleListWithPager")
	@ResponseBody
	public CustomPage getRoleListWithPager() {
		Page<Role> pageList = roleServiceImpl.selectPage();
		CustomPage<Role> customPage = new CustomPage<Role>(pageList);
		return customPage;
	}
	
	//刪除
	@RequestMapping(value="delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value = "ids[]") String[] ids) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			roleServiceImpl.deleteBatchIds(Arrays.asList(ids));
			resultMap.put("flag", true);
			resultMap.put("msg", "刪除成功！");
		} catch (Exception e) {
			resultMap.put("flag", false);
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}
	
	//增加和修改
	@RequestMapping(value="edit")
	public String edit(Role role,Model model) {
		if(roleServiceImpl.insertOrUpdate(role)){
			return "redirect:rolePage?edit=true";
		}else{
			return "redirect:rolePage?edit=false";
		}
	}
}
