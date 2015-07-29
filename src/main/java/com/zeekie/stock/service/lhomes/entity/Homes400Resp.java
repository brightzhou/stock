package com.zeekie.stock.service.lhomes.entity;

public class Homes400Resp extends HomesResponse {

	private Float closePrice;

	/**
	 * @return the closePrice
	 */
	public Float getClosePrice() {
		return closePrice;
	}

	/**
	 * @param closePrice
	 *            the closePrice to set
	 */
	public void setClosePrice(Float closePrice) {
		this.closePrice = closePrice;
	}

	public Homes400Resp(Float closePrice) {
		this.closePrice = closePrice;
	}

}
