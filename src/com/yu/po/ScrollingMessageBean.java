package com.yu.po;

/**
 * 滚动信息Bean
 * 
 * @author 金鱼
 */
public class ScrollingMessageBean {
	
	/** 主键 */
	private String scrollingMessageId;
	
	/** 显示顺序 */
	private int displayOrder;
	
	/** 标题 */
	private String title;
	
	/** 滚动图片 */
	private String scrollingPic;
	
	/** 外部链接 */
	private String linkUrl;
	
	/** 描述HTML */
	private String describeHtml;
	
	public ScrollingMessageBean(){
		
	}

	public String getScrollingMessageId() {
		return scrollingMessageId;
	}

	public void setScrollingMessageId(String scrollingMessageId) {
		this.scrollingMessageId = scrollingMessageId;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScrollingPic() {
		return scrollingPic;
	}

	public void setScrollingPic(String scrollingPic) {
		this.scrollingPic = scrollingPic;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getDescribeHtml() {
		return describeHtml;
	}

	public void setDescribeHtml(String describeHtml) {
		this.describeHtml = describeHtml;
	}

}
