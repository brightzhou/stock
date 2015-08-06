package com.zeekie.stock.entity;

public class BasicInfoDO {

	private String hasDebt;

	private String hasOperation;

	private String appStatus;

	private Float balance;

	private Float fee;

	private Float progressbar;
	/** 当前炒盘倍数 add 20150610 */
	private Float assignRadio;

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public Float getAssignRadio() {
		return assignRadio;
	}

	public void setAssignRadio(Float assignRadio) {
		this.assignRadio = assignRadio;
	}

	public BasicInfoDO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the progressbar
	 */
	public Float getProgressbar() {
		return progressbar;
	}

	/**
	 * @param progressbar
	 *            the progressbar to set
	 */
	public void setProgressbar(Float progressbar) {
		this.progressbar = progressbar;
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
