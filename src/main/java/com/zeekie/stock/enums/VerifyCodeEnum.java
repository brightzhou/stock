package com.zeekie.stock.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author zeekie
 * 
 *         2015年3月18日
 */
public enum VerifyCodeEnum {

	/**
	 * 用户注册
	 */
	REGISTER("10", "注册"),
	/**
	 * 忘记密码
	 */
	FORGETPWD("11", "忘记密码"),

	/**
	 * 更新手机
	 */
	UPDATETEL("12", "更新手机"),
	/**
	 * 绑定手机
	 */
	BINDTEL("13", "绑定手机"),

	/**
	 * 更新资金密码
	 */
	UPDATEDEPOSIT("14", "更新资金密码");

	private String source;

	private String desc;

	VerifyCodeEnum(String source, String desc) {
		this.source = source;
		this.desc = desc;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
