package com.huaxia.opas.service.report.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.report.InterestRateDifferAnalysisStatisticsDao;
import com.huaxia.opas.domain.report.ReportRatediffDetail;
import com.huaxia.opas.domain.report.SubreportDetail;
import com.huaxia.opas.domain.report.WorkloadDetail;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.report.InterestRateDifferAnalysisStatisticsService;

/**
 * @author gaohui (利率差异化分析统计报表)
 *
 */
public class InterestRateDifferAnalysisStatisticsServiceImpl extends AbstractService implements InterestRateDifferAnalysisStatisticsService, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2016488176177773810L;
	@Resource(name = "interestRateDifferAnalysisStatisticsDao")
	private InterestRateDifferAnalysisStatisticsDao interestRateDifferAnalysisStatisticsDao;
	public InterestRateDifferAnalysisStatisticsDao getInterestRateDifferAnalysisStatisticsDao() {
		return interestRateDifferAnalysisStatisticsDao;
	}
	public void setInterestRateDifferAnalysisStatisticsDao(
			InterestRateDifferAnalysisStatisticsDao interestRateDifferAnalysisStatisticsDao) {
		this.interestRateDifferAnalysisStatisticsDao = interestRateDifferAnalysisStatisticsDao;
	}


	@Override
	public Map<String, Object> findDataInterestRateDifferAnalysisStatisticsByDate(Map<String, Object> paraMap) {
		List<Map<String,Object>> listMap=interestRateDifferAnalysisStatisticsDao.selectInterestRateDifferAnalysisStatisticsDataByDate(paraMap);
		Map<String, Object> hj=new HashMap<String, Object>();
		double hjPassCount=0.0;//核准数量
		double hjAllCount=0.0;//总数量
		for (Map<String, Object> map : listMap) {
			hjPassCount+=Double.parseDouble(map.get("PASSCOUNT").toString());
			hjAllCount+=Double.parseDouble(map.get("ALLCOUNT").toString());
			String type = (String) map.get("TYPE");
			if ("1".equals(type)){
				type = "主附同申";
			} else if ("2".equals(type)){
				type = "单独申请附卡";
			} else if ("3".equals(type)){
				type = "单独申请主卡";
			}  
			map.put("TYPE", type);
		}
		String hjPassRate="0.0";
		hjPassRate=String.format("%.2f",(hjPassCount/hjAllCount)*100);
		hj.put("TYPE","总计");
		hj.put("PASSCOUNT",hjPassCount);
		hj.put("PASSRATE", Double.parseDouble(hjPassRate));
		List<Map<String,Object>> listMapHtml=new ArrayList<Map<String,Object>>();
		listMapHtml.add(hj);
		listMapHtml.addAll(listMap);
		Map<String,Object> dataMap=new HashMap<String, Object>();
		dataMap.put("rows",listMapHtml);
		return dataMap;
	}
	@Override
	public List<ReportRatediffDetail> queryInterestRateListByDate(Map<String, Object> paramMap) {
		return interestRateDifferAnalysisStatisticsDao.selectInterestRateListByDate(paramMap);
	}
	@Override
	public List<WorkloadDetail> queryWorkloadBreakdownListByDate(Map<String, Object> paramMap) {
		return interestRateDifferAnalysisStatisticsDao.selectWorkloadBreakdownListByDate(paramMap);
	}

	@Override
	public List<SubreportDetail> selectSubreportListByDate(Map<String, Object> paramMap) {
		return interestRateDifferAnalysisStatisticsDao.selectSubreportListByDate(paramMap);
	}
	@Override
	public List<SubreportDetail> selectSubmitFraudListByDate(Map<String, Object> paramMap) {
		return interestRateDifferAnalysisStatisticsDao.selectSubmitFraudListByDate(paramMap);
	}
	@Override
	public int queryWorkloadBreakdownListCountByDate(Map<String, Object> paramMap) {
		return interestRateDifferAnalysisStatisticsDao.selectWorkloadBreakdownListCountByDate(paramMap);
	}
	@Override
	public int queryInterestRateCountByDate(Map<String, Object> paramMap) {
		return interestRateDifferAnalysisStatisticsDao.selectInterestRateCountByDate(paramMap);
	}

}
