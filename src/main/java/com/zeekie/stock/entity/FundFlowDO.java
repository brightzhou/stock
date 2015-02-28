package com.zeekie.stock.entity;

public class FundFlowDO {

	private String transactionType;

	private String transactionTime;

	private Float fund;

	private Long id;

	private String desc = "";

	private String nickname;
	
	public FundFlowDO() {
		// TODO Auto-generated constructor stub
	}

	public FundFlowDO(String nickname, String transactionType, Float fund,
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
	public Float getFund() {
		return fund;
	}

	/**
	 * @param fund
	 *            the fund to set
	 */
	public void setFund(Float fund) {
		this.fund = fund;
	}

}
