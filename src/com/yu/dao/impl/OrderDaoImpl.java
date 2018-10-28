package com.yu.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.yu.dao.OrderDao;
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
 * 订单DAO操作实现类
 * 
 * @author 金鱼
 */
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

	private static Log log = LogFactory.getLog(OrderDaoImpl.class);

	/**
	 * 查询订单信息
	 * 
	 * @param orderBean 订单信息查询Bean
	 */
	public List<OrderBean> queryOrder(OrderBean orderBean) {
		log.info("查询订单信息 ");
		return getSqlSession().selectList("order.queryOrder", orderBean);
	}

	/**
	 * 插入订单信息
	 * 
	 * @param orderBean 订单信息Bean
	 */
	public int insertOrder(OrderBean orderBean) {
		log.info("插入订单信息 ");
		return getSqlSession().insert("order.insertOrder", orderBean);
	}

	/**
	 * 修改订单状态
	 * 
	 * @param orderBean 订单信息Bean
	 */
	public int updateOrderState(OrderBean orderBean) {
		log.info("修改订单状态");
		return getSqlSession().update("order.updateOrderState", orderBean);
	}

	/**
	 * 查询订单详细
	 * 
	 * @param orderDetailBean 订单详细信息Bean
	 */
	public List<OrderDetailBean> queryOrderDetail(OrderDetailBean orderDetailBean) {
		log.info("查询订单详细");
		return getSqlSession().selectList("order.queryOrderDetail", orderDetailBean);
	}

	/**
	 * 新增订单详细
	 * 
	 * @param orderDetailBean 订单详细信息Bean
	 */
	public int insertOrderDetail(OrderDetailBean orderDetailBean) {
		log.info("新增订单详细 ");
		return getSqlSession().insert("order.insertOrderDetail", orderDetailBean);
	}

	/**
	 * 查询支付明细
	 * 
	 * @param orderId 订单ID
	 */
	public List<OrderPayDetailBean> queryOrderPayDetail(String orderId) {
		log.info("查询支付明细");
		return getSqlSession().selectList("order.queryOrderPayDetail", orderId);
	}

	/**
	 * 新增支付明细
	 * 
	 * @param orderPayDetailBean 支付明细Bean
	 */
	public int insertOrderPayDetail(OrderPayDetailBean orderPayDetailBean) {
		log.info("新增支付明细 ");
		return getSqlSession().insert("order.insertOrderPayDetail", orderPayDetailBean);
	}

	/**
	 * 查询充值消费记录
	 * 
	 * @param cardRecordBean 充值消费记录信息Bean
	 */
	public List<OrderCardBean> queryCardRecord(CardRecordBean cardRecordBean) {
		log.info("查询充值消费记录");
		return getSqlSession().selectList("order.queryCardRecord", cardRecordBean);
	}

	/**
	 * 新增充值消费记录
	 * 
	 * @param cardRecordBean 充值消费记录信息Bean
	 */
	public int insertCardRecord(CardRecordBean cardRecordBean) {
		log.info("新增充值消费记录");
		return getSqlSession().insert("order.insertCardRecord", cardRecordBean);
	}

	/**
	 * 新增第三方交易平台记录
	 * 
	 * @param cardRecordBean 第三方交易平台记录信息Bean
	 */
	public int insertPayTrade(PayTradeBean payTradeBean) {
		log.info("新增第三方交易平台记录");
		return getSqlSession().insert("order.insertPayTrade", payTradeBean);
	}

	/**
	 * 查询商品券信息记录
	 * 
	 * @param voucherNo 商品券编号
	 */
	public VoucherBean queryVoucher(String voucherNo) {
		log.info("查询商品券信息记录");
		return getSqlSession().selectOne("order.queryVoucher", voucherNo);
	}

	/**
	 * 查询商品券信息根据ID
	 * 
	 * @param voucherId 商品券ID
	 */
	public VoucherBean queryVoucherById(String voucherId) {
		log.info("查询商品券信息记录");
		return getSqlSession().selectOne("order.queryVoucherById", voucherId);
	}

	/**
	 * 查询包装类型价格信息记录
	 * 
	 * @param voucherNo 商品券编号
	 */
	public List<Map<String, Object>> queryPackagePrice(String packagePriceId) {
		log.info("查询包装类型价格信息记录");
		return getSqlSession().selectList("order.queryPackagePrice", packagePriceId);
	}

	/**
	 * 修改第三方交易平台记录
	 * 
	 * @param payTradeBean 第三方交易信息Bean
	 */
	public int updatePayTrade(PayTradeBean payTradeBean) {
		log.info("修改第三方交易平台记录");
		return getSqlSession().update("order.updatePayTrade", payTradeBean);
	}
	
	/**
	 * 修改第三方交易平台记录状态记录状态
	 * 
	 * @param payTradeBean 第三方交易信息Bean
	 */
	public int updatePayTradeStatus(PayTradeBean payTradeBean) {
		log.info("修改第三方交易平台记录状态记录状态");
		return getSqlSession().update("order.updatePayTradeStatus", payTradeBean);
	}

	/**
	 * 查询第三方交易平台记录根据系统支付订单号
	 * <br>由于商户交易号的数据会重复，所以商户交易号定义在第三方交易表的主键
	 * 
	 * @param payTradeId 商户交易号
	 */
	public PayTradeBean queryPayTradeByOutTradeNo(String payTradeId) {
		log.info("查询第三方交易平台记录根据系统支付订单号");
		return getSqlSession().selectOne("order.queryPayTradeByOutTradeNo", payTradeId);
	}
	
	/**
	 * 查询第三方交易平台记录根据订单号
	 * 
	 * @param orderId 订单编号
	 */
	public List<PayTradeBean> queryPayTradeByOrderId(String orderId) {
		log.info("查询第三方交易平台记录根据系统支付订单号");
		return getSqlSession().selectList("order.queryPayTradeByOrderId", orderId);
	}

	/**
	 * 分页查询订单信息
	 * 
	 * @param orderQueryBean 订单信息查询Bean
	 */
	public List<OrderBean> queryOrderForPage(OrderQueryBean orderQueryBean) {
		log.info("分页查询订单信息 ");
		return getSqlSession().selectList("order.queryOrderForPage", orderQueryBean);
	}

	/**
	 * 查询订单数量
	 * 
	 * @param orderBean 订单信息查询Bean
	 */
	public Integer queryOrderCount(OrderQueryBean orderQueryBean) {
		log.info("查询订单数量 ");
		return getSqlSession().selectOne("order.queryOrderCount", orderQueryBean);
	}

	/**
	 * 分页查询充值消费记录
	 * 
	 * @param cardRecordQueryBean 会员卡信息查询Bean
	 */
	public List<OrderBean> queryCardRecordForPage(CardRecordQueryBean cardRecordQueryBean) {
		log.info("分页查询充值消费记录 ");
		return getSqlSession().selectList("order.queryCardRecordForPage", cardRecordQueryBean);
	}

	/**
	 * 查询充值消费记录数
	 * 
	 * @param cardRecordQueryBean 会员卡信息查询Bean
	 */
	public Integer queryCardRecordCount(CardRecordQueryBean cardRecordQueryBean) {
		log.info("查询充值消费记录数 ");
		return getSqlSession().selectOne("order.queryCardRecordCount", cardRecordQueryBean);
	}

	/**
	 * 查询自提点信息列表
	 */
	public List<OwnReceivePlaceBean> queryOwnReceivePlace() {
		log.info("查询自提点信息列表 ");
		return getSqlSession().selectList("order.queryOwnReceivePlace");
	}

	/**
	 * 根据自ID查询自提点信息
	 * 
	 * @param ownReceivePlaceId 自提点ID
	 */
	public OwnReceivePlaceBean queryOwnReceivePlaceById(String ownReceivePlaceId) {
		log.info("根据自ID查询自提点信息");
		return getSqlSession().selectOne("order.queryOwnReceivePlaceById", ownReceivePlaceId);
	}

	/**
	 * 根据订单ID删除订单产品信息
	 * 
	 * @param orderId 订单ID
	 */
	public int deleteOrderDetail(String orderId) {
		log.info("根据订单ID删除订单产品信息");
		return getSqlSession().delete("order.deleteOrderDetail", orderId);
	}
	
	/**
	 * 根据订单ID删除订单信息
	 * 
	 * @param orderId 订单ID
	 */
	public int deleteOrder(String orderId) {
		log.info("根据订单ID删除订单产品信息");
		return getSqlSession().delete("order.deleteOrder", orderId);
	}

	/**
	 * 修改订单日期 价格等信息
	 * 
	 * @param orderBean 订单信息
	 */
	public int updateOrderInfo(OrderBean orderBean) {
		log.info("修改订单日期 价格等信息");
		return getSqlSession().update("order.updateOrderInfo", orderBean);
	}

}
