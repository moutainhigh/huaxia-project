package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.SpecialprojectListDao;
import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.domain.sysparm.SpecialprojectList;

public class SpecialprojectListDaoImpl extends AbstractDAO  implements SpecialprojectListDao  {
	
	private static final long serialVersionUID = 2757582418545513971L;
	
	private static final String NAMESPACES = "SpecialprojectList.";
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(SpecialprojectList record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(SpecialprojectList record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public SpecialprojectList selectByPrimaryKey(String autoId) {
		return (SpecialprojectList) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public List<SpecialprojectList> selectByCondtion(Map map,int curNum,int pageNum) {
		return (getSqlMap().queryForList(NAMESPACES + "selectByCondtion", map,curNum,pageNum));
	}
	@Override
	public int updateByPrimaryKeySelective(SpecialprojectList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(SpecialprojectList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryCountList(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountList", map);
	}
   
}