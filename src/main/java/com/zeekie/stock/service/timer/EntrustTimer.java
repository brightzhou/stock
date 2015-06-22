package com.zeekie.stock.service.timer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sitong.thinker.common.exception.ServiceInvokerException;
import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.entity.CurrentEntrustDO;
import com.zeekie.stock.entity.FinanceIncomeDO;
import com.zeekie.stock.entity.FundFlowDO;
import com.zeekie.stock.enums.FundFlowEnum;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.respository.FinanceMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.homes.StockCommQuery;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;
import com.zeekie.stock.service.impl.BaseImpl;
import com.zeekie.stock.util.DateUtils;
import com.zeekie.stock.util.StringUtil;

@Service
@Transactional
public class EntrustTimer extends BaseImpl {

	private static final Logger log = LoggerFactory
			.getLogger(DeductManageFeeTimer.class);

	@Autowired
	@Value("${func_am_entrust_history_qry}")
	private String func_am_entrust_history_qry;

	@Autowired
	private DealMapper deal;

	@Autowired
	private BatchMapper batchMapper;

	@Autowired
	private FinanceMapper financeMapper;

	public String getFunc_am_entrust_history_qry() {
		return func_am_entrust_history_qry;
	}

	public void setFunc_am_entrust_history_qry(String funcAmEntrustHistoryQry) {
		func_am_entrust_history_qry = funcAmEntrustHistoryQry;
	}

	public void historyEntrustQuery() {
		getEntrusts();
	}

	private void getEntrusts() {
		boolean flag = false;
		int count = 5;
		do {
			try {
				if (log.isDebugEnabled()) {
					log.debug("跑线程开始获取历史委托信息 COUNT:" + count);
				}
				String day = DateUtils.formatDate(DateUtils.getInterDay(-1),
						"yyyy-MM-dd");
				StockCommQuery stockCommQuery = new StockCommQuery("2",
						DateUtils.formatDate(DateUtils.getInterDay(-1),
								"yyyyMMdd"));
				stockCommQuery.callHomes(func_am_entrust_history_qry);
				List<?> obj = returnObj(stockCommQuery.getDataSet(),
						EntrustQueryEntity.class);
				EntrustQueryEntity entrustEntity = null;
				String time = null;
				if (!obj.isEmpty()) {
					StringBuffer dayTime = null;
					for (Object each : obj) {
						dayTime = new StringBuffer();
						entrustEntity = (EntrustQueryEntity) each;
						entrustEntity.setCombineId(entrustEntity
								.getCombine_id());
						entrustEntity.setFundAccount(entrustEntity
								.getFund_account());
						entrustEntity.setNickname("");
						entrustEntity.setOperateNo("");
						time = entrustEntity.getEntrust_time();
						System.out.println(time);

						if (StringUtil.isNotBlank(time)) {
							int number = Integer.parseInt(time.substring(0, 2));
							if (number >= 10 && number <= 23) {
								dayTime.append(day).append(" ")
										.append(time.substring(0, 2))
										.append(":")
										.append(time.substring(2, 4))
										.append(":")
										.append(time.substring(4, 6));
							} else {
								dayTime.append(day).append(" 0")
										.append(time.substring(0, 1))
										.append(":")
										.append(time.substring(1, 3))
										.append(":")
										.append(time.substring(3, 5));

							}
							System.out.println(dayTime.toString());

						}
						List<CurrentEntrustDO> entrustDO = deal
								.queryTradeInfoByCombineId(
										entrustEntity.getCombineId(),
										dayTime.toString());
						if (entrustDO != null && entrustDO.size() > 0) {
							entrustEntity.setNickname(entrustDO.get(0)
									.getNickName());
							entrustEntity.setOperateNo(entrustDO.get(0)
									.getOperatorNo());
							entrustEntity.setEntrust_day(dayTime.toString());
							deal.updateEntrust(entrustEntity);
						}
					}
					flag = false;
					if (log.isDebugEnabled()) {
						log.debug("获取历史委托信息");
					}
				} else {
					if (log.isDebugEnabled()) {
						log.debug("获取历史委托信息为空");
					}
					flag = true;
					count--;
					Thread.sleep(1000 * 60 * 5);
				}
			} catch (Exception e) {
				flag = true;
				count--;
				log.error(e.getMessage(), e);
				try {
					Thread.sleep(1000 * 60 * 5);
				} catch (InterruptedException e1) {

				}
			}
			if (log.isDebugEnabled()) {
				log.debug("跑线程结束获取历史委托信息 COUNT" + count);
			}
		} while (flag && count > 0);
	}

