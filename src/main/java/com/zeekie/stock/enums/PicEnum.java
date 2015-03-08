package com.zeekie.stock.enums;

public enum PicEnum {

	MAINPAGE("main", "main.jpg"), STARTPAGE("start", "start.jpg"), APK("apk",
			"HaHaBao.apk"), VERSION("version", "update_version.txt");

	private String type;

	private String page;

	PicEnum(String type, String page) {
		this.type = type;
		this.page = page;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

}
