package com.zeekie.stock.service.lhomes.entity;

public class EntrustMoveFund extends AHomesEntity {

	private String clientNoTo;

	/**
	 * 借
	 */
	private int businessFlagPay = 2022;
	
	/**
	 * 还
	 */
	private int businessFlagBack = 2021;
	
	
	private String flag;

	private String occurBalance;

	private String remark = "";

	
	
	/**
	 * @return the businessFlagPay
	 */
	public int getBusinessFlagPay() {
		return businessFlagPay;
	}

	/**
	 * @param businessFlagPay the businessFlagPay to set
	 */
	public void setBusinessFlagPay(int businessFlagPay) {
		this.businessFlagPay = businessFlagPay;
	}

	/**
	 * @return the businessFlagBack
	 */
	public int getBusinessFlagBack() {
		return businessFlagBack;
	}

	/**
	 * @param businessFlagBack the businessFlagBack to set
	 */
	public void setBusinessFlagBack(int businessFlagBack) {
		this.businessFlagBack = businessFlagBack;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the clientNoTo
	 */
	public String getClientNoTo() {
		return clientNoTo;
	}

	/**
	 * @param clientNoTo
	 *            the clientNoTo to set
	 */
	public void setClientNoTo(String clientNoTo) {
		this.clientNoTo = clientNoTo;
	}



	/**
	 * @return the occurBalance
	 */
	public String getOccurBalance() {
		return occurBalance;
	}

	/**
	 * @param occurBalance
	 *            the occurBalance to set
	 */
	public void setOccurBalance(String occurBalance) {
		this.occurBalance = occurBalance;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
