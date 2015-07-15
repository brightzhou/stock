package com.zeekie.stock.service.timer;

import java.util.List;

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
import com.zeekie.stock.entity.TradeDO;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.StockMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.TradeService;
import com.zeekie.stock.service.homes.StockCapitalChanges;
import com.zeekie.stock.service.lhomes.CallhomesService;
import com.zeekie.stock.service.lhomes.entity.AHomesEntity;
import com.zeekie.stock.service.lhomes.entity.HomesCapital;
import com.zeekie.stock.service.syncTask.SyncHandler;
import com.zeekie.stock.util.StringUtil;

@Service
@Transactional
public class CaculateFundTimer {

	static Logger log = LoggerFactory.getLogger(CaculateFundTimer.class);

	@Autowired
	private AcountMapper acount;

	@Autowired
	private StockMapper mapper;

	@Autowired
	private TradeMapper trade;

	@Autowired
	private TradeService service;

	@Autowired
	private SyncHandler handler;

	@Autowired
	@Value("${func_am_stock_current_qry}")
	private String Fn_stock_current;// 769203-股票资产查询

	@Autowired
	@Value("${stock.status.changeIsOpen}")
	private String changeIsOpen;

	public void caculate() throws RuntimeException {

		List<TradeDO> result;
		try {
			result = acount.getSonAccountInfo();
			if (null == result || result.isEmpty()) {
				return;
			}
			if (log.isDebugEnabled()) {
				log.debug("=========start visit homes get profit and loss========");
			}

			if (StringUtils.equals("open", changeIsOpen)) {
				cal2(result);
			} else {
				cal1(result);
			}
			handler.handleJob(Constants.TYPE_JOB_NOTICE_REACH_WARNLINE, "");
		} catch (Exception e1) {
			log.error(e1.getMessage(), e1);
			throw new RuntimeException();
		}

	}

	private void cal2(List<TradeDO> result) throws Exception {
		for (TradeDO trade : result) {
			AHomesEntity entity = new AHomesEntity();
			entity.setClientNo(trade.getOperatorNo());
			entity.setFundAccount(trade.getFundAccount());
			CallhomesService service = CallhomesService.getInstance();
			service.setEntity(entity);
			if (service.call210FunResp()) {
				HomesCapital capital = (HomesCapital) service
						.getResponse(Constants.FN210);
				if (null != capital) {
					storeCapitalChanges(capital.getMarketValue(),
							capital.getCurrValue(), trade.getNickname());
				}
			}
		}

	}

	private void cal1(List<TradeDO> result) throws Exception {
		for (TradeDO each : result) {
			StockCapitalChanges changes = new StockCapitalChanges(
					each.getFundAccount(), each.getCombineId());
			changes.callHomes(Fn_stock_current);
			IDatasets dataSet = changes.getDataSet();
			IDataset ds = dataSet.getDataset(0);
			String currentCash = ds.getString("current_cash");// 现金资产
			String marketValue = StringUtils.defaultIfBlank(
					ds.getString("market_value"), "0");// 股票市值

			if (StringUtils.isBlank(currentCash)) {
				if (log.isDebugEnabled()) {
					log.debug("visit homes return blank ,value[currentCash],reason homes is stop or homes exception happened");
				}
				continue;
			}

			Float market = StringUtil.keepTwoDecimalFloat(Float
					.parseFloat(marketValue));
			Float money = StringUtil.keepTwoDecimalFloat(Float
					.parseFloat(currentCash));

			storeCapitalChanges(market, money, each.getNickname());
		}
	}

	private void storeCapitalChanges(Float market, Float money, String nickname)
			throws Exception {
		if (money == 0f && market == 0f) {
			if (log.isDebugEnabled()) {
				log.debug("nickname:" + nickname + "当前资产和股票资产为0，不更新盈亏金额");
			}
			return;
		}
		// 1、先更新盈亏金额
		mapper.updateProfitAndLoss(money + "", market + "", nickname);

		// 1、1、后更新其他
		mapper.updateOperateMainInfo(money + "", market + "", nickname);

		if (log.isDebugEnabled()) {
			log.debug("success update profit and loss for user:" + nickname);
		}
		// mapper.needNotice(nickname);
		// if(){
		// ApiUtils.sendMsg(Constants.MODEL_TRADE_STOP_FN, param, telephone);
		// }

		// 2、自动划转保证金
		/*
		 * List<AutoAddGuaranteeCashDO> cashDOs = trade
		 * .getNeedAutoAddGuaranteeCashClient(); if (null != cashDOs &&
		 * !cashDOs.isEmpty()) { log.info("开始自动【划转保证金】"); } for
		 * (AutoAddGuaranteeCashDO each : cashDOs) { // StockServiceImpl impl =
		 * new StockServiceImpl(); Map<String, String> map =
		 * service.enterAddGuaranteePage( each.getNickname(),
		 * each.getNeedWantCash());
		 * 
		 * AddCuaranteeForm form = new AddCuaranteeForm(
		 * map.get("currentGuaranteeCash"), map.get("currentOperateLimit"),
		 * map.get("profitAndLossCash"), map.get("nickname"));
		 * form.setAddedGuaranteeCash(each.getNeedWantCash());
		 * form.setFlag("sure"); service.addCuarantee(form);
		 * 
		 * log.info("用户" + each.getNickname() + "自动【划转保证金：" +
		 * each.getNeedWantCash() + "】结束"); }
		 */
	}
}
