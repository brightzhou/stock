package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class StatisticsPage extends PageQuery {

	private static final long serialVersionUID = 2767775110683210541L;
	
	private String Day ;

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public StatisticsPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String Day) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.Day = Day;
	}

	 

}
