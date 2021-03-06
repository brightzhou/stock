package com.zeekie.stock.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.AddGuaranteePageDO;
import com.zeekie.stock.entity.CurrentOperationDO;
import com.zeekie.stock.entity.DictionariesDO;
import com.zeekie.stock.entity.EveningEndDO;
import com.zeekie.stock.entity.HasOpertAndDebtDO;
import com.zeekie.stock.entity.HistoryOperationDO;
import com.zeekie.stock.entity.RuleDO;
import com.zeekie.stock.entity.StockRadioDO;
import com.zeekie.stock.entity.TradeDO;
import com.zeekie.stock.entity.form.AddCuaranteeForm;
import com.zeekie.stock.entity.form.TradeForm;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.StockMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.service.TradeService;
import com.zeekie.stock.service.homes.StockAssetMove;
import com.zeekie.stock.service.homes.StockCapitalChanges;
import com.zeekie.stock.service.homes.StockCombineInfo;
import com.zeekie.stock.service.homes.StockModifyPwd;
import com.zeekie.stock.service.homes.StockModifyUserName;
import com.zeekie.stock.service.lhomes.CallhomesService;
import com.zeekie.stock.service.lhomes.entity.AHomesEntity;
import com.zeekie.stock.service.lhomes.entity.EntrustMoveFund;
import com.zeekie.stock.service.lhomes.entity.Homes103Resp;
import com.zeekie.stock.service.lhomes.entity.HomesPwd;
import com.zeekie.stock.service.lhomes.entity.HomsEntity103;
import com.zeekie.stock.service.syncTask.SyncHandler;
import com.zeekie.stock.util.DateUtil;
import com.zeekie.stock.util.StringUtil;

import net.sf.json.JSONObject;

@Service
@Transactional
public class StockServiceImpl implements TradeService {

	static Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

	@Autowired
	private TradeMapper trade;

	@Autowired
	private AcountMapper acount;

	@Autowired
	private StockMapper stock;

	@Autowired
	private SyncHandler jobHandler;

	@Autowired
	@Value("${func_am_change_password}")
	private String Fn_changePwd;

	@Autowired
	@Value("${func_am_asset_move}")
	private String Fn_asset_move;

	@Autowired
	@Value("${func_am_combinfo_qry}")
	private String func_am_combinfo_qry;

	@Autowired
	@Value("${main_operator_no}")
	private String main_operator_no;

	@Autowired
	@Value("${main_operator_pwd}")
	private String main_operator_pwd;

	@Autowired
	@Value("${stock_manager_phone}")
	private String stock_manager_phone;

	@Autowired
	@Value("${func_am_stock_current_qry}")
	private String Fn_stock_current;// 769203-股票资产查询

	@Autowired
	@Value("${func_am_change_asset_info}")
	private String fn_change_assetName;// 769952-修改homse用户名

	@Autowired
	@Value("${stock.operation.number}")
	private String operationNo;

	@Autowired
	@Value("${isShowEntrust}")
	private String isShowEntrust;

	@Autowired
	@Value("${isDownload}")
	private String isDownload;

	@Autowired
	@Value("${stock.status.changeIsOpen}")
	private String changeIsOpen;

	@Autowired
	private EntrustService entrustService;

	private Set<String> fundAccountSet = new HashSet<String>();

