package com.zeekie.stock.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hundsun.t2sdk.common.share.util.StringUtil;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.zeekie.stock.Constants;
import com.zeekie.stock.enums.VerifyCodeEnum;
import com.zeekie.stock.respository.StockMapper;
import com.zeekie.stock.util.BeanMapUtil;

public class BaseImpl {

	static Logger log = LoggerFactory.getLogger(BaseImpl.class);

	@Autowired
	private StockMapper trade;

	@Autowired
	@Value("${interval}")
	private String interval;

	public String getDesc(String source) {
		VerifyCodeEnum[] code = VerifyCodeEnum.values();
		for (VerifyCodeEnum item : code) {
			if (StringUtils.equals(StringUtils.trim(source), item.getSource())) {
				return item.getDesc();
			}
		}
		return "";
	}

	public boolean validVerifyCode(String telephone, String verifyCode,
			String source) {
		boolean flag = false;
		try {
			if (StringUtils.isNotEmpty(trade.checkVerifyCode(telephone,
					verifyCode, getSecond(), source))) {
				flag = true;
			}
			// else {
			// // 验证码过期
			// trade.setVerifyCodeExpired(telephone);
			// }
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	private int getSecond() {
		return StringUtils.isNotBlank(interval) ? Integer.parseInt(interval) * 60
				: 60;
	}

	/**
	 * 返回homes交易结果
	 * 
	 * @param data
	 * @param entity
	 *            具体操作的子类
	 * @return
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IntrospectionException
	 */
	public List<?> returnObj(IDatasets data, Class<?> entity)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {

		int datasetCount = data.getDatasetCount();
		List<Object> result = new ArrayList<Object>();
		// 遍历所有的结果集
		for (int i = 0; i < datasetCount; i++) {
			// 开始读取单个结果集的信息
			IDataset ds = data.getDataset(i);
			String errorNo = ds.getString("error_no");
			if (StringUtil.equals(Constants.CODE_HOMES_SUCCESS, errorNo)
					|| StringUtils.isBlank(errorNo)) {
				int columnCount = ds.getColumnCount();
				// 遍历单个结果集列信息
				List<String> columnName = new ArrayList<String>();
				for (int j = 1; j <= columnCount; j++) {
					columnName.add(ds.getColumnName(j));
				}
				ds.beforeFirst();
				while (ds.hasNext()) {
					Map<String, String> map = new HashMap<String, String>();
					ds.next();
					for (int k = 0; k < columnName.size(); k++) {
						String column = columnName.get(k);
						map.put(column, ds.getString(column));
					}
					result.add(BeanMapUtil.convertMap(entity, map));
				}
				if (log.isDebugEnabled()) {
					log.debug("访问homes服务[" + data.getDatasetName(0)
							+ "],返回的数据长度为：" + result.size());
				}
			} else {
				log.error("访问homes服务[" + data.getDatasetName(0)
						+ "],：error_no 返回不为0-不成功");
			}
		}
		return result;
	}
	
	
	/**
	 * 返回homes只有父类集合交易结果
	 * 
	 * @param data
	 * @param entity
	 *            具体操作的子类
	 * @return
	 */
	public List<?> returnOnlyParentList(IDatasets data, Class<?> entity) {
        int datasetCount = data.getDatasetCount();
		List<Object> result = new ArrayList();
		// 遍历所有的结果集
		if (datasetCount>0) {
			// 开始读取单个结果集的信息
			IDataset ds = data.getDataset(0);
			String errorNo = ds.getString("error_no");
			if (StringUtil.equals(Constants.CODE_HOMES_SUCCESS, errorNo)|| StringUtils.isBlank(errorNo)) {
				int columnCount = ds.getColumnCount();
				Map<String, String> map = new HashMap<String, String>();
				// 遍历单个结果集列信息
				ds.beforeFirst();
				while (ds.hasNext()) {
					ds.next();
					for (int j = 1; j <= columnCount; j++) {
						map.put(ds.getColumnName(j), ds.getString(j));
					}
					if (!map.isEmpty()) {
						try {
							result.add(BeanMapUtil.convertMap(entity, map));
						} catch (IntrospectionException e) {
							log.error(e.getMessage(), e);
						} catch (IllegalAccessException e) {
							log.error(e.getMessage(), e);
						} catch (InstantiationException e) {
							log.error(e.getMessage(), e);
						} catch (InvocationTargetException e) {
							log.error(e.getMessage(), e);
						}
					}
				}
				if (log.isDebugEnabled()) {
					log.debug("访问homes服务[" + data.getDatasetName(0)+ "],返回的数据为：" + map);
				}
			} else {
				log.error("访问homes服务[" + data.getDatasetName(0)
						+ "],：error_no 返回不为0-不成功");
			}
		}
		return result;
	}
}
