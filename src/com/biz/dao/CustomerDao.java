package com.biz.dao;

import java.util.List;
import java.util.Map;

import com.biz.po.CardRecordBean;
import com.biz.po.CustomerAddressBean;
import com.biz.po.CustomerAdviceBean;
import com.biz.po.CustomerBean;
import com.biz.po.OrderCardBean;
import com.biz.po.ProductDescribeBean;
import com.biz.po.VoucherBean;

/**
 * 客户DAO接口
 * 
 * @author 秀才
 */
public interface CustomerDao extends BaseDao{
	
	/** 
	 * 查询 点餐会员卡
	 * @param porductQueryBean 产品信息查询Bean
	 */
	public OrderCardBean queryOrderCard(OrderCardBean orderCardBean);
	
	/**
	 * 添加产品收藏
	 * @param collectionMap 收藏信息Map
	 */
	public int insertCollection(Map<String, Object> collectionMap);
	
	/** 
	 * 会员信息
	 * @param porductQueryBean 产品信息查询Bean
	 */
	public List<CustomerBean> queryCustomer(CustomerBean customerBean);
	
	/**
	 * 插入会员信息
	 */
	public int insertCustomer(CustomerBean customerBean);
	
	/** 
	 * 查询 会员地址信息
	 * @param customerBean 会员信息查询Bean
	 */
	public List<CustomerAddressBean> queryCustomerAddress(CustomerAddressBean customerAddressBean);
	
	/**
	 * 新增会员地址信息
	 */
	public int insertCustomerAddress(CustomerAddressBean customerAddressBean);
	
	/**
	 * 新增会员卡信息记录
	 */
	public int insertOrderCard(OrderCardBean orderCardBean);
	
	/**
	 * 修改用户点餐会员卡号信息
	 */
	public int updateCustomerOrderCard(CustomerBean customerBean);
	
	/**
	 * 修改用户套餐会员卡号信息
	 */
	public int updateCustomerGroupCard(CustomerBean customerBean);
	
	/**
	 * 新增充值消费记录 
	 */
	public int insertCardRecord(CardRecordBean cardRecordBean);
	
	/**
	 * 修改会员卡信息金额
	 */
	public int updateOrderCardMoney(OrderCardBean orderCardBean);
	
	/**
	 * 查询产品评论信息
	 * @param productDescribeBean 产品评论信息
	 */
	public List<ProductDescribeBean> queryProductDescribe(ProductDescribeBean productDescribeBean);
	
	/**
	 * 新增产品评论信息
	 * @param productDescribeBean 产品评论信息
	 */
	public int insertProductDescribe(ProductDescribeBean productDescribeBean);
	
	/** 
	 * 查询用户收藏产品信息
	 * @param collectionMap 查询收藏信息的Map
	 */
	public List<Map<String, Object>> queryCollection(Map<String, Object> collectionMap);
	
	/**
	 * 修改商业券状态
	 */
	public int updateVoucherState(VoucherBean voucherBean);
	
	/**
	 * 修改用户信息
	 */
	public int updateCustomer(CustomerBean customerBean);
	
	/** 
	 * 根据ID查询客户信息
	 * @param customerId 会员信息ID
	 */
	public CustomerBean queryCustomerById(String customerId);
	
	/**
	 * 修改用户密码
	 * @param parameterMap 参数Map
	 */
	public int updateCustomerPassword(Map<String, Object> parameterMap);
	
	/**
	 * 修改用户点餐会员卡密码
	 * @param parameterMap 参数Map
	 */
	public int updateOrderCardPassword(Map<String, Object> parameterMap);
	
	/** 
	 * 分页查询用户收藏产品信息
	 * @param collectionMap 查询收藏信息的Map
	 */
	public List<Map<String, Object>> queryCollectionForPage(Map<String, Object> collectionMap);
	
	/** 
	 * 分页查询用户收藏产品记录数
	 * @param collectionMap 查询收藏信息的Map
	 */
	public Integer queryCollectionCount(Map<String, Object> collectionMap);
	
	/**
	 * 新增客户投诉建议数据
	 */
	public int insertCustomerAdvice(CustomerAdviceBean customerAdviceBean);
	
	/** 
	 * 判断客户是否特殊大客户(主要是判断当前特殊客户表中是否有数据)
	 * @param customerId 客户ID
	 */
	public String queryIsSpecialCustomer(String customerId);
	
	/** 
	 * 查询客户购买的充值卡信息
	 */
	public List<Map<String, Object>> queryCustomerRechargeCard(String customerId);
	
	/** 
	 * 查询客户购买的套餐卡信息
	 */
	public List<Map<String, Object>> queryCustomerGroupCard(String customerId);

}
