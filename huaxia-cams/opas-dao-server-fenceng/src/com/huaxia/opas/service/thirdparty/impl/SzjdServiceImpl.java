package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.SzjdDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.SzjdService;

/**
 * 数字解读
 * 
 * @author yuanquan
 *
 */
public class SzjdServiceImpl extends AbstractService implements SzjdService , Serializable{

	private static final long serialVersionUID = -7263606131246981700L;

	@Resource(name = "szjdDao")
	private SzjdDao szjdDao;

	@Override
	public Map<String, String> selectSzjdInfoByAppId(String appId) {
		Map<String,String> responseMap=new HashMap<String, String>();
		Map<String,String>	szjdInfoMap=szjdDao.selectSzjdInfoByAppId(appId);
		if(szjdInfoMap==null){
			responseMap.put("resultDesc", "未发起查询");
			return responseMap;
		}else{
			return szjdInfoMap;
		}
	}

}