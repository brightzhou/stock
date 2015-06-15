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

		// 凌晨计算理财收益
		caculateFinanceIncome();
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
	private void caculateFinanceIncome() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("开始计算理财收益...");
			}
			// 1、更新理财收益记录表
			financeMapper.updateCurrentIncome(StringUtil.getCurrentYearDays());

			List<FinanceIncomeDO> result = financeMapper.queryFinanceIncome();

			// 2、更新钱包余额，计算理财收益
			batchMapper.batchInsert(FinanceMapper.class,
					"updateFinanceIncomeBatch", result);

			// 3、记录流水
			List<FundFlowDO> fee = new ArrayList<FundFlowDO>();
			List<FundFlowDO> feeOfCapital = new ArrayList<FundFlowDO>();
			for (FinanceIncomeDO income : result) {
				String nickname = income.getNickname();
				String incomes = income.getIncome() + ""; 
				String ticket = income.getTicket();
				FundFlowDO flowDO = new FundFlowDO(nickname,
						FundFlowEnum.FINANCE_INCOME.getType(), incomes,
						MessageFormat.format(FundFlowEnum.FINANCE_INCOME.getDesc(),ticket));
				fee.add(flowDO);

				if (log.isDebugEnabled()) {
					log.debug("用户【" + nickname + "】获取理财收益【" + incomes + "】");
				}

				// 计算是否理财产品到期，如果到期需要归还本金
				String money = ""+income.getFinanceLimit();
				if (StringUtils.equals("0", income.getNum())) {
					FundFlowDO flow = new FundFlowDO(nickname,
							FundFlowEnum.FINANCE_CAPATAL.getType(), money,
							MessageFormat.format(FundFlowEnum.FINANCE_CAPATAL.getDesc(),ticket));
					feeOfCapital.add(flow);

					if (log.isDebugEnabled()) {
						log.debug("用户【" + nickname + "】有理财到期，归还本金" + money);
					}
				}

			}
			batchMapper.batchInsert(TradeMapper.class, "addFlowFundBatch", fee);

			if (!feeOfCapital.isEmpty()) {
				// 更新本金
				batchMapper.batchInsert(TradeMapper.class,
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
