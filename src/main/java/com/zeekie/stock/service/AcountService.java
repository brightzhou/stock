package com.zeekie.stock.service;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface AcountService {

	/**
	 * 实名认证
	 * 
	 * @param nickname
	 * @param truename
	 * @param idCard
	 * @return
	 */
	public boolean indentify(String nickname, String truename, String idCard);

	/**
	 * 绑定银行卡
	 * 
	 * @param nickname
	 * @param telephone
	 * @param bank
	 * @param number
	 * @param bankCode
	 * @param code
	 * @return
	 */
	public boolean bindCreditCard(String nickname, String telephone,
			String bank, String number, String bankCode, String code);

	/**
	 * 修改/修改提款密码
	 * 
	 * @param nickname
	 * @param telephone
	 * @param depositPwd
	 * @return
	 */
	public boolean setDepositPwd(String nickname, String telephone,
			String depositPwd);

	/**
	 * 获取资金流水
	 * 
	 * @param nickname
	 * @return
	 */
	public JSONArray getFundFlow(String nickname, String offset);

	/**
	 * 获取当前账户
	 * 
	 * @param nickname
	 * @return
	 */
	public Map<String, String> getCurrentAccount(String nickname);

	/**
	 * 客户端充值成功后记录流水
	 * 
	 * @param nickname
	 * @param fund
	 * @param payAccount
	 * @return
	 */
	public boolean clientRecharge(String nickname, String fund,
			String payAccount);

	/**
	 * 用户提现
	 * 
	 * @param nickname
	 * @param fund
	 * @return
	 */

	public Map<String, String> withdraw(String nickname, String fund,
			String depositPwd, String openBank);

	/**
	 * 结束操盘
	 * 
	 * @param nickname
	 * @param flag
	 *            判断是否强行平仓
	 * @return
	 */
	public Map<String, String> endStock(String nickname, String flag)
			throws RuntimeException;

	/**
	 * 进入用户推广页面
	 * 
	 * @param nickname
	 * @return 自己能从平台获得红包 /下线能获得的红包/以后下线的操盘盈亏的钱可以分多少比例给推荐人/推广链接
	 */
	public Map<String, String> enterSpreadPage(String nickname);

	/**
	 * 开始推广
	 * 
	 * @param refereeId
	 *            推广人ID
	 * @param redPacket
	 *            派发的的红包
	 * @return
	 */
	public boolean spread(String refereeId, String redPacket);

	/**
	 * 进入提现页面
	 * 
	 * @param nickname
	 * @return
	 */
	public Map<String, String> withdrawPageEnter(String nickname);

	/**
	 * 修改提款密码
	 * 
	 * @param nickname
	 * @param verifyCode
	 * @param telephone
	 * @return
	 */
	public String checkDepoist(String userId, String depositPwd);

	/**
	 * 扣除欠款
	 * 
	 * @param nickname
	 * @return
	 */
	public String deductDebt(String nickname);

	/**
	 * 获取余额
	 * 
	 * @param nickname
	 * @return
	 */
	public Map<String, String> getWallet(String nickname);

	/**
	 * 获取用户真实姓名和身份证号
	 * 
	 * @param userId
	 * @return
	 */
	public JSONObject getUserInfo(String userId);

	/**
	 * 獲取銀行信息
	 * 
	 * @param userId
	 * @return
	 */
	public JSONObject getBankInfo(String userId);

	/**
	 * 修改资金密码
	 * 
	 * @param userId
	 * @param depositPwd
	 * @param telephone
	 * @param verifyCode
	 * @return
	 */
	public String modifyDepositPwd(String userId, String depositPwd,
			String telephone, String verifyCode);

	/**
	 * 获取绑定的用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, String> getId(String userId);

	/**
	 * 获取绑定银行卡信息
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, String> getBindCreditCard(String userId);

	/**
	 * 根据code获取银行限额
	 * 
	 * @param code
	 * @return
	 */
	public JSONObject getBankLimitation(String code);

	/**
	 * 获取用户是否操盘、是否有盈亏，homes是否关闭
	 * 
	 * @param userId
	 * @return
	 */
	public JSONObject getBasicInfo(String userId);
}
