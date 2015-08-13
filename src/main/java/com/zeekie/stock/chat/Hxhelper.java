package com.zeekie.stock.chat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zeekie.stock.Constants;
import com.zeekie.stock.chat.httpclient.apidemo.EasemobIMUsers;
import com.zeekie.stock.chat.jersey.apidemo.EasemobChatGroups;
import com.zeekie.stock.entity.UserInfoDO;

public class Hxhelper {

	static Logger log = LoggerFactory.getLogger(Hxhelper.class);

	public static void registhx(String userid) {
		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
		datanode.put("username", userid);
		datanode.put("password", Constants.code_hx_pwd);
		// 注册
		ObjectNode createNewIMUserSingleNode = EasemobIMUsers
				.createNewIMUserSingle(datanode);
		// 加入群聊
		EasemobChatGroups.join(userid);
		if (null != createNewIMUserSingleNode) {
			if (log.isDebugEnabled()) {
				log.debug(createNewIMUserSingleNode.toString());
			}
		}
	}

	public static void deleteFromgroup(List<UserInfoDO> result) {
		for (UserInfoDO user : result) {
			EasemobChatGroups.deleteUserFromGroup("90968535114711484",
					user.getUserId());
		}
	}

	public static void main(String[] args) {

		for (int i = 345; i <= 4943; i++) {
			EasemobChatGroups.deleteUserFromGroup("90968535114711484", i + "");
			log.debug(EasemobChatGroups.deleteUserFromGroup("90968535114711484", i + "").toString());
		}
	}
}
