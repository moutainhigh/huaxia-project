package com.huaxia.opas.dao.common;

/**
 * @Description: 异常类定义 Copyright: Copyright (c) 2016 版权信息 Company: HUATENG 公司信息
 * @Author zhang.xinchun
 * @Version 1.0 版本信息 Created at 2016-4-26 下午3:05:45 创建日期 Modified by XXX at
 *          yyyy-mm-dd 修改信息
 */
public enum OpasError {
	
	/** (未知异常){0} */
	UNKOWN_ERROR("OPAS-N130010-0001"),

	// =====权限异常======================================================================
	/** 用户未登陆 */
	USER_NOT_LOGIN("OPAS-AUTH-0001"),
	/** 用户密码错误 */
	PASSWORD_ERROR("OPAS-AUTH-0002"),
	/** WEBSERVICE请求超时 */
	WEBSERVICE_TIMEOUT("OPAS-SYSTEM-0001"),
	/** 服务器磁盘存取异常 */
	SERVER_IO_ERROR("OPAS-N13010-0002"),
	/** WEBSERVICE通讯异常 */
	WEBSERVICE_IO_ERROR("OPAS-SYSTEM-0002"),

	// =====公共模块异常======================================================================
	/** 无流程定义 */
	PROCESS_NOT_DEFINITION("OPAS-COM-0001"),
	/** 流程重复提交异常 */
	RE_PROCESS_SUBMIT("OPAS-COM-0002"),
	/** 第一节点位置缺失 */
	NOT_FIRST_NODEPOSITION("OPAS-COM-0003"),
	/** 节点实例不存在 */
	NOT_NODE_INS("OPAS-COM-0004"),
	/** 节点定义不存在 */
	NOT_NODE_DEFINITION("OPAS-COM-0005"),
	/** 调用fico异常 */
	DO_FICO_ERROR("OPAS-COM-0006"),
	/** 调用分派子类型异常 */
	DO_ASSIGN_SUB_TYPE_ERROR("OPAS-COM-0007"),
	/** 发送MQ消息异常 */
	SEND_MQ_MESSAGE_ERROR("OPAS-COM-0008"),
	/** 调初审接口异常 */
	DO_BIZINS_FIELD_RECORD_ERROR("OPAS-COM-0009"),
	/** 调终审接口异常 */
	DO_BIZINS_FINALCHECK_RECORD_ERROR("OPAS-COM-0010"),
	/** 调电核接口异常 */
	DO_BIZINS_INFO_RECORD_ERROR("OPAS-COM-0011"),
	/** 完成节点异常 */
	FINISH_NODE_ERROR("OPAS-COM-0012"),
	/** respUser为空 */
	NULL_USER_ERROR("OPAS-COM-0018"),
	/** nodeNo为空 */
	NULL_NODENO_ERROR("OPAS-COM-0014"),

	/** 请求信息为空 */
	NULL_REQUEST_ERROR("OPAS-COM-0015"),
	/** JMS信息为空 */
	NULL_JMS_TRANSPORT_ERROR("OPAS-COM-0016"),
	/** 队列号请求信息为空 */
	NULL_QUEUE_ERROR("OPAS-COM-0017"),

	/** ftp连接异常 */
	FTP_CONNECT_ERROR("OPAS-COM-0018"),
	/** ftp上传异常 */
	FTP_UPLOAD_ERROR("OPAS-COM-0019"),
	/** ftp上传异常 */
	FTP_DOWNLOAD_ERROR("OPAS-COM-0020"),
	/** ftp获取文件名异常 */
	FTP_GETFILENAME_ERROR("OPAS-COM-0021"),
	/** ftp删除文件异常 */
	FTP_DELETE_ERROR("OPAS-COM-0022"),
	/** ftp移动文件异常 */
	FTP_MOVE_ERROR("OPAS-COM-0023"),

	/** 规则异常 */
	RULES_ERROR("OPAS-COM-0024"),

	// =====缓存模块异常======================================================================

	/** 参数不能为空 */
	NULL_PARAM_ERROR("OPAS-CACHE-0001"),
	/** 缓存删除异常 */
	CACHE_REMOVE_ERROR("OPAS-CACHE-0002"),
	/** 缓存查询异常 */
	CACHE_GET_ERROR("OPAS-CACHE-0003"),
	/** 缓存更新异常 */
	CACHE_PUT_ERROR("OPAS-CACHE-0004"),

	// =====自动节点异常======================================================================
	/** 队列中不包含内审编号 */
	NO_INSIDE_APP_NO_ERROR("OPAS-AUTONODE-0001"),
	/** 队列中内审编号为空 */
	INSIDE_APP_NO_IS_NULL_ERROR("OPAS-AUTONODE-0002"),
	/** 队列中不包含节点编号 */
	NO_NODE_NO_ERROR("OPAS-AUTONODE-0014"),
	/** 队列中节点编号为空 */
	NODE_NO_IS_NULL_ERROR("OPAS-AUTONODE-0015"),
	/** 调用数据平台异常 */
	INVOKE_CREDIT_ERROR("OPAS-AUTONODE-0003"),
	/** 调用风险策略异常 */
	INVOKE_RISK_ERROR("OPAS-AUTONODE-0004"),
	/** 调用欺诈策略异常 */
	INVOKE_FRUD_ERROR("OPAS-AUTONODE-0005"),
	/** 向运营平台发送请求失败 */
	INVOKE_OPERA_ERROR("OPAS-AUTONODE-0006"),
	/** 数据抽取结果异常 */
	INVOKE_DATA_EXTRACT_ERROR("OPAS-AUTONODE-0007"),
	/** 数据删除异常 */
	INVOKE_DATA_DELETE_ERROR("OPAS-AUTONODE-0017"),
	/** 数据存储异常 */
	INVOKE_DATA_STORE_ERROR("OPAS-AUTONODE-0008"),
	/** PBOC数据导入异常 */
	INVOKE_DATA_IMPORT_ERROR("OPAS-AUTONODE-0018"),
	/** 调用数据平台长时间没有响应异常 */
	INVOKE_CREDIT_ERROR_RCP("OPAS-AUTONODE-0009"),
	/** 调用二卡服务超时异常 */
	INVOKE_SECOND_CARD_ERROR_RCP("OPAS-AUTONODE-0019"),
	/** 调用二卡服务错误 */
	INVOKE_SECOND_CARD_ERROR("OPAS-AUTONODE-0020"),

	/** 查询配置参数错误 */
	INVOKE_QUERY_PARAM_ERROR("OPAS-AUTONODE-0021"),
	/** 没有查询到配置参数 */
	QUERY_PARAM_IS_NULL("OPAS-AUTONODE-0022"),
	/** 没有查询到配置参数 */
	NEXT_NODE_STATUS_ERROR("OPAS-AUTONODE-0023"),
	/** 调用风险策略长时间没有响应异常 */
	INVOKE_RISK_ERROR_RCP("OPAS-AUTONODE-0010"),
	/** 调用欺诈策略长时间没有响应异常 */
	INVOKE_FRUD_ERROR_RCP("OPAS-AUTONODE-0011"),
	/** 等待节点异常 */
	DO_BIZINS_AUTO_NODE_ERROR("OPAS-COM-0013");

	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	private OpasError(String errorCode) {
		this.errorCode = errorCode;
	}

}
