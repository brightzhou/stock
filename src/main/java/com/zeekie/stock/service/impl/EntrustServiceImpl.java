package com.zeekie.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.AccountDO;
import com.zeekie.stock.entity.BaseEntrustDO;
import com.zeekie.stock.entity.CombassetDO;
import com.zeekie.stock.entity.CurrentEntrustDO;
import com.zeekie.stock.enums.AmentrustStatusEnum;
import com.zeekie.stock.enums.EntrustDirectionEnum;
import com.zeekie.stock.enums.ExchangeTypeEnum;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.service.homes.StockEntrust;
import com.zeekie.stock.service.homes.StockEntrustQuery;
import com.zeekie.stock.service.homes.StockEntrustWithdraw;
import com.zeekie.stock.service.homes.entity.EntrustEntity;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;

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

	@Autowired
	@Value("${func_am_entrust_qry}")
	private String fn_am_entrust_qry;

	@Autowired
	private BatchMapper batchMapper;

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

			if (!entrust.visitSuccess(fn_entrust)) {
				return entrust.getError();
			}
			List<?> obj = returnObj(entrust.getDataSet(), EntrustEntity.class);
			List<EntrustEntity> entities = new ArrayList<EntrustEntity>();
			if (!obj.isEmpty()) {
				for (Object each : obj) {
					EntrustEntity entity = (EntrustEntity) each;
					entity.setBaseParam(fundAccount, combineId, operateNo,
							nickname);
					entity.setEntrust_direction(Constants.HOMES_ENTRUST_DIRECTION_BUY);
					entity.setEntrust_amount(entrustAmount);
					entity.setEntrust_price(entrustPrice);
					entity.setExchange_type(exchangeType);
					entity.setStock_code(stockCode);
					entities.add(entity);
				}
				batchMapper.batchInsert(DealMapper.class, "insertEntrust",
						entities);
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
			if (null == entrustDO) {
				return Constants.CODE_FAILURE;
			}
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
			BaseEntrustDO entrustDO = deal.queryEntrustInfo(nickname);
			String fundAccount = entrustDO.getFundAccount();
			String combineId = entrustDO.getCombineId();
			StockEntrustQuery entrustQuery = new StockEntrustQuery(fundAccount,
					combineId);
			entrustQuery.callHomes(fn_am_entrust_qry);

			List<?> obj = returnObj(entrustQuery.getDataSet(),
					EntrustQueryEntity.class);
			List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
			EntrustQueryEntity entity = null;
			JSONArray ja = new JSONArray();
			if (!obj.isEmpty()) {
				for (Object each : obj) {
					entity = (EntrustQueryEntity) each;
					entity.setBaseParam(fundAccount, combineId,
							entrustDO.getOperatorNo(), nickname);
					entities.add(entity);
				}
				batchMapper.batchInsert(DealMapper.class, "updateEntrust",
						entities);
				JSONObject jo = new JSONObject();
				jo.put("StockCode", entity.getStock_code());
				jo.put("amentrustStatus", AmentrustStatusEnum.getDesc(entity
						.getAmentrust_status()));
				jo.put("entrustPrice", entity.getEntrust_price());
				jo.put("entrustAmount", entity.getEntrust_amount());
				jo.put("entrustNo", entity.getEntrust_no());
				jo.put("exchangeType",
						ExchangeTypeEnum.getDesc(entity.getExchange_type()));
				jo.put("entrustDirection", EntrustDirectionEnum.getDesc(entity
						.getEntrust_direction()));
				jo.put("businessBalance", entity.getBusiness_balance());
				jo.put("businessAmount", entity.getBusiness_amount());
				jo.put("entrustTime", entity.getEntrust_time());
				jo.put("cancelInfo", entity.getCancel_info());
				ja.add(jo);
			}
			return ja.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}

	@Override
	public String queryEntrustHistory(String nickname, String startDate,
			String endDate) {
		try {
			List<CurrentEntrustDO> currentEntrustDO = deal.queryEntrustHistory(
					nickname, startDate, endDate);
			return JSONArray.fromObject(currentEntrustDO).toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}

}
