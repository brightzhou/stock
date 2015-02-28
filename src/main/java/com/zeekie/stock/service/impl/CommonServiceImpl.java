package com.zeekie.stock.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.LoginDO;
import com.zeekie.stock.entity.form.RegisterForm;
import com.zeekie.stock.enums.UserInfo;
import com.zeekie.stock.msg.ReturnMsg;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.StockMapper;
import com.zeekie.stock.service.CommonService;
import com.zeekie.stock.service.sensitive.SensitivewordFilter;
import com.zeekie.stock.service.xinge.StockMsg;
import com.zeekie.stock.service.xinge.XingePush;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.DateUtil;
import com.zeekie.stock.util.StringUtil;

@Service
public class CommonServiceImpl implements CommonService {

	static Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	private StockMapper trade;

	@Autowired
	private AcountMapper account;

	@Autowired
	private SensitivewordFilter filter;

	@Autowired
	@Value("${num}")
	private String num;

	@Autowired
	@Value("${interval}")
	private String interval;

	@Autowired
	@Value("${templateId}")
	private String templateId;

	@Override
	public Map<String, String> login(String nickname, String password) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			String userId = trade.queryLoginStatus(nickname);
			xingePush(userId);
			LoginDO loginDO = trade.login(nickname, password);
			setResult(result, loginDO, userId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public ReturnMsg register(RegisterForm register) {
		try {
			// 校验验证码是否有效
			// if (!validVerifyCode(register.getTelephone(),
			// register.getVerifyCode(),
			// Constants.CODE_VERIFYCODE_SOURCE_REGISTER)) {
			// return new ReturnMsg(Constants.CODE_ERROR_VERIFYCODE,
			// Constants.CODE_ERROR_VERIFYCODE_MSG);
			// }
			// 校验该昵称和电话是否唯一
			String registerName = register.getNickname();
			// 校验是否存在敏感词汇
			if (filter.isContaintSensitiveWord(registerName, 1)) {
				return new ReturnMsg(Constants.CODE_ERROR_NICKNAME,
						Constants.CODE_ERROR_NICKNAME_MSG);
			}

			if (StringUtils.isNotBlank(trade.isExists(registerName,
					register.getTelephone()))) {
				return new ReturnMsg(Constants.CODE_ERROR_NICKNAME,
						Constants.CODE_ERROR_NICKNAME_MSG);
			}
			trade.register(register);

			// 建立空账户为注册用户
			trade.setBlankWallet(register.getNickname());

			// 插入比例
			account.bindPercent(register.getNickname());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ReturnMsg(Constants.CODE_FAILURE, e.getMessage());
		}
		return new ReturnMsg(Constants.CODE_SUCCESS, "");
	}

	@Override
	public String genVerifyCode(String telephone, String source) {
		String verifyCode = StringUtil.genRandomNum(4);
		try {
			trade.genVerifyCode(telephone, verifyCode, source);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("verifyCode", verifyCode);
		// param.put("interval", interval);
		// 发送验证码
		if (ApiUtils.sendMsg(Constants.MODEL_VERYFY_FN, param, telephone)) {
			return verifyCode;
		}
		return "";
	}

	private boolean validVerifyCode(String telephone, String verifyCode,
			String source) {
		boolean flag = false;
		try {
			if (StringUtils.isNotEmpty(trade.checkVerifyCode(telephone,
					verifyCode, getSecond(), source))) {
				flag = true;
			} else {
				// 验证码过期
				trade.setVerifyCodeExpired(telephone);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	private int getSecond() {
		return StringUtils.isNotBlank(interval) ? Integer.parseInt(interval) * 60
				: 60;
	}

	@Override
	public boolean check(String telephone, String verifyCode, String source) {
		return validVerifyCode(telephone, verifyCode, source);
	}

	@Override
	public boolean updatePwd(String nickname, String telephone,
			String oldPassward, String newPassward) {
		try {
			// modify password
			if (StringUtils.isNotBlank(oldPassward)) {
				if (StringUtils.isNotBlank(trade.isvalidPassword(nickname,
						telephone, oldPassward))) {
					trade.updatePwd(nickname, telephone, newPassward);
				} else {
					if (log.isWarnEnabled()) {
						log.warn("execute operation[updatePwd] user:"
								+ nickname + " telephone:" + telephone
								+ " oldPassward:" + oldPassward
								+ " one of them is wrong!!!");
					}
					return false;
				}
			} else {
				// forget password
				trade.updatePwdUserIsNull(telephone, newPassward);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateFigurePwd(String nickname, String figurePwd) {
		try {
			account.updateFigurePwd(nickname, figurePwd);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public String updateTelephone(String nickname, String telephone,
			String newTelephone, String verifyCode) {
		try {
			// 1、校验旧手机号是否正确
			if (!StringUtils.equals(Constants.CODE_SUCCESS,
					account.checkOldTelephone(nickname, telephone))) {
				return Constants.ERROR_CODE_WRONG_TEL;
			}
			// 2、校验验证码是否正确
			if (!check(telephone, verifyCode,
					Constants.CODE_VERIFYCODE_SOURCE_UPDATETEL)) {
				return Constants.ERROR_CODE_WRONG_VERIFYCODE;
			}
			// 3、更新手机
			account.updateTelephone(nickname, newTelephone);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Constants.CODE_FAILURE;
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public String bindTelephone(String nickname, String telephone,
			String verifyCode) {
		// 1、校验验证码是否正确
		if (!check(telephone, verifyCode,
				Constants.CODE_VERIFYCODE_SOURCE_BINDTEL)) {
			return Constants.ERROR_CODE_WRONG_VERIFYCODE;
		}
		try {
			// 2、绑定手机号
			account.updateTelephone(nickname, telephone);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Constants.CODE_FAILURE;
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public Object figureLogin(String nickname, String figurePwd) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			String userId = trade.queryLoginStatus(nickname);
			xingePush(userId);
			LoginDO loginDO = trade.figureLogin(nickname, figurePwd);
			setResult(result, loginDO, userId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	private void xingePush(String userId) {
		if (StringUtils.isNotBlank(userId)) {
			StockMsg msg = new StockMsg();
			msg.setUserId(userId);
			msg.setContent(UserInfo.LOGINSTATUS.getStatus());
			msg.setTitle(UserInfo.LOGINSTATUS.getTip());
			// msg.setNickname(nickname);
			// msg.setSendTime(DateUtil.dateToStr(new Date()));
			XingePush.push(msg);
		}
	}

	private void setResult(Map<String, String> result, LoginDO loginDO,
			String userId) throws Exception {
		if (null != loginDO) {
			if (StringUtils.isBlank(userId)) {
				trade.updateLoginStatus(loginDO.getUserId());
			}
			result.put("isLogin", "1");
			result.put("userId", loginDO.getUserId());
			result.put("hasOperation",
					StringUtils.isBlank(loginDO.getTradeAccount()) ? "0" : "1");
			result.put("figurePwd",
					StringUtils.defaultIfBlank(loginDO.getFigurePwd(), ""));
			result.put("depositPwd",
					StringUtils.isBlank(loginDO.getDepositPwd()) ? "0" : "1");
			result.put("identification", StringUtils.isBlank(loginDO
					.getIdentification()) ? "0" : "1");
			result.put("telephone",
					StringUtils.defaultIfBlank(loginDO.getTelephone(), ""));
			result.put("bankCard",
					StringUtils.defaultIfBlank(loginDO.getBankCard(), ""));
			result.put("assignCash", StringUtils.defaultIfBlank(
					StringUtil.keepThreeDot(loginDO.getAssignCash()), ""));
			result.put(
					"debt",
					StringUtils.defaultIfBlank(
							StringUtil.keepThreeDot(loginDO.getDebt()), "0"));
		} else {
			result.put("isLogin", "0");
			result.put("userId", "");
			result.put("hasOperation", "0");
			result.put("figurePwd", "");
			result.put("depositPwd", "");
			result.put("identification", "0");
			result.put("telephone", "");
			result.put("bankCard", "");
			result.put("operateAccount", "");
		}
	}

	@Override
	public void loginOff(String nickname) {
		try {
			account.loginOff(nickname);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
