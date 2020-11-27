package com.huaxia.opas.dao.decision.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.CreditTelcheckListDao;
import com.huaxia.opas.domain.decision.CreditTelcheckList;

public class CreditTelcheckListDaoImpl extends AbstractDAO implements CreditTelcheckListDao {

	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "CreditTelcheckList.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(CreditTelcheckList record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(CreditTelcheckList record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public CreditTelcheckList selectByPrimaryKey(String autoId) {
		return (CreditTelcheckList) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(CreditTelcheckList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(CreditTelcheckList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryCreditTelcheckListCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCreditTelcheckListCount", map);
	}

	@Override
	public List<CreditTelcheckList> queryCreditTelcheckList(Map<String, Object> map, int curNum, int pageNum) {
		List<CreditTelcheckList> list = new ArrayList<CreditTelcheckList>();
		list = getSqlMap().queryForList(NAMESPACES + "queryCreditTelcheckList", map, curNum, pageNum);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectBZKCreditTelcheckList(
			Map<String, Object> map, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectBZKCreditTelcheckList", map, curNum, pageNum);
	}

	@Override
	public int selectBZKCreditTelcheckListCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectBZKCreditTelcheckListCount", map);
	}
}