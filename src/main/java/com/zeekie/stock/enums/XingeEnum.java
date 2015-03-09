package com.zeekie.stock.enums;

public enum XingeEnum {
	ELEVING_UP("2", "平仓"), OPEN_APP("3", "打开APP"), CLOSE_APP("4", "打开APP"), USER_RECHARGE_SUCCESS(
			"5", "充值成功"), USER_RECHARGE_FAILURE("6", "充值失败"), PIC_MAIN_UPDATE(
			"7", "更新首页"), PIC_START_UPDATE("8", "更新启动页");

	private String content;

	private String title;

	XingeEnum(String content, String title) {
		this.content = content;
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
