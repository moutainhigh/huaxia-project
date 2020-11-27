package com.huaxia.plaze.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页辅助类
 * 
 * @author zhiguo.li
 *
 */
public class PageProperty implements Serializable {

	private static final long serialVersionUID = 7810081770050724377L;

	/** 默认每页显示数量 */
	public final static int DEFAULT_PAGE_SIZE = 10;

	// 页码
	private int pageNo;

	// 每页数量
	private int pageSize;

	// 总页数
	private int pageCount;

	// 总记录数
	private int totalCount;
	
	// 查询参数
	private Map<String, Object> map;
	
	public PageProperty() {
		// 默认第1页
		this.pageNo = 1;

		// 默认每页显示10条
		this.pageSize = DEFAULT_PAGE_SIZE;

		// 默认总记录数
		this.totalCount = 0;
		
		this.map = new HashMap<String, Object>();
	}

	public PageProperty(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public PageProperty(int pageNo, int pageSize, int totalCount) {
		this(pageNo, pageSize);
		this.totalCount = totalCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Map<String, Object> getMap() {
		return getDataMap();
	}

	public void setMap(Map<String, Object> map) {
		setDataMap(map);
	}

	public Map<String, Object> getDataMap() {
		return map;
	}

	public void setDataMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public Object addParameter(String key, Object value) {
		return this.map.put(key, value);
	}
	
	public Object getParameter(String key) {
		return this.map.get(key);
	}
	
	public void clearDataMap() {
		this.map.clear();
	}

}
