package com.bronzesoft.pxb.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Lucare.Feng
 * 异步请求
 * 2015年7月15日
 */
@Controller
@RequestMapping("/asy")
public class AsyController {

	@ResponseBody()
	@RequestMapping()
	public String returnsome(@RequestParam String test){
		System.out.println("================================>>>>"+test);
		return "ok";
	}
}
