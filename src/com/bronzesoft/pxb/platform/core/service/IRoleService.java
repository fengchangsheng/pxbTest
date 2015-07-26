package com.bronzesoft.pxb.platform.core.service;

import java.util.Set;

import com.bronzesoft.pxb.platform.core.model.User;

public interface IRoleService {
	
	public Set<String> findUrls(String uid);
	
	public User findUser(String username);
	
	public Set<String> findRoleName(String uid);

}
