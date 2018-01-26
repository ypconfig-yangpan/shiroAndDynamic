package com.pan.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.plugins.Page;
import com.pan.entity.Menu;
import com.pan.entity.Permission;
import com.pan.entity.Role;
import com.pan.entity.User;
import com.pan.mapper.PermissionMapper;
import com.pan.mapper.RoleMapper;
import com.pan.mapper.UserMapper;
import com.pan.service.IPermissionService;
import com.pan.service.IUserService;

import groovy.util.logging.Log;
import groovy.util.logging.Log4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.pan.mapper")
public class MapperTest {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private IPermissionService permissionServiceImpl;
	@Test
	public void permissionServiceImpl(){
		Page<Permission> findUserList = permissionServiceImpl.selectPage();
		List<Permission> records = findUserList.getRecords();
		System.out.println(records);
		System.out.println(findUserList);
	}
	@Test
	public void tree(){
		List<User> list = userServiceImpl.selectAllUser();
		System.out.println(list);
	}
	@Test
	public void findRoleByUserTest(){
		Page<User> findUserList = userServiceImpl.findUserList();
		List<User> records = findUserList.getRecords();
		System.out.println(records);
		System.out.println(findUserList);
	}
	@Test
	public void findRoleByUserIdTest(){
		
		List<User> users = userMapper.selectAllUser();
		System.out.println(users);
	}
	@Test
	public void findUserByUsernameTest(){
		
		 User user = userMapper.findUserByUsername("yangpan");
		 
		System.out.println(user);
	}

	@Test
	public void findAllRole(){
		
		List<Role> list = roleMapper.findRoleByUserId("1");
		
		System.out.println(list);
	}
	@Test
	public void findUserByRoleID(){
		
		List<Role> sysUserBase = roleMapper.findAllRole();
		
		System.out.println(sysUserBase);
	}
	@Test
	public void findPermissionByUserIdTest(){
		
		List<Permission> list = permissionMapper.findPermissionByUserId("1");
		
		System.out.println(list);
	}
	@Test
	public void findMenuByUserIdTest(){
		
		List<Menu> list = userMapper.findMenuByUserId("1");
		
		System.out.println(list);
	}
}
