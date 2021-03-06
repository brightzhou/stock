package com.zeekie.stock.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.zeekie.stock.entity.CUrrentGuessProductDO;
import com.zeekie.stock.entity.CombassetDO;
import com.zeekie.stock.entity.CurrentEntrustDO;
import com.zeekie.stock.entity.CurrentOperateUserDO;
import com.zeekie.stock.entity.GuessHistoryDO;
import com.zeekie.stock.entity.ProductDO;
import com.zeekie.stock.enums.AmentrustStatusEnum;
import com.zeekie.stock.enums.ExchangeTypeEnum;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.service.homes.StockCombostockQuery;
import com.zeekie.stock.service.homes.StockEntrust;
import com.zeekie.stock.service.homes.StockEntrustQuery;
import com.zeekie.stock.service.homes.StockEntrustWithdraw;
import com.zeekie.stock.service.homes.entity.EntrustAssetEntity;
import com.zeekie.stock.service.homes.entity.EntrustEntity;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;
import com.zeekie.stock.service.lhomes.CallhomesService;
import com.zeekie.stock.service.lhomes.entity.AHomesEntity;
import com.zeekie.stock.service.lhomes.entity.Homes103Resp;
import com.zeekie.stock.service.lhomes.entity.Homes104Resp;
import com.zeekie.stock.service.lhomes.entity.HomesCapital;
import com.zeekie.stock.service.lhomes.entity.HomesEntrust;
import com.zeekie.stock.service.lhomes.entity.HomesEntrustWithdraw;
import com.zeekie.stock.service.lhomes.entity.HomesQueryEntrust;
import com.zeekie.stock.service.lhomes.entity.HomesResponse;
import com.zeekie.stock.util.DateUtil;

@Service
public class EntrustServiceImpl extends BaseImpl implements EntrustService {

	static Logger log = LoggerFactory.getLogger(EntrustServiceImpl.class);

	@Autowired
	private DealMapper deal;

	@Autowired
	private AcountMapper account;

	@Autowired
	private TradeMapper trade;

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
	@Value("${stock.guess.start}")
	private String guessStartTime;

	@Autowired
	@Value("${stock.guess.end}")
	private String guessEndTime;

	@Autowired
	@Value("${stock.status.changeIsOpen}")
	private String changeIsOpen;

