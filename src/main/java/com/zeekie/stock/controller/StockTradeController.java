package com.zeekie.stock.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sitong.thinker.common.api.ApiResponse;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.form.AddCuaranteeForm;
import com.zeekie.stock.entity.form.TradeForm;
import com.zeekie.stock.service.AcountService;
import com.zeekie.stock.service.TradeService;
import com.zeekie.stock.util.ApiUtils;

/**
 * @Author zeekie
 * @date 2014年7月23日
 */
@Controller
@RequestMapping("api/stock/trade")
public class StockTradeController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TradeService trade;

	@Autowired
	private AcountService account;

	@Autowired
	@Value("${apk_down_path}")
	private String apkPath;

	@Autowired
	@Value("${apk_name}")
	private String apk_name;

	@Autowired
	@Value("${stock_apk_down_url}")
	private String stock_apk_down_url;

	@ResponseBody
	@RequestMapping("operation/start")
	public ApiResponse startOperate(@RequestParam("nickname") String nickname,
			@RequestParam("tradeFund") String tradeFund) {
		return ApiUtils.good(trade.startOperate(nickname, tradeFund));
	}

	@ResponseBody
	@RequestMapping("operation/info/store")
	public ApiResponse storeOperationInfo(TradeForm tradeForm) {
		return ApiUtils.good(trade.storeOperationInfo(tradeForm));
	}

	@ResponseBody
	@RequestMapping("operation/current/info/get")
	public ApiResponse getCurrentOperation(
			@RequestParam("nickname") String nickname) {
		return ApiUtils.good(trade.getCurrentOperation(nickname));

	}

	@ResponseBody
	@RequestMapping("operation/account/get")
	public ApiResponse getOperateAcount(String nickname) {
		return ApiUtils.good(trade.getOperateAcount(nickname));
	}

	@ResponseBody
	@RequestMapping("operation/history/get")
	public ApiResponse getHistoryOperation(
			@RequestParam("nickname") String nickname,
			@RequestParam("offset") String offset) {
		return ApiUtils.good(trade.getHistoryOperation(nickname, offset));
	}

	@ResponseBody
	@RequestMapping("operation/guaranteeCash/add/enter")
	public ApiResponse enter(
			@RequestParam("nickname") String nickname,
			@RequestParam(value = "addGuranteeCash", required = false) String addGuranteeCash) {
		return ApiUtils.good(trade.enterAddGuaranteePage(nickname,
				addGuranteeCash));
	}

	@ResponseBody
	@RequestMapping("operation/guaranteeCash/add")
	public ApiResponse add(AddCuaranteeForm addCuaranteeForm) {
		return ApiUtils.good(trade.addCuarantee(addCuaranteeForm));
	}

	@ResponseBody
	@RequestMapping("operation/guaranteeCash/autoAdd")
	public String autoAdd(@RequestParam("nickname") String nickname) {
		return trade.setAutoAddCuaranteeCash(nickname) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("operation/fundFlow/get")
	public ApiResponse getFundFlow(@RequestParam("nickname") String nickname,
			@RequestParam("offset") String offset) {
		return ApiUtils.good(account.getFundFlow(nickname, offset));
	}

	@ResponseBody
	@RequestMapping("operation/end")
	public ApiResponse endStock(@RequestParam("nickname") String nickname) {
		return ApiUtils.good(account.endStock(nickname,""));
	}

	@ResponseBody
	@RequestMapping("spreadPage/enter")
	public ApiResponse enterSpreadPage(@RequestParam("nickname") String nickname) {
		return ApiUtils.good(account.enterSpreadPage(nickname));
	}

	@ResponseBody
	@RequestMapping("spreed")
	public String spread(@RequestParam("refereeId") String refereeId,
			@RequestParam("redPacket") String redPacket) {
		return account.spread(refereeId, redPacket) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("deductDebt")
	public String deductDebt(@RequestParam("nickname") String nickname) {
		return account.deductDebt(nickname);
	}

	@ResponseBody
	@RequestMapping("wallet/get")
	public ApiResponse getWallet(@RequestParam("nickname") String nickname) {
		return ApiUtils.good(account.getWallet(nickname));
	}

	@ResponseBody
	@RequestMapping("enterApkPage")
	public ModelAndView enterDownloadApkPage(HttpServletRequest req,
			String refereeId) {

		ModelAndView view = new ModelAndView("downloadApk");
		view.addObject("refereeId", refereeId);
		view.addObject("apkPath", stock_apk_down_url + apk_name);
		return view;
	}

	@ResponseBody
	@RequestMapping("eveningFlag/get")
	public ApiResponse getEveningFlag(@RequestParam("nickname") String nickname) {
		return ApiUtils.good(trade.getEveningFlag(nickname));
	}
}
