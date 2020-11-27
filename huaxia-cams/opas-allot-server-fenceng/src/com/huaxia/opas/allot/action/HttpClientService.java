package com.huaxia.opas.allot.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huaxia.opas.common.util.JsonUtil;


public class HttpClientService {
	
	private HttpClient httpClient=null;
	private static String REQ_MESSAGE="REQ_MESSAGE";
	
	private static Logger log = LoggerFactory.getLogger(HttpClientService.class);
	
	private String ip;
	private int port;
	private String transCode;
	
	
	
	public HttpClientService(String ip, int port, String transCode) {
		this.ip = ip;
		this.port = port;
		this.transCode = transCode;
	}



	/**
	 * <p>Description: 初始化并配置客户端服务</p>
	 * @author wang.jf
	 * @version 1.0
	 */
	private void init(){
		
		this.httpClient = new HttpClient();
		HttpClientParams httpClientParams = new HttpClientParams();
		httpClientParams.setSoTimeout(30000);
		httpClientParams.setHttpElementCharset("UTF-8");
		httpClientParams.setContentCharset("UTF-8");
		httpClientParams.setConnectionManagerTimeout(60000);
		httpClientParams.setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		this.httpClient.setParams(httpClientParams);
		HostConfiguration hostConfiguration = new HostConfiguration();
		hostConfiguration.setHost(ip, port, "http");
		this.httpClient.setHostConfiguration(hostConfiguration);

	}
	


	/**
	 * <p>Description: 设置URL参数 字段转换 发送服务</p>
	 * @param input
	 * @author wang.jf
	 * @version 1.0
	 */
	public Map<String,Object> submit(Map<String,Object> input){
		
		if(log.isInfoEnabled()){
			log.info("获取httpClient");
		}
		
		if (httpClient==null){
			this.init();
		}
		
		if(log.isInfoEnabled()){
			log.info("组装数据数据");
		}
		
		String payload = toJSONClient(input);
		
		if(log.isInfoEnabled()){
			log.info("Request is: " + payload);
		}
		
		PostMethod pm = new PostMethod(transCode);
		//pm.setRequestHeader("Content-Type", "application/json");
		NameValuePair[] nvp = new NameValuePair[1];
		
		nvp[0] = new NameValuePair(REQ_MESSAGE, payload);
		pm.addParameters(nvp);
		pm.setRequestBody(nvp);
		
		
		String htmlAsString = null;
		
		int returnFlag = 0;
		Map<String,Object> returnMsg = new HashMap<String,Object>();
		
		if(log.isInfoEnabled()){
			log.info("发送HTTP 请求");
		}
		try {
			httpClient.executeMethod(pm);
			htmlAsString = pm.getResponseBodyAsString();
			
		} catch (HttpException httpe) {
			if(log.isInfoEnabled()){
				log.info("HttpException info: " + httpe.getMessage());
			}
			returnFlag = 1;
		}catch (IOException ioe){
			if(log.isInfoEnabled()){
				log.info("HttpException info: " + ioe.getMessage());
			}
			returnFlag = 2;
		}finally{
			pm.releaseConnection();
			if(returnFlag != 0){
				if(returnFlag == 1){
					returnMsg.put("returnCode", "2");
					returnMsg.put("returnMsg", "HttpException");
					
				}else if(returnFlag == 2){
					returnMsg.put("returnCode", "3");
					returnMsg.put("returnMsg", "CommunicationException");
				}
				
				return returnMsg;
			}
			
		}
		
		
		
		if(log.isInfoEnabled()){
			log.info("Response is: " + htmlAsString);
		}
		
		if(log.isInfoEnabled()){
			log.info("格式化JSON报文");
		}
		Map info = JsonUtil.fromJSON(htmlAsString);
		
		return info;
		
	}

	public static String toJSONClient(Map<String,Object> inputMap) {

		
		Map<String,Object> jsonHead = new LinkedHashMap<String,Object>();
		Map<String,Object> jsonBody = inputMap;
		Map<String,Object> jsonTotal = new LinkedHashMap<String,Object>();
		
		jsonHead.put("TRAN_PROCESS", inputMap.get("TRAN_PROCESS"));
		
		//jsonBody.put("FILE_REALNAME", "FILE_DOWNLOAD_NAMES");
		//jsonBody.put("UPLOAD_SUBDIRS", "D:\\");
		//jsonBody.put("applicationid", inputMap.get("applicationid"));
		//jsonBody.put("sourcetype", inputMap.get("sourcetype"));
		
		jsonBody.remove("TRAN_PROCESS");
		
		jsonTotal.put("REQ_HEAD", jsonHead);
		jsonTotal.put("REQ_BODY", jsonBody);
		
		Gson gson = new Gson();
		String json = null;
		try {
			json = gson.toJson(jsonTotal);
		} catch (Exception ex) {
			log.error("toJson:", ex);
		}
		return json;
		
		
	}
	
	public static void main(String [] args){
		
		
		Map req = new HashMap();
		
		req.put("nodeNo", "n13050_TTLSQ");
		req.put("userId", "A00000");
		
		HttpClientService test = new HttpClientService("127.0.0.1",8080,"/opas-queue/autoDistribut.json");
		System.out.println(test.submit(req));
	}

}
