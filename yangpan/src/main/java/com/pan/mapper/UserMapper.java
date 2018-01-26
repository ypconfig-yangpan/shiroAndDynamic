package com.pan.mapper;

import com.pan.entity.Menu;
import com.pan.entity.Role;
import com.pan.entity.User;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 用户基础信息表USER Mapper 接口
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public interface UserMapper extends BaseMapper<User> {
	
	/** 查询所有用户,并且查询关联的角色*/
	public List<User> findAllUser();
	/** 根据用户名 查询user*/
	public User findUserByUsername(String username);
	/** 根据用户id查询菜单*/
	public List<Menu> findMenuByUserId(String userId);
	/**分页查询用户*/
	public List<User> selectAllUser();
}