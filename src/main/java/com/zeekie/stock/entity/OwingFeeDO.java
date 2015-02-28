package com.zeekie.stock.entity;

public class OwingFeeDO {

	private String id;

	private String nickname;

	private String operationAccount;

	private String phone;

	private Float oweFee;

	public OwingFeeDO() {
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
	 * @return the operationAccount
	 */
	public String getOperationAccount() {
		return operationAccount;
	}

	/**
	 * @param operationAccount
	 *            the operationAccount to set
	 */
	public void setOperationAccount(String operationAccount) {
		this.operationAccount = operationAccount;
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
	 * @return the oweFee
	 */
	public Float getOweFee() {
		return oweFee;
	}

	/**
	 * @param oweFee
	 *            the oweFee to set
	 */
	public void setOweFee(Float oweFee) {
		this.oweFee = oweFee;
	}

}
