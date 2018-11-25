package com.mobile.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.dao.CustomerDao;
import com.biz.po.CustomerAddressBean;
import com.biz.po.CustomerBean;
import com.biz.utils.PinyinUtils;
import com.biz.vo.ReturnResultBean;
import com.mobile.dao.FansDao;
import com.mobile.po.wx.WeixinFansBean;
import com.mobile.service.FansService;
import com.mobile.vo.wx.LoginFansBean;
import com.mobile.vo.wx.RegistFansBean;

/**
 * 粉丝绑定 注册等Service操作类
 * 
 * @author 秀才
 */
@Service("fansService")
public class FansServiceImpl implements FansService {

	private static Log log = LogFactory.getLog(FansServiceImpl.class);

	@Resource
	private FansDao fansDao;

	@Resource
	private CustomerDao customerDao;

	/**
	 * 查询粉丝用户对应的用户信息
	 * 
	 * @param openId 粉丝在微信公众号的唯一标识
	 */
	public ReturnResultBean queryFansByOpenId(String openId) {
		log.info("查询粉丝用户对应的用户信息");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if (StringUtils.isBlank(openId)) {
			returnResultBean.setMessage("openId不能为空");
			return returnResultBean;
		}
		List<WeixinFansBean> weixinFansList = fansDao.queryFansByOpenId(openId);
		if (weixinFansList != null && weixinFansList.size() == 1) {
			returnResultBean.operationSuccess();
			returnResultBean.addReturnData("weixinFansBean", weixinFansList.get(0));
		}
		return returnResultBean;
	}

	/**
	 * 绑定粉丝用户信息
	 * 
	 * @param loginFansBean 粉丝登录信息Bean
	 */
	@Transactional(rollbackFor = { Exception.class })
	public ReturnResultBean bindFans(LoginFansBean loginFansBean) {
		log.info("绑定粉丝用户信息");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		String loginName = loginFansBean.getLoginName();
		String password = loginFansBean.getPassword();
		String openId = loginFansBean.getOpenId();
		if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password) || StringUtils.isBlank(openId)) {
			returnResultBean.setMessage("粉丝登录信息验证不能通过...");
			return returnResultBean;
		}
		CustomerBean customerBean = new CustomerBean();
		log.info("用户名："+loginName + "===========用户密码"+password);
		customerBean.setLoginName(loginName);
		customerBean.setPassword(password);
		List<CustomerBean> customerList = customerDao.queryCustomer(customerBean);
		if(customerList == null){
			log.info("没有查询到数据............."+customerList);
		} else if(customerList.isEmpty()){
			log.info("数据为空..........");
		} else {
			log.info("数据的长度.........."+customerList.size());
		}
		
		if (customerList == null || customerList.size() != 1) {
			returnResultBean.setMessage("用户名或密码不存在！");
			return returnResultBean;
		}
		CustomerBean dbCustomerBean = customerList.get(0);
