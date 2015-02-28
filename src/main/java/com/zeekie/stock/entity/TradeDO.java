package com.zeekie.stock.entity;

/**
 * 资金划转
 * 
 * @author zeekie
 * 
 */
public class TradeDO {

	private Long id;

	private String operatorNo;

	private String operatorPwd;

	private String nickname;

	private String combineId;

	private String assetName;

	private String assetId;

	private String combineName;

	private String managerCombineId;

	/**
	 * @return the managerCombineId
	 */
	public String getManagerCombineId() {
		return managerCombineId;
	}

	/**
	 * @param managerCombineId
	 *            the managerCombineId to set
	 */
	public void setManagerCombineId(String managerCombineId) {
		this.managerCombineId = managerCombineId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCombineName() {
		return combineName;
	}

	public void setCombineName(String combineName) {
		this.combineName = combineName;
	}

	/**
	 * 资产单元名称
	 */
	private String assetunitName;

	/**
	 * 账户名称
	 */
	private String clientName;

	/**
	 * 账户编号
	 */
	private String fundAccount;

	public String getAssetunitName() {
		return assetunitName;
	}

	public void setAssetunitName(String assetunitName) {
		this.assetunitName = assetunitName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetName
	 *            the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
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
	 * @return the operatorPwd
	 */
	public String getOperatorPwd() {
		return operatorPwd;
	}

	/**
	 * @param operatorPwd
	 *            the operatorPwd to set
	 */
	public void setOperatorPwd(String operatorPwd) {
		this.operatorPwd = operatorPwd;
	}

}
