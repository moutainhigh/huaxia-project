package com.huaxia.opas.action.common;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.checking.QualityChecking;
import com.huaxia.opas.domain.dict.ApDict;
import com.huaxia.opas.domain.dict.ApDictDetail;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.checking.QualityCheckingService;
import com.huaxia.opas.service.dict.ApDictDetailService;
import com.huaxia.opas.service.dict.ApDictService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.DownloadUtil;
import com.huaxia.opas.util.ServletProxy;
import com.ibm.icu.text.SimpleDateFormat;

import net.sf.json.JSONObject;

public class QualityCheckingDownServlet  extends ServletProxy {
	private static final long serialVersionUID = 1L;
//	ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"config/naps-config.xml","config/dubbo-reference.xml"});
//	QualityCheckingService qualityCheckingService=(QualityCheckingService) ac.getBean("qualityCheckingService");
//	ApUserService  apUserService = (ApUserService)ac.getBean("apUserService");
	@Autowired
	private QualityCheckingService qualityCheckingService;
	@Autowired
	private ApUserService  apUserService;
	@Autowired
	private ApDictService apDictService ;
	@Autowired
	private ApDictDetailService apDictDetailService ;
	public QualityCheckingDownServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			// 获取前台传来的url上的参数
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String fileName = request.getParameter("fileName");
		
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startDate", startDate);
			paramMap.put("endDate", endDate);
			paramMap.put("fileName", fileName);
			exportExcel(request,response,paramMap);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	@SuppressWarnings("deprecation")
	private void exportExcel(HttpServletRequest request,HttpServletResponse response,Map<String,Object> paramMap) throws IOException, CoreException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		//产生表格标题行
		HSSFRow row = sheet.createRow(0);
		
//		sheet.setColumnWidth((short)0, 250 * 20);
//		sheet.setColumnWidth((short)1, 250 * 20);
//		sheet.setColumnWidth((short)3, 250 * 20);
//		sheet.setColumnWidth((short)5, 250 * 20);
//		sheet.setColumnWidth((short)9, 250 * 50);
		
