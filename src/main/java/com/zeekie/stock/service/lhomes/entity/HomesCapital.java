package com.zeekie.stock.service.lhomes.entity;

public class HomesCapital extends HomesResponse {

	private Float fetfund;

	private Float userfund;

	private Float usermarket;

	/**
	 * 当前资金
	 */
	private Float currMarket;

	/**
	 * 当前市值
	 */
	private Float currFund;

	public Float getFetfund() {
		return fetfund;
	}

	public void setFetfund(Float fetfund) {
		this.fetfund = fetfund;
	}

	public Float getUserfund() {
		return userfund;
	}

	public void setUserfund(Float userfund) {
		this.userfund = userfund;
	}

	public Float getUsermarket() {
		return usermarket;
	}

	public void setUsermarket(Float usermarket) {
		this.usermarket = usermarket;
	}

	public Float getCurrMarket() {
		return currMarket;
	}

	public void setCurrMarket(Float currMarket) {
		this.currMarket = currMarket;
	}

	public Float getCurrFund() {
		return currFund;
	}

	public void setCurrFund(Float currFund) {
		this.currFund = currFund;
	}

	public HomesCapital(Float fetfund, Float userfund, Float usermarket, Float currMarket, Float currFund) {
		super();
		this.fetfund = fetfund;
		this.userfund = userfund;
		this.usermarket = usermarket;
		this.currMarket = currMarket;
		this.currFund = currFund;
	}

	
	
}
