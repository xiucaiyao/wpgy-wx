package com.mobile.vo.wx;

/**
 * 回复消息接口-文本消息
 * 
 */
public class ReplyTextBean extends ReplyBaseMessageBean {
	/**
	 * 消息内容
	 */
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
