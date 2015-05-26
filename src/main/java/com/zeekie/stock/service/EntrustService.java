package com.zeekie.stock.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	 * @param entrustDirection
	 *            委托方向 1：买入 2：卖出
	 * @return 1:成功/0：失败
	 */
	String entrust(String nickname, String stockCode, String entrustAmount,
			String entrustPrice, String entrustDirection);

	/**
	 * 撤销委托
	 * 
	 * @param nickname
	 *            昵称
	 * @param entrustNo
	 *            委托编号
	 * @return
	 */
	String entrustWithdraw(String nickname, String entrustNo);

	/**
	 * 账户资金查询
	 * 
	 * @param nickname
	 * @return
	 */
	JSONObject queryCombasset(String nickname);

	/**
	 * 当日委托查询
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray queryEntrust(String nickname);

	/**
	 * 获取历史委托
	 * 
	 * @param nickname
	 * @param endDate
	 *            委托结束时间
	 * @param startDate
	 *            委托开始时间
	 * @return
	 */
	JSONArray queryEntrustHistory(String nickname, String startDate,
			String endDate);

	/**
	 * 股票持仓查询
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray queryStockPositon(String nickname);

}
