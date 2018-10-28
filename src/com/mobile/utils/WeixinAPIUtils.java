package com.mobile.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;

import com.mobile.constants.WeixinConstants;

/**
 * 微信API工具类
 * 
 * @author 金鱼
 */
public class WeixinAPIUtils {

	/**
	 * 获取accessToken
	 */
	public static String getAccessToken(String appid, String secret) throws Exception {
		JSONObject object = null;
		try {
			String response = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret);
			object = JSONObject.fromObject(response);
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException" + e.getMessage());
			e.printStackTrace();
		}
		if (object == null) {
			return null;
		}
		return object.getString("access_token");
	}

	/**
	 * 获取临时票据(jsapi_ticket)
	 */
	public static JSONObject getJsapiTicket(String accessToken) throws Exception {
		JSONObject jsonObject = null;
		try {
			if (StringUtils.isNotBlank(accessToken)) {
				String response = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi");
				jsonObject = JSONObject.fromObject(response);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException" + e.getMessage());
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 获取签名（signature）
	 */
	public static String getSignature(String jsapiTicket, String url, String nonceStr, String timestamp) {
		String signature = null;
		try {
			String signatureSource = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(signatureSource.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signature;
	}

	/**
	 * byte格式化
	 */
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 生成签名随机字符串
	 */
	public static String generateNonceStr() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 生成签名时间戳
	 */
	public static String generateTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 验证签名
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		if(StringUtils.isBlank(signature) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)){
			return false;
		}
		String[] arr = new String[] { WeixinConstants.WEIXIN_TOKEN, timestamp, nonce};
		// 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		content = null;
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
	
	/** 网页授权获取用户基本信息：用户同意授权，获取code API 
	 * @throws UnsupportedEncodingException */
	public static String getAuthorizeCodeAPI(String appid, String redirect_uri, String state) throws UnsupportedEncodingException{
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+URLEncoder.encode(redirect_uri, "UTF-8")+"&response_type=code&scope=snsapi_base&state="+state+"#wechat_redirect";
	}

	
	/** 网页授权获取粉丝唯一标识息 API 
	 * @throws UnsupportedEncodingException */
	public static JSONObject getAuthorizeOpenIdAPI(String appid, String appsecret, String code) {
		JSONObject jsonObject = null;
		try {
			if (StringUtils.isBlank(appid) || StringUtils.isBlank(appsecret) || StringUtils.isBlank(code)) {
				return null;
			}
			String response = HttpClientUtils.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code");
			jsonObject = JSONObject.fromObject(response);
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException" + e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static void main(String[] args){
		try {
			System.out.println(URLEncoder.encode("http://goldfish1987.oicp.net/bootstrap/test2.html", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
