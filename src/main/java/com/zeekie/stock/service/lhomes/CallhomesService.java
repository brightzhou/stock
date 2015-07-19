package com.zeekie.stock.service.lhomes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hczq.hz.intf.AmResultList;
import com.hczq.hz.intf.AmServiceResult;
import com.hczq.hz.intf.Fun103Requst;
import com.hczq.hz.intf.Fun103Response;
import com.hczq.hz.intf.Fun104Requst;
import com.hczq.hz.intf.Fun104Response;
import com.hczq.hz.intf.Fun105Requst;
import com.hczq.hz.intf.Fun105Response;
import com.hczq.hz.intf.Fun201Requst;
import com.hczq.hz.intf.Fun201Response;
import com.hczq.hz.intf.Fun202Requst;
import com.hczq.hz.intf.Fun210Requst;
import com.hczq.hz.intf.Fun210Response;
import com.hczq.hz.intf.Fun311Requst;
import com.hczq.hz.intf.Fun501Requst;
import com.zeekie.stock.Constants;
import com.zeekie.stock.enums.ExchangeTypeEnum;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;
import com.zeekie.stock.service.lhomes.entity.AHomesEntity;
import com.zeekie.stock.service.lhomes.entity.EntrustMoveFund;
import com.zeekie.stock.service.lhomes.entity.Homes103Resp;
import com.zeekie.stock.service.lhomes.entity.Homes104Resp;
import com.zeekie.stock.service.lhomes.entity.HomesCapital;
import com.zeekie.stock.service.lhomes.entity.HomesEntrust;
import com.zeekie.stock.service.lhomes.entity.HomesEntrustWithdraw;
import com.zeekie.stock.service.lhomes.entity.HomesPwd;
import com.zeekie.stock.service.lhomes.entity.HomesQueryEntrust;
import com.zeekie.stock.service.lhomes.entity.HomesResponse;
import com.zeekie.stock.service.lhomes.entity.HomsEntity103;
import com.zeekie.stock.util.StringUtil;

public class CallhomesService {

	private static final Logger log = LoggerFactory
			.getLogger(CallhomesService.class);

	private AHomesEntity entity;

	private AmServiceResult response;

	public CallhomesService(AHomesEntity entity) {
		this.entity = entity;
	}

	/**
	 * 委托
	 * 
	 * @return Fun201Response
	 */
	public boolean call201Fun() {
		HomesEntrust entrustEntity = (HomesEntrust) entity;
		Fun201Requst requst = new Fun201Requst();
		requst.setClientNo(entrustEntity.getClientNo());
		requst.setInvestAccount(StringUtil.StringToInteger(entrustEntity
				.getInvestAccount()));
		requst.setExchangeType(entrustEntity.getExchangeType());
		requst.setEntrustBs(entrustEntity.getEntrustDirection());
		requst.setStockCode(entrustEntity.getStockCode());
		requst.setEntrustType("0");
		requst.setEntrustProp("0");
		requst.setEntrustPrice(StringUtil.parseBigDecimal(
				entrustEntity.getEntrustPrice(), 2));
		requst.setEntrustAmount(StringUtil.parseBigDecimal(
				entrustEntity.getEntrustAmount(), 0));
		response = Constants.services.fun201(requst);
		return isTrue(response);
	}

	/**
	 * 查询委托
	 * 
	 * @return Fun201Response
	 */
	public boolean call104Fun() {
		HomesQueryEntrust param = (HomesQueryEntrust) entity;
		Fun104Requst requst = new Fun104Requst();
		requst.setClientNo(param.getClientNo());
		requst.setInvestAccount(StringUtil.StringToInteger(param
				.getInvestAccount()));
		requst.setStartDate(param.getStartDate());
		requst.setEndDate(param.getEndDate());
		requst.setCxRowcount(param.getCxRowcount());
		requst.setPageNo(param.getPageNo());
		response = Constants.services.fun104(requst);
		return isTrue(response);
	}

