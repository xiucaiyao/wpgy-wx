package com.yu.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 事务层返回结果集Bean类
 * 
 * @author 金鱼
 */
public class ReturnResultBean {
	
	/** 操作是否成功 */
	private boolean isSuccess;
	
	/** 提示信息key */
	private String message;
	
	/** 方法返回结果集Map */
	private Map<String, Object> returnDataMap;
	
	/** 异常详细信息 */
	private String exceptionMessage;
	
	
	public ReturnResultBean(){
		
	}

	/** 结果集Bean实例化 */
	public static ReturnResultBean newInstance(){
		return new ReturnResultBean();
	}
	
	/** 操作成功 */
	public void operationSuccess(){
		isSuccess = true;
	}
	
	public boolean isSuccess(){
		return isSuccess;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/** 添加方法返回结果集 */
	public void addReturnData(String key, Object data){
		if(returnDataMap == null){
			returnDataMap = new HashMap<String, Object>();
		}
		returnDataMap.put(key, data);
	}
	
	/** 获得返回结果集的数据根据KEY */
	public Object getReturnData(String key){
		if(returnDataMap == null){
			return null;
		}
		return returnDataMap.get(key);
	}
	
	public Map<String, Object> getData(){
		return returnDataMap;
	}
	
	public boolean getIsSuccess(){
		return isSuccess;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
