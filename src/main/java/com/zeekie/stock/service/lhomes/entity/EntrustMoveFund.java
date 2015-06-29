package com.zeekie.stock.service.lhomes.entity;

public class EntrustMoveFund extends AHomesEntity {

	private String clientNoTo;

	private int businessFlag = 2022;

	private String occurBalance;

	private String remark = "";

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
	 * @return the businessFlag
	 */
	public int getBusinessFlag() {
		return businessFlag;
	}

	/**
	 * @param businessFlag
	 *            the businessFlag to set
	 */
	public void setBusinessFlag(int businessFlag) {
		this.businessFlag = businessFlag;
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
