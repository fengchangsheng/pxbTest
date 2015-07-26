package com.bronzesoft.pxb.platform.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_authority")
public class Authority extends BaseModel{
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "des")
	private String des;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
	
	

}
