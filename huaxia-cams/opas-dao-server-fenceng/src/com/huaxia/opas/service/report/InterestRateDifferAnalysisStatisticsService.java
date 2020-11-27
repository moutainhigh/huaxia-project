/**
 * 
 */
package com.huaxia.opas.service.report;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.report.ReportRatediffDetail;
import com.huaxia.opas.domain.report.SubreportDetail;
import com.huaxia.opas.domain.report.WorkloadDetail;

/**
 * @author gaohui (利率差异化分析统计报表)
 *
 */
public interface InterestRateDifferAnalysisStatisticsService {
	/**
	*@Title:findDataInterestRateDifferAnalysisStatisticsByDate
	*@Description:根据时间区间从不同报表中获取   申请类型、新旧户、卡产品、利率客户分层结果标签、利率代码、核准数量、核准数量占比 等信息
	*@param paraMap
	*@return
	*@author: gaohui
	*@Date:2017年3月22日下午2:32:30
	 */
	public Map<String, Object> findDataInterestRateDifferAnalysisStatisticsByDate(Map<String, Object> paraMap);

	/**
	*@Title:queryInterestRateListByDate
	*@Description:根据时间区间从不同报表中获取   条件码，卡产品名称，利率哭护分层结果标签，利率代码 等信息
	*@param paraMap
	*@return
	*@author: andong
	*@Date:2017年7月25日下午1:48:31
	 */
	public List<ReportRatediffDetail> queryInterestRateListByDate(Map<String, Object> paramMap);
	/**
	*@Title:queryWorkloadBreakdownListByDate
	*@Description:根据时间查工作量
	*@param paraMap
	*@return
	*@author: andong
	 * @param pageNum 
	 * @param num 
	*@Date:2017年8月3日下午1:38:25
	 */
	public List<WorkloadDetail> queryWorkloadBreakdownListByDate(Map<String, Object> paramMap);

	/**
	*@Title:queryWorkloadBreakdownListByDate
	*@Description:根据时间查工作量
	*@param paraMap
	*@return
	*@author: andong
	*@Date:2017年8月3日下午1:38:25
	 */
	public List<SubreportDetail> selectSubreportListByDate(Map<String, Object> paramMap);

	public List<SubreportDetail> selectSubmitFraudListByDate(Map<String, Object> paramMap);

	public int queryWorkloadBreakdownListCountByDate(Map<String, Object> paramMap);

	public int queryInterestRateCountByDate(Map<String, Object> paramMap);

}
