package com.pan.service.impl;

import com.pan.entity.Menu;
import com.pan.entity.User;
import com.pan.mapper.PermissionMapper;
import com.pan.mapper.RoleMapper;
import com.pan.mapper.UserMapper;
import com.pan.service.IUserService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户基础信息表USER 服务实现类
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> findAllUser() {
		List<User> list = userMapper.findAllUser();
		return list;
	}

	@Override
	public User findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}

	@Override
	public List<Menu> findMenubyUserId(String userId) {
		List<Menu> menus = userMapper.findMenuByUserId(userId);
		return menus;
	}

	@Override
	public Page<User> findUserList() {
		Page<User>  page = new Page<User>(0, 5);
		page.setRecords(userMapper.selectAllUser());
		return page;
	}

	@Override
	public List<User> selectAllUser() {
		List<User> list = userMapper.findAllUser();
		return list;
	}
	
}
