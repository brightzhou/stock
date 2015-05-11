package com.zeekie.stock.entity;

public class BasicInfoDO {

	private String hasDebt;

	private String hasOperation;

	private String appStatus;

	private Float balance;

	public BasicInfoDO() {
		// TODO Auto-generated constructor stub
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
	 * @return the hasDebt
	 */
	public String getHasDebt() {
		return hasDebt;
	}

	/**
	 * @param hasDebt
	 *            the hasDebt to set
	 */
	public void setHasDebt(String hasDebt) {
		this.hasDebt = hasDebt;
	}

	/**
	 * @return the hasOperation
	 */
	public String getHasOperation() {
		return hasOperation;
	}

	/**
	 * @param hasOperation
	 *            the hasOperation to set
	 */
	public void setHasOperation(String hasOperation) {
		this.hasOperation = hasOperation;
	}

	/**
	 * @return the appStatus
	 */
	public String getAppStatus() {
		return appStatus;
	}

	/**
	 * @param appStatus
	 *            the appStatus to set
	 */
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

}
