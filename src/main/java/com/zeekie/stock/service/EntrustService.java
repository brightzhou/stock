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
	 * @param flag
	 *            区别是历史委托还是历史成绩查询
	 * 
	 * @param nickname
	 * @return
	 */
	JSONArray queryEntrustComm(CurrentEntrustDO entrustDO, String flag);

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
	 * @return
	 */
	String purchaseHhb(String nickname, String num, String cash);

	/**
	 * 
	 * @param nickname
	 * @param num
	 * @param type
	 * @param bidCode
	 * @return
	 */
	String updateGuess(String nickname, String num, String type, String bidCode);

	/**
	 * 返回当前竞猜的产品
	 * 
	 * @param nickname
	 * 
	 * @return
	 */
	JSONObject getGuessProduct(String nickname);

	/**
	 * 卖出哈哈币
	 * 
	 * @param nickname
	 * @param num
	 * @param cash
	 * @return
	 */
	String sell(String nickname, String num, String cash);

	/**
	 * 获取历史下注记录
	 * 
	 * @param userId
	 * @param offset
	 * @return
	 */
	JSONArray getHistoryGuess(String userId, String offset);

	/**
	 * 签到
	 * 
	 * @param nickname
	 * @return
	 */
	String sign(String nickname);

	/**
	 * 查询签到情况
	 * 
	 * @param userId
	 * @return
	 */
	String querySignFlag(String userId);

}
