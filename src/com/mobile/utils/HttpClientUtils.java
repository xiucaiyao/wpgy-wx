package com.mobile.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 后端http get/post请求工具类
 * 
 * @author 秀才
 * 
 * @version 1.0
 */
public class HttpClientUtils {

	/** 返回数据编码格式 */
	private static String encoding = "UTF-8";

	/**
	 * http客户端url get请求
	 * 
	 * @param url 客户端请求链接
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String get(String url) throws ClientProtocolException, IOException {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		HttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			// 执行 get 请求
			HttpResponse httpResponse = httpClient.execute(httpGet);
			// 获取响应实体
			HttpEntity httpEntity = httpResponse.getEntity();
			// 打印响应状态
			System.out.println(httpResponse.getStatusLine());
			if (httpEntity != null) {
				// 响应内容
				return EntityUtils.toString(httpEntity);
			}
			httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接，释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	/**
	 * http客户端URL post请求
	 * 
	 * @param url 客户端请求链接
	 * @param parameterMap 参数Map
	 */
	public static String post(String url, Map<String, Object> parameterMap) {
		HttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			HttpEntity entity = createEntity(parameterMap);
			if (entity != null) {
				httpPost.setEntity(entity);
			}
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				return EntityUtils.toString(httpEntity, encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接，释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	/**
	 * http客户端URL post请求
	 * 
	 * @param url 客户端请求链接
	 * @param param 参数str
	 */
	public static String post(String url, String param) {
		HttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			HttpEntity entity = new StringEntity(param,Charset.forName("UTF-8"));
			if (entity != null) {
				httpPost.setEntity(entity);
			}
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				return EntityUtils.toString(httpEntity, encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接，释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	/**
	 * 创建HttpEntity
	 * 
	 * @param parameterMap 参数Map
	 */
	private static HttpEntity createEntity(Map<String, Object> parameterMap) throws UnsupportedEncodingException {
		if (parameterMap != null && !parameterMap.isEmpty()) {
			// 添加POST参数
			Map<String, File> fileMap = new LinkedHashMap<String, File>();
			Map<String, String> fieldMap = new LinkedHashMap<String, String>();
			for (String key : parameterMap.keySet()) {
				Object value = parameterMap.get(key);
				if (value != null && value instanceof File) {
					if (value instanceof File) {
						fileMap.put(key, (File) parameterMap.get(key));
					} else {
						fieldMap.put(key, String.valueOf(parameterMap.get(key)));
					}
				}
			}
			if (!fileMap.isEmpty()) {
				MultipartEntity entity = new MultipartEntity();
				for (String key : fileMap.keySet()) {
					entity.addPart(key, new FileBody(fileMap.get(key)));
				}
				if (!fieldMap.isEmpty()) {
					for (String key : fieldMap.keySet()) {
						entity.addPart(key, new StringBody(fieldMap.get(key)));
					}
				}
				return entity;
			} else if (!fieldMap.isEmpty()) {
				List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
				for (String key : fieldMap.keySet()) {
					parameterList.add(new BasicNameValuePair(key, String.valueOf(parameterMap.get(key))));
				}
				return new UrlEncodedFormEntity(parameterList, encoding);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(String.valueOf(null));
	}

}
