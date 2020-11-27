package com.huaxia.opas.dao.report.impl;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.InterestRateDifferAnalysisStatisticsDao;
import com.huaxia.opas.domain.report.ReportRatediffDetail;
import com.huaxia.opas.domain.report.SubreportDetail;
import com.huaxia.opas.domain.report.WorkloadDetail;


/**
 * @author gaohui(利率差异化分析统计报表)
 *
 */
public class InterestRateDifferAnalysisStatisticsDaoImpl extends AbstractDAO implements InterestRateDifferAnalysisStatisticsDao{
	private static final long serialVersionUID = 3125817911818333422L;
	private static final String NAMESPACES = "InterestRateDifferAnalysisStatistics.";

	@Override
	public List<Map<String, Object>> selectInterestRateDifferAnalysisStatisticsDataByDate(Map<String, Object> paramsMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectInterestRateDifferAnalysisStatisticsDataByDate", paramsMap);
	}

	@Override
	public List<ReportRatediffDetail> selectInterestRateListByDate(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectInterestRateListByDate", paramMap);
	}

	@Override
	public List<WorkloadDetail> selectWorkloadBreakdownListByDate(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectWorkloadBreakdownListByDate", paramMap);
	}


	@Override
	public List<SubreportDetail> selectSubreportListByDate(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectSubreportListByDate", paramMap);
	}

	@Override
	public List<SubreportDetail> selectSubmitFraudListByDate(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectSubmitFraudListByDate", paramMap);
	}

	@Override
	public int selectWorkloadBreakdownListCountByDate(Map<String, Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectWorkloadBreakdownListCountByDate", paramMap);
	}

	@Override
	public int selectInterestRateCountByDate(Map<String, Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectInterestRateCountByDate", paramMap);
	}

}
