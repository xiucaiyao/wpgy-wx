package com.mobile.vo.wx;

import java.io.Serializable;

/**
 * 回复消息接口-图文消息
 */
public class ReplyArticleBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图文消息名称
	 */
	private String Title;
	/**
	 * 图文消息描述
	 */
	private String Description;
	/**
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图600*300，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	 */
	private String PicUrl;
	/**
	 * 点击图文消息跳转链接
	 */
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return null == Description ? "" : Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return null == PicUrl ? "" : PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return null == Url ? "" : Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
