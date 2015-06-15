package com.zeekie.stock.entity;

public class FlbDO {

	/*
	 * 用户名称
	 */
	private String trueName;

	/*
	 * 身份证号
	 */
	private String certification;

	/*
	 * 操盘时间
	 */
	private String operateTime;

	/*
	 * 额度
	 */
	private Float fundLimit;

	/**
	 * @return the trueName
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * @param trueName
	 *            the trueName to set
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * @return the certification
	 */
	public String getCertification() {
		return certification;
	}

	/**
	 * @param certification
	 *            the certification to set
	 */
	public void setCertification(String certification) {
		this.certification = certification;
	}

	/**
	 * @return the operateTime
	 */
	public String getOperateTime() {
		return operateTime;
	}

	/**
	 * @param operateTime
	 *            the operateTime to set
	 */
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * @return the fundLimit
	 */
	public Float getFundLimit() {
		return fundLimit;
	}

	/**
	 * @param fundLimit
	 *            the fundLimit to set
	 */
	public void setFundLimit(Float fundLimit) {
		this.fundLimit = fundLimit;
	}

	public FlbDO() {
		// TODO Auto-generated constructor stub
	}
}
