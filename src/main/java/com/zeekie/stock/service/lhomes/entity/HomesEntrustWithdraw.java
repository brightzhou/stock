package com.zeekie.stock.service.lhomes.entity;

public class HomesEntrustWithdraw extends AHomesEntity {

	private String entrustNo;

	/**
	 * @return the entrustNo
	 */
	public String getEntrustNo() {
		return entrustNo;
	}

	/**
	 * @param entrustNo
	 *            the entrustNo to set
	 */
	public void setEntrustNo(String entrustNo) {
		this.entrustNo = entrustNo;
	}
	

	public HomesEntrustWithdraw(String investAccount, String clientNo,
			String entrustNo) {
		super(investAccount, clientNo);
		this.entrustNo = entrustNo;
	}

	public HomesEntrustWithdraw() {
		// TODO Auto-generated constructor stub
	}

}
