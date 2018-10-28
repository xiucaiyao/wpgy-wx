package com.yu.dao;

import java.util.List;
import java.util.Map;

import com.yu.po.CardRecordBean;
import com.yu.po.OrderBean;
import com.yu.po.OrderCardBean;
import com.yu.po.OrderDetailBean;
import com.yu.po.OrderPayDetailBean;
import com.yu.po.OwnReceivePlaceBean;
import com.yu.po.PayTradeBean;
import com.yu.po.VoucherBean;
import com.yu.vo.CardRecordQueryBean;
import com.yu.vo.OrderQueryBean;

/**
 * 订单DAO操作接口类
 * 
 * @author 金鱼
 */
public interface OrderDao extends BaseDao {

	/**
	 * 查询订单信息
	 * 
	 * @param orderBean 订单信息查询Bean
	 */
	public List<OrderBean> queryOrder(OrderBean orderBean);

	/**
	 * 插入订单信息
	 * 
	 * @param orderBean 订单信息Bean
	 */
	public int insertOrder(OrderBean orderBean);

	/**
	 * 修改订单状态
	 * 
	 * @param orderBean 订单信息Bean
	 */
	public int updateOrderState(OrderBean orderBean);

	/**
	 * 查询订单详细
	 * 
	 * @param orderDetailBean 订单详细信息Bean
	 */
	public List<OrderDetailBean> queryOrderDetail(OrderDetailBean orderDetailBean);

	/**
	 * 新增订单详细
	 * 
	 * @param orderDetailBean 订单详细信息Bean
	 */
	public int insertOrderDetail(OrderDetailBean orderDetailBean);

	/**
	 * 查询支付明细
	 * 
	 * @param orderId 订单ID
	 */
	public List<OrderPayDetailBean> queryOrderPayDetail(String orderId);

	/**
	 * 新增支付明细
	 * 
	 * @param orderPayDetailBean 支付明细Bean
	 */
	public int insertOrderPayDetail(OrderPayDetailBean orderPayDetailBean);

	/**
	 * 查询充值消费记录
	 * 
	 * @param cardRecordBean 充值消费记录信息Bean
	 */
	public List<OrderCardBean> queryCardRecord(CardRecordBean cardRecordBean);

	/**
	 * 新增充值消费记录
	 * 
	 * @param cardRecordBean 充值消费记录信息Bean
	 */
	public int insertCardRecord(CardRecordBean cardRecordBean);

	/**
	 * 新增第三方交易平台记录
	 * 
	 * @param cardRecordBean 第三方交易平台记录信息Bean
	 */
	public int insertPayTrade(PayTradeBean payTradeBean);

	/**
	 * 查询商品券信息记录
	 * 
	 * @param voucherNo 商品券编号
	 */
	public VoucherBean queryVoucher(String voucherNo);

	/**
	 * 查询商品券信息根据ID
	 * 
	 * @param voucherId 商品券ID
	 */
	public VoucherBean queryVoucherById(String voucherId);

	/**
	 * 查询包装类型价格信息记录
	 * 
	 * @param voucherNo 商品券编号
	 */
	public List<Map<String, Object>> queryPackagePrice(String packagePriceId);

	/**
	 * 修改第三方交易平台记录
	 * 
	 * @param voucherNo 商品券编号
	 */
	public int updatePayTrade(PayTradeBean payTradeBean);
	
	/**
	 * 修改第三方交易平台记录状态记录状态
	 * 
	 * @param payTradeBean 第三方交易信息Bean
	 */
	public int updatePayTradeStatus(PayTradeBean payTradeBean);

	/**
	 * 查询第三方交易平台记录根据系统支付订单号
	 * <br>由于商户交易号的数据会重复，所以商户交易号定义在第三方交易表的主键
	 * 
	 * @param payTradeId 商户交易号
	 */
	public PayTradeBean queryPayTradeByOutTradeNo(String payTradeId);
	
	/**
	 * 查询第三方交易平台记录根据订单号
	 * 
	 * @param orderId 订单编号
	 */
	public List<PayTradeBean> queryPayTradeByOrderId(String orderId);

	/**
	 * 分页查询订单信息
	 * 
	 * @param orderBean 订单信息查询Bean
	 */
	public List<OrderBean> queryOrderForPage(OrderQueryBean orderQueryBean);

	/**
	 * 查询订单数量
	 * 
	 * @param orderBean 订单信息查询Bean
	 */
	public Integer queryOrderCount(OrderQueryBean orderQueryBean);

	/**
	 * 分页查询充值消费记录
	 * 
	 * @param cardRecordQueryBean 会员卡信息查询Bean
	 */
	public List<OrderBean> queryCardRecordForPage(CardRecordQueryBean cardRecordQueryBean);

	/**
	 * 查询充值消费记录数
	 * 
	 * @param cardRecordQueryBean 会员卡信息查询Bean
	 */
	public Integer queryCardRecordCount(CardRecordQueryBean cardRecordQueryBean);

	/**
	 * 查询自提点信息列表
	 */
	public List<OwnReceivePlaceBean> queryOwnReceivePlace();

	/**
	 * 根据自ID查询自提点信息
	 * 
	 * @param ownReceivePlaceId 自提点ID
	 */
	public OwnReceivePlaceBean queryOwnReceivePlaceById(String ownReceivePlaceId);

	/**
	 * 根据订单ID删除订单产品信息
	 * 
	 * @param orderId 订单ID
	 */
	public int deleteOrderDetail(String orderId);
	
	/**
	 * 根据订单ID删除订单信息
	 * 
	 * @param orderId 订单ID
	 */
	public int deleteOrder(String orderId);

	/**
	 * 修改订单日期 价格等信息
	 * 
	 * @param orderBean 订单信息
	 */
	public int updateOrderInfo(OrderBean orderBean);
}
