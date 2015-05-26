package stock.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import sitong.thinker.common.util.codec.TokenUtils;

import com.zeekie.stock.util.http.HandleHttpRequest;

public class TestStock {

	public static void main(String[] args) {
		login();
//		deal();
//		cxdeal();
//		queryCombasset();
//		queryEntrust();
//		queryEntrustHistory();
		// String ss = getVerifyCode();
		// System.out.println(ss);
		// REGISTER();
		// updatePwd();
		// getVerifyCode();
		// updatetel("f9d4");
		// forgetPwd();
		// updatePwd();
		// indetify();
		// bindbank();
		// withdrawPageEnter();

		// setDepositPwd();
		// getCurrentAccount();
		// record();
		// withdraw();

		// startOperate();
		// storeOperationInfo();
		// getCurrentOperation();
		// modifyDepositPwd();
		// getOperateAcount();

		// enter();
		// add();
		// getapkpath();
		// getHistoryOperation();
		// getFundFlow();
		// endStock();
		// enterSpreadPage();
		// spread();
		// int max = 9999;
		// int min = 1;
		// Random random = new Random();
		// int s = random.nextInt(max) % (max - min + 1) + min;
		// System.out.println(s);
		// String js =
		// "<script src=\"../../../../../execute.js\" type=\"text/javascript\"></"
		// + "script>";
		//
		// StringBuffer s1 = new StringBuffer("ab</title>fgh"); // ԭ�ַ�
		// String s2 = "cde"; // Ҫ������ַ�
		//
		// int len = "</title>".length();
		// System.out.println(insertString(s1.toString(), s1.indexOf("</title>")
		// + len, js));

		// Random random = new Random();
		// System.out.println(genRandomNum(4));
		// getEveningFlag();

		// getWallet();

		// tt();

		// String s ="23256dfdf565d";
		// String template =
		// "lexical error at position {0}, encountered {1}, expected {2}";
		// String result = MessageFormat.format(template, 123,"aaa","bbb");
		// System.out.println(result);
		// bindPhone(ss);

		// getId();
		// getbindCreditCard();

		// hasOperation();
		// getBankLimitation();

		// getBasicInfo();

		// getVersionPic();
		// testapi();
	}

