package com.zeekie.stock.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat antherFormat = new SimpleDateFormat(
			"yyyyMM");

	public static String getFirstDayOfYear(String startDate) {

		if (StringUtils.isEmpty(startDate)) {
			// 获取前月的第�?��
			Calendar cal_1 = Calendar.getInstance();// 获取当前日期
			cal_1.set(Calendar.DAY_OF_YEAR, 1);// 本年�?��
			return format.format(cal_1.getTime());
		}
		return startDate;
	}

	public static String getLastDayOfMonth(String endDate) {
		if (StringUtils.isEmpty(endDate)) {
			Calendar cale = Calendar.getInstance();
			cale.add(Calendar.MONTH, 1);
			cale.set(Calendar.DAY_OF_MONTH, 0);// 设置�?�?当前日期既为本月第一�?
			return format.format(cale.getTime());
		}
		return endDate;
	}

	public static String getYearMonth(String ym) {
		if (StringUtils.isEmpty(ym)) {
			return antherFormat.format(new Date());
		}
		String[] result = ym.split("-");
		String month = result[1];

		if (month.length() < 2) {
			month = "0" + month;
		}

		if (result.length <= 3) {
			return result[0] + month;
		}
		return ym;
	}
	 /**
     *  获取跨天时间
     * @param day 天数
     * @return
     */
	public static Date getInterDay(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();

	}
	/**
	 * 格式化时间
	 * @param date 
	 * @param format  比如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDate(Date date,String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return  dateFormat.format(date);
	}

}
