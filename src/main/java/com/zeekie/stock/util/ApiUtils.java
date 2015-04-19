package com.zeekie.stock.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sitong.thinker.common.api.ApiResponse;
import sitong.thinker.common.api.Head;
import sitong.thinker.common.error.Message;

import com.zeekie.stock.Constants;
import com.zeekie.stock.StockResultMessage;
import com.zeekie.stock.util.http.HandleHttpRequest;

/**
 * 
 * @author zeekie
 * @version 1.0, 2014/05/19
 */
public class ApiUtils {

	static Logger log = LoggerFactory.getLogger(ApiUtils.class);

	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";

	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";

	private static final String regEx_html = "<[^>]+>";

	private static final String blank_html = "&nbsp;";

	/**
	 * @see sitong.thinker.common.api.ApiResponse#success()
	 * @see sitong.thinker.common.api.ApiResponse#success(Object)
	 */
	public static ApiResponse good() {
		return good(Collections.emptyMap());
	}

	public static <T> ApiResponse good(T model) {
		return new ApiResponse().head(
				new Head(StockResultMessage.SUCCESS.getCode(),
						StockResultMessage.SUCCESS.getText())).body(model);
	}

	/**
	 * 浣跨敤{@link CloudResultMessage#INTERNAL_SERVER_ERROR}浣滀负榛樿閿欒
	 */
	public static ApiResponse bad() {
		return bad(StockResultMessage.INTERNAL_SERVER_ERROR);
	}

	public static ApiResponse bad(StockResultMessage message) {
		return bad(message.createMessage());
	}

	public static ApiResponse bad(Message message) {
		return new ApiResponse().head(
				new Head(message.getCode(), message.getText())).body(
				Collections.emptyMap());
	}

	/**
	 * a public simple method convert bean to map
	 * 
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static Map convertBean(Object bean) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

	/**
	 * delete html tag
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // filter script tag

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // filter style tag

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // filter html tag

		Pattern p_blank = Pattern.compile(blank_html, Pattern.CASE_INSENSITIVE);
		Matcher m_blank = p_blank.matcher(htmlStr);
		htmlStr = m_blank.replaceAll(""); // filter blank space tag
		return htmlStr.trim(); // return String
	}

	public static String toJSON(String code, String message, Object body) {
		JSONObject json = new JSONObject();

		Map<String, String> head = new HashMap<String, String>();
		head.put("code", code);
		head.put("msg", message);
		head.put("time", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

		json.put("head", head);
		json.put("body", body);
		return json.toString();
	}

	public static boolean send(String fn, String phone, String... args) {
		String template = Constants.MSG_MODEL.get(fn);
		if (StringUtils.isBlank(template)) {
			log.error("查询数据库发现功能号：" + fn + "所对应的模板并不存在，请检查");
			return false;
		}

		if (StringUtils.isBlank(phone)) {
			log.error("调用功能号【" + fn + "】发现电话号码为空，参数内容：" + args);
			return false;
		}

		String content = StringUtils.equals(Constants.MODEL_CONTENT, template) ? Constants.MODEL_CONTENT
				+ args[0]
				: MessageFormat.format(template, args);

		if (log.isDebugEnabled()) {
			log.debug("推送的消息是：" + content);
		}

		Map<String, String> datas = new HashMap<String, String>();
		datas.put("name", Constants.MSG_USER);
		datas.put("pwd", Constants.MSG_PWD);
		datas.put("mobile", phone);
		datas.put("content", content);
		datas.put("type", "pt");
		HandleHttpRequest request = new HandleHttpRequest();
		try {
			String result = request.post(Constants.MSG_URL, datas);
			if (StringUtils.startsWith(result, "0")) {
				if (log.isDebugEnabled()) {
					log.debug("send msg to [" + phone + "] success");
				}
				return true;
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}
}
