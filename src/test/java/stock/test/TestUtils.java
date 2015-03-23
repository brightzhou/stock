package stock.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.CryptoUtils;
import com.zeekie.stock.util.XmlUtil;

public class TestUtils {

	public static void main(String[] args) {

		// test();
		// DecimalFormat df = new DecimalFormat();
		// df.setMaximumFractionDigits(3);
		// df.setRoundingMode(RoundingMode.HALF_UP);
		// df.applyPattern("#,#######.###");
		// Float f = Float.parseFloat("8956.101");
		//
		// String reslt =df.format(f);
		// System.out.println(reslt);
		// LocaleTest.print();
		// JsonConfig jsonConfig = new JsonConfig();
		// jsonConfig.registerJsonValueProcessor(Float.class,
		// new FloatJsonValueProcessor());
		//
		// List li = new ArrayList();
		// HistoryOperationDO do1 = new HistoryOperationDO();
		// Float f = Float.parseFloat("8956.101");
		// do1.setActualFund(f);
		// do1.setAssetId("66565");

		// HistoryOperationDO do2 = new HistoryOperationDO();
		// f = Float.parseFloat("0.0014");
		// do2.setActualFund(f);
		// do2.setAssetId("23232");

		// li.add(do1);
		// // li.add(do2);
		// JSONArray array = JSONArray.fromObject(li, jsonConfig);
		// array.getJSONObject(0).get("actualFund");
		// float s =
		// Float.parseFloat(array.getJSONObject(0).getString("actualFund"))+1;
		// System.out.println(s+":"+s+1);

		// ts();

		// try {
		// Map<String,String> result =
		// XmlUtil.paserXmlByDOM4J("C:\\Users\\Administrator.PC-20111014DELE\\Desktop\\result.xml");
		// System.out.println(result);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String sss =
		// "mac=da7114ac34ad8110a82b000992bbcbbf&xml=%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3Cmessage%3E%3Chead%3E%3Cversion%3E01%3C%2Fversion%3E%3CmsgType%3E0002%3C%2FmsgType%3E%3CchanId%3E99%3C%2FchanId%3E%3CmerchantNo%3E1058%3C%2FmerchantNo%3E%3CclientDate%3E20150307175634%3C%2FclientDate%3E%3CserverDate%3E20150307175215%3C%2FserverDate%3E%3CtranFlow%3E1058141501000%3C%2FtranFlow%3E%3CtranCode%3EQP0001%3C%2FtranCode%3E%3CrespCode%3EC000000000%3C%2FrespCode%3E%3CrespMsg%3E%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F%3C%2FrespMsg%3E%3C%2Fhead%3E%3Cbody%3E%3CtranRespCode%3E00%3C%2FtranRespCode%3E%3CmerOrderId%3ECF100000000017561974%3C%2FmerOrderId%3E%3CcustId%3EwNJGbETndgnF2emdzx2yHz6LwSnWqstf%0A%3C%2FcustId%3E%3CrefNo%3EQP20150307175532633%3C%2FrefNo%3E%3CstorableCardNo%3E6226201053%3C%2FstorableCardNo%3E%3Camount%3E368.00%3C%2Famount%3E%3C%2Fbody%3E%3C%2Fmessage%3E";
		//
		// String lll = sss.split("&")[1].split("=")[1];
		// System.out.println(lll);

		// String keyBytes = "abcdefgh";
		// String ss="wNJGbETndgnF2emdzx2yHz6LwSnWqstf";
		// String dd = CryptoUtils.desDecryptFromBase64(ss,
		// keyBytes.getBytes());
		// System.out.println(dd.substring(14));
//		testSendMsg();
		getString();
	}

	public static void getString() {
		System.out.println(CryptoUtils.encryptKeyData("175", Calendar
				.getInstance().getTimeInMillis() + "", "abcdefgh"));
	}

