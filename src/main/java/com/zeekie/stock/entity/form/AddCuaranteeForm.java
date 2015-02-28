package com.zeekie.stock.entity.form;

public class AddCuaranteeForm {

	private String addedGuaranteeCash;

	private String currentGuaranteeCash;

	private String currentOperateLimit;

	private String profitAndLossCash;

	private String nickname;

	private String flag="";

	/**
	 * 新分配的配资钱，（根据投入的保证金-盈亏后剩的钱）
	 */
	private String addedAssginCapital;

	public AddCuaranteeForm() {
		// TODO Auto-generated constructor stub
	}

	public AddCuaranteeForm(String currentGuaranteeCash,
			String currentOperateLimit, String profitAndLossCash,
			String nickname) {
		this.currentGuaranteeCash = currentGuaranteeCash;
		this.currentOperateLimit = currentOperateLimit;
		this.profitAndLossCash = profitAndLossCash;
		this.nickname = nickname;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the addedAssginCapital
	 */
	public String getAddedAssginCapital() {
		return addedAssginCapital;
	}

	/**
	 * @param addedAssginCapital
	 *            the addedAssginCapital to set
	 */
	public void setAddedAssginCapital(String addedAssginCapital) {
		this.addedAssginCapital = addedAssginCapital;
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
	 * @return the addedGuaranteeCash
	 */
	public String getAddedGuaranteeCash() {
		return addedGuaranteeCash;
	}

	/**
	 * @param addedGuaranteeCash
	 *            the addedGuaranteeCash to set
	 */
	public void setAddedGuaranteeCash(String addedGuaranteeCash) {
		this.addedGuaranteeCash = addedGuaranteeCash;
	}

	/**
	 * @return the currentGuaranteeCash
	 */
	public String getCurrentGuaranteeCash() {
		return currentGuaranteeCash;
	}

	/**
	 * @param currentGuaranteeCash
	 *            the currentGuaranteeCash to set
	 */
	public void setCurrentGuaranteeCash(String currentGuaranteeCash) {
		this.currentGuaranteeCash = currentGuaranteeCash;
	}

	/**
	 * @return the currentOperateLimit
	 */
	public String getCurrentOperateLimit() {
		return currentOperateLimit;
	}

	/**
	 * @param currentOperateLimit
	 *            the currentOperateLimit to set
	 */
	public void setCurrentOperateLimit(String currentOperateLimit) {
		this.currentOperateLimit = currentOperateLimit;
	}

	/**
	 * @return the profitAndLossCash
	 */
	public String getProfitAndLossCash() {
		return profitAndLossCash;
	}

	/**
	 * @param profitAndLossCash
	 *            the profitAndLossCash to set
	 */
	public void setProfitAndLossCash(String profitAndLossCash) {
		this.profitAndLossCash = profitAndLossCash;
	}

}
