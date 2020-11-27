package com.huaxia.opas.outerfaces.thirdparty;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

public interface InstinctAdapterService {

	/**
	 * 
	* @Description: 黑名单加黑字段
	* @author uatbq90893
	* @version  V1.0
	* @see   UFMessage, Event
	* @param nodeId
	* @param insideAppNo
	* @param dataMap
	* @return
	* @throws CoreException    参数
	* @return String    返回类型 
	*
	 */
	public String executeInstinctActionApplication(String nodeId, String insideAppNo, Map<String, Object> dataMap) throws CoreException;
	
	/**
	* @Description: TODO(调用Instinct接口) 
	* @author uatzy990275
	* @version  V1.0
	* @see   UFMessage, Event
	* @param nodeId
	* @param queueId
	* @param insideAppNo
	* @param dataMap
	* @return
	* @throws CoreException    参数
	* @return Map<String,Object>    返回类型 
	 */
	public String executeInstinct(String nodeId, String queueId, String insideAppNo, String paper) throws CoreException;

	/**
	* @Description: TODO(调用Instinct接口) 
	* @version  V1.0
	* @see   UFMessage, Event
	* @param inputXMLString
	* @param queueId
	* @param insideAppNo
	* @return
	* @throws CoreException    参数
	* @return String    返回类型 
	 */
	 public String executeFinalDecisionInstinct(String inputXMLString, String queueId, String insideAppNo) throws CoreException;
	 
	 /**
		 * 
		 * @desc  Ins接口
		 * @param nodeId 节点id
		 * @param queueId 队列id
		 * @param insideAppNo 内审编号
		 * @param dataMap  请求方组装数据模型
		 * @throws CoreException    
		 * @return Map<String,Object>
		 */
		public Map<String, Object> executeIns(String nodeId, String queueId, String insideAppNo, Map<String, Object> dataMap) throws CoreException;
		
		/**
		 * 
		 * @desc  Ins接口
		 * @param nodeId 节点id
		 * @param queueId 队列id
		 * @param insideAppNo 内审编号
		 * @param dataMap  请求方组装数据模型
		 * @throws CoreException    
		 * @return Map<String, Object>
		 */
		public Map<String, Object> executeInsInstinct(String nodeId, String queueId, String insideAppNo, Map<String, Object> dataMap) throws CoreException;

		/**
		 * 
		 * @desc   加载xml转换配置列表
		 * @throws CoreException    
		 * @return boolean
		 */
		public boolean loadXmlConfig() throws CoreException;
}
