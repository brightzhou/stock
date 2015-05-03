package com.zeekie.stock.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class FloatJsonValueProcessor implements JsonValueProcessor {

	public FloatJsonValueProcessor() {

	}

	/**
	 * 处理数组类型
	 */
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {

		if (value instanceof float[]) {

			String[] obj = {};

			float[] nums = (float[]) value;

			for (int i = 0; i < nums.length; i++) {
				obj[i] = roundHalfUp(nums[i]);
			}

			return obj;
		} else if (value instanceof List) {

			List result = (List) value;
			for (int i = 0; i <= result.size() - 1; i++) {
				Object obj = result.get(i);
				result.set(i, processObjectValue("", obj, jsonConfig));
			}
		} else {
			value = processObjectValue("", value, jsonConfig);
		}

		return value;
	}

	/**
	 * 处理单个对象
	 */
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {

		if (value instanceof Float) {
			return roundHalfUp((Float) value);
		}

		return value;
	}

	/**
	 * 四舍五入。
	 * 
	 * @param number
	 *            数值
	 * @return 舍入后的数值
	 * @see java.text.RoundingMode.HALF_UP
	 */
	public String roundHalfUp(double number, int frac) {
		NumberFormat fmt = NumberFormat.getInstance(Locale.CHINA);
		// fmt.setMaximumFractionDigits(frac);
		// fmt.setRoundingMode(RoundingMode.HALF_UP);
		DecimalFormat df = (DecimalFormat) fmt;
		df.applyPattern("#,#######.###");
		df.setMaximumFractionDigits(frac);
		df.setRoundingMode(RoundingMode.HALF_UP);
		return fmt.format(number);
	}

	public static String roundHalfUp(Float f) {
		if (null == f)
			return "";
		DecimalFormat df = new DecimalFormat("#.00");
		df.setMaximumFractionDigits(3);
		df.setRoundingMode(RoundingMode.HALF_UP);
		Float ss = Float.parseFloat(df.format(f));
		return String.valueOf(ss);
	}

}
