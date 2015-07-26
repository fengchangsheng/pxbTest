package com.bronzesoft.pxb.platform.core.service;


import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Service;

import com.bronzesoft.pxb.platform.core.model.User;



@Service("monitorRealm")
public class MonitorRealm extends AuthorizingRealm {
	/*
	 * @Autowired UserService userService;
	 * 
	 * @Autowired RoleService roleService;
	 * 
	 * @Autowired LoginLogService loginLogService;
	 */

	@Resource(name = "roleService")
	private IRoleService roleService;
	
	public MonitorRealm() {
		super();

	}

	//授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		/* 这里编写授权代码 */
		Object ob = principals.fromRealm(getName()).iterator().next(); 
		String userName = (String) ob; 
		User user = roleService.findUser(userName);
		Set<String> roleNames = roleService.findRoleName(user.getId());
		Set<String> permissions = roleService.findUrls(user.getId());
	    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
	    info.setStringPermissions(permissions);
	    return info;
	}

	  //登录信息和用户验证信息验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		/* 这里编写认证代码 */
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = roleService.findUser(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getUserName(),
					user.getPassword(), getName());
		}
		return null;

	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	

}
