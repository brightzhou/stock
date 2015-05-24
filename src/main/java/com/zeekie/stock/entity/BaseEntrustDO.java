package com.zeekie.stock.entity;

public class BaseEntrustDO {

	/**
	 * 操作员编号
	 */
	private String operatorNo;

	/**
	 * 委托序号
	 */
	private String entrustNo;

	/**
	 * @return the operatorNo
	 */
	public String getOperatorNo() {
		return operatorNo;
	}

	/**
	 * @param operatorNo
	 *            the operatorNo to set
	 */
	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

	/**
	 * @return the entrustNo
	 */
	public String getEntrustNo() {
		return entrustNo;
	}

	/**
	 * @param entrustNo
	 *            the entrustNo to set
	 */
	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}

}
