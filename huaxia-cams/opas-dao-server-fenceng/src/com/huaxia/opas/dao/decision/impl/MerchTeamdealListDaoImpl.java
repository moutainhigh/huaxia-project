package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.MerchTeamdealListDao;
import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.domain.sysparm.GoodcompanyList;

public class MerchTeamdealListDaoImpl extends AbstractDAO implements MerchTeamdealListDao {

	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "MerchTeamdealList.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(MerchTeamdealList record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(MerchTeamdealList record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public MerchTeamdealList selectByPrimaryKey(String autoId) {
		return (MerchTeamdealList) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public List<MerchTeamdealList> selectByCondtion(Map map,int curNum,int pageNum) {
		return (getSqlMap().queryForList(NAMESPACES + "selectByCondtion", map,curNum,pageNum));
	}
	@Override
	public int updateByPrimaryKeySelective(MerchTeamdealList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(MerchTeamdealList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryCountList(Map map) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForObject(NAMESPACES+"queryCountList",map);
	}

}