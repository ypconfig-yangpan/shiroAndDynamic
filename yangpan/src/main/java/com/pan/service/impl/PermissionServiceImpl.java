package com.pan.service.impl;

import com.pan.entity.Permission;
import com.pan.mapper.PermissionMapper;
import com.pan.mapper.RoleMapper;
import com.pan.mapper.UserMapper;
import com.pan.service.IPermissionService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务功能表 SYS_PERMISSION 服务实现类
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	
	@Override
	public List<Permission> findPermissionByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return permissionMapper.findPermissionByRoleId(roleId);
	}

	/**
	 * 根据用户id 查询权限
	 */
	@Override
	public List<Permission> findPermissionByUserId(String userId) {
		List<Permission> permissions = permissionMapper.findPermissionByUserId(userId);
		return permissions;
	}
	/**
	 * 分页查询
	 */
	@Override
	public Page<Permission> selectPage() {
		Page<Permission> page = new Page<Permission>(0,5);
		 page.setRecords(permissionMapper.findAllPermission());
		 return page;
	}


}
