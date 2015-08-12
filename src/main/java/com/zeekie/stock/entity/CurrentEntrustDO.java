package com.zeekie.stock.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class CurrentEntrustDO extends BaseEntrustDO {

	/**
	 * 交易市场
	 */
	private String exchangeType;

	/**
	 * 买卖方向
	 */
	private String entrustDirection;

	/**
	 * 证劵代码
	 */
	private String stockCode;

	/**
	 * 委托状态
	 */
	private String amentrustStatus;

	/**
	 * 委托价格
	 */
	private Float entrustPrice;

	/**
	 * 委托数量
	 */
	private String entrustAmount;

	/**
	 * 委托编号
	 */
	private String entrustNo;

	/**
	 * 成交价格
	 */
	private Float businessBalance;

	/**
	 * 成交数量
	 */
	private String businessAmount;
	
	/**
	 * 默认成交时间
	 */
	private String businessTime="130101";
	
	/**
	 * 委托日期
	 */
	private String entrustDate = "20100101";

	/**
	 * 委托时间
	 */
	private String entrustTime = "130101";

	/**
	 * 委托日期
	 */
	private String entrusteDate = "20100101";

	/**
	 * 委托时间
	 */
	private String entrusteTime = "130101";

	/**
	 * 废单原因
	 */
	private String cancelInfo;

	/**
	 * 开始时间
	 */
	private String startDate;

	/**
	 * 结束时间
	 */
	private String endDate;
	
	

	/**
	 * @return the businessTime
	 */
	public String getBusinessTime() {
		return businessTime;
	}

	/**
	 * @param businessTime the businessTime to set
	 */
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public String getEntrusteDate() {
		return entrusteDate;
	}

	public void setEntrusteDate(String entrusteDate) {
		this.entrusteDate = entrusteDate;
	}

	public String getEntrusteTime() {
		return entrusteTime;
	}

	public void setEntrusteTime(String entrusteTime) {
		if (StringUtils.isNotBlank(entrusteTime)) {
			if (entrusteTime.trim().length() < 6) {
				this.entrusteTime = "0" + entrusteTime;
			}
		}else{
			this.entrusteTime = entrusteTime.trim();
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 状态集合
	 */
	private String[] statusArray;

	public String[] getStatusArray() {
		return statusArray;
	}

	public void setStatusArray(String[] statusArray) {
		this.statusArray = statusArray;
	}

	/**
	 * @return the exchangeType
	 */
	public String getExchangeType() {
		return exchangeType;
	}

	/**
	 * @param exchangeType
	 *            the exchangeType to set
	 */
	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	/**
	 * @return the entrustDirection
	 */
	public String getEntrustDirection() {
		return entrustDirection;
	}

	/**
	 * @param entrustDirection
	 *            the entrustDirection to set
	 */
	public void setEntrustDirection(String entrustDirection) {
		this.entrustDirection = entrustDirection;
	}

	/**
	 * @return the stockCode
	 */
	public String getStockCode() {
		return stockCode;
	}

	/**
	 * @param stockCode
	 *            the stockCode to set
	 */
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	/**
	 * @return the amentrustStatus
	 */
	public String getAmentrustStatus() {
		return amentrustStatus;
	}

	/**
	 * @param amentrustStatus
	 *            the amentrustStatus to set
	 */
	public void setAmentrustStatus(String amentrustStatus) {
		this.amentrustStatus = amentrustStatus;
	}

	/**
	 * @return the entrustPrice
	 */
	public Float getEntrustPrice() {
		return entrustPrice;
	}

	/**
	 * @param entrustPrice
	 *            the entrustPrice to set
	 */
	public void setEntrustPrice(Float entrustPrice) {
		this.entrustPrice = entrustPrice;
	}

	/**
	 * @return the entrustAmount
	 */
	public String getEntrustAmount() {
		return entrustAmount;
	}

	/**
	 * @param entrustAmount
	 *            the entrustAmount to set
	 */
	public void setEntrustAmount(String entrustAmount) {
		this.entrustAmount = entrustAmount;
	}

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

	/**
	 * @return the businessBalance
	 */
	public Float getBusinessBalance() {
		return businessBalance;
	}

	/**
	 * @param businessBalance
	 *            the businessBalance to set
	 */
	public void setBusinessBalance(Float businessBalance) {
		this.businessBalance = businessBalance;
	}

	/**
	 * @return the businessAmount
	 */
	public String getBusinessAmount() {
		return businessAmount;
	}

	/**
	 * @param businessAmount
	 *            the businessAmount to set
	 */
	public void setBusinessAmount(String businessAmount) {
		this.businessAmount = businessAmount;
	}

	/**
	 * @return the entrustDate
	 */
	public String getEntrustDate() {
		return entrustDate;
	}

	/**
	 * @param entrustDate
	 *            the entrustDate to set
	 */
	public void setEntrustDate(String entrustDate) {
		this.entrustDate = entrustDate;
	}

	/**
	 * @return the entrustTime
	 */
	public String getEntrustTime() {
		return entrustTime;
	}

	/**
	 * @param entrustTime
	 *            the entrustTime to set
	 */
	public void setEntrustTime(String entrustTime) {
		if (StringUtils.isNotBlank(entrustTime)) {
			entrustTime = entrustTime.trim();
			if (entrustTime.length() < 6) {
				this.entrusteTime = "0" + entrustTime;
			}
		}else{
			this.entrustTime = entrustTime.trim();
		}
	}

	/**
	 * @return the cancelInfo
	 */
	public String getCancelInfo() {
		return cancelInfo;
	}

	/**
	 * @param cancelInfo
	 *            the cancelInfo to set
	 */
	public void setCancelInfo(String cancelInfo) {
		this.cancelInfo = cancelInfo;
	}

	public CurrentEntrustDO() {
		// TODO Auto-generated constructor stub
	}

}
