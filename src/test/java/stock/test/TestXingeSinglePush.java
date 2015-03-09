package stock.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.TagTokenPair;
import com.tencent.xinge.XingeApp;

/**
 * @Author zeekie
 * @date 2014��8��5��
 */
public class TestXingeSinglePush {

	// // �·������˺�
	protected static JSONObject demoPushSingleAccount(String nsrsbh,
			String content) {
		XingeApp xinge = new XingeApp(2100079370,
				"dcb9ef3ca073a484de3631d9e26dbb28");
		Message message = new Message();
		message.setTitle("title");
		message.setContent(content);
		message.setType(Message.TYPE_MESSAGE);
		message.setSendTime("2014-08-06 10:55:10");
		JSONObject ret = xinge.pushSingleAccount(XingeApp.DEVICE_ANDROID,
				nsrsbh, message);
		return (ret);
	}

	//
	// �·������˺�
	protected static JSONObject demoIOSPushSingleAccount() {
		XingeApp xinge = new XingeApp(Long.parseLong("2100079370"),
				"ad4cba4ed5e3db17c7f6f1c894302791");
		MessageIOS ios = new MessageIOS();
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put("content", "��������");
		custom.put("cv", "��������");
		custom.put("wew", "��������");
		JSONObject jo = new JSONObject();
		jo.put("565", "���������");
		jo.put("dfd", "��������sdsdsdsd���ķ��ķ�");
		jo.put("erer", "��������");
		jo.put("erer", "��������");
		jo.put("erer", "��������");
		jo.put("erer", "��������");
		jo.put("erer", "��������");
		jo.put("content", "��������dfdfdf�ķ��ĸ��ķ����ķ��Է��Է��ķ��ķ��ķ�");
		custom.put("other", jo.toString());
		ios.setCustom(custom);
		ios.setAlert("test");
		ios.setBadge(1);
		ios.setSound("beep.wav");
		JSONObject ret = xinge.pushSingleAccount(XingeApp.DEVICE_IOS,
				"652701580232659", ios, XingeApp.IOSENV_DEV);
		return (ret);
	}

	protected static JSONObject demoPushTags() {
		XingeApp xinge = new XingeApp(Long.parseLong("2200043529"),
				"ad4cba4ed5e3db17c7f6f1c894302791");
		List<String> tagList = new ArrayList<String>();
		MessageIOS ios = new MessageIOS();
		tagList.add("16501000000");
		tagList.add("2100");
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put("content", "��������");
		custom.put("dfd", "��������");
		custom.put("erer", "��������");
		custom.put("cv", "��������");
		custom.put("wew", "��������");
		custom.put("hgh", "��������");
		custom.put("wew", "��������");
		ios.setCustom(custom);
		ios.setAlert("�����title");
		ios.setBadge(1);
		ios.setSound("beep.wav");
		ios.setExpireTime(259200);
		JSONObject ret = xinge.pushTags(XingeApp.DEVICE_IOS, tagList, "AND",
				ios, 2);
		return (ret);
	}

	
	protected static JSONObject demoPushAllService() {
		XingeApp xinge = new XingeApp(2100079370,
				"dcb9ef3ca073a484de3631d9e26dbb28");
		Message message = new Message();
		message.setTitle("泽初傻逼");
		message.setContent("傻逼傻逼");
		message.setType(Message.TYPE_MESSAGE);
//		message.setSendTime("2014-08-06 18:17:00");
//		message.setExpireTime(120);
		JSONObject ret = xinge.pushAllDevice(XingeApp.DEVICE_ALL,message);
		return (ret);
	}
	
	protected static JSONObject demoPushTags1() {
		XingeApp xinge = new XingeApp(2100042697,
				"a6c34c8e082bae24089b4d08a72b31a6");
		List<String> tagList = new ArrayList<String>();
		tagList.add("all");
//		tagList.add("0400");
//		tagList.add("2");
		Message message = new Message();
		message.setTitle("DFDFDF");
		message.setContent("的反对反对法");
		message.setType(Message.TYPE_MESSAGE);
//		message.setSendTime("2014-08-06 18:17:00");
//		message.setExpireTime(120);
		JSONObject ret = xinge.pushTags(XingeApp.DEVICE_ANDROID, tagList,
				"OR", message);
		return (ret);
	}
	

	protected static JSONObject demoQueryTokenTags() {
		XingeApp xinge = new XingeApp(2100040311,
				"a1b77b14d17dd407b772fc0f3bc99587");
		JSONObject ret = xinge
				.queryTokenTags("07603761b0d224f040df84b35fef7e68c111f986");
		return (ret);
	}

	protected static JSONObject demoQueryTags() {
		XingeApp xinge = new XingeApp(Long.parseLong("2100042697"),
				"a6c34c8e082bae24089b4d08a72b31a6");
		JSONObject ret = xinge.queryTags();
		return (ret);
	}

	protected static JSONObject queryTokenTags() {
		XingeApp xinge = new XingeApp(Long.parseLong("2100044160"),
				"983c5066dec7778efd67b647ffb2c62c");
		JSONObject ret = xinge
				.queryTokenTags("07603761b0d224f040df84b35fef7e68c111f986");
		return (ret);
	}

	protected static JSONObject BatchDelTag() {
		XingeApp xinge = new XingeApp(Long.parseLong("2100044160"),
				"983c5066dec7778efd67b647ffb2c62c");
		List<TagTokenPair> li = new ArrayList<TagTokenPair>();
		// TagTokenPair pair = new TagTokenPair("00000000000",
		// "07603761b0d224f040df84b35fef7e68c111f986");
		TagTokenPair pair1 = new TagTokenPair("26500000000",
				"07603761b0d224f040df84b35fef7e68c111f986");
		TagTokenPair pair2 = new TagTokenPair("nsrlxDm_",
				"07603761b0d224f040df84b35fef7e68c111f986");
		// TagTokenPair pair3 = new TagTokenPair("swjgdm_00000000000",
		// "07603761b0d224f040df84b35fef7e68c111f986");
		// li.add(pair);
		li.add(pair1);
		li.add(pair2);
		// li.add(pair3);
		JSONObject ret = xinge.BatchDelTag(li);
		return (ret);
	}

	public static void main(String[] args) {
		// int i = 1;
		// // long start = Calendar.getInstance().getTimeInMillis();
		// while (true) {
		// i++;
		// if (i == 2) {
		// break;
		// }
		// }
//		JSONObject jo = demoPushSingleAccount("zeekie", "��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
//		System.out.println(jo.toString());
		// System.out.println("���ʱ�䣺" +
		// (Calendar.getInstance().getTimeInMillis() - start));

		 System.out.println(demoPushAllService().toString());

//		 System.out.println(demoQueryTags().toString());
		// System.out.println(queryTokenTags().toString());
		// System.out.println(BatchDelTag().toString());
		// System.out.println(queryTokenTags().toString());
		// System.out.println(demoPushTags().toString());
		// System.out.println(new String(Base64.decode("VLo11Rw")));
		// Long f = new Long("2200043529");
		// System.out.println(f);

	}

}
