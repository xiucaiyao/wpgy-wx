package com.biz.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.biz.constants.SysConstants;
import com.biz.po.ProductBean;
import com.biz.vo.OrderBean;

public class SysUtils {

	/**
	 * 验证前端传值过来的字段合法性
	 */
	public static boolean validateFiled(String filed) {
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if (sqlPattern.matcher(filed).find()) {
			return false;
		}
		return true;
	}

	/**
	 * 自动映射字段名称（例如：userName映射成user_name,user_name映射成userName）
	 * 
	 * @param fileName 字段名称
	 */
	public static String mappingFileName(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return null;
		}
		StringBuffer fileNameBuffer = new StringBuffer();
		if (fileName.contains("_")) {
			// 自动转换 userName映射成user_name
			for (int i = 0; i < fileName.length(); i++) {
				char c = fileName.charAt(i);
				if (c == '_') {
					++i;
					if (i < fileName.length()) {
						c = fileName.charAt(i);
						fileNameBuffer.append(Character.toUpperCase(c));
					}
				} else {
					fileNameBuffer.append(c);
				}
			}
		} else {
			// user_name映射成userName
			for (int i = 0; i < fileName.length(); i++) {
				char c = fileName.charAt(i);
				if (Character.isUpperCase(c)) {
					fileNameBuffer.append("_");
					fileNameBuffer.append(Character.toLowerCase(c));
				} else {
					fileNameBuffer.append(c);
				}
			}
		}
		return fileNameBuffer.toString();
	}

	/**
	 * 根据排序List生成排序查询语句
	 * 
	 * @param orderList 排序List
	 * @param isConversion 是否自动转换字段名称
	 */
	public static String generationOrderSql(List<OrderBean> orderList, boolean isConversion) {
		StringBuffer orderSqlBuffer = new StringBuffer();
		if (orderList == null || orderList.isEmpty()) {
			return "";
		}
		for (OrderBean orderBean : orderList) {
			if (orderBean != null && StringUtils.isNotBlank(orderBean.getName())) {
				String orderName = orderBean.getName();
				if (isConversion) {
					orderName = mappingFileName(orderBean.getName());
				}
				if (orderSqlBuffer.length() > 0) {
					orderSqlBuffer.append(", " + orderName);
				} else {
					orderSqlBuffer.append(" order by " + orderName);
				}
				if (orderBean.getIsDesc()) {
					orderSqlBuffer.append(" desc");
				}
			}
		}
		return orderSqlBuffer.toString();
	}

	/**
	 * 根据排序List生成排序查询语句
	 * 
	 * @param orderList 排序List
	 */
	public static String generationOrderSql(List<OrderBean> orderList) {
		return generationOrderSql(orderList, false);
	}

	/**
	 * 根据排序List生成排序查询语句
	 * 
	 * @param orderBean 排序Bean
	 */
	public static String generationOrderSqlOrHql(OrderBean orderBean) {
		String orderSql = "";
		if (orderBean != null && StringUtils.isNotBlank(orderBean.getName())) {
			orderSql = " order by " + orderBean.getName();
			if (orderBean.getIsDesc()) {
				orderSql += " desc";
			}
		}
		return orderSql;
	}

	/** 产品分页JSON */
	public static String generationProductPageJson(List<ProductBean> productList) {
		StringBuffer jsonBuffer = new StringBuffer();
		if (productList != null && !productList.isEmpty()) {
			jsonBuffer.append("[");
			for (ProductBean productBean : productList) {
				if (productBean != null) {
					jsonBuffer.append("{");
					jsonBuffer.append("\"serial\":\"" + productBean.getSerial() + "\"");
					jsonBuffer.append(",\"name\":\"" + productBean.getName() + "\"");
					jsonBuffer.append(",\"productSaleType\":\"" + productBean.getProductSaleType() + "\"");
					jsonBuffer.append(",\"smallpic\":\"" + (productBean.getSmallpic() == null ? "/images/no_pic.jpg" : productBean.getSmallpic()) + "\"");
					jsonBuffer.append(",\"price\":\"" + (productBean.getPrice() == null ? "暂无价格" : productBean.getPrice()) + "\"");
					// jsonBuffer.append(",\"commonPrice\":\""+(productBean.getCommonPrice()==null?"暂无价格":productBean.getCommonPrice())+"\"");
					jsonBuffer.append(",\"spec\":\"" + (productBean.getSpec() == null ? "暂无规格" : productBean.getSpec()) + "\"");
					jsonBuffer.append("},");
				}
			}
			if (jsonBuffer.length() > 1) {
				jsonBuffer.delete(jsonBuffer.length() - 1, jsonBuffer.length());
			}
			jsonBuffer.append("]");
		}
		return jsonBuffer.toString();
	}

	public static String validateSendDate(Date sendDate) {
		String message = null;
		if (sendDate == null) {
			message = "订单配送日期不能为空!";
			return message;
		}
		long sendTimes = sendDate.getTime();
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		//if(hour >= 15 && hour < 16){
		if(hour > -1){
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			long earlyTimes = calendar.getTimeInMillis();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			long lateTimes = calendar.getTimeInMillis();
			if(sendTimes < earlyTimes || lateTimes <= sendTimes){
				return "亲，您的订单已经过期已经不能再支付订单了！";
			}
		} else {
			return "亲，当前时间内已经不能再支付订单了！";
		}
		return null;
	}
	
	/**
	 * 获取订单邮费
	 * <br>其逻辑规则：
	 * <br>1.自提点不需要配送费 
	 * <br>2.订单未满88元，或送货地址超出配送范围，需加10元配送费 
	 * <br>3.超过88元不在配送范围内的地址，也收10元配送费
	 * <br>4.使用新地址时，无法判断是否范围内，只根据是否达到88来决定是否增加10元
	 * @param orderPrice 订单金额
	 * @param addressType 地址类型 0：自提点类型 1.新地址 2.地址在配送范围内 3.地址未在配送范围内
	 */
	public static double getPostagePrice(double orderPrice, int addressType){
		switch (addressType) {
		case 0:
			return 0d;
		case 1:
			if(orderPrice >= SysConstants.FREE_POSTAGE_MONEY){
				return 0d;
			}
			return SysConstants.OUT_RANGE_SEND_MONEY;
		case 2:
			if(orderPrice >= SysConstants.FREE_POSTAGE_MONEY){
				return 0d;
			}
			return SysConstants.OUT_RANGE_SEND_MONEY;
		case 3:
			return SysConstants.OUT_RANGE_SEND_MONEY;
		default:
			return SysConstants.OUT_RANGE_SEND_MONEY;
		}
	}
	
	public static Date getSendDate() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		//if(hour >= 15 && hour < 16){
		if(hour > -1){
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			return calendar.getTime();
		} else {
			return null;
		}
	}
	
	public static String getSendDateStr() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		//if(hour >= 15 && hour < 16){
		if(hour > -1){
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(calendar.getTime());
		} else {
			return null;
		}
	}

	/** 获取随机充值密码 */
	public static String getRandomRechargePassword() {
		char[] passwordArray = new char[8];
		char[] sorceArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random();
		for (int i = 0; i < passwordArray.length; i++) {
			passwordArray[i] = sorceArray[random.nextInt(10)];
		}
		return new String(passwordArray);
	}

	/** 生成订单第三方支付的交易号 */
	public static String generateOutTradeNo() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		char[] no = new char[14];
		String outTradeNo = dateFormat.format(new Date());
		char[] sorceArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] letterArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int i = 0;
		Random random = new Random();
		for (; i < 2; i++) {
			no[i] = letterArray[random.nextInt(letterArray.length)];
		}
		for (; i < 10; i++) {
			no[i] = outTradeNo.charAt(i - 2);
		}
		for (; i < 14; i++) {
			no[i] = sorceArray[random.nextInt(10)];
		}
		return new String(no);
	}

	public static void main(String[] args) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR_OF_DAY, -1);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(dateFormat.format(calendar.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
