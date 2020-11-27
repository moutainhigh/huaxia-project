package com.huaxia.plaze.mq.webservice;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import com.huaxia.util.GuidUtil;
public class WebServiceClient2 {

	public static void main(String[] args) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		System.out.println("输入1-审批系统查询|2-人脸识别系统查询");
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			String line = null;
			while ((line = scanner.nextLine()) != null) {
				if ("QUIT".equalsIgnoreCase(line) || "EXIT".equalsIgnoreCase(line)) {
					break;
				}
				String[] strArr = line.split(" ");
				String response = "";
				switch (strArr[0]) {
				case "1":
				{
					Client client = null;
					long start = System.currentTimeMillis();
					try {
						URL url = new URL("http://106.128.1.171:8081/huaxia-plaze-server/webservice/WST000201?wsdl");

						HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
						httpConnection.setConnectTimeout(5000);
						httpConnection.connect();
						client = new Client(httpConnection.getInputStream(), null);
						// 设置发送超时限制，单位是毫秒
						client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,"40000");
					
						client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
						client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
						Object[] response1 = client.invoke("invoke", new Object[] { "{\"REQUEST\":{\"BODY\":{\"CERT_TYPE\":\"1\",\"NAME\":\"18612995529\",\"MOBILE\":\"18612995529\",\"XP\":\"18612995529\",\"QUERY_MODE\":\"1\",\"CERT_NO\":\"410221199505293452\"},\"HEAD\":{\"TRN_CODE\":\"000201\",\"TRN_ID\":\""+GuidUtil.getGuid()+"\",\"REQUEST_CHANNEL\":\"CAMS\"}}}" });
						String message = (String) response1[0];
						System.out.println("返回数据!!!" + message + new Date());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						client = null;
						long end = System.currentTimeMillis();
						System.out.println((end-start)/1000);
					}	
				}
					break;
				case "2":
				{

					Client client = null;
					long start = System.currentTimeMillis();
					try {
						URL url = new URL("http://106.128.1.171:8081/huaxia-plaze-server/webservice/WST000201?wsdl");

						HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
						httpConnection.setConnectTimeout(5000);
						httpConnection.connect();
						client = new Client(httpConnection.getInputStream(), null);
						// 设置发送超时限制，单位是毫秒
						client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,"40000");
					
						client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
						client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
						Object[] response2 = client.invoke("invoke", new Object[] { "{\"REQUEST\":{\"BODY\":{\"CERT_TYPE\":\"1\",\"NAME\":\"18612995529\",\"MOBILE\":\"18612995529\",\"XP\":\"18612995529\",\"QUERY_MODE\":\"1\",\"CERT_NO\":\"410221199505293452\"},\"HEAD\":{\"TRN_CODE\":\"000201\",\"TRN_ID\":\""+GuidUtil.getGuid()+"\",\"REQUEST_CHANNEL\":\"IBIS\"}}}" });
						String message = (String) response2[0];
						System.out.println("返回数据!!!" + message + new Date());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						client = null;
						long end = System.currentTimeMillis();
						System.out.println((end-start)/1000);
					}	
				
				}
					break;
				}
				
				System.out.println("查询结果 [{}]"+response);
				System.out.println("输入1-审批系统查询|2-人脸识别系统查询");
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}	
	}
}
