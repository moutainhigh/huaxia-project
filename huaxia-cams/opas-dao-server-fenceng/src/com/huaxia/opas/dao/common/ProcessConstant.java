package com.huaxia.opas.dao.common;

public class ProcessConstant {

	// ====================================流程状态========================================
	/**
	 * 草稿
	 */
	public static final String PROCESS_DRAFT = "01";

	/**
	 * 处理中
	 */
	public static final String PROCESS_UNDERWAY = "02";

	/**
	 * 结案
	 */
	public static final String PROCESS_END = "03";

	// ====================================节点状态========================================
	/**
	 * 待处理 01
	 */
	public static final String NODE_POSN_WAIT = "01";

	/**
	 * 处理中 02
	 */
	public static final String NODE_UNDERWAY = "02";

	/**
	 * 已处理 03
	 */
	public static final String NODE_END = "03";

	/**
	 * 人工接点提交 04
	 */
	public static final String NODE_POSONSUBMITE = "04";

	/**
	 * 异常 05
	 */
	public static final String NODE_ERROR = "05";

	/**
	 * 等待 09
	 */
	public static final String NODE_WAIT = "09";

	// ====================================日志状态========================================

	/**
	 * 发起 01
	 */
	public static final String LOG_LAUNCH = "01";

	/**
	 * 分配 02
	 */
	public static final String LOG_DISTRABUT = "02";

	/**
	 * 人工节点提交 04
	 */
	public static final String LOG_POSN_SUBMITE = "04";

	/**
	 * 自动节点提交 03
	 */
	public static final String LOG_AUTO_SUBMITE = "03";

	/**
	 * 异常重发提交 05
	 */
	public static final String LOG_ERROR_SUBMITE = "05";

	/**
	 * N0前 06
	 */
	public static final String LOG_BEFORE_N0 = "06";

	/**
	 * 进件 07
	 */
	public static final String LOG_INPUT = "07";

	/**
	 * 回退 08
	 */
	public static final String LOG_REBACK = "08";

	/**
	 * 转件到人 09
	 */
	public static final String LOG_TRASFER = "09";

	/**
	 * 分件 10
	 */
	public static final String LOG_SHARE = "10";

	/**
	 * 转件到队列 11
	 */
	public static final String LOG_TRASFER1 = "11";

	// ====================================节点位置========================================

	/**
	 * 第一节点位置 0
	 */
	public static final String START_POSITION = "0";

	/**
	 * 最后节点位置 1
	 */
	public static final String END_POSITION = "1";

	/**
	 * 其他节点位置 2
	 */
	public static final String OTHER_POSITION = "2";

	// ====================================节点类型========================================

	/**
	 * 人工节点类型 01
	 */
	public static final String TYPE_POSN = "01";

	/**
	 * 自动节点类型 00
	 */
	public static final String TYPE_AUTO = "00";

	/**
	 * 等待节点类型 02
	 */
	public static final String TYPE_WAIT = "02";

	// ==================================不需要过滤的异常=====================================

	public static final String[] ERROR_CODE = { "OPAS-COM-0002" };

	// ==================================dubbo服务id======================================

	/**
	 * fico接口dubbo服务id ficoAdapterService
	 */
	public static final String DUBBO_SERVICE_FICO = "ficoAdapterService";

	/**
	 * 初审接口dubbo服务id bizInsFieldRecordService
	 */
	public static final String DUBBO_SERVICE_FIELD = "bizInsFieldRecordService";

	/**
	 * 终审接口dubbo服务id bizInsFinalCheckrecordService
	 */
	public static final String DUBBO_SERVICE_FINALCHECK = "bizInsFinalCheckrecordService";

	/**
	 * 电核接口dubbo服务id bizInsInforecordService
	 */
	public static final String DUBBO_SERVICE_INFORECORD = "bizInsInforecordService";

	public static final String DUBBO_SERVICE_AWAITNODE = "awaitNodeService";

	// ==================================异常类型======================================

	/**
	 * 系统异常 S
	 */
	public static final String ERROR_TYPE_SYSTEM = "S";

	/**
	 * 运营 O
	 */
	public static final String ERROR_TYPE_OPR = "O";

	/**
	 * 欺诈 F
	 */
	public static final String ERROR_TYPE_FRAUD = "F";

	/**
	 * 风险 R
	 */
	public static final String ERROR_TYPE_RISK = "R";

}
