package com.zeekie.stock.entity;

public class FundAccountDO {

	private String id;

	private String text;

	public FundAccountDO() {
		// TODO Auto-generated constructor stub
	}

	public FundAccountDO(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
