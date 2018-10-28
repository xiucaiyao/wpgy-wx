package com.mobile.vo.wx;

import java.util.List;

/**
 * 回复消息接口-图文消息
 * 
 */
public class ReplyNewsBean extends ReplyBaseMessageBean {
	/**
	 * 图文消息个数，限制为10条以内
	 */
	private int ArticleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private List<ReplyArticleBean> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<ReplyArticleBean> getArticles() {
		return Articles;
	}

	public void setArticles(List<ReplyArticleBean> articles) {
		Articles = articles;
	}
}
