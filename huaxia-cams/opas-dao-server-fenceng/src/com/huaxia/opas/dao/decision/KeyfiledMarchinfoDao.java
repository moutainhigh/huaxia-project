package com.huaxia.opas.dao.decision;

import java.util.Map;

import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;

public interface KeyfiledMarchinfoDao {
	
    int deleteByPrimaryKey(String autoId);

    int insert(KeyfiledMarchinfo record);

    int insertSelective(KeyfiledMarchinfo record);

    KeyfiledMarchinfo selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(KeyfiledMarchinfo record);

    int updateByPrimaryKeyWithBLOBs(KeyfiledMarchinfo record);

    int updateByPrimaryKey(KeyfiledMarchinfo record);
    
    KeyfiledMarchinfo selectByAppId(Map<String,String> map);
}