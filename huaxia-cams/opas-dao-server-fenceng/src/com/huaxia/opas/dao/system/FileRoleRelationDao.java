package com.huaxia.opas.dao.system;


import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.system.FileRoleRelation;

public interface FileRoleRelationDao {
    int deleteByPrimaryKey(String id);

    int insert(FileRoleRelation record);

    int insertSelective(FileRoleRelation record);

    FileRoleRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileRoleRelation record);

    int updateByPrimaryKey(FileRoleRelation record);

	List<String> selectSmallCategoryByUserCode(String userCode);

	int queryFileRoleCount(Map<String, Object> map);

	List<FileRoleRelation> queryFileRoleList(Map<String, Object> map, int curNum, int pageNum);

	int deleteByCondition(FileRoleRelation record);
}