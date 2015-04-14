package com.zeekie.stock.entity;

public class BankInfoDO {

	private String bank;

	private String cardNumber;

	private String bankCode;

	private String phone;

	private String limiteEveryDay;

	private String limiteEveryDeal;

	/**
	 * @return the limiteEveryDay
	 */
	public String getLimiteEveryDay() {
		return limiteEveryDay;
	}

	/**
	 * @param limiteEveryDay
	 *            the limiteEveryDay to set
	 */
	public void setLimiteEveryDay(String limiteEveryDay) {
		this.limiteEveryDay = limiteEveryDay;
	}

	/**
	 * @return the limiteEveryDeal
	 */
	public String getLimiteEveryDeal() {
		return limiteEveryDeal;
	}

	/**
	 * @param limiteEveryDeal
	 *            the limiteEveryDeal to set
	 */
	public void setLimiteEveryDeal(String limiteEveryDeal) {
		this.limiteEveryDeal = limiteEveryDeal;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber
	 *            the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode
	 *            the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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

	public BankInfoDO() {
	}

}
