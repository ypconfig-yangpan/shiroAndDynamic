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
 * 角色 权限关联表 前端控制器
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
@Controller
@RequestMapping("")
public class RolePermissionController {


}
