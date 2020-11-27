package com.huaxia.opas.service.sysparm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SubReport;
import com.huaxia.opas.service.BaseService;

public interface SubReportService extends BaseService<SubReport> {

	int queryCount(SubReport subReport) throws CoreException;

	List<SubReport> queryUnfinishedList(SubReport subReport, int curNum,
			int pageNum) throws CoreException;

	int queryCount1(SubReport subReport) throws CoreException;

	List<SubReport> queryFinishedList(SubReport subReport, int curNum,
			int pageNum) throws CoreException;

	int toFinished(SubReport subReport) throws CoreException, Exception;

	int toUnfinished(SubReport subReport) throws CoreException, Exception;

	int toCredit(SubReport subReport) throws CoreException, Exception;

	int commitAll(SubReport subReport) throws CoreException, Exception;

	SubReport querySubReason(String appId) throws CoreException;

	int queryDayCount(Map<String, Object> map) throws CoreException;

	int queryMonthCount(Map<String, Object> map) throws CoreException;

	Date queryDate(Date start) throws CoreException;

	List<SubReport> selectCommitAll(SubReport subReport) throws CoreException;

	int updateApplyAllotList(List<SubReport> list) throws CoreException;
	

}
