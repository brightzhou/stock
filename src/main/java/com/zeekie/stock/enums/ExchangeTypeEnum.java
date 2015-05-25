package com.zeekie.stock.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 交易市场
 * 
 * @author zeekie
 *
 */
public enum ExchangeTypeEnum {

	EXCHAGE_TYPE_SH("1", "上交所"), EXCHAGE_TYPE_S("2", "深交所");

	private String type;

	private String msg;

	private ExchangeTypeEnum(String code, String msg) {
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
		for (ExchangeTypeEnum f : ExchangeTypeEnum.values()) {
			if (StringUtils.equals(f.type, type)) {
				return f.msg;
			}
		}
		return "";
	}
}
