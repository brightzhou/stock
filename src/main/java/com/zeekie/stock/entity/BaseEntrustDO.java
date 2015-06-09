package com.zeekie.stock.entity;

public class BaseEntrustDO {

	/**
	 * 资金账号
	 */
	private String fundAccount;

	private String combineId;

	/**
	 * 操作员编号
	 */
	private String operatorNo;

	/**
	 * 委托序号
	 */
	private String entrustNo;
    
	/**
	 * 昵称
	 */
	private String nickName;
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
