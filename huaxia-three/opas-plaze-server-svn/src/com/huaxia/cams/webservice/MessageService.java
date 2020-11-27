package com.huaxia.cams.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MessageService {
	
	@WebMethod
	int onMessage(String message) throws Exception;

}
