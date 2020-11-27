package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.FileRoleRelationDao;
import com.huaxia.opas.domain.system.FileRoleRelation;
import com.huaxia.opas.domain.system.FileUpload;

public class FileRoleRelationDaoImpl extends AbstractDAO implements FileRoleRelationDao{
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "FileRoleRelation.";
	@Override
	public int deleteByPrimaryKey(String fileId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", fileId);
	}
	@Override
	public int deleteByCondition(FileRoleRelation record) {
		return getSqlMap().delete(NAMESPACES + "deleteByCondition", record);
	}
	@Override
	public int insert(FileRoleRelation record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(FileRoleRelation record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public FileRoleRelation selectByPrimaryKey(String fileId) {
		return (FileRoleRelation) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", fileId));
	}
	
	@Override
	public List<String> selectSmallCategoryByUserCode(String userCode) {
		return getSqlMap().queryForList(NAMESPACES + "selectSmallCategoryByUserCode", userCode);
	}
	@Override
	public int queryFileRoleCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryFileRoleCount", map);
	}
	@Override
	public List<FileRoleRelation> queryFileRoleList(Map<String, Object> map, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryFileRoleList", map, curNum, pageNum);
	}

	@Override
	public int updateByPrimaryKeySelective(FileRoleRelation record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(FileRoleRelation record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

}
