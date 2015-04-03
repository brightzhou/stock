package stock.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zeekie.stock.util.http.HandleHttpRequest;

public class TestPublishChannel {

	public static void main(String[] args) {

		publish();
	}

	public static void publish() {
		String url = "http://localhost:8080/sms-service/api/sms/publish.htm";
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < 10; i++) {
			JSONObject jo = new JSONObject();
			jo.put("msg", "我就是内容==" + i);
			jo.put("phone", "15867198578");

			jsonArray.add(jo);
		}
		HandleHttpRequest request = new HandleHttpRequest();

		Map<String, String> headers = new HashMap<String, String>();
		JSONObject item = new JSONObject();
		item.put("authType", "2");
//		try {
//			item.put("token", TokenUtils.encryptToken(
//					"02,6338673674855554,20150328155536,100015",
//					"servyou_sitong_s"));
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		} catch (CodecException e1) {
//			e1.printStackTrace();
//		}
		headers.put("user_auth", item.toString());
		request.setHeaders(headers);

		Map<String, String> map = new HashMap<String, String>();
		map.put("payloadPrivate", jsonArray.toString());
		try {
			request.post(url, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
