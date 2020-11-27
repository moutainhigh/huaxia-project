package com.huaxia.opas.action.pad;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.huaxia.opas.util.PadProperties;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PadPatchboltHttpServer extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(PadHttpServer.class);

	public PadPatchboltHttpServer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isInfoEnabled()) {
			logger.info("pad进入servlet=========================");
		}
		response.setContentType("application/x-www-form-urlencoded");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = new PrintWriter(response.getOutputStream());
		String json = request.getParameter("jsonString");// pad传的参数

		json = URLDecoder.decode(json, "UTF-8");
		if (logger.isInfoEnabled()) {
			logger.info("传过来的参数json{}=====", json);
		}
//		JSONObject params = JSONObject.fromObject(json);
//		String appCode = params.getString("appCode");
//		String id = params.getString("id");
//		String addDesc = params.getString("addDesc");
//		Map<String, String> paramMap = new HashMap<>();
		String method = "POST";
		Map<String, String> padMap = PadProperties.getParoperMap();
		String urlStr = padMap.get("padUrl");
		String result = patchboltApsInvoking(method,urlStr,json);
		if (logger.isInfoEnabled()) {
			logger.info("pad返回的结果:", result);
		}
		out.print(result);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (logger.isInfoEnabled()) {
			logger.info("pad进入post方法=====================================");
		}
		doGet(request, response);
	}
	
	private String patchboltApsInvoking(String method, String urlStr,String json) {
		try {
			StringBuffer buff = null;
			// 创建制定链接的url对象
			URL url = new URL(urlStr);
			// 建立到url对象之间的链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置参数
			// 设置连接方式
			conn.setRequestMethod(method);
			// 用URL连接进行输出，则将dooutput标志设置为true
			conn.setDoOutput(true);
			// 用URL连接进行写入
			conn.setDoInput(true);
			// 不允许缓存
			conn.setUseCaches(false);
			// 设置请求的数据内容不被存储
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置该httpurlConnection实例支持自动执行重定向
			conn.setInstanceFollowRedirects(true);
			// 设置请求的字符集编码格式
			conn.setRequestProperty("Charset", "GBK");
			conn.setConnectTimeout(40000);
			conn.setReadTimeout(40000);
			// 建立连接
			conn.connect();
			// 构造向指定链接写入数据的输出流
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			String param = "jsonString=" + URLEncoder.encode(json);
			out.writeBytes(param);
			out.flush();
			out.close();
			// 将pad端返回的数据读取到内存
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
					logger.info("此条显示调用pad接口的情况：{}","调用pad接口成功");
				}
				conn.disconnect();// 销毁连接
			}
			if (logger.isInfoEnabled()) {
				logger.info("返回ResponseMessage参数：{}",conn.getResponseMessage());
			} 
			if (buff != null) {
				return buff.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("pad**************：{}","调用pad接口失败");
			} 
			return null;
		}
	}

}
