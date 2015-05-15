package com.zeekie.stock.entity;

/**
 * 结束操盘的时候计算用户的钱和我们的配资的钱
 * 
 * @author zeekie
 * @since 2014.12.6
 * 
 */
public class EndStockCashDO extends StopDealStockDO{

	/**
	 * 用户的钱：保证金+盈亏金额
	 */
	private Float userCash;

	/**
	 * 我们的钱：
	 */
	private Float assginCash;

	/**
	 * 资金账号
	 */
	private String fundAccount;

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
	 * @return the userCash
	 */
	public Float getUserCash() {
		return userCash;
	}

	/**
	 * @param userCash
	 *            the userCash to set
	 */
	public void setUserCash(Float userCash) {
		this.userCash = userCash;
	}

	/**
	 * @return the assginCash
	 */
	public Float getAssginCash() {
		return assginCash;
	}

	/**
	 * @param assginCash
	 *            the assginCash to set
	 */
	public void setAssginCash(Float assginCash) {
		this.assginCash = assginCash;
	}

}
