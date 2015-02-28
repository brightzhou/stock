package com.zeekie.stock.util.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zeekie
 * 
 * @version 1.0, 2014/05/30
 */

public class HandleHttpRequest {
	
	private static Logger log = LoggerFactory.getLogger(HandleHttpRequest.class);

	protected String url = "";

	protected Map<String, String> headers = null;

	protected int connectionTimeout = 50000;

	protected int soTimeout = 10000;

	protected int statusCode = 200;

	protected String charset = HTTP.UTF_8;

	protected HttpGet httpGet;

	protected HttpPost httpPost;

	protected HttpParams httpParameters;

	protected HttpResponse httpResponse;

	protected HttpClient httpClient;

	protected String inputContent;

	/**
	 * 设置当前请求的链�?
	 * 
	 * @param url
	 * @return
	 */
	public HandleHttpRequest setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * 设置请求�?header 信息
	 * 
	 * @param headers
	 * @return
	 */
	public HandleHttpRequest setHeaders(Map headers) {
		this.headers = headers;
		return this;
	}

	/**
	 * 设置连接超时时间
	 * 
	 * @param timeout
	 *            单位（毫秒），默�?5000
	 * @return
	 */
	public HandleHttpRequest setConnectionTimeout(int timeout) {
		this.connectionTimeout = timeout;
		return this;
	}

	/**
	 * 设置 socket 读取超时时间
	 * 
	 * @param timeout
	 *            单位（毫秒），默�?10000
	 * @return
	 */
	public HandleHttpRequest setSoTimeout(int timeout) {
		this.soTimeout = timeout;
		return this;
	}

	/**
	 * 设置获取内容的编码格�?
	 * 
	 * @param charset
	 *            默认�?UTF-8
	 * @return
	 */
	public HandleHttpRequest setCharset(String charset) {
		this.charset = charset;
		return this;
	}

	/**
	 * 获取 HTTP 请求响应信息
	 * 
	 * @return
	 */
	public HttpResponse getHttpResponse() {
		return this.httpResponse;
	}

	/**
	 * 获取 HTTP 客户端连接管理器
	 * 
	 * @return
	 */
	public HttpClient getHttpClient() {
		return this.httpClient;
	}

	/**
	 * 获取请求的状态码
	 * 
	 * @return
	 */
	public int getStatusCode() {
		return this.statusCode;
	}

	/**
	 * 通过 GET 方式请求数据
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String get(String url) throws IOException {
		// 设置当前请求的链�?
		this.setUrl(url);
		// 实例�?GET 连接
		this.httpGet = new HttpGet(this.url);
		// 自定义配�?header 信息
		this.addHeaders(this.httpGet);
		// 初始化客户端请求
		this.initHttpClient();
		// 发�? HTTP 请求
		this.httpResponse = this.httpClient.execute(this.httpGet);
		// 读取远程数据
		this.getInputStream();
		// 远程请求状�?码是否正�?
		if (this.statusCode != HttpStatus.SC_OK) {
			return "";
		}
		// 返回全部读取到的字符�?
		return this.inputContent;
	}

	public String post(String url, Map<String, String> datas) throws IOException
    {
        this.setUrl(url);
        // 实例�?GET 连接
        this.httpPost = new HttpPost(this.url);
        // 自定义配�?header 信息
        this.addHeaders(this.httpPost);
        // 初始化客户端请求
        this.initHttpClient();
        Iterator<Map.Entry<String, String>> iterator = datas.entrySet().iterator();
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        // 生成 HTTP 实体
        HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);//multipartEntityBuilder.build();
        // 设置 POST 请求的实体部�?
        this.httpPost.setEntity(httpEntity);
        // 发�? HTTP 请求
        this.httpResponse = this.httpClient.execute(this.httpPost);
        
		// 获取请求返回的状态码
		this.statusCode = this.httpResponse.getStatusLine().getStatusCode();
        
        // 远程请求状�?码是否正�?
        if (this.statusCode != HttpStatus.SC_OK) {
        	if(log.isDebugEnabled()){
        		log.debug("handle http failure,httpStatus = "+this.statusCode);
        	}
        	return "";
        }
        // 读取远程数据
        this.getInputStream();
        // 返回全部读取到的字符�?
        return this.inputContent.toString();
    }

	/**
	 * �?HTTP 请求添加 header 信息
	 * 
	 * @param request
	 */
	protected void addHeaders(HttpRequestBase request) {
		if (this.headers != null) {
			Set keys = this.headers.entrySet();
			Iterator iterator = keys.iterator();
			Map.Entry<String, String> entry;
			while (iterator.hasNext()) {
				entry = (Map.Entry<String, String>) iterator.next();
				request.addHeader(entry.getKey().toString(), entry.getValue()
						.toString());
			}
		}
	}

	/**
	 * 配置请求参数
	 */
	protected void setParams() {
		this.httpParameters = new BasicHttpParams();
		this.httpParameters.setParameter(charset, this.charset);
		// 设置 连接请求超时时间
		HttpConnectionParams.setConnectionTimeout(this.httpParameters,
				this.connectionTimeout);
		// 设置 socket 读取超时时间
		HttpConnectionParams.setSoTimeout(this.httpParameters, this.soTimeout);
	}

	/**
	 * 初始化配置客户端请求
	 */
	protected void initHttpClient() {
		// 配置 HTTP 请求参数
		this.setParams();
		// �?���?��客户�?HTTP 请求
		this.httpClient = new DefaultHttpClient(this.httpParameters);
	}

	/**
	 * 读取远程数据
	 * 
	 * @throws IOException
	 */
	protected void getInputStream() throws IOException {
		// 接收远程输入�?
		InputStream inStream = this.httpResponse.getEntity().getContent();
		// 分段读取输入流数�?
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buf)) != -1) {
			baos.write(buf, 0, len);
		}
		// 将数据转换为字符串保�?
		this.inputContent = new String(baos.toByteArray(),"UTF-8");
		// 数据接收完毕�?��
		inStream.close();
		// 获取请求返回的状态码
//		this.statusCode = this.httpResponse.getStatusLine().getStatusCode();
		
		shutdownHttpClient();
	}

	/**
	 * 关闭连接管理器释放资�?
	 */
	protected void shutdownHttpClient() {
		if (this.httpClient != null
				&& this.httpClient.getConnectionManager() != null) {
			this.httpClient.getConnectionManager().shutdown();
		}
	}
}
