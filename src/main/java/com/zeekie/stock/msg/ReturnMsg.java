package com.zeekie.stock.msg;

public class ReturnMsg {

	private String code;

	private String msg;

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

	public ReturnMsg(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

}
