package com.bronzesoft.pxb.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

import com.bronzesoft.pxb.common.https.MySecureProtocolSocketFactory;

public class Https {
	
	// 编码方式
	private static final String CONTENT_CHARSET = "UTF-8";

	// 连接超时时间
	private static final int CONNECTION_TIMEOUT = 3000;

	// 读数据超时时间
	private static final int READ_DATA_TIMEOUT = 3000;
	
	/**
	 *  通过POST方式请求，协议为HTTP
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postHttp(String url, HashMap<String, String> params) {
		return postRequest(url, params, null, null, "http");
	}
	
	/**
	 * 通过POST方式请求，协议为HTTPS
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postHttps(String url, HashMap<String, String> params) {
		return postRequest(url, params, null, null, "https");
	}
	
	/**
	 *  通过POST方式请求，协议为HTTP，Body内容为JSON格式
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postJson(String url, String jsonString) {
		return postRequest(url, null, jsonString, null, "http");
	}
	
	/**
	 *  通过POST方式请求，协议为HTTPS，Body内容为JSON格式
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postJsons(String url, String jsonString) {
		return postRequest(url, null, jsonString, null, "https");
	}
	
	private static String postRequest(String url,
			HashMap<String, String> params, String jsonString,
			HashMap<String, String> cookies, String protocol) {
		
		if (protocol.equalsIgnoreCase("https")) {
			Protocol httpsProtocol = new Protocol("https",
					new MySecureProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", httpsProtocol);
		}

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		
		if (jsonString != null) {
			StringRequestEntity requestEntity = null;
			try {
				requestEntity = new StringRequestEntity(jsonString,"application/json", CONTENT_CHARSET);
			} catch (UnsupportedEncodingException e1) {
				System.out.println("Request [" + url + "] failed:" + e1.getMessage());
			}
			postMethod.setRequestEntity(requestEntity);
		} else {
			// 设置请求参数
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> datas = new ArrayList<NameValuePair>();
				for (String key : params.keySet()) {
					datas.add(new NameValuePair(key, params.get(key)));
				}
				postMethod.setRequestBody(datas.toArray(new NameValuePair[datas.size()]));
			}
		}

		// 设置cookie
		if (cookies != null && !cookies.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (String key : cookies.keySet()) {
				sb.append(key).append("=").append(cookies.get(key)).append(";");
			}
			
			// 设置cookie策略
			postMethod.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);

			// 设置cookie内容
			postMethod.setRequestHeader("Cookie", sb.toString());
		}

		// 设置User-Agent
		postMethod.setRequestHeader("User-Agent", "RDM WX Client");

		// 设置建立连接超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(
				CONNECTION_TIMEOUT);

		// 设置读数据超时时间
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(
				READ_DATA_TIMEOUT);

		// 设置编码
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, CONTENT_CHARSET);

		//使用系统提供的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		try {
			try {
				int statusCode = httpClient.executeMethod(postMethod);

				if (statusCode != HttpStatus.SC_OK) {
					System.out.println("Request [" + url + "] failed:"
							+ postMethod.getStatusLine());
				}

				//读取内容 
				byte[] responseBody = postMethod.getResponseBody();

				return new String(responseBody, CONTENT_CHARSET);
			} finally {
				//释放链接
				postMethod.releaseConnection();
			}
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Request [" + url + "] failed:" + e.getMessage());
		} catch (IOException e) {
			//发生网络异常
			System.out.println("Request [" + url + "] failed:" + e.getMessage());
		}
		return "";
	}

}
