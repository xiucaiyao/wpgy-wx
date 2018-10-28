package com.mobile.utils;

import java.util.Date;
import java.util.List;

import com.mobile.vo.wx.ReplyArticleBean;
import com.mobile.vo.wx.ReplyImageBean;
import com.mobile.vo.wx.ReplyMusicBean;
import com.mobile.vo.wx.ReplyNewsBean;
import com.mobile.vo.wx.ReplyTextBean;
import com.mobile.vo.wx.ReplyVideoBean;
import com.mobile.vo.wx.ReplyVoiceBean;

/**
 * 回复消息工具类
 */
public class MakeReplyMessageUtil{

	/**
	 * 回复文本消息
	 * 
	 * @param fromusername 粉丝openid
	 * @param tousername 微信公众号
	 * @param respContent 回复信息
	 * @return String
	 */
	public static String sendTextmessage(String fromusername, String tousername, String respContent) {
		// 初始化回复信息
		String respmessage;
		// 回复文本消息
		ReplyTextBean textMessage = new ReplyTextBean();
		// 发送方帐号（一个OpenID）
		textMessage.setToUserName(fromusername);
		// 开发者微信号
		textMessage.setFromUserName(tousername);
		// 消息创建时间 （整型）
		textMessage.setCreateTime(new Date().getTime());
		// 消息类型text
		textMessage.setMsgType("text");
		// 回复的消息内容,长度不超过2048字节
		textMessage.setContent(respContent);
		// 转为xml格式
		respmessage = XmlMessageUtil.textMessageToXml(textMessage);
		// 返回回复信息
		return respmessage;
	}

	/**
	 * 回复图片消息
	 * 
	 * @param fromusername 粉丝openid
	 * @param tousername 微信公众号
	 * @param mediaId 通过上传多媒体文件，得到的id
	 * @return
	 */
	public static String sendImagemessage(String fromusername, String tousername, String mediaId) {
		// 初始化回复消息
		String respmessage;
		// 回复图片消息
		ReplyImageBean imageMessage = new ReplyImageBean();
		// 发送放账号（一个OpenID）
		imageMessage.setToUserName(fromusername);
		// 开发者微信号
		imageMessage.setFromUserName(tousername);
		// 消息创建时间（整型）
		imageMessage.setCreateTime(new Date().getTime());
		// 消息类型image
		imageMessage.setMsgType("image");
		// 图片通过上传多媒体文件，得到的id
		imageMessage.setMediaId(mediaId);
		// 转换为xml格式
		respmessage = XmlMessageUtil.imageMessageToXml(imageMessage);
		// 返回回复消息
		return respmessage;
	}

	/**
	 * 回复语音消息
	 * 
	 * @param fromusername 粉丝openid
	 * @param tousername 微信公众号
	 * @param mediaId 通过上传多媒体文件，得到的id
	 * @return
	 */
	public static String sendVoicemessage(String fromusername,
			String tousername, String mediaId) {
		// 初始化回复消息
		String respmessage;
		// 回复语言消息
		ReplyVoiceBean voiceMessage = new ReplyVoiceBean();
		// 发送放账号（一个OpenID）
		voiceMessage.setToUserName(fromusername);
		// 开发者微信号
		voiceMessage.setFromUserName(tousername);
		// 消息创建时间（整型）
		voiceMessage.setCreateTime(new Date().getTime());
		// 消息类型image
		voiceMessage.setMsgType("voice");
		// 图片通过上传多媒体文件，得到的id
		voiceMessage.setMediaId(mediaId);
		// 转换为xml格式
		respmessage = XmlMessageUtil.voiceMessageToXml(voiceMessage);
		// 返回回复消息
		return respmessage;
	}

