package com.bronzesoft.pxb.platform.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_user")
public class User extends BaseModel {
	
	@Column(name = "usercode")
	private String usercode;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
