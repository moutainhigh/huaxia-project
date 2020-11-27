package com.huaxia.opas.dao.thirdparty;

import java.util.Map;

/**
 * 数字解读
 * 
 * @author yuanquan
 *
 */
public interface SzjdDao {
	Map<String, String> selectSzjdInfoByAppId(String appId);
}
