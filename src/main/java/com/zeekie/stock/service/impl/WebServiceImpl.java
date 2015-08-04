package com.zeekie.stock.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sitong.thinker.common.exception.ServiceInvokerException;
import sitong.thinker.common.page.DefaultPage;
import sitong.thinker.common.page.PageQuery;
import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.AddCashErrorDO;
import com.zeekie.stock.entity.ClientPercentDO;
import com.zeekie.stock.entity.CurrentOperationWebDO;
import com.zeekie.stock.entity.DayDO;
import com.zeekie.stock.entity.DictionariesDO;
import com.zeekie.stock.entity.FinanceProductDO;
import com.zeekie.stock.entity.FinanceProductDetailDO;
import com.zeekie.stock.entity.FlbDO;
import com.zeekie.stock.entity.FundAccountDO;
import com.zeekie.stock.entity.GuessHistoryDO;
import com.zeekie.stock.entity.GuessProductDO;
import com.zeekie.stock.entity.MovecashToRefereeDO;
import com.zeekie.stock.entity.OperateAccountDO;
import com.zeekie.stock.entity.OperationInfoDO;
import com.zeekie.stock.entity.OtherFundFlowDO;
import com.zeekie.stock.entity.OwingFeeDO;
import com.zeekie.stock.entity.PayDO;
import com.zeekie.stock.entity.PercentDO;
import com.zeekie.stock.entity.StatisticsDO;
import com.zeekie.stock.entity.StockCodeDO;
import com.zeekie.stock.entity.TotalFundDO;
import com.zeekie.stock.entity.TransactionDO;
import com.zeekie.stock.entity.UserBankDO;
import com.zeekie.stock.entity.UserInfoDO;
import com.zeekie.stock.entity.WithdrawlDO;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.respository.FinanceMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.AcountService;
import com.zeekie.stock.service.WebService;
import com.zeekie.stock.service.syncTask.SyncHandler;
import com.zeekie.stock.util.StringUtil;
import com.zeekie.stock.web.ClientPage;
import com.zeekie.stock.web.DictionariesPage;
import com.zeekie.stock.web.EveningUpPage;
import com.zeekie.stock.web.FinanceDetailPage;
import com.zeekie.stock.web.FinancePage;
import com.zeekie.stock.web.GuessDetailPage;
import com.zeekie.stock.web.GuessPage;
import com.zeekie.stock.web.MoveToRefereePage;
import com.zeekie.stock.web.OperationInfoPage;
import com.zeekie.stock.web.PayPage;
import com.zeekie.stock.web.PercentDOPage;
import com.zeekie.stock.web.StatisticsPage;
import com.zeekie.stock.web.StockCodePage;
import com.zeekie.stock.web.TotalFundPage;
import com.zeekie.stock.web.WithdrawlPage;

@Service
@Transactional
public class WebServiceImpl implements WebService {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AcountMapper account;

	@Autowired
	private TradeMapper trade;

	@Autowired
	private AcountService service;

	@Autowired
	private BatchMapper batchMapper;

	@Autowired
	private SyncHandler handler;

	@Autowired
	private FinanceMapper financeMapper;

	@Autowired
	private DealMapper dealMapper;

	@Value("${stock.finance.code}")
	private String guessCode;

