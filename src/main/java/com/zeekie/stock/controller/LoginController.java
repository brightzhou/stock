package com.zeekie.stock.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sitong.thinker.common.api.ApiResponse;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.form.RegisterForm;
import com.zeekie.stock.msg.ReturnMsg;
import com.zeekie.stock.service.CommonService;
import com.zeekie.stock.util.ApiUtils;

/**
 * @Author zeekie
 * @date 2014年7月23日
 */
@Controller
@RequestMapping("api/stock")
public class LoginController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private CommonService operator;

	@ResponseBody
	@RequestMapping("login")
	public ApiResponse login(@RequestParam("nickname") String nickname,
			@RequestParam("password") String password) {
		return ApiUtils.good(operator.login(nickname, password));
	}
	
	@ResponseBody
	@RequestMapping("loginOff")
	public void loginOff(@RequestParam("nickname") String nickname) {
		 operator.loginOff(nickname);
	}

	@ResponseBody
	@RequestMapping("figure/login")
	public ApiResponse figureLogin(@RequestParam("nickname") String nickname,
			@RequestParam("figurePwd") String figurePwd) {
		return ApiUtils.good(operator.figureLogin(nickname,figurePwd));
	}

	@ResponseBody
	@RequestMapping("verifyCode/get")
	public String getVerifyCode(@RequestParam("telephone") String telephone,
			@RequestParam("source") String source) {
		return operator.genVerifyCode(telephone, source);
	}

	@ResponseBody
	@RequestMapping("register")
	public String register(RegisterForm form) {
		// if (StringUtils.isEmpty(form.getVerifyCode())) {
		// return Constants.CODE_ERROR_VERIFYCODE_MSG;
		// }
		ReturnMsg msg = operator.register(form);
		return StringUtils.equals(Constants.CODE_SUCCESS, msg.getCode()) ? Constants.CODE_SUCCESS
				: msg.getCode();
	}

	@ResponseBody
	@RequestMapping("forgetPwd/verifyCode/check")
	public String forgetPassword(@RequestParam("telephone") String telephone,
			@RequestParam("verifyCode") String verifyCode) {
		return operator.check(telephone, verifyCode,
				Constants.CODE_VERIFYCODE_SOURCE_FORGETPWD) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("forgetPwd/password/update")
	public String updatePassword(
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "oldPassword", required = false) String oldPassword,
			@RequestParam("newPassward") String newPassward) {
		return operator
				.updatePwd(nickname, telephone, oldPassword, newPassward) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("figurePwd/update")
	public String updateFigurePwd(@RequestParam("nickname") String nickname,
			@RequestParam("figurePwd") String figurePwd) {
		return operator.updateFigurePwd(nickname, figurePwd) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("telephone/update")
	public String updateTelephone(@RequestParam("nickname") String nickname,
			@RequestParam("telephone") String telephone,
			@RequestParam("newTelephone") String newTelephone,
			@RequestParam("verifyCode") String verifyCode) {
		return operator.updateTelephone(nickname, telephone, newTelephone,
				verifyCode);
	}

	@ResponseBody
	@RequestMapping("telephone/bind")
	public String bindTelephone(@RequestParam("nickname") String nickname,
			@RequestParam("telephone") String telephone,
			@RequestParam("verifyCode") String verifyCode) {
		return operator.bindTelephone(nickname, telephone, verifyCode);
	}

}
