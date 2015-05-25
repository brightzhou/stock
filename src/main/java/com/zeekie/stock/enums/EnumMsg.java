package com.zeekie.stock.enums;

import org.apache.commons.lang.StringUtils;

public enum EnumMsg {

	TOKEN_ERROR("80110101", "无效的token"), PARAM_EMPTY("80110102", "缺少参数"), VISIT_EXCEED(
			"80110103", "token已经过期"), ERROR_IN("80110104", "服务器异常"), ERROR_PARAM(
			"80110105", "错误的参数");

	private String code;

	private String msg;

	private EnumMsg(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
}
