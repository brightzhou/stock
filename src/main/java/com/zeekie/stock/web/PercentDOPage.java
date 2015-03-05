package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class PercentDOPage extends PageQuery {

	private static final long serialVersionUID = 2767775110683210541L;

	private String assetName;

	public PercentDOPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String assetName) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.assetName = assetName;
	}

	/**
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetName
	 *            the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

}
