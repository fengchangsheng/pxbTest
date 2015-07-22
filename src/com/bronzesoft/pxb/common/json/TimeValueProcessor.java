package com.bronzesoft.pxb.common.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class TimeValueProcessor implements JsonValueProcessor {

	public Object processArrayValue(Object value, JsonConfig cfg) {
		return null;
	}

	public Object processObjectValue(String key, Object value, JsonConfig cfg) {
		if (value == null)
			return "";
		if (value instanceof Date) {
			String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.format((Date) value);
			return str;
		}
		return value.toString();
	}

}
