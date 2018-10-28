package com.yu.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yu.dao.CardDao;
import com.yu.po.CardTypeBean;
import com.yu.po.VoucherBean;

/**
 * 会员卡DAO操作实现类
 * 
 * @author 金鱼
 */
@Repository("cardDaoImpl")
public class CardDaoImpl extends BaseDaoImpl implements CardDao{

	private static Log log = LogFactory.getLog(CardDaoImpl.class);
	
	/** 
	 * 查询充值卡面值类型
	 */
	public List<CardTypeBean> queryRechargeCardType(){
		log.info("查询充值卡面值类型");
		return getSqlSession().selectList("card.queryRechargeCardType");
	}
	
	/** 
	 * 查看充值卡类型详细信息
	 */
	public CardTypeBean queryRechargeCardTypeById(String cardTypeId){
		log.info("查看充值卡类型详细信息");
		return getSqlSession().selectOne("card.queryRechargeCardTypeById", cardTypeId);
	}
	
	/** 
	 * 新增一张充值卡
	 * @param voucherBean 券信息Bean
	 */
	public int insertRechargeCard(VoucherBean voucherBean){
		log.info("新增一张充值卡");
		return getSqlSession().insert("card.insertRechargeCard", voucherBean);
	}
	
	/** 
	 * 修改充值卡购买状态
	 * @param voucherBean 券信息Bean
	 */
	public int updateRechargeCardState(VoucherBean voucherBean){
		log.info("修改充值卡购买状态");
		return getSqlSession().update("card.updateRechargeCardState", voucherBean);
	}
	
	/** 
	 * 删除还未支付的充值卡信息
	 * @param voucherId 券信息ID
	 */
	public int deleteRechargeCardById(String voucherId){
		log.info("删除还未支付的充值卡信息");
		return getSqlSession().update("card.deleteRechargeCardById", voucherId);
	}
	
	/** 
	 * 查询套餐卡面值类型
	 */
	public List<CardTypeBean> queryGroupCardType(){
		log.info("查询套餐卡面值类型");
		return getSqlSession().selectList("card.queryGroupCardType");
	}
	
	/** 
	 * 查看套餐卡类型详细
	 * @param cardTypeId 套餐卡类型ID
	 */
	public CardTypeBean queryGroupCardTypeById(String cardTypeId){
		log.info("查看套餐卡类型详细");
		return getSqlSession().selectOne("card.queryGroupCardTypeById", cardTypeId);
	}
	
	/** 
	 * 查看套餐卡类型详细
	 * @param cardTypeId 套餐卡类型ID
	 */
	public Map<String, Object> getGroupCard(String cardTypeId){
		log.info("查看套餐卡类型详细");
		return getSqlSession().selectOne("card.getGroupCard", cardTypeId);
	}
	
	/** 
	 * 删除套餐交易记录
	 * @param groupCardId 套餐卡ID
	 */
	public int deletePayTradeByGroupCardId(String groupCardId){
		log.info("删除套餐交易记录");
		return getSqlSession().update("card.deletePayTradeByGroupCardId", groupCardId);
	}
}
