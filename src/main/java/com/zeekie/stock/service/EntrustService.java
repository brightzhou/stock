package com.zeekie.stock.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zeekie.stock.entity.CurrentEntrustDO;

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
	 * 成交查询
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray tradedQuery(String nickname);
	
	/**
	 * 历史成交查询
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray historyTradedQuery(String nickname);
    /**
     * 持仓查询
     * @param nickname
     * @return
     */
	JSONArray queryCombostock(String nickname);
	 /**
     * 委托查询公交服务接口
     * @param nickname
     * @return
     */
	JSONArray queryEntrustComm(CurrentEntrustDO entrustDO);
}
