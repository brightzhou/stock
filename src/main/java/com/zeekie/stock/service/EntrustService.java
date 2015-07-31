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
	 * 成交查询
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray tradedQuery(String nickname);

	/**
	 * 持仓查询
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray queryCombostock(String nickname);

	/**
	 * 委托查询公交服务接口
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray queryEntrustComm(CurrentEntrustDO entrustDO);

	/**
	 * 返回产品
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray getProduct(String nickname);

	/**
	 * 购买哈哈币
	 * 
	 * @param nickname
	 * @param num
	 * @param unitPrice
	 * @return
	 */
	String purchaseHhb(String nickname, String num, String unitPrice);

	/**
	 * 
	 * @param nickname
	 * @param num
	 * @param type
	 * @return
	 */
	String guess(String nickname, String num, String type);

	/**
	 * 返回当前竞猜的产品
	 * @param nickname 
	 * 
	 * @return
	 */
	JSONObject getGuessProduct(String nickname);

}
