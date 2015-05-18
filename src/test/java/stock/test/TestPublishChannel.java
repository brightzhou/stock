package stock.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.json.JSONException;

//import sitong.thinker.common.util.codec.CodecException;
//import sitong.thinker.common.util.codec.TokenUtils;

import com.zeekie.stock.util.http.HandleHttpRequest;

public class TestPublishChannel {

	public static void main(String[] args) {

//		publish();
//		publishCommon();
//		 getdata();
//		 getMaxBytes();
		// getTime();
	}

	public static void getTime() {
		HandleHttpRequest req = new HandleHttpRequest();
		try {
			String result = req.post(
					"http://localhost:8080/sms-service/api/getServerTime.htm",
					new HashMap<String, String>());
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void getMaxBytes() {
//		HandleHttpRequest req = new HandleHttpRequest();
//		Map<String, String> headers = new HashMap<String, String>();
//		JSONObject item = new JSONObject();
//		item.put("authType", "2");
//		try {
//			item.put("token", TokenUtils.encryptToken(
//					"02,6338673674855554,20150429165536,100015",
//					"servyou_sitong_s"));
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		} catch (CodecException e1) {
//			e1.printStackTrace();
//		}
//		headers.put("user_auth", item.toString());
//		req.setHeaders(headers);
//		try {
//			String result = req
//					.post("http://localhost:8080/sms-service/api/sms/query/getMaxBytes.htm",
//							new HashMap<String, String>());
//			System.out.println(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void getdata() {
//		String url = "http://localhost:8080/sms-service/api/sms/getResultByBatch.htm";
//		HandleHttpRequest request = new HandleHttpRequest();
//
//		Map<String, String> headers = new HashMap<String, String>();
//		JSONObject item = new JSONObject();
//		item.put("authType", "2");
//		try {
//			item.put("token", TokenUtils.encryptToken(
//					"02,6338673674855554,20150430185036,100015",
//					"servyou_sitong_s"));
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		} catch (CodecException e1) {
//			e1.printStackTrace();
//		}
//		headers.put("user_auth", item.toString());
//		request.setHeaders(headers);
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("batchId", "0bde4973256c446fbe8e625df82ce5bc");
//		map.put("offset", "1");
//		try {
//			System.out.println(request.post(url, map));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void publishCommon() {
//		// String url =
//		// "http://192.168.150.56:8000/sms-service/api/sms/publish.htm";
//		String url = "http://localhost:8080/sms-service/api/sms/publish.htm";
//
//		JSONObject main = new JSONObject();
//
//		JSONArray jsonArray = new JSONArray();
//		jsonArray.add("尊敬的李昭");
//		main.put("messages", jsonArray);
//
//		jsonArray.clear();
//		JSONObject jo1 = new JSONObject();
//		jo1.put("phone", "15867198578");
//		jsonArray.add(jo1);
//		jo1.clear();
//		jo1.put("phone", "18658169008");
//		jsonArray.add(jo1);
//
//		main.put("receivers", jsonArray);
//
//		HandleHttpRequest request = new HandleHttpRequest();
//
//		Map<String, String> headers = new HashMap<String, String>();
//		JSONObject item = new JSONObject();
//		item.put("authType", "2");
//
//		try {
//			item.put("token", TokenUtils.encryptToken(
//					"02,6338673674855554,20150430155500,100015",
//					"servyou_sitong_s"));
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		} catch (CodecException e1) {
//			e1.printStackTrace();
//		}
//
//		headers.put("user_auth", item.toString());
//		request.setHeaders(headers);
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("payloadCommon", main.toString());
//		try {
//			System.out.println(request.post(url, map));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void publish() {
//		// String url =
//		// "http://192.168.150.56:8000/sms-service/api/sms/publish.htm";
//		String url = "http://localhost:8080/sms-service/api/sms/publish.htm";
//		JSONArray jsonArray = new JSONArray();
//		for (int i = 0; i < 1; i++) {
//			JSONObject jo = new JSONObject();
//			jo.put("msg", "尊敬的李昭" + i);
//			jo.put("phone", "15867198578");
//			jsonArray.add(jo);
//		}
//
//		JSONObject jo = new JSONObject();
//		jo.put("msg", "尊敬的黄田");
//		jo.put("phone", "18658169008");
//		jsonArray.add(jo);
//
//		HandleHttpRequest request = new HandleHttpRequest();
//
//		Map<String, String> headers = new HashMap<String, String>();
//		JSONObject item = new JSONObject();
//		item.put("authType", "2");
//
//		try {
//			item.put("token", TokenUtils.encryptToken(
//					"02,6338673674855554,20150430151500,100015",
//					"servyou_sitong_s"));
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		} catch (CodecException e1) {
//			e1.printStackTrace();
//		}
//
//		headers.put("user_auth", item.toString());
//		request.setHeaders(headers);
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("payloadPrivate", jsonArray.toString());
//		try {
//			System.out.println(request.post(url, map));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
