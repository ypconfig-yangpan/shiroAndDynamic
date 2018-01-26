package com.pan.service;

import com.pan.entity.Permission;
import com.pan.mapper.PermissionMapper;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 业务功能表 SYS_PERMISSION 服务类
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public interface IPermissionService extends IService<Permission> {
	/** 根据角色id 查询权限*/
	List<Permission> findPermissionByRoleId(String roleId);
	/** 根据用户id 查询 权限*/
	List<Permission> findPermissionByUserId(String userId);
	Page<Permission> selectPage();
}
