package com.zeekie.stock.service.xinge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tencent.xinge.XingeApp;
import com.zeekie.stock.Constants;

public class XingePush {

	static Logger log = LoggerFactory.getLogger(XingePush.class);

	private static XingeApp app = Constants.getXinge();

	public static void push(StockMsg msg) {
		pushSingleAccount(msg);
	}

	public static void pushTags(StockMsg msg) {
		callApi(msg);
	}

	private static void callApi(StockMsg msg) {

		List<String> tagList = new ArrayList<String>();
		tagList.add(msg.getUserId());

		JSONObject androidRet = app.pushTags(XingeApp.DEVICE_ANDROID, tagList,
				"AND", Messages.getAndroidMessage(msg));// send to android

		JSONObject iosRet = app.pushTags(XingeApp.DEVICE_IOS, tagList, "AND",
				Messages.getIosMessage(msg),// send to ios
				Constants.environment);
		boolean success = (0 == androidRet.getInt("ret_code") && 0 == iosRet
				.getInt("ret_code"));
		if (!success) {
			log.error("batch set tag exception happend:android err_msg="
					+ androidRet.getString("err_msg") + ",ret_code="
					+ androidRet.getInt("ret_code") + ",iso err_msg="
					+ iosRet.getString("err_msg") + ",ret_code="
					+ iosRet.getInt("ret_code"));
		} else {
			if (log.isDebugEnabled()) {
				log.debug("acceptor =" + msg.getContent()
						+ " already accept message successfully. ");
			}
		}
	}

	// 下发单个账号
	private static void pushSingleAccount(StockMsg msg) {
		String acceptor = msg.getUserId();
		JSONObject androidRet = app.pushSingleAccount(XingeApp.DEVICE_ANDROID,
				acceptor, Messages.getAndroidMessage(msg));

		JSONObject iosRet = app.pushSingleAccount(XingeApp.DEVICE_IOS,
				acceptor, Messages.getIosMessage(msg), Constants.environment);

		boolean success = (0 == androidRet.getInt("ret_code") && 0 == iosRet
				.getInt("ret_code"));

		if (!success) {
			log.error("send message exception happend:android err_msg="
					+ androidRet.getString("err_msg") + ",ret_code="
					+ androidRet.getInt("ret_code") + ",iso err_msg="
					+ iosRet.getString("err_msg") + ",ret_code="
					+ iosRet.getInt("ret_code"));
		} else {
			if (log.isDebugEnabled()) {
				log.debug("nickname =" + acceptor
						+ " already accept message successfully. ");
			}
		}
	}
}
