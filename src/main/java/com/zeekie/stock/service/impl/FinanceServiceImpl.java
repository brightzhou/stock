package com.zeekie.stock.service.impl;

import java.beans.IntrospectionException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sitong.thinker.common.exception.ServiceInvokerException;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.FinanceProductDO;
import com.zeekie.stock.entity.HistoryFinanceDO;
import com.zeekie.stock.entity.form.FinanceProducetForm;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.FinanceMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.FinanceService;
import com.zeekie.stock.util.BeanMapUtil;
import com.zeekie.stock.util.StringUtil;

@Service
public class FinanceServiceImpl implements FinanceService {

	private static final Logger log = LoggerFactory
			.getLogger(FinanceServiceImpl.class);

	@Autowired
	private FinanceMapper financeMapper;

	@Autowired
	@Value("${stock_root_url}")
	private String root;

	@Autowired
	@Value("${apk_down_path}")
	private String contextpath;

	@Autowired
	@Value("${finance.protocal}")
	private String financeProtocal;

	@Autowired
	@Value("${stock.finance.ticket}")
	private String ticketPrex;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getCurrentFinance()
			throws ServiceInvokerException {

		FinanceProductDO productDO = financeMapper.getFinanceProduct();
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (null != productDO) {
				return BeanMapUtil.convertBean(productDO);
			}
		} catch (IntrospectionException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			log.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			log.error(e.getMessage(), e);
		}
		return map;
	}

	@Override
	public String saveCurrentFinance(FinanceProducetForm form)
			throws ServiceInvokerException {
		form.setTicket(ticketPrex + StringUtil.genRandomNum(4));
		String financeLimit = form.getFinanceLimit();

		String balance = financeMapper.checkBalance(form.getUserId(),
				financeLimit);
		if (StringUtils.isBlank(balance)) {
			return Constants.CODE_BALANCE_LITTLE;
		}
		// 计算额度是否够买
		synchronized (this) {
			Float totalLimit = financeMapper.queryTotalLimitBalance(
					form.getProductCode(), financeLimit);
			if (totalLimit <= 0f) {
				return Constants.CODE_TOTAL_lIMIT_LITTLE;
			}
		}
		// 保存购买记录
		financeMapper.saveCurrentFinance(form);
		// 更新理财额度
		financeMapper.updateTotalLimit(form.getProductCode(), financeLimit);
		// 更新钱包，扣除理财的钱
		financeMapper.updateWallet(financeLimit, form.getUserId());
		return Constants.CODE_SUCCESS;
	}

	@Override
	public JSONArray getHistoryFinance(String userId, String offset)
			throws ServiceInvokerException {
		List<HistoryFinanceDO> list = financeMapper.getHistoryFinance(userId,
				offset, root + contextpath + File.separator + financeProtocal);
		if (null != list) {
			return JSONArray.fromObject(list, Constants.jsonConfig);
		}
		return null;
	}

	@Override
	public String updateStatus(String userId, String isStock)
			throws ServiceInvokerException {
		financeMapper.updateStatus(userId, isStock);
		return Constants.CODE_SUCCESS;
	}

}
