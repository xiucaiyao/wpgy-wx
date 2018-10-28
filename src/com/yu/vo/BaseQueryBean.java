package com.yu.vo;

import java.util.List;

import com.yu.utils.SysUtils;

/**
 * 查询Bean基类
 * 
 * @author 金鱼
 */
public class BaseQueryBean {

	/** 每页记录数 */
	private Integer pageItems;

	/** 当前页面数 */
	private Integer currentPage;

	/** 重要的字段查询项 */
	private String searchKeywords;

	/** 客户端排序 (提交到action传值格式为：order.name[0]) */
	private List<OrderBean> orderList;

	public BaseQueryBean() {

	}

	public Integer getPageItems() {
		if (pageItems == null || pageItems < 1) {
			pageItems = 40;
		}
		return pageItems;
	}

	public void setPageItems(Integer pageItems) {
		this.pageItems = pageItems;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getStartItem() {
		if (pageItems == null || pageItems < 1) {
			pageItems = 10;
		}
		if (currentPage == null || currentPage < 1) {
			currentPage = 1;
		}
		return pageItems * (currentPage-1);
	}

	public String getSearchKeywords() {
		return searchKeywords;
	}

	public void setSearchKeywords(String searchKeywords) {
		if(searchKeywords != null){
			searchKeywords = searchKeywords.trim();
		}
		this.searchKeywords = searchKeywords;
	}

	public List<OrderBean> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderBean> orderList) {
		this.orderList = orderList;
	}
	
	public String getOrderSql(){
		return SysUtils.generationOrderSql(orderList);
	}
}
