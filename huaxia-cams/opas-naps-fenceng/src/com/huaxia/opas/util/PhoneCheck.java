package com.huaxia.opas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.avaya.ic.sdk.huateng.model.ParamModel;
//import com.avaya.ic.sdk.huateng.model.PhoneModel;
//import com.avaya.ic.sdk.huateng.util.PhoneUtil;

/**
 * avaya拨打电话，在手机或者座机号之前添加0或者00
 *
 * 外地手机 90+手机号 本地手机 00+手机号 外地座机 9+区号+座机 本地座机 0+座机
 *
 */
public class PhoneCheck {
//
//	// Log log = LogFactory.getLog(getClass().getName());
//	private static Logger logger = LoggerFactory.getLogger(PhoneCheck.class);
//	// 本地配置
//	// mobile_number_prefix_local = 0
//	// mobile_number_prefix_nonlocal = 00
//	// 生产配置
//	// mobile_number_prefix_local = 9
//	// mobile_number_prefix_nonlocal = 90
//
//	public void doPhoneCheck(ParamModel paramModel, String local, String nonlocal) {
//		if (paramModel.getPhoneNum().indexOf("-") > 0) {
//			System.out.println("去除电话号码中 - ");
//			paramModel.setPhoneNum(paramModel.getPhoneNum().replaceAll("-", ""));
//		}
//
//		PhoneModel phoneModel = PhoneUtil.getPhoneModel(paramModel.getPhoneNum());
//		if ("".equals(phoneModel) || phoneModel == null) {
//			logger.info("手机号码为空：" + paramModel.getPhoneNum());
//			return;
//		}
//
//		// PropertiesUtil propertiesUtil = new PropertiesUtil();
//		if ("北京市".equals(phoneModel.getCityName())) {
//			if (paramModel.getPhoneNum().startsWith("010")) { // 北京固定电话特殊处理
//				paramModel.setPhoneNum(paramModel.getPhoneNum().substring(3, paramModel.getPhoneNum().length()));
//				logger.info("北京座机：" + paramModel.getPhoneNum());
//			}
//			paramModel.setPhoneNum(local + paramModel.getPhoneNum()); // 北京手机号码处理
//		} else {
//			// 外地手机号处理
//			if (paramModel.getPhoneNum().startsWith("1")) {
//				paramModel.setPhoneNum(nonlocal + paramModel.getPhoneNum());
//			} else {
//				// 外地座机
//				paramModel.setPhoneNum(local + paramModel.getPhoneNum());
//			}
//			if ("".equals(phoneModel.getCityName()) || null == phoneModel.getCityName()) {
//				logger.info("***手机号码" + paramModel.getPhoneNum() + "未查询到手机号归属地 ***");
//			}
//		}
//		logger.info("***手机号码:" + paramModel.getPhoneNum() + " | 归属地：" + phoneModel.getCityName());
//	}
}
