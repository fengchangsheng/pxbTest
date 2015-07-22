package com.bronzesoft.pxb.common;

public class Strings {

	/**
	 * 判断字符是否为“”或者null
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}
	
	public static String getID() {
		return java.util.UUID.randomUUID().toString();
	}
	
}
