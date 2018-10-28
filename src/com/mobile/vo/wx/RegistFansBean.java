package com.mobile.vo.wx;

/**
 * 粉丝注册信息Bean
 * 
 * @author 金金
 */
public class RegistFansBean {

	/** 姓名 */
	private String name;

	/** 性别 */
	private String gender;

	/** 电话 */
	private String phone;

	/** 登录用户名 */
	private String loginName;

	/** 登录密码 */
	private String password;

	public RegistFansBean() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}
