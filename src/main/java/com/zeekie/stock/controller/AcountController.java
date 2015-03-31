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
 */
@Controller
@RequestMapping("api/stock/account")
public class AcountController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AcountService operator;

	@ResponseBody
	@RequestMapping("ID/indentify")
	public String indentify(@RequestParam("nickname") String nickname,
			@RequestParam("truename") String truename,
			@RequestParam("idCard") String idCard) {
		return operator.indentify(nickname, truename, idCard) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("creditCard/bind")
	public String bindCreditCard(@RequestParam("userId") String userId,
			@RequestParam("telephone") String telephone,
			@RequestParam("bank") String bank,
			@RequestParam("number") String number,
			@RequestParam("bankCode") String bankCode) {
		return operator.bindCreditCard(userId, telephone, bank, number,
				bankCode) ? Constants.CODE_SUCCESS : Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("depositPwd/set")
	public String setDepositPwd(
			@RequestParam("nickname") String nickname,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam("depositPwd") String depositPwd) {
		return operator.setDepositPwd(nickname, telephone, depositPwd) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("depositPwd/modify")
	public String modifyDepositPwd(@RequestParam("userId") String userId,
			@RequestParam("depositPwd") String depositPwd,
			@RequestParam("telephone") String telephone,
			@RequestParam("verifyCode") String verifyCode) {
		return operator.modifyDepositPwd(userId, depositPwd, telephone,
				verifyCode);

	}

	@ResponseBody
	@RequestMapping("depositPwd/check")
	public String checkDepoist(@RequestParam("userId") String userId,
			@RequestParam("depositPwd") String depositPwd) {
		return operator.checkDepoist(userId, depositPwd);
	}

	@ResponseBody
	@RequestMapping("currentAccount/get")
	public ApiResponse getCurrentAccount(
			@RequestParam("nickname") String nickname) {
		return ApiUtils.good(operator.getCurrentAccount(nickname));
	}

	@ResponseBody
	@RequestMapping("recharge/fundFlow/record")
	public String record(@RequestParam("nickname") String nickname,
			@RequestParam("payAccount") String payAccount,
			@RequestParam("fund") String fund) {
		return operator.clientRecharge(nickname, fund, payAccount) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("withdrawPage/enter")
	public ApiResponse withdrawPageEnter(
			@RequestParam("nickname") String nickname) {
		Map<String, String> map = operator.withdrawPageEnter(nickname);
		if (StringUtils.equals(Constants.CODE_SUCCESS, map.get("flag"))) {
			return ApiUtils.good(map);
		}
		return ApiUtils.bad(new Message("10665825", map.get("msg")));
	}

	@ResponseBody
	@RequestMapping("fund/withdraw")
	public ApiResponse withdraw(@RequestParam("nickname") String nickname,
			@RequestParam("fund") String fund,
			@RequestParam("depositPwd") String depositPwd,
			@RequestParam("openBank") String openBank) {
		return ApiUtils.good(operator.withdraw(nickname, fund, depositPwd,
				openBank));
	}

	@ResponseBody
	@RequestMapping("userInfo/get")
	public ApiResponse getUserInfo(@RequestParam("userId") String userId) {
		JSONObject jo = operator.getUserInfo(userId);
		if (null != jo) {
			return ApiUtils.good(jo);
		}
		return ApiUtils.good();
	}

	@ResponseBody
	@RequestMapping("bankInfo/get")
	public ApiResponse getBankInfo(@RequestParam("userId") String userId) {
		JSONObject jo = operator.getBankInfo(userId);
		if (null != jo) {
			return ApiUtils.good(jo);
		}
		return ApiUtils.good();
	}
}
