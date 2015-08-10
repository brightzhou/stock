package com.zeekie.stock.controller;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sitong.thinker.common.api.ApiResponse;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.CurrentEntrustDO;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.service.lhomes.CallhomesService;
import com.zeekie.stock.service.lhomes.entity.Homes400Resp;
import com.zeekie.stock.service.lhomes.entity.HomsEntity400;
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
	private DealMapper deal;

	@Autowired
	@Value("${stock.little.homes.limit.end}")
	private String endTime;

	@Autowired
	@Value("${stock.little.homes.limit.start}")
	private String startTime;

	@Autowired
	@Value("${stock.guess.start}")
	private String guessStartTime;

	@Autowired
	@Value("${stock.guess.end}")
	private String guessEndTime;

	@ResponseBody
	@RequestMapping("common/entrust")
	public String entrust(@RequestParam("nickname") String nickname, @RequestParam("stockCode") String stockCode,
			@RequestParam("entrustAmount") String entrustAmount, @RequestParam("entrustPrice") String entrustPrice,
			@RequestParam("entrustDirection") String entrustDirection) throws Exception {

		if (!DateUtil.compareDate(startTime, endTime)) {
			return "不在交易时间内";
			// return Constants.CODE_STOCK_NOTIN_DEAL_TIME;
		}

		Float value = Float.parseFloat(entrustPrice);
		if (value == 0f) {
			return "不允许以0元下单";
		} else {
			if (!verifyPrice(stockCode, value)) {
				return "输入价格非法";
			}
		}
		// 判断是否已经禁止买入
		if (StringUtils.equals(entrustDirection, "1")) {
			if (StringUtils.equals("1", deal.queryStopFlag(nickname))) {
				return Constants.CODE_STOCK_STOP;
			}
		}
		List<String> code = deal.queryAllStockCode();

		for (String item : code) {
			if (StringUtils.startsWith(stockCode, item) || StringUtils.equals(stockCode, item)) {
				if (log.isDebugEnabled()) {
					log.debug("不能买入" + stockCode + "的股票");
				}
				return "该股票限制买入";
				// return Constants.CODE_STOCK_lIMIT;
			}
		}
		return entrust.entrust(nickname, stockCode, entrustAmount, entrustPrice, entrustDirection);
	}

	@ResponseBody
	@RequestMapping("common/entrust/withdraw")
	public String entrustWithdraw(@RequestParam("nickname") String nickname,
			@RequestParam("entrustNo") String entrustNo) {
		return entrust.entrustWithdraw(nickname, entrustNo);
	}

	@ResponseBody
	@RequestMapping("combasset/query")
	public ApiResponse<?> queryCombasset(@RequestParam("nickname") String nickname) {
		JSONObject jo = entrust.queryCombasset(nickname);
		return (null != jo) ? ApiUtils.good(jo) : ApiUtils.good();
	}

	/**
	 * 当日委托查询
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public ApiResponse<?> queryEntrust(@RequestParam("nickname") String nickname) {
		try {
			JSONArray ja = entrust.queryEntrust(nickname);
			if (null != ja) {
				return ApiUtils.good(ja);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good(new JSONArray());
	}

	/**
	 * 历史委托查询
	 * 
	 * @param nickname
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("history/query")
	public ApiResponse<?> queryhistory(@RequestParam("nickname") String nickname,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		try {
			CurrentEntrustDO entrustDO = new CurrentEntrustDO();
			entrustDO.setNickName(nickname);
			entrustDO.setStartDate(StringUtil.isNotBlank(startDate) ? startDate + " 00:00:00" : null);
			entrustDO.setEndDate(StringUtil.isNotBlank(endDate) ? endDate + " 23:59:29" : null);
			entrustDO.setStatusArray(
					new String[] { "1", "4", "5", "6", "7", "8", "9", "a", "A", "B", "C", "D", "E", "F" });
			JSONArray ja = entrust.queryEntrustComm(entrustDO);
			if (null != ja) {
				return ApiUtils.good(ja);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good(new JSONArray());
	}

	/**
	 * 成交查询
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("traded/query")
	public ApiResponse<?> queryTraded(@RequestParam("nickname") String nickname) {
		try {
			JSONArray ja = entrust.tradedQuery(nickname);
			if (null != ja) {
				return ApiUtils.good(ja);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good(new JSONArray());
	}

	/**
	 * 历史成交查询
	 * 
	 * @param nickname
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("history/traded/query")
	public ApiResponse<?> queryHistoryTraded(@RequestParam("nickname") String nickname,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		try {
			CurrentEntrustDO entrustDO = new CurrentEntrustDO();
			entrustDO.setNickName(nickname);
			entrustDO.setStartDate(StringUtil.isNotBlank(startDate) ? (startDate + " 00:00:00") : null);
			entrustDO.setEndDate(StringUtil.isNotBlank(endDate) ? (endDate + " 23:59:29") : null);
			entrustDO.setAmentrustStatus("7");
			JSONArray ja = entrust.queryEntrustComm(entrustDO);
			if (null != ja) {
				return ApiUtils.good(ja);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good(new JSONArray());
	}

	/**
	 * 持仓查询
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("combostock/query")
	public ApiResponse<?> queryCombostock(@RequestParam("nickname") String nickname) {
		try {
			JSONArray jo = entrust.queryCombostock(nickname);
			if (null != jo) {
				return ApiUtils.good(jo);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good(new JSONArray());
	}

	/**
	 * 返回产品列表
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("currentProduct/query")
	public ApiResponse getProduct(@RequestParam("nickname") String nickname) {
		try {
			return ApiUtils.good(entrust.getProduct(nickname));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good(new JSONArray());
	}

	/**
	 * 返回竞猜产品
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("guess/enter")
	public ApiResponse enterGuessPage(@RequestParam("nickname") String nickname) {
		try {
			return ApiUtils.good(entrust.getGuessProduct(nickname));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ApiUtils.good(new JSONArray());
	}

	/**
	 * 进入购买页面获取单个哈哈币单价
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("hhb/purchase/enter")
	public String enterPurchaseHhbPage() {
		try {
			return deal.queryUnitPrice();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_SUCCESS;
	}

	/**
	 * 购买哈哈币
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("hhb/purchase")
	public String purchaseHhb(@RequestParam("nickname") String nickname, @RequestParam("num") String num,
			@RequestParam("cash") String cash) {
		try {
			return entrust.purchaseHhb(nickname, num, cash);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_SUCCESS;
	}

	/**
	 * 购买哈哈币
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("guess")
	public String guess(@RequestParam("nickname") String nickname, @RequestParam("num") String num,
			@RequestParam("type") String type, @RequestParam("bidCode") String bidCode) {
		try {
			if (!StringUtils.equals("rise", type) && !StringUtils.equals("fail", type)) {
				return Constants.CODE_GUESS_INVALID_TYPE;
			}
			if (!StringUtils.equals("Y", deal.queryGuessStatus())) {
				return Constants.CODE_GUESS_NOT_INTIME;
			}

			return entrust.updateGuess(nickname, num, type, bidCode);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 卖出哈哈币
	 * 
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping("hhb/sell")
	public String sell(@RequestParam("nickname") String nickname, @RequestParam("num") String num,
			@RequestParam("cash") String cash) {
		try {
			return entrust.sell(nickname, num, cash);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

	private boolean verifyPrice(String stockCode, Float value) {
		String exchangeType = StringUtils.startsWith(stockCode, "6") ? Constants.HOMES_EXCHANGE_TYPE_SH
				: Constants.HOMES_EXCHANGE_TYPE_S;
		HomsEntity400 entity400 = new HomsEntity400(stockCode, exchangeType);
		CallhomesService service = new CallhomesService(entity400);
		if (service.call400Fun()) {
			Homes400Resp resp = (Homes400Resp) service.getResponse(Constants.FN400);
			Float closePrice = resp.getClosePrice();
			Float endPrice = StringUtil.keepTwoDecimalFloat(Float.parseFloat((closePrice * 1.1) + ""));
			Float startPrice = StringUtil.keepTwoDecimalFloat(Float.parseFloat((closePrice * 0.9) + ""));
			return (startPrice <= value && value <= endPrice);
		}
		return false;
	}

	@ResponseBody
	@RequestMapping("guess/history/query")
	public ApiResponse getHistoryGuess(@RequestParam("userId") String userId, @RequestParam("offset") String offset) {
		return ApiUtils.good(entrust.getHistoryGuess(userId, offset));
	}

	@ResponseBody
	@RequestMapping("sign")
	public String sign(@RequestParam("userId") String userId) {
		return entrust.sign(userId);
	}

	@ResponseBody
	@RequestMapping("sign/query")
	public String querySign(@RequestParam("userId") String userId) {
		return entrust.querySignFlag(userId);
	}
}
