package com.zeekie.stock.service.homes.entity;

public class EntrustAssetEntity extends BaseEntity {

   //账户编号
	private String fund_account	; 
	//单元编号
	private String asset_id ;	 	
	//组合编号
	private String combine_id ;	 	
	//返回编号
	private String error_no	  ;	
	//错误信息
	private String error_info ; 	
	//单元净值
	private String asset_value ;	 	
	//单元总资产
	private String asset_total_value ;	 
	//单元现金余额
	private String current_cash ;	 	
	//单元股票资产
	private String stock_asset	; 
	//单元基金资产
	private String fund_asset;	 	
	// 单元债券资产
	private String bond_asset;	
	//单元回购资产
	private String hg_asset ; 	
	//单元期货资产
	private String futures_asset ;
	public String getFund_account() {
		return fund_account;
	}
	public void setFund_account(String fundAccount) {
		fund_account = fundAccount;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String assetId) {
		asset_id = assetId;
	}
	public String getCombine_id() {
		return combine_id;
	}
	public void setCombine_id(String combineId) {
		combine_id = combineId;
	}
	public String getError_no() {
		return error_no;
	}
	public void setError_no(String errorNo) {
		error_no = errorNo;
	}
	public String getError_info() {
		return error_info;
	}
	public void setError_info(String errorInfo) {
		error_info = errorInfo;
	}
	public String getAsset_value() {
		return asset_value;
	}
	public void setAsset_value(String assetValue) {
		asset_value = assetValue;
	}
	public String getAsset_total_value() {
		return asset_total_value;
	}
	public void setAsset_total_value(String assetTotalValue) {
		asset_total_value = assetTotalValue;
	}
	public String getCurrent_cash() {
		return current_cash;
	}
	public void setCurrent_cash(String currentCash) {
		current_cash = currentCash;
	}
	public String getStock_asset() {
		return stock_asset;
	}
	public void setStock_asset(String stockAsset) {
		stock_asset = stockAsset;
	}
	public String getFund_asset() {
		return fund_asset;
	}
	public void setFund_asset(String fundAsset) {
		fund_asset = fundAsset;
	}
	public String getBond_asset() {
		return bond_asset;
	}
	public void setBond_asset(String bondAsset) {
		bond_asset = bondAsset;
	}
	public String getHg_asset() {
		return hg_asset;
	}
	public void setHg_asset(String hgAsset) {
		hg_asset = hgAsset;
	}
	public String getFutures_asset() {
		return futures_asset;
	}
	public void setFutures_asset(String futuresAsset) {
		futures_asset = futuresAsset;
	}	 	



}
