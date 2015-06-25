package com.zeekie.stock.entity;

public class DownLineUserDO {

	private String nickname;

	private Float tradeFund;

	/**
	 * 总的理财金额
	 */
	private Float totalFinanceFund;

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
	 * @return the tradeFund
	 */
	public Float getTradeFund() {
		return tradeFund;
	}

	/**
	 * @param tradeFund
	 *            the tradeFund to set
	 */
	public void setTradeFund(Float tradeFund) {
		this.tradeFund = tradeFund;
	}

	/**
	 * @return the totalFinanceFund
	 */
	public Float getTotalFinanceFund() {
		return totalFinanceFund;
	}

	/**
	 * @param totalFinanceFund
	 *            the totalFinanceFund to set
	 */
	public void setTotalFinanceFund(Float totalFinanceFund) {
		this.totalFinanceFund = totalFinanceFund;
	}

}
