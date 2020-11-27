package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.ExcellentDao;
import com.huaxia.opas.domain.decision.ExcellentCompanyList;

public class ExcellentDaoImpl  extends AbstractDAO implements ExcellentDao{

	private static final long serialVersionUID = -1264109150276625900L;

	private static final String NAMESPACES = "Excellent.";
	/**
	 * 数量统计
	 */
	@Override
	public int queryExcellentCount(ExcellentCompanyList excellent) {
		return sqlMap.queryForObject(NAMESPACES+"queryExcellentCount", excellent);
	}
	/**
	 * 分页查询
	 */	@Override
	public List<ExcellentCompanyList> queryExcellentList(ExcellentCompanyList excellent, int curNum, int pageNum) {
		return sqlMap.queryForList(NAMESPACES+"queryExcellentList", excellent, curNum,pageNum);
	}
	@Override
	public List<ExcellentCompanyList> checkIsExistExcellent(ExcellentCompanyList excellent) {
		return sqlMap.queryForList(NAMESPACES+"checkIsExistExcellent", excellent);
	}
	@Override
	public int insertExcellent(ExcellentCompanyList excellent) {
		return sqlMap.insert(NAMESPACES+"insertExcellent", excellent);
	}
	@Override
	public int updateExcellent(ExcellentCompanyList excellent) {
		return sqlMap.update(NAMESPACES+"updateExcellent", excellent);
	}
	@Override
	public int deleteExcellent(ExcellentCompanyList excellent) {
		return sqlMap.delete(NAMESPACES+"deleteExcellent", excellent);
	}
	@Override
	public int excellentStatusOK(ExcellentCompanyList excellent) {
		return sqlMap.update(NAMESPACES+"excellentStatusOK", excellent);
	}
	@Override
	public int excellentStatusStop(ExcellentCompanyList excellent) {
		return sqlMap.update(NAMESPACES+"excellentStatusStop", excellent);
	}
	@Override
	public int insertExcellentUpload(List<ExcellentCompanyList> list) {
		return sqlMap.insert(NAMESPACES+"insertExcellentUpload", list);
	}

}
