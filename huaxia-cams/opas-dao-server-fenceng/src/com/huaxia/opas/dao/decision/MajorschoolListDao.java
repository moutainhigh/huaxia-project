package com.huaxia.opas.dao.decision;

import com.huaxia.opas.domain.sysparm.MajorschoolList;

public interface MajorschoolListDao {
    int deleteByPrimaryKey(String autoId);

    int insert(MajorschoolList record);

    int insertSelective(MajorschoolList record);

    MajorschoolList selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(MajorschoolList record);

    int updateByPrimaryKey(MajorschoolList record);
}