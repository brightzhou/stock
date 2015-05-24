package com.zeekie.stock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sitong.thinker.common.api.ApiResponse;

import com.zeekie.stock.service.AcountService;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.util.ApiUtils;

/**
 * @Author zeekie
 * @date 2015年5月22日
 * 
 *       处理HOMES交易
 */
@Controller
@RequestMapping("api/stock/entrust")
public class StockEntrustController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private EntrustService entrust;

	@Autowired
	private AcountService account;

	@ResponseBody
	@RequestMapping("common/entrust")
	public String entrust(@RequestParam("nickname") String nickname,
			@RequestParam("stockCode") String stockCode,
			@RequestParam("entrustAmount") String entrustAmount,
			@RequestParam("entrustPrice") String entrustPrice) {
		return entrust.entrust(nickname, stockCode, entrustAmount, entrustPrice);
	}

	@ResponseBody
	@RequestMapping("common/entrust/withdraw")
	public String entrustWithdraw(@RequestParam("nickname") String nickname) {
		return entrust.entrustWithdraw(nickname);
	}

	@ResponseBody
	@RequestMapping("combasset/query")
	public ApiResponse<?> queryCombasset(
			@RequestParam("nickname") String nickname) {
		return ApiUtils.good(entrust.queryCombasset(nickname));

	}
	
	@ResponseBody
	@RequestMapping("entrust/query")
	public ApiResponse<?> queryEntrust(
			@RequestParam("nickname") String nickname) {
		return ApiUtils.good(entrust.queryEntrust(nickname));

	}
}
