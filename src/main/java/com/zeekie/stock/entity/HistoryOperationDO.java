package com.zeekie.stock.entity;

public class HistoryOperationDO extends CurrentOperationDO {

	private String loanRule;

	private String startDate;

	private String endDate;

	private Float managementFee;

	private String assetId;

	private Float guaranteeCash;

	private String id;

	private Float assginRadio;

	public HistoryOperationDO() {
		// TODO Auto-generated constructor stub
	}

	public Float getAssginRadio() {
		return assginRadio;
	}

	public void setAssginRadio(Float assginRadio) {
		this.assginRadio = assginRadio;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the managementFee
	 */
	public Float getManagementFee() {
		return managementFee;
	}

	/**
	 * @param managementFee
	 *            the managementFee to set
	 */
	public void setManagementFee(Float managementFee) {
		this.managementFee = managementFee;
	}

	/**
	 * @return the guaranteeCash
	 */
	public Float getGuaranteeCash() {
		return guaranteeCash;
	}

	/**
	 * @param guaranteeCash
	 *            the guaranteeCash to set
	 */
	public void setGuaranteeCash(Float guaranteeCash) {
		this.guaranteeCash = guaranteeCash;
	}

	/**
	 * @return the loanRule
	 */
	public String getLoanRule() {
		return loanRule;
	}

	/**
	 * @param loanRule
	 *            the loanRule to set
	 */
	public void setLoanRule(String loanRule) {
		this.loanRule = loanRule;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the assetId
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * @param assetId
	 *            the assetId to set
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

}
