package com.bronzesoft.pxb.neixun.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bronzesoft.pxb.common.Jsons;
import com.bronzesoft.pxb.neixun.model.NeixunProject;
import com.bronzesoft.pxb.neixun.service.INeixunService;
import com.bronzesoft.pxb.platform.core.controller.BaseController;
@Controller
@RequestMapping("/neixun")
public class NeixunController extends BaseController {

	@Resource(name = "neixunService")
	private INeixunService neixunService;

	@ResponseBody
	@RequestMapping(value="/save")
	public String saveProject() {
		try {
			neixunService.saveNeixunProject(null);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":saveProject()", e);
		}
		return "";
	}

	@ResponseBody
	@RequestMapping(value="/search")
	public String searchProject() {
		try {
			List<NeixunProject> projects = neixunService.searchNeixunProject(null);
			return JSONArray.fromObject(projects, Jsons.getTimeConfig()).toString();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":searchProject()", e);
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value="/search2")
	public ModelAndView searchProject2() {
		try {
			
			List<NeixunProject> projects = neixunService.searchNeixunProject(null);
//			ModelAndView  mv = new ModelAndView();
//			mv.addObject("projects", projects);
//			mv.setViewName("test");
//			return mv;
			return new ModelAndView("test");
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":searchProject()", e);
		}
		return null;
	}
	
	
	@RequestMapping(value="/search3")
	public String searchProject3(ModelMap mm) {
		try {
			List<NeixunProject> projects = neixunService.searchNeixunProject(null);
			mm.addAttribute("projects", projects);
			return "test";
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":searchProject()", e);
		}
		return "";
	}

}
