package com.huaxia.opas.dao.system;

import com.huaxia.opas.domain.system.CustBaseInfo;

public interface CustBaseInfoDao {
	
	int deleteByPrimaryKey(String autoId);

	int insert(CustBaseInfo record);

	int insertSelective(CustBaseInfo record);

	CustBaseInfo selectByPrimaryKey(String autoId);

	int updateByPrimaryKeySelective(CustBaseInfo record);

	int updateByPrimaryKey(CustBaseInfo record);
}