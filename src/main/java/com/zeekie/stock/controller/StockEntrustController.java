package com.zeekie.stock.controller;

import java.util.Date;

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

import com.zeekie.stock.entity.CurrentEntrustDO;
import com.zeekie.stock.service.AcountService;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.DateUtil;
import com.zeekie.stock.util.StringUtil;

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
	public String entrustWithdraw(@RequestParam("nickname") String nickname) {
		return entrust.entrustWithdraw(nickname);
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

    /**
     * 当日委托查询
     * @param nickname
     * @return
     */
	@ResponseBody
	@RequestMapping("query")
	public ApiResponse<?> queryEntrust(@RequestParam("nickname") String nickname) {
		CurrentEntrustDO entrustDO = new CurrentEntrustDO();
        entrustDO.setNickName(nickname);
        entrustDO.setStartDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd")+" 00:00:00");
        entrustDO.setEndDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd")+" 23:59:59");
        entrustDO.setStatusArray(new String[] {"1","4","5","6","7","8","9","a","A","B","C","D","E","F"});
		JSONArray ja = entrust.queryEntrustComm(entrustDO);
		if (null != ja) {
			return ApiUtils.good(ja);
		}
		return ApiUtils.good(new JSONArray());
	}
    /**
     * 历史委托查询
     * @param nickname
     * @param startDate
     * @param endDate
     * @return
     */
	@ResponseBody
	@RequestMapping("history/query")
	public ApiResponse<?> queryhistory(@RequestParam("nickname") String nickname,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		CurrentEntrustDO entrustDO = new CurrentEntrustDO();
        entrustDO.setNickName(nickname);
        entrustDO.setStartDate(StringUtil.isNotBlank(startDate)?startDate+" 00:00:00":null);
        entrustDO.setEndDate(StringUtil.isNotBlank(endDate)?endDate+" 23:59:29":null);
        entrustDO.setStatusArray(new String[] {"1","4","5","6","7","8","9","a","A","B","C","D","E","F"});
		JSONArray ja = entrust.queryEntrustComm(entrustDO);
		if (null != ja) {
			return ApiUtils.good(ja);
		}
		return ApiUtils.good(new JSONArray());
	}
	/**
	 * 成交查询
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("traded/query")
	public ApiResponse<?> queryTraded(@RequestParam("nickname") String nickname) {
		CurrentEntrustDO entrustDO = new CurrentEntrustDO();
        entrustDO.setNickName(nickname);
        entrustDO.setStartDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd")+" 00:00:00");
        entrustDO.setEndDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd")+" 23:59:59");
        entrustDO.setAmentrustStatus("7");
		JSONArray ja = entrust.queryEntrustComm(entrustDO);
		if (null != ja) {
			return ApiUtils.good(ja);
		}
		return ApiUtils.good(new JSONArray());
	}
    /**
     * 历史成交查询
     * @param nickname
     * @param startDate
     * @param endDate
     * @return
     */
	@ResponseBody
	@RequestMapping("history/traded/query")
	public ApiResponse<?> queryHistoryTraded(@RequestParam("nickname") String nickname,@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        CurrentEntrustDO entrustDO = new CurrentEntrustDO();
        entrustDO.setNickName(nickname);
        entrustDO.setStartDate(StringUtil.isNotBlank(startDate)?(startDate+" 00:00:00"):null);
        entrustDO.setEndDate(StringUtil.isNotBlank(endDate)?(endDate+" 23:59:29"):null);
        entrustDO.setAmentrustStatus("7");
		JSONArray ja = entrust.queryEntrustComm(entrustDO);
		if (null != ja) {
			return ApiUtils.good(ja);
		}
		return ApiUtils.good(new JSONArray());
	}
	/**
	 * 持仓查询
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("combostock/query")
	public ApiResponse<?> queryCombostock(@RequestParam("nickname") String nickname) {
		JSONArray jo = entrust.queryCombostock(nickname);
		if (null != jo) {
			return ApiUtils.good(jo);
		}
		return ApiUtils.good();
	}

}
