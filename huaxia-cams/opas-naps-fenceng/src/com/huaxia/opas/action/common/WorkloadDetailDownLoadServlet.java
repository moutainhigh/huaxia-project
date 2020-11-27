package com.huaxia.opas.action.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.huaxia.opas.domain.report.WorkloadDetail;
import com.huaxia.opas.service.report.InterestRateDifferAnalysisStatisticsService;
import com.huaxia.opas.util.FileUploadProperties;
import com.huaxia.opas.util.ServletProxy;
import com.huaxia.opas.util.ZipMultiFile;
/***
 * 工作量明细报表下载
 * @author andong
 *
 */
public class WorkloadDetailDownLoadServlet extends ServletProxy {
	
	private static final long serialVersionUID = -6752154714932308737L;
	@Autowired
	private InterestRateDifferAnalysisStatisticsService interestRateDifferAnalysisStatisticsService;

	public InterestRateDifferAnalysisStatisticsService getInterestRateDifferAnalysisStatisticsService() {
		return interestRateDifferAnalysisStatisticsService;
	}

    public void setInterestRateDifferAnalysisStatisticsService(
		InterestRateDifferAnalysisStatisticsService interestRateDifferAnalysisStatisticsService) {
    	this.interestRateDifferAnalysisStatisticsService = interestRateDifferAnalysisStatisticsService;
    }

	private static Logger logger = LoggerFactory.getLogger(WorkloadDetailDownLoadServlet.class);
	private Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
	private String reportPath = paroperMap.get("report_path");
	
	public WorkloadDetailDownLoadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			// 获取前台传来的url上的参数
			String startDate = request.getParameter("startDate").toString();
			String endDate = request.getParameter("endDate").toString();
			String fileName = request.getParameter("fileName").toString();
			String userCode = request.getParameter("userCode").toString();
			
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
	
	private void exportExcel(HttpServletRequest request,HttpServletResponse response,Map<String,Object> paramMap) throws IOException, CoreException,InterruptedException{
		//设置response
		response.setContentType("application/x-download;charset=gbk");
		String fileName = (String) paramMap.get("fileName");
		String userCode = (String) paramMap.get("userCode");
		String csvName  = fileName.substring(0, 22)+"csv";
		double pageNum1 = 5000;
		int pageNum = 5000;
		long startTime = System.currentTimeMillis() ;
		logger.info("工作量==报表开始下载文件名："+fileName+"操作人："+userCode);
		int count = interestRateDifferAnalysisStatisticsService.queryWorkloadBreakdownListCountByDate(paramMap);
		if (count!=0){
			int i = (int)Math.ceil(count/pageNum1);
			//根据日期查询报表内容集合
			int start  = 0;
			int end = 0;
			for (int pageSize = 0; pageSize <i; pageSize++) {
				start = start +pageSize*pageNum;
				end = end +(pageSize+1)*pageNum;
				paramMap.put("start", start);
				paramMap.put("end", end);
				//循环查询归档数据
				List<WorkloadDetail> workloadBreakdownPageList= interestRateDifferAnalysisStatisticsService.queryWorkloadBreakdownListByDate(paramMap);
				//每次循环出的数据都写入csv文件  以追加的形式写入
				packFile(workloadBreakdownPageList,csvName,response,pageSize);
			}
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("申请件条形码,流水号字母,录入商,渠道,地区,客户姓名,证件类型,进入系统时间,录入员,录入员组别,征信调查员,征信调查员组别,授信审批员,授信审批员组别,政策码1,政策码2,政策码3,违例码1,违例码2,违例码3,拒绝码1,拒绝码2,拒绝码3,决策结果,归档日期,归档员,申请卡种,审批卡种,审批额度").append("\n");
			
			String reportFile=reportPath+csvName;
			File dataFile = new File(reportFile);
			if (!dataFile.exists()) {
				dataFile.createNewFile();
			}
			//不覆盖,在后边继续写入  追加
			FileOutputStream outputStream = new FileOutputStream(dataFile,true); 
			outputStream.write(sb.toString().getBytes("GBK"));
			outputStream.flush();
			outputStream.close();
		}
        //response.setContentType("application/csv;charset=gbk");
		fileName=request.getParameter("fileName");
		//文件名中文乱码
		fileName = new String(fileName.getBytes("iso-8859-1"),"utf-8");
		response.addHeader("Content-Disposition",   "attachment;filename=" + fileName); 
		String reportLocal=reportPath+csvName;
		File srcFiles = (new File(reportLocal));
		String zipName=reportPath+fileName;
		File zipFile = new File(zipName);
		ZipMultiFile.zipFile(srcFiles,zipFile);
		
		if (logger.isDebugEnabled()) {
			logger.debug("CSV路径为[" + reportLocal + "]");
		}
		File file = new File(zipName);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("csv文件不存在，请重新下载");
		}
		
