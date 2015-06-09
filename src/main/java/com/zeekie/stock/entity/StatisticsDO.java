package com.zeekie.stock.entity;

public class StatisticsDO {
    //当日服务费统计
	private Float serviceCharge=0.00f;
    //当日用户提出统计
	private Float commission=0.00f;
    //当日系统财务优化
	private Float financialOptimization=0.00f;
	//保证金
	private Float  bond =0.00f;
	//
	
	
    public Float getBond() {
		return bond;
	}
	public void setBond(Float bond) {
		this.bond = bond;
	}
	//日期
	private String transactionDay;
	public Float getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Float serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public Float getCommission() {
		return commission;
	}
	public void setCommission(Float commission) {
		this.commission = commission;
	}
	public Float getFinancialOptimization() {
		return financialOptimization;
	}
	public void setFinancialOptimization(Float financialOptimization) {
		this.financialOptimization = financialOptimization;
	}
	public String getTransactionDay() {
		return transactionDay;
	}
	public void setTransactionDay(String transactionDay) {
		this.transactionDay = transactionDay;
	}

	
	
 

	

}
