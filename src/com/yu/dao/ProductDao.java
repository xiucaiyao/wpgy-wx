package com.yu.dao;

import java.util.List;
import java.util.Map;

import com.yu.po.DistionaryBean;
import com.yu.po.ProductBean;
import com.yu.po.ScrollingMessageBean;
import com.yu.vo.EnterpriseProductBean;
import com.yu.vo.ProductQueryBean;

/**
 * 产品DAO接口
 * 
 * @author 金鱼
 */
public interface ProductDao extends BaseDao{
	
	/** 
	 * 产品信息查询 
	 * @param porductQueryBean 产品信息查询Bean
	 */
	public List<ProductBean> productQuery(ProductQueryBean porductQueryBean);
	
	/** 
	 * 产品信息查询 
	 * @param porductQueryBean 产品信息查询Bean
	 */
	public Integer productCountQuery(ProductQueryBean porductQueryBean);
	
	/** 
	 * 产品的分类查询
	 */
	public List<DistionaryBean> queryProductType();
	
	/** 
	 * 产品信息预览根据Serial
	 * @param productSerial 产品信息编号
	 */
	public ProductBean productPreviewBySerial(String productSerial);
	
	/** 
	 * 产品详细根据Serial
	 * @param productSerial 产品信息编号
	 */
	public ProductBean productDetailBySerial(String productSerial);
	
	/** 
	 * 产品所有规格价格根据Serial查询
	 * @param productSerial 产品信息编号
	 */
	public List<Map<String, Object>> productSpecPriceBySerial(String productSerial);
	
	/** 
	 * 滚动信息查询
	 */
	public List<ScrollingMessageBean> scrollingMessageQuery();
	
	/** 
	 * 滚动信息根据ID查询
	 * @param scrollingMessageId 滚动信息ID
	 */
	public ScrollingMessageBean queryScrollingMessageById(String scrollingMessageId);
	
	/** 
	 * 查询产品规格价格
	 * @param parameterMap 查询信息Map参数
	 */
	public List<ProductBean> queryProductSpecPrice(Map<String, Object> parameterMap);
	
	/** 
	 * 大客户产品信息查询
	 * @param productQueryBean 产品查询信息Bean
	 */
	public List<ProductBean> vipCustomerProductQuery(ProductQueryBean productQueryBean);
	
	/** 
	 * 特殊客户产品信息查询
	 * @param productQueryBean 产品查询信息Bean
	 */
	public List<ProductBean> specialCustomerProductQuery(ProductQueryBean productQueryBean);
	
	/** 
	 * 批量查询大客户选择的产品信息 
	 * @param parameterMap 参数信息列表
	 */
	public List<ProductBean> bathQueryVipCustomerProduct(Map<String, Object> parameterMap);
	
	/** 
	 * 批量查询特殊客户选择的产品信息
	 * @param parameterMap 参数信息列表
	 */
	public List<ProductBean> bathQuerySpecialCustomerProduct(Map<String, Object> parameterMap);
	
	/** 
	 * 摊位客户产品信息查询
	 * @param productQueryBean 产品查询信息Bean
	 */
	public List<ProductBean> marketCustomerProductQuery(ProductQueryBean productQueryBean);
	
	/** 
	 * 批量查询特殊客户选择的产品信息
	 * @param enterpriseProductList 选中产品List
	 */
	public List<ProductBean> bathQueryMarketCustomerProduct(List<EnterpriseProductBean> enterpriseProductList);
	
}
