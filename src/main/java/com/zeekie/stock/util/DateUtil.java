package com.zeekie.stock.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zeekie
 * @version 1.0, 2013/04/16
 */
public class DateUtil {

	private static final String[] DATE_FORMATS = { "yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd", "yyyy/MM/dd" };

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd
	 */
	public static final int DEFAULT = 0;
	public static final int YM = 1;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy-MM-dd
	 * 
	 */
	public static final int YMR_SLASH = 11;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMMdd
	 * 
	 */
	public static final int NO_SLASH = 2;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMM
	 * 
	 */
	public static final int YM_NO_SLASH = 3;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm:ss
	 * 
	 */
	public static final int DATE_TIME = 4;

	/**
	 * 变量：日期格式化类型 - 格式:yyyyMMddHHmmss
	 * 
	 */
	public static final int DATE_TIME_NO_SLASH = 5;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm
	 * 
	 */
	public static final int DATE_HM = 6;

	/**
	 * 变量：日期格式化类型 - 格式:HH:mm:ss
	 * 
	 */
	public static final int TIME = 7;

	/**
	 * 变量：日期格式化类型 - 格式:HH:mm
	 * 
	 */
	public static final int HM = 8;

	/**
	 * 变量：日期格式化类型 - 格式:HHmmss
	 * 
	 */
	public static final int LONG_TIME = 9;
	/**
	 * 变量：日期格式化类型 - 格式:HHmm
	 * 
	 */

	public static final int SHORT_TIME = 10;

	/**
	 * 变量：日期格式化类型 - 格式:yyyy-MM-dd HH:mm:ss
	 */
	public static final int DATE_TIME_LINE = 12;

	public static String dateToStr(Date date, int type) {
		switch (type) {
		case DEFAULT:
			return dateToStr(date);
		case YM:
			return dateToStr(date, "yyyy/MM");
		case NO_SLASH:
			return dateToStr(date, "yyyyMMdd");
		case YMR_SLASH:
			return dateToStr(date, "yyyy-MM-dd");
		case YM_NO_SLASH:
			return dateToStr(date, "yyyyMM");
		case DATE_TIME:
			return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
		case DATE_TIME_NO_SLASH:
			return dateToStr(date, "yyyyMMddHHmmss");
		case DATE_HM:
			return dateToStr(date, "yyyy/MM/dd HH:mm");
		case TIME:
			return dateToStr(date, "HH:mm:ss");
		case HM:
			return dateToStr(date, "HH:mm");
		case LONG_TIME:
			return dateToStr(date, "HHmmss");
		case SHORT_TIME:
			return dateToStr(date, "HHmm");
		case DATE_TIME_LINE:
			return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
		default:
			throw new IllegalArgumentException("Type undefined : " + type);
		}
	}

	public static String dateToStr(Date date, String pattern) {
		if (date == null || date.equals(""))
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static String dateToStr(Date date) {
		return dateToStr(date, "yyyy/MM/dd");
	}

	/**
	 * 在不确定日期格式的情况下，依次解析，如成功就返回
	 * 
	 * @param date
	 *            日期字符�?
	 * @return
	 */
	public static Date toDate(String date) {

		SimpleDateFormat dateFormat = null;

		for (String format : DATE_FORMATS) {
			if (dateFormat == null) {
				dateFormat = new SimpleDateFormat(format);
			} else {
				dateFormat.applyPattern(format);
			}

			try {
				return dateFormat.parse(date);
			} catch (Exception ex) {
				continue;
			}
		}

		return null;
	}

	/**
	 * 格式化日�?
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static boolean compareDate(String start, String end)
			throws ParseException {

		Date date = new Date();
		int pos = end.indexOf("+");
		Date endDate = date;
		if (pos != -1) {
			Calendar cal = Calendar.getInstance();
			String time = end.substring(0, pos).trim();
			int days = Integer.valueOf(end.substring(pos + 1).trim());
			cal.setTime(date);
			cal.add(Calendar.DATE, days);
			end = time;
			endDate = cal.getTime();
		}

		String now = formatDate(date, DATE_FORMATS[0]);
		String beginTime = formatDate(date, DATE_FORMATS[1]) + " " + start;
		String endTime = formatDate(endDate, DATE_FORMATS[1]) + " " + end;
		try {
			Date d1 = df.parse(now);
			Date d2 = df.parse(endTime);
			Date d3 = df.parse(beginTime);
			return ((d2.getTime() - d1.getTime()) > 0l)
					&& ((d1.getTime() - d3.getTime()) > 0l);
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
		}
	}
	
	public static boolean compareDate(String start, String end,String nowdate)
			throws ParseException {

		Date date = new Date(nowdate);
		int pos = end.indexOf("+");
		Date endDate = date;
		if (pos != -1) {
			Calendar cal = Calendar.getInstance();
			String time = end.substring(0, pos).trim();
			int days = Integer.valueOf(end.substring(pos + 1).trim());
			cal.setTime(date);
			cal.add(Calendar.DATE, days);
			end = time;
			endDate = cal.getTime();
		}

		String now = formatDate(date, DATE_FORMATS[0]);
		String beginTime = formatDate(date, DATE_FORMATS[1]) + " " + start;
		String endTime = formatDate(endDate, DATE_FORMATS[1]) + " " + end;
		try {
			Date d1 = df.parse(now);
			Date d2 = df.parse(endTime);
			Date d3 = df.parse(beginTime);
			return ((d2.getTime() - d1.getTime()) > 0l)
					&& ((d1.getTime() - d3.getTime()) > 0l);
		} catch (ParseException e) {
			throw new ParseException(e.getMessage(), 0);
		}
	}

}
