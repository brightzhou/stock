package com.zeekie.stock.entity;

public class LoginDO {

	private String userId;

	private String nickname;

	private String figurePwd;

	private String tradeAccount;

	private String depositPwd;

	private String identification;

	private String telephone;

	private String bankCard;

	private Float assignCash;

	private Float debt;

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

	/**
	 * @return the assignCash
	 */
	public Float getAssignCash() {
		return assignCash;
	}

	/**
	 * @param assignCash
	 *            the assignCash to set
	 */
	public void setAssignCash(Float assignCash) {
		this.assignCash = assignCash;
	}

	/**
	 * @return the debt
	 */
	public Float getDebt() {
		return debt;
	}

	/**
	 * @param debt
	 *            the debt to set
	 */
	public void setDebt(Float debt) {
		this.debt = debt;
	}

	/**
	 * @return the bankCard
	 */
	public String getBankCard() {
		return bankCard;
	}

	/**
	 * @param bankCard
	 *            the bankCard to set
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	/**
	 * @return the identification
	 */
	public String getIdentification() {
		return identification;
	}

	/**
	 * @param identification
	 *            the identification to set
	 */
	public void setIdentification(String identification) {
		this.identification = identification;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the figurePwd
	 */
	public String getFigurePwd() {
		return figurePwd;
	}

	/**
	 * @param figurePwd
	 *            the figurePwd to set
	 */
	public void setFigurePwd(String figurePwd) {
		this.figurePwd = figurePwd;
	}

	/**
	 * @return the tradeAccount
	 */
	public String getTradeAccount() {
		return tradeAccount;
	}

	/**
	 * @param tradeAccount
	 *            the tradeAccount to set
	 */
	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	/**
	 * @return the depositPwd
	 */
	public String getDepositPwd() {
		return depositPwd;
	}

	/**
	 * @param depositPwd
	 *            the depositPwd to set
	 */
	public void setDepositPwd(String depositPwd) {
		this.depositPwd = depositPwd;
	}

}
