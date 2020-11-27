package com.huaxia.opas.cache.service.rule;

import com.huateng.huaxia.xom.FicoGradeInfo;
import com.huateng.huaxia.xom.LoansInfo;
import com.huateng.huaxia.xom.QueryThdSide;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;

public interface PlazeRuleModelBuildService {
	
	public Opasbizinpapplc1 queryOpasbizinpapplc1(String appId) throws CoreException;

	public QueryThdSide createQueryThdSide(Opasbizinpapplc1 on1) throws CoreException;

	public FicoGradeInfo queryFicoMsg(Opasbizinpapplc1 on1) throws CoreException;

	public LoansInfo queryLoansMsg(Opasbizinpapplc1 on1) throws CoreException;
}