	@Override
	public String entrust(String nickname, String stockCode, String entrustAmount, String entrustPrice,
			String entrustDirection) {
		try {
			AccountDO accountDO = account.getAccount(nickname);
			String combineId = accountDO.getCombineId();
			String operateNo = accountDO.getTradeAcount();
			String fundAccount = accountDO.getFundAccount();
			String exchangeType = StringUtils.startsWith(stockCode, "6") ? Constants.HOMES_EXCHANGE_TYPE_SH
					: Constants.HOMES_EXCHANGE_TYPE_S;
			List<EntrustEntity> entities = new ArrayList<EntrustEntity>();

			if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(operateNo, "6")) {
				HomesEntrust entrust = new HomesEntrust(operateNo, fundAccount, exchangeType, stockCode,
						entrustDirection, entrustAmount, entrustPrice);
				CallhomesService service = new CallhomesService(entrust);
				if (service.call201Fun()) {
					HomesResponse resp = service.getResponse(Constants.FN201);
					EntrustEntity entity = new EntrustEntity();
					setParams(nickname, stockCode, entrustAmount, entrustPrice, combineId, operateNo, fundAccount,
							exchangeType, entities, entity);
					entity.setEntrust_no(resp.getEntrustNo() + "");
					entity.setBatch_no(resp.getBatchNo() + "");
				}
			} else {
				StockEntrust entrust = new StockEntrust(fundAccount, combineId, operateNo, stockCode, entrustAmount,
						entrustPrice, exchangeType, entrustDirection, "");
				entrust.callHomes(fn_entrust);

				if (!entrust.visitSuccess(fn_entrust)) {
					return entrust.getError();
				}
				List<?> obj = returnObj(entrust.getDataSet(), EntrustEntity.class);
				if (!obj.isEmpty()) {
					for (Object each : obj) {
						EntrustEntity entity = (EntrustEntity) each;
						setParams(nickname, stockCode, entrustAmount, entrustPrice, combineId, operateNo, fundAccount,
								exchangeType, entities, entity);
					}
				}
			}
			if (!entities.isEmpty()) {
				batchMapper.batchInsert(DealMapper.class, "insertEntrust", entities);
			}
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Constants.CODE_FAILURE;
		}
	}

	private void setParams(String nickname, String stockCode, String entrustAmount, String entrustPrice,
			String combineId, String operateNo, String fundAccount, String exchangeType, List<EntrustEntity> entities,
			EntrustEntity entity) {
		entity.setBaseParam(fundAccount, combineId, operateNo, nickname);
		entity.setEntrust_direction(Constants.HOMES_ENTRUST_DIRECTION_BUY);
		entity.setEntrust_amount(entrustAmount);
		entity.setEntrust_price(entrustPrice);
		entity.setExchange_type(exchangeType);
		entity.setStock_code(stockCode);
		entities.add(entity);
	}

	@Override
	public String entrustWithdraw(String nickname, String entrustNo) {
		try {
			BaseEntrustDO entrustDO = deal.queryEntrustInfo(nickname, entrustNo);
			if (null == entrustDO) {
				return Constants.CODE_FAILURE;
			}
			if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(entrustDO.getOperatorNo(), "6")) {
				HomesEntrustWithdraw req = new HomesEntrustWithdraw(entrustDO.getFundAccount(),
						entrustDO.getOperatorNo(), entrustNo);
				CallhomesService service = new CallhomesService(req);
				if (service.call202Fun()) {
					if (log.isDebugEnabled()) {
						log.debug("用户【" + nickname + "】委托撤销成功，委托编号：" + entrustNo);
					}
					return Constants.CODE_SUCCESS;
				}
			} else {

				StockEntrustWithdraw entrustWithdraw = new StockEntrustWithdraw(entrustDO.getOperatorNo(),
						entrustDO.getEntrustNo());
				entrustWithdraw.callHomes(fn_entrust_withdraw);

				if (!entrustWithdraw.visitSuccess(fn_entrust_withdraw)) {
					log.error("用户【" + nickname + "】委托撤销失败，委托编号：" + entrustDO.getEntrustNo());
				} else {
					if (log.isDebugEnabled()) {
						log.debug("用户【" + nickname + "】委托撤销成功，委托编号：" + entrustNo);
					}
					return Constants.CODE_SUCCESS;
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

	@Override
	public JSONObject queryCombasset(String nickname) {
		try {
			CombassetDO combassetDO = new CombassetDO();
			CurrentOperateUserDO userDO = account.getCurrentOperateUser(nickname);
			if (userDO == null) {
				return JSONObject.fromObject(combassetDO, Constants.jsonConfig);
			}
			String fundAccount = userDO.getFundAccount();
			String combineId = userDO.getCombieId();

			if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(userDO.getTradeAcount(), "6")) {
				AHomesEntity param = new AHomesEntity(fundAccount, combineId);
				CallhomesService service = new CallhomesService(param);
				if (service.call210FunResp()) {
					HomesCapital resp = (HomesCapital) service.getResponse(Constants.FN210);
					Float fetFund = resp.getFetfund();
					Float userFund = resp.getUserfund();
					// Float userMarket = resp.getUsermarket();
					combassetDO.setAssetTotalValue(resp.getAssetValue());
					combassetDO.setAssetValue(userFund);
					combassetDO.setCurrentCash(fetFund);
				}
			} else {

				StockEntrustQuery entrustQuery = new StockEntrustQuery(fundAccount, combineId);
				entrustQuery.callHomes(func_am_combasset_qry);
				List<?> obj = returnObj(entrustQuery.getDataSet(), EntrustAssetEntity.class);
				if (!obj.isEmpty()) {
					for (Object each : obj) {
						EntrustAssetEntity entity = (EntrustAssetEntity) each;
						if (entity != null && entity.getAsset_total_value() != null)
							combassetDO.setAssetTotalValue(Float.valueOf(entity.getAsset_total_value()));
						combassetDO.setCurrentCash(Float.valueOf(entity.getCurrent_cash()));
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
						combassetDO.setAssetValue(Float.valueOf(entity.getEnable_balance_t1()));
						break;
					}
				} else {
					if (log.isDebugEnabled()) {
						log.debug("账户委托查询，返回数据为空");
					}
				}
			}
			if (null != combassetDO) {
				return JSONObject.fromObject(combassetDO, Constants.jsonConfig);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new JSONObject();
	}

	@Override
	public JSONArray queryEntrust(String nickname) {
		JSONArray ja = new JSONArray();
		try {
			CurrentOperateUserDO userDO = account.getCurrentOperateUser(nickname);
			if (userDO == null) {
				return ja;
			}
			String fundAccount = userDO.getFundAccount();
			String combineId = userDO.getCombieId();

			if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(userDO.getTradeAcount(), "6")) {
				HomesQueryEntrust queryEntrust = new HomesQueryEntrust(fundAccount, combineId);
				CallhomesService service = new CallhomesService(queryEntrust);
				if (service.call104Fun()) {
					Homes104Resp list = (Homes104Resp) service.getResponse(Constants.FN104);
					List<EntrustQueryEntity> entities = list.getList();
					for (EntrustQueryEntity entity : entities) {
						entity.setBaseParam(fundAccount, combineId, userDO.getTradeAcount(), nickname);
						assembleResult(entity, ja);
					}

				}
			} else {
				StockEntrustQuery entrustQuery = new StockEntrustQuery(fundAccount, combineId);
				entrustQuery.callHomes(func_am_entrust_qry);
				List<?> obj = returnObj(entrustQuery.getDataSet(), EntrustQueryEntity.class);
				List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
				EntrustQueryEntity entity = null;
				if (!obj.isEmpty()) {
					for (Object each : obj) {
						entity = (EntrustQueryEntity) each;
						entity.setBaseParam(fundAccount, combineId, userDO.getTradeAcount(), nickname);
						entities.add(entity);
						assembleResult(entity, ja);
					}

				} else {
					if (log.isDebugEnabled()) {
						log.debug("执行委托查询，返回数据为空");
					}
				}
			}
			return ja;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ja;
	}

	public JSONArray tradedQuery(String nickname) {
		JSONArray ja = new JSONArray();
		try {
			CurrentOperateUserDO userDO = account.getCurrentOperateUser(nickname);
			if (userDO == null) {
				return ja;
			}
			String fundAccount = userDO.getFundAccount();
			String combineId = userDO.getCombieId();
			if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(userDO.getTradeAcount(), "6")) {
				HomesQueryEntrust queryEntrust = new HomesQueryEntrust(fundAccount, combineId);
				CallhomesService service = new CallhomesService(queryEntrust);
				if (service.call105Fun()) {
					Homes104Resp list = (Homes104Resp) service.getResponse(Constants.FN105);
					List<EntrustQueryEntity> entities = list.getList();
					for (EntrustQueryEntity entity : entities) {
						entity.setBaseParam(fundAccount, combineId, userDO.getTradeAcount(), nickname);
						JSONObject jo = new JSONObject();
						jo.put("stockCode", entity.getStock_code());
						jo.put("amentrustStatus", entity.getAmentrust_status());
						jo.put("entrustNo", entity.getEntrust_no());
						jo.put("exchangeType", entity.getExchange_type());
						jo.put("entrustDirection", entity.getEntrust_direction());
						jo.put("businessBalance", entity.getBusiness_balance());
						jo.put("businessAmount", entity.getBusiness_amount());
						String bussi = StringUtils.defaultIfBlank(entity.getBusiness_time(), "130201");
						if (bussi.length() < 6) {
							bussi = "0" + bussi;
						}
						jo.put("businessTime", bussi);
						jo.put("entrustTime", StringUtils.defaultIfBlank(entity.getBusiness_time(), "130101"));
						jo.put("cancelInfo", entity.getCancel_info());
						jo.put("entrustDate", entity.getEntrust_day());
						ja.add(jo);
					}

				}
			} else {
				StockEntrustQuery entrustQuery = new StockEntrustQuery(fundAccount, combineId);
				entrustQuery.callHomes(func_am_realdeal_qry);
				List<?> obj = returnObj(entrustQuery.getDataSet(), EntrustQueryEntity.class);
				List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
				EntrustQueryEntity entityObj = null;

				if (!obj.isEmpty()) {
					for (Object each : obj) {
						entityObj = (EntrustQueryEntity) each;
						entityObj.setBaseParam(fundAccount, combineId, userDO.getTradeAcount(), nickname);
						entities.add(entityObj);
					}
					for (EntrustQueryEntity entity : entities) {
						JSONObject jo = new JSONObject();
						jo.put("stockCode", entity.getStock_code());
						jo.put("amentrustStatus", AmentrustStatusEnum.getDesc(entity.getAmentrust_status()));
						jo.put("entrustPrice", entity.getEntrust_price());
						jo.put("entrustAmount", entity.getEntrust_amount());
						jo.put("entrustNo", entity.getEntrust_no());
						jo.put("exchangeType", ExchangeTypeEnum.getDesc(entity.getExchange_type()));
						jo.put("entrustDirection", entity.getEntrust_direction());
						jo.put("businessBalance", entity.getBusiness_balance());
						jo.put("businessAmount", entity.getBusiness_amount());
						jo.put("businessTime", "100101");
						jo.put("entrustTime", entity.getEntrust_time());
						jo.put("cancelInfo", entity.getCancel_info());
						ja.add(jo);
					}
				}
			}
			return ja;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ja;
	}

	@Override
	public JSONArray queryCombostock(String nickname) {
		JSONArray ja = new JSONArray();
		try {
			CurrentOperateUserDO userDO = account.getCurrentOperateUser(nickname);
			if (userDO == null) {
				return null;
			}
			String fundAccount = userDO.getFundAccount();
			String combineId = userDO.getCombieId();

			if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(userDO.getTradeAcount(), "6")) {
				AHomesEntity aentity = new AHomesEntity(fundAccount, combineId);
				CallhomesService service = new CallhomesService(aentity);
				if (service.call103Fun()) {
					Homes103Resp resp = (Homes103Resp) service.getResponse(Constants.FN103);
					if (resp != null) {
						return JSONArray.fromObject(resp.getList());
					} else {
						return ja;
					}
				}
			} else {
				StockCombostockQuery combostockQuery = new StockCombostockQuery(fundAccount, combineId);
				combostockQuery.callHomes(func_am_combostock_qry);
				List<?> obj = returnObj(combostockQuery.getDataSet(), EntrustQueryEntity.class);
				List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
				EntrustQueryEntity entity = null;

				if (!obj.isEmpty()) {
					for (Object each : obj) {
						entity = (EntrustQueryEntity) each;
						entity.setBaseParam(fundAccount, combineId, userDO.getTradeAcount(), nickname);
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
		String bussi = StringUtils.defaultIfBlank(entity.getBusiness_time(), "130201");
		if (bussi.length() < 6) {
			bussi = "0" + bussi;
		}
		jo.put("businessTime", bussi);
		String time = StringUtils.defaultIfBlank(entity.getEntrust_time(), "130101");
		if (time.length() < 6) {
			time = "0" + time;
		}
		jo.put("entrustTime", time);
		jo.put("cancelInfo", entity.getCancel_info());
		jo.put("entrustDate", StringUtils.defaultIfBlank(entity.getEntrust_day(), "2010-01-01 00:00:00"));
		jo.put("entrusteDate", StringUtils.defaultIfBlank(entity.getEntrust_day(), "2010-01-01 00:00:00"));
		jo.put("reportTime", entity.getReport_time());
		ja.add(jo);
	}

	@Override
	public JSONArray queryEntrustComm(CurrentEntrustDO entrustDO, String flag) {
		JSONArray ja = new JSONArray();
		try {
			String nickname = entrustDO.getNickName();
			CurrentOperateUserDO userDO = account.getCurrentOperateUser(nickname);
			if (StringUtils.equals("open", changeIsOpen) && !StringUtils.startsWith(userDO.getTradeAcount(), "6")) {
				String investNO = userDO.getFundAccount();
				String clientNo = userDO.getTradeAcount();
				HomesQueryEntrust entrust = new HomesQueryEntrust(investNO, clientNo);
				entrust.setStartDate(entrustDO.getStartDate());
				entrust.setEndDate(entrustDO.getEndDate());
				entrust.setCxRowcount(50);
				CallhomesService service = new CallhomesService(entrust);
				HomesResponse response = null;
				if (StringUtils.equals("entrust", flag)) {
					if (service.call104Fun()) {
						response = service.getResponse(Constants.FN104);
					}
				} else {
					if (service.call105Fun()) {
						response = service.getResponse(Constants.FN105);
					}
				}
				if (null != response) {
					Homes104Resp resp = (Homes104Resp) response;
					List<EntrustQueryEntity> result = resp.getList();
					for (EntrustQueryEntity item : result) {
						item.setBaseParam(investNO, clientNo, clientNo, nickname);
						assembleResult(item, ja);
					}
					return ja;
				}
			} else {
				return JSONArray.fromObject(deal.queryEntrustComm(entrustDO));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ja;
	}

	@Override
	public JSONArray getProduct(String nickname) {
		JSONArray ja = new JSONArray();
		try {
			ProductDO product = deal.queryProduct(nickname);
			if (null != product) {
				String code = product.getCode();
				if (StringUtils.isNotBlank(code)) {
					String[] codes = code.split(",");
					for (String item : codes) {
						JSONObject jo = new JSONObject();
						if (item.equals("10")) {
							jo.put("financeIncome", product.getFinanceIncome() + "");
							jo.put("code", "10");
						} else if (item.equals("11")) {
							jo.put("stockIncome", product.getStockIncome() + "");
							jo.put("code", "11");
						} else if (item.equals("12")) {
							jo.put("guessIncome", product.getGuessIncome() + "");
							jo.put("code", "12");
						} else if (item.equals("13")) {
							jo.put("futures", "");
							jo.put("code", "13");
						}
						ja.add(jo);
					}
				}

				JSONObject jo = new JSONObject();
				jo.put("flag", product.getFlag());
				jo.put("hasOperation", product.getHasOperation());
				ja.add(jo);
				return ja;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ja;
	}

	@Override
	public String purchaseHhb(String nickname, String num, String cash) {
		try {
			if (!StringUtils.equals(Constants.CODE_SUCCESS, account.queryCash(nickname, num))) {
				return Constants.CODE_GUESS_FUND_NOT_ENOUGH;
			}
			deal.updateHhb(nickname, num);

			account.insertHhbFlow(nickname, num, cash, "1");

			trade.recordFundflow(nickname, "190", "-" + cash, "购买哈哈币");
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

	@Override
	public String updateGuess(String nickname, String num, String type, String bidCode) throws RuntimeException {
		try {
			// 判断哈哈币是否充足
			if (!StringUtils.equals(Constants.CODE_SUCCESS, deal.queryHhb(nickname, num))) {
				return Constants.CODE_GUESS_FUND_NOT_ENOUGH;
			}
			// 扣除哈哈币
			deal.modifyhhb(nickname, num);
			// 下注
			deal.updateGuess(nickname, num, type, bidCode);

			account.insertHhbFlowForGuess(nickname, num, "4");
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public JSONObject getGuessProduct(String nickname) {
		try {
			CUrrentGuessProductDO productDO = deal.queryCurrentGuessProduct(nickname);
			if (null != productDO) {
				// String rightNow = DateUtil.dateToStr(new Date(),
				// DateUtil.FORMAT_YYYY_MM_DD);
				// if (StringUtils.isNotBlank(trade.selectFeeDay(rightNow))) {
				// // 判断是否在购买时间范围内 15:30到第二天早上9:00
				// if (DateUtil.compareDate(guessStartTime, guessEndTime,
				// productDO.getPublishTime())) {
				// productDO.setIsInTime("Y");
				// }
				// } else {
				// if (log.isDebugEnabled()) {
				// log.debug("今天[" + rightNow + "]是非交易日，不用设置时间门槛");
				// }
				// }
				return JSONObject.fromObject(productDO, Constants.jsonConfig);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new JSONObject();
	}

	@Override
	public String sell(String nickname, String num, String cash) {
		// 判断哈哈币是否充足
		try {
			if (!StringUtils.equals(Constants.CODE_SUCCESS, deal.queryHhb(nickname, num))) {
				return Constants.CODE_GUESS_FUND_NOT_ENOUGH;
			}
			deal.modifyhhbAndBalance(nickname, num, cash);

			account.insertHhbFlow(nickname, num, cash, "2");

			trade.recordFundflow(nickname, "200", cash, "赎回哈哈币");
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

	@Override
	public JSONArray getHistoryGuess(String userId, String offset) {
		JSONArray jo = new JSONArray();
		try {
			List<GuessHistoryDO> flow = deal.getHistoryGuess(userId, offset);
			if (null != flow) {
				return jo.fromObject(flow);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return jo;
	}

	@Override
	public String sign(String userId) {
		try {
			if (StringUtils.equals(Constants.CODE_SUCCESS, deal.querySignFlag(userId))) {
				if (log.isDebugEnabled()) {
					log.debug("[Zeekie] user=[{}] 已经签过了，不允许重复签到", userId);
				}
				return Constants.CODE_FAILURE;
			}
			deal.updateSignTable(userId);

			deal.updateHhbbyId(userId);

			account.insertHhbFlowByUserid(userId, "3");
			return Constants.CODE_SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

	@Override
	public String querySignFlag(String userId) {

		try {
			if (StringUtils.equals(Constants.CODE_SUCCESS, deal.querySignFlag(userId))) {
				return Constants.CODE_SUCCESS;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Constants.CODE_FAILURE;
	}

}
