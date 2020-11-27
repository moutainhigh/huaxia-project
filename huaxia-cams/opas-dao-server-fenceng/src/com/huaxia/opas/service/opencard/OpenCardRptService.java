package com.huaxia.opas.service.opencard;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.opencard.OpenCardRpt;

public interface OpenCardRptService {

	public int insertOpenCardRpt(OpenCardRpt openCardRpt) throws CoreException;

	public int insertOpenCardRptSelective(OpenCardRpt openCardRpt) throws CoreException;

	public int deleteOpenCardRptByBizOpenCardRptId(String bizOpenCardRptId) throws CoreException;

	public int updateOpenCardRpt(OpenCardRpt openCardRpt) throws CoreException;

	public int updateOpenCardRptSelective(OpenCardRpt openCardRpt) throws CoreException;

	public OpenCardRpt queryOpenCardRptByBizOpenCardRptId(String bizOpenCardRptId) throws CoreException;

	public void insertOpenCardRptSelectiveBach(List<OpenCardRpt> OpenCardRptList) throws CoreException;

	public List<OpenCardRpt> queryOpenCardRptListByInfo(Map paramMap, int curNum, int pageNum) throws CoreException;

	public int queryOpenCardRptCountByInfo(Map paramMap) throws CoreException;
}
