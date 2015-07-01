package com.zeekie.stock.service.lhomes.entity;

public class HomesCapital extends HomesResponse {

	private Float marketValue;

	private Float currValue;

	private Float usermarket;

	/**
	 * @return the usermarket
	 */
	public Float getUsermarket() {
		return usermarket;
	}

	/**
	 * @param usermarket
	 *            the usermarket to set
	 */
	public void setUsermarket(Float usermarket) {
		this.usermarket = usermarket;
	}

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

	public HomesCapital(Float marketValue, Float currValue, Float usermarket) {
		super();
		this.marketValue = marketValue;
		this.currValue = currValue;
		this.usermarket = usermarket;
	}

	public HomesCapital(Float marketValue, Float currValue) {
		super();
		this.marketValue = marketValue;
		this.currValue = currValue;
	}

}
