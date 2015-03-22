package com.zeekie.stock.service;

import java.util.List;
import java.util.Map;

import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.zeekie.stock.entity.HistoryOperationDO;
import com.zeekie.stock.entity.form.AddCuaranteeForm;
import com.zeekie.stock.entity.form.TradeForm;

public interface TradeService {

	/**
	 * 开始操盘(进入操盘页面)
	 * 
	 * @param nickname
	 * @param tradeFund
	 *            操盘金额
	 * @return
	 * @throws T2SDKException
	 */
	public Map<String, String> startOperate(String nickname, String tradeFund);

	/**
	 * 保存操盘信息
	 * 
	 * @param tradeForm
	 * @return
	 */
	public Map<String, String> storeOperationInfo(TradeForm tradeForm);

	/**
	 * 获取当前操盘信息
	 * 
	 * @param nickname
	 * @return
	 */
	public Map<String, String> getCurrentOperation(String nickname);

	/**
	 * 获取操盘账户信息
	 * 
	 * @param nickname
	 * @return
	 */
	public Map<String, String> getOperateAcount(String nickname);

	/**
	 * 进入新增保证金页面
	 * 
	 * @param nickname
	 * @return
	 */
	public Map<String, String> enterAddGuaranteePage(String nickname,
			String addGuranteeCash);

	/**
	 * 新增保证金
	 * 
	 * @param nickname
	 * @return
	 */
	public Map<String, String> addCuarantee(AddCuaranteeForm addCuaranteeForm)
			throws RuntimeException;

	/**
	 * 设置自动增加保证金
	 * 
	 * @param nickname
	 * @return
	 */
	public boolean setAutoAddCuaranteeCash(String nickname);

	/**
	 * 获取历史操盘
	 * 
	 * @param nickname
	 * @param offset
	 * @return
	 */
	public List<HistoryOperationDO> getHistoryOperation(String nickname, String offset);

	/**
	 * 获取当前用户是否被强制结束操盘过
	 * 
	 * @param nickname
	 * @return
	 */
	public Map<String, String> getEveningFlag(String nickname);

}
