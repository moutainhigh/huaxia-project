package com.huaxia.plaze.ui.system.common;

/**
 * 授权登录枚举类
 * 
 * @author zhiguo.li
 *
 */
public enum Authorization {

	/** 首次登录系统 */
	FIRST_LOGIN_SYSTEM("010003", "请修改系统默认登录密码!"),
	/** 登录成功 */
	LOGIN_SUCCESS("010100", "登录成功"),
	/** 请求参数缺失 */
	REQUEST_PARAMETER_LOST("010110", "登录请求参数缺失"),
	/** 账号或密码错误 */
	ACCOUNT_OR_PASSWORD_ERROR("010112", "账号或密码错误"),
	/** 长时间未登录 */
	LONG_TIME_UNLOGIN("010113", "您的账户长时间未登录系统已被锁定, 请联系管理员!"),
	/** 登录密码过期 */
	LOGIN_PASSWORD_EXPIRED("010114", "登录密码已过期, 请修改密码!"),
	/** 密码错误次数达到上限 */
	PASSWORD_ERROR_REACHED_MAX_LIMIT("010115", "登录密码错误次数达到上限"),
	/** 终端地址异常 */
	TERMINAL_ADDRESS_EXCEPTION("010116", "您的终端地址异常, 请联系管理员!"),
	/** 短信验证码错误 */
	SMS_CAPTCHA_ERROR("010190", "短信验证码错误"),
	/** 短信验证码错误次数达到上限 */
	SMS_CAPTCHA_ERROR_REACHED_MAX_LIMIT("010191", "短信验证码错误次数达到上限");

	private String code;

	private String desc;

	private Authorization(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
