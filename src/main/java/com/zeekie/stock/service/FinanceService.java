package com.zeekie.stock.service;

import java.util.Map;

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

}
