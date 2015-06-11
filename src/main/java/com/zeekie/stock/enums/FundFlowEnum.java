package com.zeekie.stock.enums;

import org.apache.commons.lang.StringUtils;

public enum FundFlowEnum {

	FINANCE_INCOME("160", "当日理财收益");

	private String type;

	private String desc;

	FundFlowEnum(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

}
