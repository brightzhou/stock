package com.zeekie.stock.entity;

public class FundFlowDO {

	private String transactionType;

	private String transactionTime;

	private String fund;

	private Long id;

	private String desc = "";

	private String nickname;

	private Float fundFloat;

	/**
	 * @return the fundFloat
	 */
	public Float getFundFloat() {
		return fundFloat;
	}

	/**
	 * @param fundFloat
	 *            the fundFloat to set
	 */
	public void setFundFloat(Float fundFloat) {
		this.fundFloat = fundFloat;
	}

	public FundFlowDO() {
		// TODO Auto-generated constructor stub
	}

	public FundFlowDO(String nickname, String transactionType, String fund,
			String desc) {
		super();
		this.nickname = nickname;
		this.transactionType = transactionType;
		this.fund = fund;
		this.desc = desc;
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the transactionTime
	 */
	public String getTransactionTime() {
		return transactionTime;
	}

	/**
	 * @param transactionTime
	 *            the transactionTime to set
	 */
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * @return the fund
	 */
	public String getFund() {
		return fund;
	}

	/**
	 * @param fund
	 *            the fund to set
	 */
	public void setFund(String fund) {
		this.fund = fund;
	}

}
