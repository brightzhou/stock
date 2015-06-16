package com.zeekie.stock.service.impl;

import java.beans.IntrospectionException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
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
import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.FinanceProductDO;
import com.zeekie.stock.entity.FundFlowDO;
import com.zeekie.stock.entity.HistoryFinanceDO;
import com.zeekie.stock.entity.form.FinanceProducetForm;
import com.zeekie.stock.enums.FundFlowEnum;
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
	private BatchMapper batchMapper;

	@Autowired
	private AcountMapper acountMapper;

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
			} else {
				return BeanMapUtil.convertBean(financeMapper
						.getFinanceProductLast());
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
		String userId = form.getUserId();
		String productName = form.getFinanceProduct();
		String balance = financeMapper.checkBalance(userId, financeLimit);
		if (StringUtils.isBlank(balance)) {
			return Constants.CODE_BALANCE_LITTLE;
		}
		// 计算额度是否够买
		synchronized (this) {
			Float leaveLimits = financeMapper.queryLeaveLimits(
					form.getProductCode(), userId);
			if (null != leaveLimits) {
				if (leaveLimits <= 0) {
					if (log.isDebugEnabled()) {
						log.debug("用户[" + userId + "]购买理财产品[" + productName
								+ "]历史金额已经达到最大额度，不能继续购买");
					}
					return Constants.CODE_REACH_MAX_lIMIT;
				} else {
					if (leaveLimits < Float.parseFloat(financeLimit)) {
						String canBuyLeavelimits = "$"
								+ StringUtil.keepThreeDot(leaveLimits);
						if (log.isDebugEnabled()) {
							log.debug("用户[" + userId + "]当前购买理财产品["
									+ productName + "]的金额超出限制,当前只能购买："
									+ canBuyLeavelimits);
						}
						return canBuyLeavelimits;
					}
				}
			}

			Float totalLimit = financeMapper.queryTotalLimitBalance(
					form.getProductCode(), financeLimit);
			if (totalLimit < 0f) {
				if (log.isDebugEnabled()) {
					log.debug("用户[" + userId + "]当前购买理财产品[" + productName
							+ "]的金额超出总的理财金额限制，不能购买");
				}
				return Constants.CODE_TOTAL_lIMIT_LITTLE;
			}
		}
		// 保存购买记录
		financeMapper.saveCurrentFinance(form);
		// 更新理财额度
		financeMapper.updateTotalLimit(form.getProductCode(), financeLimit);
		// 更新钱包，扣除理财的钱
		financeMapper.updateWallet(financeLimit, form.getUserId());

		List<FundFlowDO> fee = new ArrayList<FundFlowDO>();
		try {
			FundFlowDO flowDO = new FundFlowDO(acountMapper.queryNickname(form
					.getUserId()), FundFlowEnum.FINANCE_CAPATAL_BUY.getType(),
					"-" + financeLimit, MessageFormat.format(
							FundFlowEnum.FINANCE_CAPATAL_BUY.getDesc(),
							form.getTicket()));
			fee.add(flowDO);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		// 记录流水
		batchMapper.batchInsert(TradeMapper.class, "addFlowFundBatch", fee);
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
