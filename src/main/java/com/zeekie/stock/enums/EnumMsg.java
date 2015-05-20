package com.zeekie.stock.enums;

public enum EnumMsg {

	TOKEN_ERROR("80110101", "token错误"), PARAM_EMPTY("80110102", "缺少参数"), VISIT_EXCEED(
			"80110103", "访问超时"), ERROR_IN("80110104", "服务器异常");

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
