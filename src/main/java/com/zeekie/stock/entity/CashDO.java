package com.zeekie.stock.entity;

public class CashDO {

	private Float residueCash;

	private Float orginCash;

	private Float debt;

	/**
	 * @return the debt
	 */
	public Float getDebt() {
		return debt;
	}

	/**
	 * @param debt
	 *            the debt to set
	 */
	public void setDebt(Float debt) {
		this.debt = debt;
	}

	/**
	 * @return the residueCash
	 */
	public Float getResidueCash() {
		return residueCash;
	}

	/**
	 * @param residueCash
	 *            the residueCash to set
	 */
	public void setResidueCash(Float residueCash) {
		this.residueCash = residueCash;
	}

	/**
	 * @return the orginCash
	 */
	public Float getOrginCash() {
		return orginCash;
	}

	/**
	 * @param orginCash
	 *            the orginCash to set
	 */
	public void setOrginCash(Float orginCash) {
		this.orginCash = orginCash;
	}

}
