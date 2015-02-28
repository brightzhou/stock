package com.zeekie.stock.entity;

public class WithdrawPageDO {

	/**
	 * 银矿名称
	 */
	private String bank;

	/**
	 * 卡号
	 */
	private String number;

	/**
	 * 使用条款
	 */
	private String rule;

	/**
	 * 隐私条款
	 */
	private String privateRule;

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getPrivateRule() {
		return privateRule;
	}

	public void setPrivateRule(String privateRule) {
		this.privateRule = privateRule;
	}

}