	/**
	 * 回复视频消息
	 * 
	 * @param fromusername 粉丝openid
	 * @param tousername 微信公众号
	 * @param mediaId 通过上传多媒体文件，得到的id
	 * @param title 视频标题
	 * @param description 视频描述
	 * @return String
	 */
	public static String sendVideoMessage(String fromusername, String tousername, String mediaId, String title, String description) {
		// 初始化回复信息
		String respmessage;
		// 创建视频消息
		ReplyVideoBean videoMessage = new ReplyVideoBean();
		// 发送方帐号（一个OpenID）
		videoMessage.setToUserName(fromusername);
		// 开发者微信号
		videoMessage.setFromUserName(tousername);
		// 消息创建时间 （整型）
		videoMessage.setCreateTime(new Date().getTime());
		// 消息类型video
		videoMessage.setMsgType("video");
		// 通过上传多媒体文件，得到的id
		videoMessage.setMediaId(mediaId);
		// 视频消息的标题
		videoMessage.setTitle(title);
		// 视频消息的描述
		videoMessage.setDescription(description);
		// 转换为xml格式
		respmessage = XmlMessageUtil.videoMessageToXml(videoMessage);
		// 返回回复消息
		return respmessage;
	}

	/**
	 * 回复音乐消息
	 * 
	 * @param fromusername 粉丝openid
	 * @param tousername 微信公众号
	 * @param title 音乐标题
	 * @param description 音乐描述
	 * @param musicurl 音乐链接
	 * @param hqmusicurl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 * @param thumbmediaid 缩略图的媒体id，通过上传多媒体文件，得到的id
	 * @return String
	 */
	public static String sendMusicMessage(String fromusername, String tousername, String title, String description, String musicurl, String hqmusicurl, String thumbmediaid) {
		// 初始化回复信息
		String respmessage;
		// 创建音乐消息
		ReplyMusicBean musicMessage = new ReplyMusicBean();
		// 发送方帐号（一个OpenID）
		musicMessage.setFromUserName(fromusername);
		// 开发者微信号
		musicMessage.setToUserName(tousername);
		// 消息创建时间 （整型）
		musicMessage.setCreateTime(new Date().getTime());
		// 消息类型music
		musicMessage.setMsgType("music");
		// 音乐标题
		musicMessage.setTitle(title);
		// 音乐描述
		musicMessage.setDescription(description);
		// 音乐链接
		musicMessage.setMusicurl(musicurl);
		// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
		musicMessage.setHqmusicurl(hqmusicurl);
		// 缩略图的媒体id，通过上传多媒体文件，得到的id
		musicMessage.setThumb_media_id(thumbmediaid);
		// 转换为xml格式
		respmessage = XmlMessageUtil.musicMessageToXml(musicMessage);
		// 返回回复消息
		return respmessage;
	}

	/**
	 * 图文消息设置
	 * 
	 * @param fromusername 粉丝openid
	 * @param tousername 开发者微信公众账号
	 * @param newslist 图文消息list
	 * @return String
	 */
	public static String sendNewsmessage(String fromusername, String tousername, List<ReplyArticleBean> newslist) {
		// 初始化回复信息
		String respmessage;
		// 创建图文消息
		ReplyNewsBean newsMessage = new ReplyNewsBean();
		// 发送方帐号（一个OpenID）
		newsMessage.setToUserName(fromusername);
		// 开发者微信号
		newsMessage.setFromUserName(tousername);
		// 消息创建时间 （整型）
		newsMessage.setCreateTime(new Date().getTime()/1000);
		// 消息类型news
		newsMessage.setMsgType("news");
		// 图文消息个数，限制为10条以内
		newsMessage.setArticleCount(newslist.size());
		// 多条图文消息信息，默认第一个item为大图
		newsMessage.setArticles(newslist);
		// 转成xml形式
		respmessage = XmlMessageUtil.newsMessageToXml(newsMessage);
		// 回复信息
		return respmessage;
	}

	public static void main(String[] args) {
		System.out.println(MakeReplyMessageUtil.sendTextmessage("fromuser", "tousername", "respContent"));
	}
}