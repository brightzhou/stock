package com.zeekie.stock.entity;

public class StockRadioDO {

	private Float assignRadio;

	private Float warnRadio;

	private Float stopRadio;

	private Float manageFeeRadio;

	private Float upLinePercent;

	/**
	 * @return the upLinePercent
	 */
	public Float getUpLinePercent() {
		return upLinePercent;
	}

	/**
	 * @param upLinePercent
	 *            the upLinePercent to set
	 */
	public void setUpLinePercent(Float upLinePercent) {
		this.upLinePercent = upLinePercent;
	}

	public Float getAssignRadio() {
		return assignRadio;
	}

	public void setAssignRadio(Float assignRadio) {
		this.assignRadio = assignRadio;
	}

	public Float getWarnRadio() {
		return warnRadio;
	}

	public void setWarnRadio(Float warnRadio) {
		this.warnRadio = warnRadio;
	}

	public Float getStopRadio() {
		return stopRadio;
	}

	public void setStopRadio(Float stopRadio) {
		this.stopRadio = stopRadio;
	}

	/**
	 * @return the manageFeeRadio
	 */
	public Float getManageFeeRadio() {
		return manageFeeRadio;
	}

	/**
	 * @param manageFeeRadio
	 *            the manageFeeRadio to set
	 */
	public void setManageFeeRadio(Float manageFeeRadio) {
		this.manageFeeRadio = manageFeeRadio;
	}

}
