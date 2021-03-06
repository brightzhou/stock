package com.zeekie.stock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
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
import sitong.thinker.common.exception.ServiceInvokerException;

import com.zeekie.stock.Constants;
import com.zeekie.stock.chat.jersey.apidemo.EasemobMessages;
import com.zeekie.stock.entity.UserInfoDO;
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
	@RequestMapping("ID/get")
	public ApiResponse getId(@RequestParam("userId") String userId) {
		return ApiUtils.good(operator.getId(userId));
	}

	@ResponseBody
	@RequestMapping("creditCard/bind")
	public String bindCreditCard(@RequestParam("userId") String userId,
			@RequestParam("telephone") String telephone,
			@RequestParam("bank") String bank,
			@RequestParam("number") String number,
			@RequestParam("bankCode") String bankCode,
			@RequestParam(value = "code", required = false) String code) {
		return operator.bindCreditCard(userId, telephone, bank, number,
				bankCode, code) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("creditCard/get")
	public ApiResponse getBindCreditCard(@RequestParam("userId") String userId) {
		return ApiUtils.good(operator.getBindCreditCard(userId));
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
			@RequestParam("nickname") String nickname,
			@RequestParam(value = "version", required = false) String version) {
		return ApiUtils.good(operator.getCurrentAccount(nickname, version));
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

	@ResponseBody
	@RequestMapping("bankLimitation/get")
	public ApiResponse getBankLimitation(@RequestParam("code") String code) {
		JSONObject jo = operator.getBankLimitation(code);
		return (null != jo) ? ApiUtils.good(jo) : ApiUtils.good();
	}

	@ResponseBody
	@RequestMapping("basicInfo/get")
	public ApiResponse getBasicInfo(@RequestParam("userId") String userId) {
		JSONObject jo = operator.getBasicInfo(userId);
		return (null != jo) ? ApiUtils.good(jo) : ApiUtils.good();
	}

	@ResponseBody
	@RequestMapping("idCard/exists")
	public String getDuplicateIdCard(@RequestParam("idCard") String idCard) {
		try {
			return operator.getDuplicateIdCard(idCard);
		} catch (ServiceInvokerException e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_SUCCESS;
	}

	@ResponseBody
	@RequestMapping("getDownUser")
	public ApiResponse getDownUser(@RequestParam("userId") String userId,
			@RequestParam("offset") String offset) {
		return ApiUtils.good(operator.getDownUser(userId, offset));
	}

	@ResponseBody
	@RequestMapping("hx/getCustomerCare")
	public ApiResponse getCustomerCare() {
		return ApiUtils.good(operator.getCustomerCare());
	}

	@ResponseBody
	@RequestMapping("hx/advise")
	public String advise(@RequestParam("id") String id,
			@RequestParam("username") String username,
			@RequestParam("servicename") String servicename,
			@RequestParam("grade") String grade) {
		return operator.advise(id, username, servicename, grade);
	}

	@ResponseBody
	@RequestMapping("hx/sendCMDMsg")
	public void sendCMDMsg(@RequestParam("serviceid") String serviceid,
			@RequestParam("newServiceId") String newServiceId) {
		EasemobMessages.sendCMDMsg(serviceid, newServiceId);
	}
	
	@ResponseBody
	@RequestMapping("hx/queryNicknameByids")
	public String queryNicknameByids(@RequestParam("ids") String ids,@RequestParam("callbackparam") String callbackparam,HttpServletResponse response ) {
	     List<UserInfoDO> list = operator.queryUserInfosByids(ids); 
	     List<Map<String, String>>  resultList = new ArrayList<Map<String,String>>() ;
	     Map<String, String>  info = null;
	     if(list!=null){
	    	 for (UserInfoDO userInfoDO : list) {
	    		  info = new HashMap<String, String>();
	    		  info.put("userId",userInfoDO.getUserId());
	    		  info.put("nickname", userInfoDO.getNickname());
				  resultList.add(info);
			 }
	     }
	     return callbackparam + "("+JSONArray.fromObject(resultList).toString()+")" ;
	     
	     //return callbackparam + "({\"KK\":123})" ;
	}
	
	@ResponseBody
	@RequestMapping("hhb/flow")
	public ApiResponse hhbFlow(@RequestParam("userId") String userId,@RequestParam("offset") String offset) {
	      return ApiUtils.good(operator.getHhbFlow(userId, offset));
	    
	}
	
 
	
	
}
