package stock.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import sitong.thinker.common.util.codec.CodecException;
import sitong.thinker.common.util.codec.TokenUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zeekie.stock.util.http.HandleHttpRequest;

public class TestPublishChannel {

	public static void main(String[] args) {

		publish();
//		getdata();
//		getMaxBytes();
	}
	
	public static void getMaxBytes() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> headers = new HashMap<String, String>();
		JSONObject item = new JSONObject();
		item.put("authType", "2");
		try {
			item.put("token", TokenUtils.encryptToken(
					"02,6338673674855554,20150403165536,100015",
					"servyou_sitong_s"));
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (CodecException e1) {
			e1.printStackTrace();
		}
		headers.put("user_auth", item.toString());
		req.setHeaders(headers);
		try {
			String result = req
					.post("http://localhost:8080/sms-service/api/sms/query/getMaxBytes.htm",
							new HashMap<String, String>());
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getdata(){
		String url = "http://localhost:8080/sms-service/api/sms/getResultByBatch.htm";
		HandleHttpRequest request = new HandleHttpRequest();

		Map<String, String> headers = new HashMap<String, String>();
		JSONObject item = new JSONObject();
		item.put("authType", "2");
		try {
			item.put("token", TokenUtils.encryptToken(
					"02,6338673674855554,20150401185036,100015",
					"servyou_sitong_s"));
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (CodecException e1) {
			e1.printStackTrace();
		}
		headers.put("user_auth", item.toString());
		request.setHeaders(headers);

		Map<String, String> map = new HashMap<String, String>();
		map.put("batchId", "77a78036d0f146138627db8669ffce55");
		map.put("offset", "0");
		try {
			System.out.println(request.post(url, map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void publish() {
		String url = "http://localhost:8080/sms-service/api/sms/publish.htm";
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < 3; i++) {
			JSONObject jo = new JSONObject();
			jo.put("msg", "摇一摇" + i);
			jo.put("phone", "15867198578");
			jsonArray.add(jo);
		}
		
		JSONObject jo = new JSONObject();
		jo.put("msg", "错的1");
		jo.put("phone", "158671");
		jsonArray.add(jo);
		
		jo.put("msg", "错的2");
		jo.put("phone", "5656");
		jsonArray.add(jo);
		
		jo.put("msg", "黄田");
		jo.put("phone", "18658169008");
		jsonArray.add(jo);
		
		HandleHttpRequest request = new HandleHttpRequest();

		Map<String, String> headers = new HashMap<String, String>();
		JSONObject item = new JSONObject();
		item.put("authType", "2");
		try {
			item.put("token", TokenUtils.encryptToken(
					"02,6338673674855554,20150402193536,100015",
					"servyou_sitong_s"));
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (CodecException e1) {
			e1.printStackTrace();
		}
		headers.put("user_auth", item.toString());
		request.setHeaders(headers);

		Map<String, String> map = new HashMap<String, String>();
		map.put("payloadPrivate", jsonArray.toString());
		try {
			System.out.println(request.post(url, map));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