	public static void testSendMsg() {
		String phone = "15867198578";
		ApiUtils.send("1307", phone, "2356");
		ApiUtils.send("1308", phone, "张三");
		ApiUtils.send("1309", phone);
		ApiUtils.send("1310", phone, "2669854699");
		ApiUtils.send("1311", phone);
		ApiUtils.send("1312", phone, "张三", "63389001", "95");
		ApiUtils.send("1314", phone, "63389001");
		ApiUtils.send("1315", phone, "张三", "HB000565");
		ApiUtils.send("1316", phone, "张三", "HB000565", "36", "28");
		ApiUtils.send("1317", phone, "张三", "李四", "李四", "3");
		ApiUtils.send("1318", phone, "张三", "李四", "5");
	}

	public static void ts() {

		XingeApp app = new XingeApp(Long.parseLong("2100079370"),
				"dcb9ef3ca073a484de3631d9e26dbb28");

		Message android = new Message();
		android.setType(Message.TYPE_MESSAGE);
		android.setTitle("推送2323");
		android.setContent("dfgdfdf");
		android.setExpireTime(259200);

		List<String> tagList = new ArrayList<String>();
		tagList.add("all");
		tagList.add("all");
		tagList.add("all1");

		JSONObject androidRet = app.pushTags(XingeApp.DEVICE_ANDROID, tagList,
				"OR", android);// send to android

		// JSONObject androidRet =
		// app.pushSingleAccount(XingeApp.DEVICE_ANDROID,
		// "349", android);
		boolean success = (0 == androidRet.getInt("ret_code"));
		if (!success) {
			System.out
					.println("batch set tag exception happend:android err_msg="
							+ androidRet.getString("err_msg") + ",ret_code="
							+ androidRet.getInt("ret_code"));
		} else {
			System.out.println("成功");
		}

	}

	static class LocaleTest {
		static public void print() {
			String countries[] = { "China", "Canada", "Korea", "Taiwan",
					"France", "Italy", "Japan", "Germany", "US", "UK",
					"CHINESE", "SIMPLIFIED_CHINESE" };
			Locale locale[] = { Locale.CHINA, Locale.CANADA, Locale.KOREA,
					Locale.TAIWAN, Locale.FRANCE, Locale.ITALY, Locale.JAPAN,
					Locale.GERMANY, Locale.US, Locale.UK, Locale.CHINESE,
					Locale.SIMPLIFIED_CHINESE };
			int len = countries.length;
			for (int i = 0; i < len; ++i) {
				System.out.println(countries[i] + ": "
						+ NumberFormat.getNumberInstance().format(120000));
			}
		}
	}

	public static void test() {
		String xml = "%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3Cmessage%3E%3Chead%3E%3Cversion%3E01%3C%2Fversion%3E%3CmsgType%3E0002%3C%2FmsgType%3E%3CchanId%3E99%3C%2FchanId%3E%3CmerchantNo%3E1058%3C%2FmerchantNo%3E%3CclientDate%3E20150307175634%3C%2FclientDate%3E%3CserverDate%3E20150307175215%3C%2FserverDate%3E%3CtranFlow%3E1058141501000%3C%2FtranFlow%3E%3CtranCode%3EQP0001%3C%2FtranCode%3E%3CrespCode%3EC000000000%3C%2FrespCode%3E%3CrespMsg%3E%E4%BA%A4%E6%98%93%E6%88%90%E5%8A%9F%3C%2FrespMsg%3E%3C%2Fhead%3E%3Cbody%3E%3CtranRespCode%3E00%3C%2FtranRespCode%3E%3CmerOrderId%3ECF100000000017561974%3C%2FmerOrderId%3E%3CcustId%3EwNJGbETndgnF2emdzx2yHz6LwSnWqstf%0A%3C%2FcustId%3E%3CrefNo%3EQP20150307175532633%3C%2FrefNo%3E%3CstorableCardNo%3E6226201053%3C%2FstorableCardNo%3E%3Camount%3E368.00%3C%2Famount%3E%3C%2Fbody%3E%3C%2Fmessage%3E";
		try {
			String str = URLDecoder.decode(new String(xml.getBytes()), "Utf-8");
			System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
