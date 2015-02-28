package com.zeekie.stock.entity;

public class AutoAddGuaranteeCashDO {
	
	private String nickname;
	
	/**
	 * 需要补充的保证金
	 */
	private String needWantCash;

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the needWantCash
	 */
	public String getNeedWantCash() {
		return needWantCash;
	}

	/**
	 * @param needWantCash the needWantCash to set
	 */
	public void setNeedWantCash(String needWantCash) {
		this.needWantCash = needWantCash;
	}
	
	

}
