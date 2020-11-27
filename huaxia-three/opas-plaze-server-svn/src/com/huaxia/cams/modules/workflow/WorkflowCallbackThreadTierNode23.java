package com.huaxia.cams.modules.workflow;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.util.AppConfig;
import com.huaxia.support.cams.domain.TaskCallWorkflow;
import com.huaxia.support.cams.service.WorkflowCallbackService;
import com.huaxia.util.SpringContextUtil;

public class WorkflowCallbackThreadTierNode23 implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkflowCallbackThreadTierNode23.class);
	
	private String defaultWorkflowUrl;
	private int querySize;
	private AppConfig appConfig;
	
	@Resource
	private WorkflowCallbackService workflowCallbackService;
	
	public WorkflowCallbackThreadTierNode23(){
		this.appConfig = AppConfig.getInstance();
	    this.defaultWorkflowUrl = appConfig.getValue("ws_workflow_url");
	    this.querySize = Integer.parseInt(appConfig.getValue("workflow.query.records"));
	    this.workflowCallbackService = ((WorkflowCallbackService)SpringContextUtil.getBean("workflowCallbackService"));
	}

	@Override
	public void run() {
		logger.info("第23梯队线程启动");
		while(true){
			try{
				
				try{
			        Thread.sleep(1000);
			      }catch (InterruptedException localInterruptedException) {
			      }
				
				List<TaskCallWorkflow> workflowTaskList = workflowCallbackService.queryWorkflowTaskList(querySize, 23);
				if ((workflowTaskList != null) && (workflowTaskList.size() > 0)){
					for (TaskCallWorkflow workflowTask : workflowTaskList){
						try{
							
							try{
						        Thread.sleep(500);
						      }catch (InterruptedException localInterruptedException) {
						      }
							
							Client client = null;
					        URL url = new URL(this.defaultWorkflowUrl);
					        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();

					        httpConnection.setReadTimeout(
					          Integer.parseInt(String.valueOf(this.appConfig.getValue("workflow.read_timeout"))));
					        httpConnection.connect();
					        client = new Client(httpConnection.getInputStream(), null);
					        client.setProperty("http.timeout",appConfig.getValue("workflow.timeout"));
					        client.setProperty("disable-keep-alive", "true");
					        client.setProperty("disable.expect-continue", "true");
					        
					        StringBuffer buffer = new StringBuffer();
					        buffer.append("{");
					        buffer.append("\"appId\":\"").append(workflowTask.getAppId()).append("\"");
					        buffer.append(",\"tier\":").append(workflowTask.getTierNode());
					        buffer.append(",\"nodeId\":\"").append(workflowTask.getWorkflowNodeId()).append("\"");
					        buffer.append(",\"processId\":\"").append(workflowTask.getWorkflowProcessId()).append("\"");
					        buffer.append("}");

					        logger.debug("[工作流] 申请件[{}]回调工作流参数[{}]", workflowTask.getAppId(), buffer.toString());

					        Object[] response = client.invoke("signal", new Object[] { buffer.toString().trim() });
					        String responseCode = String.valueOf(response[0]).trim();
					        
					        if ("1".equals(responseCode)) {
					        	logger.info("[工作流] 申请件[{}]回调工作流成功[{}]!", workflowTask.getAppId(), responseCode);
					            logger.info("第23梯队线程回调工作流成功[{}]!", workflowTask.getAppId(), responseCode);
					            int result = workflowCallbackService.saveWorkflowHistoryWithCallback(workflowTask.getAppId());
					            if (result > 0) {
					            	logger.info("[工作流] 申请件[{}]记录工作流回调历史成功!", workflowTask.getAppId());
					            }
					            continue;
					        }else{
					        	workflowCallbackService.saveWorkflowException(workflowTask.getAppId());
					            logger.warn("[工作流] 申请件[{}]回调工作流程失败[{}]!", workflowTask.getAppId(), responseCode);
					        }
							
						}catch(Exception e){
							workflowCallbackService.saveWorkflowException(workflowTask.getAppId());
					        e.printStackTrace();
					        logger.warn("回调工作流异常:" + workflowTask.getAppId(), e);
						}
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error("线程运行异常,准备重启", e);
			}
		}
	}
}
