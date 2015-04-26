package com.zeekie.stock.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlUtil {

	Logger log = LoggerFactory.getLogger(getClass());

	private static String keyBytes = "ifls8709";

	// 利用dom4j解析xml文件内容，并返回map数据形式
	public static Map<String, String> paserXmlByDOM4J(String path)
			throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new FileInputStream(new File(path)));
		Map<String, String> xml = paserXml(doc);
		return xml;
	}

	// 传入xml格式的string，转化为xml类型，然后解析其内容，返回map数据形式
	public static Map<String, String> strToXmlAndPaserXml(String strXml)
			throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new ByteArrayInputStream(strXml
				.getBytes("UTF-8")));
		Map<String, String> xml = paserXml(doc);
		return xml;
	}

	// 遍历解析xml数据
	public static Map<String, String> paserXml(Document doc) throws Exception {
		Map<String, String> xml = new HashMap<String, String>();
		Element root = doc.getRootElement();
		Iterator it = root.elementIterator();
		Element element;
		while (it.hasNext()) {
			element = (Element) it.next();
			xml.put(element.getName(), element.getText());
		}
		return xml;
	}

	public static Map<String, String> readStringXmlOut(String xml)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		doc = DocumentHelper.parseText(xml); // 将字符串转为XML
		Element rootElt = doc.getRootElement(); // 获取根节点
		Iterator iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head
		// 遍历head节点
		while (iter.hasNext()) {
			Element recordEle = (Element) iter.next();
			String respCode = recordEle.elementTextTrim("respCode"); // 拿到head节点下的子节点title值
			map.put("respCode", respCode);
			String respMsg = recordEle.elementTextTrim("respMsg");
			map.put("respMsg", StringUtils.trim(respMsg));
			map.put("merchantDate", recordEle.elementTextTrim("clientDate"));
		}

		Iterator iterss = rootElt.elementIterator("body"); // /获取根节点下的子节点body
		// 遍历body节点
		while (iterss.hasNext()) {
			Element recordEless = (Element) iterss.next();
			map.put("amount", recordEless.elementTextTrim("amount"));
			String useId = StringUtils.trim(CryptoUtils.desDecryptFromBase64(
					recordEless.elementTextTrim("custId"), keyBytes.getBytes())
					.substring(14));
			map.put("userId", useId);
			map.put("merchantId", recordEless.elementTextTrim("merOrderId"));

		}
		return map;
	}

	public static Map<String, String> parseXMl(String xml)
			throws DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = DocumentHelper.parseText(parseXml(xml)); // 将字符串转为XML
		Element rootElt = doc.getRootElement(); // 获取根节点
		Iterator iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head
		// 遍历head节点
		while (iter.hasNext()) {
			Element recordEle = (Element) iter.next();
			String respCode = recordEle.elementTextTrim("respCode"); // 拿到head节点下的子节点title值
			map.put("respCode", respCode);
			String respMsg = recordEle.elementTextTrim("respMsg");
			map.put("respMsg", StringUtils.trim(respMsg));
		}

		Iterator iterss = rootElt.elementIterator("body"); // /获取根节点下的子节点body
		// 遍历body节点
		while (iterss.hasNext()) {
			Element recordEless = (Element) iterss.next();
			map.put("amount", recordEless.elementTextTrim("amount"));
			map.put("merchantId", recordEless.elementTextTrim("merOrderId"));
			map.put("refNo", recordEless.elementTextTrim("refNo"));
			map.put("bankName", recordEless.elementTextTrim("bankName"));
			map.put("merchantDate", recordEless.elementTextTrim("transTime"));
		}
		return map;
	}

	private static String parseXml(String xml) {
		String[] result = StringUtils.split(xml, "&");
		if (result.length > 0) {
			String first = result[0];
			int index = first.indexOf("=");
			if (-1 != index) {
				return first.substring(index + 1);
			}
		}
		return "";
	}

}
