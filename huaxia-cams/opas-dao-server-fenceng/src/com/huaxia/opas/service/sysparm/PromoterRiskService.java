package com.huaxia.opas.service.sysparm;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.Promoter;
import com.huaxia.opas.domain.sysparm.PromoterHis;

public interface PromoterRiskService {

	public int queryPromoterCount(Promoter prom) throws CoreException;

	public int queryPromoterHisCount(PromoterHis promHis) throws CoreException;

	public List<Promoter> queryPromoterList(Promoter prom, int curNum,
			int pageNum) throws CoreException;

	public List<PromoterHis> queryPromoterHisList(PromoterHis promHis,
			int curNum, int pageNum) throws CoreException;

	public int insertPromoter(Promoter prom, PromoterHis promHis)
			throws CoreException, ParseException;

	public void updatePromoter(Promoter prom, PromoterHis promHis)
			throws CoreException, ParseException;

	public void deletePromoters(List<String> autoIds) throws CoreException,
			ParseException;

	public void closePromoters(List<String> autoIds, String user)
			throws CoreException, ParseException;

	public void openPromoters(List<String> autoIds, String user)
			throws CoreException, ParseException;

	public int insertPromoterRiskList(List<Promoter> list, BatchfileInfo batchfileInfo) throws Exception;

	
}
