package com.huaxia.plaze.ui.system.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.system.domain.Dictionary;

/**
 * 业务字典服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface DictionaryService {

	List<Dictionary> queryList();
	
	String queryObjectByGroup(String group);
	
	String queryName(Map<String,String> args);

}
