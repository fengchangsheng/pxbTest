package com.bronzesoft.pxb.common.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bronzesoft.pxb.common.convert.JavaBean;
import com.bronzesoft.pxb.common.convert.MaskFormat;
import com.bronzesoft.pxb.common.convert.SocialSecurityNumber;

/**
 * @author Lucare.Feng
 * 类型转换  数据解析
 * 2015年7月15日
 */
@Controller
@RequestMapping("/convert")
public class ConverController {
	
	@RequestMapping("primitive")
	public @ResponseBody String primitive(@RequestParam Integer value) {
		return "Converted primitive " + value;
	}

	// requires Joda-Time on the classpath              表示请求的日期必须满足的类型    不然则报400
	@RequestMapping("date/{value}")//pattern="yyyy-MM-dd"/iso=ISO.DATE均能识别类似2015-07-15类型的字符串  并转换为日期
	public @ResponseBody String date(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date value) {
		return "Converted date " + value+": "+new SimpleDateFormat("yyyyMMdd").format(value);
	}

	@RequestMapping("collection")
	public @ResponseBody String collection(@RequestParam Collection<Integer> values) {
		return "Converted collection " + values;
	}

	@RequestMapping("formattedCollection")
	public @ResponseBody String formattedCollection(@RequestParam @DateTimeFormat(iso=ISO.DATE) Collection<Date> values) {
		return "Converted formatted collection " + values;
	}

	@RequestMapping("bean")
	public @ResponseBody String bean(JavaBean bean) {
		return "Converted " + bean;
	}

	@RequestMapping("value")
	public @ResponseBody String valueObject(@RequestParam SocialSecurityNumber value) {
		return "Converted value object " + value;
	}

	@RequestMapping("custom")
	public @ResponseBody String customConverter(@RequestParam @MaskFormat("###-##-####") String value) {
		return "Converted '" + value + "' with a custom converter";
	}

}