        byte[] data = null;
        try {
        	BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
            data = new byte[input.available()];
            input.read(data);
            response.getOutputStream().write(data);
            input.close();
            logger.info("工作量==报表下载完成  文件名："+fileName+"操作人："+userCode);
            long endTime = System.currentTimeMillis() ;
            System.out.println("下载文件使用的时间是:" + (endTime - startTime) + "毫秒");
        } catch (Exception e) {
            logger.error("csv文件处理异常：" + e.getMessage());
        }
        //删除下载或好的文件
        rmHardDiskFile(reportPath,fileName);
        rmHardDiskFile(reportPath,csvName);
	}
	
	/**
	 packFile 每次以追加的形式写入csv到服务器的目录下
	 * @param workloadBreakdownPageList 每次循环出来的数据
	 * @param fileName 文件名
	 * @param response
	 * @param pageSize 页数
	 * @throws IOException
	 */
	public void packFile(List<WorkloadDetail> workloadBreakdownPageList,String fileName,HttpServletResponse response,int pageSize) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		if (pageSize == 0){
			sb.append("申请件条形码,流水号字母,录入商,渠道,地区,客户姓名,证件类型,进入系统时间,录入员,录入员组别,征信调查员,征信调查员组别,授信审批员,授信审批员组别,政策码1,政策码2,政策码3,违例码1,违例码2,违例码3,拒绝码1,拒绝码2,拒绝码3,决策结果,归档日期,归档员,申请卡种,审批卡种,审批额度").append("\n");
		}
		for (WorkloadDetail workloadBreakdown : workloadBreakdownPageList){
			WorkloadDetail workloadDetail = new WorkloadDetail();
			//appResult 0-批准  1-拒绝
			String appResult = workloadBreakdown.getAppResult();
			if (!"".equals(appResult)&&null!=appResult) {
				if ("1".equals(appResult)){
					workloadBreakdown.setViolateCode1("");//违例码1
					workloadBreakdown.setViolateCode2("");
					workloadBreakdown.setViolateCode3("");
					workloadBreakdown.setPolicyCode1("");//政策码1
					workloadBreakdown.setPolicyCode2("");
					workloadBreakdown.setPolicyCode3("");
					workloadBreakdown.setApproveCreditLimit("");//审批额度
					workloadBreakdown.setApproveCreditProduct("");//审批卡种
				} else if ("0".equals(appResult)){
					workloadBreakdown.setRefuseCode1("");//拒绝码1
					workloadBreakdown.setRefuseCode2("");
					workloadBreakdown.setRefuseCode3("");
				} 
			}
			workloadDetail.setAppId(workloadBreakdown.getAppId());//申请件条形码
			workloadDetail.setAppIdLetter(workloadBreakdown.getAppIdLetter());//流水号字母
			workloadDetail.setEntryClerk(workloadBreakdown.getEntryClerk());//录入商
			workloadDetail.setChannel(workloadBreakdown.getChannel());//渠道
			workloadDetail.setArea(workloadBreakdown.getArea());//地区
			workloadDetail.setC1Cname(workloadBreakdown.getC1Cname());//客户姓名
			workloadDetail.setC1Idtype(workloadBreakdown.getC1Idtype());//证件类型
			workloadDetail.setCrtDate(workloadBreakdown.getCrtDate());//进入系统时间
			workloadDetail.setAuditorName(workloadBreakdown.getAuditorName());//录入员
			workloadDetail.setInputGroup(workloadBreakdown.getInputGroup());//录入员组别
			workloadDetail.setCrtUser(workloadBreakdown.getCrtUser());//征信调查员
			workloadDetail.setCreditGroup(workloadBreakdown.getCreditGroup());//征信调查员组别
			workloadDetail.setApprover(workloadBreakdown.getApprover());//授信审批员
			workloadDetail.setApproverGroup(workloadBreakdown.getApproverGroup());//授信审批员组别
			workloadDetail.setPolicyCode1(workloadBreakdown.getPolicyCode1());//政策码1
			workloadDetail.setPolicyCode2(workloadBreakdown.getPolicyCode2());//政策码2
			workloadDetail.setPolicyCode3(workloadBreakdown.getPolicyCode3());//政策码3
			workloadDetail.setViolateCode1(workloadBreakdown.getViolateCode1());//违例码1
			workloadDetail.setViolateCode2(workloadBreakdown.getViolateCode2());//违例码2
			workloadDetail.setViolateCode3(workloadBreakdown.getViolateCode3());//违例码3
			workloadDetail.setRefuseCode1(workloadBreakdown.getRefuseCode1());//拒绝码1
			workloadDetail.setRefuseCode2(workloadBreakdown.getRefuseCode2());//拒绝码2
			workloadDetail.setRefuseCode3(workloadBreakdown.getRefuseCode3());//拒绝码3
			workloadDetail.setMastercardDecresult(workloadBreakdown.getMastercardDecresult());//决策结果
			workloadDetail.setToArchiveTime(workloadBreakdown.getToArchiveTime());//归档日期
			workloadDetail.setOperator(workloadBreakdown.getOperator());//归档员
			workloadDetail.setAppProd(workloadBreakdown.getAppProd());//申请卡种
			workloadDetail.setApproveCreditProduct(workloadBreakdown.getApproveCreditProduct());//审批卡种
			workloadDetail.setApproveCreditLimit(workloadBreakdown.getApproveCreditLimit());//审批额度
			//workloadDetailList.add(workloadDetail);
			//查出来的数据直接写入csv
			sb.append((workloadDetail.getAppId()==null?"":workloadDetail.getAppId())+","+(workloadDetail.getAppIdLetter()==null?"":workloadDetail.getAppIdLetter())+","+(workloadDetail.getEntryClerk()==null?"":workloadDetail.getEntryClerk())+","+(workloadDetail.getChannel()==null?"":workloadDetail.getChannel())+","
					+(workloadDetail.getArea()==null?"":workloadDetail.getArea())+","+(workloadDetail.getC1Cname()==null?"":workloadDetail.getC1Cname())+","+(workloadDetail.getC1Idtype()==null?"":workloadDetail.getC1Idtype())+","+(workloadDetail.getCrtDate()==null?"":workloadDetail.getCrtDate())+","
					+(workloadDetail.getAuditorName()==null?"":workloadDetail.getAuditorName())+","+(workloadDetail.getInputGroup()==null?"":workloadDetail.getInputGroup())+","+(workloadDetail.getCrtUser()==null?"":workloadDetail.getCrtUser())+","+(workloadDetail.getCreditGroup()==null?"":workloadDetail.getCreditGroup())+","
					+(workloadDetail.getApprover()==null?"":workloadDetail.getApprover())+","+(workloadDetail.getApproverGroup()==null?"":workloadDetail.getApproverGroup())+","+(workloadDetail.getPolicyCode1()==null?"":workloadDetail.getPolicyCode1())+","+(workloadDetail.getPolicyCode2()==null?"":workloadDetail.getPolicyCode2())+","
					+(workloadDetail.getPolicyCode3()==null?"":workloadDetail.getPolicyCode3())+","+(workloadDetail.getViolateCode1()==null?"":workloadDetail.getViolateCode1())+","+(workloadDetail.getViolateCode2()==null?"":workloadDetail.getViolateCode2())+","+(workloadDetail.getViolateCode3()==null?"":workloadDetail.getViolateCode3())+","
					+(workloadDetail.getRefuseCode1()==null?"":workloadDetail.getRefuseCode1())+","+(workloadDetail.getRefuseCode2()==null?"":workloadDetail.getRefuseCode2())+","+(workloadDetail.getRefuseCode3()==null?"":workloadDetail.getRefuseCode3())+","+(workloadDetail.getMastercardDecresult()==null?"":workloadDetail.getMastercardDecresult())+","
					+(workloadDetail.getToArchiveTime()==null?"":workloadDetail.getToArchiveTime())+","+(workloadDetail.getOperator()==null?"":workloadDetail.getOperator())+","+(workloadDetail.getAppProd()==null?"":workloadDetail.getAppProd())+","+(workloadDetail.getApproveCreditProduct()==null?"":workloadDetail.getApproveCreditProduct())+","
					+(workloadDetail.getApproveCreditLimit()==null?"":workloadDetail.getApproveCreditLimit())).append("\n");
		}
		
		String reportFile=reportPath+fileName;
		File dir = new File(reportPath);
		if (!dir.exists()) {
			//创建文件夹
			dir.mkdirs();
		}
		File dataFile = new File(reportFile);
		if (!dataFile.exists()) {
			dataFile.createNewFile();
		}
		//不覆盖,在后边继续写入  追加
		FileOutputStream outputStream = new FileOutputStream(dataFile,true); 
		outputStream.write(sb.toString().getBytes("GBK"));
		outputStream.flush();
		outputStream.close();
	}
	
	/**
	 *@Title:rmHardDiskFile
	 *@Description:删除硬盘上的文件
	 *@param filePath 文件目录
	 *@param fileName 文件名
	 *@author: gaohui
	 *@Date:2017年11月23日下午4:18:16
	 */
	public static void rmHardDiskFile(String filePath,String fileName){
		try {
		String path=filePath+fileName;
		String[] cmd=new String[]{"/bin/sh","-c","rm -rf "+path};
		Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 & "+""+"] 文件删除异常,文件名: {}",fileName);
			}
		}
	}
	
	

}
