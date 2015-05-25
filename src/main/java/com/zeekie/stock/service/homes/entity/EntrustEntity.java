package com.zeekie.stock.service.homes.entity;

public class EntrustEntity extends BaseEntity {

	private String entrust_no;

	private String batch_no;
	// 证券代码
	private String stock_code;
	// 交易市场 1：上交所 2：生交所
	private String exchange_type;
	// 委托方向
	private String entrust_direction;
	// 委托价格
	private String entrust_price;
	// 委托数量
	private String entrust_amount;

	/**
	 * @return the entrust_no
	 */
	public String getEntrust_no() {
		return entrust_no;
	}

	/**
	 * @param entrust_no
	 *            the entrust_no to set
	 */
	public void setEntrust_no(String entrust_no) {
		this.entrust_no = entrust_no;
	}

	/**
	 * @return the batch_no
	 */
	public String getBatch_no() {
		return batch_no;
	}

	/**
	 * @param batch_no
	 *            the batch_no to set
	 */
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	/**
	 * @return the stock_code
	 */
	public String getStock_code() {
		return stock_code;
	}

	/**
	 * @param stock_code
	 *            the stock_code to set
	 */
	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}

	/**
	 * @return the exchange_type
	 */
	public String getExchange_type() {
		return exchange_type;
	}

	/**
	 * @param exchange_type
	 *            the exchange_type to set
	 */
	public void setExchange_type(String exchange_type) {
		this.exchange_type = exchange_type;
	}

	/**
	 * @return the entrust_direction
	 */
	public String getEntrust_direction() {
		return entrust_direction;
	}

	/**
	 * @param entrust_direction
	 *            the entrust_direction to set
	 */
	public void setEntrust_direction(String entrust_direction) {
		this.entrust_direction = entrust_direction;
	}

	/**
	 * @return the entrust_price
	 */
	public String getEntrust_price() {
		return entrust_price;
	}

	/**
	 * @param entrust_price
	 *            the entrust_price to set
	 */
	public void setEntrust_price(String entrust_price) {
		this.entrust_price = entrust_price;
	}

	/**
	 * @return the entrust_amount
	 */
	public String getEntrust_amount() {
		return entrust_amount;
	}

	/**
	 * @param entrust_amount
	 *            the entrust_amount to set
	 */
	public void setEntrust_amount(String entrust_amount) {
		this.entrust_amount = entrust_amount;
	}

}
