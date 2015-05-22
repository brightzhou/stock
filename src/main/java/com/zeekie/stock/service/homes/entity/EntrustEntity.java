package com.zeekie.stock.service.homes.entity;

public class EntrustEntity extends BaseEntity {

	private String entrust_no;

	private String batch_no;
	
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

}
