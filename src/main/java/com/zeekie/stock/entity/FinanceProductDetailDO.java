package com.zeekie.stock.entity;

public class FinanceProductDetailDO extends HistoryFinanceDO {

	/**
	 * 用户昵称
	 */
	private String nickname;

	/**
	 * 购买日期
	 */
	private String buyDate;

	/**
	 * 产品名称
	 */
	private String financeProduct;

	/**
	 * 产品代码
	 */
	private String productCode;

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
	 * @return the buyDate
	 */
	public String getBuyDate() {
		return buyDate;
	}

	/**
	 * @param buyDate
	 *            the buyDate to set
	 */
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	/**
	 * @return the financeProduct
	 */
	public String getFinanceProduct() {
		return financeProduct;
	}

	/**
	 * @param financeProduct
	 *            the financeProduct to set
	 */
	public void setFinanceProduct(String financeProduct) {
		this.financeProduct = financeProduct;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public FinanceProductDetailDO() {
		// TODO Auto-generated constructor stub
	}
}
