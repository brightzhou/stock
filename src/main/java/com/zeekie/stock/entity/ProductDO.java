package com.zeekie.stock.entity;

public class ProductDO {

	private String code;

	private Float financeIncome;

	private Float stockIncome;

	private String guessIncome;

	private String flag;

	private String hasOperation;

	public String getHasOperation() {
		return hasOperation;
	}

	public void setHasOperation(String hasOperation) {
		this.hasOperation = hasOperation;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return the financeIncome
	 */
	public Float getFinanceIncome() {
		return financeIncome;
	}

	/**
	 * @param financeIncome
	 *            the financeIncome to set
	 */
	public void setFinanceIncome(Float financeIncome) {
		this.financeIncome = financeIncome;
	}

	/**
	 * @return the stockIncome
	 */
	public Float getStockIncome() {
		return stockIncome;
	}

	/**
	 * @param stockIncome
	 *            the stockIncome to set
	 */
	public void setStockIncome(Float stockIncome) {
		this.stockIncome = stockIncome;
	}

	/**
	 * @return the guessIncome
	 */
	public String getGuessIncome() {
		return guessIncome;
	}

	/**
	 * @param guessIncome
	 *            the guessIncome to set
	 */
	public void setGuessIncome(String guessIncome) {
		this.guessIncome = guessIncome;
	}

}
