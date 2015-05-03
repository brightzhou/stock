package com.zeekie.stock.entity;

public class TransactionDO extends BaseUserDO {

	private String refNo;

	private String merchantId;

	private String statusAppTrans;

	private String transactionDate;

	private Float cash;

	private String description;

	/**
	 * @return the refNo
	 */
	public String getRefNo() {
		return refNo;
	}

	/**
	 * @param refNo
	 *            the refNo to set
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId
	 *            the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the statusAppTrans
	 */
	public String getStatusAppTrans() {
		return statusAppTrans;
	}

	/**
	 * @param statusAppTrans
	 *            the statusAppTrans to set
	 */
	public void setStatusAppTrans(String statusAppTrans) {
		this.statusAppTrans = statusAppTrans;
	}

	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the cash
	 */
	public Float getCash() {
		return cash;
	}

	/**
	 * @param cash
	 *            the cash to set
	 */
	public void setCash(Float cash) {
		this.cash = cash;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public TransactionDO() {
		// TODO Auto-generated constructor stub
	}

}