	/**
	 * 查询成交
	 * 
	 * @return Fun201Response
	 */
	public boolean call105Fun() {
		HomesQueryEntrust param = (HomesQueryEntrust) entity;
		Fun105Requst requst = new Fun105Requst();
		requst.setClientNo(param.getClientNo());
		requst.setInvestAccount(StringUtil.StringToInteger(param
				.getInvestAccount()));
		requst.setStartDate(param.getStartDate());
		requst.setEndDate(param.getEndDate());
		requst.setCxRowcount(param.getCxRowcount());
		requst.setPageNo(param.getPageNo());
		response = Constants.services.fun105(requst);
		return isTrue(response);
	}

	/**
	 * 持仓查询
	 * 
	 * @return true/false
	 */
	public boolean call103Fun() {
		Fun103Requst requst = new Fun103Requst();
		requst.setClientNo(entity.getClientNo());
		requst.setInvestAccount(StringUtil.StringToInteger(entity
				.getInvestAccount()));
		response = Constants.services.fun103(requst);
		return isTrue(response);
	}

	/**
	 * 撤单
	 * 
	 * @return Fun201Response
	 */
	public boolean call202Fun() {
		HomesEntrustWithdraw param = (HomesEntrustWithdraw) entity;
		Fun202Requst requst = new Fun202Requst();
		requst.setClientNo(param.getClientNo());
		requst.setInvestAccount(StringUtil.StringToInteger(param
				.getInvestAccount()));
		requst.setEntrustNo(StringUtil.StringToInteger(param.getEntrustNo()));
		response = Constants.services.fun202(requst);
		return isTrue(response);
	}

	/**
	 * 查询可用资产
	 * 
	 * @return
	 */
	public boolean call210Fun() {
		Fun210Requst requst = new Fun210Requst();
		requst.setClientNo(entity.getClientNo());
		requst.setInvestAccount(StringUtil.StringToInteger(entity
				.getFundAccount()));
		Fun210Response resp = Constants.services.fun210(requst);
		response = resp;
		if (isTrue(resp)) {
			if (resp.getCurrmarket().compareTo(BigDecimal.ZERO) == 0
					&& resp.getCurrfund().compareTo(BigDecimal.ZERO) == 0) {
				return true;
			} else {
				log.warn("账户:" + entity.getClientNo() + "在小Homs中还有可用资产，不可使用");
			}
		}
		return false;
	}

	/**
	 * 查询可用资产
	 * 
	 * @return
	 */
	public boolean call210FunResp() {
		Fun210Requst requst = new Fun210Requst();
		requst.setClientNo(entity.getClientNo());
		requst.setInvestAccount(StringUtil.StringToInteger(entity
				.getFundAccount()));
		Fun210Response resp = Constants.services.fun210(requst);
		response = resp;
		return isTrue(resp);
	}

	/*	*//**
	 * 查询可用资产
	 * 
	 * @return
	 */
	/*
	 * public HomesCapital call210FunResponse() { Fun210Requst requst = new
	 * Fun210Requst(); requst.setClientNo(entity.getClientNo());
	 * requst.setInvestAccount(StringUtil.StringToInteger(entity
	 * .getFundAccount()));
	 * 
	 * Fun210Response response = amService.fun210(requst);
	 * 
	 * if (isTrue(response)) { return new
	 * HomesCapital(response.getCurrmarket().floatValue(),
	 * response.getCurrfund().floatValue()); } return null; }
	 */

