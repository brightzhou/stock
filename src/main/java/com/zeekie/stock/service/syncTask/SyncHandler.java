package com.zeekie.stock.service.syncTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sitong.thinker.common.util.mybatis.BatchMapper;

import com.tencent.xinge.XingeApp;
import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.DeductDO;
import com.zeekie.stock.entity.WarnLineDO;
import com.zeekie.stock.enums.Fund;
import com.zeekie.stock.enums.XingeEnum;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.respository.TradeMapper;
import com.zeekie.stock.service.WebService;
import com.zeekie.stock.service.xinge.StockMsg;
import com.zeekie.stock.service.xinge.XingePush;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.DateUtil;

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
			try {
				nickname = account.queryNickname(param.get("userId"));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			if (webService.payToUs("", nickname, param.get("amount"))) {
				if (log.isDebugEnabled()) {
					log.debug("用户:" + nickname + "充值成功，开始向APP对宋消息");
				}
				pushPaySuccess(param.get("userId"), nickname);
			}

		}
	}

	private void pushPaySuccess(String userId, String nickname) {
		StockMsg msg = new StockMsg();
		msg.setContent("5");
		msg.setNickname(nickname);
		msg.setUserId(userId);
		msg.setTitle("充值成功");
		XingePush.push(msg);
	}

	private void push(String userId, String nickname) {
		if (log.isDebugEnabled()) {
			log.debug("用户" + nickname + "被后台平仓，推送消息给APP");
		}
		StockMsg msg = new StockMsg();
		msg.setContent("2");
		msg.setNickname(nickname);
		msg.setUserId(userId);
		msg.setTitle("平仓");
		XingePush.push(msg);
	}

	private void handle(String type, String param) {
		// 操盘口手续费
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
				msg.setContent("3");
				msg.setTitle("打开APP");
				msg.setUserId(tag);
			} else {
				msg.setTitle("关闭APP");
				msg.setContent("4");
				msg.setUserId(tag);
			}
			XingePush.pushTags(msg);
		} else if (StringUtils.equals(Constants.TYPE_JOB_PIC_UPDATE, type)) {

			StockMsg msg = new StockMsg();
			msg.setContent(XingeEnum.PIC_UPDATE.getContent());
			msg.setTitle(XingeEnum.PIC_UPDATE.getTitle());
			msg.setUserId(tag);
			XingePush.pushTags(msg);
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
				Map<String, String> params = new HashMap<String, String>();
				params.put("operateId", operationNo + lineDO.getOperateId());
				params.put("nickname", lineDO.getNickname());
				params.put("actualAsset", lineDO.getActualAsset());
				params.put("warnFund", lineDO.getWarnFund());
				ApiUtils.sendMsg(Constants.MODEL_REACH_WARNLINE_REMIND_FN,
						params, lineDO.getPhone());
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
			String telephone = account.getUserPhone(param.get("nickname"));
			param.put("operationId", operationNo + operationId);
			ApiUtils.sendMsg(Constants.MODEL_EVENING_UP_REMIND_FN, param,
					telephone);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void deductFeeWhenOperation(String nickname) {
		try {
			if (DateUtil.compareDate()) {
				trade.updateManageFeeByUser(nickname);
				DeductDO fee = trade.queryNewStocker(nickname);
				trade.deductManageFee(fee);
				trade.recordFundflow(nickname, Constants.MANAGEMENT_FEE,
						fee.getFee() + "", "技术服务费");

				String referee = account.queryRefereeNickname(nickname);
				if (StringUtils.isNotBlank(referee)) {
					String type = Fund.AMORTIZATION.getType();
					trade.recordFundflow(referee, type, fee.getDrawFee() + "",
							Fund.getDesc(nickname, type));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
