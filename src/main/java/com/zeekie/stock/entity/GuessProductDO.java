package com.zeekie.stock.entity;

public class GuessProductDO {

	private long id;

	private String code;

	private String name;

	private String riseNum;

	private String failNum;

	private String publishTime;

	private String status;

	private String guessResult;

	private String perNum;

	private Float pumpedPercent;

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
	 * @return the pumpedPercent
	 */
	public Float getPumpedPercent() {
		return pumpedPercent;
	}

	/**
	 * @param pumpedPercent
	 *            the pumpedPercent to set
	 */
	public void setPumpedPercent(Float pumpedPercent) {
		this.pumpedPercent = pumpedPercent;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the riseNum
	 */
	public String getRiseNum() {
		return riseNum;
	}

	/**
	 * @param riseNum
	 *            the riseNum to set
	 */
	public void setRiseNum(String riseNum) {
		this.riseNum = riseNum;
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
	 * @return the publishTime
	 */
	public String getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime
	 *            the publishTime to set
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
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

}
