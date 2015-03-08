package com.zeekie.stock.enums;

public enum XingeEnum {
	
	PIC_UPDATE("6","更新图片");
	
	private String content;
	
	private String title;
	
	XingeEnum(String content,String title){
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
	 * @param content the content to set
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
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
