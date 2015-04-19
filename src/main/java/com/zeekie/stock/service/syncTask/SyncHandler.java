package com.zeekie.stock.service.syncTask;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sitong.thinker.common.util.mybatis.BatchMapper;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.DeductDO;
import com.zeekie.stock.entity.PhoneAndTIcketDO;
import com.zeekie.stock.entity.StockRadioDO;
import com.zeekie.stock.entity.WarnLineDO;
import com.zeekie.stock.enums.Fund;
import com.zeekie.stock.enums.PicEnum;
import com.zeekie.stock.enums.XingeEnum;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.WebService;
import com.zeekie.stock.service.xinge.StockMsg;
import com.zeekie.stock.service.xinge.XingePush;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.DateUtil;
import com.zeekie.stock.util.StringUtil;

@Service
public class SyncHandler {

	static Logger log = LoggerFactory.getLogger(SyncHandler.class);

	@Autowired
	private TradeMapper trade;

	@Autowired
	private AcountMapper account;

	@Autowired
	private BatchMapper batchMapper;

	@Autowired
	private WebService webService;

	@Autowired
	@Value("${stock.actualAsset.reach.warnline.percent}")
	private String swingPercent;

	@Autowired
	@Value("${stock.operation.number}")
	private String operationNo;

	@Autowired
	@Value("${xinge.tag.all}")
	private String tag;

	public void handleJob(final String type, final String param) {
		new Thread() {
			@Override
			public void run() {
				handle(type, param);
			}
		}.start();
	}

	public void handleOtherJob(final String type,
			final Map<String, String> param) {
		new Thread() {
			@Override
			public void run() {
				handle(type, param);
			}
		}.start();
	}

