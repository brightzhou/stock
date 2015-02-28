package com.zeekie.stock.entity;

public class DeductDO {

	private String nickname;

	private Float fee;

	private Float drawFee;

	public DeductDO() {
	}

	public DeductDO(String nickname, Float fee, Float drawFee) {
		super();
		this.nickname = nickname;
		this.fee = fee;
		this.drawFee = drawFee;
	}

	/**
	 * @return the drawFee
	 */
	public Float getDrawFee() {
		return drawFee;
	}

	/**
	 * @param drawFee
	 *            the drawFee to set
	 */
	public void setDrawFee(Float drawFee) {
		this.drawFee = drawFee;
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
	 * @return the fee
	 */
	public Float getFee() {
		return fee;
	}

	/**
	 * @param fee
	 *            the fee to set
	 */
	public void setFee(Float fee) {
		this.fee = fee;
	}

}
