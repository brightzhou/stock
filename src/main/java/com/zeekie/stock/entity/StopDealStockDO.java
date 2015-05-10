package com.zeekie.stock.entity;


public class StopDealStockDO extends WarnLineDO {

	private String operateNO;

	private String stopBuy;

	public StopDealStockDO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the operateNO
	 */
	public String getOperateNO() {
		return operateNO;
	}

	/**
	 * @param operateNO
	 *            the operateNO to set
	 */
	public void setOperateNO(String operateNO) {
		this.operateNO = operateNO;
	}

	/**
	 * @return the stopBuy
	 */
	public String getStopBuy() {
		return stopBuy;
	}

	/**
	 * @param stopBuy
	 *            the stopBuy to set
	 */
	public void setStopBuy(String stopBuy) {
		this.stopBuy = stopBuy;
	}

}