	@Override
	public Map<String, String> startOperate(String nickname, String tradeFund) {
		Map<String, String> currentOperate = new HashMap<String, String>();
		currentOperate.put("flag", "0");

		if (StringUtils.equals(Constants.HOMES_STATUS, "close")) {
			currentOperate.put("flag", "3");
			return currentOperate;
		}

		try {
			/** start 修改用户 配置比例 警戒 止损 add20150610 */
			DictionariesDO dictionariesDO = new DictionariesDO();
			dictionariesDO.setDicType("2");
			dictionariesDO.setStatus("1");
			List<DictionariesDO> dicList = trade.queryDictionarieList(dictionariesDO);
			float stopRadio = 0;
			float warnRadio = 0;
			float assignRadio = 0;
			if (dicList != null && dicList.size() == 3) {
				int i = 0;
				for (DictionariesDO dictionarie : dicList) {
					if ("stopRadio".equals(dictionarie.getDicWord())) {
						stopRadio = Float.valueOf(dictionarie.getDicValue());
						i++;
					}
					if ("warnRadio".equals(dictionarie.getDicWord())) {
						warnRadio = Float.valueOf(dictionarie.getDicValue());
						i++;
					}
					if ("assignRadio".equals(dictionarie.getDicWord())) {
						assignRadio = Integer.valueOf(dictionarie.getDicValue());
						i++;
					}
				}
				if (i != 3) {
					currentOperate.put("flag", "4");
					return currentOperate;
				} else {
					stopRadio = new BigDecimal(1 - stopRadio / assignRadio).setScale(4, BigDecimal.ROUND_HALF_UP)
							.floatValue();
					warnRadio = new BigDecimal(1 - warnRadio / assignRadio).setScale(4, BigDecimal.ROUND_HALF_UP)
							.floatValue();
					long count = acount.updateAssignRadio(nickname, stopRadio, warnRadio, assignRadio);
					if (count != 1) {
						currentOperate.put("flag", "5");
						return currentOperate;
					}
				}

			} else {
				currentOperate.put("flag", "4");
				return currentOperate;
			}
			/** end 修改用户 配置比例 警戒 止损 add20150610 */

			StockRadioDO radioDO = acount.getAssignRadioForCurrUser(nickname);
			if (null != radioDO) {
				// 判断是否大于设置操盘额度的上线
				if (StringUtil.compareNum(Float.parseFloat(tradeFund), radioDO.getAssignCash())) {
					currentOperate.put("flag", "2");
					currentOperate.put("msg", "您当前操盘额度【" + tradeFund + "】元不能超过设置的操盘上线【" + radioDO.getAssignCash()
							+ "】元，如果需要调整，可以联系客户，谢谢!");
					return currentOperate;
				}
				// 3、返回操盘信息
				returnOperateInfo(currentOperate, radioDO, Float.parseFloat(tradeFund));

				currentOperate.put("flag", "1");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return currentOperate;
	}

	@Override
	public Map<String, String> storeOperationInfo(TradeForm tradeForm) throws RuntimeException {

		long startTime = System.currentTimeMillis();

		Map<String, String> currentOperateInfo = new HashMap<String, String>();
		currentOperateInfo.put("flag", "0");

		if (StringUtils.equals(Constants.HOMES_STATUS, "close")) {
			currentOperateInfo.put("flag", "5");
			return currentOperateInfo;
		}

		try {
			String nickname = tradeForm.getNickname();
			if (log.isDebugEnabled()) {
				log.debug("用户  [{}] 开始创建操盘...", nickname);
			}
			HasOpertAndDebtDO andDebtDO = trade.queryHasOperation(nickname);
			if (null != andDebtDO) {
				if (StringUtils.isNotBlank(andDebtDO.getOperation())) {
					if (log.isDebugEnabled()) {
						log.debug("用戶[{}] 拥有操盘，不能创建新的操盘", nickname);
					}
					currentOperateInfo.put("flag", "4");
					return currentOperateInfo;
				}
			}

			String tradeFund = tradeForm.getTradeFund();
			String guaranteeCash = tradeForm.getGuaranteeCash();

			// 判断用户钱包是否有充足的余额支付保证金
			if (!StringUtils.equals("1", acount.userWalletIsFull(nickname, guaranteeCash))) {
				if (log.isDebugEnabled()) {
					log.debug("用户【" + nickname + "】余额不足以支付保证金，无法开始操盘");
				}
				return currentOperateInfo;
			}

			// 判断是否存在可用的操盘账号
			String tradeAccount = acount.getOperateAccount();
			if (StringUtils.isBlank(tradeAccount)) {
				currentOperateInfo.put("flag", "2");
				// ApiUtils.send(Constants.MODEL_ACCOUNT_EMPTY_FN,
				// stock_manager_phone);
				if (log.isDebugEnabled()) {
					log.debug("已經没有可用的操盘账号,不能操盘，操盘用户：" + nickname);
				}
				return currentOperateInfo;
			}

			/**
			 * 是否开启了小homes
			 */
			TradeDO tradeDO = null;
			// 1、保存操盘账号信息 资金划转
			if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(tradeAccount, "6")) {
				if (log.isDebugEnabled()) {
					log.debug("开始访问小HOMS，获取可用的操盘账号");
				}
				tradeDO = callLittleHoms(nickname, tradeFund);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("开始访问大HOMS，获取可用的操盘账号");
				}
				tradeDO = callHomesInterface(nickname, tradeFund);
			}

			if (null == tradeDO) {
				currentOperateInfo.put("flag", "2");
				return currentOperateInfo;
			}
			String flag = tradeDO.getFlag();
			if (StringUtils.equals(Constants.CODE_FAILURE, flag)) {
				currentOperateInfo.put("flag", "2");
				return currentOperateInfo;
			} else if (StringUtils.equals(flag, "3#")) {
				// 管理账户资金不足
				currentOperateInfo.put("flag", "3");
				return currentOperateInfo;
			}

			// 3.0 更新该账户状态为已经被使用
			String operateNO = tradeDO.getOperatorNo();
			String operatePwd = tradeDO.getOperatorPwd();
			acount.updateAccountStatus(operateNO, operatePwd);
			if (log.isDebugEnabled()) {
				log.debug("用户【" + nickname + "】开始使用操盘账户【" + operateNO + "】,新密码为【" + operatePwd + "】");
			}

			// 4、操盘账户信息入库
			Long id = trade.getSeqId("Seq_User_Operate_Acount_Id.Nextval");
			tradeDO.setId(id);
			tradeDO.setNickname(nickname);
			trade.storeOperatorAcountInfo(tradeDO);
			if (log.isDebugEnabled()) {
				log.debug("用户操盘账号【" + operateNO + "】当前操盘信息入库");
			}

			// 5、保存操盘信息
			StockRadioDO radioDO = acount.getAssignRadioForCurrUser(nickname);
			tradeForm.setWarnRadio(radioDO.getWarnRadio());
			tradeForm.setStopRadio(radioDO.getStopRadio());
			tradeForm.setAssignRadio(radioDO.getAssignRadio());
			tradeForm.setOperateAccountId(id + "");
			acount.storeOperatorCash(tradeForm);

			// 6、从客户账户扣除保证金
			trade.deductGuaranteeCash(guaranteeCash, nickname);

			String fundAccount = tradeDO.getFundAccount();
			// 7、主账户减去配资的钱
			acount.addTotalFund("0", "-" + tradeFund, tradeDO.getFundAccount(), "主账户扣除配资的钱", "recharge");

			// 更新历史金额状态为N
			acount.updateStatusToN(fundAccount);
			// 更新当前金额状态为Y
			acount.updateStatusToY(fundAccount);

			// 8、记录资金流水
			trade.recordFundflow(tradeForm.getNickname(), Constants.CLIENTWALLET_TO_MAINACOUNT, "-" + guaranteeCash,
					"支付保证金");

			// 第一次更新当前资产
			stock.updateOperateMainInfo(tradeFund, "0", nickname);

			// 如果为8:45~14:59分之间，则立即扣除当天服务费；如为14:59分之后，则当天服务费不扣除
			jobHandler.handleJob(Constants.TYPE_JOB_DEDUCT, nickname);

			// 返回当前操盘信息
			currentOperateInfo.put("currentAsset", guaranteeCash);// 当前资产
			currentOperateInfo.put("actualFund", tradeFund);// 实盘金额
			currentOperateInfo.put("profitAndLossCash", Constants.CODE_FAILURE);// 盈亏金额
			currentOperateInfo.put("profitAndLossRadio", Constants.CODE_FAILURE);// 盈亏比例
			currentOperateInfo.put("progressBar", Constants.CODE_SUCCESS);// 进度条
			currentOperateInfo.put("flag", Constants.CODE_SUCCESS);

			if (log.isDebugEnabled()) {
				log.debug("执行保存操盘耗时：" + ((System.currentTimeMillis() - startTime) / 1000) + "s");
			}
			return currentOperateInfo;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	private TradeDO callLittleHoms(String nickname, String moveFund) {
		// 2.2、查詢数据库获取密码
		String operator = "";
		String operPwd = "";
		String combineId = "";
		String fundAccount = "";
		Integer total;
		TradeDO client = null;
		try {
			total = acount.queryTotalFundAccount();
			boolean haveOperator = false;
			List<TradeDO> item = acount.getAllUserInfo();
			if (null == item || item.isEmpty()) {
				if (log.isDebugEnabled()) {
					log.debug("已经无可用的资金账号，请管理员注意了");
				}
				return client;
			}
			for (int i = 0; i < item.size(); i++) {
				client = item.get(i);
				fundAccount = client.getFundAccount();
				if (fundAccountSet.contains(fundAccount)) {
					continue;
				}
				if (total == fundAccountSet.size()) {
					fundAccountSet.clear();
					client.setFlag(Constants.CODE_FAILURE_NO_ACCOUNT);
					return client;
				}
				operator = client.getOperatorNo();
				combineId = client.getCombineId();
				operPwd = client.getOperatorPwd();
				// 判断该账号是否已经结束但是在HOMES却还有资金和持仓，没有就继续
				if (hasnotCashAndCc(operator, fundAccount)) {
					if (log.isDebugEnabled()) {
						log.debug("获取小homes分配的操盘账号 [{}] 可用，开始看该资金账号下资金是否充足", operator);
					}
					if (mainAccountCashIsEnough(nickname, moveFund, fundAccount)) {
						haveOperator = true;
						break;
					}
				} else {
					if (log.isDebugEnabled()) {
						log.debug("小homes操盘账号[{}]存在资金或拥有操盘，不能使用", operator);
					}
				}
			}
			fundAccountSet.clear();
			if (!haveOperator) {
				return client;
			}
			// 2.3 生成新密码更新到homes
			// AUTO 修改密码
			String newPwd = StringUtil.genRandomNum(6);
			// if (modifyPwd(operator, operPwd, newPwd)) {
			// if (log.isDebugEnabled()) {
			// log.debug("用户 【" + nickname + "】 修改密码成功,新密码："+newPwd);
			// }
			client.setOperatorPwd(newPwd);
			// } else {
			// return client;
			// }

			// 2.3.1修改用户名称

			// 2.5资金划转
			if (move(moveFund, combineId, fundAccount)) {
				if (log.isDebugEnabled()) {
					log.debug("用户 【" + nickname + "】 进行资金划转成功");
				}
			} else {
				return client;
			}
			client.setFlag(Constants.CODE_SUCCESS);
			return client;
		} catch (Exception e) {
			log.error("调用小homs发生错误：" + e);
			return client;
		}

	}

	private boolean modifyPwd(String clientNo, String operPwd, String newPwd) {
		HomesPwd homesPwd = new HomesPwd();
		homesPwd.setClientNo(clientNo);
		homesPwd.setOldPwd(operPwd);
		homesPwd.setNewPwd(newPwd);
		CallhomesService service = new CallhomesService(homesPwd);
		return service.call311Fun();
	}

	private boolean hasnotCashAndCc(String clientNo, String fundAccount) {
		AHomesEntity entity = new AHomesEntity();
		entity.setClientNo(clientNo);
		entity.setFundAccount(fundAccount);
		CallhomesService service = new CallhomesService(entity);
		if (service.call210Fun()) {
			// 资金为空，查看是否持仓
			AHomesEntity entitys = new AHomesEntity(fundAccount, clientNo);
			CallhomesService services = new CallhomesService(entitys);
			if (services.call103Fun()) {
				Homes103Resp resp = (Homes103Resp) services.getResponse(Constants.FN103);
				List<HomsEntity103> result = resp.getList();
				for (int j = 0; j < result.size(); j++) {
					HomsEntity103 item = result.get(j);
					String cash = item.getCurrentAmount();
					if (!StringUtils.equals("0", cash) && StringUtils.isNotBlank(cash)) {
						if(log.isDebugEnabled()){
							log.debug("该账号  [{}] 拥有持仓，不可使用",clientNo);
						}
						return false;
					}
				}
				return true;
			}else{
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 资金划转
	 * 
	 * @param moveFund
	 *            划转资金
	 * @param combineId
	 *            客户号
	 * @param fundAccount
	 *            投资账号
	 * @param managerCombineId
	 *            划入客户号，主客户号
	 * @return
	 */
	private boolean move(String moveFund, String combineId, String fundAccount) {
		EntrustMoveFund entrustMoveFund = new EntrustMoveFund();
		entrustMoveFund.setClientNo(fundAccount);
		entrustMoveFund.setClientNoTo(combineId);
		entrustMoveFund.setOccurBalance(moveFund);
		entrustMoveFund.setFlag("pay");
		CallhomesService service = new CallhomesService(entrustMoveFund);
		return service.call501Fun();
	}

	@Override
	public Map<String, String> getCurrentOperation(String nickname) {
		Map<String, String> currentOperateInfo = new HashMap<String, String>();
		try {
			CurrentOperationDO current = stock.getCurrentOperation(nickname);
			if (null != current) {
				currentOperateInfo.put("currentAsset", current.getCurrentAsset() + "");// 当前资产
				currentOperateInfo.put("actualFund", current.getActualFund() + "");// 实盘金额
				currentOperateInfo.put("profitAndLossCash", current.getProfitAndLossCash() + "");// 盈亏金额
				currentOperateInfo.put("profitAndLossRadio",
						StringUtil.keepTwoDecimalFloat(current.getProfitAndLossRadio() * 100) + "");// 盈亏比例
				currentOperateInfo.put("progressBar", current.getProgressBar() + "");// 进度条
				currentOperateInfo.put("fee", current.getFee() + "");
				currentOperateInfo.put("balance", current.getBalance() + "");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return currentOperateInfo;
	}

	@Override
	public Map<String, String> getOperateAcount(String nickname) {
		// 2、获取操盘账号、密码、单号 并存入操盘信息表
		TradeDO tradeDO;
		Map<String, String> operateAcount = new HashMap<String, String>();
		try {
			tradeDO = acount.getOperateAcount(nickname);
			String tip = "不可用";
			if (null != tradeDO) {
				String account = tradeDO.getOperatorNo();
				String pwd = tradeDO.getOperatorPwd();
				if (!StringUtils.startsWith(account, "6")) {
					account = tip;
					pwd = tip;
				}
				operateAcount.put("actualTicket", tradeDO.getAssetId());
				operateAcount.put("operatorNo", account);
				operateAcount.put("operatorPwd", pwd);
				operateAcount.put("flag", "1");
			} else {
				operateAcount.put("actualTicket", "");
				operateAcount.put("operatorNo", "");
				operateAcount.put("operatorPwd", "");
				operateAcount.put("flag", "0");
			}
			operateAcount.put("isSwitch", isShowEntrust);
			operateAcount.put("isOpen", isDownload);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return operateAcount;
	}

	private synchronized TradeDO callHomesInterface(String nickname, String moveFund) {
		// 2.2、查詢数据库获取密码
		String operatorPwd = "";
		String operator = "";
		String combineId = "";
		String fundAccount = "";
		String managerCombineId = "";
		Integer total;
		TradeDO client = null;
		try {
			total = acount.queryTotalFundAccount();
			boolean haveOperator = false;
			// 判断该账号是否已经结束但是在HOMES却还有资金
			List<TradeDO> item = acount.getAllUserInfo();
			if (null == item || item.isEmpty()) {
				if (log.isDebugEnabled()) {
					log.debug("已经无可用的资金账号，请管理员注意了");
				}
				return client;
			}
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < item.size(); i++) {
				client = item.get(i);
				client.setFlag(Constants.CODE_FAILURE);
				fundAccount = client.getFundAccount();
				if (fundAccountSet.contains(fundAccount)) {
					continue;
				}
				if (total == fundAccountSet.size()) {
					fundAccountSet.clear();
					client.setFlag(Constants.CODE_FAILURE_NO_ACCOUNT);
					return client;
				}
				operatorPwd = client.getOperatorPwd();
				operator = client.getOperatorNo();
				combineId = client.getCombineId();
				managerCombineId = client.getManagerCombineId();
				if (canUse(operator, combineId, fundAccount)) {
					if (mainAccountCashIsEnough(nickname, moveFund, fundAccount)) {
						haveOperator = true;
						break;
					}
				}
			}
			fundAccountSet.clear();
			if (!haveOperator) {
				return client;
			}
			if (log.isDebugEnabled()) {
				log.debug("判断该账户" + operator + "是否还有余额，选择一个可以用的账号，消耗时间："
						+ (System.currentTimeMillis() - startTime) / 1000 + "s");
			}
			// 2.3 生成新密码更新到homes
			String newOperatePwd = genNewPassword();
			startTime = System.currentTimeMillis();
			if (!modifyClientPwd(client, operatorPwd, operator, newOperatePwd)) {
				log.error("modify homes password failure for user[" + nickname + "],operation NO:" + operator);
				return client;
			}

			if (log.isDebugEnabled()) {
				log.debug("修改账户" + operator + "密码，消耗时间：" + (System.currentTimeMillis() - startTime) / 1000 + "s");
				log.debug("访问HOMES服务，修改用户密码成功");
			}

			client.setOperatorPwd(newOperatePwd);
			// 2.3.1修改用户名称
			startTime = System.currentTimeMillis();
			if (!modifyUserName(nickname, fundAccount, combineId)) {
				log.error("modify homes name failure for user" + nickname + ",operation NO:" + operator);
				// client.setFlag(Constants.CODE_FAILURE);
				return client;
			}

			if (log.isDebugEnabled()) {
				log.debug("修改账户" + operator + "名称，消耗时间：" + (System.currentTimeMillis() - startTime) / 1000 + "s");
				log.debug("访问HOMES服务，修改用户名称成功");
			}
			// 2.4 获取单号相关信息
			startTime = System.currentTimeMillis();
			if (!setCombineInfo(client, fundAccount)) {
				return client;
			}
			if (log.isDebugEnabled()) {
				log.debug("修改账户" + operator + "其他信息，消耗时间：" + (System.currentTimeMillis() - startTime) / 1000 + "s");
			}

			// 2.5资金划转
			startTime = System.currentTimeMillis();
			if (!moveFund(moveFund, combineId, fundAccount, managerCombineId)) {
				return client;
			}
			if (log.isDebugEnabled()) {
				log.debug("账户" + operator + "资金划转，消耗时间：" + (System.currentTimeMillis() - startTime) / 1000 + "s");
				log.debug("访问HOMES服务，进行资金划转成功");
				log.debug("访问HOMES服务结束,处理过程正常，成功推出！！！");
			}
			client.setFlag(Constants.CODE_SUCCESS);
			return client;
		} catch (Exception e) {
			log.error("访问HOMES发生错误：");
			log.error(e.getMessage(), e);
			return client;
		}

	}

	private boolean mainAccountCashIsEnough(String nickname, String moveFund, String fundAccount) throws Exception {
		// 判断管理账户资金是否充足,资金充足，可以操盤,插入操盘数据
		if (!StringUtils.equals("1", acount.cashIsEnough(moveFund, fundAccount))) {
			if (log.isDebugEnabled()) {
				log.debug("小homes资金账户[" + fundAccount + "]不足，不能操盘,操盘用户：" + nickname);
			}
			fundAccountSet.add(fundAccount);
			return false;
		}
		return true;
	}

	private boolean modifyUserName(String nickname, String fundAccount, String combineId) throws Exception {
		String trueName = StringUtils.defaultIfBlank(acount.queryTrueName(nickname), "匿名用户XX");
		StockModifyUserName user = new StockModifyUserName(fundAccount, combineId, trueName);
		user.callHomes(fn_change_assetName);
		return user.visitSuccess(fn_change_assetName);
	}

	private boolean canUse(String operator, String combineId, String fundAccount) throws Exception {
		StockCapitalChanges changes = new StockCapitalChanges(fundAccount, combineId);
		if (log.isDebugEnabled()) {
			log.debug("开始操盘，判断获取的操盘账号是否可用，开始访问HOMES");
		}
		changes.callHomes(Fn_stock_current);
		IDataset ds = changes.getDataSet().getDataset(0);
		String currentCash = "";
		ds.beforeFirst();
		while (ds.hasNext()) {
			ds.next();
			currentCash = ds.getString("current_cash");
			if (StringUtils.isNotBlank(currentCash)) {
				break;
			}
		}

		Float money = 0f;
		if (StringUtils.isNotBlank(currentCash)) {
			money = StringUtil.keepTwoDecimalFloat(Float.parseFloat(currentCash));
		}
		if (money != 0f) {
			log.error("操盘账号：" + operator + " 仍然有有资金在HOMES中，不可使用!!!");
			// ApiUtils.send(Constants.MODEL_OPERATOR_HAS_CASH_FN,
			// stock_manager_phone, operator);
			return false;
		}
		if (log.isDebugEnabled()) {
			log.debug("访问HOMES结束,该操盘账号[" + operator + "]可用!");
		}
		return true;
	}

	private boolean setCombineInfo(TradeDO clientDO, String fundAccount) throws Exception {
		StockCombineInfo assetInfo = new StockCombineInfo(fundAccount, clientDO.getCombineId());
		assetInfo.callHomes(func_am_combinfo_qry);
		// assetInfo.over();
		return setClientAssetInfo(clientDO, assetInfo.getDataSet());
	}

	private boolean modifyClientPwd(TradeDO clientDO, String operatorPwd, String operatorAcount, String newOperatePwd)
			throws Exception {
		clientDO.setOperatorPwd(newOperatePwd);
		StockModifyPwd modify = new StockModifyPwd(operatorAcount, operatorPwd, newOperatePwd);
		modify.callHomes(Fn_changePwd);
		// modify.over();
		return modify.visitSuccess(Fn_changePwd);

	}

	private boolean moveFund(String moveFund, String clientCombineId, String managerAcount, String managerCombineId)
			throws Exception {
		StockAssetMove move = new StockAssetMove(managerAcount, managerCombineId, clientCombineId, moveFund);
		move.callHomes(Fn_asset_move);
		return move.visitSuccess(Fn_asset_move);
	}

	private boolean setClientAssetInfo(TradeDO acountDO, IDatasets ds) {
		IDataset set = ds.getDataset(0);
		String asset_id = set.getString("asset_id");
		if (StringUtils.isBlank(asset_id)) {
			return false;
		}
		acountDO.setAssetId(asset_id);
		acountDO.setAssetunitName(set.getString("assetunit_name"));
		acountDO.setClientName(set.getString("combine_name"));
		acountDO.setCombineName(set.getString("combine_name"));
		return true;
	}

	private void returnOperateInfo(Map<String, String> currentOperate, StockRadioDO radioDO, float tradeFund)
			throws Exception {
		currentOperate.put("tradeFund", tradeFund + "");
		String guaranteeCash = StringUtil.keepThreeDot(tradeFund / radioDO.getAssignRadio());
		currentOperate.put("guaranteeCash", guaranteeCash);
		String stopCash = StringUtil.keepThreeDot(tradeFund * radioDO.getStopRadio());
		String warnCash = StringUtil.keepThreeDot(tradeFund * radioDO.getWarnRadio());
		currentOperate.put("stopCash", stopCash);
		currentOperate.put("warnCash", warnCash);
		currentOperate.put("startDate", DateUtil.dateToStr(new Date(), 11));
		String manageFee = StringUtil.keepThreeDot(tradeFund * radioDO.getManageFeeRadio());
		currentOperate.put("manageFee", manageFee);
		RuleDO rule = trade.getRule();
		currentOperate.put("useRule", rule.getUse_rule());
		currentOperate.put("securetRule", rule.getSecuret_rule());
	}

	@Override
	public Map<String, String> enterAddGuaranteePage(String nickname, String addGuranteeCash) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", "0");// 增加保证金操盘额度大于设置上线
		try {
			Float addGuranteeCashFloat = Float.parseFloat(StringUtils.defaultIfBlank(addGuranteeCash, "0"));
			AddGuaranteePageDO pageDO = trade.enterAddGuaranteePage(nickname, addGuranteeCashFloat);
			if (null != pageDO) {
				Float profitAndLossCash = pageDO.getProfitAndLossCash();
				Float newProfitAndLossCash = 0f;
				// 计算补充的亏损值
				Float loss = 0f;
				// 如果有亏损，先填平亏损，如果没有，盈利多少就是多少
				Float balance = 0f;
				if (profitAndLossCash < 0) {
					balance = addGuranteeCashFloat + profitAndLossCash;
					if (balance < 0) {
						newProfitAndLossCash = balance;
						loss = addGuranteeCashFloat;
						balance = 0f;
					} else {
						loss = profitAndLossCash;
					}
				} else if (profitAndLossCash >= 0) {
					newProfitAndLossCash = profitAndLossCash;
					balance = addGuranteeCashFloat;
				}

				Float progressBar = 1f;
				Float profitAndLossCashPercent = newProfitAndLossCash / pageDO.getCurrentGuaranteeCash();
				if (newProfitAndLossCash < 0) {
					progressBar = 1 + profitAndLossCashPercent;
				}
				pageDO.setProgressBar(progressBar + "");
				String guaranteeCash = StringUtil.keepThreeDot(pageDO.getGuaranteeCash());
				String currentGuaranteeCash = StringUtil.keepThreeDot(pageDO.getCurrentGuaranteeCash());
				map.put("guaranteeCash", guaranteeCash);
				map.put("currentGuaranteeCash", currentGuaranteeCash);
				String orginOperateLimit = StringUtil.keepThreeDot(pageDO.getOrginOperateLimit());
				String currentOperateLimit = StringUtil.keepThreeDot(pageDO.getCurrentOperateLimit());
				map.put("orginOperateLimit", orginOperateLimit);
				map.put("currentOperateLimit", currentOperateLimit);
				map.put("progressBar", pageDO.getProgressBar());
				map.put("profitAndLossCash", newProfitAndLossCash + "");
				map.put("assignRadio", pageDO.getAssignRadio());

				map.put("profitAndLossCashPercent", StringUtil.keepThreeDot(profitAndLossCashPercent));
				map.put("loss", StringUtil.keepThreeDot(loss));
				map.put("warnCash", StringUtil.keepThreeDot(pageDO.getWarnCash()));
				map.put("stopCash", StringUtil.keepThreeDot(pageDO.getStopCash()));
				map.put("warnRadio", StringUtil.keepThreeDot(pageDO.getWarnRadio()));
				map.put("feeRadio", StringUtil.keepFourDot(pageDO.getFeeRadio()));
				map.put("stopRadio", StringUtil.keepThreeDot(pageDO.getStopRadio()));
				map.put("fundAccount", pageDO.getFundAccount());
				map.put("managerCombineId", pageDO.getManagerCombineId());
				map.put("needDeductFee", StringUtil.keepThreeDot(balance));
				map.put("assignCash", StringUtil.keepThreeDot(pageDO.getAssignCash()));
				map.put("flag", "1");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return map;
	}

	@Override
	public Map<String, String> addCuarantee(AddCuaranteeForm transferData) throws RuntimeException {

		long startTime = System.currentTimeMillis();
		Map<String, String> map = new HashMap<String, String>();
		String flag = Constants.CODE_SUCCESS;
		String nickname = transferData.getNickname();
		String addedAssginCapital = "";
		try {
			log.info("用户[" + nickname + "]开始增加保证金...");
			// 判断当前账户是否有足够的钱
			String addedGuaranteeCash = transferData.getAddedGuaranteeCash();
			// 判断用户输入的保证金额是否正确
			if (StringUtils.isBlank(addedGuaranteeCash) || !addedGuaranteeCash.matches("[1-9]\\d*")) {
				String msg = "亲爱的【" + nickname + "】请正确输入保证金";
				map.put("msg", msg);
				map.put("flag", Constants.CODE_ERROR_MONEY);
				if (log.isDebugEnabled()) {
					log.debug(msg);
				}
				return map;
			}

			String isEnough = trade.isEnoughCashForClient(nickname, addedGuaranteeCash);
			if (!StringUtils.equals(Constants.CODE_SUCCESS, isEnough)) {
				if (log.isDebugEnabled()) {
					String msg = "亲爱的" + nickname + " 对不起 ，账户余额不足，请充值后再操作";
					log.debug(msg);
					map.put("msg", msg);
					map.put("flag", Constants.CODE_ERROR_WALLET_LITTLE);
				}
				return map;
			}

			Map<String, String> result = enterAddGuaranteePage(nickname, addedGuaranteeCash);
			Float currentOperateLimit = Float.parseFloat(result.get("currentOperateLimit"));
			Float assignCash = Float.parseFloat(result.get("assignCash"));
			if (StringUtil.compareNum(currentOperateLimit, assignCash)) {
				map.put("msg", "增加保证金后，您当前操盘额度【" + currentOperateLimit + "】元不能超过设置的操盘上线【" + assignCash
						+ "】元，如果需要调整，可以联系客户，谢谢!");
				map.put("flag", Constants.CODE_ERROR_EXCEED_LIMIT);
				return map;
			}

			String profitAndLossCash = result.get("profitAndLossCash");
			transferData.setCurrentOperateLimit(result.get("currentOperateLimit"));
			transferData.setCurrentGuaranteeCash(result.get("currentGuaranteeCash"));
			transferData.setProfitAndLossCash(profitAndLossCash);

			// 1、原始操盘额度
			String orginTradeFund = trade.queryOrginTradeFund(nickname);

			Float profitAndLossCashFloat = Float.parseFloat(profitAndLossCash);

			// 如果盈亏金额小于0，那么证明填充的钱未填平亏损，只是将增加的钱划转到HOMES
			if (profitAndLossCashFloat < 0f) {
				addedAssginCapital = addedGuaranteeCash;
			} else {
				// 如果盈亏的钱够填平亏损或则是原来就在盈利，那么就需要计算新增的配资额度
				// 1、1.需要配资的钱
				Float capitalCashNeedFloat = Float.parseFloat(transferData.getCurrentOperateLimit())
						- Float.parseFloat(orginTradeFund) + (-1 * Float.parseFloat(result.get("loss")));
				String capitalCashNeed = StringUtil.keepTwoDecimalFloat(capitalCashNeedFloat) + "";
				if (0f == capitalCashNeedFloat) {// 如果是有亏损，刚够填平亏损或则不够填平亏损，那么配资额度就为0，下面无需再计算账户余额是否够配资
					flag = "1";
				} else {
					// 管理账户余额是否充足
					flag = StringUtils.defaultIfBlank(acount.cashIsEnough(capitalCashNeed, result.get("fundAccount")),
							"2");
				}

				// 管理账户不充足，需要给提示，告诉用户只划转保证金，不配资
				String isSure = transferData.getFlag();
				if (!StringUtils.equals(Constants.CODE_SUCCESS, flag)) {
					if (StringUtils.equals(Constants.CODE_FIRST_ENTER_ADD_GUARANTEE, isSure)
							|| StringUtils.isBlank(isSure)) {
						map.put("flag", flag);
						return map;
					}
				}

				// 资金不足，只划转用户的保证金,不去进行配资
				if (!StringUtils.equals(Constants.CODE_SUCCESS, flag)) {
					addedAssginCapital = addedGuaranteeCash;
				} else {
					// 新增的配资的钱
					addedAssginCapital = capitalCashNeed;
				}
			}

			transferData.setAddedAssginCapital(addedAssginCapital);

			// 2、资金划转
			Float moveCash = 0f;
			if (StringUtils.isNotBlank(addedAssginCapital)) {
				moveCash = Float.parseFloat(addedAssginCapital);
				if (moveCash != 0f) {
					log.info("客户【" + nickname + "】【增加保证金addCuarantee】访问HOMES,执行资金划转操作");
					String clientCombineId = acount.queryClientCombineId(nickname);
					// ManagerDO managerDO = acount.getStockManager();

					String fundAccount = result.get("fundAccount");
					String managerCombineId = result.get("managerCombineId");
					boolean moveSuccess = false;
					// 切换小homs
					if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(fundAccount, "6")) {
						moveSuccess = move(addedAssginCapital, clientCombineId, fundAccount);
						if (log.isDebugEnabled()) {
							log.debug("向小homs划拨资金");
						}
					} else {
						moveSuccess = moveFund(addedAssginCapital, clientCombineId, fundAccount, managerCombineId);
					}
					if (!moveSuccess) {
						log.error("向用户" + nickname + "资金划转失败");
						throw new RuntimeException("向用户" + nickname + "资金划转失败");
					}
					if (log.isDebugEnabled()) {
						log.debug("成功【划转资金到操盘账户】,nickname=" + nickname + ",转移资金：" + addedAssginCapital);
					}
				}
			}

			// 3、如果主账户资金充足从主账户减去配资的钱
			if (moveCash != 0f) {
				String fundAccount = acount.queryFundAccount(nickname);
				acount.addTotalFund("0", "-" + moveCash, fundAccount, "主账户扣除配资的钱", "recharge");
				// 更新历史金额状态为N
				acount.updateStatusToN(fundAccount);
				// 更新当前金额状态为Y
				acount.updateStatusToY(fundAccount);
				// trade.deductTradeFund(moveCash);
				// // 5、记录流水新增配资
				// trade.recordFundflow(nickname,
				// Constants.TRANS_FROM_MAINACOUNT_TO_STOCKACCOUNT,
				// addedAssginCapital);
			}

			// 4、更新STOCK_USER_OPERATE_MAININFO
			trade.modifyFund(transferData);
			log.info("成功更新用户[" + nickname + "]操盘信息表");

			// 5、从客户账户扣除保证金
			trade.deductGuaranteeCash(addedGuaranteeCash, nickname);

			// 6、记录流水新增配资
			/*
			 * trade.recordFundflow(nickname,
			 * Constants.CLIENTWALLET_TO_MAINACOUNT, "-" + addedGuaranteeCash,
			 * "增加保证金");
			 */

			// 第一笔流水记录亏损
			String loss = result.get("loss");
			if (!StringUtils.equals("0", loss)) {
				trade.recordFundflow(nickname, Constants.REPLENISH_LOSS,
						(StringUtils.startsWith(loss, "-") ? loss : "-" + loss), "补充保证金（补充亏损）");
			}

			map.put("flag", flag);

			if (log.isDebugEnabled()) {
				log.debug("客户" + nickname + "成功增加保证金结束！");
			}

			Float deductFee = Float.parseFloat(result.get("needDeductFee"));
			if (deductFee != 0f) {
				// 第二笔流水记录增加的保证金
				trade.recordFundflow(nickname, Constants.CLIENTWALLET_TO_MAINACOUNT, "-" + deductFee, "增加保证金（扩大操盘）");

				Map<String, String> param = new HashMap<String, String>();
				param.put("nickname", nickname);
				param.put("needDeductFee", result.get("needDeductFee"));
				jobHandler.handleOtherJob(Constants.TYPE_JOB_DEDUCT_ADDGURANTEE, param);
			}

			if (log.isDebugEnabled()) {
				log.debug("执行增加保证金操作耗时：" + ((System.currentTimeMillis() - startTime) / 1000) + "s");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error("用户[" + nickname + "]增加保证金失败，向表中记录日志");
			Map<String, String> param = new HashMap<String, String>();
			param.put("nickname", nickname);
			param.put("addedAssginCapital", addedAssginCapital);
			param.put("message", e.getMessage());
			jobHandler.handleOtherJob(Constants.TYPE_JOB_RECORD_ERROR, param);
			map.put("flag", Constants.CODE_FAILURE);
			throw new RuntimeException(e);
		} finally {
			log.info("增加保证金结束");
		}
		return map;
	}

	@Override
	public boolean setAutoAddCuaranteeCash(String nickname) {
		try {
			trade.setAutoAddCuaranteeCash(nickname);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	private String genNewPassword() {
		return StringUtil.genRandomNum(6);
	}

	@Override
	public List<HistoryOperationDO> getHistoryOperation(String nickname, String offset) {
		List<HistoryOperationDO> historyDO = new ArrayList<HistoryOperationDO>();
		try {
			historyDO = trade.getHistoryOperation(nickname, offset);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return historyDO;
	}

	@Override
	public Map<String, String> getEveningFlag(String nickname) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			EveningEndDO eveningEndDO = trade.getEveningFlag(nickname);
			if (null != eveningEndDO) {
				map.put("id", eveningEndDO.getId());
				map.put("assetId", eveningEndDO.getAssetId());
				map.put("capital", StringUtil.keepThreeDot(eveningEndDO.getCapital()));
				map.put("stopPercent", eveningEndDO.getStopPercent());
			} else {
				map.put("id", "");
				map.put("assetId", "");
				map.put("capital", "");
				map.put("stopPercent", "");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return map;
	}

	@Override
	public Map<String, String> hasOperation(String nickname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("homesStatus", Constants.HOMES_STATUS);
		try {
			HasOpertAndDebtDO andDebtDO = trade.queryHasOperation(nickname);
			if (null != andDebtDO) {
				String flag = StringUtils.isNotBlank(andDebtDO.getOperation()) ? "1" : "0";
				map.put("flag", flag);
				map.put("debt", andDebtDO.getDebt() == null ? "" : andDebtDO.getDebt());
				map.put("balance", andDebtDO.getBalance() + "");
				map.put("desc", "交易系统清算时间为0:00到8:00");
				map.put("fee", andDebtDO.getFee() + "");

				/** start 放入杠杆倍数 update 20150616 */
				if ("1".equals(andDebtDO.getOperation())) {
					StockRadioDO radioDO = acount.getAssignRadioForCurrUser(nickname);
					map.put("assignRadio", Float.toString(radioDO.getAssignRadio()));
				} else {
					DictionariesDO dictionariesDO = trade.getDictionariesByDicWord("assignRadio");
					if (dictionariesDO != null) {
						map.put("assignRadio", dictionariesDO.getDicValue());
					}
				}
				/** end 放入杠杆倍数 update 20150616 */
			} else {
				map.put("flag", "0");
				map.put("debt", "");
				map.put("balance", "");
				map.put("desc", "交易系统清算时间为0:00到8:00");
				map.put("fee", "0");
				/** start 放入杠杆倍数 */
				DictionariesDO dictionariesDO = trade.getDictionariesByDicWord("assignRadio");
				if (dictionariesDO != null) {
					map.put("assignRadio", dictionariesDO.getDicValue());
				}
				/** end 放入杠杆倍数 */
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return map;
	}

	@Override
	public String setPayInfo(String userId, String nickname, String merchantId, String cash, String status,
			String respMsg, String merchantDate, String refNo) {
		try {
			trade.setPayInfo(userId, nickname, merchantId, cash, status, "【APP】:" + respMsg, merchantDate, refNo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Constants.CODE_FAILURE;
		}
		return Constants.CODE_SUCCESS;
	}
}
