package com.zeekie.stock.enums;

import org.apache.commons.lang.StringUtils;

public enum RealStatusEnum {

	REALSTATUS_DEAL("0", "成交"), REALSTATUS_DISCARD("2", "废单"), REALSTATUS_SURE(
			"4", "确认");

	private String status;

	private String word;

	private RealStatusEnum(String status, String word) {
		this.status = status;
		this.word = word;
	}

	/**
	 * @return the desc
	 */
	public static String getDesc(String type) {
		for (RealStatusEnum f : RealStatusEnum.values()) {
			if (StringUtils.equals(f.status, type)) {
				return f.word;
			}
		}
		return "";
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
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

}
