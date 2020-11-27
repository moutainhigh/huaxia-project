package com.huaxia.opas.service.thirdparty;

import java.util.Map;

/**
 * 网申短表数字解读
 * 
 * @author yuanquan
 *
 */
public interface SzjdService {

	Map<String, String> selectSzjdInfoByAppId(String appId);
}
