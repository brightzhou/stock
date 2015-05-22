package com.zeekie.stock.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.AccountDO;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.service.EntrustService;
import com.zeekie.stock.service.homes.StockEntrust;
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
	private String functionNo;

	@Override
	public String entrust(String nickname, String stockCode,
			String entrustAmount, String entrustPrice) {
		try {
			AccountDO accountDO = account.getAccount(nickname);
			String combineId = accountDO.getCombineId();
			String operateNo = accountDO.getTradeAcount();
			String fundAccount = accountDO.getFundAccount();
			StockEntrust entrust = new StockEntrust(fundAccount, combineId,
					operateNo, stockCode, entrustAmount, entrustPrice);
			entrust.callHomes(functionNo);
			Object obj = returnObj(entrust.getDataSet(), EntrustEntity.class);
			if (obj != null) {
				EntrustEntity entity = (EntrustEntity) obj;
				entity.set(fundAccount, combineId, operateNo);
				deal.insertEntrust(entity);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Constants.CODE_FAILURE;
		}
		return Constants.CODE_SUCCESS;
	}
}
