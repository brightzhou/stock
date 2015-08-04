package com.zeekie.stock.chat.jersey.apidemo;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zeekie.stock.chat.comm.Constants;
import com.zeekie.stock.chat.comm.HTTPMethod;
import com.zeekie.stock.chat.comm.Roles;
import com.zeekie.stock.chat.jersey.utils.JerseyUtils;
import com.zeekie.stock.chat.jersey.vo.ClientSecretCredential;
import com.zeekie.stock.chat.jersey.vo.Credential;
import com.zeekie.stock.chat.jersey.vo.EndPoints;

/**
 * REST API Demo : �����¼ Jersey2.9ʵ��
 * 
 * Doc URL: http://www.easemob.com/docs/rest/chatmessage/
 * 
 * @author Lynch 2014-07-12
 * 
 */
public class EasemobChatMessage {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EasemobChatMessage.class);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	private static final String APPKEY = Constants.APPKEY;

	// ͨ��app��client_id��client_secret4��ȡapp����Աtoken
	private static Credential credential = new ClientSecretCredential(
			Constants.APP_CLIENT_ID, Constants.APP_CLIENT_SECRET,
			Roles.USER_ROLE_APPADMIN);

	/**
	 * Main Test
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// ������Ϣ ��ȡ���µ�20���¼
		ObjectNode queryStrNode = factory.objectNode();
		queryStrNode.put("limit", "20");
		ObjectNode messages = getChatMessages(queryStrNode);

		// ������Ϣ ��ȡ7�����ڵ���Ϣ
		String currentTimestamp = String.valueOf(System.currentTimeMillis());
		String senvenDayAgo = String.valueOf(System.currentTimeMillis() - 7
				* 24 * 60 * 60 * 1000);
		ObjectNode queryStrNode1 = factory.objectNode();
		queryStrNode1.put("ql", "select * where  timestamp > " + senvenDayAgo
				+ " and timestamp < " + currentTimestamp);
		ObjectNode messages1 = getChatMessages(queryStrNode1);

		// ������Ϣ ��ҳ��ȡ
		ObjectNode queryStrNode2 = factory.objectNode();
		queryStrNode2.put("limit", "20");
		// ��һҳ
		ObjectNode messages2 = getChatMessages(queryStrNode2);
		// �ڶ�ҳ
		String cursor = messages2.get("cursor").asText();
		queryStrNode2.put("cursor", cursor);
		ObjectNode messages3 = getChatMessages(queryStrNode2);
	}

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @param queryStrNode
	 *
	 */
	public static ObjectNode getChatMessages(ObjectNode queryStrNode) {
		ObjectNode objectNode = factory.objectNode();
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		try {
			JerseyWebTarget webTarget = EndPoints.CHATMESSAGES_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);
			if (null != queryStrNode && null != queryStrNode.get("ql")
					&& !StringUtils.isEmpty(queryStrNode.get("ql").asText())) {
				webTarget = webTarget.queryParam("ql", queryStrNode.get("ql")
						.asText());
			}
			if (null != queryStrNode && null != queryStrNode.get("limit")
					&& !StringUtils.isEmpty(queryStrNode.get("limit").asText())) {
				webTarget = webTarget.queryParam("limit",
						queryStrNode.get("limit").asText());
			}
			if (null != queryStrNode
					&& null != queryStrNode.get("cursor")
					&& !StringUtils
							.isEmpty(queryStrNode.get("cursor").asText())) {
				webTarget = webTarget.queryParam("cursor",
						queryStrNode.get("cursor").asText());
			}
			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_GET, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}

}
