package com.zeekie.stock.entity;

public class PercentDO {

	private String total;

	private String leaveFund;

	private String leaveFundPercent;

	private String assetName;

	/**
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetName
	 *            the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
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
	 * @return the leaveFund
	 */
	public String getLeaveFund() {
		return leaveFund;
	}

	/**
	 * @param leaveFund
	 *            the leaveFund to set
	 */
	public void setLeaveFund(String leaveFund) {
		this.leaveFund = leaveFund;
	}

	/**
	 * @return the leaveFundPercent
	 */
	public String getLeaveFundPercent() {
		return leaveFundPercent;
	}

	/**
	 * @param leaveFundPercent
	 *            the leaveFundPercent to set
	 */
	public void setLeaveFundPercent(String leaveFundPercent) {
		this.leaveFundPercent = leaveFundPercent;
	}

	public PercentDO() {
		// TODO Auto-generated constructor stub
	}

}
