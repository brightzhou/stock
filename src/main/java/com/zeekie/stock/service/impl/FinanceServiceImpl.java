package com.zeekie.stock.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import sitong.thinker.common.exception.ServiceInvokerException;

import com.zeekie.stock.respository.FinanceMapper;
import com.zeekie.stock.service.FinanceService;

public class FinanceServiceImpl implements FinanceService {

	@Autowired
	private FinanceMapper financeMapper;

	@Override
	public Map<String, String> getCurrentFinance()
			throws ServiceInvokerException {

		return null;
	}

}
