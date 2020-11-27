package com.huaxia.opas.outerfaces.fico;

import java.util.Map;

import com.huateng.neofp.core.CoreException;

public interface FicoAdapterService {

	
	/**
	 * 
	 * @desc  fico接口-运营
	 * @param nodeId 节点id
	 * @param queueId 队列id
	 * @param insideAppNo 内审编号
	 * @param dataMap  请求方组装数据模型
	 * @throws CoreException    
	 * @return Map<String,Object>
	 */
	public Map<String, Object> executeFicoOpr(String nodeId, String queueId, String insideAppNo, Map<String, Object> dataMap) throws CoreException;
	
	/**
	 * 
	 * @desc  fico接口-风险
	 * @param nodeId 节点id
	 * @param queueId 队列id
	 * @param insideAppNo 内审编号
	 * @param dataMap  请求方组装数据模型
	 * @throws CoreException    
	 * @return Map<String,Object>
	 */
	public Map<String, Object> executeFicoRsk(String nodeId, String queueId, String insideAppNo, Map<String, Object> dataMap) throws CoreException;
	
	/**
	 * 
	 * @desc  fico接口-欺诈
	 * @param nodeId 节点id
	 * @param queueId 队列id
	 * @param insideAppNo 内审编号
	 * @param dataMap  请求方组装数据模型
	 * @throws CoreException    
	 * @return Map<String,Object>
	 */
	public Map<String, Object> executeFicoFrd(String nodeId, String queueId, String insideAppNo, Map<String, Object> dataMap) throws CoreException;

	/**
	 * 
	 * @desc   加载xml转换配置列表
	 * @throws CoreException    
	 * @return boolean
	 */
	public boolean loadXmlConfig() throws CoreException;
	
}
