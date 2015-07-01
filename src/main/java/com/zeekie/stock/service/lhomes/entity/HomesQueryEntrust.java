package com.zeekie.stock.service.lhomes.entity;

import java.util.Date;

import org.apache.tools.ant.util.DateUtils;

import com.zeekie.stock.util.StringUtil;

public class HomesQueryEntrust extends AHomesEntity {

	private int startDate = StringUtil.StringToInteger(DateUtils.format(
			new Date(), "yyyymmdd"));

	private int endDate = StringUtil.StringToInteger(DateUtils.format(
			new Date(), "yyyymmdd"));

	private int cxRowcount = 10;

	private int pageNo = 1;

	public HomesQueryEntrust(String investAccount, String clientNo,
			int startDate, int endDate, int cxRowcount, int pageNo) {
		super(investAccount, clientNo);
		this.startDate = startDate;
		this.endDate = endDate;
		this.cxRowcount = cxRowcount;
		this.pageNo = pageNo;
	}

	public HomesQueryEntrust(String fundAccount, String combineId) {
		super(fundAccount, combineId);
	}

	/**
	 * @return the startDate
	 */
	public int getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public int getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the cxRowcount
	 */
	public int getCxRowcount() {
		return cxRowcount;
	}

	/**
	 * @param cxRowcount
	 *            the cxRowcount to set
	 */
	public void setCxRowcount(int cxRowcount) {
		this.cxRowcount = cxRowcount;
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}
