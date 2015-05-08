package com.zeekie.stock.entity;

public class HasOpertAndDebtDO {

	private String debt;

	private String operation;

	private Float balance;

	/**
	 * @return the balance
	 */
	public Float getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(Float balance) {
		this.balance = balance;
	}

	/**
	 * @return the debt
	 */
	public String getDebt() {
		return debt;
	}

	/**
	 * @param debt
	 *            the debt to set
	 */
	public void setDebt(String debt) {
		this.debt = debt;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	public HasOpertAndDebtDO() {
		// TODO Auto-generated constructor stub
	}

}