//		if (!"大客户".equals(dbCustomerBean.getCustomerType()) || !"3".equals(dbCustomerBean.getVipCustomerType())) {
//			returnResultBean.setMessage("只有用户才可以登录！");
//			return returnResultBean;
//		}
		List<WeixinFansBean> weixinFansList = fansDao.queryFansByOpenId(openId);
		if (weixinFansList != null && !weixinFansList.isEmpty()) {
			returnResultBean.setMessage("您已经绑定我们的电商平台无需登录！");
			returnResultBean.addReturnData("weixinFansBean", weixinFansList.get(0));
			returnResultBean.operationSuccess();
			return returnResultBean;
		}
		WeixinFansBean weixinFansBean = new WeixinFansBean();
		weixinFansBean.setWeixinFansId(UUID.randomUUID().toString());
		weixinFansBean.setCustomerId(dbCustomerBean.getCustomerId());
		weixinFansBean.setCustomerType("3");
		weixinFansBean.setOpenId(openId);
		weixinFansBean.setUnionId(loginFansBean.getUnionId());
		weixinFansBean.setCreateTime(new Date());
		weixinFansBean.setRemark("微信端粉丝用户登录绑定");
		fansDao.insertWeixinFans(weixinFansBean);
		returnResultBean.addReturnData("weixinFansBean", weixinFansBean);
		returnResultBean.operationSuccess();
		return returnResultBean;
	}
	
	/**
	 * 验证用户名是否存在
	 * @param customerBean 用户信息Bean
	 */
	public ReturnResultBean validateNameIsExist(String loginName){
		log.info("验证用户名是否存在");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if(StringUtils.isBlank(loginName)){
			returnResultBean.setMessage("登录用户名不能为空！");
			return returnResultBean;
		}
		CustomerBean customerBean = new CustomerBean();
		customerBean.setLoginName(loginName);
		List<CustomerBean> customerList = customerDao.queryCustomer(customerBean);
		if(customerList != null && !customerList.isEmpty()){
			returnResultBean.setMessage("登录用户名已经存在，请重新填写！");
			return returnResultBean;
		}
		returnResultBean.operationSuccess();
		returnResultBean.setMessage("用户名可以使用!");
		return returnResultBean;
	}

	/**
	 * 粉丝注册
	 * 
	 * @param registFansBean 粉丝注册Bean
	 * @param openId 粉丝唯一标识
	 */
	public ReturnResultBean regist(RegistFansBean registFansBean, String openId) {
		log.info("绑定粉丝用户信息");
		ReturnResultBean returnResultBean = ReturnResultBean.newInstance();
		if (registFansBean == null) {
			returnResultBean.setMessage("注册信息验证不能通过...");
			return returnResultBean;
		}
		List<WeixinFansBean> weixinFansList = fansDao.queryFansByOpenId(openId);
		if (weixinFansList != null && weixinFansList.size() == 1) {
			returnResultBean.setMessage("您已经绑定我们的电商平台，不能再进行注册。请退出重新进入...");
			return returnResultBean;
		}
		String name = registFansBean.getName();
		String gender = registFansBean.getGender();
		String loginName = registFansBean.getLoginName();
		String password = registFansBean.getPassword();
		String phone = registFansBean.getPhone();
		String address = registFansBean.getAddress();
		if (StringUtils.isBlank(name) || StringUtils.isBlank(gender) || StringUtils.isBlank(loginName) || StringUtils.isBlank(password) || StringUtils.isBlank(phone)) {
			returnResultBean.setMessage("注册信息验证不能通过...");
			return returnResultBean;
		}
		CustomerBean queryCustomerBean = new CustomerBean();
		queryCustomerBean.setLoginName(loginName);
		List<CustomerBean> customerList = customerDao.queryCustomer(queryCustomerBean);
		if(customerList != null && !customerList.isEmpty()){
			returnResultBean.setMessage("登录用户名已经存在，请重新填写！");
			return returnResultBean;
		}
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCustomerId(fansDao.getTableId("TB_CUSTOMER"));
		customerBean.setName(name);
		customerBean.setPhone(phone);
		customerBean.setCustomerType("wx");
		customerBean.setLoginName(loginName);
		customerBean.setPassword(password);
		customerBean.setNameFirstHeadLetter(PinyinUtils.getPinYinHeadChar(name));
		customerBean.setMemo(address);
		customerBean.setCreateTime(new Date());
		customerBean.setCreateUser(customerBean.getCustomerId());
		customerBean.setLinkman(name);
		customerBean.setLinkmanTel(phone);
		customerDao.insertCustomer(customerBean);
		returnResultBean.operationSuccess();
		
		//add by xiucai at 20181125 for "注册的时候保存客户地址信息";begin
		CustomerAddressBean customerAddressBean = new CustomerAddressBean();
		customerAddressBean.setCustomerAddressId(fansDao.getTableId("TB_CUSTOMER_ADDRESS"));
		customerAddressBean.setCustomerId(customerBean.getCustomerId());
		customerAddressBean.setLinkman(customerBean.getLinkman());
		customerAddressBean.setLinkmanTel(customerBean.getLinkmanTel());
		customerAddressBean.setDetailAddress(customerBean.getMemo());
		customerAddressBean.setIsDefaultAddress(true);
		customerDao.insertCustomerAddress(customerAddressBean);
		//end.
		
		return returnResultBean;
	}

}
