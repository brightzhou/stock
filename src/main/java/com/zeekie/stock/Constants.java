package com.zeekie.stock;

import java.util.ArrayList;
import java.util.List;
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
	/**
	 * 余额不足 code=2 理财
	 */
	public static final String CODE_BALANCE_LITTLE = "2";
	
	/**
	 * 理财额度已经用完 code=3 
	 */
	public static final String CODE_TOTAL_lIMIT_LITTLE = "3";

	public static final String CODE_FAILURE_NO_ACCOUNT = "3#";

	public static final String CODE_HOMES_SUCCESS = "0";

	public static final String ERROR_CODE_WRONG_TEL = "2";

	public static final String ERROR_CODE_WRONG_VERIFYCODE = "3";

	// 该用户已经存在
	public static final String CODE_ERROR_NICKNAME = "2";

	public static final String CODE_ERROR_NICKNAME_MSG = "该用户已经存在，请重新输入";

	public static final String CODE_ERROR_WALLET_LITTLE = "4";

	public static final String CODE_ERROR_EXCEED_LIMIT = "5";

	public static final String MSG_ERROR_WALLET_LITTLE = "余额不足以支付保证金";

	public static final String CODE_ERROR_MONEY = "6"; // 输入保证金错误
	// 无效的验证码
	public static final String CODE_ERROR_VERIFYCODE = "3";

	public static final String CODE_ERROR_VERIFYCODE_MSG = "无效的验证码";

	public static final String CODE_API_RESPONSE = "000000";

	public static final String CODE_VERIFYCODE_SOURCE_REGISTER = "10";

	public static final String CODE_VERIFYCODE_SOURCE_FORGETPWD = "11";

	public static final String CODE_VERIFYCODE_SOURCE_UPDATETEL = "12";

	public static final String CODE_VERIFYCODE_SOURCE_BINDTEL = "13";

	/**
	 * 修改提现密码验证码
	 */
	public static final String CODE_VERIFYCODE_SOURCE_DEPOSIT_UPDATE = "14";

	public static final String TIPS_RETURN_GURANTEE_CASH = "00";// 返回剩余保证金

	public static final String CLIENTWALLET_TO_MAINACOUNT = "10";// 扣除客户账户的钱（用于保证金）

	public static final String MAINACOUNT_TO_STOCKACCOUNT = "20";// 从主账户划出钱到子账户（配资）

	public static final String MANAGEMENT_FEE = "30";// 从用户钱包扣钱用于管理费

	public static final String TRANS_FROM_CLIENT_TO_WALET = "40";// 充值

	public static final String TRANS_FROM_WALET_TO_CLIENT = "50";// 提现

	public static final String TRANS_FROM_HOMES_TO_CLIENT = "60";// 结束操盘把盈亏划到用户钱包

	public static final String TRANS_FROM_HOMES_TO_TOTALFUND = "70";// 结束操盘把配资的钱划到我们自己的总资产

	public static final String REDPACKET_TO_CLIENT = "用户注册，获取平台红包";// 80

	public static final String SEND_RED_PACKET = "130";// 管理端主动发红包

	public static final String SEND_UNDO_WITHDRAWL = "140";// 用户撤销提现

	public static final String PAY_OFF_LOSS = "150";// 付清欠款

	public static final String REDPACKET_TO_CLIENT_REFEREE = "用户【nickname】注册，您获取推广红包";// 给推荐人派发红包

	public static final String REDPACKET_TO_CLIENT_REFEREE_TO_USER = "用户【nickname】注册，您派发红包";// 推荐人给注册的人指定的红包

	public static final String REDPACKET_TO_CLIENT_USER_FROM_REFEREE = "用户【nickname】奖励给您一个红包";// 注册的人收到指定的红包

	public static final String AMORTIZATION = "获取用户【nickname】技术服务费提成";
	// 验证码 1307
	public static final String MODEL_VERYFY_FN = "1307";
	// 提醒充值 1308
	public static final String MODEL_NOTICE_RECHARGE_FN = "1308";
	// 用户发起提现 1309
	public static final String MODEL_DEPOSIT_SQ_FN = "1309";
	// 管理账户余额不多 1310
	public static final String MODEL_MANAAGER_RECHARGE_FN = "1310";
	// 操盘账户已经用完 1311
	public static final String MODEL_ACCOUNT_EMPTY_FN = "1311";
	// 实盘金额已经达到平仓线 1312
	public static final String MODEL_TRADE_STOP_FN = "1312";

	// public static final String MODEL_TRADE_WARN_FN = "1313";
	// 该账号在HOMES中还有资金 1314
	public static final String MODEL_OPERATOR_HAS_CASH_FN = "1314";
	// 后台平仓 1315
	public static final String MODEL_EVENING_UP_REMIND_FN = "1315";
	// 当实际资产小于警戒线以下的时候发送短信给用户1316
	public static final String MODEL_REACH_WARNLINE_REMIND_FN = "1316";
	/**
	 * 成为推荐人 FN=1317
	 */
	public static final String MODEL_TO_BE_REFEREE_FN = "1317";

	/**
	 * 未成为推荐人 FN=1318
	 */
	public static final String MODEL_NOT_TO_BE_REFEREE_FN = "1318";
	/**
	 * 发红包给用户 FN=1319
	 */
	public static final String MODEL_REDPACKET_TO_USER_FN = "1319";

	public static final String MODEL_CONTENT = "【哈哈宝】";

	public static final String EVENING_UP = "1";

	public static String MSG_USER = "";

	public static String MSG_PWD = "";

	public static String MSG_URL = "";

	public static final String TYPE_JOB_RECEIPT = "receipt";
	public static String TYPE_JOB_DEDUCT_ADDGURANTEE = "deductByAddGurantee";

	public static String TYPE_JOB_DEDUCT = "deduct";

	public static String TYPE_JOB_EVENING_UP_REMIND = "remind";

	public static String TYPE_JOB_NOTICE_REACH_WARNLINE = "warnline";

	public static String TYPE_JOB_CONTROL_APP = "closeOrOpenApp";

	public static String TYPE_JOB_PAY_NOTICE = "payNotice";

	public static String TYPE_JOB_REDPACKET_NOTICE = "redpacketNotice";

	public static String TYPE_JOB_SENDMSG_NOTICE = "sendMessageToAll";

	public static Map<String, String> MSG_MODEL = null;

	// 修改
	public static final String OPERATE_TYPE = "3";

	// 限制买入股票
	public static final String OPERATE_RIGHT_ONE = "1";

	// 戒除限制买入股票
	public static final String OPERATE_RIGHT_ZERO = "0";

	/**
	 * 发给推荐人的信息，告知是否成为推荐人
	 */
	public static String TYPE_JOB_TO_REFEREE = "toBeReferee";
	/**
	 * 通知图片更新
	 */
	public static String TYPE_JOB_PIC_UPDATE = "picUpdate";

	public static String accessId = "";

	public static String secretkey = "";

	public static int environment = 2;

	public static String fileAdress = "";

	public static String fileAdress_qq = "";

	public static String fileAdress_wx = "";

	public static Map sensitiveWordMap = null;

	// public static String HOMES_STATUS = "nomral";

	public static String HOMES_STATUS = "open";

	/**
	 * 上交所
	 */
	public static String HOMES_EXCHANGE_TYPE_SH = "1";

	/**
	 * 深交所
	 */
	public static String HOMES_EXCHANGE_TYPE_S = "2";

	/**
	 * 委托方向 1：买入
	 */
	public static String HOMES_ENTRUST_DIRECTION_BUY = "1";

	/**
	 * 委托方向 2：卖出
	 */
	public static String HOMES_ENTRUST_DIRECTION_SELL = "2";

	/**
	 * 获取的xml信息
	 */
	public static String XML = "";

	/**
	 * 摘要
	 */
	public static String MAC = "";

	public static String PAY_SUCCESS = "C000000000";

	// 接口访问时间
	public static int range = 120;

	// 加密因子
	public static String factor;

	public static List<String> user = new ArrayList<String>();

	public static IClient getClient() {
		return client;
	}

	public static XingeApp getXinge() {
		return new XingeApp(Long.parseLong(accessId), secretkey);
	}

}
