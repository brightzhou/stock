package com.zeekie.stock.entity;

public class GuessHistoryDO {

	private String nickname;

	private String code;

	private String name;

	private String guessStatus;

	private String actualStatus;

	private String profitNum;

	private String purchaseNum;

	private String purchaseTime;

	private String correctNum;

	private String failNum;

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
	 * @return the correctNum
	 */
	public String getCorrectNum() {
		return correctNum;
	}

	/**
	 * @param correctNum
	 *            the correctNum to set
	 */
	public void setCorrectNum(String correctNum) {
		this.correctNum = correctNum;
	}

	/**
	 * @return the failNum
	 */
	public String getFailNum() {
		return failNum;
	}

	/**
	 * @param failNum
	 *            the failNum to set
	 */
	public void setFailNum(String failNum) {
		this.failNum = failNum;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the guessStatus
	 */
	public String getGuessStatus() {
		return guessStatus;
	}

	/**
	 * @param guessStatus
	 *            the guessStatus to set
	 */
	public void setGuessStatus(String guessStatus) {
		this.guessStatus = guessStatus;
	}

	/**
	 * @return the actualStatus
	 */
	public String getActualStatus() {
		return actualStatus;
	}

	/**
	 * @param actualStatus
	 *            the actualStatus to set
	 */
	public void setActualStatus(String actualStatus) {
		this.actualStatus = actualStatus;
	}

	/**
	 * @return the profitNum
	 */
	public String getProfitNum() {
		return profitNum;
	}

	/**
	 * @param profitNum
	 *            the profitNum to set
	 */
	public void setProfitNum(String profitNum) {
		this.profitNum = profitNum;
	}

	/**
	 * @return the purchaseNum
	 */
	public String getPurchaseNum() {
		return purchaseNum;
	}

	/**
	 * @param purchaseNum
	 *            the purchaseNum to set
	 */
	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	/**
	 * @return the purchaseTime
	 */
	public String getPurchaseTime() {
		return purchaseTime;
	}

	/**
	 * @param purchaseTime
	 *            the purchaseTime to set
	 */
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

}
