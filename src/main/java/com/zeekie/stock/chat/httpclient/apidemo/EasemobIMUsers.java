package com.zeekie.stock.chat.httpclient.apidemo;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zeekie.stock.chat.comm.Constants;
import com.zeekie.stock.chat.comm.HTTPMethod;
import com.zeekie.stock.chat.comm.Roles;
import com.zeekie.stock.chat.httpclient.utils.HTTPClientUtils;
import com.zeekie.stock.chat.httpclient.vo.EndPoints;
import com.zeekie.stock.chat.httpclient.vo.ClientSecretCredential;
import com.zeekie.stock.chat.httpclient.vo.Credential;

/**
 * REST API Demo :�û���ϵ���� REST API HttpClient4.3ʵ��
 * 
 * Doc URL: http://www.easemob.com/docs/rest/userapi
 * 
 * @author Lynch 2014-09-15
 * 
 */
public class EasemobIMUsers {

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobIMUsers.class);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    // ͨ��app��client_id��client_secret4��ȡapp����Աtoken
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    public static void main(String[] args) {
        /**
         * ע��IM�û�[����]
         */
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username","kenshinnuser100");
        datanode.put("password", Constants.DEFAULT_PASSWORD);
        ObjectNode createNewIMUserSingleNode = createNewIMUserSingle(datanode);
        if (null != createNewIMUserSingleNode) {
            LOGGER.info("ע��IM�û�[����]: " + createNewIMUserSingleNode.toString());
        }

        /**
         * IM�û���¼
         */
        ObjectNode imUserLoginNode = imUserLogin(datanode.get("username").asText(), datanode.get("password").asText());
        if (null != imUserLoginNode) {
            LOGGER.info("IM�û���¼: " + imUserLoginNode.toString());
        }

        /**
         * ע��IM�û�[������û�Ȼ��ע��]
         */
        String usernamePrefix = "kenshinnuser";
        Long perNumber = 10l;
        Long totalNumber = 100l;
        ObjectNode createNewIMUserBatchGenNode = createNewIMUserBatchGen(usernamePrefix, perNumber, totalNumber);
        if (null != createNewIMUserBatchGenNode) {
            LOGGER.info("ע��IM�û�[��]: " + createNewIMUserBatchGenNode.toString());
        }

        /**
         * ��ȡIM�û�[����ѯ]
         */
        String userName = "kenshinnuser100";
        ObjectNode getIMUsersByUserNameNode = getIMUsersByUserName(userName);
        if (null != getIMUsersByUserNameNode) {
            LOGGER.info("��ȡIM�û�[����ѯ]: " + getIMUsersByUserNameNode.toString());
        }

        /**
         * ����IM�û����� �ṩ����Աtoken
         */
		String username = "kenshinnuser100";
        ObjectNode json2 = JsonNodeFactory.instance.objectNode();
        json2.put("newpassword", Constants.DEFAULT_PASSWORD);
        ObjectNode modifyIMUserPasswordWithAdminTokenNode = modifyIMUserPasswordWithAdminToken(username, json2);
        if (null != modifyIMUserPasswordWithAdminTokenNode) {
            LOGGER.info("����IM�û����� �ṩ����Աtoken: " + modifyIMUserPasswordWithAdminTokenNode.toString());
        }
        ObjectNode imUserLoginNode2 = imUserLogin(username, json2.get("newpassword").asText());
        if (null != imUserLoginNode2) {
            LOGGER.info("����IM�û������,IM�û���¼: " + imUserLoginNode2.toString());
        }

        /**
         * ��Ӻ���[����]
         */
        String ownerUserName = username;
        String friendUserName = "kenshinnuser099";
        ObjectNode addFriendSingleNode = addFriendSingle(ownerUserName, friendUserName);
        if (null != addFriendSingleNode) {
            LOGGER.info("��Ӻ���[����]: " + addFriendSingleNode.toString());
        }

        /**
         * �鿴����
         */
        ObjectNode getFriendsNode = getFriends(ownerUserName);
        if (null != getFriendsNode) {
            LOGGER.info("�鿴����: " + getFriendsNode.toString());
        }

        /**
         * �����ѹ�ϵ
         **/
        ObjectNode deleteFriendSingleNode = deleteFriendSingle(ownerUserName, friendUserName);
        if (null != deleteFriendSingleNode) {
            LOGGER.info("�����ѹ�ϵ: " + deleteFriendSingleNode.toString());
        }

        /**
         * ɾ��IM�û�[����]
         */
        ObjectNode deleteIMUserByuserNameNode = deleteIMUserByuserName(userName);
        if (null != deleteIMUserByuserNameNode) {
            LOGGER.info("ɾ��IM�û�[����]: " + deleteIMUserByuserNameNode.toString());
        }

        /**
         * ɾ��IM�û�[��]
         */
        Long limit = 2l;
        ObjectNode deleteIMUserByUsernameBatchNode = deleteIMUserByUsernameBatch(limit);
        if (null != deleteIMUserByUsernameBatchNode) {
            LOGGER.info("ɾ��IM�û�[��]: " + deleteIMUserByUsernameBatchNode.toString());
        }
    }

    /**
	 * ע��IM�û�[����]
	 * 
	 * ��ָ��Constants.APPKEY����һ���µ��û�
	 * 
	 * @param dataNode
	 * @return
	 */
	public static ObjectNode createNewIMUserSingle(ObjectNode dataNode) {

		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		objectNode.removeAll();

		// check properties that must be provided
		if (null != dataNode && !dataNode.has("username")) {
			LOGGER.error("Property that named username must be provided .");

			objectNode.put("message", "Property that named username must be provided .");

			return objectNode;
		}
		if (null != dataNode && !dataNode.has("password")) {
			LOGGER.error("Property that named password must be provided .");

			objectNode.put("message", "Property that named password must be provided .");

			return objectNode;
		}

		try {

		    objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, credential, dataNode,
					HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ע��IM�û�[��]
	 * 
	 * ��ָ��Constants.APPKEY����һ���û�
	 * 
	 * @param dataArrayNode
	 * @return
	 */
	public static ObjectNode createNewIMUserBatch(ArrayNode dataArrayNode) {

		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		// check properties that must be provided
		if (dataArrayNode.isArray()) {
			for (JsonNode jsonNode : dataArrayNode) {
				if (null != jsonNode && !jsonNode.has("username")) {
					LOGGER.error("Property that named username must be provided .");

					objectNode.put("message", "Property that named username must be provided .");

					return objectNode;
				}
				if (null != jsonNode && !jsonNode.has("password")) {
					LOGGER.error("Property that named password must be provided .");

					objectNode.put("message", "Property that named password must be provided .");

					return objectNode;
				}
			}
		}

		try {

			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, credential, dataArrayNode,
					HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ע��IM�û�[������û�Ȼ��ע��]
	 * 
	 * ��ָ��Constants.APPKEY����һ���û�
	 * 
	 * @param usernamePrefix
	 *            ����û����ǰ׺
	 * @param perNumber
	 *            ��ע��ʱһ��ע�����
	 * @param totalNumber
	 *            ����û�ע����û�����
	 * @return
	 */
	public static ObjectNode createNewIMUserBatchGen(String usernamePrefix, Long perNumber, Long totalNumber) {
		ObjectNode objectNode = factory.objectNode();

		if (totalNumber == 0 || perNumber == 0) {
			return objectNode;
		}
		System.out.println("�㼴��ע��" + totalNumber + "���û���������" + perNumber + "��,�����ע��,ÿ��ע��" + perNumber + "��");
		ArrayNode genericArrayNode = EasemobIMUsers.genericArrayNode(usernamePrefix, totalNumber);
		if (totalNumber <= perNumber) {
			objectNode = EasemobIMUsers.createNewIMUserBatch(genericArrayNode);
		} else {

			ArrayNode tmpArrayNode = factory.arrayNode();
			
			for (int i = 0; i < genericArrayNode.size(); i++) {
				tmpArrayNode.add(genericArrayNode.get(i));
				// 300 records on one migration
				if ((i + 1) % perNumber == 0) {
					objectNode = EasemobIMUsers.createNewIMUserBatch(tmpArrayNode);
					if(objectNode!=null){
						LOGGER.info("ע��IM�û�[��]: " + objectNode.toString());
					}
					tmpArrayNode.removeAll();
					continue;
				}

				// the rest records that less than the times of 300
				if (i > (genericArrayNode.size() / perNumber * perNumber - 1)) {
					objectNode = EasemobIMUsers.createNewIMUserBatch(tmpArrayNode);
					if(objectNode!=null){
						LOGGER.info("ע��IM�û�[��]: " + objectNode.toString());
					}
					tmpArrayNode.removeAll();
				}
			}
		}

		return objectNode;
	}

	/**
	 * ��ȡIM�û�
	 * 
	 * @param userName
	 *            �û����username����uuid
	 * @return
	 */
	public static ObjectNode getIMUsersByUserName(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("The userName that will be used to query must be provided .");

			objectNode.put("message", "The userName that will be used to query must be provided .");

			return objectNode;
		}

		try {

			URL userPrimaryUrl = HTTPClientUtils
					.getURL(Constants.APPKEY.replace("#", "/") + "/users/" + userName);
			objectNode = HTTPClientUtils.sendHTTPRequest(userPrimaryUrl, credential, null, HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ɾ��IM�û�[����]
	 * 
	 * ɾ��ָ��Constants.APPKEY��IM�����û�
	 *
	 * 
	 * @param userName
	 * @return
	 */
	public static ObjectNode deleteIMUserByuserName(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		try {

			URL deleteUserPrimaryUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/"
					+ userName);
			objectNode = HTTPClientUtils.sendHTTPRequest(deleteUserPrimaryUrl, credential, null,
					HTTPMethod.METHOD_DELETE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ɾ��IM�û�[��]
	 * 
	 * ��ָ��Constants.APPKEY��ɾ��IM�û�
	 * 
	 * @param limit
	 * @param queryStr
	 * @return
	 */
	public static ObjectNode deleteIMUserByUsernameBatch(Long limit) {

		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		try {

			URL deleteIMUserByUsernameBatchUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users"
					+ "?limit=" + limit);
			objectNode = HTTPClientUtils.sendHTTPRequest(deleteIMUserByUsernameBatchUrl, credential, null,
					HTTPMethod.METHOD_DELETE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ����IM�û����� �ṩ����Աtoken
	 * 
	 * @param userName
	 * @param dataObjectNode
	 * @return
	 */
	public static ObjectNode modifyIMUserPasswordWithAdminToken(String userName, ObjectNode dataObjectNode) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Property that named userName must be provided��the value is username of imuser.");

			objectNode.put("message",
					"Property that named userName must be provided��the value is username or imuser.");

			return objectNode;
		}

		if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
			LOGGER.error("Property that named newpassword must be provided .");

			objectNode.put("message", "Property that named newpassword must be provided .");

			return objectNode;
		}

		try {
			URL modifyIMUserPasswordWithAdminTokenUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/")
					+ "/users/" + userName + "/password");
			objectNode = HTTPClientUtils.sendHTTPRequest(modifyIMUserPasswordWithAdminTokenUrl, credential,
					dataObjectNode, HTTPMethod.METHOD_PUT);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}
	
	/**
	 * ��Ӻ���[����]
	 * 
	 * @param ownerUserName
	 * @param friendUserName
	 * 
	 * @return
	 */
	public static ObjectNode addFriendSingle(String ownerUserName, String friendUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided��the value is username of imuser.");

			objectNode.put("message", "Your userName must be provided��the value is username of imuser.");

			return objectNode;
		}

		if (StringUtils.isEmpty(friendUserName)) {
			LOGGER.error("The userName of friend must be provided��the value is username of imuser.");

			objectNode.put("message",
					"The userName of friend must be provided��the value is username of imuser.");

			return objectNode;
		}

		try {

			URL addFriendSingleUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/"
					+ ownerUserName + "/contacts/users/" + friendUserName);

			ObjectNode body = factory.objectNode();
			objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl, credential, body, HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}
	
	/**
	 * ɾ�����
	 * 
	 * @param ownerUserName
	 * @param friendUserName
	 * 
	 * @return
	 */
	public static ObjectNode deleteFriendSingle(String ownerUserName, String friendUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided��the value is username of imuser.");

			objectNode.put("message", "Your userName must be provided��the value is username of imuser.");

			return objectNode;
		}

		if (StringUtils.isEmpty(friendUserName)) {
			LOGGER.error("The userName of friend must be provided��the value is username of imuser.");

			objectNode.put("message",
					"The userName of friend must be provided��the value is username of imuser.");

			return objectNode;
		}

		try {
			URL addFriendSingleUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/"
					+ ownerUserName + "/contacts/users/" + friendUserName);

			ObjectNode body = factory.objectNode();
			objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl, credential, body, HTTPMethod.METHOD_DELETE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}
	
	/**
	 * ��ȡ�û����к���
	 * 
	 * @param ownerUserName
	 * 
	 * @return
	 */
	public static ObjectNode getFriends(String ownerUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided��the value is username of imuser.");

			objectNode.put("message", "Your userName must be provided��the value is username of imuser.");

			return objectNode;
		}

		try {
			
			URL addFriendSingleUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/"
					+ ownerUserName + "/contacts/users");

			ObjectNode body = factory.objectNode();
			objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl, credential, body, HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * IM�û���¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static ObjectNode imUserLogin(String username, String password) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}
		if (StringUtils.isEmpty(username)) {
			LOGGER.error("Your username must be provided��the value is username of imuser.");

			objectNode.put("message", "Your username must be provided��the value is username of imuser.");

			return objectNode;
		}
		if (StringUtils.isEmpty(password)) {
			LOGGER.error("Your password must be provided��the value is username of imuser.");

			objectNode.put("message", "Your password must be provided��the value is username of imuser.");

			return objectNode;
		}

		try {
			ObjectNode dataNode = factory.objectNode();
			dataNode.put("grant_type", "password");
			dataNode.put("username", username);
			dataNode.put("password", password);

			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.TOKEN_APP_URL, null, dataNode,
					HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			throw new RuntimeException("Some errors occurred while fetching a token by username and password .");
		}

		return objectNode;
	}

	/*************************************************************************************************************************/
	/**
	 * ָ��ǰ׺��������û������
	 * 
	 * @param usernamePrefix
	 * @param number
	 * @return
	 */
	public static ArrayNode genericArrayNode(String usernamePrefix, Long number) {
		ArrayNode arrayNode = factory.arrayNode();
		for (int i = 0; i < number; i++) {
			ObjectNode userNode = factory.objectNode();
			userNode.put("username", usernamePrefix + "_" + i);
			userNode.put("password", Constants.DEFAULT_PASSWORD);

			arrayNode.add(userNode);
		}

		return arrayNode;
	}

}
