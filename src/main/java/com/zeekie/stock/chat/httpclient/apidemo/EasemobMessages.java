package com.zeekie.stock.chat.httpclient.apidemo;

import java.io.File;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * REST API Demo: ������Ϣ REST API HttpClient4.3ʵ��
 * 
 * Doc URL: http://www.easemob.com/docs/rest/sendmessage/
 * 
 * @author Lynch 2014-09-15
 *
 */
public class EasemobMessages {

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobMessages.class);
    private static final String APPKEY = Constants.APPKEY;
    private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    // ͨ��app��client_id��client_secret4��ȡapp����Աtoken
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    public static void main(String[] args) {
        //  ����û��Ƿ�����
        String targetUserName = "kenshinn";
        ObjectNode usernode = getUserStatus(targetUserName);
        if (null != usernode) {
            LOGGER.info("����û��Ƿ�����: " + usernode.toString());
        }

        // ���û���һ���ı���Ϣ
        String from = "kenshinnuser000";
        String targetTypeus = "users";
        ObjectNode ext = factory.objectNode();
        ArrayNode targetusers = factory.arrayNode();
        targetusers.add("kenshinnuser001");
        targetusers.add("kenshinnuser002");
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", "Hello Easemob!");
        txtmsg.put("type","txt");
        ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus, targetusers, txtmsg, from, ext);
        if (null != sendTxtMessageusernode) {
            LOGGER.info("���û���һ���ı���Ϣ: " + sendTxtMessageusernode.toString());
        }
        // ��һ��Ⱥ�鷢�ı���Ϣ
        String targetTypegr = "chatgroups";
        ArrayNode  chatgroupidsNode = (ArrayNode) EasemobChatGroups.getAllChatgroupids().path("data");
        ArrayNode targetgroup = factory.arrayNode();
        targetgroup.add(chatgroupidsNode.get(0).path("groupid").asText());
        ObjectNode sendTxtMessagegroupnode = sendMessages(targetTypegr, targetgroup, txtmsg, from, ext);
        if (null != sendTxtMessagegroupnode) {
            LOGGER.info("��һ��Ⱥ�鷢�ı���Ϣ: " + sendTxtMessagegroupnode.toString());
        }

        // ���û���һ��ͼƬ��Ϣ
        File uploadImgFile = new File("/home/lynch/Pictures/24849.jpg");
        ObjectNode imgDataNode = EasemobFiles.mediaUpload(uploadImgFile);
        if (null != imgDataNode) {
            String imgFileUUID = imgDataNode.path("entities").get(0).path("uuid").asText();
            String shareSecret = imgDataNode.path("entities").get(0).path("share-secret").asText();

            LOGGER.info("�ϴ�ͼƬ�ļ�: " + imgDataNode.toString());

            ObjectNode imgmsg = factory.objectNode();
            imgmsg.put("type","img");
            imgmsg.put("url", HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatfiles/" + imgFileUUID).toString());
            imgmsg.put("filename", "24849.jpg");
            imgmsg.put("length", 10);
            imgmsg.put("secret", shareSecret);
            ObjectNode sendimgMessageusernode = sendMessages(targetTypeus, targetusers, imgmsg, from, ext);
            if (null != sendimgMessageusernode) {
                LOGGER.info("��һ��Ⱥ�鷢�ı���Ϣ: " + sendimgMessageusernode.toString());
            }
            // ��һ��Ⱥ�鷢ͼƬ��Ϣ
            ObjectNode sendimgMessagegroupnode = sendMessages(targetTypegr, targetgroup, imgmsg, from, ext);
            if (null != sendimgMessagegroupnode) {
                LOGGER.info("��һ��Ⱥ�鷢�ı���Ϣ: " + sendimgMessagegroupnode.toString());
            }

        }
        // ���û���һ��������Ϣ
        File uploadAudioFile = new File("/home/lynch/Music/music.MP3");
        ObjectNode audioDataNode = EasemobFiles.mediaUpload(uploadAudioFile);
        if (null != audioDataNode) {
            String audioFileUUID = audioDataNode.path("entities").get(0).path("uuid").asText();
            String audioFileShareSecret = audioDataNode.path("entities").get(0).path("share-secret").asText();

            LOGGER.info("�ϴ������ļ�: " + audioDataNode.toString());

            ObjectNode audiomsg = factory.objectNode();
            audiomsg.put("type","audio");
            audiomsg.put("url", HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatfiles/" + audioFileUUID).toString());
            audiomsg.put("filename", "music.MP3");
            audiomsg.put("length", 10);
            audiomsg.put("secret", audioFileShareSecret);
            ObjectNode sendaudioMessageusernode = sendMessages(targetTypeus, targetusers, audiomsg, from, ext);
            if (null != sendaudioMessageusernode) {
                LOGGER.info("���û���һ��������Ϣ: " + sendaudioMessageusernode.toString());
            }

            // ��һ��Ⱥ�鷢������Ϣ
            ObjectNode sendaudioMessagegroupnode = sendMessages(targetTypegr, targetgroup, audiomsg, from, ext);
            if (null != sendaudioMessagegroupnode) {
                LOGGER.info("��һ��Ⱥ�鷢������Ϣ: " + sendaudioMessagegroupnode.toString());
            }
        }
        // ���û���һ��͸����Ϣ
        ObjectNode cmdmsg = factory.objectNode();
        cmdmsg.put("action", "gogogo");
        cmdmsg.put("type","cmd");
        ObjectNode sendcmdMessageusernode = sendMessages(targetTypeus, targetusers, cmdmsg, from, ext);
        if (null != sendcmdMessageusernode) {
            LOGGER.info("���û���һ��͸����Ϣ: " + sendcmdMessageusernode.toString());
        }
    }

	/**
	 * ����û��Ƿ�����
	 * 
	 * @param username
     *
	 * @return
	 */
	public static ObjectNode getUserStatus(String username) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(username)) {
			LOGGER.error("You must provided a targetUserName .");
			objectNode.put("message", "You must provided a targetUserName .");
			return objectNode;
		}

		try {
			URL userStatusUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/"
					+ username + "/status");
			objectNode = HTTPClientUtils.sendHTTPRequest(userStatusUrl, credential, null, HTTPMethod.METHOD_GET);
			String userStatus = objectNode.get("data").path(username).asText();
			if ("online".equals(userStatus)) {
				LOGGER.error(String.format("The status of user[%s] is : [%s] .", username, userStatus));
			} else if ("offline".equals(userStatus)) {
				LOGGER.error(String.format("The status of user[%s] is : [%s] .", username, userStatus));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * ������Ϣ
	 * 
	 * @param targetType
	 *            ��ϢͶ�������ͣ�users �û�, chatgroups Ⱥ��
	 * @param target
	 *            ������ID ����������,����Ԫ��Ϊ�û�ID����Ⱥ��ID
	 * @param msg
	 *            ��Ϣ����
	 * @param from
	 *            ������
	 * @param ext
	 *            )չ�ֶ�
	 * 
	 * @return ������Ӧ
	 */
	public static ObjectNode sendMessages(String targetType, ArrayNode target, ObjectNode msg, String from,
			ObjectNode ext) {

		ObjectNode objectNode = factory.objectNode();

		ObjectNode dataNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (!("users".equals(targetType) || "chatgroups".equals(targetType))) {
			LOGGER.error("TargetType must be users or chatgroups .");

			objectNode.put("message", "TargetType must be users or chatgroups .");

			return objectNode;
		}

		try {
			// ������Ϣ��
			dataNode.put("target_type", targetType);
			dataNode.put("target", target);
			dataNode.put("msg", msg);
			dataNode.put("from", from);
			dataNode.put("ext", ext);

			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.MESSAGES_URL, credential, dataNode,
					HTTPMethod.METHOD_POST);

			objectNode = (ObjectNode) objectNode.get("data");
			for (int i = 0; i < target.size(); i++) {
				String resultStr = objectNode.path(target.path(i).asText()).asText();
				if ("success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] successfully .", target.path(i)
							.asText()));
				} else if (!"success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] failed .", target.path(i).asText()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

}
