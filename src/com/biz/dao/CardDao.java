package com.biz.dao;

import java.util.List;
import java.util.Map;

import com.biz.po.CardTypeBean;
import com.biz.po.VoucherBean;


/**
 * 会员卡DAO接口
 * 
 * @author 秀才
 */
public interface CardDao extends BaseDao{

	/** 
	 * 查询充值卡面值类型
	 */
	public List<CardTypeBean> queryRechargeCardType();
	
	/** 
	 * 查看充值卡类型详细信息
	 */
	public CardTypeBean queryRechargeCardTypeById(String cardTypeId);
	
	/** 
	 * 新增一张充值卡
	 * @param voucherBean 券信息Bean
	 */
	public int insertRechargeCard(VoucherBean voucherBean);
	
	/** 
	 * 修改充值卡购买状态
	 * @param voucherBean 券信息Bean
	 */
	public int updateRechargeCardState(VoucherBean voucherBean);
	
	/** 
	 * 删除还未支付的充值卡信息
	 * @param voucherBean 券信息Bean
	 */
	public int deleteRechargeCardById(String cardTypeId);
	
	/** 
	 * 查询套餐卡面值类型
	 */
	public List<CardTypeBean> queryGroupCardType();
	
	/** 
	 * 查看套餐卡类型详细
	 * @param cardTypeId 套餐卡类型ID
	 */
	public CardTypeBean queryGroupCardTypeById(String cardTypeId);
	
	/** 
	 * 查看套餐卡类型详细
	 * @param cardTypeId 套餐卡类型ID
	 */
	public Map<String, Object> getGroupCard(String cardTypeId);
	
	/** 
	 * 删除套餐交易记录
	 * @param groupCardId 套餐卡ID
	 */
	public int deletePayTradeByGroupCardId(String groupCardId);
	
}
