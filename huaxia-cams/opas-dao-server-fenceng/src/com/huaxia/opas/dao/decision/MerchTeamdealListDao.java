package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.domain.sysparm.GoodcompanyList;

public interface MerchTeamdealListDao {
	
	int deleteByPrimaryKey(String autoId);

	int insert(MerchTeamdealList record);

	int insertSelective(MerchTeamdealList record);

	MerchTeamdealList selectByPrimaryKey(String autoId);

	int updateByPrimaryKeySelective(MerchTeamdealList record);

	int updateByPrimaryKey(MerchTeamdealList record);

	List<MerchTeamdealList> selectByCondtion(Map map,int curNum,int pageNum);
	int queryCountList(Map map);
}