package com.zeekie.stock.entity;

public class CurrentAccountDO {

	/**
	 * 账户余额
	 */
	private Float balance;

	/**
	 * 股票账户资产
	 */
	private Float stockCapital;

	/**
	 * 保证金
	 */
	private Float guaranteeCash;

	/**
	 * 冻结金额
	 */
	private Float freezeCash;

	/**
	 * @return the freezeCash
	 */
	public Float getFreezeCash() {
		return freezeCash;
	}

	/**
	 * @param freezeCash
	 *            the freezeCash to set
	 */
	public void setFreezeCash(Float freezeCash) {
		this.freezeCash = freezeCash;
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
	 * @return the stockCapital
	 */
	public Float getStockCapital() {
		return stockCapital;
	}

	/**
	 * @param stockCapital
	 *            the stockCapital to set
	 */
	public void setStockCapital(Float stockCapital) {
		this.stockCapital = stockCapital;
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
