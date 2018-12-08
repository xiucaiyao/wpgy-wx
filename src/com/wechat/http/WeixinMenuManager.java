package com.wechat.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.mobile.constants.WeixinConstants;
import com.mobile.utils.HttpClientUtils;

import net.sf.json.JSONObject;

/**
 * 微信公众号菜单管理
 * 
 * @author xiucai.yao@126.com
 *
 */
public class WeixinMenuManager {
	
	//white ip: 114.61.8.192

	public static String getAccessToken() {

		String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ WeixinConstants.WEIXIN_APP_ID + "&secret=" + WeixinConstants.WEIXIN_APP_SECRET;

		try {
			String response = HttpClientUtils.get(accessTokenUrl);
			
			System.out.println("response:" + response);

			String accessToken = JSONObject.fromObject(response).getString("access_token");

			System.out.println("accessToken:" + accessToken);
			return accessToken;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static void createMenu() {
		String accessToken = getAccessToken(); //16_cWgQV7hiFdqFcRqXWUxl9yxDWPVBv7j2WIhRTRvugKY5OL9U9tvPNbQISZle3l-PvfgdHCckrfJnnD9_jVfdCjY8EH-VVRlWGhaP8wAqAs62KFaZ3Urc5-XmMSRCznO884ExvL-AXePmzAWBFSFiACAVIV
		String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;

		//url处一定放登陆的链接，不能放其它的action
		String requestBody = 
				"{\"button\":[{" 
					+ "\"type\":\"view\"," 
					+ "\"name\":\"果品市场\","
					+ "\"url\":\"https://www.wangpangguopin.com/wpgy-wx/fans!gotoLogin.action\""   
					+ " }]}";
		
		System.out.println("requestBody:" + requestBody);
		
		
		String response = HttpClientUtils.post(createMenuUrl, requestBody);
		
		System.out.println("response:" + response);
	}
	
	public static void deleteMenu(){
		String accessToken = getAccessToken(); 
		String deleteUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
		try {
			String response = HttpClientUtils.get(deleteUrl);
			System.out.println("deleteMenu response:" + response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		deleteMenu();
		createMenu();
	}
}
