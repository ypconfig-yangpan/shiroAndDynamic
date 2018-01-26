package com.pan.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * shiro的配置类
 * @ClassName:  ShiroConfigurtion   
 * @Description:
 * @author: 杨攀
 */
@Configuration
public class ShiroConfigurtion {
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfigurtion.class);

	// redis 主机ip
    @Value("${spring.redis.host}")
    private String host;
    // redis 端口
    @Value("${spring.redis.port}")
    private int port;


    /**
     * 配置web 过滤器  主要是加载org.springframework.web.filter.DelegatingFilterProxy
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
    	logger.info("-------------加载DelegatingFilterProxy过滤器----------------");
    	FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    	// 这里的shiroFilter 名称要和 shiroFilter() 一致
    	//filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
    	DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("shiroFilter");
    	filterRegistrationBean.setFilter(delegatingFilterProxy);
    	filterRegistrationBean.addInitParameter("targetFilterLifecycle", "true");
    	filterRegistrationBean.setEnabled(true);
    	// 拦截规则
    	filterRegistrationBean.addUrlPatterns("/*");
    	return filterRegistrationBean;
    }
  
  

    
	/**
	 * 自定义 realm   由于我们使用的是CasRealm，所以已经集成了单点登录的功能 
	* @Title: MyRealm 
	* @Description: TODO
	* @param @return    设定文件 
	* @return MyRealm    返回类型 
	* @throws
	 */
    @Bean(name="MyRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public MyRealm MyRealm() {
    	MyRealm realm  = new MyRealm();
    	// 设置密码凭证器
    	//realm.setCredentialsMatcher(credentialsMatcher());
    	//realm.setCacheManager(shiroCacheManager());
        return realm ;
    }
    
    

	/**
	 * 
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 * shiro的核心过滤器
	 * 
	 * Filter Chain定义说明 
	 * 1、一个URL可以配置多个Filter，使用逗号分隔 
	 * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
	 */
    @Bean(name = "shiroFilter")
	public  ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager  securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //添加casFilter到shiroFilter中，注意，casFilter需要放到shiroFilter的前面， 从而保证程序在进入shiro的login登录之前就会进入单点认证 
        
        //限制同一帐号同时在线的个数。
       // filtersMap.put("kickout", kickoutSessionControlFilter());
       //  filtersMap.put("loginFilter", LoginFilter());
       //  filtersMap.put("roleFilter", RoleFilter());
        // filtersMap.put("permissionFilter", PermissionFilter());
         filtersMap.put("myFormaAuthenticationFilter", myFormaAuthenticationFilter());
         shiroFilterFactoryBean.setFilters(filtersMap);
        logger.info("---------------Shiro拦截器工厂类注入成功-----------------");
		
		// shiro URL控制过滤器规则
        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        // 过滤链定义，从上向下顺序执行，一般将 /** 放在最为下边    :这是一个坑呢，一不小心代码就不好使了;
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/login.html", "anon");
        filterChainDefinitionMap.put("/main.html*", "roles[administrator]");
        filterChainDefinitionMap.put("/typography.html*", "perms[不存在的权限]");
        filterChainDefinitionMap.put("/typography.html*", "roles[不存在的权限]");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");
        filterChainDefinitionMap.put("/add", "perms[add]");
        filterChainDefinitionMap.put("/**",  "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println(filterChainDefinitionMap);
		// 未认证跳转页面
		shiroFilterFactoryBean.setLoginUrl("/");
		// 登录跳转
		shiroFilterFactoryBean.setSuccessUrl("index");
		// 认证后，没有权限跳转页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/error-404.html");
        return shiroFilterFactoryBean;
		
	}
    
	/**
	 * 安全管理器 
	* @Title: securityManager 
	* @Description: TODO
	* @param @return    设定文件 
	* @return DefaultWebSecurityManager    返回类型 
	* @throws
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManager(){
		logger.info("shiro安全管理器启动了......");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(MyRealm());
		// redis实现缓存
		securityManager.setCacheManager(cacheManager());
		// redis 实现session
		securityManager.setSessionManager(sessionManager());
		// 记住我
		//securityManager.setRememberMeManager(rememberMeManager());
		
		return securityManager;
		
	}

	
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * session管理器：
     * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，
     * 因为我们禁用掉了会话，所以没必要再定期过期会话了。
     * @return
     */
    public DefaultSessionManager  sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        /*
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);*/
        return sessionManager;
    }


    /**
     * cacheManager 缓存 redis实现
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        //redisManager.setPassword(pwd);
        redisManager.setExpire(1800);// 配置过期时间
        return redisManager;
    }

    /**
     * ajax 角色控制器
    * @Title: RoleFilter 
    * @Description: TODO
    * @param @return    设定文件 
    * @return Filter    返回类型 
    * @throws
     */
    @Bean(name="roleFilter")
    public RoleFilter RoleFilter() {
    	logger.info("---------------角色权限的处理器-----------------");
    	RoleFilter roleFilter = new RoleFilter();
		return roleFilter;
	}

	@Bean(name="permissionFilter")
	public PermissionFilter PermissionFilter() {
		logger.info("---------------授权处理器-----------------");
		PermissionFilter permissionFilter = new PermissionFilter();
		return permissionFilter;
	}

	/**
	 * ajax控制器(登录)
	 * @Title: LoginFilter 
	 * @Description: TODO
	 * @param @return    设定文件 
	 * @return Filter    返回类型 
	 * @throws
	 */
    @Bean(name="myFormaAuthenticationFilter")
    public MyFormaAuthenticationFilter myFormaAuthenticationFilter() {
    	logger.info("---------------登录验证处理器-----------------");
    	MyFormaAuthenticationFilter myFormaAuthenticationFilter = new MyFormaAuthenticationFilter();
		return myFormaAuthenticationFilter;
	}
	/**
     * ajax控制器(登录)
    * @Title: LoginFilter 
    * @Description: TODO
    * @param @return    设定文件 
    * @return Filter    返回类型 
    * @throws
     */
