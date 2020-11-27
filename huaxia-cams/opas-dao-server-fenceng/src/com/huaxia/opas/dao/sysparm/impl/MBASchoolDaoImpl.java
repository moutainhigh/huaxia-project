package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.MBASchoolDao;
import com.huaxia.opas.domain.sysparm.MBASchool;

public class MBASchoolDaoImpl extends AbstractDAO implements MBASchoolDao {

	private static final long serialVersionUID = 5590649230697386055L;

	private static final String NAMESPACES = "MBASchool.";

	@Override
	public int queryMBASchoolListCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMBASchoolListCount", map);
	}

	@Override
	public List<MBASchool> queryMBASchoolList(Map<String, Object> map, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryMBASchoolList", map, curNum, pageNum);
	}

	@Override
	public void deleteMBASchoolByAutoId(String autoId) {
		getSqlMap().delete(NAMESPACES + "deleteMBASchoolById", autoId);

	}

	@Override
	public int insert(MBASchool record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(MBASchool record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public int update(MBASchool record) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateMBASchoolSelective", record);
	}

	@Override
	public void updateStatusById(MBASchool record) {
		getSqlMap().update(NAMESPACES + "updateStatusById", record);
	}

	@Override
	public void insertMBASchoolHistory(MBASchool MBASchoolResult) {
		getSqlMap().update(NAMESPACES + "insertMBASchoolHistory", MBASchoolResult);
	}

	@Override
	public int delete(MBASchool record) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteMBASchoolById", record.getAutoId());
	}

	@Override
	public MBASchool selectById(MBASchool mbaSchool) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMBASchoolByAutoId", mbaSchool);
	}

	@Override
	public List<Map<String, String>> selectAllMBASchool() {
		return getSqlMap().queryForList(NAMESPACES + "queryAllMBASchool");
	}

	@Override
	public int queryMBASchoolHistoryCount(MBASchool school) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMBASchoolHistoryListCount", school);
	}

	@Override
	public List<MBASchool> queryMBASchoolHistoryList(MBASchool school, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryMBASchoolHistoryList", school, curNum, pageNum);
	}
	/**
	 * 修改时插入以前的值作为历史
	 */
	@Override
	public MBASchool queryMBASchoolByAutoId(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMBASchoolByAutoId", autoId);
	}

	@Override
	public void insertinsertMBASchoolHistory(MBASchool mschool) {
		getSqlMap().insert(NAMESPACES + "insertinsertMBASchoolHistory", mschool); 
	}
	/**
	 * 批量导入
	 */
	@Override
	public int saveBatchMBASchool(List<MBASchool> mBASchoollist) {
		return getSqlMap().insert(NAMESPACES + "saveBatchMBASchool", mBASchoollist);
	}

}
