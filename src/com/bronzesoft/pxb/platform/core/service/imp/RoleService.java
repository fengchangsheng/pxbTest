package com.bronzesoft.pxb.platform.core.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.bronzesoft.pxb.platform.core.dao.IDao;
import com.bronzesoft.pxb.platform.core.model.Authority;
import com.bronzesoft.pxb.platform.core.model.Role;
import com.bronzesoft.pxb.platform.core.model.User;
import com.bronzesoft.pxb.platform.core.service.IRoleService;

public class RoleService implements IRoleService{

	@Resource(name = "dao")
	private IDao dao;
	
	@Override
	@Transactional
	public Set<String> findUrls(String uid) {
		Set<String> urls = new HashSet<String>();
		String sql = "select rid from t_user_role where uid = ?";
		String sql2 = "select authid from t_role_auth where rid = ?";
		List rolelist = dao.findBySQl(sql, new String[]{uid});
		for (int i = 0; i < rolelist.size(); i++) {
			String rid = (String) rolelist.get(i);//查找角色id
			List<Authority> authList = dao.findBySQl(sql2, new String[]{rid});//根据角色id查找权限id
			for (int j = 0; j < authList.size(); j++) {
				Authority authority =  (Authority) dao.getObject(Authority.class, authList.get(i));
				urls.add(authority.getUrl());
			}
		}
		return urls;
	}

	@Override
	@SuppressWarnings("unchecked")
	public User findUser(String username) {
		List<User> userList = dao.query("from User where username=?", new String[]{username});
		User user = userList.get(0);
		return user;
	}

	@Override
	@Transactional
	public Set<String> findRoleName(String uid) {
		Set<String> roleNames = new HashSet<String>();
		String sql = "select rid from t_user_role where uid = ?";
		List rolelist = dao.findBySQl(sql, new String[]{uid});
		for (int i = 0; i < rolelist.size(); i++) {
			String rid = (String) rolelist.get(i);//查找角色id
			Role role = (Role) dao.getObject(Role.class, rid);
			roleNames.add(role.getName());
		}
		return roleNames;
	}
	
	

}
