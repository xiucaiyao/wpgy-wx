package com.mobile.vo.wx;

/**
 * 回复消息接口-回复音乐消息
 * 
 */
public class ReplyMusicBean extends ReplyBaseMessageBean {
	/**
	 * 音乐名称
	 */
	private String title;
	/**
	 * 音乐描述
	 */
	private String description;
	/**
	 * 音乐链接
	 */
	private String musicurl;
	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 */
	private String hqmusicurl;
	/**
	 * 缩略图的媒体id，通过上传多媒体文件，得到的id
	 */
	private String thumb_media_id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicurl() {
		return musicurl;
	}

	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}

	public String getHqmusicurl() {
		return hqmusicurl;
	}

	public void setHqmusicurl(String hqmusicurl) {
		this.hqmusicurl = hqmusicurl;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
}
