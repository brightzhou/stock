package com.zeekie.stock.controller;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sitong.thinker.common.api.ApiResponse;
import sitong.thinker.common.exception.ServiceInvokerException;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.form.FinanceProducetForm;
import com.zeekie.stock.service.FinanceService;
import com.zeekie.stock.util.ApiUtils;

/**
 * @Author zeekie
 * @date 2014年7月23日
 * 
 *       理财类
 */
@Controller
@RequestMapping("api/stock/finance")
public class StockFinanceController {

	private static final Logger log = LoggerFactory
			.getLogger(StockFinanceController.class);

	@Autowired
	private FinanceService operator;

	@ResponseBody
	@RequestMapping("get")
	public ApiResponse getCurrentFinance() {
		try {
			return ApiUtils.good(operator.getCurrentFinance());
		} catch (ServiceInvokerException e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good();
	}

	@ResponseBody
	@RequestMapping("save")
	public String saveCurrentFinance(FinanceProducetForm form) {
		try {
			return operator.saveCurrentFinance(form);
		} catch (ServiceInvokerException e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("history/get")
	public ApiResponse<?> getHistoryFinance(
			@RequestParam("userId") String userId,
			@RequestParam("offset") String offset) {
		try {
			JSONArray result = operator.getHistoryFinance(userId, offset);
			if (null != result) {
				return ApiUtils.good(result);
			}
		} catch (ServiceInvokerException e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good();
	}
}
