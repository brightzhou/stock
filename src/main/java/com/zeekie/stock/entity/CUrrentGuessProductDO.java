package com.zeekie.stock.entity;

public class CUrrentGuessProductDO {

	/**
	 * 产品代码
	 */
	private String bidCode;

	/**
	 * 是否已经结束
	 */
	private String status;

	/**
	 * 猜涨的人数
	 */
	private Float risePercent;

	/**
	 * 猜跌的人数
	 */
	private Float failPercent;

	/**
	 * 当日竞猜名称
	 */
	private String name;

	/**
	 * 每次需要的哈哈币个数
	 */
	private String perNum;

	/**
	 * 是否已经竞猜过了 Y/N
	 */
	private String isGuessed;

	/**
	 * 竞猜结果
	 */
	private String guessResult;

	/**
	 * @return the bidCode
	 */
	public String getBidCode() {
		return bidCode;
	}

	/**
	 * @param bidCode
	 *            the bidCode to set
	 */
	public void setBidCode(String bidCode) {
		this.bidCode = bidCode;
	}

	/**
	 * @return the guessResult
	 */
	public String getGuessResult() {
		return guessResult;
	}

	/**
	 * @param guessResult
	 *            the guessResult to set
	 */
	public void setGuessResult(String guessResult) {
		this.guessResult = guessResult;
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
	 * @return the risePercent
	 */
	public Float getRisePercent() {
		return risePercent;
	}

	/**
	 * @param risePercent
	 *            the risePercent to set
	 */
	public void setRisePercent(Float risePercent) {
		this.risePercent = risePercent;
	}

	/**
	 * @return the failPercent
	 */
	public Float getFailPercent() {
		return failPercent;
	}

	/**
	 * @param failPercent
	 *            the failPercent to set
	 */
	public void setFailPercent(Float failPercent) {
		this.failPercent = failPercent;
	}

	/**
	 * @return the perNum
	 */
	public String getPerNum() {
		return perNum;
	}

	/**
	 * @param perNum
	 *            the perNum to set
	 */
	public void setPerNum(String perNum) {
		this.perNum = perNum;
	}

	/**
	 * @return the isGuessed
	 */
	public String getIsGuessed() {
		return isGuessed;
	}

	/**
	 * @param isGuessed
	 *            the isGuessed to set
	 */
	public void setIsGuessed(String isGuessed) {
		this.isGuessed = isGuessed;
	}

}
