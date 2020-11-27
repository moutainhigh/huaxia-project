package com.huaxia.opas.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.PBOC;
import com.huaxia.opas.interfaces.out.ReceiveSinglePBOC;
import com.huaxia.opas.parser.PBOCMessageParser;
import com.huaxia.opas.service.PBOCService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

public class PBOCTaskReceiver implements ReceiveSinglePBOC {

	private final static Logger logger = LoggerFactory.getLogger(PBOCTaskReceiver.class);

	private TaskCallStatusService taskCallStatusService;

	private PBOCService pbocService;
	
	private final String taskType = "000100";

	// 报文存储路径
	private String xmlSavePath;

	public PBOCTaskReceiver() {
		// 获取配置参数
		AppConfig appConfig = AppConfig.getInstance();
		setXmlSavePath(appConfig.getValue("PLAZE_PBOC_HTTP_XML_PATH"));

		// 初始化服务接口
		setTaskCallStatusService((TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService"));
		setPbocService((PBOCService) SpringContextUtil.getBean("pbocService"));
	}

	@Override
	public String getResult(String json) {
		if (json == null || "".equals(json)) {
			if (logger.isWarnEnabled()) {
				logger.warn("[客户端 & 人行] 接收APS响应信息为空!!!");
			}
			return "failure";
		}

		if (logger.isDebugEnabled()) {
			logger.debug("[客户端 & 人行] 接收APS响应信息: {}", json);
		}

		JSONObject responseObject = JSONObject.fromObject(json);
		if (!responseObject.isNullObject() && !responseObject.isEmpty()) {
			String APP_ID = responseObject.getString("appid");
			String BODY = responseObject.getString("body");
			CommonUtil.downLoadMessageContent(BODY, getXmlSavePath(), APP_ID, null, ".xml","人行信息");
			//插入历史表
			getTaskCallStatusService().saveTaskStatusHistory(APP_ID, taskType);
			
			// 调用人行解析方法
			PBOCMessageParser messageParser = new PBOCMessageParser();
			PBOC pboc=null;
			try {
				pboc = messageParser.parse(BODY);
			 } catch (Exception e1) {
				//解析异常
				getTaskCallStatusService().updateSingleTaskStatus(APP_ID, TaskStatusUtil.PARSE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER , null, "1");
				 if (logger.isErrorEnabled()) {
					 logger.error("[客户端 & 人行]解析异常   "+APP_ID+" Exception:{}",e1);
				 }
				 return "failure";
			}
		   try{
			    pboc.setAppId(APP_ID);
				pbocService.savePbocUpdateDataAdapterAction(pboc, APP_ID, taskType);
				taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.SUCCESS, taskType, null, TaskStatusUtil.CURR_USER, null, null);
				if (logger.isDebugEnabled()) {
				logger.debug("[客户端 & 人行] 报文数据持久化成功, 申请件编号: {}", APP_ID);
			   }
				return "success";
		    } catch (Exception e) {
				if (logger.isErrorEnabled()) {
					 logger.error("[客户端 & 人行]保存异常   "+APP_ID+" Exception:{}",e);
				 }
				//入库异常
				getTaskCallStatusService().updateSingleTaskStatus(APP_ID, TaskStatusUtil.SAVE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER , null, "1");
			   return "failure";
			}
		}

		return "failure";
	}

	public String getXmlSavePath() {
		return xmlSavePath;
	}

	public void setXmlSavePath(String xmlSavePath) {
		this.xmlSavePath = xmlSavePath;
	}

	public TaskCallStatusService getTaskCallStatusService() {
		return taskCallStatusService;
	}

	public void setTaskCallStatusService(TaskCallStatusService taskCallStatusService) {
		this.taskCallStatusService = taskCallStatusService;
	}

	public PBOCService getPbocService() {
		return pbocService;
	}

	public void setPbocService(PBOCService pbocService) {
		this.pbocService = pbocService;
	}

	public static void main(String[] args) {
		PBOCTaskReceiver receive = new PBOCTaskReceiver();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File("D:/template/20170808911P2321.xml")), "GBK"));
			String body = reader.readLine();
			System.out.println(body);
			String result = receive.getResult("{'appid':'20170808911P2321','body':\'" + body + "'}");
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