//    @Bean(name="loginFilter")
//    public Filter LoginFilter() {
//    	logger.info("---------------登录验证处理器-----------------");
//    	com.pan.shiro.LoginFilter loginFilter = new LoginFilter();
//		return loginFilter;
//	}


	/**
	 * 
	* @Title: <!-- rememberMe管理器 --> 
	* @Description: TODO
	* @param @return    设定文件 
	* @return CookieRememberMeManager    返回类型 
	* @throws
	 */
//	@Bean
//	public CookieRememberMeManager rememberMeManager(){
//		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//		//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位） 
//		cookieRememberMeManager.setCipherKey("#{T(org.apache.shiro.codec.Base64).decode('4AvVhmFLUs0KTA3Kprsdag==')}".getBytes());
//		cookieRememberMeManager.setCookie(rememberMeCookie());
//		return cookieRememberMeManager;
//	}

	/**
	 * <!-- 持久cookie设置 -->
	* @Title: rememberMeCookie 
	* @Description: TODO
	* @param @return    设定文件 
	* @return SimpleCookie    返回类型 
	* @throws
	 */
//	@Bean
//	public  SimpleCookie rememberMeCookie(){
//		SimpleCookie simpleCookie = new SimpleCookie();
//		simpleCookie.setName("sid");
//		simpleCookie.setHttpOnly(true);
//		simpleCookie.setMaxAge(864000);
//		return simpleCookie;
//	}

	/**
	 * 会话Cookie模板
	* @Title: sessionIdCookie 
	* @Description: TODO
	* @param @return    设定文件 
	* @return SimpleCookie    返回类型 
	* @throws
	 */
//	@Bean
//	public  SimpleCookie sessionIdCookie(){
//		SimpleCookie simpleCookie = new SimpleCookie();
//		simpleCookie.setName("sid");
//		simpleCookie.setHttpOnly(true);
//		simpleCookie.setMaxAge(864000);
//		return simpleCookie;
//	}
	

    /**
     * 凭证匹配器/密码匹配器
    * @Title: credentialsMatcher 
    * @Description: TODO
    * @param @return    设定文件 
    * @return HashedCredentialsMatcher    返回类型 
    * @throws
     */
//    @Bean
//    public HashedCredentialsMatcher credentialsMatcher(){
//    	HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//    	//散列算法:这里使用MD5算法;
//    	credentialsMatcher.setHashAlgorithmName("MD5");
//    	//散列的次数，比如散列两次，相当于 md5(md5(""));
//    	credentialsMatcher.setHashIterations(2);
//    	credentialsMatcher.setStoredCredentialsHexEncoded(true);
//		return credentialsMatcher;
//    }

//	/**
//	 * 基于Form表单的身份验证过滤器
//	* @Title: formAuthenticationFilter 
//	* @Description: TODO
//	* @param @return    设定文件 
//	* @return FormAuthenticationFilter    返回类型 
//	* @throws
//	 */
//    @Bean
//    public FormAuthenticationFilter formAuthenticationFilter(){
//    	FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
//    	formAuthenticationFilter.setUsernameParam("username");
//    	formAuthenticationFilter.setPasswordParam("pswd");
//    	// 记住我
//    	formAuthenticationFilter.setRememberMeParam("rememberMe");
//    	formAuthenticationFilter.setLoginUrl("login");
//		return formAuthenticationFilter;
//    }
    /**
     * 自定义拦截器 
    * @Title: sysUserFilter 
    * @Description: TODO
    * @param @return    设定文件 
    * @return SysUserFilter    返回类型 
    * @throws
     */
    @Bean
	public SysUserFilter sysUserFilter(){
		SysUserFilter sysUserFilter = new SysUserFilter();
		return sysUserFilter;
	}

		
	/**
	 * 并发人数登录控制器
	* @Title: kickoutFilter 
	* @Description: TODO
	* @param @return    设定文件 
	* @return KickoutSessionControlFilter    返回类型 
	* @throws
	 */
	@Bean
	public KickoutSessionControlFilter kickoutFilter(){
		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
		// 退出登录方法
		kickoutSessionControlFilter.setKickoutUrl("logout");
		kickoutSessionControlFilter.setSessionManager(sessionManager());
		// 设置缓存,这里用shiro的缓存
		kickoutSessionControlFilter.setCacheManager(cacheManager());
		kickoutSessionControlFilter.setKickoutAfter(false);
		// 最大同时在线人数
		kickoutSessionControlFilter.setMaxSession(1);
		return kickoutSessionControlFilter;
		
	}
	
    

	/**
	 * 生命周期处理器
	* @Title: lifecycleBeanPostProcessor 
	* @Description: TODO
	* @param @return    设定文件 
	* @return LifecycleBeanPostProcessor    返回类型 
	* @throws
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
		return lifecycleBeanPostProcessor;
		
	}
	/*	<!-- 开启shiro注解模式  -->
	<bean
		class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
		depends-on="lifecycleBeanPostProcessor" >
		<property name="proxyTargetClass" value="true" />
	</bean>*/
	/**
	 * 开启shiro注解
	* @Title: getDefaultAdvisorAutoProxyCreator 
	* @Description: TODO
	* @param @return    设定文件 
	* @return DefaultAdvisorAutoProxyCreator    返回类型 
	* @throws
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		// 设置代理到 Service 的实现
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}
	/**
     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持; Controller才能使用@RequiresPermissions
     * 
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager securityManager) {
        logger.info("authorizationAttributeSourceAdvisor()");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    





	
	   /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
