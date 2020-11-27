package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.SpecialprojectList;

public interface SpecialprojectListDao {

	int deleteByPrimaryKey(String autoId);

	int insert(SpecialprojectList record);

	int insertSelective(SpecialprojectList record);

	SpecialprojectList selectByPrimaryKey(String autoId);

	int updateByPrimaryKeySelective(SpecialprojectList record);

	int updateByPrimaryKey(SpecialprojectList record);

	List<SpecialprojectList> selectByCondtion(Map map,int curNum,int pageNum);
	int queryCountList(Map map);
}