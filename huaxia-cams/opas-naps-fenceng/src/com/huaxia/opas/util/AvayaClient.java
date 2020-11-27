package com.huaxia.opas.util;

import java.net.URLDecoder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvayaClient {
	private static final Logger logger = LoggerFactory.getLogger(AvayaClient.class);

	public String postJsonToAvaya(String urlStr, String json) {
		try {
			StringBuffer buff = null;
			// 创建制定链接的url对象
			URL url = new URL(urlStr);
			// 建立到url对象之间的链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置参数
			// 设置连接方式
			conn.setRequestMethod("POST");
			// 用URL连接进行输出，则将dooutput标志设置为true
			conn.setDoOutput(true);
			// 用URL连接进行写入
			conn.setDoInput(true);
			// 不允许缓存
			conn.setUseCaches(false);
			// 设置请求的数据内容不被存储
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
			// 设置该httpurlConnection实例支持自动执行重定向
			conn.setInstanceFollowRedirects(true);
			// 设置请求的字符集编码格式
			 //conn.setRequestProperty("charset", "UTF-8");
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(20000);
			// 建立连接
			conn.connect();
			// 构造向指定链接写入数据的输出流
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			String param = URLEncoder.encode(json,"GBK");
			out.writeBytes(param);
			out.flush();
			out.close();
			// 将avaya服务端端返回的数据读取到内存
			int resultCode = conn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				InputStream in = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				buff = new StringBuffer();
				String resultLine = br.readLine();
				while (resultLine != null) {
					buff.append(URLDecoder.decode(resultLine, "GBK"));
					resultLine = br.readLine();
				}
				br.close();
				if (logger.isInfoEnabled()) {
					logger.info("[审批avaya客户端]：{}", "调用avaya接口成功");
				}
				conn.disconnect();// 销毁连接
			}
			if (logger.isInfoEnabled()) {
				logger.info("[审批avaya客户端]：{}", conn.getResponseMessage());
			}
			if (buff != null) {
				return buff.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("[审批avaya客户端]：{}", "调用avaya接口失败");
			}
			return null;
		}
	}
}
