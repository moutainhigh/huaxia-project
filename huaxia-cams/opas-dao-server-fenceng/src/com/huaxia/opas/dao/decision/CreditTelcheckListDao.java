package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.decision.CreditTelcheckList;

public interface CreditTelcheckListDao {
	
    int deleteByPrimaryKey(String autoId);

    int insert(CreditTelcheckList record);

    int insertSelective(CreditTelcheckList record);

    CreditTelcheckList selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(CreditTelcheckList record);

    int updateByPrimaryKey(CreditTelcheckList record);


	List<CreditTelcheckList> queryCreditTelcheckList(Map<String, Object> map, int curNum, int pageNum);

	int queryCreditTelcheckListCount(Map<String, Object> map);

	List<Map<String, Object>> selectBZKCreditTelcheckList(Map<String, Object> map, int curNum, int pageNum);

	int selectBZKCreditTelcheckListCount(Map<String, Object> map);

}