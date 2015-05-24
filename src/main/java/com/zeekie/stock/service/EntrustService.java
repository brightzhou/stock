package com.zeekie.stock.service;

public interface EntrustService {

	/**
	 * 普通委托
	 * 
	 * @param nickname
	 *            昵称
	 * @param stockCode
	 *            股票代码
	 * @param entrustAmount
	 *            股票数目
	 * @param entrustPrice
	 *            股票单价
	 * @return 1:成功/0：失败
	 */
	String entrust(String nickname, String stockCode, String entrustAmount,
			String entrustPrice);

	/**
	 * 撤销委托
	 * 
	 * @param nickname
	 *            昵称
	 * @return
	 */
	String entrustWithdraw(String nickname);

	/**
	 * 账户资金查询
	 * 
	 * @param nickname
	 * @return
	 */
	String queryCombasset(String nickname);

	
	/**
	 * 当日委托查询
	 * @param nickname
	 * @return
	 */
	String queryEntrust(String nickname);

}
