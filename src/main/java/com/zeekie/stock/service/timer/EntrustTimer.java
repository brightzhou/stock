package com.zeekie.stock.service.timer;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.Constants;
import com.zeekie.stock.enums.AmentrustStatusEnum;
import com.zeekie.stock.enums.EntrustDirectionEnum;
import com.zeekie.stock.enums.ExchangeTypeEnum;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.service.homes.StockCommQuery;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;
import com.zeekie.stock.service.impl.BaseImpl;

@Service
@Transactional
public class EntrustTimer extends BaseImpl{

	@Autowired
	private DealMapper deal;

	@Autowired
	private AcountMapper account;

	@Autowired
	@Value("${func_am_entrust_history_qry}")
	private String func_am_entrust_history_qry;
	
	@Autowired
	private BatchMapper batchMapper;
	
	@Autowired
	private AcountMapper acount;

	public JSONArray  historyEntrustQuery(){
		try {
			StockCommQuery stockCommQuery = new StockCommQuery("2","20150527");
			stockCommQuery.callHomes(func_am_entrust_history_qry);
            List<?> obj = returnObj(stockCommQuery.getDataSet(),EntrustQueryEntity.class);
			List<EntrustQueryEntity> entities = new ArrayList<EntrustQueryEntity>();
			EntrustQueryEntity entity = null;
			JSONArray ja = new JSONArray();
			if (!obj.isEmpty()) {
				for (Object each : obj) {
					entity = (EntrustQueryEntity) each;
					entity.setFundAccount(Constants.HOME_MANAGER_NO);
					entities.add(entity);
				}
				for (EntrustQueryEntity entrustEntity : entities) {
					JSONObject jo = new JSONObject();
					jo.put("StockCode", entrustEntity.getStock_code());
					jo.put("amentrustStatus", AmentrustStatusEnum.getDesc(entrustEntity.getAmentrust_status()));
					jo.put("entrustPrice", entrustEntity.getEntrust_price());
					jo.put("entrustAmount", entrustEntity.getEntrust_amount());
					jo.put("entrustNo", entrustEntity.getEntrust_no());
					jo.put("exchangeType",ExchangeTypeEnum.getDesc(entrustEntity.getExchange_type()));
					jo.put("entrustDirection", EntrustDirectionEnum.getDesc(entrustEntity.getEntrust_direction()));
					jo.put("businessBalance", entrustEntity.getBusiness_balance());
					jo.put("businessAmount", entrustEntity.getBusiness_amount());
					jo.put("entrustTime", entrustEntity.getEntrust_time());
					jo.put("cancelInfo", entrustEntity.getCancel_info());
					ja.add(jo);
			    }
				
			}
			return ja;
		} catch (Exception e) {
			 e.getMessage() ;
		}
		return null;
	}

	 

 



 

}
