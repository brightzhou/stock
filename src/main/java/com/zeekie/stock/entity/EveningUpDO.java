package com.zeekie.stock.entity;

public class EveningUpDO {

	private String id;

	private String nickname;

	private String StopLine;

	private String warnLine;

	public EveningUpDO() {
		// TODO Auto-generated constructor stub
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
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the stopLine
	 */
	public String getStopLine() {
		return StopLine;
	}

	/**
	 * @param stopLine
	 *            the stopLine to set
	 */
	public void setStopLine(String stopLine) {
		StopLine = stopLine;
	}

	/**
	 * @return the warnLine
	 */
	public String getWarnLine() {
		return warnLine;
	}

	/**
	 * @param warnLine
	 *            the warnLine to set
	 */
	public void setWarnLine(String warnLine) {
		this.warnLine = warnLine;
	}

}
