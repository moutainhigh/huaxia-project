package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.ExcellentCompanyList;

public interface ExcellentService {

	int queryExcellentCount(ExcellentCompanyList Excellent);

	List<ExcellentCompanyList> queryExcellentList(ExcellentCompanyList Excellent, int curNum, int pageNum);

	List<ExcellentCompanyList> checkIsExistExcellent(ExcellentCompanyList Excellent);

	int insertExcellent(ExcellentCompanyList Excellent);

	int updateExcellent(ExcellentCompanyList Excellent);

	int deleteExcellent(ExcellentCompanyList Excellent);

	int excellentStatusOK(ExcellentCompanyList Excellent);

	int excellentStatusStop(ExcellentCompanyList Excellent);

	int insertExcellentUpload(List<ExcellentCompanyList> list)  throws CoreException ;

}
