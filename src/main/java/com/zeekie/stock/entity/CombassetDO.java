package com.zeekie.stock.entity;

public class CombassetDO {
	// 单元总资产
	private Float assetTotalValue;
	// 单元净值
	private Float assetValue;
	// 单元现金余额
	private Float currentCash;

	/**
	 * @return the assetTotalValue
	 */
	public Float getAssetTotalValue() {
		return assetTotalValue;
	}

	/**
	 * @param assetTotalValue
	 *            the assetTotalValue to set
	 */
	public void setAssetTotalValue(Float assetTotalValue) {
		this.assetTotalValue = assetTotalValue;
	}

	/**
	 * @return the assetValue
	 */
	public Float getAssetValue() {
		return assetValue;
	}

	/**
	 * @param assetValue
	 *            the assetValue to set
	 */
	public void setAssetValue(Float assetValue) {
		this.assetValue = assetValue;
	}

	/**
	 * @return the currentCash
	 */
	public Float getCurrentCash() {
		return currentCash;
	}

	/**
	 * @param currentCash
	 *            the currentCash to set
	 */
	public void setCurrentCash(Float currentCash) {
		this.currentCash = currentCash;
	}

	public CombassetDO() {
		// TODO Auto-generated constructor stub
	}

}
