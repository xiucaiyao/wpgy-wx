package com.mobile.vo.wx;

/**
 * 回复消息接口-语音消息
 * 
 */
public class ReplyVoiceBean extends ReplyBaseMessageBean {
	/**
	 * 媒体文件ID
	 */
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
