package com.zeekie.stock.entity;

public class TotalFundDO {

	private String total;

	private String totalFund;

	private String modifyFund;

	private String storeTime;

	private String current;

	private String fundAccountName;

	private String desc;

	private String leaveCashPercent;

	/**
	 * @return the leaveCashPercent
	 */
	public String getLeaveCashPercent() {
		return leaveCashPercent;
	}

	/**
	 * @param leaveCashPercent
	 *            the leaveCashPercent to set
	 */
	public void setLeaveCashPercent(String leaveCashPercent) {
		this.leaveCashPercent = leaveCashPercent;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the fundAccountName
	 */
	public String getFundAccountName() {
		return fundAccountName;
	}

	/**
	 * @param fundAccountName
	 *            the fundAccountName to set
	 */
	public void setFundAccountName(String fundAccountName) {
		this.fundAccountName = fundAccountName;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	/**
	 * @return the totalFund
	 */
	public String getTotalFund() {
		return totalFund;
	}

	/**
	 * @param totalFund
	 *            the totalFund to set
	 */
	public void setTotalFund(String totalFund) {
		this.totalFund = totalFund;
	}

	/**
	 * @return the modifyFund
	 */
	public String getModifyFund() {
		return modifyFund;
	}

	/**
	 * @param modifyFund
	 *            the modifyFund to set
	 */
	public void setModifyFund(String modifyFund) {
		this.modifyFund = modifyFund;
	}

	public String getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(String storeTime) {
		this.storeTime = storeTime;
	}

}