	public static void queryEntrustHistory() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "实5");
		datas.put("startDate", "2015-05-19");
		datas.put("endDate", "2015-05-25");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/entrust/history/query",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void queryEntrust() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "实5");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/entrust/query",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void queryCombasset() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "实5");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/entrust/combasset/query",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testapi() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		String xml = "";
		String mac = "";
		try {
			String tempXml = FileUtils
					.readFileToString(new File("C:\\new.xml"));
			String finaldata = tempXml + "wv8XU61B45nx4F";
			// �ύ������
			xml = new String(tempXml.getBytes("utf-8"));
			// ժҪ
			mac = DigestUtils.md5Hex(finaldata);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		datas.put("xml", xml);
		datas.put("mac", mac);
		try {// https://www.umbpay.com/QuickPay/msgProcess/acceptReq.do
			String result = req.post(
					"https://www.umbpay.com/QuickPay/msgProcess/acceptReq.do",
					datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getVersionPic() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		try {
			String result = req.post(
					"http://121.41.34.71:8082/stock/api/stock/picVersion/get",
					datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getBasicInfo() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("userId", "1121");
		try {
			String result = req
					.post("http://121.40.71.84:8083/stock/api/stock/account/basicInfo/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getBankLimitation() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("code", "1010001");
		try {
			String result = req
					.post("http://121.41.34.71:8082/stock/api/stock/account/bankLimitation/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void hasOperation() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "crespowang");
		try {
			String result = req
					.post("http://121.40.71.84:8083/stock/api/stock/trade/operation/has",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getId() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("userId", "518");
		try {
			String result = req.post(
					"http://121.41.34.71:8082/stock/api/stock/account/ID/get",
					datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getbindCreditCard() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("userId", "518");
		try {
			String result = req
					.post("http://121.41.34.71:8082/stock/api/stock/account/creditCard/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void bindPhone(String ss) {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "qianhongqiang");
		datas.put("telephone", "15867198578");
		datas.put("verifyCode", ss);
		try {
			String result = req.post(
					"http://121.41.34.71:8082/stock/api/stock/telephone/bind",
					datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void tt() {
		Set<String> nameSet = new HashSet<String>();
		nameSet.add("����");
		nameSet.add("����");
		nameSet.add("����");
		nameSet.add("����");

		// ������ ���� ���� ����
		for (String name : nameSet) {
			System.out.print(name + "\t");
		}

		System.out.println(nameSet.size());
	}

	public static void getWallet() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "letaojin");
		try {
			String result = req.post(
					"http://localhost:8080/stock/api/stock/trade/wallet/get",
					datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getEveningFlag() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "haluo");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/eveningFlag/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String genRandomNum(int pwd_len) {
		final int maxNum = 10;
		int i; // ��ɵ������
		int count = 0; // ��ɵ�����ĳ���
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// ��������ȡ���ֵ����ֹ ��ɸ���
			i = Math.abs(r.nextInt(maxNum)); // ��ɵ������Ϊ36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	public static String insertString(String original, int offset,
			String destination) {
		return new StringBuilder(original).insert(offset, destination)
				.toString();
	}

	public static void spread() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("refereeId", "63");
		datas.put("redPacket", "5");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/spreed",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getFundFlow() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "����1");
		datas.put("offset", "0");
		try {
			String result = req
					.post("http://121.40.71.84:8083/stock/api/stock/trade/operation/fundFlow/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void enterSpreadPage() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "��1");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/spreadPage/enter",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void endStock() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "test");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/operation/end",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getHistoryOperation() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "wangyujie");
		datas.put("offset", "0");
		try {
			String result = req
					.post("http://121.41.34.71:8082/stock/api/stock/trade/operation/history/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getapkpath() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();

		try {
			String result = req.post(
					"http://localhost:8080/stock/api/stock/web/apkpath/get",
					datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void add() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "letaojin");
		datas.put("addedGuaranteeCash", "199.5");
		// datas.put("currentGuaranteeCash", "100");
		// datas.put("currentOperateLimit", "1000");
		// datas.put("profitAndLossCash", "-15.0");
		datas.put("flag", "66");

		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/operation/guaranteeCash/add/",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void enter() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "����1");
		datas.put("addGuranteeCash", "199.5");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/operation/guaranteeCash/add/enter",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getOperateAcount() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "letaojin");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/operation/account/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void modifyDepositPwd() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("userId", "350");
		datas.put("depositPwd", "456789");
		datas.put("telephone", "15867198578");
		datas.put("verifyCode", "0180");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/account/depositPwd/modify",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getCurrentOperation() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "haluo");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/operation/current/info/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void startOperate() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "��136");
		datas.put("tradeFund", "10");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/trade/operation/start",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void storeOperationInfo() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "ceshi");
		datas.put("guaranteeCash", "1");
		datas.put("tradeFund", "10");
		datas.put("stopCash", "9.3");
		datas.put("warnCash", "9.5");
		datas.put("startDate", "2015-12-23");
		try {
			String result = req
					.post("http://121.40.71.84:8083/stock/api/stock/trade/operation/info/store",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updatetel(String verifyCode) {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "zeekie");
		datas.put("telephone", "15867198571");
		datas.put("newTelephone", "15867198578");
		datas.put("verifyCode", verifyCode);
		try {
			String result = req
					.post("http://121.41.34.71:8082/stock/api/stock/telephone/update",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void withdraw() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "Wyk");
		datas.put("fund", "50");
		datas.put("depositPwd", "123456");
		datas.put("openBank", "111");
		try {
			String result = req
					.post("http://121.41.34.71:8082/stock/api/stock/account/fund/withdraw",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void record() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "haluo");
		datas.put("fund", "100");
		datas.put("payAccount", "letaojin@hotmail.com");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/account/recharge/fundFlow/record",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getCurrentAccount() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "��4");
		datas.put("version", "android1.1.4");
		try {
			String result = req
					.post("http://121.41.34.71:8082/stock/api/stock/account/currentAccount/get",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setDepositPwd() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "zeekie");
		datas.put("telephone", "15265485245");
		datas.put("depositPwd", "999999");
		try {
			String result = req
					.post("http://121.41.34.71:8082/stock/api/stock/account/depositPwd/set",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void withdrawPageEnter() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "haluo");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/account/withdrawPage/enter",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void bindbank() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "zeekie");
		datas.put("telephone", "15867196985");
		datas.put("bank", "�й�������");
		datas.put("number", "52342418596542001X");
		try {
			String result = req
					.post("http://121.41.34.71:8082/stock/api/stock/account/creditCard/bind",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void indetify() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("truename", "�ǵ�");
		datas.put("nickname", "��1");
		datas.put("idCard", "36419851024013X");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/account/ID/indentify",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void forgetPwd() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("verifyCode", "c19b");
		datas.put("telephone", "15867198577");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/forgetPwd/verifyCode/check",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updatePwd() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "����18");
		// datas.put("oldPassword", "");
		datas.put("newPassward", "654321");
		datas.put("telephone", "15867198578");
		try {
			String result = req
					.post("http://localhost:8080/stock/api/stock/forgetPwd/password/update",
							datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cxdeal() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "实5");
		Map<String, String> headers = new HashMap<String, String>();
		JSONObject item = new JSONObject();

		try {
			item.put("token", TokenUtils.encryptToken("实5,20150525230220",
					"saiying_$hahabao"));
			headers.put("user_auth", item.toString());
			req.setHeaders(headers);
			String result = req.post(
					"http://localhost:8080/stock/api/stock/entrust/common/entrust/withdraw", datas);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deal() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "实5");
		datas.put("stockCode", "600005");
		datas.put("entrustPrice", "6.39");
		datas.put("entrustAmount", "100");
		Map<String, String> headers = new HashMap<String, String>();
		JSONObject item = new JSONObject();

		try {
			item.put("token", TokenUtils.encryptToken("实5,20150525230220",
					"saiying_$hahabao"));
			headers.put("user_auth", item.toString());
			req.setHeaders(headers);
			String result = req.post(
					"http://localhost:8080/stock/api/stock/entrust/common/entrust", datas);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void login() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "测试");
		datas.put("password", "123456");
		Map<String, String> headers = new HashMap<String, String>();
		JSONObject item = new JSONObject();

		try {
			item.put("token", TokenUtils.encryptToken("哇哈哈2,20150525230220",
					"saiying_$hahabao"));
			headers.put("user_auth", item.toString());
			req.setHeaders(headers);
			String result = req.post(
					"http://121.40.71.84:8083/stock/api/stock/user/login", datas);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getVerifyCode() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("telephone", "15867198578");
		datas.put("source", "13");
		String result = "";
		try {
			result = req.post(
					"http://121.41.34.71:8082/stock/api/stock/verifyCode/get",
					datas);
			System.out.println("��֤�룺" + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void REGISTER() {
		HandleHttpRequest req = new HandleHttpRequest();
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("nickname", "����");
		datas.put("password", "111");
		// datas.put("telephone", "15867266666");
		// datas.put("verifyCode", verifyCode);
		// datas.put("figurePwd", "123596");
		datas.put("refereeId", "407");
		try {
			String result = req.post(
					"http://121.41.34.71:8082/stock/api/stock/register", datas);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
