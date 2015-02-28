package com.zeekie.stock.entity;

public class AccountDO {

	private String fundAccount;

	private String combineId;

	private String tradeAcount;

	private String managerCombineId;

	/**
	 * @return the managerCombineId
	 */
	public String getManagerCombineId() {
		return managerCombineId;
	}

	/**
	 * @param managerCombineId
	 *            the managerCombineId to set
	 */
	public void setManagerCombineId(String managerCombineId) {
		this.managerCombineId = managerCombineId;
	}

	/**
	 * @return the tradeAcount
	 */
	public String getTradeAcount() {
		return tradeAcount;
	}

	/**
	 * @param tradeAcount
	 *            the tradeAcount to set
	 */
	public void setTradeAcount(String tradeAcount) {
		this.tradeAcount = tradeAcount;
	}

	/**
	 * @return the fundAccount
	 */
	public String getFundAccount() {
		return fundAccount;
	}

	/**
	 * @param fundAccount
	 *            the fundAccount to set
	 */
	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	/**
	 * @return the combineId
	 */
	public String getCombineId() {
		return combineId;
	}

	/**
	 * @param combineId
	 *            the combineId to set
	 */
	public void setCombineId(String combineId) {
		this.combineId = combineId;
	}

}
