package com.pan.service.impl;

import com.pan.entity.Role;
import com.pan.mapper.PermissionMapper;
import com.pan.mapper.RoleMapper;
import com.pan.mapper.UserMapper;
import com.pan.service.IRoleService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色表SYS_ROLE 服务实现类
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	
	
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> findRoleByUserId(String userId) {
		return roleMapper.findRoleByUserId(userId);
	}

	@Override
	public List<Role> findAllRole() {
		return roleMapper.findAllRole();
	}

	@Override
	public Page<Role> selectPage() {
		Page<Role> page = new Page<Role>(0,5);
		page.setRecords(findAllRole());
		return page;
	}
	
}