	private void handle(String type, Map<String, String> param) {

		if (StringUtils.equals(Constants.TYPE_JOB_EVENING_UP_REMIND, type)) {
			eveningUpRemind(param);

			push(param.get("userId"), param.get("nickname"));

		} else if (StringUtils.equals(Constants.TYPE_JOB_PAY_NOTICE, type)) {

			String nickname = "";
			String rechargeResult = param.get("rechargeResult");
			String respResult = param.get("responseResult");
			String userId = param.get("userId");
			if (StringUtils.equals(Constants.CODE_SUCCESS, rechargeResult)) {
				try {
					nickname = account.queryNickname(param.get("userId"));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
				if (webService.payToUs("", nickname, param.get("amount"))) {
					if (log.isDebugEnabled()) {
						log.debug("用户:" + nickname + "充值成功，开始向APP对宋消息");
					}
				}
			} else {
				if (log.isDebugEnabled()) {
					log.debug("用户" + userId + "支付失败：" + respResult);
				}
			}
			pushPayResult(userId, nickname, rechargeResult, respResult);

		} else if (StringUtils.equals(Constants.TYPE_JOB_DEDUCT_ADDGURANTEE,
				type)) {
			deductFeeWhenAddGuarantee(param.get("nickname"),
					param.get("needDeductFee"));
		} else if (StringUtils.equals(Constants.TYPE_JOB_TO_REFEREE, type)) {
			String flag = param.get("flag");
			String referee = param.get("referee");
			String packet = param.get("packet");
			String nickname = param.get("nickname");
			String telephone = "";
			try {
				telephone = account.getUserPhone(referee).getPhone();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			if (StringUtils.equals("insufficient", flag)) {
				ApiUtils.send(Constants.MODEL_NOT_TO_BE_REFEREE_FN, telephone,
						referee, nickname, packet);
			} else {
				ApiUtils.send(Constants.MODEL_TO_BE_REFEREE_FN, telephone,
						referee, nickname, nickname, packet);
			}
		} else if (StringUtils
				.equals(Constants.TYPE_JOB_REDPACKET_NOTICE, type)) {
			ApiUtils.send(Constants.MODEL_REDPACKET_TO_USER_FN,
					param.get("telephone"), param.get("message"));
		}
	}

	private void pushPayResult(String userId, String nickname,
			String rechargeResult, String responseResult) {
		StockMsg msg = new StockMsg();
		String content = "";
		if (StringUtils.equals(Constants.CODE_SUCCESS, rechargeResult)) {
			content = XingeEnum.USER_RECHARGE_SUCCESS.getContent();
		} else {
			content = XingeEnum.USER_RECHARGE_FAILURE.getContent();
		}
		msg.setContent(content);
		msg.setNickname(nickname);
		msg.setUserId(userId);
		msg.setTitle(responseResult);
		XingePush.push(msg);
	}

	private void push(String userId, String nickname) {
		if (log.isDebugEnabled()) {
			log.debug("用户" + nickname + "被后台平仓，推送消息给APP");
		}
		StockMsg msg = new StockMsg();
		msg.setContent(XingeEnum.ELEVING_UP.getContent());
		msg.setNickname(nickname);
		msg.setUserId(userId);
		msg.setTitle(XingeEnum.ELEVING_UP.getTitle());
		XingePush.push(msg);
	}

	private void handle(String type, String param) {
		// 操盘扣手续费
		if (StringUtils.equals(Constants.TYPE_JOB_DEDUCT, type)) {
			deductFeeWhenOperation(param);
			// 到达警戒线需要短信提醒
		} else if (StringUtils.equals(Constants.TYPE_JOB_NOTICE_REACH_WARNLINE,
				type)) {
			try {
				sendWarnLineMsg();
				updateSwingUser();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		} else if (StringUtils.equals(Constants.TYPE_JOB_CONTROL_APP, type)) {

			StockMsg msg = new StockMsg();
			if (StringUtils.equals("open", param)) {
				msg.setContent(XingeEnum.OPEN_APP.getContent());
				msg.setTitle(XingeEnum.OPEN_APP.getTitle());
			} else if (StringUtils.equals("close", param)) {
				msg.setTitle(XingeEnum.CLOSE_APP.getTitle());
				msg.setContent(XingeEnum.CLOSE_APP.getContent());
			}
			msg.setUserId("all");
			XingePush.pushTags(msg);
		} else if (StringUtils.equals(Constants.TYPE_JOB_PIC_UPDATE, type)) {
			StockMsg msg = new StockMsg();
			if (StringUtils.equals(param, PicEnum.MAINPAGE.getType())) {
				msg.setContent(XingeEnum.PIC_MAIN_UPDATE.getContent());
				msg.setTitle(XingeEnum.PIC_MAIN_UPDATE.getTitle());
			} else if (StringUtils.equals(param, PicEnum.STARTPAGE.getType())) {
				msg.setContent(XingeEnum.PIC_START_UPDATE.getContent());
				msg.setTitle(XingeEnum.PIC_START_UPDATE.getTitle());
			}
			msg.setUserId("all");
			XingePush.pushTags(msg);
		} else if (StringUtils.equals(Constants.TYPE_JOB_SENDMSG_NOTICE, type)) {
			List<String> phones = account.queryAllUserPhone();
			for (String phone : phones) {
				ApiUtils.send(Constants.MODEL_REDPACKET_TO_USER_FN, phone,
						param);
			}
		}
	}

	private void updateSwingUser() throws Exception {
		trade.updateWarnFlagToZero(swingPercent);
	}

	private void sendWarnLineMsg() throws Exception {
		List<WarnLineDO> result = trade.queryNeedNoticeUser();
		if (null != result && !result.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("Start send warnLine msg,send users total:"
						+ result.size());
			}
			for (WarnLineDO lineDO : result) {
				ApiUtils.send(Constants.MODEL_REACH_WARNLINE_REMIND_FN,
						lineDO.getPhone(), lineDO.getNickname(),
						lineDO.getTicket(), lineDO.getActualAsset(),
						lineDO.getWarnFund());
			}
			batchMapper.batchInsert(TradeMapper.class, "updateWarnFlagToOne",
					result);
		}
	}

	/**
	 * 
	 * @param id
	 *            操盘id
	 */
	private void eveningUpRemind(Map<String, String> param) {
		try {
			String operationId = param.get("operationId");
			if (log.isDebugEnabled()) {
				log.debug("後台為操盘号为[" + operationId + "]平倉，發短信提醒用戶");
			}
			String nickname = param.get("nickname");
			PhoneAndTIcketDO andTIcketDO = account.getUserPhone(nickname);
			ApiUtils.send(Constants.MODEL_EVENING_UP_REMIND_FN,
					andTIcketDO.getPhone(), nickname, andTIcketDO.getTicket());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void deductFeeWhenAddGuarantee(String nickname,
			String needCaculateMoney) {
		try {
			if (DateUtil.compareDate()) {
				StockRadioDO radioDO = account
						.getAssignRadioForCurrUser(nickname);
				Float deductFee = (Float.parseFloat(needCaculateMoney)
						* radioDO.getAssignRadio() * radioDO
						.getManageFeeRadio());
				trade.recharge(nickname, "-" + deductFee);
				trade.recordFundflow(nickname, Constants.MANAGEMENT_FEE, ""
						+ deductFee + "", "技术服务费");
				if (log.isDebugEnabled()) {
					log.debug("用户增加保证金，收取服务费：" + deductFee);
				}

				String referee = account.queryRefereeNickname(nickname);
				if (StringUtils.isNotBlank(referee)) {
					String refereeDrawFee = "" + radioDO.getUpLinePercent()
							* deductFee;
					String type = Fund.AMORTIZATION.getType();
					trade.recharge(referee, refereeDrawFee);
					trade.recordFundflow(referee, type, refereeDrawFee + "",
							Fund.getDesc(nickname, type));

					if (log.isDebugEnabled()) {
						log.debug("用户增加保证金，收取服务费之后给推荐人" + referee + "用户提成："
								+ refereeDrawFee);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void deductFeeWhenOperation(String nickname) {
		try {

			String rightNow = DateUtil.dateToStr(new Date(),
					DateUtil.FORMAT_YYYY_MM_DD);

			if (StringUtils.isBlank(trade.selectFeeDay(rightNow))) {
				if (log.isDebugEnabled()) {
					log.debug("用户操盘，但是今天[" + rightNow + "]未设置收取服务费，故不进行时间比较了");
				}
			} else {
				if (DateUtil.compareDate()) {
					trade.updateManageFeeByUser(nickname);
					DeductDO fee = trade.queryNewStocker(nickname);
					trade.deductManageFee(fee);
					trade.recordFundflow(nickname, Constants.MANAGEMENT_FEE,
							fee.getFee() + "", "技术服务费");

					String referee = account.queryRefereeNickname(nickname);
					if (StringUtils.isNotBlank(referee)) {
						String type = Fund.AMORTIZATION.getType();
						String refereeDrawFee = fee.getDrawFee() + "";

						trade.recharge(referee, refereeDrawFee);
						trade.recordFundflow(referee, type, refereeDrawFee,
								Fund.getDesc(nickname, type));
						if (log.isDebugEnabled()) {
							log.debug("用户增加保证金，收取服务费之后给推荐人" + referee + "用户提成："
									+ refereeDrawFee);
						}
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
