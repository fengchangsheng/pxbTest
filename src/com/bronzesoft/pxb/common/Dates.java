package com.bronzesoft.pxb.common;

import java.util.Calendar;
import java.util.Date;

public class Dates {

	/**
	 * 获取指定时间，增加XX秒后的时间
	 * 
	 * @param date 指定时间
	 * @param senond 秒数
	 * @return
	 */
	public static Date getAddSecondDate(Date date, int senond) {
		if (date == null) {
			return date;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, senond);

		return cal.getTime();
	}

	/**
	 * 获取两个日期差值，单位为天
	 * 
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return
	 */
	public static long getSubDays(Date start, Date end) {
		if (start == null || end == null) {
			return 0;
		}
		return (start.getTime() - end.getTime()) / 1000 / 60 / 60 / 24;
	}
	
	/**
	 * 获取两个日期差值，单位为秒
	 * 
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return
	 */
	public static long getSubSenonds(Date start, Date end) {
		if (start == null || end == null) {
			return 0;
		}
		return (start.getTime() - end.getTime()) / 1000;
	}

}
