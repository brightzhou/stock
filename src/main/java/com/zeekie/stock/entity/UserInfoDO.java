package com.zeekie.stock.entity;

import java.util.Date;

public class UserInfoDO {

	private String userId;

	private String nickname;

	private String phone;

	private String trueName;

	private Float balance;

	private Float actualFund;

	private String upLine;

	private String version;

	private String status;

	private String refereeId;

	private Date registerDate;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(String refereeId) {
		this.refereeId = refereeId;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the trueName
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * @param trueName
	 *            the trueName to set
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * @return the balance
	 */
	public Float getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(Float balance) {
		this.balance = balance;
	}

	/**
	 * @return the actualFund
	 */
	public Float getActualFund() {
		return actualFund;
	}

	/**
	 * @param actualFund
	 *            the actualFund to set
	 */
	public void setActualFund(Float actualFund) {
		this.actualFund = actualFund;
	}

	/**
	 * @return the upLine
	 */
	public String getUpLine() {
		return upLine;
	}

	/**
	 * @param upLine
	 *            the upLine to set
	 */
	public void setUpLine(String upLine) {
		this.upLine = upLine;
	}

	public UserInfoDO() {
		// TODO Auto-generated constructor stub
	}

}
