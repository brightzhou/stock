package com.zeekie.stock.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 委托状态
 * 
 * @author zeekie
 *
 */
public enum AmentrustStatusEnum {

	STATUS_WB("1", "未报"), STATUS_YB("4", "已报"), STATUS_FD("5", "废单"), STATUS_BC(
			"6", "部成"), STATUS_WBYC("7", "已成"), STATUS_BCHE("8", "部撤"), STATUS_YCHE(
			"9", "已撤"), STATUS_DCHE("a", "待撤"), STATUS_WCHE("A", "未撤"), STATUS_ZCHE(
			"C", "正撤"), STATUS_CHER("D", "撤认"), STATUS_CF("E", "撤废");

	private String status;

	private String msg;

	private AmentrustStatusEnum(String code, String msg) {
		this.status = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return status;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.status = code;
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
	public static String getDesc(String status) {
		for (AmentrustStatusEnum f : AmentrustStatusEnum.values()) {
			if (StringUtils.equals(f.status, status)) {
				return f.msg;
			}
		}
		return "";
	}
}
