package com.zeekie.stock.service.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.DeductDO;
import com.zeekie.stock.entity.FundFlowDO;
import com.zeekie.stock.entity.InsufficientBalanceRemindDO;
import com.zeekie.stock.enums.Fund;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.DealMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.DateUtil;

@Service
@Transactional
public class DeductManageFeeTimer {

	static Logger log = LoggerFactory.getLogger(DeductManageFeeTimer.class);

	@Autowired
	private AcountMapper acount;

	@Autowired
	private TradeMapper trade;

	@Autowired
	private BatchMapper batchMapper;

	@Autowired
	private DealMapper deal;

	@Autowired
	@Value("${stock.deduct.management.fee.radio}")
	private String fee;

	@Autowired
	@Value("${templateId_manage_fee}")
	private String templateId;

	/*
	 * @Autowired
	 * 
	 * @Value("${templateId_manage_fee}") private String chargeFeeDate;
	 */

	private String chargeFeeDate;

	public void deductFee() throws RuntimeException {
		try {
			String rightNow = DateUtil.dateToStr(new Date(), DateUtil.FORMAT_YYYY_MM_DD);

			if (StringUtils.isBlank(trade.selectFeeDay(rightNow))) {
				if (log.isDebugEnabled()) {
					log.debug("今天[" + rightNow + "]未设置收取服务费");
				}
				return;
			} else {
				// 开盘日禁止猜大盘,设置为不可买
				if (log.isDebugEnabled()) {
					log.debug("开始更新猜大盘状态");
				}
				deal.updateGuessStatus();
			}
			// 1、更新当前所有操盘用户管理费（相当于计算管理费）
			trade.updateManageFeeBatch("");

			// 2、查询更新的用户
			List<DeductDO> result = trade.queryCurrentStocker("");
			deductAllFee(result);

			// 5、计算所有客户钱包是否充足,不充足需要发短信提醒
			List<InsufficientBalanceRemindDO> li = acount.queryUserBalanceIsNotEnough();

			if (null != li && !li.isEmpty()) {
				// 短信提醒，充值
				for (int i = 0; i < li.size(); i++) {
					InsufficientBalanceRemindDO remindDO = li.get(i);
					sendRechargeNotice(remindDO.getTelephone(), remindDO.getNickname());
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	/**
	 * 3.05 扣除当天操盘的服务费
	 * 
	 * @throws RuntimeException
	 */
	public void deductFeeCurrentDay() throws RuntimeException {
		try {
			// 1、更新当前所有操盘用户管理费（相当于计算管理费）
			trade.updateManageFeeBatch(chargeFeeDate);

			// 2、查询更新的用户
			List<DeductDO> result = trade.queryCurrentStocker(chargeFeeDate);

			deductAllFee(result);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException();
		}
	}

	private void deductAllFee(List<DeductDO> result) throws Exception {
		if (result.isEmpty()) {
			return;
		}
		// 3、从用户账户中批量扣除管理费
		batchMapper.batchInsert(TradeMapper.class, "deductManageFeeBatch", result);

		List<FundFlowDO> fee = new ArrayList<FundFlowDO>();
		List<FundFlowDO> drawFeeJh = new ArrayList<FundFlowDO>();

		// 4、记录流水
		for (DeductDO each : result) {

			FundFlowDO flowDO = new FundFlowDO(each.getNickname(), Constants.MANAGEMENT_FEE, "-" + each.getFee(),
					"技术服务费");
			fee.add(flowDO);
			String nickname = each.getNickname();
			String referee = acount.queryRefereeNickname(nickname);
			if (StringUtils.isNotBlank(referee)) {
				// 2015.2.12 23:09 记录推广人服务费提成
				String type = Fund.AMORTIZATION.getType();
				FundFlowDO drawFeeDO = new FundFlowDO(referee, type, "+" + each.getDrawFee(),
						Fund.getDesc(nickname, type));
				drawFeeJh.add(drawFeeDO);
			}
		}
		// 4、1 给推广人加服务提成费
		batchMapper.batchInsert(TradeMapper.class, "addDrawFeeBatch", drawFeeJh);

		// 4、2记录流水
		batchMapper.batchInsert(TradeMapper.class, "addFlowFundBatch", fee);
		batchMapper.batchInsert(TradeMapper.class, "addFlowFundBatch", drawFeeJh);

	}

	private void sendRechargeNotice(String phone, String nickname) {
		if (StringUtils.isNotBlank(phone)) {
			if (!ApiUtils.send(Constants.MODEL_NOTICE_RECHARGE_FN, phone, nickname)) {
				log.error("send recharge notice failure,nickname:" + nickname + ",reason call api is wrong");
			}
		} else {
			log.error("send recharge notice failure,nickname:" + nickname + ",reason telephone is null");
		}
	}

	/**
	 * @param chargeFeeDate
	 *            the chargeFeeDate to set
	 */
	public void setChargeFeeDate(String chargeFeeDate) {
		this.chargeFeeDate = chargeFeeDate;
	}

}
