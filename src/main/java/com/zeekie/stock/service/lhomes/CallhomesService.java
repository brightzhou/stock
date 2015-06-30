package com.zeekie.stock.service.lhomes;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.hczq.hz.intf.AmServiceResult;
import com.hczq.hz.intf.AmServices;
import com.hczq.hz.intf.Fun201Requst;
import com.hczq.hz.intf.Fun201Response;
import com.hczq.hz.intf.Fun210Requst;
import com.hczq.hz.intf.Fun210Response;
import com.hczq.hz.intf.Fun501Requst;
import com.zeekie.stock.Constants;
import com.zeekie.stock.service.lhomes.entity.AHomesEntity;
import com.zeekie.stock.service.lhomes.entity.EntrustEntity;
import com.zeekie.stock.service.lhomes.entity.EntrustMoveFund;
import com.zeekie.stock.service.lhomes.entity.HomesResponse;
import com.zeekie.stock.util.StringUtil;

public class CallhomesService {

	private static final Logger log = LoggerFactory
			.getLogger(CallhomesService.class);

	private static CallhomesService service = null;

	private static AmServices amService = Constants.getAmService();

	private AHomesEntity entity;

	private AmServiceResult response;

	public static synchronized CallhomesService getInstance() {
		return (null == service) ? new CallhomesService() : service;
	}

	/**
	 * 委托
	 * 
	 * @return Fun201Response
	 */
	public boolean call201Fun() {
		EntrustEntity entrustEntity = (EntrustEntity) entity;
		Fun201Requst requst = new Fun201Requst();
		requst.setClientNo(entrustEntity.getClientNo());
		requst.setInvestAccount(StringUtils.stringToInteger(entrustEntity
				.getInvestAccount()));
		requst.setPassword(entrustEntity.getPassword());
		requst.setExchangeType("0");
		requst.setEntrustBs("0");
		requst.setStockCode(entrustEntity.getStockCode());
		requst.setEntrustType("0");
		requst.setEntrustProp("0");
		requst.setEntrustPrice(StringUtil.parseBigDecimal(entrustEntity
				.getEntrustPrice()));
		response = amService.fun201(requst);
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
		requst.setFundAccount(StringUtils.stringToInteger(entity
				.getFundAccount()));

		Fun210Response response = amService.fun210(requst);

		if (isTrue(response)) {

			if (response.getCurrmarket().compareTo(BigDecimal.ZERO) == 0
					&& response.getCurrfund().compareTo(BigDecimal.ZERO) == 0) {
				return true;
			} else {
				log.warn("账户:" + entity.getClientNo() + "在小Homs中还有可用资产，不可使用");
			}
		}

		return false;
	}

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
		requst.setOccurBalance(StringUtil.parseBigDecimal(entrustMoveFund
				.getOccurBalance()));
		requst.setBusinessFlag(entrustMoveFund.getBusinessFlag());
		requst.setRemark(entrustMoveFund.getRemark());
		return isTrue(amService.fun501(requst));
	}

	public void setEntity(AHomesEntity entity) {
		this.entity = entity;
	}

	public boolean isTrue(AmServiceResult response) {
		if (response.getErrorNo() == 0) {
			return true;
		} else {
			log.error("调用委托接口发生异常：" + response.getErrorInfo());
			return false;
		}
	}

	public HomesResponse getResponse(String fn) {
		if (StringUtils.equals(Constants.FN201, fn)) {
			Fun201Response resp = (Fun201Response) response;
			return new HomesResponse(resp.getBranchNo(), resp.getFundAccount(),
					resp.getEntrustNo(), resp.getBatchNo(), resp.getClientNo());
		} else {
			return null;
		}
	}

}
