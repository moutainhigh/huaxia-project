package com.huaxia.opas.dao.avaya;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.avaya.Avaya;

public interface AutoDialingDao {
	/**
	 * 自动拨号
	 * */
	public int add(Avaya avaya);
	
	public int del(String avaya);
	
	public int update(Avaya avaya);
	
//	public Avaya findByConditions(List<Map<String,String>> avaya);
	
	public List<Avaya> queryAll();

	public int queryAvayaAutoDialingLimitCount(Avaya avaya);

	public List<Avaya> queryAvayaAutoDialingLimit(Avaya avaya, int curNum, int pageNum);

}
