package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class FinanceDetailPage extends PageQuery {

	private static final long serialVersionUID = 2767775110683210541L;

	private String productCode;

	public FinanceDetailPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String productCode) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.productCode = productCode;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

}
