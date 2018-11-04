package com.biz.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.biz.dao.ProductDao;
import com.biz.po.DistionaryBean;
import com.biz.po.ProductBean;
import com.biz.po.ScrollingMessageBean;
import com.biz.vo.EnterpriseProductBean;
import com.biz.vo.ProductQueryBean;


/**
 * 产品DAO操作实现类
 * 
 * @author 秀才
 */
@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao{
	
	private static Log log = LogFactory.getLog(ProductDaoImpl.class);
	
	/** 
	 * 产品信息查询 
	 * @param porductQueryBean 产品信息查询Bean
	 */
	public List<ProductBean> productQuery(ProductQueryBean porductQueryBean){
		log.info("产品信息查询 ");
		return getSqlSession().selectList("product.productQuery", porductQueryBean);
	}
	
	/** 
	 * 产品信息查询 
	 * @param porductQueryBean 产品信息查询Bean
	 */
	public Integer productCountQuery(ProductQueryBean porductQueryBean){
		log.info("产品信息查询 ");
		return getSqlSession().selectOne("product.productCountQuery", porductQueryBean);
	}
	
	/** 
	 * 产品的分类查询
	 */
	public List<DistionaryBean> queryProductType(){
		log.info("产品的分类查询 ");
		return getSqlSession().selectList("product.queryProductType");
	}
	
	/** 
	 * 产品信息预览根据Serial
	 * @param productSerial 产品信息编号
	 */
	public ProductBean productPreviewBySerial(String productSerial){
		log.info("产品信息预览根据Serial");
		return getSqlSession().selectOne("product.productPreviewBySerial", productSerial);
	}
	
	/** 
	 * 产品详细根据Serial
	 * @param productSerial 产品信息编号
	 */
	public ProductBean productDetailBySerial(String productSerial){
		log.info("产品详细根据Serial");
		return getSqlSession().selectOne("product.productDetailBySerial", productSerial);
	}
	
	/** 
	 * 产品所有规格价格根据Serial查询
	 * @param productSerial 产品信息编号
	 */
	public List<Map<String, Object>> productSpecPriceBySerial(String productSerial){
		log.info("产品所有规格价格根据Serial查询");
		return getSqlSession().selectList("product.productSpecPriceBySerial", productSerial);
	}
	
	/** 
	 * 滚动信息查询
	 */
	public List<ScrollingMessageBean> scrollingMessageQuery(){
		log.info("产品详细根据Serial");
		return getSqlSession().selectList("product.scrollingMessageQuery");
	}
	
	/** 
	 * 滚动信息根据ID查询
	 * @param scrollingMessageId 滚动信息ID
	 */
	public ScrollingMessageBean queryScrollingMessageById(String scrollingMessageId){
		log.info("滚动信息根据ID查询");
		return getSqlSession().selectOne("product.queryScrollingMessageById", scrollingMessageId);
	}
	
	/** 
	 * 查询产品规格价格
	 * @param parameterMap 查询信息Map参数
	 */
	public List<ProductBean> queryProductSpecPrice(Map<String, Object> parameterMap){
		log.info("查询产品规格价格");
		return getSqlSession().selectList("product.queryProductSpecPrice", parameterMap);
	}

	/** 
	 * 大客户产品信息查询
	 * @param productQueryBean 产品查询信息Bean
	 */
	public List<ProductBean> vipCustomerProductQuery(ProductQueryBean productQueryBean){
		log.info("大客户产品信息查询");
		return getSqlSession().selectList("product.vipCustomerProductQuery", productQueryBean);
	}
	
	/** 
	 * 特殊客户产品信息查询
	 * @param productQueryBean 产品查询信息Bean
	 */
	public List<ProductBean> specialCustomerProductQuery(ProductQueryBean productQueryBean){
		log.info("特殊客户产品信息查询");
		return getSqlSession().selectList("product.specialCustomerProductQuery", productQueryBean);
	}
	
	/** 
	 * 批量查询大客户选择的产品信息 
	 * @param parameterMap 参数信息列表
	 */
	public List<ProductBean> bathQueryVipCustomerProduct(Map<String, Object> parameterMap){
		log.info("批量查询大客户选择的产品信息 ");
		return getSqlSession().selectList("product.bathQueryVipCustomerProduct", parameterMap);
	}
	
	/** 
	 * 批量查询特殊客户选择的产品信息
	 * @param productSerialList 产品编码List
	 */
	public List<ProductBean> bathQuerySpecialCustomerProduct(Map<String, Object> parameterMap){
		log.info("批量查询特殊客户选择的产品信息");
		return getSqlSession().selectList("product.bathQuerySpecialCustomerProduct", parameterMap);
	}
	
	/** 
	 * 摊位客户产品信息查询
	 * @param productQueryBean 产品查询信息Bean
	 */
	public List<ProductBean> marketCustomerProductQuery(ProductQueryBean productQueryBean){
		log.info("特殊客户产品信息查询");
		return getSqlSession().selectList("product.marketCustomerProductQuery", productQueryBean);
	}
	
	/** 
	 * 批量查询客户选择的产品信息
	 * @param productSerialList 产品编码List
	 */
	public List<ProductBean> bathQueryMarketCustomerProduct(List<EnterpriseProductBean> enterpriseProductList){
		log.info("批量查询客户选择的产品信息");
		return getSqlSession().selectList("product.bathQueryMarketCustomerProduct", enterpriseProductList);
	}
	
}
