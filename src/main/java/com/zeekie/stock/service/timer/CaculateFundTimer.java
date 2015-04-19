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

	public void caculate() throws RuntimeException {

		List<TradeDO> result;
		StockCapitalChanges changes = null;
		try {
			result = acount.getSonAccountInfo();
			if (null == result || result.isEmpty()) {
				return;
			}
			if (log.isDebugEnabled()) {
				log.debug("=========start visit homes get profit and loss========");
			}
			for (TradeDO each : result) {
				try {
					changes = new StockCapitalChanges(each.getFundAccount(),
							each.getCombineId());
					changes.callHomes(Fn_stock_current);
					storeCapitalChanges(changes.getDataSet(),
							each.getNickname());
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
			handler.handleJob(Constants.TYPE_JOB_NOTICE_REACH_WARNLINE, "");
		} catch (Exception e1) {
			log.error(e1.getMessage(), e1);
			throw new RuntimeException();
		}

	}

	private void storeCapitalChanges(IDatasets dataSet, String nickname)
			throws Exception {
		IDataset ds = dataSet.getDataset(0);
		String currentCash = ds.getString("current_cash");// 现金资产
		String marketValue = StringUtils.defaultIfBlank(
				ds.getString("market_value"), "0");// 股票市值

		if (StringUtils.isBlank(currentCash)) {
			if (log.isDebugEnabled()) {
				log.debug("visit homes return blank ,value[currentCash],reason homes is stop or homes exception happened");
			}
			return;
		}

		Float money = StringUtil.keepTwoDecimalFloat(Float
				.parseFloat(currentCash));
		Float market = StringUtil.keepTwoDecimalFloat(Float
				.parseFloat(marketValue));

		if (money == 0f && market==0f) {
			if (log.isDebugEnabled()) {
				log.debug("nickname:" + nickname + "当前资产和股票资产为0，不更新盈亏金额");
			}
			return;
		}
		// 1、先更新盈亏金额
		mapper.updateProfitAndLoss(currentCash, marketValue, nickname);
		log.info("======success update profitAndLoss======");

		// 1、1、后更新其他
		mapper.updateOperateMainInfo(currentCash, marketValue, nickname);
		log.info("======success update other infomation======");

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
