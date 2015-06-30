package com.zeekie.stock.service.lhomes;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hczq.hz.intf.AmServiceResult;
import com.hczq.hz.intf.AmServices;
import com.hczq.hz.intf.Fun201Requst;
import com.hczq.hz.intf.Fun210Requst;
import com.hczq.hz.intf.Fun210Response;
import com.hczq.hz.intf.Fun311Requst;
import com.hczq.hz.intf.Fun501Requst;
import com.zeekie.stock.Constants;
import com.zeekie.stock.service.lhomes.entity.AHomesEntity;
import com.zeekie.stock.service.lhomes.entity.EntrustEntity;
import com.zeekie.stock.service.lhomes.entity.EntrustMoveFund;
import com.zeekie.stock.service.lhomes.entity.HomesCapital;
import com.zeekie.stock.service.lhomes.entity.HomesPwd;
import com.zeekie.stock.util.StringUtil;

public class CallhomesService {

	private static final Logger log = LoggerFactory
			.getLogger(CallhomesService.class);

	private static CallhomesService service = null;

	private static AmServices amService = Constants.getAmService();

	private AHomesEntity entity;

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
		requst.setInvestAccount(StringUtil.StringToInteger(entrustEntity
				.getInvestAccount()));
		requst.setPassword(entrustEntity.getPassword());
		requst.setExchangeType("0");
		requst.setEntrustBs("0");
		requst.setStockCode(entrustEntity.getStockCode());
		requst.setEntrustType("0");
		requst.setEntrustProp("0");
		requst.setEntrustPrice(StringUtil.parseBigDecimal(entrustEntity
				.getEntrustPrice()));
		return isTrue(amService.fun201(requst));
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
	 * 查询可用资产
	 * 
	 * @return
	 */
	public HomesCapital call210FunResponse() {
		Fun210Requst requst = new Fun210Requst();
		requst.setClientNo(entity.getClientNo());
		requst.setInvestAccount(StringUtil.StringToInteger(entity
				.getFundAccount()));

		Fun210Response response = amService.fun210(requst);

		if (isTrue(response)) {
			return new HomesCapital(response.getCurrmarket().floatValue(),
					response.getCurrfund().floatValue());
		}
		return null;
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
		return isTrue(amService.fun311(requst));
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

}
