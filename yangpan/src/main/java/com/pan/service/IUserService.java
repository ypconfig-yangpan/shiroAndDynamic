package com.pan.service;

import com.pan.entity.Menu;
import com.pan.entity.User;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户基础信息表USER 服务类
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public interface IUserService extends IService<User> {
	/** 查询所有用户,并且查询关联的角色*/
	public List<User> findAllUser();
	/** 根据用户名 查询user*/
	public User findUserByUsername(String username);
	/** 根据用户名 查询menus*/
	public List<Menu> findMenubyUserId(String userId);
	Page<User> findUserList();
	public List<User> selectAllUser();
}
