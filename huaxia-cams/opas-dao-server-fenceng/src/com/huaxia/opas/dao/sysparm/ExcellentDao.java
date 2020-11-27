package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huaxia.opas.domain.decision.ExcellentCompanyList;

public interface ExcellentDao {

	int queryExcellentCount(ExcellentCompanyList excellent);

	List<ExcellentCompanyList> queryExcellentList(ExcellentCompanyList excellent, int curNum, int pageNum);

	List<ExcellentCompanyList> checkIsExistExcellent(ExcellentCompanyList excellent);

	int insertExcellent(ExcellentCompanyList excellent);

	int updateExcellent(ExcellentCompanyList excellent);

	int deleteExcellent(ExcellentCompanyList excellent);

	int excellentStatusOK(ExcellentCompanyList excellent);

	int excellentStatusStop(ExcellentCompanyList excellent);

	int insertExcellentUpload(List<ExcellentCompanyList> list);

}
