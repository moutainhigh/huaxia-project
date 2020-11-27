package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AutoApply;

public interface AutoApplyDao {
    int deleteByPrimaryKey(String autoId);

    int insert(AutoApply record);

    int insertSelective(AutoApply record);

    AutoApply selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(AutoApply record);

    int updateByPrimaryKey(AutoApply record);
    
    int selectBzkAuto (Map map);
    
    int selectYdjAuto (Map map);
    
    List<AllotApply> selectBzkAutoList(Map map,int page, int rows);
    
    List<AllotApply> selectYdjAutoList(Map map,int page, int rows);
}