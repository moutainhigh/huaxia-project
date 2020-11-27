package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import com.huaxia.opas.dao.system.KeyValueConfigureDao;
import com.huaxia.opas.domain.system.KeyValueConfigure;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.KeyValueConfigureService;

public class KeyValueConfigureServiceImpl extends AbstractService implements KeyValueConfigureService, Serializable {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(KeyValueConfigureServiceImpl.class);
	private static final long serialVersionUID = -8342555708270237019L;

	@Resource(name = "keyValueConfigureDao")
	private KeyValueConfigureDao keyValueConfigureDao;
	
	public KeyValueConfigureDao getKeyValueConfigureDao() {
		return keyValueConfigureDao;
	}

	@Override
	public List<KeyValueConfigure> queryKeyValueConfigureList(KeyValueConfigure keyValueConfigure, int curNum, int pageNum) {
		return keyValueConfigureDao.queryKeyValueConfigureList(keyValueConfigure, curNum, pageNum);
	}

	@Override
	public int queryKeyValueConfigureCount(KeyValueConfigure keyValueConfigure) {
		return keyValueConfigureDao.queryKeyValueConfigureCount(keyValueConfigure);
	}

	@Override
	public int insertKeyValueConfigure(KeyValueConfigure keyValueConfigure) {
		return keyValueConfigureDao.insertKeyValueConfigure(keyValueConfigure);
	}

	@Override
	public int updateKeyValueConfigure(KeyValueConfigure keyValueConfigure) {
		return keyValueConfigureDao.updateKeyValueConfigure(keyValueConfigure);
	}

	@Override
	public KeyValueConfigure queryKeyValueConfigureById(String autoId) {
		return keyValueConfigureDao.queryKeyValueConfigureById(autoId);
	}

	@Override
	public int queryKeyValueConfigureEqualCount(KeyValueConfigure keyValueConfigure) {
		return keyValueConfigureDao.queryKeyValueConfigureEqualCount(keyValueConfigure);
	}

	@Override
	public String queryAutoIdEqualByKeyOrValue(KeyValueConfigure keyValueConfigure) {
		return keyValueConfigureDao.queryAutoIdEqualByKeyOrValue(keyValueConfigure);
	}

	@Override
	public String getValueContentByKeyName(String keyName) {
		return keyValueConfigureDao.getValueContentByKeyName(keyName);
	}
	
	@Override
	public Map<String, String> getKVMapByKeyNameArray(String[] keyName) {
		List<KeyValueConfigure> list = new ArrayList<KeyValueConfigure>();
		Map<String, String> kvMap = new HashMap<String, String>();
		try{
		//获取目标键值对列表
		list = keyValueConfigureDao.getValueContentListByKeyName(keyName);
		
		//把目标键值对列表封装成一个 map，方便取值
		for(int i=0; i<=list.size()-1; i++){
			KeyValueConfigure keyValueConfigure = list.get(i);
			kvMap.put(keyValueConfigure.getKeyName(), keyValueConfigure.getValueContent());
		}
		logger.info("", kvMap);
		return kvMap;
		}catch(Exception e){
			logger.error("", e);
		}
		return kvMap;
	}

}
