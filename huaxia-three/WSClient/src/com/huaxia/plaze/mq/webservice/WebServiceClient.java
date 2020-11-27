package com.huaxia.plaze.mq.webservice;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

import com.huaxia.util.GuidUtil;

public class WebServiceClient {

	public static void main(String[] args) throws Exception {
		Client client = null;
		long start = System.currentTimeMillis();
		try {
//			client = new Client(new URL("http://106.128.6.140:8080/huaxia-plaze-server/webservice/DtjdWebService?wsdl"));
//			client = new Client(new URL("http://106.128.6.140:8080/webservice/DtjdWebService?wsdl"));
//			client = new Client(new URL("http://106.128.1.215:8080/webservice/DtjdWebService?wsdl"));
//			client = new Client(new URL("http://106.128.6.140:9081/huaxia-plaze-server/webservice/DtjdWebService?wsdl"));
//			client = new Client(new URL("http://106.128.31.134:8080/huaxia-plaze-server/webservice/WST000700?wsdl"));
//			client = new Client(new URL("http://106.128.31.230:8080/huaxia-dmz-server-brdtjd/webservice/WST000700?wsdl"));
//			client = new Client(new URL("http://106.128.31.134:8080/huaxia-plaze-server/webservice/WST000700?wsdl"));
////测试挡板 百融
//			client = new Client(new URL("http://106.128.1.171:8081/huaxia-plaze-server/webservice/WST000700?wsdl"));
//			client =  new Client(new URL("http://192.167.3.171:8081/huaxia-plaze-server/webservice/WST000700?wsdl"));

			//			//测试挡板 同盾
//			client = new Client(new URL("http://106.128.1.171:8081/huaxia-plaze-server/webservice/WST001500?wsdl"));
			//测试挡板 简项公安
//			client = new Client(new URL("http://106.128.1.171:8081/huaxia-plaze-server/webservice/WST000200?wsdl"));
//			//测试挡板 学历
//			client = new Client(new URL("http://106.128.1.171:8081/huaxia-plaze-server/webservice/WST000300?wsdl"));
//			//测试挡板 手机实名
//			client = new Client(new URL("http://106.128.1.171:8081/huaxia-plaze-server/webservice/WST001100?wsdl"));

//		//测试挡板 百融 sit
//			client = new Client(new URL("http://106.128.1.104:8081/huaxia-plaze-server/webservice/WST000700?wsdl"));
//			//测试挡板 同盾 sit
//			client = new Client(new URL("http://106.128.1.104:8081/huaxia-plaze-server/webservice/WST001500?wsdl"));
//			//测试挡板 简项公安 sit
//			client = new Client(new URL("http://106.128.1.104:8081/huaxia-plaze-server/webservice/WST000200?wsdl"));
//			//测试挡板 学历 sit
//			client = new Client(new URL("http://106.128.1.104:8081/huaxia-plaze-server/webservice/WST000300?wsdl"));
//			//测试挡板 手机实名 sit
//			client = new Client(new URL("http://106.128.1.104:8081/huaxia-plaze-server/webservice/WST001100?wsdl"));
			
			// ������������
//			client = new Client(new URL("http://106.128.31.134:8080/huaxia-plaze-server/webservice/ReceiveSingleBank?wsdl"));
			
//			client = new Client(new URL("http://106.128.31.230:8080/huaxia-plaze-server/webservice/WST000700?wsdl"));
//			client.setTimeout(120000); 
//			
//			URL url = new URL("http://106.128.31.134:8080/huaxia-plaze-server/webservice/WST001300?wsdl");
//			URL url = new URL("http://106.128.31.134:8080/huaxia-plaze-server/webservice/WST000700?wsdl");
			URL url = new URL("http://106.128.31.230:8083/huaxia-dmz-server/webservice/WST000203?wsdl");
//			URL url = new URL("http://106.128.31.230:8082/huaxia-plaze-server/webservice/TRN?wsdl");
//			URL url = new URL("http://106.128.31.230:8082/huaxia-plaze-server/webservice/WST000202?wsdl");
//			URL url = new URL("http://106.128.31.230:8080/huaxia-dmz-server/webservice/WST000700?wsdl");
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setConnectTimeout(5000);
//			(
//					Integer.parseInt("19000"));// 设置http连接的读超时，单位是毫秒
			httpConnection.connect();
			client = new Client(httpConnection.getInputStream(), null);
			// 设置发送超时限制，单位是毫秒
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,"40000");
		
			client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
			client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
			// ����Ϣ���͸� jms.queue.cams ����
			for(int i=0;i<10;i++){
			Object[] response = client.invoke("invoke", new Object[] { "{\"REQUEST\":{\"BODY\":{\"CERT_TYPE\":\"1\",\"NAME\":\"18612995529\",\"BIRTH\":\"19950529\",\"EXPIRY_DATE\":\"20950529\",\"QUERY_MODE\":\"1\",\"CERT_NO\":\"410221199505293452\"},\"HEAD\":{\"TRN_CODE\":\"WST000203\",\"TRN_ID\":\""+GuidUtil.getGuid()+"\",\"REQUEST_CHANNEL\":\"CAMS\"}}}" });
			String message = (String) response[0];
			System.out.println("������Ϣ�ɹ�!!!" + message + new Date());
			}
//			Object[] response = client.invoke("invoke", new Object[] { "{\"REQUEST\":{\"BODY\":{\"CERT_TYPE\":\"1\",\"NAME\":\"18612995529\",\"MOBILE\":\"18612995529\",\"QUERY_MODE\":\"1\",\"CERT_NO\":\"410221199505293452\"},\"HEAD\":{\"TRN_CODE\":\"000202\",\"TRN_ID\":\""+GuidUtil.getGuid()+"\",\"REQUEST_CHANNEL\":\"CAMS\"}}}" });
//			Object[] response = client.invoke("invoke", new Object[] { "{\"REQUEST\":{\"BODY\":{\"CERT_TYPE\":\"1\",\"NAME\":\"18612995529\",\"MOBILE\":\"18612995529\",\"XP\":\"18612995529\",\"QUERY_MODE\":\"1\",\"CERT_NO\":\"410221199505293452\"},\"HEAD\":{\"TRN_CODE\":\"000201\",\"TRN_ID\":\""+GuidUtil.getGuid()+"\",\"REQUEST_CHANNEL\":\"IBIS\"}}}" });
//			Object[] response = client.invoke("invoke", new Object[] { "{\"REQUEST\":{\"BODY\":{\"CERT_TYPE\":\"1\",\"NAME\":\"18612995529\",\"MOBILE\":\"18612995529\",\"QUERY_MODE\":\"1\",\"CERT_NO\":\"123456789\",\"USERNAME\":\"hxywStr\",\"PASSWORD\":\"hxywStr\"},\"HEAD\":{\"TRN_CODE\":\"WST001700\",\"TRN_ID\":\"EA009F666DF684C1016DF684C1320000\",\"REQUEST_CHANNEL\":\"CAMS\"}}}" });
//			Object[] respons2 = client.invoke("invoke", new Object[] { "{\"REQUEST\":{\"BODY\":{\"CERT_TYPE\":\"1\",\"NAME\":\"18612995529\",\"MOBILE\":\"18612995529\",\"QUERY_MODE\":\"1\",\"CERT_NO\":\"123456789\",\"USERNAME\":\"hxywStr\",\"PASSWORD\":\"hxywStr\"},\"HEAD\":{\"TRN_CODE\":\"WST001700\",\"TRN_ID\":\"EA009F666DF684C1016DF684C1320000\",\"REQUEST_CHANNEL\":\"CAMS\"}}}" });
//			Object[] response = client.invoke("invoke", new Object[] { "{\"REQUEST\":{\"BODY\":{\"CERT_TYPE\":\"1\",\"NAME\":\"18612995529\",\"MOBILE\":\"18612995529\",\"CARD_ACCOUNT\":\"123456789123\",\"SEQUENCE\":\"999999999999\",\"QUERY_MODE\":\"1\",\"CERT_NO\":\"123456789\",\"USERNAME\":\"hxywStr\",\"PASSWORD\":\"hxywStr\"},\"HEAD\":{\"TRN_CODE\":\"WST001700\",\"TRN_ID\":\"EA009F666DF684C1016DF684C1320000\",\"REQUEST_CHANNEL\":\"IBIS\"}}}"});


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client = null;
			long end = System.currentTimeMillis();
			System.out.println((end-start));
		}	
		// sendDefaultQueue();
	}
	
	public static void sendTargetQueue() {
		String ip = "106.128.6.220";
		String port = "8080";
		Object object = "{'PUBLIC':{'APP_ID':'1704011611P00102'}}";
		object = "{\"REQUEST\":{\"HEAD\":{\"REQUEST_SYSTEM\":\"CAMS\",\"REQUEST_TASK\":\"0100\",\"REQUEST_STAMP\":\"2018-09-03 15:21:03\"},\"BODY\":{\"CERT_TYPE\":\"\",\"CERT_NO\":\"231026199105183312\",\"NAME\":\"ˮ���\",\"MOBILE\":\"18301257851\"}}}";
		if (object instanceof String) {
			Client client = null;
			try {
				client = new Client(new URL("http://" + ip + ":" + port + "/huaxia-plaze-mq/ws/MessageSender?wsdl"));
				client.setTimeout(120000); 
				
				// ����Ϣ���͸� jms.queue.cams ����
				Object[] response = client.invoke("sendToDestination", new Object[] { object, "jms.queue.cams" });
				String message = (String) response[0];
				System.out.println("������Ϣ�ɹ�!!!" + message);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				client = null;
			}
		}
	}
	
	public static void sendDefaultQueue() {
		String ip = "106.128.6.72";
		String port = "8080";
		
		Object object = "{'PUBLIC':{'APP_ID':'1704011611P00101'}}";
		if (object instanceof String) {
			Client client = null;
			try {
//				client = new Client(new URL("http://106.128.6.220:8080/huaxia-plaze-mq/ws/MessageSender?wsdl"));
//				client = new Client(new URL("http://127.0.0.1:8080/huaxia-plaze-mq/ws/MessageSender?wsdl"));
//				client = new Client(new URL("http://106.128.6.72:8080/huaxia-plaze-mq/ws/MessageSender?wsdl"));
//				client = new Client(new URL("http://" + ip + ":" + port + "/huaxia-plaze-mq/ws/MessageSender?wsdl"));
				client = new Client(new URL("http://106.128.31.134:8080/huaxia-plaze-server/webservice/DtjdWebService?wsdl"));
				client.setTimeout(500); 
				client.invoke("invoke", new Object[] { object });
				System.out.println("������Ϣ�ɹ�!!!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				client = null;
			}
		}
	}
	
	/** �����ѯƽ̨�����������Ĳ��� */
	public static void invokePbocWebService() {
		Object object = "{'REQUEST':{'HEAD':{'REQUEST_SYSTEM':'CAMS','TRN_ID':'EA009F0668983D540168983D54300000'},'BODY':{'QUERY_TYPE':'2','CERT_TYPE':'01','CERT_NO':'622926198501293785','NAME':'����02','MOBILE':'15721572514'}}}";
		if (object instanceof String) {
			Client client = null;
			try {
				client = new Client(new URL("http://106.128.7.208:9091/huaxia-plaze-server/webservice/BankCallWebService?wsdl"));
				client.setTimeout(500); 
				Object[] response = client.invoke("queryBankMessage", new Object[] { object });
				System.out.println("������Ϣ�ɹ�!!!" + response);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				client = null;
			}
		}
	}
}
