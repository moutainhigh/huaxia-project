package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.PromoterRiskDao;
import com.huaxia.opas.domain.sysparm.Promoter;
import com.huaxia.opas.domain.sysparm.PromoterHis;

public class PromoterRiskDaoImpl extends AbstractDAO implements PromoterRiskDao {

	private static final long serialVersionUID = -6905039237463254250L;

	private static final String NAMESPACES = "Promoter.";
	
	@Override
	public Promoter selectByPrimaryKey(String autoId) {
		return (Promoter) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public int queryPromoterCount(Promoter prom) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryPromoterCount", prom);
	}
	
	@Override
	public int queryPromoterHisCount(PromoterHis promHis) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryPromoterHisCount", promHis);
	}
	
	@Override
	public List<Promoter> queryPromoterList(Promoter prom, int curNum, int pageNum) throws CoreException{
		List<Promoter> list = new ArrayList<Promoter>();
		list = getSqlMap().queryForList(NAMESPACES + "queryPromoterList", prom, curNum, pageNum);
		return list;
	}
	
	@Override
	public List<PromoterHis> queryPromoterHisList(PromoterHis promHis, int curNum, int pageNum) throws CoreException{
		List<PromoterHis> list = new ArrayList<PromoterHis>();
		list = getSqlMap().queryForList(NAMESPACES + "queryPromoterHisList", promHis, curNum, pageNum);
		return list;
	}

	@Override
	public int insertPromoter(Promoter prom) throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertPromoter", prom);
	}

	@Override
	public void updatePromoter(Promoter prom) throws CoreException{
		getSqlMap().update(NAMESPACES + "updatePromoter", prom);
	}

	@Override
	public void deletePromoter(String autoId) throws CoreException{
		getSqlMap().delete(NAMESPACES + "deletePromoter", autoId);
	}

	@Override
	public void openPromoter(Promoter prom) throws CoreException{
		getSqlMap().update(NAMESPACES + "openPromoter", prom);
	}

	@Override
	public void closePromoter(Promoter prom) throws CoreException{
		getSqlMap().update(NAMESPACES + "closePromoter", prom);
	}

	@Override
	public void insertPromoterHis(PromoterHis promHis) throws CoreException {
		getSqlMap().insert(NAMESPACES + "insertPromoterHis", promHis);
	}
	@Override
	public int insertPromoterImportFile(List<Promoter> list) {
		return getSqlMap().insert(NAMESPACES + "insertPromoterImportFile", list);
	}

}
