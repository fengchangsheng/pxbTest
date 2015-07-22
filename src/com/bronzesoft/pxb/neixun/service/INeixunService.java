package com.bronzesoft.pxb.neixun.service;

import java.util.List;

import com.bronzesoft.pxb.neixun.model.NeixunProject;

public interface INeixunService {

	/**
	 * 保存内训项目
	 * 
	 * @param project 内训对象
	 * @return
	 */
	public String saveNeixunProject(NeixunProject project);
	
	/**
	 * 查询内训项目
	 * 
	 * @param searchCondition 查询条件，格式如下：
	 * @return
	 */
	public List<NeixunProject> searchNeixunProject(String searchCondition);
	
}
