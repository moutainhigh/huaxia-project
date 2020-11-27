package com.huaxia.cams.modules.unicom.service;

import java.util.Map;

import com.huaxia.cams.modules.unicom.domain.UnicomAddress;

import net.sf.json.JSONObject;

/**
 * 联通地址类信息(工作和居住地址通用)
 * @author lipengfei
 *
 */
public interface UnicomAddressService {
	
	// 查询数据表,确认数据是否存在
	int countByAppId(String appIdString,String apikey);
	
	// 地址类信息入库
	void saveUnicomAddress(UnicomAddress unicomAddress);

}
