package com.zeekie.stock.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		return entrust
				.entrust(nickname, stockCode, entrustAmount, entrustPrice);
	}

	@ResponseBody
	@RequestMapping("common/entrust/withdraw")
	public String entrustWithdraw(@RequestParam("nickname") String nickname,
			@RequestParam("entrustNo") String entrustNo) {
		return entrust.entrustWithdraw(nickname,entrustNo);
	}

	@ResponseBody
	@RequestMapping("combasset/query")
	public ApiResponse<?> queryCombasset(
			@RequestParam("nickname") String nickname) {
		JSONObject jo = entrust.queryCombasset(nickname);
		if (null != jo) {
			return ApiUtils.good(jo);
		}
		return ApiUtils.good();
	}

	@ResponseBody
	@RequestMapping("query")
	public ApiResponse<?> queryEntrust(@RequestParam("nickname") String nickname) {
		JSONArray ja = entrust.queryEntrust(nickname);
		if (null != ja) {
			return ApiUtils.good(ja);
		}
		return ApiUtils.good();
	}

	@ResponseBody
	@RequestMapping("history/query")
	public ApiResponse<?> queryEntrustHistory(
			@RequestParam("nickname") String nickname,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		JSONArray ja = entrust
				.queryEntrustHistory(nickname, startDate, endDate);
		if (null != ja) {
			return ApiUtils.good(ja);
		}
		return ApiUtils.good();
	}

}
