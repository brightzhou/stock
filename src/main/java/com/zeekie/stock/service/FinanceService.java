package com.zeekie.stock.service;

import java.util.Map;

import net.sf.json.JSONArray;

import com.zeekie.stock.entity.form.FinanceProducetForm;

import sitong.thinker.common.exception.ServiceInvokerException;

public interface FinanceService {

	/**
	 * 获取当前理财产品
	 * 
	 * @return
	 * @throws ServiceInvokerException
	 */
	public Map<String, String> getCurrentFinance()
			throws ServiceInvokerException;

	/**
	 * 保存理财产品
	 * 
	 * @param form
	 * 
	 * @return
	 */
	public String saveCurrentFinance(FinanceProducetForm form)
			throws ServiceInvokerException;

	/**
	 * 获取历史理财记录
	 * 
	 * @param userId
	 * @param offset
	 * @return JSONArray
	 */
	public JSONArray getHistoryFinance(String userId, String offset)
			throws ServiceInvokerException;

	/**
	 * 设置是理财还是操盘
	 * 
	 * @param userId
	 * @param isStock
	 *            0:配资 1：理财
	 * @return 1:成功 0：失败
	 * @throws ServiceInvokerException
	 */
	public String updateStatus(String userId, String isStock)
			throws ServiceInvokerException;


}
