package com.huaxia.cams.webservice;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.huaxia.support.cams.service.TaskDispatcherService;

@Service
@WebService(serviceName = "messageService", endpointInterface = "com.huaxia.cams.webservice.MessageService")
public class MessageWebService implements MessageService {
	
	@Resource
	private TaskDispatcherService taskDispatcherService;
	
	@Override
	public int onMessage(String message) throws Exception {
		return taskDispatcherService.saveMessageFromWorkFlow(message);
	}

}
