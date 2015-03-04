package com.zeekie.stock;

import java.util.Map;

import net.sf.json.JsonConfig;

import com.hundsun.t2sdk.interfaces.IClient;
import com.tencent.xinge.XingeApp;

/**
 * @author zeekie
 * 
 * @version 1.0, 2014/06/10
 */
public class Constants {

	// 留底税额
	public static final String TYPE_TAX = "tax";

	// 企业注册信息
	public static final String TYPE_REGISTER = "register";

	// 申报
	public static final String TYPE_DECLARE = "declare";

	public static IClient client = null;

	public static String HOME_MANAGER_NO = "";

	public static String HOME_MANAGER_PWD = "";

	public static String HOME_MANAGER_FNC_LOGIN = "";

	public static JsonConfig jsonConfig = null;

	public static String HOMES_TOKEN = "";

	public static String TOKEN_LOSS = "Session错误!";

	// 扣款
	public static final String CODE_FIRST_ENTER_ADD_GUARANTEE = "remind";

	// 扣款
	public static final String CODE_FAILURE = "0";

	public static final String CODE_SUCCESS = "1";

	public static final String CODE_HOMES_SUCCESS = "0";

	public static final String ERROR_CODE_WRONG_TEL = "2";

	public static final String ERROR_CODE_WRONG_VERIFYCODE = "3";

	// 该用户已经存在
	public static final String CODE_ERROR_NICKNAME = "2";

	public static final String CODE_ERROR_NICKNAME_MSG = "该用户已经存在，请重新输入";

	public static final String CODE_ERROR_WALLET_LITTLE = "4";

	public static final String MSG_ERROR_WALLET_LITTLE = "余额不足以支付保证金";
	// 无效的验证码
	public static final String CODE_ERROR_VERIFYCODE = "3";

	public static final String CODE_ERROR_VERIFYCODE_MSG = "无效的验证码";

	public static final String CODE_API_RESPONSE = "000000";

	public static final String CODE_VERIFYCODE_SOURCE_REGISTER = "10";

	public static final String CODE_VERIFYCODE_SOURCE_FORGETPWD = "11";

	public static final String CODE_VERIFYCODE_SOURCE_UPDATETEL = "12";

	public static final String CODE_VERIFYCODE_SOURCE_BINDTEL = "13";

	public static final String TIPS_RETURN_GURANTEE_CASH = "00";// 返回剩余保证金

	public static final String CLIENTWALLET_TO_MAINACOUNT = "10";// 扣除客户账户的钱（用于保证金）

	public static final String MAINACOUNT_TO_STOCKACCOUNT = "20";// 从主账户划出钱到子账户（配资）

	public static final String MANAGEMENT_FEE = "30";// 从用户钱包扣钱用于管理费

	public static final String TRANS_FROM_CLIENT_TO_WALET = "40";// 充值

	public static final String TRANS_FROM_WALET_TO_CLIENT = "50";// 提现

	public static final String TRANS_FROM_HOMES_TO_CLIENT = "60";// 结束操盘把盈亏划到用户钱包

	public static final String TRANS_FROM_HOMES_TO_TOTALFUND = "70";// 结束操盘把配资的钱划到我们自己的总资产

	public static final String REDPACKET_TO_CLIENT = "用户注册，获取平台红包";// 80

	public static final String REDPACKET_TO_CLIENT_REFEREE = "用户【nickname】注册，您获取推广红包";// 给推荐人派发红包

	public static final String REDPACKET_TO_CLIENT_REFEREE_TO_USER = "用户【nickname】注册，您派发红包";// 推荐人给注册的人指定的红包

	public static final String REDPACKET_TO_CLIENT_USER_FROM_REFEREE = "用户【nickname】奖励给您一个红包";// 注册的人收到指定的红包

	public static final String AMORTIZATION = "获取用户【nickname】技术服务费提成";
	// 验证码 1307
	public static final String MODEL_VERYFY_FN = "1307";
	public static final String MODEL_VERYFY = "【哈哈宝】亲爱的，你的验证码是{?}，请在2分钟以内输入验证。过期请重新获取";
	// 提醒充值 1308
	public static final String MODEL_NOTICE_RECHARGE_FN = "1308";
	public static final String MODEL_NOTICE_RECHARGE = "【哈哈宝】亲爱的{?},你的账户余额所剩不多，请及时充值，以免影响正常使用";
	// 用户发起提现 1309
	public static final String MODEL_DEPOSIT_SQ_FN = "1309";
	public static final String MODEL_DEPOSIT_SQ = "【哈哈宝】管理员，有用户发起提醒，请及时处理";
	// 管理账户余额不多 1310
	public static final String MODEL_MANAAGER_RECHARGE_FN = "1310";
	public static final String MODEL_MANAAGER_RECHARGE = "【哈哈宝】管理员，资金账户{?}余额不多,请处理";
	// 操盘账户已经用完 1311
	public static final String MODEL_ACCOUNT_EMPTY_FN = "1311";
	public static final String MODEL_ACCOUNT_EMPTY = "【哈哈宝】管理员，子账户已经使用完毕,请处理";

	// 实盘金额已经达到平仓线 1312
	public static final String MODEL_TRADE_STOP_FN = "1312";
	public static final String MODEL_TRADE_STOP = "【哈哈宝】亲爱的{?}，你的交易{?},按照预先的设置,已经达到{?},请及时增加保证金以免影响操盘";

	// 实盘金额已经达到警戒线 1313
	public static final String MODEL_TRADE_WARN_FN = "1313";
	public static final String MODEL_TRADE_WARN = "【哈哈宝】管理员，子账户已经使用完毕,请处理";

	// 该账号在HOMES中还有资金 1314
	public static final String MODEL_OPERATOR_HAS_CASH_FN = "1314";
	public static final String MODEL_OPERATOR_HAS_CASH = "【哈哈宝】管理员，该账号{?}已经结束操盘，但是在HOMES中还有资金未划转到管理单元,请处理";

	// 后台平仓 1315
	public static final String MODEL_EVENING_UP_REMIND_FN = "1315";
	public static final String MODEL_EVENING_UP_REMIND = "【哈哈宝】亲爱的#,您当前操盘单号为#由于已经爆仓,已经被后台强制结束操盘.请在历史操盘中查询。如有疑问请联系管理员，谢谢！";

	// 当实际资产小于警戒线以下的时候发送短信给用户1316
	public static final String MODEL_REACH_WARNLINE_REMIND_FN = "1316";
	public static final String MODEL_REACH_WARNLINE_REMIND = "【哈哈宝】亲爱的#,您当前操盘单号为#的操盘，实际资产#已经低于警戒线#,请及时增加保证金，以免爆仓，谢谢！";

	public static final String EVENING_UP = "1";

	public static String MSG_USER = "";

	public static String MSG_PWD = "";

	public static String MSG_URL = "";

	public static String TYPE_JOB_DEDUCT = "deduct";

	public static String TYPE_JOB_EVENING_UP_REMIND = "remind";

	public static String TYPE_JOB_NOTICE_REACH_WARNLINE = "warnline";

	public static String TYPE_JOB_CONTROL_APP = "closeOrOpenApp";

	public static String accessId = "";

	public static String secretkey = "";

	public static int environment = 2;

	public static String fileAdress = "";

	public static String fileAdress_qq = "";

	public static String fileAdress_wx = "";

	public static Map sensitiveWordMap = null;

	public static IClient getClient() {
		return client;
	}

	public static XingeApp getXinge() {
		return new XingeApp(Long.parseLong(accessId), secretkey);
	}

}
