package com.huaxia.opas.dao.decision;

import com.huaxia.opas.domain.decision.Teamlist;

public interface TeamlistDao {
	int deleteByPrimaryKey(String autoId);

	int insert(Teamlist record);

	int insertSelective(Teamlist record);

	Teamlist selectByPrimaryKey(String autoId);

	int updateByPrimaryKeySelective(Teamlist record);

	int updateByPrimaryKey(Teamlist record);
}