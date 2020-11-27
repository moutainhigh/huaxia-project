package com.huaxia.opas.service.rule;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;

public interface StatisInfoService {
	public boolean getInsideAppNum(Opasbizinpapplc1 on) throws CoreException;
	public String getFullInfo(Opasbizinpapplc1 on)throws CoreException;
}
