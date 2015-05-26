package com.zeekie.stock.service.homes.entity;

public class EntrustQueryEntity extends EntrustEntity {

	// 成交数量
	private String business_amount;
	// 成交金额
	private String business_balance;
	// 委托状态
	private String amentrust_status;
	// 废单原因
	private String cancel_info;
	// 委托时间
	private String entrust_time;

	/**
	 * @return the entrust_time
	 */
	public String getEntrust_time() {
		return entrust_time;
	}

	/**
	 * @param entrust_time
	 *            the entrust_time to set
	 */
	public void setEntrust_time(String entrust_time) {
		this.entrust_time = entrust_time;
	}

	/**
	 * @return the business_amount
	 */
	public String getBusiness_amount() {
		return business_amount;
	}

	/**
	 * @param business_amount
	 *            the business_amount to set
	 */
	public void setBusiness_amount(String business_amount) {
		this.business_amount = business_amount;
	}

	/**
	 * @return the business_balance
	 */
	public String getBusiness_balance() {
		return business_balance;
	}

	/**
	 * @param business_balance
	 *            the business_balance to set
	 */
	public void setBusiness_balance(String business_balance) {
		this.business_balance = business_balance;
	}

	/**
	 * @return the amentrust_status
	 */
	public String getAmentrust_status() {
		return amentrust_status;
	}

	/**
	 * @param amentrust_status
	 *            the amentrust_status to set
	 */
	public void setAmentrust_status(String amentrust_status) {
		this.amentrust_status = amentrust_status;
	}

	/**
	 * @return the cancel_info
	 */
	public String getCancel_info() {
		return cancel_info;
	}

	/**
	 * @param cancel_info
	 *            the cancel_info to set
	 */
	public void setCancel_info(String cancel_info) {
		this.cancel_info = cancel_info;
	}

}
