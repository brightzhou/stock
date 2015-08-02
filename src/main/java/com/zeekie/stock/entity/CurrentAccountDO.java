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
	 * 管理费
	 */
	private Float fee;

	/**
	 * 理财金额
	 */
	private Float finance;

	/**
	 * 开关
	 */
	private String isStock;
	
	/**
	 * 哈哈币
	 */
	private String hhb;
	
	

	/**
	 * @return the hhb
	 */
	public String getHhb() {
		return hhb;
	}

	/**
	 * @param hhb the hhb to set
	 */
	public void setHhb(String hhb) {
		this.hhb = hhb;
	}

	/**
	 * @return the isStock
	 */
	public String getIsStock() {
		return isStock;
	}

	/**
	 * @param isStock
	 *            the isStock to set
	 */
	public void setIsStock(String isStock) {
		this.isStock = isStock;
	}

	/**
	 * @return the finance
	 */
	public Float getFinance() {
		return finance;
	}

	/**
	 * @param finance
	 *            the finance to set
	 */
	public void setFinance(Float finance) {
		this.finance = finance;
	}

	/**
	 * @return the fee
	 */
	public Float getFee() {
		return fee;
	}

	/**
	 * @param fee
	 *            the fee to set
	 */
	public void setFee(Float fee) {
		this.fee = fee;
	}

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
