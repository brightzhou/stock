package com.zeekie.stock.service.homes.entity;

public abstract class BaseEntity {

	private Long id;

	private String error_no;

	private String error_info;

	private String nickname;

	private String fundAccount;

	private String combineId;

	private String operateNo;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the operateNo
	 */
	public String getOperateNo() {
		return operateNo;
	}

	/**
	 * @param operateNo
	 *            the operateNo to set
	 */
	public void setOperateNo(String operateNo) {
		this.operateNo = operateNo;
	}

	/**
	 * @return the fundAccount
	 */
	public String getFundAccount() {
		return fundAccount;
	}

	/**
	 * @param fundAccount
	 *            the fundAccount to set
	 */
	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	/**
	 * @return the combineId
	 */
	public String getCombineId() {
		return combineId;
	}

	/**
	 * @param combineId
	 *            the combineId to set
	 */
	public void setCombineId(String combineId) {
		this.combineId = combineId;
	}

	/**
	 * @return the error_no
	 */
	public String getError_no() {
		return error_no;
	}

	/**
	 * @param error_no
	 *            the error_no to set
	 */
	public void setError_no(String error_no) {
		this.error_no = error_no;
	}

	/**
	 * @return the error_info
	 */
	public String getError_info() {
		return error_info;
	}

	/**
	 * @param error_info
	 *            the error_info to set
	 */
	public void setError_info(String error_info) {
		this.error_info = error_info;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void set(String fundAccount, String combineId, String operateNo) {
		this.fundAccount = fundAccount;
		this.combineId = combineId;
		this.operateNo = operateNo;
	}
}
