package com.huaxia.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.util.AppConfig;

/**
 * 短信工具类
 * 
 * @version 1.0.1
 * @author zhiguo.li, 2019-10-11, 增加"将同一短信内容发送给多个人"功能
 *
 */
public class SmsUtil {

	private final static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	private static String SMS_SERVER_IP;

	private static int SMS_SERVER_PORT;

	private static int SMS_TIMEOUT;

	private static Socket socket;

	static {
		AppConfig config = AppConfig.getInstance();

		SMS_SERVER_IP = config.getValue("sms.server_ip");
		logger.info("短信参数 [SMS_SERVER_IP]:[ {} ]", SMS_SERVER_IP);
		if (SMS_SERVER_IP == null || "".equals(SMS_SERVER_IP)) {
			SMS_SERVER_IP = "106.193.5.237";
		}

		SMS_SERVER_PORT = Integer.parseInt(String.valueOf(config.getValue("sms.server_port")));
		logger.info("短信参数 [SMS_SERVER_PORT]:[ {} ]", SMS_SERVER_PORT);
		if (SMS_SERVER_PORT == 0) {
			SMS_SERVER_PORT = 21002;
		}

		SMS_TIMEOUT = Integer.parseInt(String.valueOf(config.getValue("sms.timeout")));
		logger.info("短信参数 [SMS_TIMEOUT]:[ {} ]", SMS_TIMEOUT);
		if (SMS_TIMEOUT == 0) {
			SMS_TIMEOUT = 5000;
		}

		// 初始化Socket连接
		initSocketConnect();
	}

	private static void initSocketConnect() {
		try {
			socket = new Socket(SMS_SERVER_IP, SMS_SERVER_PORT);
			socket.setSoTimeout(SMS_TIMEOUT);
		} catch (Exception e) {
			logger.error("与短信系统建立连接异常", e);
		}
	}

	public static boolean send(String mobile, String message) {
		if (socket == null || socket.isClosed()) {
			initSocketConnect();
		}

		StringBuilder buffer = init();
		buffer.append(mobile);
		buffer.append(message);
		return call(buffer.toString());
	}

	/**
	 * 将同一短信内容发送给多个人
	 * 
	 * @param mobiles
	 *            手机号码列表
	 * @param message
	 *            短信内容
	 * @author zhiguo.li
	 */
	public static void send(List<String> mobiles, String message) {
		if (socket == null || socket.isClosed()) {
			initSocketConnect();
		}

		if (mobiles == null) {
			logger.warn("非有效短信参数 [ {} ]", mobiles);
			return;
		}
		
		if (mobiles.size() == 0) {
			logger.warn("无接收手机号码列表");
			return;
		}

		List<String> msgList = new ArrayList<String>();
		for (String mobile : mobiles) {
			StringBuilder builder = init();
			builder.append(mobile);
			builder.append(message);
			msgList.add(builder.toString());
		}
		call(msgList);
	}

	private static StringBuilder init() {
		StringBuilder builder = new StringBuilder();
		builder.append("0547"); // 报文长度
		builder.append("6001"); // 报文类型
		builder.append("6304"); // 银行代码
		builder.append("654321"); // 序列号
		builder.append(new SimpleDateFormat("MMddHHmmss").format(new Date()));
		return builder;
	}

	private static boolean call(String msg) {
		logger.info("发送短信内容 [ {} ]", new Object[] { msg });
		PrintWriter os = null;
		BufferedReader is = null;
		try {
			if (socket == null || socket.isClosed()) {
				initSocketConnect();
			}
			os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "GBK"), true);
			is = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(msg.getBytes())));
			os.println(reader.readLine());
			os.flush();
			return true;
		} catch (Exception e) {
			logger.error("获取短信系统通道异常", e);
			return false;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
			if (os != null) {
				os.close();
			}
		}
	}

	private static void call(List<String> msgList) {
		PrintWriter os = null;
		BufferedReader is = null;
		try {
			os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "GBK"), true);
			is = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));
		} catch (Exception e) {
			logger.error("获取短信系统通道异常", e);
			return;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

		for (String msg : msgList) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(msg.getBytes())));
			String readline = null;
			try {
				readline = reader.readLine();
				os.println(readline);
				os.flush();
				logger.info("发送短信 [ {} ] 成功", readline);
			} catch (IOException e) {
				logger.info("发送短信 [ {} ] 失败", readline);
			}
		}

		if (os != null) {
			os.close();
		}

	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("[样例] java -jar SmsUtil <短信内容> <手机号>...");
			return;
		}

		if (args.length == 2) {
			SmsUtil.send(args[1], args[0]);
		} else {
			List<String> mobiles = new ArrayList<String>();
			for (int i = 1; i < args.length; i++) {
				mobiles.add(args[i]);
			}
			SmsUtil.send(mobiles, args[0]);
		}
	}

}
