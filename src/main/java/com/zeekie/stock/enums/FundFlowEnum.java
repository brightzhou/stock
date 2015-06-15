package com.zeekie.stock.enums;

public enum FundFlowEnum {

	FINANCE_INCOME("160", "理财[{0}]当日收益"),FINANCE_CAPATAL("170","理财[{0}]返还本金"),FINANCE_CAPATAL_BUY("180","购买理财[{0}]");

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
