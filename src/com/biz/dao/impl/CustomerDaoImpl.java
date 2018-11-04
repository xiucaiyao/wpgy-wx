package com.biz.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.biz.dao.CustomerDao;
import com.biz.po.CardRecordBean;
import com.biz.po.CustomerAddressBean;
import com.biz.po.CustomerAdviceBean;
import com.biz.po.CustomerBean;
import com.biz.po.OrderCardBean;
import com.biz.po.ProductDescribeBean;
import com.biz.po.VoucherBean;

/**
 * 客户DAO操作实现类
 * 
 * @author 秀才
 */
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao{

	private static Log log = LogFactory.getLog(CustomerDaoImpl.class);
	
	/** 
	 * 查询 点餐会员卡
	 * @param porductQueryBean 产品信息查询Bean
	 */
	public OrderCardBean queryOrderCard(OrderCardBean orderCardBean){
		log.info("查询 点餐会员卡 ");
		return getSqlSession().selectOne("customer.queryOrderCard", orderCardBean);
	}
	
	/**
	 * 添加产品收藏
	 * @param collectionMap 收藏信息Map
	 */
	public int insertCollection(Map<String, Object> collectionMap){
		log.info("添加产品收藏");
		return getSqlSession().insert("customer.insertCollection", collectionMap);
	}
	
	/** 
	 * 查询 会员信息
	 * @param customerBean 会员信息查询Bean
	 */
	public List<CustomerBean> queryCustomer(CustomerBean customerBean){
		log.info("查询 会员信息 ");
		return getSqlSession().selectList("customer.queryCustomer", customerBean);
	}
	
	/** 
	 * 根据ID查询客户信息
	 * @param customerId 会员信息ID
	 */
	public CustomerBean queryCustomerById(String customerId){
		log.info("根据ID查询客户信息 ");
		return getSqlSession().selectOne("customer.queryCustomerById", customerId);
	}
	
	/**
	 * 插入会员信息
	 */
	public int insertCustomer(CustomerBean customerBean){
		log.info("插入会员信息");
		return getSqlSession().insert("customer.insertCustomer", customerBean);
	}
	
	/** 
	 * 查询 会员地址信息
	 * @param customerBean 会员信息查询Bean
	 */
	public List<CustomerAddressBean> queryCustomerAddress(CustomerAddressBean customerAddressBean){
		log.info("查询 会员地址信息 ");
		return getSqlSession().selectList("customer.queryCustomerAddress", customerAddressBean);
	}
	
	/**
	 * 新增会员地址信息
	 */
	public int insertCustomerAddress(CustomerAddressBean customerAddressBean){
		log.info("新增会员地址信息");
		return getSqlSession().insert("customer.insertCustomerAddress", customerAddressBean);
	}
	
	/**
	 * 新增会员卡信息记录
	 */
	public int insertOrderCard(OrderCardBean orderCardBean){
		log.info("新增会员卡信息记录");
		return getSqlSession().insert("customer.insertOrderCard", orderCardBean);
	}
	
	/**
	 * 修改用户点餐会员卡号信息
	 */
	public int updateCustomerOrderCard(CustomerBean customerBean){
		log.info("修改用户点餐会员卡号信息");
		return getSqlSession().update("customer.updateCustomerOrderCard", customerBean);
	}
	
	/**
	 * 修改用户套餐会员卡号信息
	 */
	public int updateCustomerGroupCard(CustomerBean customerBean){
		log.info("修改用户套餐会员卡号信息");
		return getSqlSession().update("customer.updateCustomerGroupCard", customerBean);
	}
	
	/**
	 * 新增充值消费记录 
	 */
	public int insertCardRecord(CardRecordBean cardRecordBean){
		log.info("新增充值消费记录 ");
		return getSqlSession().insert("customer.insertCardRecord", cardRecordBean);
	}
	
	/**
	 * 修改会员卡信息金额
	 */
	public int updateOrderCardMoney(OrderCardBean orderCardBean){
		log.info("修改会员卡信息金额");
		return getSqlSession().update("customer.updateOrderCardMoney", orderCardBean);
	}
	
	/**
	 * 查询产品评论信息
	 * @param productDescribeBean 产品评论信息
	 */
	public List<ProductDescribeBean> queryProductDescribe(ProductDescribeBean productDescribeBean){
		log.info("查询产品评论信息");
		return getSqlSession().selectList("customer.queryProductDescribe", productDescribeBean);
	}
	
	/**
	 * 新增产品评论信息
	 * @param productDescribeBean 产品评论信息
	 */
	public int insertProductDescribe(ProductDescribeBean productDescribeBean){
		log.info("新增产品评论信息");
		return getSqlSession().insert("customer.insertProductDescribe", productDescribeBean);
	}
	
	/** 
	 * 查询用户收藏产品信息
	 * @param collectionMap 查询收藏信息的Map
	 */
	public List<Map<String, Object>> queryCollection(Map<String, Object> collectionMap){
		log.info("查询用户收藏产品信息");
		return getSqlSession().selectList("customer.queryCollection", collectionMap);
	}
	
	/**
	 * 修改商业券状态
	 */
	public int updateVoucherState(VoucherBean voucherBean){
		log.info("修改商业券状态");
		return getSqlSession().update("customer.updateVoucherState", voucherBean);
	}
	
	/**
	 * 修改用户信息
	 */
	public int updateCustomer(CustomerBean customerBean){
		log.info("修改用户信息");
		return getSqlSession().update("customer.updateCustomer", customerBean);
	}
	
	/**
	 * 修改用户密码
	 * @param parameterMap 参数Map
	 */
	public int updateCustomerPassword(Map<String, Object> parameterMap){
		log.info("修改用户密码");
		return getSqlSession().update("customer.updateCustomerPassword", parameterMap);
	}
	
	/**
	 * 修改用户点餐会员卡密码
	 * @param parameterMap 参数Map
	 */
	public int updateOrderCardPassword(Map<String, Object> parameterMap){
		log.info("修改用户点餐会员卡密码");
		return getSqlSession().update("customer.updateOrderCardPassword", parameterMap);
	}
	
	/** 
	 * 分页查询用户收藏产品信息
	 * @param collectionMap 查询收藏信息的Map
	 */
	public List<Map<String, Object>> queryCollectionForPage(Map<String, Object> collectionMap){
		log.info("分页查询用户收藏产品信息");
		return getSqlSession().selectList("customer.queryCollectionForPage", collectionMap);
	}
	
	/** 
	 * 分页查询用户收藏产品记录数
	 * @param collectionMap 查询收藏信息的Map
	 */
	public Integer queryCollectionCount(Map<String, Object> collectionMap){
		log.info("分页查询用户收藏产品记录数");
		return getSqlSession().selectOne("customer.queryCollectionCount", collectionMap);
	}
	
	/**
	 * 新增客户投诉建议数据
	 */
	public int insertCustomerAdvice(CustomerAdviceBean customerAdviceBean){
		log.info("新增客户投诉建议数据");
		return getSqlSession().insert("customer.insertCustomerAdvice", customerAdviceBean);
	}
	
	/** 
	 * 判断客户是否特殊大客户(主要是判断当前特殊客户表中是否有数据)
	 * @param customerId 客户ID
	 */
	public String queryIsSpecialCustomer(String customerId){
		log.info("判断客户是否特殊大客户(主要是判断当前特殊客户表中是否有数据)");
		return getSqlSession().selectOne("customer.queryIsSpecialCustomer", customerId);
	}
	
	/** 
	 * 查询客户购买的充值卡信息
	 */
	public List<Map<String, Object>> queryCustomerRechargeCard(String customerId){
		log.info("查询客户购买的充值卡信息");
		return getSqlSession().selectList("customer.queryCustomerRechargeCard", customerId);
	}
	
	/** 
	 * 查询客户购买的套餐卡信息
	 */
	public List<Map<String, Object>> queryCustomerGroupCard(String customerId){
		log.info("查询客户购买的套餐卡信息");
		return getSqlSession().selectList("customer.queryCustomerGroupCard", customerId);
	}
}