		String[] rowHeader = {"检查日期","流水号","被检查人-录入","被检查人-征信","被检查人-审批","被检查人-征审合一","检查情况","质检结果","检查人","操作员-审查","操作员-征信","操作员-审批","备注","卡类型"};
		for (int j=0;j<rowHeader.length;j++){
			sheet.setColumnWidth((short)j, 250 * (rowHeader[j].length()*2+2));
		}
		sheet.setColumnWidth((short)0, 250 * 20);
		sheet.setColumnWidth((short)1, 250 * 20);
		sheet.setColumnWidth((short)7, 250 * 20);
		sheet.setColumnWidth((short)12, 250 * 100);
		for (int i = 0; i < rowHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(rowHeader[i]);
			cell.setCellValue(text);
		}
		int rownum = 1;
		//根据日期查询报表内容集合
		List<QualityChecking> qualityCheckingList = qualityCheckingService.queryQualityCheckingListByDate(paramMap);
		if(qualityCheckingList!=null && qualityCheckingList.size()>0){
			for (QualityChecking qualityChecking : qualityCheckingList) {
				row = sheet.createRow(rownum++);
				String appId = qualityChecking.getAppId();
				if(appId==null||"".equals(appId)){
					continue;
				}
				//根据appId查询对应的申请人(被检查人)和其所在岗位
//				Map<String,String> perMap = qualityCheckingService.queryPerInspected(appId);
				//检查日期设置
				Date lstDate = qualityChecking.getLstDate();
				String date = "";
				if(lstDate!=null){
					 date = new SimpleDateFormat("yyyy-MM-dd").format(lstDate);
				}
				//被检查人-录入、被检查人-征信、被检查人-审批、被检查人征审合一以及操作员逻辑
				String userRecord = qualityChecking.getUserRecord();
				if(null!=userRecord){
					if(userRecord.contains(";")){
						String[] split = userRecord.split(";",-1);
						if(!(split[0]==null&&split[1]==null&&split[2]==null&&split[3]==null)){
							//表格第三列审查员split[0]  表格第四列征信员split[1] 表格第五列审批员split[2] 表格第六列政审合一员 split[3]
							row.createCell(2).setCellValue(split[0]);
							row.createCell(3).setCellValue(split[1]);
							row.createCell(4).setCellValue(split[2]);
							row.createCell(5).setCellValue(split[3]);
						}
					}
				}
				//查询分件表的历史表申请件每个环节的最后操作人
				String currNode = "01";
				Map<String,String> map = new HashMap<>();
				map.put("appId", appId);
				map.put("currNode", "01");				
				String  currOptUser01 = qualityCheckingService.queryCurrOptUserByNode(map);
				if (currOptUser01!=null && !currOptUser01.equals("")){
					currOptUser01 = apUserService.queryApUserByUserCode(currOptUser01).getUserName();
				}
				map.put("currNode", "02");		
				String  currOptUser02 = qualityCheckingService.queryCurrOptUserByNode(map);
				if (currOptUser02!=null && !currOptUser02.equals("")){
					currOptUser02 = apUserService.queryApUserByUserCode(currOptUser02).getUserName();
				}
				map.put("currNode", "03");	
				StringBuilder currOptUser03=new StringBuilder();
				List<String>  currOptUser03Temp = qualityCheckingService.queryListOptUserByNode(map);
				for (int k=0;k<currOptUser03Temp.size();k++){
					String tempName="";
					if (currOptUser03Temp.get(k)!=null && !currOptUser03Temp.get(k).equals("")){
						tempName = apUserService.queryApUserByUserCode(currOptUser03Temp.get(k)).getUserName();
						currOptUser03.append(tempName);
						if (k!=currOptUser03Temp.size()-1){
							currOptUser03.append(",");
						}
					}
				}
//				String currOptUser = "审查-" +currOptUser01 + ",征信-" + currOptUser02 +",审批-" + currOptUser03;
				row.createCell(9).setCellValue(currOptUser01);
				row.createCell(10).setCellValue(currOptUser02);
				row.createCell(11).setCellValue(currOptUser03.toString());
				//被检查人所在岗位设置
//				String dictCode = "GANGWEIZHIWU";	
//				Map<String, String> dictMap = getDictMap(dictCode);
//				String c1Title = qualityChecking.getCurrNode();
//				if(null!=c1Title){
//					switch(c1Title){
//						case "01":
//							c1Title = "审查员";
//							break;
//						case "02":
//							c1Title = "征信员";
//							break;
//						case "03":
//							c1Title = "审批员";
//						default:
//					}
//				}
//				String c1Name = "";
//				if(null != perMap){
//					c1Name = perMap.get("C1_CNAME");
//					String c1TitleKey = perMap.get("C1_TITLE");//被检查人的所在岗位
//					if(null!= c1TitleKey){
//						c1Title = dictMap.get(c1TitleKey);
//					}
//				}
				//检查情况设置
				String checkCondition = qualityChecking.getCheckCondition();
				if(null!=checkCondition){
					switch(checkCondition){
						case "1":
							checkCondition = "已查-系统";
							break;
						case "2":
							checkCondition = "已查-专项";
							break;
						case "3":
							checkCondition = "已查-自查";
							break;
						case "4":
							checkCondition = "已查-核销";
							break;
						default:
					}
				}
				//检查结果设置
				String checkResult = qualityChecking.getCheckResult();
				if(null!=checkResult){
					switch(checkResult){
						case "0":
							checkResult = "正常";
							break;
						case "1":
							checkResult = "问题-申请资料类";
							break;
						case "2":
							checkResult = "问题-流程类";
							break;
						case "3":
							checkResult = "问题-核实话术类";
							break;
						case "4":
							checkResult = "问题-注记类";
							break;
						case "5":
							checkResult = "问题-信息采集类";
							break;
						case "6":
							checkResult = "问题-审批评估类";
							break;
						case "7":
							checkResult = "问题-工单投诉类";
							break;
						case "8":
							checkResult = "问题-其他类";
							break;
						default:
					}
				}
				
				//被检查人----〉业务需求改为当前操作人
//				String currOptUser = qualityChecking.getCurrOptUser();
//				String currOptUserName = "";
//				if(currOptUser!=null){
//					ApUser apUser1 = apUserService.queryApUserByUserCode(currOptUser);
//					if(apUser1!=null){
//						currOptUserName = apUser1.getUserName();
//					}
//				}
				row.createCell(0).setCellValue(date);
				row.createCell(1).setCellValue(appId);
//				row.createCell(2).setCellValue(currOptUserName);
//				row.createCell(3).setCellValue(c1Title);
				row.createCell(6).setCellValue(checkCondition);
				row.createCell(7).setCellValue(checkResult);
				String crtUser = qualityChecking.getCrtUser();
				String userName = "";
				if(crtUser!=null){
					ApUser apUser = apUserService.queryApUserByUserCode(crtUser);
					if(apUser!=null){
						userName = apUser.getUserName();
					}
				}
				row.createCell(8).setCellValue(userName);
				row.createCell(12).setCellValue(qualityChecking.getMemo());
				//添加卡类型标识
				String ydjFlag = qualityChecking.getYdjFlag();
				if(null!=ydjFlag){
					switch(ydjFlag){
						case "1":
							ydjFlag = "易达金";
							break;
						case "2":
							ydjFlag = "标准卡";
							break;
						default:
					}
				}
				row.createCell(13).setCellValue(ydjFlag);
			}
		}

		
		//文件存到流中
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		os.flush();
		os.close();
		// 下载
		DownloadUtil downloadUtil = new DownloadUtil();
		String fileName = (String) paramMap.get("fileName");
		downloadUtil.download(os,request,response, fileName);
	}
	
	/**
	 * 根据大类编码获取岗位业务字典的map
	 * @param dictCode
	 * @return
	 * @throws CoreException 
	 */
	public Map<String,String> getDictMap(String dictCode) throws CoreException{
		Map<String, String> map = new HashMap<>();
//		ApDictService apDictService = (ApDictService)ac.getBean("apDictService");
//		ApDictDetailService apDictDetailService = (ApDictDetailService)ac.getBean("apDictDetailService");
		ApDict apDict = apDictService.queryApDictByDictCode(dictCode);
		List<ApDictDetail> list = apDictDetailService.queryDictDetailListByDictId(apDict.getDictId());
		for (ApDictDetail apDictDetail : list) {
			map.put(apDictDetail.getDictDetailCode(),apDictDetail.getDictDetailName());
		}
		return map;
	}
}
