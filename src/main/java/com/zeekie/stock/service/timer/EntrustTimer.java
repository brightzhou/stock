package com.zeekie.stock.service.timer;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.CurrentEntrustDO;
import com.zeekie.stock.enums.AmentrustStatusEnum;
import com.zeekie.stock.enums.EntrustDirectionEnum;
import com.zeekie.stock.enums.ExchangeTypeEnum;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.service.homes.StockCommQuery;
import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;
import com.zeekie.stock.service.impl.BaseImpl;
import com.zeekie.stock.util.DateUtils;
import com.zeekie.stock.util.StringUtil;

@Service
@Transactional
public class EntrustTimer extends BaseImpl {

	 
	static Logger log = LoggerFactory.getLogger(DeductManageFeeTimer.class);
	@Autowired
	@Value("${func_am_entrust_history_qry}")
	private String func_am_entrust_history_qry;
    @Autowired
	private DealMapper deal;
	
	
	public String getFunc_am_entrust_history_qry() {
		return func_am_entrust_history_qry;
	}



	public void setFunc_am_entrust_history_qry(String funcAmEntrustHistoryQry) {
		func_am_entrust_history_qry = funcAmEntrustHistoryQry;
	}



	public void  historyEntrustQuery(){
		boolean flag = false ;
		int count = 5 ;
		do{
		   try {
				String day =DateUtils.formatDate(DateUtils.getInterDay(-1),"yyyy-MM-dd");
				StockCommQuery stockCommQuery = new StockCommQuery("2",DateUtils.formatDate(DateUtils.getInterDay(-1),"yyyyMMdd"));   
				stockCommQuery.callHomes(func_am_entrust_history_qry);
	            List<?> obj =  returnObj(stockCommQuery.getDataSet(),EntrustQueryEntity.class);
				EntrustQueryEntity entrustEntity = null;
				String time = null;
				if (!obj.isEmpty()) {
					 StringBuffer dayTime =null;
					for (Object each : obj) {
						dayTime = new StringBuffer();
						entrustEntity = (EntrustQueryEntity) each;
					    entrustEntity.setCombineId(entrustEntity.getCombine_id());
						entrustEntity.setFundAccount(entrustEntity.getFund_account());
					    entrustEntity.setNickname("");
					    entrustEntity.setOperateNo("");
					    time =  entrustEntity.getEntrust_time();
					    if(StringUtil.isNotBlank(time)){
					    	   int  number = Integer.parseInt(time.substring(0,1)) ;
					    	   if(number<10&&number>1){
					    		   dayTime.append(day).append(" 0").append(time.substring(0,1)).append(":").append(time.substring(1,3)).append(":").append(time.substring(3,5));
					    	   }else{
					    		   dayTime.append(day).append(" ").append(time.substring(0,2)).append(":").append(time.substring(2,4)).append(":").append(time.substring(4,5)).append("0");
					    	   }
					    	   System.out.println(dayTime.toString());
					   	   
					    }
					    List<CurrentEntrustDO> entrustDO  =   deal.queryTradeInfoByCombineId(entrustEntity.getCombineId(),dayTime.toString());
					    if(entrustDO!=null&&entrustDO.size()>0){
					    	 entrustEntity.setNickname(entrustDO.get(0).getNickName());
					    	 entrustEntity.setOperateNo(entrustEntity.getOperateNo());
		                     deal.updateEntrust(entrustEntity);
					    }
					  }
					 if(log.isDebugEnabled()) {
						  log.debug("获取历史委托信息");
					 }
				 }else{
					 if(log.isDebugEnabled()) {
						   log.debug("获取历史委托信息为空");
				     }
					 flag = true ;
					 count--;
					 Thread.sleep(1000*60*5);
				 }
			} catch (Exception e) {
				 flag = true ;
				 count--;
				 log.error(e.getMessage(), e);
				 try {
					 Thread.sleep(1000*60*5);
				 } catch (InterruptedException e1) {
				 
				 }
			}
		}while(flag&&count>0);
	}

	 

 



 

}
