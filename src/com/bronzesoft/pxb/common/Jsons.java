package com.bronzesoft.pxb.common;

import java.util.Date;

import net.sf.json.JsonConfig;

import com.bronzesoft.pxb.common.json.DateValueProcessor;
import com.bronzesoft.pxb.common.json.TimeValueProcessor;

public class Jsons {

	public static JsonConfig getTimeConfig() {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Date.class, new TimeValueProcessor());
		return cfg;
	}
	
	public static JsonConfig getDateConfig() {
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Date.class, new DateValueProcessor());
		return cfg;
	}
	
}
