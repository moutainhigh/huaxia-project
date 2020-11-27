package com.huaxia.opas.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.common.SysConst;

public class CommonProperties {
	private static final Logger logger = LoggerFactory.getLogger(CommonProperties.class);

	private Properties property;

	private static Map<String, String> cache = new HashMap<String, String>();

	public void init() {
		// 易达金流程节点
		String nodeId_lr_ydj = (String) property.get("nodeId_lr_ydj");
		String nodeId_sc_ydj = (String) property.get("nodeId_sc_ydj");
		String nodeId_sp_ydj = (String) property.get("nodeId_sp_ydj");
		String nodeId_gd_ydj = (String) property.get("nodeId_gd_ydj");
		if (nodeId_lr_ydj != null) {
			cache.put("nodeId_lr_ydj", nodeId_lr_ydj);
		}
		if (nodeId_sc_ydj != null) {
			cache.put("nodeId_sc_ydj", nodeId_sc_ydj);
		}
		if (nodeId_sp_ydj != null) {
			cache.put("nodeId_sp_ydj", nodeId_sp_ydj);
		}
		if (nodeId_gd_ydj != null) {
			cache.put("nodeId_gd_ydj", nodeId_gd_ydj);
		}
		// 标准卡流程节点
		String nodeId_lr_bzk = (String) property.get("nodeId_lr_bzk");
		String nodeId_sc_bzk = (String) property.get("nodeId_sc_bzk");
		String nodeId_sp_bzk = (String) property.get("nodeId_sp_bzk");
		String nodeId_gd_bzk = (String) property.get("nodeId_gd_bzk");
		if (nodeId_lr_bzk != null) {
			cache.put("nodeId_lr_bzk", nodeId_lr_bzk);
		}
		if (nodeId_sc_bzk != null) {
			cache.put("nodeId_sc_bzk", nodeId_sc_bzk);
		}
		if (nodeId_sp_bzk != null) {
			cache.put("nodeId_sp_bzk", nodeId_sp_bzk);
		}
		if (nodeId_gd_bzk != null) {
			cache.put("nodeId_gd_bzk", nodeId_gd_bzk);
		}
		String process_IP = (String) property.get("process_IP");
		if (process_IP != null) {
			cache.put("process_IP", process_IP);
		}
		//获取工作流易达金启动方法参数-wenyh
		String bpm_ydj_start_method = (String) property.get("BPM_YDJ_START_METHOD");
		if (bpm_ydj_start_method != null) {
			cache.put("bpm_ydj_start_method", bpm_ydj_start_method);
		}
		//获取工作流标准卡启动方法参数-wenyh
		String bpm_bzk_start_method = (String) property.get("BPM_BZK_START_METHOD");
		if (bpm_bzk_start_method != null) {
			cache.put("bpm_bzk_start_method", bpm_bzk_start_method);
		}
		
		/*String workflowUrl = (String) property.get("WORKFLOW_URL");
		if (workflowUrl != null) {
			//cache.put(SysConst.WORKFLOW_URL, workflowUrl);
		}*/
		logger.info("nodeId_lr_ydj=" + nodeId_lr_ydj);
		logger.info("nodeId_sc_ydj=" + nodeId_sc_ydj);
		logger.info("nodeId_sp_ydj=" + nodeId_sp_ydj);
		logger.info("nodeId_gd_ydj=" + nodeId_gd_ydj);
		logger.info("nodeId_lr_bzk=" + nodeId_lr_bzk);
		logger.info("nodeId_sc_bzk=" + nodeId_sc_bzk);
		logger.info("nodeId_sp_bzk=" + nodeId_sp_bzk);
		logger.info("nodeId_gd_bzk=" + nodeId_gd_bzk);
		logger.info("process_IP=" + process_IP);
		logger.info("bpm_ydj_start_methpd=" + bpm_ydj_start_method);
		logger.info("bpm_bzk_start_methpd=" + bpm_bzk_start_method);
	}

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}

	public static Map<String, String> getParoperMap() {
		return cache;
	}

	public static void setParoperMap(Map<String, String> paroperMap) {
		CommonProperties.cache = paroperMap;
	}
}
