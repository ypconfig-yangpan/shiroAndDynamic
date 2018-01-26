package com.pan.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pan.entity.Permission;
import com.pan.entity.Role;
import com.pan.entity.User;
import com.pan.service.IPermissionService;
import com.pan.service.IRoleService;
import com.pan.service.IUserService;

/**
 * 
 * @ClassName:  MyRealm   
 * @Description:
 * @author: 杨攀
 */
public class MyRealm extends AuthorizingRealm{
	private final Logger logger = LoggerFactory.getLogger(MyRealm.class);
	@Autowired
	private  IUserService userServiceImpl;
	@Autowired
	private IRoleService roleServiceImpl;
	@Autowired
	private IPermissionService permissionServiceImpl;

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String  principal = (String) authcToken.getPrincipal();// 得到用户名
        String tokenPassword = new String((char[])token.getCredentials()); //得到密码 // 得到密码
        logger.info("----用户名--------------"+principal);
        logger.info("-----密码----------------"+tokenPassword);
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
         User user = userServiceImpl.findUserByUsername(token.getUsername());
         String encodedPassword =null;
        if (null == user) {
        	// 从数据查询出 空数据 证明问题严重 
        	throw new RuntimeException("帐号或密码不正确！");
           
        }else if(user.getStatus()=="0"){
        	
             //如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
        	throw new DisabledAccountException("帐号已经禁止登录！");
        	
        }

        //账号判断;
        //加密方式;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		user, //用户
        		user.getPswd(), //密码
                getName()  //realm name
        );
       // ByteSource byteSource = ByteSource.Util.bytes(principal+user.getSalt2());
        //authenticationInfo.setCredentialsSalt(byteSource);
      //身份验证通过,返回一个身份信息
        return authenticationInfo;
	}
	
 
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
        /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        logger.info("--------授权方法：doGetAuthenticationInfo()-------");
        
        //根据用户名查找角色，请根据需求实现
        SimpleAuthorizationInfo  authorizationInfo  = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        
        User user = (User) subject.getPrincipal();
        
        // 获得用户名
        String userId = user.getUserId();
        // 查询该用户的 角色
        List<Role> roles = roleServiceImpl.findRoleByUserId(userId);
        Set<String> roleSet = new HashSet<String>();
        for (Role role : roles) { 
        	roleSet.add(role.getKeyword());
        	logger.info(user.getUsername()+"-----拥有角色"+role.getKeyword());
		}
        authorizationInfo.addRoles(roleSet);
        // 根据用户id 查询 对应的权限
      	List<Permission> permissions = permissionServiceImpl.findPermissionByUserId(userId);
      	HashSet<String> permissionSet = new HashSet<String>();
    	for (Permission permission : permissions) {
    		permissionSet.add(permission.getKeyword());
    		logger.info(user.getUsername()+"-----拥有权限"+permission.getKeyword());
		}
    	authorizationInfo.addStringPermissions(permissionSet);
		return authorizationInfo;
	}

}
