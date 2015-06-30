package com.zeekie.stock.service.lhomes.entity;

public class HomesCapital {

	private Float marketValue;

	private Float currValue;

	/**
	 * @return the marketValue
	 */
	public Float getMarketValue() {
		return marketValue;
	}

	/**
	 * @param marketValue
	 *            the marketValue to set
	 */
	public void setMarketValue(Float marketValue) {
		this.marketValue = marketValue;
	}

	/**
	 * @return the currValue
	 */
	public Float getCurrValue() {
		return currValue;
	}

	/**
	 * @param currValue
	 *            the currValue to set
	 */
	public void setCurrValue(Float currValue) {
		this.currValue = currValue;
	}

	public HomesCapital(Float marketValue, Float currValue) {
		super();
		this.marketValue = marketValue;
		this.currValue = currValue;
	}

}
