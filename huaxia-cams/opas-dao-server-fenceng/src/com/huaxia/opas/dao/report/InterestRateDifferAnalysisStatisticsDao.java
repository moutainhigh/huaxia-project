package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.report.ReportRatediffDetail;
import com.huaxia.opas.domain.report.SubreportDetail;
import com.huaxia.opas.domain.report.WorkloadDetail;

/**
 * 
 * @author gaohui(利率差异化分析统计报表)
 *
 */
public interface InterestRateDifferAnalysisStatisticsDao {
	/**
	*@Title:selectInterestRateDifferAnalysisStatisticsDataByDate
	*@Description:根据时间区间从不同报表中获取   申请类型、新旧户、卡产品、利率客户分层结果标签、利率代码、核准数量、核准数量占比 等信息
	*@param paramsMap
	*@return
	*@author: gaohui
	*@Date:2017年3月22日下午2:56:01
	 */
	public List<Map<String, Object>> selectInterestRateDifferAnalysisStatisticsDataByDate(Map<String, Object> paramsMap);

	/**
	*@Title:selectInterestRateListByDate
	*@Description:根据时间区间从不同报表中获取   条件码，卡产品名称，利率哭护分层结果标签，利率代码 等信息
	*@param paraMap
	*@return
	*@author: andong
	*@Date:2017年7月25日下午1:50:44
	 */
	public List<ReportRatediffDetail> selectInterestRateListByDate(Map<String, Object> paramMap);
	/**
	*@Title:selectWorkloadBreakdownListByDate
	*@Description:根据时间区间
	*@param paraMap
	*@return
	*@author: andong
	 * @param pageNum 
	 * @param pageSize 
	*@Date:2017年8月3日下午1:50:14
	 */
	public List<WorkloadDetail> selectWorkloadBreakdownListByDate(Map<String, Object> paramMap);
	/**
	*@Title:selectSubreportListByDate
	*@Description:根据时间区间
	*@param paraMap
	*@return
	*@author: andong
	*@Date:2017年10月17日下午3:12:24
	 */
	public List<SubreportDetail> selectSubreportListByDate(Map<String, Object> paramMap);

	public List<SubreportDetail> selectSubmitFraudListByDate(Map<String, Object> paramMap);

	public int selectWorkloadBreakdownListCountByDate(Map<String, Object> paramMap);

	public int selectInterestRateCountByDate(Map<String, Object> paramMap);

}