package com.zeekie.stock.entity.form;

import com.zeekie.stock.util.StringUtil;

public class TradeForm {

	private Long id;

	/**
	 * 操盘金额
	 */
	private String tradeFund;

	/**
	 * 保证金
	 */
	private String guaranteeCash;

	/**
	 * 警戒金额
	 */
	private String warnCash;

	/**
	 * 止损金额
	 */
	private String stopCash;

	/**
	 * 用户
	 */
	private String nickname;

	/**
	 * 开始日期
	 */
	private String startDate;

	private String operateAccountId;

	private int ticket = Integer.parseInt(StringUtil.genRandomFive(1));
	/**
	 * 止损比例
	 */
	private float stopRadio ;
	/**
	 * 警戒比例
	 */
	private float warnRadio ;
	/**
	 * 配资比例
	 */
	private float assignRadio ;
	
	
	

	public float getStopRadio() {
		return stopRadio;
	}

	public void setStopRadio(float stopRadio) {
		this.stopRadio = stopRadio;
	}

	public float getWarnRadio() {
		return warnRadio;
	}

	public void setWarnRadio(float warnRadio) {
		this.warnRadio = warnRadio;
	}

	public float getAssignRadio() {
		return assignRadio;
	}

	public void setAssignRadio(float assignRadio) {
		this.assignRadio = assignRadio;
	}

	/**
	 * @return the ticket
	 */
	public int getTicket() {
		return ticket;
	}

	/**
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the operateAccountId
	 */
	public String getOperateAccountId() {
		return operateAccountId;
	}

	/**
	 * @param operateAccountId
	 *            the operateAccountId to set
	 */
	public void setOperateAccountId(String operateAccountId) {
		this.operateAccountId = operateAccountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	 * @return the tradeFund
	 */
	public String getTradeFund() {
		return tradeFund;
	}

	/**
	 * @param tradeFund
	 *            the tradeFund to set
	 */
	public void setTradeFund(String tradeFund) {
		this.tradeFund = tradeFund;
	}

	/**
	 * @return the guaranteeCash
	 */
	public String getGuaranteeCash() {
		return guaranteeCash;
	}

	/**
	 * @param guaranteeCash
	 *            the guaranteeCash to set
	 */
	public void setGuaranteeCash(String guaranteeCash) {
		this.guaranteeCash = guaranteeCash;
	}

	/**
	 * @return the warnCash
	 */
	public String getWarnCash() {
		return warnCash;
	}

	/**
	 * @param warnCash
	 *            the warnCash to set
	 */
	public void setWarnCash(String warnCash) {
		this.warnCash = warnCash;
	}

	/**
	 * @return the stopCash
	 */
	public String getStopCash() {
		return stopCash;
	}

	/**
	 * @param stopCash
	 *            the stopCash to set
	 */
	public void setStopCash(String stopCash) {
		this.stopCash = stopCash;
	}

}
