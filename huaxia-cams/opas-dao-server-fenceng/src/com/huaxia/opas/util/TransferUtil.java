package com.huaxia.opas.util;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.CaseFormat;

/**
 * @Description: 属性转换工具 Copyright: Copyright (c) 2016 版权信息 Company: HUATENG 公司信息
 * @Author XXX 作者姓名
 * @Version 1.0 版本信息 Created at 2016-4-27 下午3:07:09 创建日期 Modified by XXX at
 *          yyyy-mm-dd 修改信息
 */
public class TransferUtil {

	private static final Map<String, String> UPPER_CAMEL_BUFFER = new HashMap<String, String>();

	public static String upper2Camel(String upperString) {
		if (UPPER_CAMEL_BUFFER.containsKey(upperString)) {
			return UPPER_CAMEL_BUFFER.get(upperString);
		}
		String rspString = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, upperString);
		UPPER_CAMEL_BUFFER.put(upperString, rspString);
		return rspString;
	}

	public static void main(String[] args) {
		String result = upper2Camel("USER_ID");
		System.out.println(result);
	}

}
