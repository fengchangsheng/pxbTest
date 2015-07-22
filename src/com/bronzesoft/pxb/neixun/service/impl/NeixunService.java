package com.bronzesoft.pxb.neixun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.bronzesoft.pxb.common.Strings;
import com.bronzesoft.pxb.neixun.model.NeixunProject;
import com.bronzesoft.pxb.neixun.service.INeixunService;
import com.bronzesoft.pxb.platform.core.dao.IDao;

public class NeixunService implements INeixunService {

	@Resource(name = "dao")
	private IDao dao;
	
	@Transactional
	public String saveNeixunProject(NeixunProject project) {
		if (project != null) {
			project.setId(Strings.getID());
			dao.saveObject(project);
			return project.getId();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<NeixunProject> searchNeixunProject(String searchCondition) {
		List<NeixunProject> projects = dao.query("from NeixunProject", null);
		return projects;
	}
	
}
