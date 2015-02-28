package com.zeekie.stock.entity;

public class EveningEndDO {

	private String id;

	private String assetId;

	private Float capital;

	private String stopPercent;

	/**
	 * @return the capital
	 */
	public Float getCapital() {
		return capital;
	}

	/**
	 * @param capital
	 *            the capital to set
	 */
	public void setCapital(Float capital) {
		this.capital = capital;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the assetId
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * @param assetId
	 *            the assetId to set
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * @return the stopPercent
	 */
	public String getStopPercent() {
		return stopPercent;
	}

	/**
	 * @param stopPercent
	 *            the stopPercent to set
	 */
	public void setStopPercent(String stopPercent) {
		this.stopPercent = stopPercent;
	}

}
