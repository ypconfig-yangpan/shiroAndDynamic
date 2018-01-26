package com.pan.service;

import com.pan.entity.Role;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色表SYS_ROLE 服务类
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public interface IRoleService extends IService<Role> {
	/** 根据用户id 查询 role*/
	public List<Role> findRoleByUserId(String userId);
	/** 查询 所有的role*/
	public List<Role> findAllRole();
	public Page<Role> selectPage();
}
