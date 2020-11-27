package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.GoodcompanyList;

public interface GoodcompanyListDao{
    int deleteByPrimaryKey(String autoId);

    int insert(GoodcompanyList record);

    int insertSelective(GoodcompanyList record);

    GoodcompanyList selectByPrimaryKey(String autoId);
    int queryCountList(Map map);

    int updateByPrimaryKeySelective(GoodcompanyList record);

    int updateByPrimaryKey(GoodcompanyList record);

	List<GoodcompanyList> selectByCondtion(Map map,int curNum,int pageNum);
}