package com.bronzesoft.pxb.platform.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_role")
public class Role extends BaseModel{

	@Column(name = "name")
	private String name;
	
	@Column(name = "des")
	private String des;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	
}
