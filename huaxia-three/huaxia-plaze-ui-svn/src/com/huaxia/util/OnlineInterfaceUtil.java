package com.huaxia.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.util.IOUtil;
import com.huaxia.plaze.common.Constant;
import com.huaxia.plaze.common.InitPropertyParams;
import com.huaxia.plaze.ui.unionpay.domain.InpKeyField;


/**
 * 联机接口工具类
 * @author liyanan
 * @date 2018-12-28 11:06
 * @version 1.0
 */
public class OnlineInterfaceUtil<T> {

	private static final Logger logger = LoggerFactory.getLogger(OnlineInterfaceUtil.class);
	
	private Charset charset = Charset.forName("GBK");
	
	/**
	 * 解析响应报文到对应的实体
	 * @param tarClass
	 * 			联机接口响应报文实体class
	 * @param template
	 * 			联机接口响应报文对应模板
	 * @param resMsg
	 * 			联机接口响应报文
	 * @param appId
	 * 			申请书流水号
	 * @return 联机接口实体
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Object analysisResMsg(@SuppressWarnings("rawtypes") Class tarClass, List<InpKeyField> template, String resMsg) throws InstantiationException, IllegalAccessException {
		Field field = null;
		Object  newInstance = tarClass.newInstance();
		byte[] bytes = resMsg.getBytes(charset);
		String trxtype = new String(bytes, 0, 4, charset);;
		for (InpKeyField keyField : template) {
			try {
				field = tarClass.getDeclaredField(keyField.getName());
				field.setAccessible(true);
				int beginIndx = keyField.getColBeginIndex() - 1;
				String tmpStr = new String(bytes, beginIndx, keyField.getLength(), charset).trim();
				if (!tmpStr.isEmpty()) {
					switch (keyField.getToType()) {
					case Constant.S:
						field.set(newInstance, tmpStr);
						break;
					case Constant.I:
						field.set(newInstance, Integer.parseInt(tmpStr));
						break;
					case Constant.L:
						field.set(newInstance, Long.parseLong(tmpStr));
						break;
					case Constant.B:
						if (new BigDecimal(Constant.ZERO_STRING).equals(new BigDecimal(tmpStr))  || keyField.getPointLen() == 0) {
							field.set(newInstance, new BigDecimal(tmpStr));
						} else {
							// BigDecimal 类型且含有小数处理
							StringBuffer sb = new StringBuffer();
							int subIndex = tmpStr.length() - keyField.getPointLen();
							sb.append(tmpStr.substring(0, subIndex));
							sb.append(Constant.DOT);
							sb.append(tmpStr.substring(subIndex));
							tmpStr = sb.toString();
							field.set(newInstance, new BigDecimal(tmpStr));
						}
						break;
					case Constant.D:
						if (!"00000000".equals(tmpStr)) {
							field.set(newInstance, DateUtil.stringToDate(tmpStr, "yyyyMMdd"));
						}
						break;
					}
				}
			} catch (NumberFormatException | NullPointerException | NoSuchFieldException| SecurityException | StringIndexOutOfBoundsException e) {
				logger.error("流水号：{}联机接口[{}]属性[{}]解析异常：", new Object[]{ trxtype, keyField.getName(), e});
			}
			
		}
		return newInstance;
	}
	
	/**
	 * 联机接口发送和接收报文
	 * @param reqMsg
	 * 			请求报文
	 * @param readIdx
	 * 			读取的数量
	 * @return	响应报文(String)
	 * @throws Exception
	 */
	public static String sendMsgAndReadMsg(String reqMsg, int readIdx) throws Exception{
		Socket socket = null;
		OutputStream os = null;
		InputStream in = null;
		BufferedReader bufread  = null;
		try {
			socket = CommonUtil.getSocket(InitPropertyParams.YZ_UNIONPAY_IP, InitPropertyParams.YZ_UNIONPAY_PORT, InitPropertyParams.SJS_UNIONPAY_IP, InitPropertyParams.SJS_UNIONPAY_PORT, InitPropertyParams.UNIONPAY_TIMEOUT);
			os = socket.getOutputStream();
			os.write(reqMsg.getBytes());
			in = socket.getInputStream();
			int readIndex = readIdx;
			bufread = new BufferedReader(new InputStreamReader(in, "GBK"), readIndex);
			char[] charArray = new char[readIndex];
			int read_rst = bufread.read(charArray);
			return new String(charArray, 4, read_rst);
		} catch (Exception e) {
			logger.error("联机接口请求获取响应报文异常：", e);
			throw e;
		} finally {
			IOUtil.close(socket);
			IOUtil.close(bufread);
			IOUtil.close(in);
			IOUtil.close(os);
		}
	}
}
