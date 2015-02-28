package com.zeekie.stock.enums;

public enum UserInfo {

	LOGINSTATUS("1", "该用户已经登陆");

	private String status;

	private String tip;

	private UserInfo(String status, String tip) {
		this.status = status;
		this.tip = tip;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the tip
	 */
	public String getTip() {
		return tip;
	}

	/**
	 * @param tip
	 *            the tip to set
	 */
	public void setTip(String tip) {
		this.tip = tip;
	}

}
