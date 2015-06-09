package com.zeekie.stock.controller;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sitong.thinker.common.api.ApiResponse;
import sitong.thinker.common.error.Message;

import com.zeekie.stock.Constants;
import com.zeekie.stock.service.AcountService;
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

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AcountService operator;


	@ResponseBody
	@RequestMapping("ID/get")
	public ApiResponse getId(@RequestParam("userId") String userId) {
		return ApiUtils.good(operator.getId(userId));
	}

	
}
