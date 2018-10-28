package com.mobile.vo.wx;

/**
 * 回复消息接口-图片消息
 * 
 */
public class ReplyImageBean extends ReplyBaseMessageBean {
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
