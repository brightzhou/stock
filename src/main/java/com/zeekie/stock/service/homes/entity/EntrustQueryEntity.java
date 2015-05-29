package com.zeekie.stock.service.homes.entity;



public class EntrustQueryEntity extends EntrustEntity {

	// 成交数量
	private String business_amount;
	// 成交金额
	private String business_balance;
	// 委托状态
	private String amentrust_status;
	// 废单原因
	private String cancel_info;
	// 委托时间
	private String entrust_time;
	//成交时间
	private String business_time;
    //当前数量
	private String current_amount;
	//可用数量
	private String enable_amount;
	//当前成本
	private String cost_balance;
	//当前市值
	private String market_value;
	//账户编号
	private String  fund_account	;	
	//组合编号
	private String combine_id	;	
	//指令序号
	private String instruct_no	;	
	//指令修改序号
	private String instructmod_no	;	
    //股东代码
	private String stock_account	;	
	//申报席位
	private String seat_no	;
	//投资类型
	private String invest_way	;	
    //申报时间
	private String report_time 	;
	//已撤数量
	private String withdraw_amount ;


		 
		 


	public String getFund_account() {
		return fund_account;
	}

	public void setFund_account(String fundAccount) {
		fund_account = fundAccount;
	}

	public String getCombine_id() {
		return combine_id;
	}

	public void setCombine_id(String combineId) {
		combine_id = combineId;
	}

	public String getInstruct_no() {
		return instruct_no;
	}

	public void setInstruct_no(String instructNo) {
		instruct_no = instructNo;
	}

	public String getInstructmod_no() {
		return instructmod_no;
	}

	public void setInstructmod_no(String instructmodNo) {
		instructmod_no = instructmodNo;
	}

	public String getStock_account() {
		return stock_account;
	}

	public void setStock_account(String stockAccount) {
		stock_account = stockAccount;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seatNo) {
		seat_no = seatNo;
	}

	public String getInvest_way() {
		return invest_way;
	}

	public void setInvest_way(String investWay) {
		invest_way = investWay;
	}

	public String getReport_time() {
		return report_time;
	}

	public void setReport_time(String reportTime) {
		report_time = reportTime;
	}

	public String getWithdraw_amount() {
		return withdraw_amount;
	}

	public void setWithdraw_amount(String withdrawAmount) {
		withdraw_amount = withdrawAmount;
	}

	public String getCurrent_amount() {
		return current_amount;
	}

	public void setCurrent_amount(String currentAmount) {
		current_amount = currentAmount;
	}

	public String getEnable_amount() {
		return enable_amount;
	}

	public void setEnable_amount(String enableAmount) {
		enable_amount = enableAmount;
	}

	public String getCost_balance() {
		return cost_balance;
	}

	public void setCost_balance(String costBalance) {
		cost_balance = costBalance;
	}

	public String getMarket_value() {
		return market_value;
	}

	public void setMarket_value(String marketValue) {
		market_value = marketValue;
	}

	public String getBusiness_time() {
		return business_time;
	}

	public void setBusiness_time(String businessTime) {
		business_time = businessTime;
	}

	/**
	 * @return the entrust_time
	 */
	public String getEntrust_time() {
		return entrust_time;
	}

	/**
	 * @param entrust_time
	 *            the entrust_time to set
	 */
	public void setEntrust_time(String entrust_time) {
		this.entrust_time = entrust_time;
	}

	/**
	 * @return the business_amount
	 */
	public String getBusiness_amount() {
		return business_amount;
	}

	/**
	 * @param business_amount
	 *            the business_amount to set
	 */
	public void setBusiness_amount(String business_amount) {
		this.business_amount = business_amount;
	}

	/**
	 * @return the business_balance
	 */
	public String getBusiness_balance() {
		return business_balance;
	}

	/**
	 * @param business_balance
	 *            the business_balance to set
	 */
	public void setBusiness_balance(String business_balance) {
		this.business_balance = business_balance;
	}

	/**
	 * @return the amentrust_status
	 */
	public String getAmentrust_status() {
		return amentrust_status;
	}

	/**
	 * @param amentrust_status
	 *            the amentrust_status to set
	 */
	public void setAmentrust_status(String amentrust_status) {
		this.amentrust_status = amentrust_status;
	}

	/**
	 * @return the cancel_info
	 */
	public String getCancel_info() {
		return cancel_info;
	}

	/**
	 * @param cancel_info
	 *            the cancel_info to set
	 */
	public void setCancel_info(String cancel_info) {
		this.cancel_info = cancel_info;
	}

}
