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

	public static void pushAllServices(StockMsg msg) {

		JSONObject androidRet = app.pushAllDevice(XingeApp.DEVICE_ALL,
				Messages.getAndroidMessage(msg));// send to android

		JSONObject iosRet = app.pushAllDevice(XingeApp.DEVICE_ALL,
				Messages.getIosMessage(msg),// send to ios
				Constants.environment);

		log(msg, androidRet, iosRet);

	}

	private static void callApi(StockMsg msg) {

		List<String> tagList = new ArrayList<String>();
		tagList.add(msg.getUserId());

		JSONObject androidRet = app.pushTags(XingeApp.DEVICE_ANDROID, tagList,
				"OR", Messages.getAndroidMessage(msg));// send to android

		JSONObject iosRet = app.pushTags(XingeApp.DEVICE_IOS, tagList, "OR",
				Messages.getIosMessage(msg),// send to ios
				Constants.environment);
		log(msg, androidRet, iosRet);
	}

	// 下发单个账号
	private static void pushSingleAccount(StockMsg msg) {
		String acceptor = msg.getUserId();
		JSONObject androidRet = app.pushSingleAccount(XingeApp.DEVICE_ANDROID,
				acceptor, Messages.getAndroidMessage(msg));

		JSONObject iosRet = app.pushSingleAccount(XingeApp.DEVICE_IOS,
				acceptor, Messages.getIosMessage(msg), Constants.environment);

		log(msg, androidRet, iosRet);
		
	}

	private static void log(StockMsg msg, JSONObject androidRet,
			JSONObject iosRet) {
		if (0 == androidRet.getInt("ret_code")) {
			if (log.isDebugEnabled()) {
				log.debug("push to android success,send title="
						+ msg.getTitle() + " and content=" + msg.getContent());
			}
		} else {
			log.error("push exception happened,androidRet="
					+ androidRet.toString());
		}

		if (0 == iosRet.getInt("ret_code")) {
			if (log.isDebugEnabled()) {
				log.debug("push to ios success,send title=" + msg.getTitle()
						+ " and content=" + msg.getContent());
			}
		} else {
			log.error("push exception happened,iosRet=" + iosRet.toString());
		}
	}
}
