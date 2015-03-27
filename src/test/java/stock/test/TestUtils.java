package stock.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.CryptoUtils;
import com.zeekie.stock.util.StringUtil;

public class TestUtils {

	static Logger log = LoggerFactory.getLogger(TestUtils.class);

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

		try {
			// Map<String, String> result = XmlUtil
			// .paserXmlByDOM4J("C:\\Users\\Administrator.PC-20111014DELE\\Desktop\\result.xml");
			InputStream in = new FileInputStream(new File(
					"C:\\Users\\liz\\Desktop\\result.xml"));
			String result1 = getStreamString(in);
			System.out.println(result1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		// testSendMsg();
		// getString();

		// getMaxBytes();

	}

	public static void testAcceptCall() {
		// InputStream in = new FileInputStream(new File(
		// "C:\\Users\\liz\\Desktop\\result.xml"));
		// HandleHttpRequest request = new HandleHttpRequest();
		// Map<String, String> map = new HashMap<String, String>();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://localhost:8080/stock/api/stock/acceptCall");
		FileBody fileBody = new FileBody(new File(
				"C:\\Users\\liz\\Desktop\\result.xml"));
		MultipartEntity entity = new MultipartEntity();
		entity.addPart("file", fileBody);
		post.setEntity(entity);
		HttpResponse response = null;
		try {
			response = httpclient.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
			System.out.println("ddd");
		}
		httpclient.getConnectionManager().shutdown();

	}

	public static void testw() throws Exception {

		String path = "C:\\Users\\liz\\Desktop\\in.xml";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();

		org.w3c.dom.Document doc = db
				.parse(new FileInputStream(new File(path)));

		XPathFactory factory = XPathFactory.newInstance();

		XPath xpath = factory.newXPath();

		String expression;
		Node node;
		NodeList nodeList;

		// 1. root element
		expression = "/*";
		node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
		System.out.println("1. " + node.getNodeName());

		// 2. root element (by name)
		expression = "/rss";
		node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
		System.out.println("2. " + node.getNodeName());

		// 3. element under rss
		expression = "/rss/channel";
		node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
		System.out.println("3. " + node.getFirstChild().getNodeValue());

		// 4. all elements under rss/channel
		expression = "/rss/channel/*";
		nodeList = (NodeList) xpath.evaluate(expression, doc,
				XPathConstants.NODESET);
		System.out.print("4. ");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.print(nodeList.item(i).getNodeName() + " ");
		}
		System.out.println();

		// 5. all title elements in the document
		expression = "//title";
		nodeList = (NodeList) xpath.evaluate(expression, doc,
				XPathConstants.NODESET);
		System.out.print("5. ");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.print(nodeList.item(i).getNodeName() + " ");
		}
		System.out.println();

		// 6. all elements in the document except title
		expression = "//*[name() != 'title']";
		nodeList = (NodeList) xpath.evaluate(expression, doc,
				XPathConstants.NODESET);
		System.out.print("6. ");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.print(nodeList.item(i).getNodeName() + " ");
		}
		System.out.println();

		// 7. all elements with at least one child element
		expression = "//*[*]";
		nodeList = (NodeList) xpath.evaluate(expression, doc,
				XPathConstants.NODESET);
		System.out.print("7. ");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.print(nodeList.item(i).getNodeName() + " ");
		}
		System.out.println();

		// 8. all level-5 elements (the root being at level 1)
		expression = "/*/*/*/*";
		nodeList = (NodeList) xpath.evaluate(expression, doc,
				XPathConstants.NODESET);
		System.out.print("8. ");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.print(nodeList.item(i).getNodeName() + " ");
		}
		System.out.println();

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

	private static String getStreamString(InputStream in) {
		if (in != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(
						new InputStreamReader(in));
				StringBuffer xml = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					xml.append(sTempOneLine);
				}

				String originResult = StringUtil.getResult(xml.toString());
				if (log.isDebugEnabled()) {
					log.debug("原始返回：" + originResult);
				}
				String convertResult = URLDecoder.decode(new String(
						originResult.getBytes()), "UTF-8");
				if (log.isDebugEnabled()) {
					log.debug("转换后结果为：" + convertResult);
				}
				return convertResult;
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			} finally {
				IOUtils.closeQuietly(in);
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("获取支付回调结果为空");
			}
		}
		return "";
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