	@Override
	public DefaultPage<WithdrawlDO> getDepositList(WithdrawlPage withdrawlPage)
			throws ServiceInvokerException {
		List<WithdrawlDO> result = new ArrayList<WithdrawlDO>();
		long total = 0;
		try {
			total = account.queryDepositCount(withdrawlPage);
			if (0 != total) {
				result = account.getDepositList(withdrawlPage);
			}
			return new DefaultPage<WithdrawlDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public boolean withdrawlToUser(String id, String nickname, String cash) {
		try {
			account.updateDepositStatus(id);

			account.deductWithdrawCahs(nickname, cash);

			trade.recordFundflow(nickname,
					Constants.TRANS_FROM_WALET_TO_CLIENT, "-" + cash, "用户提现");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public DefaultPage<TotalFundDO> getTotalFund(TotalFundPage totalFundPage)
			throws ServiceInvokerException {
		List<TotalFundDO> result = new ArrayList<TotalFundDO>();
		long total = 0;
		try {
			total = account.queryTotalFundCount(totalFundPage.getFundAccount());
			if (0 != total) {
				result = account.getTotalFundList(totalFundPage);
			}
			return new DefaultPage<TotalFundDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public DefaultPage<MovecashToRefereeDO> queryMoveCashToReferee(
			MoveToRefereePage moveToRefereePage) throws ServiceInvokerException {
		List<MovecashToRefereeDO> result = new ArrayList<MovecashToRefereeDO>();
		long total = 0;
		try {
			total = account.queryMoveCashToRefereeCount();
			if (0 != total) {
				result = account.queryMoveCashToReferee(moveToRefereePage);
			}
			return new DefaultPage<MovecashToRefereeDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public boolean addTotalFund(String fund, String fundAccount, String desc,
			String storeType) {
		try {
			account.addTotalFund("1", fund, fundAccount, desc, storeType);
			// 更新历史金额状态为N
			account.updateStatusToN(fundAccount);
			// 更新当前金额状态为Y
			account.updateStatusToY(fundAccount);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public DefaultPage<PayDO> getPayList(PayPage payPage)
			throws ServiceInvokerException {
		List<PayDO> result = new ArrayList<PayDO>();
		long total = 0;
		try {
			total = account.getPayListCount();
			if (0 != total) {
				result = account.getPayList(payPage);
			}
			return new DefaultPage<PayDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public boolean payToUs(String id, String nickname, String fund) {
		try {
			if (StringUtils.isNotBlank(id)) {
				account.updatePayStatus(id);
			}

			trade.recharge(nickname, fund);

			trade.recordFundflow(nickname,
					Constants.TRANS_FROM_CLIENT_TO_WALET, "+" + fund, "用戶充值");

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public DefaultPage<CurrentOperationWebDO> getEveningUp(
			EveningUpPage eveningUpPage) throws ServiceInvokerException {
		List<CurrentOperationWebDO> result = new ArrayList<CurrentOperationWebDO>();
		long total = 0;
		try {
			total = account.queryCurrentOperationCount(eveningUpPage
					.getNickname());
			if (0 != total) {
				result = account.queryCurrentOperation(eveningUpPage);
			}
			return new DefaultPage<CurrentOperationWebDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public boolean eveningUp(String id, String nickname)
			throws RuntimeException {
		try {
			// 结束操盘,更新操盘为主动结束操盘
			Map<String, String> result = service.endStock(nickname,
					Constants.EVENING_UP);
			if (!StringUtils.equals(Constants.CODE_SUCCESS, result.get("flag"))) {
				return false;
			}
			// 更新操盘为主动结束操盘
			account.update(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException();
		}
		return true;
	}

	@Override
	public void setFeeCalendar(String yearMonth, String days) {
		batchMapper.batchInsert(TradeMapper.class, "setFeeCalendarBatch",
				parseDays(yearMonth, days));
	}

	private List<DayDO> parseDays(String yearMonth, String days) {
		String[] day = days.split(",");
		List<DayDO> ret = new ArrayList<DayDO>();
		for (String item : day) {
			DayDO dayDO = new DayDO(item, yearMonth);
			ret.add(dayDO);
		}
		return ret;
	}

	@Override
	public String initFeeDays(String month) {

		try {
			List<String> result = trade.initFeeDays(month);
			StringBuilder builder = new StringBuilder();
			for (String day : result) {
				builder.append(day);
				builder.append(",");
			}
			int len = builder.length();
			String days = "";
			if (len >= 1) {
				days = builder.substring(0, len - 1);
			}
			return days;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}

	@Override
	public DefaultPage<ClientPercentDO> getClient(ClientPage clientPage)
			throws ServiceInvokerException {
		List<ClientPercentDO> result = new ArrayList<ClientPercentDO>();
		long total = 0;
		try {
			total = account.queryClientCount(clientPage.getNickname());
			if (0 != total) {
				result = account.queryClient(clientPage);
			}
			return new DefaultPage<ClientPercentDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public String getClientById(String id) throws ServiceInvokerException {
		String result = "";
		ClientPercentDO percentDO;
		try {
			percentDO = account.getClientById(id);
			if (null != percentDO) {
				JSONObject jo = JSONObject.fromObject(percentDO,
						Constants.jsonConfig);
				jo.put("managementFeePercent", StringUtil.keepFourDot(percentDO
						.getManagementFeePercent()));
				result = jo.toString();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e.getMessage());
		}
		return result;
	}

	@Override
	public String saveClientInfo(String data) throws ServiceInvokerException {
		try {
			JSONObject jo = JSONObject.fromObject(data);

			JSONObject dbdata = JSONObject.fromObject(getClientById(jo
					.getString("id")));

			String range = StringUtils.trim(jo.getString("range"));
			String stopLine = StringUtils.trim(jo.getString("stopLine"));
			String warnLine = StringUtils.trim(jo.getString("warnLine"));
			String assignPercent = StringUtils.trim(jo
					.getString("assignPercent"));
			String toRefereePacket = StringUtils.trim(StringUtils.trim(jo
					.getString("toRefereePacket")));
			String registerPacket = StringUtils.trim(jo
					.getString("registerPacket"));
			String assignCash = StringUtils.trim(jo.getString("assignCash"));
			String managementFeePercent = StringUtils.trim(jo
					.getString("managementFeePercent"));
			String upLinePercent = StringUtils.trim(jo
					.getString("upLinePercent"));
			String downLinePercent = StringUtils.trim(jo
					.getString("downLinePercent"));

			ClientPercentDO clientPercentDO = new ClientPercentDO();
			if (!StringUtils.equals(range, dbdata.getString("range"))) {
				clientPercentDO.setRange(range);
			}
			if (!StringUtils.equals(stopLine, dbdata.getString("stopLine"))) {
				clientPercentDO.setStopLine(Float.parseFloat(stopLine));
			}
			if (!StringUtils.equals(warnLine, dbdata.getString("warnLine"))) {
				clientPercentDO.setWarnLine(Float.parseFloat(warnLine));
			}
			if (!StringUtils.equals(assignPercent,
					dbdata.getString("assignPercent"))) {
				clientPercentDO.setAssignPercent(Float
						.parseFloat(assignPercent));
			}
			if (!StringUtils.equals(toRefereePacket,
					dbdata.getString("toRefereePacket"))) {
				clientPercentDO.setToRefereePacket(Float
						.parseFloat(toRefereePacket));
			}
			if (!StringUtils.equals(registerPacket,
					dbdata.getString("registerPacket"))) {
				clientPercentDO.setRegisterPacket(Float
						.parseFloat(registerPacket));
			}
			if (!StringUtils.equals(assignCash, dbdata.getString("assignCash"))) {
				clientPercentDO.setAssignCash(Float.parseFloat(assignCash));
			}
			if (!StringUtils.equals(managementFeePercent,
					dbdata.getString("managementFeePercent"))) {
				clientPercentDO.setManagementFeePercent(Float
						.parseFloat(managementFeePercent));
			}
			if (!StringUtils.equals(upLinePercent,
					dbdata.getString("upLinePercent"))) {
				clientPercentDO.setUpLinePercent(Float
						.parseFloat(upLinePercent));
			}
			if (!StringUtils.equals(downLinePercent,
					dbdata.getString("downLinePercent"))) {
				clientPercentDO.setDownLinePercent(Float
						.parseFloat(downLinePercent));
			}
			clientPercentDO.setId(jo.getString("id"));
			account.saveClientInfo(clientPercentDO);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e.getMessage());
		}
		return "1";
	}

	@Override
	public String getFundAccount(String status) throws ServiceInvokerException {
		String result = "";
		List<FundAccountDO> fundAccount;
		try {
			fundAccount = account.getFundAccount(status);
			if (null != fundAccount) {
				JSONArray jo = JSONArray.fromObject(fundAccount);
				result = jo.toString();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e.getMessage());
		}
		return result;
	}

	@Override
	public String saveFundAccount(String data) throws ServiceInvokerException {
		// JSONArray ja = JSONArray.fromObject(data);
		JSONObject jo = JSONObject.fromObject(data);
		String type = jo.getString("type");
		String managerAccountId = jo.getString("id");
		try {
			account.updateFundAccountStatus(type, managerAccountId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e.getMessage());
		}
		return "1";
		// try {
		// if (jo.isEmpty()) {
		// account.updateAllStatusToOne();
		// } else {
		// StringBuffer sb = new StringBuffer();
		// for (int i = 0; i < ja.size(); i++) {
		// JSONObject jo = ja.getJSONObject(i);
		// sb.append("'");
		// sb.append(jo.getString("id"));
		// sb.append("',");
		// }
		// String accounts = sb.toString();
		// accounts = accounts.substring(0, accounts.lastIndexOf(","));
		// account.updateStatusToZero(accounts);
		// account.updateStatusToOne(accounts);
		// }
		// } catch (Exception e) {
		// log.error(e.getMessage(), e);
		// throw new ServiceInvokerException(e.getMessage());
		// }

	}

	@Override
	public DefaultPage<OwingFeeDO> getOwingFee(ClientPage clientPage)
			throws ServiceInvokerException {
		List<OwingFeeDO> result = new ArrayList<OwingFeeDO>();
		long total = 0;
		try {
			total = account.queryOwingFeeCount(clientPage.getNickname());
			if (0 != total) {
				result = account.queryOwingFee(clientPage);
			}
			return new DefaultPage<OwingFeeDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public void openOrCloseApp(String param) {
		handler.handleJob(Constants.TYPE_JOB_CONTROL_APP, param);
	}

	@Override
	public DefaultPage<PercentDO> caculatePercent(PercentDOPage page)
			throws ServiceInvokerException {
		List<PercentDO> result = new ArrayList<PercentDO>();
		long total = 0;
		try {
			total = account.queryTotal(page.getAssetName());
			if (0 != total) {
				result = account.queryList(page);

				List<OperateAccountDO> operateAccountDO = account
						.queryOperateAccountByFlag();

				for (PercentDO item : result) {
					for (OperateAccountDO each : operateAccountDO) {
						if (StringUtils.equals(each.getFundAccount(),
								item.getFundAcount())) {
							String num = each.getNums();
							if (StringUtils.equals(each.getFlag(), "1")) {
								item.setUseCount(num);
							} else {
								item.setLeaveCount(num);
							}
						}
						if (StringUtils.isNotBlank(item.getUseCount())
								&& StringUtils.isNotBlank(item.getLeaveCount())) {
							break;
						}
					}
				}
			}
			return new DefaultPage<PercentDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public DefaultPage<UserInfoDO> getClientInfo(ClientPage clientPage)
			throws ServiceInvokerException {
		List<UserInfoDO> result = new ArrayList<UserInfoDO>();
		long total = 0;
		try {
			total = account.queryClientInfoCount(clientPage.getNickname());
			if (0 != total) {
				result = account.queryClientInfo(clientPage);
			}
			return new DefaultPage<UserInfoDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public DefaultPage<OtherFundFlowDO> getFundFlowInfo(ClientPage clientPage)
			throws ServiceInvokerException {
		List<OtherFundFlowDO> result = new ArrayList<OtherFundFlowDO>();
		long total = 0;
		try {
			total = account.queryFundFlowInfoCount(clientPage.getNickname());
			if (0 != total) {
				result = account.queryFundFlowInfo(clientPage);
				// convertBussinessType(result);
			}
			return new DefaultPage<OtherFundFlowDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	private void convertBussinessType(List<OtherFundFlowDO> result) {
		for (int i = 0; i < result.size(); i++) {
			OtherFundFlowDO flowDO = result.get(i);
			String type = StringUtils.trim(flowDO.getBussniessType());
			Float fund = flowDO.getFund();
			String desc = flowDO.getDesc();
			if (StringUtils.equals(type, Constants.TIPS_RETURN_GURANTEE_CASH)) {
				flowDO.setBussniessType("返还平仓后保证金");
				flowDO.setFundStr("+" + fund);
			} else if (StringUtils.equals("10", type)) {
				flowDO.setBussniessType("支付保证金");
				flowDO.setFundStr("-" + fund);
			} else if (StringUtils.equals("20", type)) {
				flowDO.setBussniessType("获取配资");
				flowDO.setFundStr("+" + fund);
			} else if (StringUtils.equals("30", type)) {
				flowDO.setBussniessType("扣除服务费");
				flowDO.setFundStr("-" + fund);
			} else if (StringUtils.equals("40", type)) {
				flowDO.setBussniessType("用户充值");
				flowDO.setFundStr("+" + fund);
			} else if (StringUtils.equals("50", type)) {
				flowDO.setBussniessType(desc);
				flowDO.setFundStr("-" + fund);
			} else if (StringUtils.equals("60", type)) {
				boolean flag = StringUtils.startsWith("" + fund, "-");
				flowDO.setBussniessType(flag ? "亏损(扣除保证金)" : "返还保证金");
				flowDO.setFundStr(flag ? "" + fund : "+" + fund);
			} else if (StringUtils.equals("80", type)) {
				flowDO.setBussniessType(desc);
				flowDO.setFundStr("+" + fund);
			} else if (StringUtils.equals("90", type)) {
				flowDO.setBussniessType(desc);
				flowDO.setFundStr("+" + fund);
			} else if (StringUtils.equals("100", type)) {
				flowDO.setBussniessType(desc);
				flowDO.setFundStr("-" + fund);
			} else if (StringUtils.equals("110", type)) {//
				flowDO.setBussniessType(desc);
				flowDO.setFundStr("+" + fund);
			} else if (StringUtils.equals("120", type)) {//
				flowDO.setBussniessType(desc);
				flowDO.setFundStr("+" + fund);
			} else if (StringUtils.equals("130", type)) {
				flowDO.setBussniessType(desc);
				flowDO.setFundStr("+" + fund);
			} else if (StringUtils.equals("140", type)) {
				flowDO.setBussniessType(desc);
				flowDO.setFundStr("+" + fund);
			}
		}

	}

	@Override
	public DefaultPage<OperationInfoDO> getOperationInfo(
			OperationInfoPage infoPage) throws ServiceInvokerException {
		List<OperationInfoDO> result = new ArrayList<OperationInfoDO>();
		long total = 0;
		try {
			total = account.queryOperationInfoCount(infoPage.getNickname(),
					infoPage.getRange());
			if (0 != total) {
				result = account.queryOperationInfo(infoPage);
			}
			return new DefaultPage<OperationInfoDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public String sendRedPacket(String data) throws ServiceInvokerException {
		JSONObject jo = JSONObject.fromObject(data);
		String nickname = jo.getString("nickname");
		String telephone = jo.getString("telephone");
		String fund = jo.getString("fund");
		String message = jo.getString("message");
		try {
			if (StringUtils.isNotBlank(fund)) {
				account.moveProfitToUserWallet(nickname, fund);

				trade.recordFundflow(nickname, Constants.SEND_RED_PACKET, fund,
						"平台财务优化");
			}

			if (StringUtils.isNotBlank(message)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("nickname", nickname);
				map.put("message", message);
				map.put("telephone", telephone);
				handler.handleOtherJob(Constants.TYPE_JOB_REDPACKET_NOTICE, map);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e.getMessage());
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public String sendMsgToAll(String data) throws ServiceInvokerException {
		JSONObject jo = JSONObject.fromObject(data);
		String message = jo.getString("message");
		try {
			if (StringUtils.isNotBlank(message)) {
				handler.handleJob(Constants.TYPE_JOB_SENDMSG_NOTICE, message);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e.getMessage());
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public boolean undoWithDrawal(String id, String nickname, String cash)
			throws ServiceInvokerException {
		try {
			account.deleteWithdral(id);

			account.updateWithdrawCash(nickname, cash);

			trade.recordFundflow(nickname, Constants.SEND_UNDO_WITHDRAWL, cash,
					"用户撤销提现");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public void updateReceiptStatus(Map<String, String> param)
			throws ServiceInvokerException {
		String nickname = "";
		try {
			nickname = account.queryNickname(param.get("userId"));
		} catch (Exception e) {
			throw new ServiceInvokerException(e.getMessage());
		}
		param.put("nickname", nickname);
		trade.updateReceiptStatus(param);
	}

	@Override
	public void setFreeDays(String yearMonth, String days)
			throws ServiceInvokerException {
		batchMapper.batchInsert(TradeMapper.class, "setFeeCalendarFreeBatch",
				parseDays(yearMonth, days));
	}

	@Override
	public DefaultPage<TransactionDO> getTransactionInfo(ClientPage clientPage)
			throws ServiceInvokerException {
		List<TransactionDO> result = new ArrayList<TransactionDO>();
		long total = 0;
		try {
			total = account.queryTransactionInfoCount(clientPage.getNickname());
			if (0 != total) {
				result = account.queryTransactionInfo(clientPage);
			}
			return new DefaultPage<TransactionDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public DefaultPage<UserBankDO> getUserbank(ClientPage clientPage)
			throws ServiceInvokerException {
		List<UserBankDO> result = new ArrayList<UserBankDO>();
		long total = 0;
		try {
			total = account.queryUserbankCount(clientPage.getNickname());
			if (0 != total) {
				result = account.queryUserbankInfo(clientPage);
			}
			return new DefaultPage<UserBankDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public String saveUserbank(JSONArray ja) throws ServiceInvokerException {
		try {
			List<UserBankDO> list = JSONArray.toList(ja, UserBankDO.class);
			batchMapper.batchInsert(AcountMapper.class, "updateUserbank", list);
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public String deleteUserbank(String id) throws ServiceInvokerException {
		try {
			account.deleteUserbank(id);
			account.deleteIdCard(id);
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public DefaultPage<StatisticsDO> queryStatistics(
			StatisticsPage statisticsPage) throws ServiceInvokerException {
		List<StatisticsDO> result = new ArrayList<StatisticsDO>();
		long total = 0;
		try {
			total = trade.queryStatisticsCount(statisticsPage);
			if (0 != total) {
				result = trade.queryStatistics(statisticsPage);

				StatisticsDO statisticsDO = trade.queryOtherStaticValue();

				for (StatisticsDO item : result) {
					item.setBalance(statisticsDO.getBalance());
					item.setBond(statisticsDO.getBond());
					item.setProfit(statisticsDO.getProfit());
				}
			}
			return new DefaultPage<StatisticsDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	/**
	 * 添加字典信息
	 * 
	 * @param dictionariesDO
	 * @return
	 * @throws Exception
	 */
	public String insertDictionaries(DictionariesDO dictionariesDO) {
		try {
			DictionariesPage dictionariesPage = new DictionariesPage(0, 10,
					null, null, null, null);
			dictionariesPage.setDicWord(dictionariesDO.getDicWord());
			long count = trade.queryDictionariesCount(dictionariesPage);
			if (count == 0) {
				return String.valueOf(trade.insertDictionaries(dictionariesDO));
			} else {
				return "-1";
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		}
		return null;
	}

	/**
	 * 删除字典信息
	 * 
	 * @param id
	 * @return
	 */
	public String deleteDictionaries(String id) {
		trade.deleteDictionaries(id);
		return "1";
	}

	/**
	 * 修改字典信息
	 * 
	 * @param dictionariesDO
	 * @return
	 */
	public String updateDictionaries(DictionariesDO dictionariesDO) {
		return String.valueOf(trade.updateDictionaries(dictionariesDO));

	}

	/**
	 * 查询字典信息
	 * 
	 * @param dictionariesPage
	 * @return
	 */
	public DefaultPage<DictionariesDO> queryDictionaries(
			DictionariesPage dictionariesPage) throws ServiceInvokerException {
		List<DictionariesDO> result = new ArrayList<DictionariesDO>();
		long total = 0;
		try {
			total = trade.queryDictionariesCount(dictionariesPage);
			if (0 != total) {
				result = trade.queryDictionaries(dictionariesPage);
			}
			return new DefaultPage<DictionariesDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	/**
	 * 查找字典信息
	 * 
	 * @param id
	 * @return
	 */
	public String getDictionaries(String id) {
		DictionariesDO dictionariesDO = trade.getDictionaries(id);
		JSONObject jo = JSONObject.fromObject(dictionariesDO,
				Constants.jsonConfig);
		return jo.toString();
	}

	@Override
	public DefaultPage<FinanceProductDO> getAllCurrentFinance(FinancePage page)
			throws ServiceInvokerException {

		List<FinanceProductDO> result = new ArrayList<FinanceProductDO>();
		long total = 0;
		try {
			total = financeMapper.queryAllCurrentFinanceCount(page.getDate());
			if (0 != total) {
				result = financeMapper.getAllCurrentFinance(page);
			}
			return new DefaultPage<FinanceProductDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public String saveProduct(String financeProduct, String financeTotalLimit,
			String annualIncome, String expireDay, String carryDate,
			String maxLimit, String minLimit) throws ServiceInvokerException {
		String productCode = StringUtil.genRandomNum(6);
		financeMapper.insertProduct(productCode, financeProduct,
				financeTotalLimit, annualIncome, expireDay, carryDate,
				maxLimit, minLimit);
		return Constants.CODE_SUCCESS;
	}

	@Override
	public DefaultPage<FinanceProductDetailDO> getFinanceDetail(
			FinanceDetailPage page) throws ServiceInvokerException {

		List<FinanceProductDetailDO> result = new ArrayList<FinanceProductDetailDO>();
		long total = 0;
		try {
			total = financeMapper
					.queryFinanceDetailCount(page.getProductCode());
			if (0 != total) {
				result = financeMapper.queryFinanceDetail(page);
			}
			return new DefaultPage<FinanceProductDetailDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public DefaultPage<FlbDO> getFlb(PageQuery flbPage)
			throws ServiceInvokerException {

		List<FlbDO> result = new ArrayList<FlbDO>();
		long total = 0;
		try {
			total = financeMapper.queryFlbUnitCount();
			if (0 != total) {
				result = financeMapper.queryFlbUnit(flbPage);
			}
			return new DefaultPage<FlbDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public DefaultPage<AddCashErrorDO> getError(ClientPage errorPage)
			throws ServiceInvokerException {
		List<AddCashErrorDO> result = new ArrayList<AddCashErrorDO>();
		long total = 0;
		try {
			total = trade.getErrorCount(errorPage.getNickname());
			if (0 != total) {
				result = trade.getErrorList(errorPage);
			}
			return new DefaultPage<AddCashErrorDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}

	}

	public DefaultPage<StockCodeDO> queryStockCode(StockCodePage stockPage)
			throws ServiceInvokerException {
		List<StockCodeDO> result = new ArrayList<StockCodeDO>();
		long total = 0;
		try {
			total = dealMapper.queryStockCodeNum(stockPage.getStockCode());
			if (0 != total) {
				result = dealMapper.queryStockCode(stockPage);
			}
			return new DefaultPage<StockCodeDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String saveStockCode(String stockCode)
			throws ServiceInvokerException {
		JSONArray ja = JSONArray.fromObject(stockCode);
		List<StockCodeDO> addList = new ArrayList<StockCodeDO>();
		List<StockCodeDO> modifyList = new ArrayList<StockCodeDO>();
		List<StockCodeDO> delList = new ArrayList<StockCodeDO>();
		for (int i = 0; i < ja.size(); i++) {
			StockCodeDO codeDO = new StockCodeDO();
			JSONObject jo = ja.getJSONObject(i);
			String state = jo.getString("_state");
			String code = jo.getString("stockCode");
			if (StringUtils.equals("added", state)) {
				codeDO.setStockCode(code);
				addList.add(codeDO);
			} else if (StringUtils.equals("modified", state)) {
				String id = jo.getString("id");
				codeDO.setStockCode(code);
				codeDO.setId(Long.parseLong(id));
				modifyList.add(codeDO);
			} else if (StringUtils.equals("removed", state)) {
				codeDO.setStockCode(code);
				delList.add(codeDO);
			}

		}
		if (!addList.isEmpty()) {
			batchMapper.batchInsert(DealMapper.class, "saveStockCode", addList);
		}
		if (!modifyList.isEmpty()) {
			batchMapper
					.batchInsert(DealMapper.class, "updateStock", modifyList);
		}
		if (!delList.isEmpty()) {
			batchMapper.batchDelete(DealMapper.class, "updateStockCode",
					delList);
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public String editUserphone(String data) throws ServiceInvokerException {
		JSONArray ja = JSONArray.fromObject(data);
		List<UserInfoDO> list = new ArrayList<UserInfoDO>();
		for (int i = 0; i < ja.size(); i++) {
			JSONObject jo = ja.getJSONObject(i);
			UserInfoDO infoDO = new UserInfoDO();
			infoDO.setNickname(jo.getString("nickname"));
			infoDO.setPhone(jo.getString("phone"));
			list.add(infoDO);
		}
		if (!list.isEmpty()) {
			batchMapper
					.batchInsert(AcountMapper.class, "updateUserphone", list);
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public String setProductStatus(String data) throws ServiceInvokerException {
		JSONObject jo = JSONObject.fromObject(data);
		try {
			dealMapper.updateProductStatus(jo.getString("code"),
					jo.getString("status"));
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

	@Override
	public DefaultPage<GuessProductDO> queryGuessproduct(GuessPage product)
			throws ServiceInvokerException {
		List<GuessProductDO> result = new ArrayList<GuessProductDO>();
		long total = 0;
		try {
			total = dealMapper.queryGuessproductTotal(product.getDate());
			if (0 != total) {
				result = dealMapper.queryGuessproduct(product);
			}
			return new DefaultPage<GuessProductDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}

	@Override
	public String saveGuessProduct(String data) throws ServiceInvokerException {
		try {
			JSONObject jo = JSONObject.fromObject(data);
			dealMapper.saveGuessProduct(guessCode + StringUtil.genRandomNum(4),
					jo.getString("guessName"), jo.getString("purchaseNum"),
					jo.getString("pumpedPercent"));
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

	@Override
	public String updateGuessResult(String type, String code)
			throws ServiceInvokerException {
		try {
			// 更新猜测表
			dealMapper.updateGuessResult(type, code);
			// 更新猜测记录
			dealMapper.updateGuessRecord(type, code);
			// 更新钱包哈哈币
			dealMapper.addHhb(code);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e.getMessage());
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public DefaultPage<GuessHistoryDO> queryGuessDetail(GuessDetailPage bizCode)
			throws ServiceInvokerException {
		List<GuessHistoryDO> result = new ArrayList<GuessHistoryDO>();
		long total = 0;
		try {
			total = dealMapper.queryGuessDetailCount(bizCode.getBidCode());
			if (0 != total) {
				result = dealMapper.queryGuessDetail(bizCode);
			}
			return new DefaultPage<GuessHistoryDO>(total, result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceInvokerException(e);
		}
	}
}
