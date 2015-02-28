package com.zeekie.stock.entity;

public class DebtDO {

	private Float balance;

	private Float guaranteeCash;

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
	 * @return the guaranteeCash
	 */
	public Float getGuaranteeCash() {
		return guaranteeCash;
	}

	/**
	 * @param guaranteeCash
	 *            the guaranteeCash to set
	 */
	public void setGuaranteeCash(Float guaranteeCash) {
		this.guaranteeCash = guaranteeCash;
	}

}
