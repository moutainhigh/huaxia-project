package com.huaxia.opas.parser;

import java.lang.reflect.Field;
import java.security.PrivateKey;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.common.AppConst;
import com.huaxia.opas.domain.Education;
import com.huaxia.opas.interfaces.in.MessageParser;
import com.huaxia.opas.util.AppConfig;

import cn.id5.gboss.client.api.codec.HexCodec;
import cn.id5.gboss.client.api.codec.StringUtils;
import cn.id5.gboss.client.api.codec.security.AesCodec;
import cn.id5.gboss.client.api.codec.security.Base64;
import cn.id5.gboss.client.api.codec.security.RsaCodec;

/**
 * 学历报文解析器
 * 
 * @author zhiguo.li
 *
 */
public class EducationMessageParser implements MessageParser<Education> {

	private Logger logger = LoggerFactory.getLogger(EducationMessageParser.class);

	private static RsaCodec rsa;
	private static Base64 base64;
	private static HexCodec hex;
	private static AesCodec aes;

	// 初始化加密算法
	static {
		rsa = new RsaCodec();
		base64 = new Base64();
		hex = new HexCodec();
		aes = new AesCodec();
		
	}

	@Override
	public Education parse(String message) throws Exception {
		if (message == null) {
			throw new IllegalArgumentException("学历报文为空");
		}
		Education education = new Education();
		education.setCrtUser(AppConst.DEFAULT_USER);

		Document document = DocumentHelper.parseText(message);
		String message_status = document.selectSingleNode("/data/message/status").getText();
		// 处理成功
		if ("0".equals(message_status)) {
			String eduinfos_status = document.selectSingleNode("/data/xlQueryS/xlQuery/code").getText();
			String eduinfos_value = document.selectSingleNode("/data/xlQueryS/xlQuery/message").getText();
			education.setFlag(message_status);
			// 1 - 查询成功有数据
			if ("1".equals(eduinfos_status)) {
				education.setQueryStatus(eduinfos_status);
				education.setQueryResult(eduinfos_value);

				try {
					String keyinfoKey = document.selectSingleNode("/data/xlQueryS/xlQuery/keyinfoKey").getText();
					String keydata = AppConfig.getInstance().getValue("PLAZE_EDU_PRIVATE_KEY");
					// 使用rsa对私钥密文解密
					PrivateKey privateKey = rsa.loadPrivateKey(keydata);
					byte[] decdata = rsa.decrypt(Base64.decode(keyinfoKey), privateKey, 2048, "");
					String enEduData = document.selectSingleNode("/data/xlQueryS/xlQuery/xlinfos").getText();
					byte[] eduDataArray = hex.hexStringToByte(enEduData);
					// 使用aes对学历密文进行解密
					byte[] eduData = aes.decrypt(eduDataArray, Base64.decode(new String(decdata)));
					String decEduData = StringUtils.newString(eduData, "UTF-8");
					Document dom = DocumentHelper.parseText(decEduData);
					List<Element> eduNodes = dom.selectNodes("/xl/*");
					for (Element elem : eduNodes) {
						Field field = Education.class.getDeclaredField(elem.getName());
						field.setAccessible(true);
						field.set(education, elem.getTextTrim());

					}

				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
						| IllegalAccessException e) {
					if (logger.isErrorEnabled()) {
						logger.error("[学历数据解析] 构建对象赋值异常：{}", e.getMessage());
					}
					e.printStackTrace();
					throw e;
				}
			}
			// 0标示查询成功无数据
			if ("0".equals(eduinfos_status)) {
				education.setQueryStatus(eduinfos_status);
				education.setQueryResult(eduinfos_value);
			} else {// 数据源接口调用失败或数据格式错误
				education.setQueryStatus(eduinfos_status);
				education.setQueryResult(eduinfos_value);
			}
		} else {// 处理失败
			education.setQueryStatus("2");// 查询失败
			education.setQueryResult(document.selectSingleNode("/data/message/value").getText());
			if (logger.isErrorEnabled()) {
				logger.error("[学历数据解析] 学历响应报文处理失败!");
			}
		}

		return education;
	}

	public static void main(String[] args) throws Exception {
	}
}
