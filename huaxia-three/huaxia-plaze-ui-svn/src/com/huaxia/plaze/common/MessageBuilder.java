package com.huaxia.plaze.common;

import java.util.Map;

/**
 * 报文构造器接口
 * 
 * @author zhiguo.li
 * @version 1.0.0
 * @since 2019-08-22
 *
 */
public interface MessageBuilder {

	/** 请求方英文简称 */
	public final static String REQUEST_CHANNEL = "REQUEST_CHANNEL";

	/** 交易编号 */
	public final static String TRN_ID = "TRN_ID";

	/** 交易代码 */
	public final static String TRN_CODE = "TRN_CODE";

	/** 查询模式 */
	public final static String QUERY_MODE = "QUERY_MODE";

	/** 被查询者证件类型 */
	public final static String CERT_TYPE = "CERT_TYPE";

	/** 被查询者证件号码 */
	public final static String CERT_NO = "CERT_NO";

	/** 被查询者姓名 */
	public final static String NAME = "NAME";

	/** 被查询者手机号码 */
	public final static String MOBILE = "MOBILE";

	/**
	 * 构建默认交易请求报文
	 * 
	 * @param args
	 * 
	 *            <pre>
	 *     [ REQUEST_CHANNEL ] 请求方英文简称
	 *     [ TRN_ID          ] 交易编号
	 *     [ TRN_CODE        ] 交易代码
	 *     [ QUERY_MODE      ] 查询模式
	 *     [ CERT_TYPE       ] 被查询者证件类型
	 *     [ CERT_NO         ] 被查询者证件号码
	 *     [ NAME            ] 被查询者姓名
	 *     [ MOBILE          ] 被查询者手机号码
	 *            </pre>
	 * 
	 * @return 默认格式的 XML 请求报文
	 */
	String build(Map<String, String> args);

}
