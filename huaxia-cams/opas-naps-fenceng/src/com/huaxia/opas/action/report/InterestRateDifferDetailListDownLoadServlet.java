package com.huaxia.opas.action.report;
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
import com.huaxia.opas.domain.report.ReportRatediffDetail;
import com.huaxia.opas.service.report.InterestRateDifferAnalysisStatisticsService;
import com.huaxia.opas.util.FileUploadProperties;
import com.huaxia.opas.util.ServletProxy;
import com.huaxia.opas.util.ZipMultiFile;
/**
 * 利率差异化明细表文件从服务器上下载
 * 
 * @author gaohui 
 *
 */
public class InterestRateDifferDetailListDownLoadServlet extends ServletProxy{
	
	private static final long serialVersionUID = -4452123075162015188L;
	@Autowired
	private InterestRateDifferAnalysisStatisticsService interestRateDifferAnalysisStatisticsService;

	public InterestRateDifferAnalysisStatisticsService getInterestRateDifferAnalysisStatisticsService() {
		return interestRateDifferAnalysisStatisticsService;
	}

    public void setInterestRateDifferAnalysisStatisticsService(
		InterestRateDifferAnalysisStatisticsService interestRateDifferAnalysisStatisticsService) {
    	this.interestRateDifferAnalysisStatisticsService = interestRateDifferAnalysisStatisticsService;
    }
    private static Logger logger = LoggerFactory.getLogger(InterestRateDifferDetailListDownLoadServlet.class);
    private Map<String, String> paroperMap = FileUploadProperties.getParoperMap();
	private String reportPath = paroperMap.get("report_path");
	
	public InterestRateDifferDetailListDownLoadServlet() {
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
		//设置response
		response.setContentType("application/csv;charset=gbk");
		String fileName = (String) paramMap.get("fileName");
		String userCode = (String) paramMap.get("userCode");
		String csvName  = fileName.substring(0, 22)+"csv";
		long startTime = System.currentTimeMillis() ;
		
		logger.info("利率差异化==报表开始下载 文件名："+fileName+"操作人："+userCode);
		double pageNum1 = 5000;
		int pageNum = 5000;
		int count = interestRateDifferAnalysisStatisticsService.queryInterestRateCountByDate(paramMap);
		int i = (int)Math.ceil(count/pageNum1);
		//根据日期查询报表内容集合
		int start  = 0;
		int end = 0;
		for (int pageSize = 0; pageSize <i; pageSize++) {
			start = start +pageSize*pageNum;
			end = end +(pageSize+1)*pageNum;
			paramMap.put("start", start);
			paramMap.put("end", end);
			List<ReportRatediffDetail> ratediffDetailList = interestRateDifferAnalysisStatisticsService.queryInterestRateListByDate(paramMap);
			//reportRatediffDetailList.addAll(ratediffDetailList);
			packFile(ratediffDetailList,csvName,response,pageSize);
		}
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
            long endTime = System.currentTimeMillis() ;
            logger.info("利率差异化==报表下载完成 文件名："+fileName+"操作人："+userCode+"下载文件使用的时间是:" + (endTime - startTime) + "毫秒");
        } catch (Exception e) {
            logger.error("csv文件处理异常：" + e.getMessage());
        }
        //删除下载或好的文件
        rmHardDiskFile(reportPath,csvName);
        rmHardDiskFile(reportPath,fileName);
	}
	
	/**
	 packFile 每次以追加的形式写入csv到服务器的目录下
	 * @param reportRatediffDetailList 每次循环出来的数据
	 * @param fileName 文件名
	 * @param response
	 * @param pageSize 页数
	 * @throws IOException
	 */
	public void packFile(List<ReportRatediffDetail> reportRatediffDetailList,String fileName,HttpServletResponse response,int pageSize) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		if (pageSize == 0){
			sb.append("条件码,卡产品名称,利率客户分层结果标签,利率代码,最低还款额客户分层结果标签,最低还款额比例代码,免息还款期客户分层结果标签,免息还款期代码,归档时间").append("\n");
		}

		for (ReportRatediffDetail reportRatediffDetail : reportRatediffDetailList){
			ReportRatediffDetail ratediffDetail = new ReportRatediffDetail();

			ratediffDetail.setAppId(reportRatediffDetail.getAppId());
			ratediffDetail.setProdName(reportRatediffDetail.getProdName());//卡产品名称
			ratediffDetail.setCustLevelTag(reportRatediffDetail.getCustLevelTag());//利率客户分层结果标签
			ratediffDetail.setRateCode(reportRatediffDetail.getRateCode());//利率代码
			ratediffDetail.setLowpayCustLevelTag(reportRatediffDetail.getLowpayCustLevelTag());//最低还款额客户分层结果标签
			ratediffDetail.setRepayRatioValue(reportRatediffDetail.getRepayRatioValue());//最低还款额比例代码
			ratediffDetail.setFreeCustLevelTag(reportRatediffDetail.getFreeCustLevelTag());//免息还款期客户分层结果标签
			ratediffDetail.setRepayFreeCode(reportRatediffDetail.getRepayFreeCode());//免息还款期代码
			ratediffDetail.setToArchiveTime(reportRatediffDetail.getToArchiveTime());
			//reportRatediffDetailAllList.add(ratediffDetail);
			sb.append(ratediffDetail.getAppId()+","+(ratediffDetail.getProdName()==null?"":ratediffDetail.getProdName()+"\t")+","+(ratediffDetail.getCustLevelTag()==null?"":ratediffDetail.getCustLevelTag())+","+(ratediffDetail.getRateCode()==null?"":ratediffDetail.getRateCode()+"\t")+","
					 +(ratediffDetail.getLowpayCustLevelTag()==null?"":ratediffDetail.getLowpayCustLevelTag()+"\t")+","+(ratediffDetail.getRepayRatioValue()==null?"":ratediffDetail.getRepayRatioValue()+"\t")+","
					 +(ratediffDetail.getFreeCustLevelTag()==null?"":ratediffDetail.getFreeCustLevelTag()+"\t")+","+(ratediffDetail.getRepayFreeCode()==null?"":ratediffDetail.getRepayFreeCode()+"\t")+","+(ratediffDetail.getToArchiveTime()==null?"":ratediffDetail.getToArchiveTime())+"\t").append("\n");
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
