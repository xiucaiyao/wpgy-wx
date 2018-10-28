package com.yu.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yu.dao.GroupDao;

/**
 * 套餐DAO操作实现类
 * 
 * @author 金鱼
 */
@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl implements GroupDao{

	private static Log log = LogFactory.getLog(GroupDaoImpl.class);
	
	/** 
	 * 查询用户蔬菜套餐信息
	 * @param groupCardId 套餐卡ID
	 */
	public List<Map<String, Object>> queryGroupCard(String groupCardId){
		log.info("查询 点餐会员卡 ");
		return getSqlSession().selectList("group.queryGroupCard", groupCardId);
	}
	
	/** 
	 * 查询套餐数量
	 * @param groupCardId 套餐卡ID
	 */
	public Integer queryGroupNumber(String groupId){
		log.info("查询套餐数量 ");
		return getSqlSession().selectOne("group.queryGroupNumber", groupId);
	}
	
	/** 
	 * 查询可选套餐产品列表 
	 */
	public List<Map<String, Object>> queryGroupOptionalProduct(){
		log.info("查询可选套餐产品列表 ");
		return getSqlSession().selectList("group.queryGroupOptionalProduct");
	}
	
	/** 
	 * 查询可选套餐产品列表 
	 */
	public Map<String, Object> queryGroupOrder(Map<String, Object> parameterMap){
		log.info("查询可选套餐产品列表 ");
		return getSqlSession().selectOne("group.queryGroupOrder", parameterMap);
	}
	
	/** 
	 * 根据ID查询套餐卡信息
	 */
	public Map<String, Object> queryGroupCardById(String groupCardId){
		log.info("根据ID查询套餐卡 ");
		return getSqlSession().selectOne("group.queryGroupCardById", groupCardId);
	}
	
	/** 
	 * 根据套餐卡号查询套餐卡
	 */
	public Map<String, Object> queryGroupCardByCardNo(String groupCardNo){
		log.info("根据套餐卡号查询套餐卡");
		return getSqlSession().selectOne("group.queryGroupCardByCardNo", groupCardNo);
	}
	
	/** 
	 * 查询套餐详细信息
	 * @param groupCardNo 套餐卡卡号
	 */
	public List<Map<String, Object>> queryGroupCardDetail(String groupCardNo){
		log.info("查询套餐详细信息");
		return getSqlSession().selectList("group.queryGroupCardDetail", groupCardNo);
	}
	
	/** 
	 * 修改套餐卡购买状态
	 * @param parameterMap 套餐卡参数Map
	 */
	public int updateGroupCardState(Map<String, Object> parameterMap){
		log.info("修改套餐卡购买状态");
		return getSqlSession().update("group.updateGroupCardState", parameterMap);
	}
	
	/** 
	 * 修改套餐卡详细次数
	 * @param parameterMapn 套餐卡详细信息参数Map
	 */
	public int updateGroupCardDetailTimes(Map<String, Object> parameterMap){
		log.info("修改套餐卡详细次数");
		return getSqlSession().update("group.updateGroupCardDetailTimes", parameterMap);
	}

	/** 
	 * 修改套餐卡详细配送信息
	 * @param parameterMapn 套餐卡详细信息参数Map
	 */
	public int updateGroupCardDetailSend(Map<String, Object> parameterMap){
		log.info("修改套餐卡详细配送信息");
		return getSqlSession().update("group.updateGroupCardDetailSend", parameterMap);
	}
	
	/** 
	 * 新增套餐卡详细配送信息
	 * @param parameterMapn 套餐卡详细信息参数Map
	 */
	public int insertGroupCardDetail(Map<String, Object> parameterMap){
		log.info("新增套餐卡详细配送信息");
		return getSqlSession().insert("group.insertGroupCardDetail", parameterMap);
	}
	
	/** 
	 * 根据ID查询套餐详细信息
	 * @param groupCardDetailId 套餐卡详细信息ID
	 */
	public Map<String, Object> queryGroupCardDetailById(String groupCardDetailId){
		log.info("根据ID查询套餐详细信息");
		return getSqlSession().selectOne("group.queryGroupCardDetailById", groupCardDetailId);
	}
	
	/** 
	 * 修改套餐卡金额
	 * @param parameterMap 套餐卡详细信息ID
	 */
	public Map<String, Object> updateGroupCardMoney(Map<String, Object> parameterMap){
		log.info("修改套餐卡金额");
		return getSqlSession().selectOne("group.updateGroupCardMoney", parameterMap);
	}

	/** 
	 * 查询套餐免费产品
	 * @param groupCardDetailId 套餐卡详细信息ID
	 */
	public List<Map<String, Object>> queryGroupFreeProduct(String groupId){
		log.info("查询套餐免费产品");
		return getSqlSession().selectList("group.queryGroupFreeProduct", groupId);
	}
}
