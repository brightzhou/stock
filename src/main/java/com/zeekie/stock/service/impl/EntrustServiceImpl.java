package com.zeekie.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.exolab.castor.xml.validators.FloatValidator;
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
import com.zeekie.stock.entity.CurrentOperateUserDO;
import com.zeekie.stock.enums.AmentrustStatusEnum;
import com.zeekie.stock.enums.EntrustDirectionEnum;
import com.zeekie.stock.enums.ExchangeTypeEnum;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.service.homes.StockCombostockQuery;
import com.zeekie.stock.service.homes.StockCommQuery;
import com.zeekie.stock.service.homes.StockEntrust;
import com.zeekie.stock.service.homes.StockEntrustQuery;
import com.zeekie.stock.service.homes.StockEntrustWithdraw;
import com.zeekie.stock.service.homes.entity.EntrustAssetEntity;
import com.zeekie.stock.service.homes.entity.EntrustEntity;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;
import com.zeekie.stock.util.DateUtil;
import com.zeekie.stock.util.StringUtil;

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
	@Value("${func_am_realdeal_qry}")
	private String func_am_realdeal_qry;

	@Autowired
	@Value("${func_am_realdeal_history_qry}")
	private String func_am_realdeal_history_qry;

	@Autowired
	@Value("${func_am_combostock_qry}")
	private String func_am_combostock_qry;

	@Autowired
	@Value("${func_am_entrust_qry}")
	private String func_am_entrust_qry;

	@Autowired
	@Value("${func_am_entrust_history_qry}")
	private String func_am_entrust_history_qry;

	@Autowired
	@Value("${func_am_combasset_qry}")
	private String func_am_combasset_qry;

	@Autowired
	@Value("${func_am_combofund_qry}")
	private String func_am_combofund_qry;

	@Autowired
	private BatchMapper batchMapper;

	@Autowired
	private AcountMapper acount;

	@Override
	public String entrust(String nickname, String stockCode,
			String entrustAmount, String entrustPrice, String entrustDirection,
			String ampriceType) {
		try {
			AccountDO accountDO = account.getAccount(nickname);
			String combineId = accountDO.getCombineId();
			String operateNo = accountDO.getTradeAcount();
			String fundAccount = accountDO.getFundAccount();
			String exchangeType = StringUtils.startsWith(stockCode, "6") ? Constants.HOMES_EXCHANGE_TYPE_SH
					: Constants.HOMES_EXCHANGE_TYPE_S;
			StockEntrust entrust = new StockEntrust(fundAccount, combineId,
					operateNo, stockCode, entrustAmount, entrustPrice,
					exchangeType, entrustDirection, ampriceType);
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
	public String entrustWithdraw(String nickname, String entrustNo) {
		try {
			BaseEntrustDO entrustDO = deal
					.queryEntrustInfo(nickname, entrustNo);
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
	public JSONObject queryCombasset(String nickname) {
		try {
			CombassetDO combassetDO = new CombassetDO();
			CurrentOperateUserDO userDO = account
					.getCurrentOperateUser(nickname);
			if (userDO == null) {
				return null;
			}
			String fundAccount = userDO.getFundAccount();
			String combineId = userDO.getCombieId();

			StockEntrustQuery entrustQuery = new StockEntrustQuery(fundAccount,
					combineId);
			entrustQuery.callHomes(func_am_combasset_qry);
			List<?> obj = returnObj(entrustQuery.getDataSet(),
					EntrustAssetEntity.class);
			if (!obj.isEmpty()) {
				for (Object each : obj) {
					EntrustAssetEntity entity = (EntrustAssetEntity) each;
					if (entity != null && entity.getAsset_total_value() != null)
						combassetDO.setAssetTotalValue(Float.valueOf(entity
								.getAsset_total_value()));
					combassetDO.setCurrentCash(Float.valueOf(entity
							.getCurrent_cash()));
					break;
				}
			} else {
				if (log.isDebugEnabled()) {
					log.debug("账户资金查询，返回数据为空");
				}

			}

			entrustQuery = new StockEntrustQuery(fundAccount, combineId);
			entrustQuery.callHomes(func_am_combofund_qry);
			obj = returnObj(entrustQuery.getDataSet(), EntrustQueryEntity.class);
			EntrustQueryEntity entity = null;

			if (!obj.isEmpty()) {
				for (Object each : obj) {
					entity = (EntrustQueryEntity) each;
					combassetDO.setAssetValue(Float.valueOf(entity
							.getEnable_balance_t1()));
					break;
				}
			} else {
				if (log.isDebugEnabled()) {
					log.debug("账户委托查询，返回数据为空");
				}

			}

			if (null != combassetDO) {
				return JSONObject.fromObject(combassetDO, Constants.jsonConfig);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public JSONArray queryEntrust(String nickname) {
		try {
			CurrentOperateUserDO userDO = account
					.getCurrentOperateUser(nickname);
			if (userDO == null) {
				return null;
			}
			String fundAccount = userDO.getFundAccount();
			String combineId = userDO.getCombieId();
			StockEntrustQuery entrustQuery = new StockEntrustQuery(fundAccount,
					combineId);
			entrustQuery.callHomes(func_am_entrust_qry);

			List<?> obj = returnObj(entrustQuery.getDataSet(),
					EntrustQueryEntity.class);
			List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
			EntrustQueryEntity entity = null;
			JSONArray ja = new JSONArray();
			if (!obj.isEmpty()) {
				for (Object each : obj) {
					entity = (EntrustQueryEntity) each;
					entity.setBaseParam(fundAccount, combineId,
							userDO.getTradeAcount(), nickname);
					entities.add(entity);
					entity = (EntrustQueryEntity) each;
					entity.setBaseParam(fundAccount, combineId,
							userDO.getTradeAcount(), nickname);
					entities.add(entity);
					assembleResult(entity, ja);
				}
				
			} else {
				if (log.isDebugEnabled()) {
					log.debug("执行委托查询，返回数据为空");
				}

			}
			return ja;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public JSONArray tradedQuery(String nickname) {
		try {
			CurrentOperateUserDO userDO = account
					.getCurrentOperateUser(nickname);
			if (userDO == null) {
				return null;
			}
			String fundAccount = userDO.getFundAccount();
			String combineId = userDO.getCombieId();
			StockEntrustQuery entrustQuery = new StockEntrustQuery(fundAccount,
					combineId);
			entrustQuery.callHomes(func_am_realdeal_qry);
			List<?> obj = returnObj(entrustQuery.getDataSet(),
					EntrustQueryEntity.class);
			List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
			EntrustQueryEntity entityObj = null;
			JSONArray ja = new JSONArray();
			if (!obj.isEmpty()) {
				for (Object each : obj) {
					entityObj = (EntrustQueryEntity) each;
					entityObj.setBaseParam(fundAccount, combineId,
							userDO.getTradeAcount(), nickname);
					entities.add(entityObj);
				}
				for (EntrustQueryEntity entity : entities) {
					JSONObject jo = new JSONObject();
					jo.put("stockCode", entity.getStock_code());
					jo.put("amentrustStatus", AmentrustStatusEnum.getDesc(entity
							.getAmentrust_status()));
					jo.put("entrustPrice", entity.getEntrust_price());
					jo.put("entrustAmount", entity.getEntrust_amount());
					jo.put("entrustNo", entity.getEntrust_no());
					jo.put("exchangeType",
							ExchangeTypeEnum.getDesc(entity.getExchange_type()));
					jo.put("entrustDirection",entity
							.getEntrust_direction());
					jo.put("businessBalance", entity.getBusiness_balance());
					jo.put("businessAmount", entity.getBusiness_amount());
					jo.put("entrustTime", entity.getEntrust_time());
					jo.put("cancelInfo", entity.getCancel_info());
					ja.add(jo);
				}
				
			}
			return ja;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public JSONArray queryCombostock(String nickname) {
		JSONArray ja = new JSONArray();
		try {
			CurrentOperateUserDO userDO = account
					.getCurrentOperateUser(nickname);
			if (userDO == null) {
				return null;
			}
			String fundAccount = userDO.getFundAccount();
			String combineId = userDO.getCombieId();
			StockCombostockQuery combostockQuery = new StockCombostockQuery(
					fundAccount, combineId);
			combostockQuery.callHomes(func_am_combostock_qry);
			List<?> obj = returnObj(combostockQuery.getDataSet(),
					EntrustQueryEntity.class);
			List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
			EntrustQueryEntity entity = null;

			if (!obj.isEmpty()) {
				for (Object each : obj) {
					entity = (EntrustQueryEntity) each;
					entity.setBaseParam(fundAccount, combineId,
							userDO.getTradeAcount(), nickname);
					entities.add(entity);
				}
				for (EntrustQueryEntity entrustEntity : entities) {
					JSONObject jo = new JSONObject();
					jo.put("stockCode", entrustEntity.getStock_code());
					jo.put("currentAmount", entrustEntity.getCurrent_amount());
					jo.put("enableAmount", entrustEntity.getEnable_amount());
					jo.put("costBalance", entrustEntity.getCost_balance());
					jo.put("marketValue", entrustEntity.getMarket_value());
					ja.add(jo);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ja;
	}

	private void assembleResult(EntrustQueryEntity entity, JSONArray ja) {
		JSONObject jo = new JSONObject();
		jo.put("stockCode", entity.getStock_code());
		jo.put("amentrustStatus", entity.getAmentrust_status());
		jo.put("entrustPrice", entity.getEntrust_price());
		jo.put("entrustAmount", entity.getEntrust_amount());
		jo.put("entrustNo", entity.getEntrust_no());
		jo.put("exchangeType", entity.getExchange_type());
		jo.put("entrustDirection", entity.getEntrust_direction());
		jo.put("businessBalance", entity.getBusiness_balance());
		jo.put("businessAmount", entity.getBusiness_amount());
		jo.put("entrustTime", entity.getEntrust_time());
		jo.put("cancelInfo", entity.getCancel_info());
		jo.put("entrusteDate", DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
		ja.add(jo);
	}

	@Override
	public JSONArray queryEntrustComm(CurrentEntrustDO entrustDO) {
		List<CurrentEntrustDO> list = null;
		try {
			list = deal.queryEntrustComm(entrustDO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.fromObject(list);
	}

}
