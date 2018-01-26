package com.pan.mapper;

import com.pan.entity.Role;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 角色表SYS_ROLE Mapper 接口
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public interface RoleMapper extends BaseMapper<Role> {
	
	/** 根据用户id 查询 role*/
	public List<Role> findRoleByUserId(String userId);
	/** 查询 所有的role*/
	public List<Role> findAllRole();
}