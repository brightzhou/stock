package com.zeekie.stock.service.homes;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hundsun.t2sdk.common.core.context.ContextUtil;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.hundsun.t2sdk.interfaces.share.event.EventReturnCode;
import com.hundsun.t2sdk.interfaces.share.event.EventType;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import com.zeekie.stock.Constants;

public class AStockTrade {

	Logger log = LoggerFactory.getLogger(AStockTrade.class);

	private T2Services server;

	private IEvent resp;

	private IDatasets result;

	private IDataset dataset;

	private IClient client = Constants.getClient();

	public AStockTrade() {

	}

	public void callHomes(String functionNo) throws Exception {
		dataset = DatasetService.getDefaultInstance().getDataset();
		if (StringUtils.isEmpty(Constants.HOMES_TOKEN)) {
			resp = ContextUtil
					.getServiceContext()
					.getEventFactory()
					.getEventByAlias(Constants.HOME_MANAGER_FNC_LOGIN,
							EventType.ET_REQUEST);
			dataset.addColumn("operator_no");
			dataset.addColumn("password");
			dataset.appendRow();
			dataset.updateString("operator_no", Constants.HOME_MANAGER_NO);
			dataset.updateString("password", Constants.HOME_MANAGER_PWD);
			resp.putEventData(dataset);
			resp = client.sendReceive(resp, 10000);
			// 获得结果集
			if (null == callApi())
				throw new Exception();
			Constants.HOMES_TOKEN = result.getDataset(0)
					.getString("user_token");
			dataset.clearAll();

			if (log.isDebugEnabled()) {
				log.debug("开始登陆homes，获取token:" + Constants.HOMES_TOKEN);
			}
		}
		callFunction(functionNo, Constants.HOMES_TOKEN);
	}

	private void callFunction(String functionNo, String token) throws Exception {
		resp = ContextUtil.getServiceContext().getEventFactory()
				.getEventByAlias(functionNo, EventType.ET_REQUEST);
		Map<String, String> data = assembleColumn(token);
		if (null != data && !data.isEmpty()) {
			for (Map.Entry<String, String> entry : data.entrySet()) {
				dataset.addColumn(entry.getKey());
			}
			dataset.appendRow();
			for (Map.Entry<String, String> entry : data.entrySet()) {
				dataset.updateString(entry.getKey(), entry.getValue());
			}
			resp.putEventData(dataset);
			resp = client.sendReceive(resp, 10000);
			if (null == callApi())
				throw new Exception();
		}
	}

	private IDatasets callApi() {
		try {
			if (resp.getReturnCode() != EventReturnCode.I_OK) { // 返回错误
				log.error("[!EventReturnCode.I_OK] error_no:"
						+ resp.getErrorNo() + ",error_info:"
						+ resp.getErrorInfo());
				// Constants.HOMES_TOKEN = "";
				return null;
			} else {
				result = resp.getEventDatas();
				tokenIsEffective();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	public boolean visitSuccess(String operation_no) {
		IDataset dataset = result.getDataset(0);
		String errorNo = dataset.getString("error_no");
		if (StringUtils.equals(Constants.CODE_HOMES_SUCCESS, errorNo)) {
			return true;
		} else {
			log.error("when operate function[" + operation_no
					+ "] excute failure ,error is : "
					+ dataset.getString("error_info"));
			return false;
		}
	}

	public Map<String, String> assembleColumn(String token) {
		if (log.isDebugEnabled()) {
			log.debug("return token is : " + token);
		}
		return null;
	}

	private boolean tokenIsEffective() {
		IDataset dataset = result.getDataset(0);
		String errorNo = dataset.getString("error_no");
		if (StringUtils.isEmpty(errorNo)) {
			return true;
		} else {
			if (StringUtils.equals("-1", errorNo)
					&& StringUtils.equals(Constants.TOKEN_LOSS,
							dataset.getString("error_info"))) {
				log.warn("token loss，下次访问重新登陆");
				Constants.HOMES_TOKEN = "";
			}
			return false;
		}
	}

	public IDatasets getDataSet() {
		return result;
	}

	public void over() {
		server.stop();
	}

}
