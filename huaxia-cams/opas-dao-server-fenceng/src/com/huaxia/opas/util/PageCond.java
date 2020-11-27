package com.huaxia.opas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.service.sqlmap.SqlMap;
import com.huaxia.opas.dao.AbstractDAO;

/**
 * @Description:              功能描述
 * Copyright: Copyright (c) 2016  版权信息
 * Company: HUATENG 
 * @Author zhang.xinchun
 * @Version 1.0    分页查询类
 * Created at 2016-4-21 下午5:47:53  创建日期
 * Modified by XXX at yyyy-mm-dd 修改信息  
 */
public class PageCond {

	private int pageSize;//每页显示的数目
	
	private int currentPage;//当前页
	
	private List<Map<String, Object>> rspList = new ArrayList<Map<String,Object>>();
	
	private final SqlMap sqlMap;
	
	private Map<String, Object> reqParams;//查询条件
	
	public static PageCond getInstance(AbstractDAO dao){
		return new PageCond(dao.getSqlMap());
	}
	
	private PageCond(SqlMap sqlMap){
		this.sqlMap = sqlMap;
	}
	
	/**
	 * 
	* @Description: 根据条件查询总条数 
	* @author zhang.xinchun
	* @version  V1.0
	* @see   UFMessage, Event
	* @param queryCountMap
	* @return    参数
	* @return Integer    返回类型 
	 */
	public Integer queryForCount(String queryCountMap){
		return sqlMap.queryForObject(queryCountMap, reqParams);
	}
	
	/**
	 * 
	* @Description: 分页查询调用 
	* @author zhang.xinchun
	* @version  V1.0
	* @param dataMap:分页的参数,使用方式
	* Map<String,Object> reqMap = new HashMap<String,Object>();
	* reqMap.put("currentPage",currentPage);
	* reqMap.put("pageSize",pageSize);
	* Map<String,Object> dataMap = new HashMap<String,Object>();//放置查询参数
	* reqMap.put("params",dataMap);//查询参数
	* 
	* @param queryContentMap:NAMESPACE+ID
	* @param queryCountMap:查询分页总条数的NAMESPACE+ID
	* @param dbPaging:是否物理分页
	* @param sql:传入，需手动编写
	* @return    参数
	* @return List<Map<String,Object>>    返回类型 
	 */
	public List<Map<String, Object>> queryForListByPage(Map<String, Object> dataMap,String queryContentMap,String queryCountMap,Boolean dbPaging,String sql){
		
		List<Map<String, Object>> tmpList = new LinkedList<Map<String,Object>>();
		int currentPage = dataMap.containsKey("currentPage") ? (Integer)dataMap.get("currentPage") :0;
		int pageSize = dataMap.containsKey("pageSize") ? (Integer)dataMap.get("pageSize") : 0;
		reqParams = dataMap.containsKey("params") ? (Map<String, Object>)dataMap.get("params") :new HashMap<String,Object>();
		int count = queryForCount(queryCountMap);
		if(count != 0){
			rspList = queryForListContent(queryContentMap,dbPaging,sql);
			for (Map<String, Object> map : rspList) {
				Map<String, Object> tmpMap = new HashMap<String, Object>();
				for (String key: map.keySet()) {
					tmpMap.put(TransferUtil.upper2Camel(key), map.get(key));
				}
				tmpList.add(tmpMap);
			}
		}
		return tmpList;
	}
	
	/**
	 * 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhang.xinchun
	* @version  V1.0
	* @param queryContentMap
	* @param dbPaging :是否物理分页
	* @param sql:根据需要填写sql
	* @return    参数
	 */
	private List<Map<String, Object>> queryForListContent(String queryContentMap, Boolean dbPaging,String sql){
		List<Map<String, Object>> contentList = new ArrayList<Map<String,Object>>();
		try{
		if(dbPaging){
			reqParams.put("_dbPaging1", "SELECT * FROM (");
			reqParams.put("_dbPaging2", " SELECT A.*,ROWNUM RN FROM (");
			reqParams.put("_dbPaging3", sql);
			reqParams.put("_dbPaging4", " ) A WHERE ROWNUM <="+ (currentPage * pageSize) +" ) WHERE RN >=" + (currentPage-1) * pageSize);
			contentList = sqlMap.queryForList(queryContentMap, reqParams);
		}else{
			contentList = sqlMap.queryForList(queryContentMap, reqParams, (currentPage-1)*pageSize, pageSize);
		}
		}catch(Exception e){
			int start =(currentPage-1)*pageSize;
			start -= pageSize;
			if(start >= 0){
				contentList = queryForListContent(queryContentMap,dbPaging,sql);
			}
		}
	
		return contentList;
	}
	
	
	
}
