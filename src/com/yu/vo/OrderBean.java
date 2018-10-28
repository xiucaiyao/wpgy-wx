package com.yu.vo;

import com.yu.utils.SysUtils;


/**
 * <b>文件名：</b>PageBean.java <br>
 * <b>功能描述:</b>客户端排序条件Bean<br>
 * 
 * @author 金鱼
 */
public class OrderBean {

	/** 名称(排序字段的名称顺序) */
	private String name;

	/** 是否按降序排序（true:order by XX desc,false: order by XX） */
	private boolean isDesc;

	public OrderBean() {

	}
	
	public OrderBean(String name, boolean isDesc){
		this.name = name;
		this.isDesc = isDesc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(SysUtils.validateFiled(name)){
			this.name = name;
		}
	}

	public boolean getIsDesc() {
		return isDesc;
	}

	public void setIsDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}

}