	/**
	 * 资金划转
	 * 
	 * @return Fun201Response
	 */
	public boolean call501Fun() {
		EntrustMoveFund entrustMoveFund = (EntrustMoveFund) entity;
		Fun501Requst requst = new Fun501Requst();
		requst.setClientNoFrom(entrustMoveFund.getClientNo());
		requst.setClientNoTo(entrustMoveFund.getClientNoTo());
		requst.setOccurBalance(StringUtil.parseBigDecimal(
				entrustMoveFund.getOccurBalance(), 2));
		if(StringUtils.equals("pay", entrustMoveFund.getFlag())){
			requst.setBusinessFlag(entrustMoveFund.getBusinessFlagPay());
		}else{
			requst.setBusinessFlag(entrustMoveFund.getBusinessFlagBack());
		}
		requst.setRemark(entrustMoveFund.getRemark());
		return isTrue(Constants.services.fun501(requst));
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public boolean call311Fun() {
		HomesPwd homesPwd = (HomesPwd) entity;
		Fun311Requst requst = new Fun311Requst();
		requst.setClientNo(homesPwd.getClientNo());
		requst.setOldpassword(homesPwd.getOldPwd());
		requst.setNewpassword(homesPwd.getNewPwd());
		return isTrue(Constants.services.fun311(requst));
	}

	public boolean isTrue(AmServiceResult response) {
		if (response.getErrorNo() == 0) {
			return true;
		} else {
			log.error("调用小homs发生异常：" + response.getErrorInfo());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public HomesResponse getResponse(String fn) {
		if (StringUtils.equals(Constants.FN201, fn)) {
			Fun201Response resp = (Fun201Response) response;
			return new HomesResponse(resp.getBranchNo(), resp.getFundAccount(),
					resp.getEntrustNo(), resp.getBatchNo(), resp.getClientNo());
		} else if (StringUtils.equals(Constants.FN210, fn)) {
			Fun210Response resp = (Fun210Response) response;
			return new HomesCapital(resp.getCurrmarket().floatValue(), resp
					.getCurrfund().floatValue(), resp.getUsermarket()
					.floatValue());
		} else if (StringUtils.equals(Constants.FN104, fn)) {
			AmResultList<Fun104Response> list = (AmResultList<Fun104Response>) response;
			Homes104Resp resp = new Homes104Resp();
			List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
			for (Fun104Response item : list) {
				EntrustQueryEntity homes104Resp = new EntrustQueryEntity();
				homes104Resp.setAmentrust_status(StringUtil.convertStatus(item
						.getEntrustStatus()));
				homes104Resp.setEntrust_price(item.getEntrustPrice()
						.floatValue() + "");
				homes104Resp.setEntrust_amount(item.getEntrustAmount()
						.intValue() + "");
				homes104Resp.setEntrust_time(item.getEntrustTime() + "");
				homes104Resp.setEntrust_direction(item.getEntrustBs());
				homes104Resp.setExchange_type(ExchangeTypeEnum.getDesc(item
						.getExchangeType()));
				homes104Resp.setEntrust_no(item.getEntrustNo() + "");
				homes104Resp.setBusiness_balance(item.getBusinessBalance()
						.floatValue() + "");
				homes104Resp.setBusiness_amount(item.getBusinessAmount()
						.intValue() + "");
				homes104Resp.setCancel_info(item.getSecuErrorInfo());
				entities.add(homes104Resp);
			}
			resp.setList(entities);
			return resp;
		} else if (StringUtils.equals(Constants.FN105, fn)) {
			AmResultList<Fun105Response> list = (AmResultList<Fun105Response>) response;
			Homes104Resp resp = new Homes104Resp();
			List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
			for (Fun105Response item : list) {
				EntrustQueryEntity homes104Resp = new EntrustQueryEntity();
				homes104Resp.setStock_code(item.getStockCode());
				homes104Resp.setAmentrust_status(StringUtil.dealStatus(item
						.getRealStatus()));
				homes104Resp.setEntrust_direction(item.getEntrustBs());
				homes104Resp.setExchange_type(ExchangeTypeEnum.getDesc(item
						.getExchangeType()));
				homes104Resp.setEntrust_no(item.getEntrustNo() + "");
				homes104Resp.setBusiness_balance(item.getBusinessBalance()
						.floatValue() + "");
				homes104Resp.setBusiness_amount(item.getBusinessAmount()
						.intValue() + "");
				homes104Resp.setBusiness_time(item.getBusinessTime() + "");
				entities.add(homes104Resp);
			}
			resp.setList(entities);
			return resp;
		} else if (StringUtils.equals(Constants.FN103, fn)) {
			AmResultList<Fun103Response> list = (AmResultList<Fun103Response>) response;
			Homes103Resp resp = new Homes103Resp();
			List<HomsEntity103> entity103s = new ArrayList<HomsEntity103>();
			for (Fun103Response item : list) {
				HomsEntity103 entity103 = new HomsEntity103(
						item.getStockCode(), item.getCurrentAmount()
								.floatValue() + "", item.getEnableAmount()
								.floatValue() + "", item.getCostPrice()
								.floatValue() + "", item.getCurrMarket()
								.floatValue() + "");
				entity103s.add(entity103);
			}
			resp.setList(entity103s);
			return resp;
		} else {
			return null;

		}
	}
}
