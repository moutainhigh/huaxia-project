package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Promoter;
import com.huaxia.opas.domain.sysparm.PromoterHis;

public interface PromoterRiskDao {

	public int queryPromoterCount(Promoter prom) throws CoreException;

	public int queryPromoterHisCount(PromoterHis promHis) throws CoreException;
	
	public List<Promoter> queryPromoterList(Promoter prom, int curNum, int pageNum) throws CoreException;

	public List<PromoterHis> queryPromoterHisList(PromoterHis promHis, int curNum, int pageNum) throws CoreException;
	
	public int insertPromoter(Promoter prom) throws CoreException;

	public void updatePromoter(Promoter prom) throws CoreException;

	public void deletePromoter(String autoId) throws CoreException;

	public void openPromoter(Promoter prom) throws CoreException;

	public void closePromoter(Promoter prom) throws CoreException;

	public void insertPromoterHis(PromoterHis promHis) throws CoreException;

	Promoter selectByPrimaryKey(String autoId);
	//推广员批量导入
	public int insertPromoterImportFile(List<Promoter> list);
}
