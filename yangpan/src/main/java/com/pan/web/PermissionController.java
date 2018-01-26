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

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.pan.entity.CustomPage;
import com.pan.entity.Permission;
import com.pan.service.IPermissionService;

/**
 * <p>
 * 业务功能表 SYS_PERMISSION 前端控制器
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	private IPermissionService permissionServiceImpl;

	// 跳转到用户管理页面
	@RequestMapping(value = "permissionPage")
	public String userPage(String edit, Model modle) {
		// edit判断是否编辑成功
		modle.addAttribute("edit", edit);
		return "permission/permission";
	}

	// 跳轉到編輯頁面edit
	@RequestMapping(value = "editPage/{Id}")
	public String editPage(@PathVariable("Id") String Id, Model model) {
		if (Id.equals("add")) {
		} else {
			Permission permission = permissionServiceImpl.selectById(Id);
			model.addAttribute("permission", permission);
		}
		return "permission/edit";
	}

	// 增加和修改
	@RequestMapping(value = "edit")
	public String edit(Permission permission, Model model) {
		if (permissionServiceImpl.insertOrUpdate(permission)) {
			return "forward:permissionPage?edit=true";
		} else {
			return "forward:permissionPage?edit=false";
		}
	}

	// 用权限表分页json
	@RequestMapping(value = "getPermissionListWithPager")
	@ResponseBody
	public CustomPage<Permission> getPermissionListWithPager() {
		Page<Permission> pageList = permissionServiceImpl.selectPage();
		CustomPage<Permission> customPage = new CustomPage<Permission>(pageList);
		return customPage;
	}

	// 刪除
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(@RequestParam(value = "ids[]") String[] ids) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			permissionServiceImpl.deleteBatchIds(Arrays.asList(ids));
			resultMap.put("flag", true);
			resultMap.put("msg", "刪除成功！");
		} catch (Exception e) {
			resultMap.put("flag", false);
			resultMap.put("msg", e.getMessage());
		}
		return JSON.toJSONString(resultMap);
	}
}
