package com.zeekie.stock.entity;

import java.util.Date;

public class CurrentOperateUserDO {
	private Date startDate;
	private String operateAccountId;
	private String tradeAcount;
	private String tradePassword;
	private String assetId;
	private String fundAccount;
	private String combieId;

	public String getOperateAccountId() {
		return operateAccountId;
	}

	public void setOperateAccountId(String operateAccountId) {
		this.operateAccountId = operateAccountId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTradeAcount() {
		return tradeAcount;
	}

	public void setTradeAcount(String tradeAcount) {
		this.tradeAcount = tradeAcount;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getFundAccount() {
		return fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	public String getCombieId() {
		return combieId;
	}

	public void setCombieId(String combieId) {
		this.combieId = combieId;
	}

}
