package com.zeekie.stock.chat.jersey.apidemo;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ArrayNode;
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
 * REST API Demo : Ⱥ����� Jersey2.9ʵ��
 * 
 * Doc URL: http://www.easemob.com/docs/rest/groups/
 * 
 * @author Lynch 2014-09-12
 * 
 */
public class EasemobChatGroups {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EasemobChatGroups.class);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	private static final String APPKEY = Constants.APPKEY;

	// ͨ��app��client_id��client_secret4��ȡapp����Աtoken
	private static Credential credential = new ClientSecretCredential(
			Constants.APP_CLIENT_ID, Constants.APP_CLIENT_SECRET,
			Roles.USER_ROLE_APPADMIN);

	public static void main(String[] args) {
		/**
		 * ��ȡAPP�����е�Ⱥ��ID curlʾ��: curl -X GET -i
		 * "https://a1.easemob.com/easemob-playground/test1/chatgroups" -H
		 * "Authorization: Bearer {token}"
		 */
		ObjectNode chatgroupidsNode = getAllChatgroupids();
		System.out.println(chatgroupidsNode.toString());

		/**
		 * ��ȡһ����߶��Ⱥ������� curlʾ�� curl -X GET -i
		 * "https://a1.easemob.com/easemob-playground/test1/chatgroups/1414379474926191,1405735927133519"
		 * -H "Authorization: Bearer {token}"
		 */
		String[] chatgroupIDs = { "1414379474926191", "1405735927133519" };
		ObjectNode groupDetailNode = getGroupDetailsByChatgroupid(chatgroupIDs);
		System.out.println(groupDetailNode.toString());

		/**
		 * ����Ⱥ�� curlʾ�� curl -X POST
		 * 'https://a1.easemob.com/easemob-playground/test1/chatgroups' -H
		 * 'Authorization: Bearer {token}' -d
		 * '{"groupname":"����Ⱥ��","desc":"����Ⱥ��","public":true,"approval":true,"owner":"xiaojianguo001","maxusers":333,"members":["xiaojianguo002","xiaojianguo
		 * 0 0 3 " ] } '
		 */
		ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
		dataObjectNode.put("groupname", "����Ⱥ��");
		dataObjectNode.put("desc", "����Ⱥ��");
		dataObjectNode.put("approval", true);
		dataObjectNode.put("public", true);
		dataObjectNode.put("maxusers", 333);
		dataObjectNode.put("owner", "xiaojianguo001");
		ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
		arrayNode.add("xiaojianguo002");
		arrayNode.add("xiaojianguo003");
		dataObjectNode.put("members", arrayNode);
		ObjectNode creatChatGroupNode = creatChatGroups(dataObjectNode);
		System.out.println(creatChatGroupNode.toString());

		/**
		 * ɾ��Ⱥ�� curlʾ�� curl -X DELETE
		 * 'https://a1.easemob.com/easemob-playground/test1/chatgroups/14057359271
		 * 3 3 5 1 9 ' -H 'Authorization: Bearer {token}'
		 */
		String toDelChatgroupid = "1405735927133519";
		ObjectNode deleteChatGroupNode = deleteChatGroups(toDelChatgroupid);
		System.out.println(deleteChatGroupNode.toString());

		/**
		 * ��ȡȺ���е����г�Ա curlʾ�� curl -X GET
		 * 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/
		 * u s e r s ' -H 'Authorization: Bearer {token}'
		 */
		String chatgroupid = "1405735927133519";
		ObjectNode getAllMemberssByGroupIdNode = getAllMemberssByGroupId(chatgroupid);
		System.out.println(getAllMemberssByGroupIdNode.toString());

		/**
		 * ��Ⱥ�������һ���� curlʾ�� curl -X POST
		 * 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/users/xiaojiang
		 * u o 0 0 2 ' -H 'Authorization: Bearer {token}'
		 */
		String addToChatgroupid = "90968535114711484";
		String toAddUsername = "xiaojianguo002";
		ObjectNode addUserToGroupNode = addUserToGroup(addToChatgroupid,
				toAddUsername);
		System.out.println(addUserToGroupNode.toString());

		/**
		 * ��Ⱥ���м���һ���� curlʾ�� curl -X DELETE
		 * 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/users/xiaojiang
		 * u o 0 0 2 ' -H 'Authorization: Bearer {token}'
		 */
		String delFromChatgroupid = "1405735927133519";
		String toRemoveUsername = "xiaojianguo002";
		ObjectNode deleteUserFromGroupNode = deleteUserFromGroup(
				delFromChatgroupid, toRemoveUsername);
		System.out.println(deleteUserFromGroupNode.asText());

		/**
		 * ��ȡһ���û����������Ⱥ�� curlʾ�� curl -X GET
		 * 'https://a1.easemob.com/easemob-playground/test1/users/xiaojianguo002/joined_chatg
		 * r o u p s ' -H 'Authorization: Bearer {token}'
		 */
		String username = "xiaojianguo002";
		ObjectNode getJoinedChatgroupsForIMUserNode = getJoinedChatgroupsForIMUser(username);
		System.out.println(getJoinedChatgroupsForIMUserNode.toString());

		/**
		 * Ⱥ������ӳ�Ա curlʾ�� curl -X POST -i
		 * 'https://a1.easemob.com/easemob-playground/test1/chatgroups/1405735927133519/
		 * u s e r s ' -H 'Authorization: Bearer {token}' -d
		 * '{"usernames":["xiaojianguo002","xiaojianguo003"]}'
		 */
		String toAddBacthChatgroupid = "1405735927133519";
		ArrayNode usernames = JsonNodeFactory.instance.arrayNode();
		usernames.add("xiaojianguo002");
		usernames.add("xiaojianguo003");
		ObjectNode usernamesNode = JsonNodeFactory.instance.objectNode();
		usernamesNode.put("usernames", usernames);
		ObjectNode addUserToGroupBatchNode = addUsersToGroupBatch(
				toAddBacthChatgroupid, usernamesNode);
		System.out.println(addUserToGroupBatchNode.toString());
	}

	public static void join(String userId) {
		String addToChatgroupid = "90968535114711484";
		// String toAddUsername = "xiaojianguo002";
		ObjectNode addUserToGroupNode = addUserToGroup(addToChatgroupid, userId);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("用户加入群聊：" + addUserToGroupNode.toString());
		}
	}

	/**
	 * ��ȡAPP�����е�Ⱥ��ID
	 * 
	 * 
	 * @return
	 */
	public static ObjectNode getAllChatgroupids() {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ��ȡһ����߶��Ⱥ�������
	 * 
	 * @return
	 */
	public static ObjectNode getGroupDetailsByChatgroupid(String[] chatgroupIDs) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.path(chatgroupIDs.toString());

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ����Ⱥ��
	 * 
	 */
	public static ObjectNode creatChatGroups(ObjectNode dataObjectNode) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (!dataObjectNode.has("groupname")) {
			LOGGER.error("Property that named groupname must be provided .");

			objectNode.put("message",
					"Property that named groupname must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("desc")) {
			LOGGER.error("Property that named desc must be provided .");

			objectNode.put("message",
					"Property that named desc must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("public")) {
			LOGGER.error("Property that named public must be provided .");

			objectNode.put("message",
					"Property that named public must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("approval")) {
			LOGGER.error("Property that named approval must be provided .");

			objectNode.put("message",
					"Property that named approval must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("owner")) {
			LOGGER.error("Property that named owner must be provided .");

			objectNode.put("message",
					"Property that named owner must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("members")
				|| !dataObjectNode.path("members").isArray()) {
			LOGGER.error("Property that named members must be provided .");

			objectNode.put("message",
					"Property that named members must be provided .");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode,
					credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ɾ��Ⱥ��
	 * 
	 */
	public static ObjectNode deleteChatGroups(String chatgroupid) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.path(chatgroupid);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_DELETE, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ��ȡȺ���е����г�Ա
	 * 
	 */
	public static ObjectNode getAllMemberssByGroupId(String chatgroupid) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.path(chatgroupid).path("users");

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ��Ⱥ�������һ����
	 * 
	 */
	public static ObjectNode addUserToGroup(String chatgroupid, String userName) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.path(chatgroupid).path("users").path(userName);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ��Ⱥ���м���һ����
	 * 
	 */
	public static ObjectNode deleteUserFromGroup(String chatgroupid,
			String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.path(chatgroupid).path("users").path(userName);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_DELETE, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ��ȡһ���û����������Ⱥ��
	 * 
	 * @param username
	 * @return
	 */
	private static ObjectNode getJoinedChatgroupsForIMUser(String username) {
		ObjectNode objectNode = factory.objectNode();
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		if (StringUtils.isBlank(username.trim())) {
			LOGGER.error("Property that named username must be provided .");
			objectNode.put("message",
					"Property that named username must be provided .");
			return objectNode;
		}

		try {
			objectNode = JerseyUtils.sendRequest(
					EndPoints.USERS_TARGET.path(username).path(
							"joined_chatgroups"), null, credential,
					HTTPMethod.METHOD_GET, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * Ⱥ������ӳ�Ա
	 * 
	 * @param toAddBacthChatgroupid
	 * @param usernames
	 * @return
	 */
	private static ObjectNode addUsersToGroupBatch(
			String toAddBacthChatgroupid, ObjectNode usernames) {
		ObjectNode objectNode = factory.objectNode();
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		if (StringUtils.isBlank(toAddBacthChatgroupid.trim())) {
			LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
			objectNode
					.put("message",
							"Property that named toAddBacthChatgroupid must be provided .");
			return objectNode;
		}
		// check properties that must be provided
		if (null != usernames && !usernames.has("usernames")) {
			LOGGER.error("Property that named usernames must be provided .");
			objectNode.put("message",
					"Property that named usernames must be provided .");
			return objectNode;
		}

		try {
			objectNode = JerseyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET
					.path(toAddBacthChatgroupid).path("users"), usernames,
					credential, HTTPMethod.METHOD_POST, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

}