	// 计算理财收益
	public void caculateFinanceIncome() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("开始计算理财收益...");
			}
			// financeMapper.updateCurrentIncome(StringUtil.getCurrentYearDays());

			// 1、查询所有需要计算理财收益的名单
			List<FinanceIncomeDO> result = financeMapper.queryFinanceIncome();

			// 理财收益
			List<FinanceIncomeDO> caculateIncome = new ArrayList<FinanceIncomeDO>();

			// 2、理财收益流水
			List<FundFlowDO> caculateIncomeFlow = new ArrayList<FundFlowDO>();

			// 本金和流水
			List<FundFlowDO> feeOfCapital = new ArrayList<FundFlowDO>();
			for (FinanceIncomeDO income : result) {
				String nickname = income.getNickname();
				String ticket = income.getTicket();
				String money = "" + income.getFinanceLimit();
				String leaveDays = income.getNum();
				// 不是最后一天，最後一天不計算
				if (!StringUtils.equals("0", leaveDays)) {
					Float currentIncomeOfDay = income.getIncome();
					FundFlowDO flowDO = new FundFlowDO(nickname,
							FundFlowEnum.FINANCE_INCOME.getType(),
							currentIncomeOfDay + "", MessageFormat.format(
									FundFlowEnum.FINANCE_INCOME.getDesc(),
									ticket));
					caculateIncomeFlow.add(flowDO);

					caculateIncome.add(income);
					if (log.isDebugEnabled()) {
						log.debug("用户【" + nickname + "】获取理财[" + ticket + "]收益【"
								+ currentIncomeOfDay + "】");
					}
					// 如果是到期前一天需要归还本金
					if (StringUtils.equals("1", leaveDays)) {
						FundFlowDO flow = new FundFlowDO(nickname,
								FundFlowEnum.FINANCE_CAPATAL.getType(), money,
								MessageFormat.format(
										FundFlowEnum.FINANCE_CAPATAL.getDesc(),
										ticket));
						feeOfCapital.add(flow);

						// 到期的不计算收益只归还本金,这里踢除掉
						if (log.isDebugEnabled()) {
							log.debug("用户【" + nickname + "】的理财["
									+ income.getTicket() + "]到期，归还本金" + money);
						}
					}
				}

			}

			// 3、更新钱包余额，计算理财收益
			if (!caculateIncome.isEmpty()) {
				batchMapper.batchInsert(FinanceMapper.class,
						"updateFinanceIncomeBatch", caculateIncome);

				// 4、理财收益流水
				batchMapper.batchInsert(TradeMapper.class, "addFlowFundBatch",
						caculateIncomeFlow);
			}

			// 5、归还本金
			if (!feeOfCapital.isEmpty()) {
				// 更新本金
				batchMapper.batchInsert(FinanceMapper.class,
						"updateCapitalBatch", feeOfCapital);

				// 记录流水
				batchMapper.batchInsert(TradeMapper.class, "addFlowFundBatch",
						feeOfCapital);
			}
			if (log.isDebugEnabled()) {
				log.debug("计算理财收益完成!!!");
			}
		} catch (ServiceInvokerException e) {
			log.error(e.getMessage(), e);
		}
	}

}
