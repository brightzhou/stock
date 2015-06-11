package com.zeekie.stock.util;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.zeekie.stock.enums.PicEnum;

/**
 * @author zeekie
 * @since 1.0
 */
public class StringUtil {

	public static boolean isEquals(String a, String b) {
		if (a == null) {
			return b == null;
		}
		return a.equals(b);
	}

	public static boolean equalsIgnoreCase(String a, String b) {
		if (a == null) {
			return b == null;
		}
		return a.equalsIgnoreCase(b);
	}

	public static boolean isEmpty(String a) {
		return a == null || a.length() == 0;
	}

	public static boolean isNotEmpty(String a) {
		return a != null && a.length() > 0;
	}

	public static boolean isBlank(String a) {
		return a == null || a.trim().length() == 0;
	}

	public static boolean isNotBlank(String a) {
		return a != null && a.trim().length() > 0;
	}

	public static boolean contains(String t, String[] arr) {
		for (String one : arr) {
			if (isEquals(t, one))
				return true;
		}
		return false;
	}

	public static String trim(String t, char c) {
		if (isBlank(t)) {
			return t;
		}
		StringBuilder sb = new StringBuilder(t);
		while (sb.length() > 0 && sb.charAt(0) == c) {
			sb.deleteCharAt(0);
		}
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String trimTrailing(String t, char c) {
		if (isBlank(t)) {
			return t;
		}

		StringBuilder sb = new StringBuilder(t);
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();

	}

	/**
	 * 生成唯一ID
	 * 
	 * @return
	 */
	public static String generateUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 生成活动下发的原始文件名�?
	 * 
	 * @return
	 */
	public static String genOrgFilename() {
		return "upload_result_"
				+ DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")
				+ ".txt";
	}

	/**
	 * 生成错误信息
	 * 
	 * @return
	 */
	public static String getErrorInfo(String param) {
		String result = "";
		String year = Calendar.getInstance().get(Calendar.YEAR) + "";
		String month = Calendar.getInstance().get(Calendar.MONTH) + 1 + "";
		String day = Calendar.getInstance().get(Calendar.DATE) + "";
		result = param.replaceAll("Y", year).replaceAll("M", month)
				.replaceAll("D", day);
		return result + "{";

	}

	/**
	 * 返回第几个字符串
	 * 
	 * @param param
	 * @param n
	 * @return
	 */
	public static String getStr(String param, int n) {
		String[] str = null;
		if (isNotBlank(param)) {
			str = param.split("_");
			return str[n];
		}
		return "";
	}

	/**
	 * 返回第几个字符串
	 * 
	 * @param str
	 * @param n
	 * @return
	 */
	public static String returnStr(String[] str, int n) {
		return null != str ? str[n] : "";
	}

	public static String returnRandom(String num) {

		String random = UUID.randomUUID().toString();

		if (StringUtils.isNotBlank(num)) {
			int pos = Integer.parseInt(num);
			random = random.substring(0, pos);
		} else {
			random = random.substring(0, 4);
		}
		return random;

	}

	public static String genRandomNum(int pwd_len) {
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止 生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	public static String genRandomFive(int pwd_len) {
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '1', '2', '3', '4', '5' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止 生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * 生成验证码
	 * 
	 * @param 4 几位
	 * @return
	 */
	public static String getVerifyCode() {
		int max = 9999;
		int min = 1;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s + "";
	}

	public static String returnNumber(String s) {
		int i;
		int pos = 0;
		Float a = new Float(s);
		String b = (new Float(a.floatValue())).toString();
		if (b.indexOf(".") != -1) {
			i = b.indexOf(".") + 1;
			for (; i < b.length(); i++) {
				if (b.charAt(i) != '0') {
					pos = i + 1;
					break;
				}
			}
		}
		if (0 == pos) {
			System.out.println(a);
		} else {
			System.out.println(b.substring(0, pos));
		}

		return "";
	}

	/**
	 * Float 保留两位小数
	 * 
	 * @return
	 */
	public static Float keepTwoDecimalFloat(Float f) {
		if (null == f)
			return 0f;
		DecimalFormat df = new DecimalFormat("#.00");
		df.setMaximumFractionDigits(2);
		df.setRoundingMode(RoundingMode.HALF_UP);
		// df.applyPattern(".00");
		Float ss = Float.parseFloat(df.format(f));
		return ss;
	}

	public static String keepThreeDot(Float f) {
		if (null == f)
			return "";
		DecimalFormat df = new DecimalFormat(".###");
		df.setMaximumFractionDigits(3);
		df.setRoundingMode(RoundingMode.HALF_UP);
		df.applyPattern(".###");
		return subZeroAndDot(Float.parseFloat(df.format(f)));
	}

	public static String keepFourDot(Float f) {
		if (null == f)
			return "";
		DecimalFormat df = new DecimalFormat(".####");
		df.setMaximumFractionDigits(3);
		df.setRoundingMode(RoundingMode.HALF_UP);
		df.applyPattern(".####");
		return subZeroAndDot(Float.parseFloat(df.format(f)));
	}

	public static String subZeroAndDot(Float s) {

		String result = s + "";
		if (result.indexOf(".") > 0) {
			result = result.replaceAll("0+?$", "");// 去掉多余的0
			result = result.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return result;
	}
	
	public static JSONArray getCalendar(String yearMonth) {

		String[] ret = yearMonth.split("-");

		Calendar rightNow = Calendar.getInstance();
		// 如果写成年月日的形式的话，要写小d，如："yyyy/MM/dd"
		int year = Integer.parseInt(ret[0]);
		int month = Integer.parseInt(ret[1]);

		// int startMonth = month;
		// int endMonth = month + 2;
		// int newYear = year;

		// Map<String, JSONArray> result = new HashMap<String, JSONArray>();
		try {
			// if (month == 12) {
			// endMonth = 2;
			// newYear = year + 1;
			// startMonth = 1;
			// setArray(rightNow, year, month, result);
			// }
			// for (int i = startMonth; i <= endMonth; i++) {
			return setArray(rightNow, year, month);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static JSONArray setArray(Calendar rightNow, int year, int month)
			throws Exception {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
		String yearMonth = year + "-" + month;
		rightNow.setTime(simpleDate.parse(year + "/" + month));
		int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
		JSONArray array = new JSONArray();
		for (int i = 1; i <= days; i++) {
			JSONObject jo = new JSONObject();
			String week = dayForWeek(yearMonth + "-" + i);
			if (StringUtils.equals("星期日", week)
					|| StringUtils.equals("星期六", week)) {
				continue;
			}
			String j = (i < 10) ? "0" + i : i + "";
			jo.put("id", j);
			jo.put("text", week + "(" + i + "号)");
			array.add(jo);
		}
		// result.put(yearMonth + "", array);
		return array;
	}

	/**
	 * 判断当前日期是星期几<br>
	 * <br>
	 * 
	 * @param pTime
	 *            修要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	public static String dayForWeek(String pTime) throws Exception {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayForWeek < 0) {
			dayForWeek = 0;
		}
		return weekDays[dayForWeek];
	}

	public static String getResult(String param) {
		if (StringUtils.isNotBlank(param)) {
			if (param.indexOf("&") != -1) {
				return param.split("&")[1].split("=")[1];
			} else {
				return param;
			}
		}
		return "";
	}

	/**
	 * 根据页面传递的类别生成不同的页面名称
	 * 
	 * @param type
	 * @return
	 */
	public static String getFileName(String type) {

		if (StringUtils.equals(PicEnum.MAINPAGE.getType(), type)) {
			return PicEnum.MAINPAGE.getPage();
		} else if (StringUtils.equals(PicEnum.STARTPAGE.getType(), type)) {
			return PicEnum.STARTPAGE.getPage();
		} else if (StringUtils.equals(PicEnum.VERSION.getType(), type)) {
			return PicEnum.VERSION.getPage();
		} else if (StringUtils.equals(PicEnum.APK.getType(), type)) {
			return PicEnum.APK.getPage();
		} else if (StringUtils.equals(PicEnum.PIC_VERSION.getType(), type)) {
			return PicEnum.PIC_VERSION.getPage();
		} else {
			return "";
		}
	}

	/**
	 * 数字转
	 * 
	 * @param param
	 * @return
	 */
	public static String FloatToPercentum(Float param) {
		DecimalFormat df = new DecimalFormat("0.00%");
		String r = df.format(param);
		return r;
	}

	public static boolean compareNum(Float param1, Float param2) {
		return (param1 > param2);
	}

	/**
	 * 获取当年天数
	 * 
	 * @return
	 */
	public static int getCurrentYearDays() {
		Calendar c = GregorianCalendar.getInstance();
		return c.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

}
