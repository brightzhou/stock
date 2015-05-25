package com.zeekie.stock.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 委托状态
 * 
 * @author zeekie
 *
 */
public enum EntrustDirectionEnum {

	DIRECION_STOCK_BUY("1", "股票买入"), DIRECION_STOCK_SELL("2", "股票卖出");

	private String type;

	private String msg;

	private EntrustDirectionEnum(String code, String msg) {
		this.type = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return type;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.type = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the desc
	 */
	public static String getDesc(String type) {
		for (EntrustDirectionEnum f : EntrustDirectionEnum.values()) {
			if (StringUtils.equals(f.type, type)) {
				return f.msg;
			}
		}
		return "";
	}
}
