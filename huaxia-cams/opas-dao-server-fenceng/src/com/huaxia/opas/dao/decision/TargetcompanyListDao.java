package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.TargetcompanyList;

public interface TargetcompanyListDao {

	int deleteByPrimaryKey(String autoId);

	int insert(TargetcompanyList record);

	int insertSelective(TargetcompanyList record);

	TargetcompanyList selectByPrimaryKey(String autoId);

	int updateByPrimaryKeySelective(TargetcompanyList record);

	int updateByPrimaryKey(TargetcompanyList record);

	List<TargetcompanyList> selectByCondtion(Map map,int curNum,int pageNum);
	int queryCountList(Map map);
}