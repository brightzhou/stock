package com.zeekie.stock.service.impl;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.AccountDO;
import com.zeekie.stock.entity.BaseEntrustDO;
import com.zeekie.stock.entity.CombassetDO;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.service.homes.StockEntrust;
import com.zeekie.stock.service.homes.StockEntrustWithdraw;
import com.zeekie.stock.service.homes.entity.EntrustEntity;

@Service
public class EntrustServiceImpl extends BaseImpl implements EntrustService {

	static Logger log = LoggerFactory.getLogger(EntrustServiceImpl.class);

	@Autowired
	private DealMapper deal;

	@Autowired
	private AcountMapper account;

	@Autowired
	@Value("${func_am_entrust}")
	private String fn_entrust;

	@Autowired
	@Value("${func_am_entrust_withdraw}")
	private String fn_entrust_withdraw;

	@Override
	public String entrust(String nickname, String stockCode,
			String entrustAmount, String entrustPrice) {
		try {
			AccountDO accountDO = account.getAccount(nickname);
			String combineId = accountDO.getCombineId();
			String operateNo = accountDO.getTradeAcount();
			String fundAccount = accountDO.getFundAccount();
			String exchangeType = StringUtils.startsWith(stockCode, "6") ? Constants.HOMES_EXCHANGE_TYPE_SH
					: Constants.HOMES_EXCHANGE_TYPE_S;
			StockEntrust entrust = new StockEntrust(fundAccount, combineId,
					operateNo, stockCode, entrustAmount, entrustPrice,
					exchangeType);
			entrust.callHomes(fn_entrust);
			Object obj = returnObj(entrust.getDataSet(), EntrustEntity.class);
			if (obj != null) {
				EntrustEntity entity = (EntrustEntity) obj;
				entity.setBaseParam(fundAccount, combineId, operateNo);
				entity.setIndividualParam(exchangeType, entrustPrice,
						entrustAmount, Constants.HOMES_ENTRUST_DIRECTION_BUY,
						stockCode);
				deal.insertEntrust(entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Constants.CODE_FAILURE;
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public String entrustWithdraw(String nickname) {
		try {
			BaseEntrustDO entrustDO = deal.queryEntrustInfo(nickname);

			StockEntrustWithdraw entrustWithdraw = new StockEntrustWithdraw(
					entrustDO.getOperatorNo(), entrustDO.getEntrustNo());

			entrustWithdraw.callHomes(fn_entrust_withdraw);

			if (!entrustWithdraw.visitSuccess(fn_entrust_withdraw)) {
				log.error("用户【" + nickname + "】委托撤销失败，委托编号："
						+ entrustDO.getEntrustNo());
			} else {
				if (log.isDebugEnabled()) {
					log.debug("用户【" + nickname + "】委托撤销成功，委托编号："
							+ entrustDO.getEntrustNo());
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Constants.CODE_FAILURE;
		}
		return Constants.CODE_SUCCESS;
	}

	@Override
	public String queryCombasset(String nickname) {
		try {
			CombassetDO combassetDO = deal.queryCombasset(nickname);
			JSONObject jo = JSONObject.fromObject(combassetDO,
					Constants.jsonConfig);
			return jo.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}

	@Override
	public String queryEntrust(String nickname) {
		try {
			
//			deal.queryEntrust(nickname);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}
}
