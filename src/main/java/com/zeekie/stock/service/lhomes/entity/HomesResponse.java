package com.zeekie.stock.service.lhomes.entity;

public class HomesResponse {

	private Integer branchNo;

	private Integer fundAccount;

	private Integer entrustNo;

	private Integer batchNo;

	private String clientNo;

	public HomesResponse(int branchNo, int fundAccount, int entrustNo,
			int batchNo, String clientNo) {
		super();
		this.branchNo = branchNo;
		this.fundAccount = fundAccount;
		this.entrustNo = entrustNo;
		this.batchNo = batchNo;
		this.clientNo = clientNo;
	}

	public HomesResponse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the branchNo
	 */
	public Integer getBranchNo() {
		return branchNo;
	}

	/**
	 * @param branchNo
	 *            the branchNo to set
	 */
	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	/**
	 * @return the fundAccount
	 */
	public Integer getFundAccount() {
		return fundAccount;
	}

	/**
	 * @param fundAccount
	 *            the fundAccount to set
	 */
	public void setFundAccount(Integer fundAccount) {
		this.fundAccount = fundAccount;
	}

	/**
	 * @return the entrustNo
	 */
	public Integer getEntrustNo() {
		return entrustNo;
	}

	/**
	 * @param entrustNo
	 *            the entrustNo to set
	 */
	public void setEntrustNo(Integer entrustNo) {
		this.entrustNo = entrustNo;
	}

	/**
	 * @return the batchNo
	 */
	public Integer getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo
	 *            the batchNo to set
	 */
	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the clientNo
	 */
	public String getClientNo() {
		return clientNo;
	}

	/**
	 * @param clientNo
	 *            the clientNo to set
	 */
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}

}
