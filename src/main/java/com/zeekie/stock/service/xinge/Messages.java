package com.zeekie.stock.service.xinge;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;

/**
 * @Author zeekie
 * @date 2014��8��1��
 */
public class Messages {

	public static Message getAndroidMessage(StockMsg notice) {

		Message android = new Message();
		android.setType(Message.TYPE_MESSAGE);
//		android.setSendTime(notice.getSendTime());
		android.setTitle(notice.getTitle());
		android.setContent(notice.getContent());
		android.setExpireTime(259200);
		return android;
	}

	public static MessageIOS getIosMessage(StockMsg notice) {
		MessageIOS ios = new MessageIOS();
//		ios.setSendTime(notice.getSendTime());
		ios.setCustom(toMap(notice, "IOS"));
		ios.setAlert(notice.getTitle());
		ios.setExpireTime(259200);
		ios.setBadge(1);
		ios.setSound("beep.wav");
		return ios;
	}

	private static Map<String, Object> toMap(StockMsg notice, String equipment) {
		Map<String, Object> custom = new HashMap<String, Object>();
		if (StringUtils.equals("IOS", equipment)) {
			custom.put("content", notice.getContent());
			custom.put("title", notice.getTitle());
		}
		return custom;
	}

}
