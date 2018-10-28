package com.yu.dao;

import java.util.List;
import java.util.Map;

/**
 * 套餐DAO接口
 * 
 * @author 金鱼
 */
public interface GroupDao extends BaseDao{

	/** 
	 * 查询用户蔬菜套餐信息
	 * @param groupCardId 套餐卡ID
	 */
	public List<Map<String, Object>> queryGroupCard(String groupCardId);
	
	/** 
	 * 查询套餐数量
	 * @param groupCardId 套餐卡ID
	 */
	public Integer queryGroupNumber(String groupId);
	
	/** 
	 * 查询可选套餐产品列表 
	 */
	public List<Map<String, Object>> queryGroupOptionalProduct();
	
	/** 
	 * 查询套餐详细信息
	 * @param groupCardNo 套餐卡卡号
	 */
	public List<Map<String, Object>> queryGroupCardDetail(String groupCardNo);
	
	/** 
	 * 根据套餐卡号查询套餐卡
	 */
	public Map<String, Object> queryGroupCardByCardNo(String groupCardNo);
	
	/** 
	 * 查询可选套餐产品列表 
	 */
	public Map<String, Object> queryGroupOrder(Map<String, Object> parameterMap);
	
	/** 
	 * 根据ID查询套餐卡信息
	 */
	public Map<String, Object> queryGroupCardById(String groupCardId);
	
	/** 
	 * 修改充值卡购买状态
	 * @param parameterMap 套餐卡参数Map
	 */
	public int updateGroupCardState(Map<String, Object> parameterMap);
	
	/** 
	 * 修改套餐卡详细次数
	 * @param parameterMapn 套餐卡详细信息参数Map
	 */
	public int updateGroupCardDetailTimes(Map<String, Object> parameterMap);
	
	/** 
	 * 修改套餐卡详细配送信息
	 * @param parameterMapn 套餐卡详细信息参数Map
	 */
	public int updateGroupCardDetailSend(Map<String, Object> parameterMap);
	
	/** 
	 * 新增套餐卡详细配送信息
	 * @param parameterMapn 套餐卡详细信息参数Map
	 */
	public int insertGroupCardDetail(Map<String, Object> parameterMap);
	
	/** 
	 * 根据ID查询套餐详细信息
	 * @param groupCardDetailId 套餐卡详细信息ID
	 */
	public Map<String, Object> queryGroupCardDetailById(String groupCardDetailId);
	
	/** 
	 * 修改套餐卡金额
	 * @param parameterMap 套餐卡详细信息ID
	 */
	public Map<String, Object> updateGroupCardMoney(Map<String, Object> parameterMap);
	
	/** 
	 * 查询套餐免费产品
	 * @param groupCardDetailId 套餐卡详细信息ID
	 */
	public List<Map<String, Object>> queryGroupFreeProduct(String groupId);
	
}
