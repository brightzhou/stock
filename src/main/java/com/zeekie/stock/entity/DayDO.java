package com.zeekie.stock.entity;

public class DayDO {

	private String day;

	private String yearMonth;

	private String feeDate;

	public DayDO() {
		// TODO Auto-generated constructor stub
	}

	public DayDO(String day, String yearMonth) {
		this.day = day;
		this.yearMonth = yearMonth;
		this.feeDate = yearMonth + "-" + day;
	}

	/**
	 * @return the feeDate
	 */
	public String getFeeDate() {
		return feeDate;
	}

	/**
	 * @param feeDate
	 *            the feeDate to set
	 */
	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}

	/**
	 * @return the yearMonth
	 */
	public String getYearMonth() {
		return yearMonth;
	}

	/**
	 * @param yearMonth
	 *            the yearMonth to set
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

}
