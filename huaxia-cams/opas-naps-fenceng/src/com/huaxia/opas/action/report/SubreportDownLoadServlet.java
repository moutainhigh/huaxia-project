package com.huaxia.opas.action.report;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.report.SubreportDetail;
import com.huaxia.opas.service.report.InterestRateDifferAnalysisStatisticsService;
import com.huaxia.opas.util.ServletProxy;
/**
 * 提报环节报表下载
 * 
 * @author andong 
 *
 */
public class SubreportDownLoadServlet extends ServletProxy {
	
	private static final long serialVersionUID = -4452123075162015188L;
	private static Logger logger = LoggerFactory.getLogger(SubreportDownLoadServlet.class);
	
	@Autowired
	private InterestRateDifferAnalysisStatisticsService interestRateDifferAnalysisStatisticsService;

	public InterestRateDifferAnalysisStatisticsService getInterestRateDifferAnalysisStatisticsService() {
		return interestRateDifferAnalysisStatisticsService;
	}

    public void setInterestRateDifferAnalysisStatisticsService(
		InterestRateDifferAnalysisStatisticsService interestRateDifferAnalysisStatisticsService) {
    	this.interestRateDifferAnalysisStatisticsService = interestRateDifferAnalysisStatisticsService;
    }
	
	public SubreportDownLoadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			// 获取前台传来的url上的参数
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String fileName = request.getParameter("fileName");
			String userCode = request.getParameter("userCode");
			
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startDate", startDate);
			paramMap.put("endDate", endDate);
			paramMap.put("fileName", fileName);
			paramMap.put("userCode", userCode);
			exportExcel(request,response,paramMap);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private void exportExcel(HttpServletRequest request,HttpServletResponse response,Map<String,Object> paramMap) throws IOException, CoreException{
		String fileName = (String) paramMap.get("fileName");
		//设置response
		response.setContentType("application/csv;charset=gbk");
		String userCode = (String) paramMap.get("userCode");
		logger.info("提报环节==报表开始下载   文件名："+fileName+"操作人："+userCode);
		List<SubreportDetail> SubreportDetailAllList = new ArrayList<SubreportDetail>();
		//根据日期查询提报list
		List<SubreportDetail> SubreportDetailList = interestRateDifferAnalysisStatisticsService.selectSubreportListByDate(paramMap);
		
		//根据日期查询欺诈list
		List<SubreportDetail> SubmitFraudList = interestRateDifferAnalysisStatisticsService.selectSubmitFraudListByDate(paramMap);
		
		for (SubreportDetail submitFraud : SubmitFraudList){
			SubreportDetail submitFraudDetail = new SubreportDetail();
			//提报环节
			String submitType = "提报反欺诈";
			String vdelStatus = submitFraud.getVdelStatus();
			if (!"".equals(vdelStatus)&&null!=vdelStatus) {
				if ("0".equals(vdelStatus)) {
					vdelStatus="未完成";
				} else if ("1".equals(vdelStatus)) {
					vdelStatus="退回欺诈调查";
				} else if ("2".equals(vdelStatus)) {
					vdelStatus="提交欺诈审批";
				} else if ("3".equals(vdelStatus)) {
					vdelStatus="退回征信调查";
				} else if ("4".equals(vdelStatus)) {
					vdelStatus="已完成";
				} 
			}
			submitFraud.setVdelStatus(vdelStatus);
			submitFraudDetail.setAppId(submitFraud.getAppId());
			submitFraudDetail.setProdName(submitFraud.getProdName());//卡产品名称
			submitFraudDetail.setSubmitType(submitType);//提报环节
			submitFraudDetail.setCurrOptUser(submitFraud.getCurrOptUser());//提报人员
			submitFraudDetail.setCrtDate(submitFraud.getCrtDate());//提报时间
			submitFraudDetail.setCrtUser(submitFraud.getCrtUser());//处理人员
			submitFraudDetail.setVdelStatus(submitFraud.getVdelStatus());//提报环节处理情况
			submitFraudDetail.setOperateDesc(submitFraud.getOperateDesc());//申请件当前状态
			SubreportDetailAllList.add(submitFraudDetail);
		}
		
		for (SubreportDetail subreportDetail : SubreportDetailList){
			SubreportDetail subDetail = new SubreportDetail();
			//提报环节
			String submitType = subreportDetail.getSubmitType();
			if (!"".equals(submitType)&&null!=submitType) {
				if ("1".equals(submitType)) {
					submitType="提报反欺诈";
				} else if ("2".equals(submitType)) {
					submitType="提报催收";
				} else if ("3".equals(submitType)) {
					submitType="提报账户";
				} else if ("4".equals(submitType)) {
					submitType="提报授权";
				} 
			}
			
			subreportDetail.setSubmitType(submitType);
			//提报环节处理情况
			String vdelStatus = subreportDetail.getVdelStatus();
			if (!"".equals(vdelStatus)&&null!=vdelStatus) {
				if ("0".equals(vdelStatus)) {
					vdelStatus="未完成";
				} else if ("1".equals(vdelStatus)) {
					vdelStatus="退回征信调查";
				}  else if ("2".equals(vdelStatus)) {
					vdelStatus="已完成";
				}  
			}
			
			
			subreportDetail.setVdelStatus(vdelStatus);
			subDetail.setAppId(subreportDetail.getAppId());
			subDetail.setProdName(subreportDetail.getProdName());//卡产品名称
			subDetail.setSubmitType(subreportDetail.getSubmitType());//提报环节
			subDetail.setCurrOptUser(subreportDetail.getCurrOptUser());//提报人员
			subDetail.setCrtDate(subreportDetail.getCrtDate());//提报时间
			subDetail.setCrtUser(subreportDetail.getCrtUser());//处理人员
			subDetail.setVdelStatus(subreportDetail.getVdelStatus());//提报环节处理情况
			subDetail.setOperateDesc(subreportDetail.getOperateDesc());//申请件当前状态
			SubreportDetailAllList.add(subDetail);
		}
		PrintWriter pw = response.getWriter();
		pw.println("条件码,申请卡种,提报环节,提报人员,提报时间,处理人员,提报环节处理情况,申请件当前状态");
		
		
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.addHeader("Content-Disposition",   "attachment;filename=" + fileName);
		StringBuilder sb = new StringBuilder();
		for (SubreportDetail o:SubreportDetailAllList) {
			sb.append(o.getAppId()+","+(o.getProdName()==null?"":o.getProdName()+"\t")+","+(o.getSubmitType()==null?"":o.getSubmitType())+","+(o.getCurrOptUser()==null?"":o.getCurrOptUser()+"\t")+","
					 +(o.getCrtDate()==null?"":o.getCrtDate()+"\t")+","+(o.getCrtUser()==null?"":o.getCrtUser()+"\t")+","
					 +(o.getVdelStatus()==null?"":o.getVdelStatus()+"\t")+","+(o.getOperateDesc()==null?"":o.getOperateDesc())+"\t").append("\n");
		}
		pw.print(sb);
		pw.close();
		if(logger.isInfoEnabled()){
			logger.info("提报环节==报表完成下载   文件名："+fileName+"操作人："+userCode);
		}
		
	}
}
