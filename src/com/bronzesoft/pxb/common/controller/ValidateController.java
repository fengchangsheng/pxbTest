package com.bronzesoft.pxb.common.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bronzesoft.pxb.common.validate.JavaBean;
/**
 * @author Lucare.Feng
 * 数据校验
 * 2015年7月15日
 */
@Controller
public class ValidateController {

	@RequestMapping("/validate")
	public @ResponseBody String validate(@Valid JavaBean bean, BindingResult result) {
		if (result.hasErrors()) {
			return "Object has validation errors";
		} else {
			return "No errors";
		}
	}

}
