package com.mobile.vo.wx;

/**
 * 粉丝登录信息Bean
 * 
 * @author 金金
 */
public class LoginFansBean {

	/** 粉丝登录名 */
	private String loginName;

	/** 粉丝登录密码 */
	private String password;

	/** 粉丝唯一标识 */
	private String openId;

	/** 微信平台粉丝唯一标识 */
	private String unionId;

	public LoginFansBean() {

	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
