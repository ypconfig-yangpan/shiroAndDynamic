package com.pan.mapper;

import com.pan.entity.Permission;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 业务功能表 SYS_PERMISSION Mapper 接口
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public interface PermissionMapper extends BaseMapper<Permission> {
	/** 根据角色id 查询权限*/
	List<Permission> findPermissionByRoleId(String roleId);
	/** 根据用户id 查询 permission*/
	List<Permission> findPermissionByUserId(String userId);
	List<Permission> findAllPermission();

}