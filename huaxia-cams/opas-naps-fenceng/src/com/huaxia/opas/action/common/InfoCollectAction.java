package com.huaxia.opas.action.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.audit.ApprovaInfoSupp;
import com.huaxia.opas.domain.audit.FicoSdBlaze;
import com.huaxia.opas.service.collect.InfoCollectService;
import com.huaxia.opas.util.SequenceUtil;
import com.huaxia.opas.util.TransBean2Map;

/**
 * 录入
 * 
 * @author xiebinxu 2017年3月3日
 */

public class InfoCollectAction implements Action {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(InfoCollectAction.class);

	@Resource(name = "infoCollectService")
	private InfoCollectService infoCollectService;

	public InfoCollectService getInfoCollectService() {
		return infoCollectService;
	}

	public void setInfoCollectService(InfoCollectService infoCollectService) {
		this.infoCollectService = infoCollectService;
	}

	/**
	 * @Title:queryInfoCollect
	 * @Description:返显 录入页面需要的数据的功能
	 * @param ctx
	 * @throws CoreException
	 * @author: xiebinxu
	 * @Date:2017年3月30日下午8:49:49
	 */
	public void queryInfoCollect(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		Map<String, String> dataBlaze = null;
		ApprovaInfoSupp approvaInfoSupp = null;
		Map<String, String> carMap = new HashMap<String, String>();
		try {
			dataBlaze = infoCollectService.queryEnteringPageNeedDataByAppId(map);
			//对录入页面“车产信息”中的‘车辆性质’进行设置值
			//通过key：‘carCategroy’获取value值，并判断此value值是否为'Y或者y'，是的话把‘carCategroy’键的值设置成'1',表示车辆性质为“非运营”
			if(dataBlaze != null && dataBlaze.get("carCategroy") != null){
				if("Y".equals(dataBlaze.get("carCategroy")) || 
				   "y".equals(dataBlaze.get("carCategroy"))){
					dataBlaze.put("carCategroy", "1");
				}else{
					dataBlaze.put("carCategroy", "");
				}
			}
			//公积金缴至月份gjjEndMonth 日期格式转换  yyyyMMdd 转换成 yyyy.MM.dd
			if(dataBlaze != null && dataBlaze.get("gjjEndMonth") != null){
				String date = dataBlaze.get("gjjEndMonth");
				//String ss = "2016-10-24";
				//String gjjEndMonth = "20200525";//公积金缴至月份在blzae表中的日期形式：如：20200525
				SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat s1 = new SimpleDateFormat("yyyyMM");
				Date d = null;
				try {
					if(date.length()==8){
						d = s.parse(date);//将字符串日期转换成对应的日期格式 yyyyMMdd
						date = new SimpleDateFormat("yyyy.MM.dd").format(d);//将yyyyMMdd日期格式转换成yyyy.MM.dd字符串类型
					}else if(date.replace(".", "").length()==6 && date.length()==6){//防止2016.5这种格式的日期进行解析
						d = s1.parse(date);  //为了解析202005 这种格式的日期
						date = new SimpleDateFormat("yyyy.MM").format(d);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				dataBlaze.put("gjjEndMonth", date);
			}
			//决策表中公积金缴交状态为 01,02,03，需要转化成对应的 1,2,3（业务字典表中公积金缴交状态为 1,2,3）
			if(dataBlaze != null && dataBlaze.get("gjjPayStatus") != null){
				String gjjPayStatus = dataBlaze.get("gjjPayStatus");
				switch (gjjPayStatus) {
				case "01":	//厦门
				case "正常": //温州
				case "11":	//银川
					dataBlaze.put("gjjPayStatus", "1");
					break;
				case "02":
					dataBlaze.put("gjjPayStatus", "2");
					break;
				case "03":
					dataBlaze.put("gjjPayStatus", "3");
					break;
				default:
					break;
				}
			}
			//录入页面公积金信息栏位“个人缴存比例”(gjjPerPayrate)有正整数校验，有的数据源（如银川数据源）是小数形式，如0.12，有的是整数形式，如12，
			//需要把小数的变换成整数 
			String perPayrate;
			if(dataBlaze != null && dataBlaze.get("gjjPerPayrate") != null){
				perPayrate = String.valueOf(dataBlaze.get("gjjPerPayrate"));//个人缴存比例
				if(perPayrate.contains(".")){
					double d = Double.parseDouble(perPayrate);
					if(d<1){
						int gjjPerPayrate1 = (int)(d*100);
						perPayrate = String.valueOf(gjjPerPayrate1);
					}else{
						int gjjPerPayrate1 = (int)d;
						perPayrate = String.valueOf(gjjPerPayrate1);
					}
				}
				dataBlaze.put("gjjPerPayrate", perPayrate);
			}
			approvaInfoSupp = infoCollectService.queryApprovaInfoSupp(map);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		if (dataBlaze != null) {
			ctx.setData("jsonapprovaInfoSupp",
					JSON.toJSONStringWithDateFormat(dataBlaze, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat));
		} else {
			ctx.setData("jsonapprovaInfoSupp", "");
		}
		if (approvaInfoSupp != null) {
			ctx.setData("approvaInfoSupp", JSON.toJSONStringWithDateFormat(approvaInfoSupp, "yyyy-MM-dd",
					SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteMapNullValue));
		} else {
			ctx.setData("approvaInfoSupp", "");
		}
	}

	/**
	 * 
	 * @Title:addInfoCollect
	 * @Description:返显 录入页面 保存数据的功能
	 * @param ctx
	 * @throws CoreException
	 * @author: xiebinxu
	 * @throws ParseException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @Date:2017年3月30日下午8:53:13
	 */
	public void addInfoCollect(Context ctx)
			throws CoreException, ParseException, NoSuchFieldException, SecurityException {
		Map<String, Object> map = ctx.getDataMap();
		String userCode = (String) map.get("userCode");
		ApprovaInfoSupp approvaInfoSupp = new ApprovaInfoSupp();
		String houseCost1 = (String) map.get("houseCost");
		String houseArea1 = (String) map.get("houseArea");
		String carCost1 = (String) map.get("carCost");
		String perPayWage1 = (String) map.get("perPayWage");
		String perAssetBalance1 = (String) map.get("perAssetBalance");
		String perFinAssetSum1 = (String) map.get("perFinAssetSum");
		String perLoanpri1 = (String) map.get("perLoanpri");
		String pubCreditlimit1 = (String) map.get("pubCreditlimit");
		String pubSavebalance1 = (String) map.get("pubSavebalance");
		String pubSaveDaybalance1 = (String) map.get("pubSaveDaybalance");
		String sbMonthPayment1 = (String) map.get("sbMonthPayment");
		String sbMonthPaybase1 = (String) map.get("sbMonthPaybase");
		String gjjMonthPayment1 = (String) map.get("gjjMonthPayment");
		String carCategroy1 = (String) map.get("carCategroy");
		String salaryCategroy1 = (String) map.get("salaryCategroy");
		String yearSalary1 = (String) map.get("yearSalary");
		String balanceCategroy1 = (String) map.get("balanceCategroy");
		String balanceAcount1 = (String) map.get("balanceAcount");
		String individual1 = (String) map.get("individual");
		String individualMonth1 = (String) map.get("individualMonth");
		
		String specialProject1 = (String) map.get("specialProject");
		String projectCategroy1 = (String) map.get("projectCategroy");
		String projectAcount1 = (String) map.get("projectAcount");
		String projectMemo1 = (String) map.get("projectMemo");
		String commitFlag = (String) map.get("commitFlag");
		BigDecimal houseCost = null;
		BigDecimal houseArea = null;
		BigDecimal carCost = null;
		BigDecimal perPayWage = null;
		BigDecimal perAssetBalance = null;
		BigDecimal perFinAssetSum = null;
		BigDecimal perLoanpri = null;
		BigDecimal pubCreditlimit = null;
		BigDecimal pubSavebalance = null;
		BigDecimal pubSaveDaybalance = null;
		BigDecimal sbMonthPayment = null;
		BigDecimal sbMonthPaybase = null;
		BigDecimal gjjMonthPayment = null;

		if (!"".equals(houseCost1) && houseCost1 != null) {
			houseCost = new BigDecimal(houseCost1);
			approvaInfoSupp.setHouseCost(houseCost);
		}
		if (!"".equals(houseArea1) && houseArea1 != null) {
			houseArea = new BigDecimal(houseArea1);
			approvaInfoSupp.setHouseArea(houseArea);
		}
		if (!"".equals(carCost1) && carCost1 != null) {
			approvaInfoSupp.setCarCost(carCost1);
		}
		if (!"".equals(perPayWage1) && perPayWage1 != null) {
			perPayWage = new BigDecimal(perPayWage1);
			approvaInfoSupp.setPerPayWage(perPayWage);
		}
		if (!"".equals(perAssetBalance1) && perAssetBalance1 != null) {
			perAssetBalance = new BigDecimal(perAssetBalance1);
			approvaInfoSupp.setPerAssetBalance(perAssetBalance);
		}
		if (!"".equals(perFinAssetSum1) && perFinAssetSum1 != null) {
			perFinAssetSum = new BigDecimal(perFinAssetSum1);
			approvaInfoSupp.setPerFinAssetSum(perFinAssetSum);
		}
		if (!"".equals(perLoanpri1) && perLoanpri1 != null) {
			perLoanpri = new BigDecimal(perLoanpri1);
			approvaInfoSupp.setPerLoanpri(perLoanpri);
		}
		if (!"".equals(pubCreditlimit1) && pubCreditlimit1 != null) {
			pubCreditlimit = new BigDecimal(pubCreditlimit1);
			approvaInfoSupp.setPubCreditlimit(pubCreditlimit);
		}
		if (!"".equals(pubSavebalance1) && pubSavebalance1 != null) {
			pubSavebalance = new BigDecimal(pubSavebalance1);
			approvaInfoSupp.setPubSavebalance(pubSavebalance);
		}
		if (!"".equals(pubSaveDaybalance1) && pubSaveDaybalance1 != null) {
			pubSaveDaybalance = new BigDecimal(pubSaveDaybalance1);
			approvaInfoSupp.setPubSaveDaybalance(pubSaveDaybalance);
		}
		if (!"".equals(sbMonthPayment1) && sbMonthPayment1 != null) {
			sbMonthPayment = new BigDecimal(sbMonthPayment1);
			approvaInfoSupp.setSbMonthPayment(sbMonthPayment);
		}
		if (!"".equals(sbMonthPaybase1) && sbMonthPaybase1 != null) {
			sbMonthPaybase = new BigDecimal(sbMonthPaybase1);
			approvaInfoSupp.setSbMonthPaybase(sbMonthPaybase);
		}
		if (!"".equals(gjjMonthPayment1) && gjjMonthPayment1 != null) {
			gjjMonthPayment = new BigDecimal(gjjMonthPayment1);
			approvaInfoSupp.setGjjMonthPayment(gjjMonthPayment);
		}
		if (!"".equals(carCategroy1) && carCategroy1 != null) {
			approvaInfoSupp.setCarCategroy(carCategroy1);
		}
		if (!"".equals(salaryCategroy1) && salaryCategroy1 != null) {
			approvaInfoSupp.setSalaryCategroy(salaryCategroy1);
		}
		if (!"".equals(yearSalary1) && yearSalary1 != null) {
			try {
				Double.parseDouble(yearSalary1);
				approvaInfoSupp.setYearSalary(yearSalary1);
			} catch (NumberFormatException e1) {
				//ctx.setData("isSuccess", false);
				//ctx.setData("exMsg","年收入为非数字格式");
				e1.printStackTrace();
				System.out.print("年收入为非数字格式");
				return;
			}
		}
		if (!"".equals(balanceCategroy1) && balanceCategroy1 != null) {
			approvaInfoSupp.setBalanceCategroy(balanceCategroy1);
		}
		if (!"".equals(balanceAcount1) && balanceAcount1 != null) {
			try {
				Double.parseDouble(balanceAcount1);
				approvaInfoSupp.setBalanceAcount(balanceAcount1);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				System.out.print("资产金额为非数字格式");
				return;
			}
		}
		if (!"".equals(individual1) && individual1 != null) {
			approvaInfoSupp.setIndividual(individual1);
		}
	
		
		
		if (!"".equals(specialProject1) && specialProject1 != null) {
			approvaInfoSupp.setSpecialProject(specialProject1);
		}
		if (!"".equals(projectCategroy1) && projectCategroy1 != null) {
			approvaInfoSupp.setProjectCategroy(projectCategroy1);
		}
		if (!"".equals(projectAcount1) && projectAcount1 != null) {
			try {
				Double.parseDouble(projectAcount1);
				approvaInfoSupp.setProjectAcount(projectAcount1);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				System.out.print("项目金额为非数字格式");
				return;
			}
		}
		if (!"".equals(projectMemo1) && projectMemo1 != null) {
			approvaInfoSupp.setProjectMemo(projectMemo1);
		}
		String carOld = map.get("carOld") != null ? map.get("carOld").toString() : "";
		if (!"".equals(carOld)) {
			approvaInfoSupp.setCarOld(carOld);
		}
		String gjjPayDate1 = (String) map.get("gjjPayDate");
		String gjjEndMonth1 = (String) map.get("gjjEndMonth");
		String registerDate1 = (String) map.get("registerDate");
		String issueDate1 = (String) map.get("issueDate");
		String pubOpendate1 = (String) map.get("pubOpendate");
		String perOpendate1 = (String) map.get("perOpendate");
		String sbPayDate1 = (String) map.get("sbPayDate");
		String appId = (String) map.get("appId");
		String salarySignDate1 = (String) map.get("salarySignDate");
		String salarySignLatestDate1 = (String) map.get("salarySignLatestDate");

		Date gjjPayDate = null;
		Date gjjEndMonth = null;
		Date registerDate = null;
		Date issueDate = null;
		Date pubOpendate = null;
		Date perOpendate = null;
		Date sbPayDate = null;
		Date salarySignDate = null;
		Date salarySignLatestDate = null;
		Date individualMonth = null;

		if (gjjPayDate1 != null && !"".equals(gjjPayDate1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				gjjPayDate = sdf.parse(gjjPayDate1);
				approvaInfoSupp.setGjjPayDate(gjjPayDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		if (gjjEndMonth1 != null && !"".equals(gjjEndMonth1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
			try {
				gjjEndMonth = sdf.parse(gjjEndMonth1);
				approvaInfoSupp.setGjjEndMonth(gjjEndMonth);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.println("日期类型转换错误");
				return;
			}
		}
		if (registerDate1 != null && !"".equals(registerDate1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				registerDate = sdf.parse(registerDate1);
				approvaInfoSupp.setRegisterDate(registerDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		if (issueDate1 != null && !"".equals(issueDate1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				issueDate = sdf.parse(issueDate1);
				approvaInfoSupp.setIssueDate(issueDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		if (pubOpendate1 != null && !"".equals(pubOpendate1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				pubOpendate = sdf.parse(pubOpendate1);
				approvaInfoSupp.setPubOpendate(pubOpendate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		if (perOpendate1 != null && !"".equals(perOpendate1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				perOpendate = sdf.parse(perOpendate1);
				approvaInfoSupp.setPerOpendate(perOpendate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		if (sbPayDate1 != null && !"".equals(sbPayDate1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sbPayDate = sdf.parse(sbPayDate1);
				approvaInfoSupp.setSbPayDate(sbPayDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		if (salarySignDate1 != null && !"".equals(salarySignDate1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				salarySignDate = sdf.parse(salarySignDate1);
				approvaInfoSupp.setSalarySignDate(salarySignDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		if (salarySignLatestDate1 != null && !"".equals(salarySignLatestDate1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				salarySignLatestDate = sdf.parse(salarySignLatestDate1);
				approvaInfoSupp.setSalarySignLatestDate(salarySignLatestDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("开始日期类型转换错误");
				return;
			}
		}
		if (individualMonth1 != null && !"".equals(individualMonth1)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				individualMonth = sdf.parse(individualMonth1);
				approvaInfoSupp.setIndividualMonth(individualMonth);
			} catch (ParseException e1) {
				e1.printStackTrace();
				System.out.print("个体成立日期类型转换错误");
				return;
			}
		}
		
		
		String refreeLimit = map.get("refreeLimit") != null ? map.get("refreeLimit").toString() : "";
		if (!"".equals(refreeLimit)) {
			approvaInfoSupp.setRefreeLimit(Long.parseLong(refreeLimit));
		}
		String refreeLimit2 = map.get("refreeLimit2") != null ? map.get("refreeLimit2").toString() : "";
		if (!"".equals(refreeLimit2)) {
			approvaInfoSupp.setRefreeLimit2(Long.parseLong(refreeLimit2));
		}
		String refreeLimit3 = map.get("refreeLimit3") != null ? map.get("refreeLimit3").toString() : "";
		if (!"".equals(refreeLimit3)) {
			approvaInfoSupp.setRefreeLimit3(Long.parseLong(refreeLimit3));
		}
		String sbCurrCompanyMonth = map.get("sbCurrCompanyMonth") != null ? map.get("sbCurrCompanyMonth").toString()
				: "";
		if (!"".equals(sbCurrCompanyMonth)) {
			approvaInfoSupp.setSbCurrCompanyMonth(Long.parseLong(sbCurrCompanyMonth));
		}
		String gjjPerPayrate = map.get("gjjPerPayrate") != null ? map.get("gjjPerPayrate").toString() : "";
		if (!"".equals(gjjPerPayrate)) {
			approvaInfoSupp.setGjjPerPayrate(Short.parseShort(gjjPerPayrate));
		}
		String industryCode = map.get("industryCode") != null ? map.get("industryCode").toString() : "";
		approvaInfoSupp.setIndustryCode(industryCode);
		String jobCode = map.get("jobCode") != null ? map.get("jobCode").toString() : "";
		approvaInfoSupp.setJobCode(jobCode);
		String houseInfoFrom = map.get("houseInfoFrom") != null ? map.get("houseInfoFrom").toString() : "";
		approvaInfoSupp.setHouseInfoFrom(houseInfoFrom);
		String carHand = map.get("carHand") != null ? map.get("carHand").toString() : "";
		approvaInfoSupp.setCarHand(carHand);
		String perLoanstatus = map.get("perLoanstatus") != null ? map.get("perLoanstatus").toString() : "";
		approvaInfoSupp.setPerLoanstatus(perLoanstatus);
		String pubLoanstatus = map.get("pubLoanstatus") != null ? map.get("pubLoanstatus").toString() : "";
		approvaInfoSupp.setPubLoanstatus(pubLoanstatus);
		String hxCardcentreStaff = map.get("hxCardcentreStaff") != null ? map.get("hxCardcentreStaff").toString() : "";
		approvaInfoSupp.setHxCardcentreStaff(hxCardcentreStaff);
		String hxCardcentreStaffLevel = map.get("hxCardcentreStaffLevel") != null
				? map.get("hxCardcentreStaffLevel").toString() : "";
		approvaInfoSupp.setHxCardcentreStaffLevel(hxCardcentreStaffLevel);
		String hxBankStaff = map.get("hxBankStaff") != null ? map.get("hxBankStaff").toString() : "";
		approvaInfoSupp.setHxBankStaff(hxBankStaff);
		String hxBankStaffLevel = map.get("hxBankStaffLevel") != null ? map.get("hxBankStaffLevel").toString() : "";
		approvaInfoSupp.setHxBankStaffLevel(hxBankStaffLevel);
		String refree = map.get("refree") != null ? map.get("refree").toString() : "";
		approvaInfoSupp.setRefree(refree);
		String refree2 = map.get("refree2") != null ? map.get("refree2").toString() : "";
		approvaInfoSupp.setRefree2(refree2);
		String refree3 = map.get("refree3") != null ? map.get("refree3").toString() : "";
		approvaInfoSupp.setRefree3(refree3);
		String sbCurrPayStatus = map.get("sbCurrPayStatus") != null ? map.get("sbCurrPayStatus").toString() : "";
		approvaInfoSupp.setSbCurrPayStatus(sbCurrPayStatus);
//		String sbCurrPaycompany = map.get("sbCurrPaycompany") != null ? map.get("sbCurrPaycompany").toString() : "";
//		approvaInfoSupp.setSbCurrPaycompany(sbCurrPaycompany);
		String thirdPartPay = map.get("thirdPartPay") != null ? map.get("thirdPartPay").toString() : "";
		approvaInfoSupp.setThirdPartPay(thirdPartPay);
		String gjjPayStatus = map.get("gjjPayStatus") != null ? map.get("gjjPayStatus").toString() : "";
		approvaInfoSupp.setGjjPayStatus(gjjPayStatus);
		// String
		// gjjEndMonth=map.get("gjjEndMonth")!=null?map.get("gjjEndMonth").toString():"";
		// approvaInfoSupp.setGjjEndMonth(gjjEndMonth);
		String eduModel = map.get("eduModel") != null ? map.get("eduModel").toString() : "";
		approvaInfoSupp.setEduModel(eduModel);
		String eduType = map.get("eduType") != null ? map.get("eduType").toString() : "";
		approvaInfoSupp.setEduType(eduType);
		String highCust = map.get("highCust") != null ? map.get("highCust").toString() : "";
		approvaInfoSupp.setHighCust(highCust);
		String eliteEarncust = map.get("eliteEarncust") != null ? map.get("eliteEarncust").toString() : "";
		approvaInfoSupp.setEliteEarncust(eliteEarncust);
		String youngHigheducust = map.get("youngHigheducust") != null ? map.get("youngHigheducust").toString() : "";
		approvaInfoSupp.setYoungHigheducust(youngHigheducust);
		String normalearncust = map.get("normalearncust") != null ? map.get("normalearncust").toString() : "";
		approvaInfoSupp.setNormalearncust(normalearncust);
		String prescreenCust = map.get("prescreenCust") != null ? map.get("prescreenCust").toString() : "";
		approvaInfoSupp.setPrescreenCust(prescreenCust);
		String majorProject = map.get("majorProject") != null ? map.get("majorProject").toString() : "";
		approvaInfoSupp.setMajorProject(majorProject);
		String otherCust = map.get("otherCust") != null ? map.get("otherCust").toString() : "";
		approvaInfoSupp.setOtherCust(otherCust);
		String houseInfo = map.get("houseInfo") != null ? map.get("houseInfo").toString() : "";
		approvaInfoSupp.setHouseInfo(houseInfo);
		String drivingLicense = map.get("drivingLicense") != null ? map.get("drivingLicense").toString() : "";
		approvaInfoSupp.setDrivingLicense(drivingLicense);
		String crossSalecust = map.get("crossSalecust") != null ? map.get("crossSalecust").toString() : "";
		approvaInfoSupp.setCrossSalecust(crossSalecust);
		String bankStaff = map.get("bankStaff") != null ? map.get("bankStaff").toString() : "";
		approvaInfoSupp.setBankStaff(bankStaff);
		String vipCust = map.get("vipCust") != null ? map.get("vipCust").toString() : "";
		approvaInfoSupp.setVipCust(vipCust);
		String sbCust = map.get("sbCust") != null ? map.get("sbCust").toString() : "";
		approvaInfoSupp.setSbCust(sbCust);
		String gjjCust = map.get("gjjCust") != null ? map.get("gjjCust").toString() : "";
		approvaInfoSupp.setGjjCust(gjjCust);
		approvaInfoSupp.setAppId(appId);

		Map<String, String> querymap = new HashMap<String, String>();
		querymap.put("appId", approvaInfoSupp.getAppId());
		// 基本信息补充表信息
		ApprovaInfoSupp approvaInfoSupp2 = infoCollectService.queryApprovaInfoSupp(querymap);
		// 标准卡BLAZE返回表信息
		FicoSdBlaze blazeMap1 = infoCollectService.queryEnteringPageNeedDataByAppId1(map);
		Map<String, Object> newMap = TransBean2Map.transBean2Map(approvaInfoSupp);
		Map<String, Object> beanMap = TransBean2Map.transBean2Map(approvaInfoSupp2);
		Map<String, Object> blazeMap = TransBean2Map.transBean2Map(blazeMap1);
		//为了防止blaze决策表中的数据和转变后的数据不一致而记录信息修改记录
		if(blazeMap != null && null != blazeMap.get("carCategroy")){
			if("Y".equals(blazeMap.get("carCategroy").toString()) || 
			   "y".equals(blazeMap.get("carCategroy").toString())){
				blazeMap.put("carCategroy", "1");
			}else{
				blazeMap.put("carCategroy", "");
			}
		}
		//为了防止blaze决策表中的数据和转变后的数据不一致而记录信息修改记录
		//公积金缴至月份gjjEndMonth 日期格式转换  yyyyMMdd 转换成 yyyy.MM.dd
		if(blazeMap != null && blazeMap.get("gjjEndMonth") != null){
			String date = (String) blazeMap.get("gjjEndMonth");
			//String ss = "2016-10-24";
			//String gjjEndMonth = "20200525";//公积金缴至月份在blzae表中的日期形式：如：20200525
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat s1 = new SimpleDateFormat("yyyyMM");
			Date d = null;
			try {
				if(date.length()==8){
					d = s.parse(date);//将字符串日期转换成对应的日期格式 yyyyMMdd
					date = new SimpleDateFormat("yyyy.MM.dd").format(d);//将yyyyMMdd日期格式转换成yyyy.MM.dd字符串类型
				}else if(date.replace(".", "").length()==6 && date.length()==6){
					d = s1.parse(date);
					date = new SimpleDateFormat("yyyy.MM").format(d);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//2020.05.20 2020.05 2020.5
			if(date.length()>7){
				date = date.substring(0, 7);
			}
			blazeMap.put("gjjEndMonth", date);
		}
		//为了防止blaze决策表中的数据和转变后的数据不一致而记录信息修改记录
		//决策表中公积金缴交状态为 01,02,03，需要转化成对应的 1,2,3（业务字典表中公积金缴交状态为 1,2,3）
		if(blazeMap != null && blazeMap.get("gjjPayStatus") != null){
			String gjjPayStatus1 = (String) blazeMap.get("gjjPayStatus");
			switch (gjjPayStatus1) {
			case "01":
			case "正常":
			case "11":
				blazeMap.put("gjjPayStatus", "1");
				break;
			case "02":
				blazeMap.put("gjjPayStatus", "2");
				break;
			case "03":
				blazeMap.put("gjjPayStatus", "3");
				break;
			default:
				break;
			}
		}
		//为了防止blaze决策表中的数据和转变后的数据不一致而记录信息修改记录
		//录入页面公积金信息栏位“个人缴存比例”(gjjPerPayrate)有正整数校验，有的数据源（如银川数据源）是小数形式，如0.12，有的是整数形式，如12，
		//需要把小数的变换成整数 
		String perPayrate;
		if(blazeMap != null && blazeMap.get("gjjPerPayrate") != null){
			perPayrate = String.valueOf(blazeMap.get("gjjPerPayrate"));//个人缴存比例
			if(perPayrate.contains(".")){
				double d = Double.parseDouble(perPayrate);
				if(d<1){//银川数据源中个人缴存比例是小数形式，如 0.12，需转换成整数
					int gjjPerPayrate1 = (int)(d*100);
					perPayrate = String.valueOf(gjjPerPayrate1);
				}else{
					int gjjPerPayrate1 = (int)d;
					perPayrate = String.valueOf(gjjPerPayrate1);
				}
			}
			blazeMap.put("gjjPerPayrate", perPayrate);
		}
		
		if (beanMap != null && approvaInfoSupp2 != null) {
			Set<Entry<String, Object>> beanSet = beanMap.entrySet();
			for (Entry<String, Object> entry : beanSet) {
				String key = entry.getKey();
				Field[] fields = approvaInfoSupp2.getClass().getDeclaredFields();
				for (Field field : fields) {
					if (field.getName().equals(key)) {
						String type = field.getGenericType().toString();
						if ("class java.math.BigDecimal".equals(type) && null != (entry.getValue())) {
							BigDecimal scale = new BigDecimal(String.valueOf(entry.getValue())).setScale(2);
							beanMap.put(key, scale);
						}
					}
				}
			}
		}

		newMap.remove("appId");
		Map<String, Object> applyModifyLogMap = getApplyModifyLogMap(newMap, beanMap);
		Map<String, Object> applyModifyLogMap1 = getApplyModifyLogMap1(newMap, blazeMap);

		List<ApplyModifyLog> applyModifyLogList = new ArrayList<>();
		Date date = new Date();

		if ("1".equals(commitFlag)) {
			if (null != applyModifyLogMap1 && !applyModifyLogMap1.isEmpty()) {
				Set<Entry<String, Object>> entrySet1 = applyModifyLogMap1.entrySet();
				for (Entry<String, Object> entry : entrySet1) {
					String key = entry.getKey();
					Object value = entry.getValue();
					ApplyModifyLog applyModifyLog = null;
					applyModifyLog = new ApplyModifyLog();
					applyModifyLog.setAutoId(SequenceUtil.getUUID());
					applyModifyLog.setFieldName(key);
					if (String.valueOf(value).contains("CST")) {
						if ("gjjEndMonth".equals(key)) {
							applyModifyLog.setFieldNewValue(new SimpleDateFormat("yyyy.MM").format(value));
						} else {
							applyModifyLog.setFieldNewValue(new SimpleDateFormat("yyyy-MM-dd").format(value));
						}
					} else {
						applyModifyLog.setFieldNewValue(String.valueOf(value));
					}
					
					if("industryCode".equals(key) && blazeMap != null){
						applyModifyLog.setFieldOldValue(String.valueOf(blazeMap.get(key)));
					}else if ("jobCode".equals(key) && blazeMap != null) {
						applyModifyLog.setFieldOldValue(String.valueOf(blazeMap.get(key)));
					}else {
						applyModifyLog.setFieldOldValue("");
					}
				
					if (applyModifyLog != null) {
						// 根据appId去查主卡申请人姓、证件类型、证件号码
						Map<String, String> c1Map = infoCollectService.selectNameAndCardMap(appId);
						String c1Name = "";
						String c1IdType = "";
						String c1Idnbr = "";
						if (c1Map != null) {
							c1Name = c1Map.get("C1_CNAME");
							c1IdType = c1Map.get("C1_IDTYPE");
							c1Idnbr = c1Map.get("C1_IDNBR");
						}
						applyModifyLog.setFlag("2");
						applyModifyLog.setAppId(appId);
						applyModifyLog.setApplyName(c1Name);
						applyModifyLog.setCretifiType(c1IdType);
						applyModifyLog.setCretifiId(c1Idnbr);
						applyModifyLog.setCheckFlag("0");
						applyModifyLog.setIsKeyField("0");
						applyModifyLog.setCrtDate(date);
						applyModifyLog.setLstUpdDate(date);
						applyModifyLog.setCrtUser(userCode);
						applyModifyLog.setLstUpdUser(userCode);
						applyModifyLogList.add(applyModifyLog);
					}
				}
			}
		} else {
			if (null != applyModifyLogMap && !applyModifyLogMap.isEmpty()) {
				Set<Entry<String, Object>> entrySet = applyModifyLogMap.entrySet();
				// List<ApplyModifyLog> applyModifyLogList = new ArrayList<>();
				// Date date = new Date();
				for (Entry<String, Object> entry : entrySet) {
					String key = entry.getKey();
					Object value = entry.getValue();
					ApplyModifyLog applyModifyLog = null;
					if (null == beanMap || beanMap.isEmpty()) {// 表示blaze返回没值
						if (value != null && !"".equals(value) && ((blazeMap != null && blazeMap.get(key) == null)
								|| (blazeMap != null && "".equals(blazeMap.get(key))))) {
							applyModifyLog = new ApplyModifyLog();
							applyModifyLog.setAutoId(SequenceUtil.getUUID());
							applyModifyLog.setFieldName(key);
							if (String.valueOf(value).contains("CST")) {
								if ("gjjEndMonth".equals(key)) {
									applyModifyLog.setFieldNewValue(new SimpleDateFormat("yyyy.MM").format(value));
								} else {
									applyModifyLog.setFieldNewValue(new SimpleDateFormat("yyyy-MM-dd").format(value));
								}
							} else {
								applyModifyLog.setFieldNewValue(String.valueOf(value));
							}
							applyModifyLog.setFieldOldValue("");
						}
					} else {
						applyModifyLog = new ApplyModifyLog();
						applyModifyLog.setAutoId(SequenceUtil.getUUID());
						applyModifyLog.setFieldName(key);
						if (String.valueOf(beanMap.get(key)).contains("CST")) {
							if ("gjjEndMonth".equals(key)) {
								applyModifyLog
										.setFieldOldValue(new SimpleDateFormat("yyyy.MM").format(beanMap.get(key)));
							} else {
								applyModifyLog
										.setFieldOldValue(new SimpleDateFormat("yyyy-MM-dd").format(beanMap.get(key)));
							}
							if (value != null && !"".equals(value)) {
								if ("gjjEndMonth".equals(key)) {
									applyModifyLog.setFieldNewValue(new SimpleDateFormat("yyyy.MM").format(value));
								} else {
									applyModifyLog.setFieldNewValue(new SimpleDateFormat("yyyy-MM-dd").format(value));
								}
							} else {
								applyModifyLog.setFieldNewValue("");
							}
						} else {
							if (value != null && !"".equals(value) && String.valueOf(value).contains("CST")) {
								if ("gjjEndMonth".equals(key)) {
									applyModifyLog.setFieldNewValue(new SimpleDateFormat("yyyy.MM").format(value));
								} else {
									applyModifyLog.setFieldNewValue(new SimpleDateFormat("yyyy-MM-dd").format(value));
								}
							} else {
								applyModifyLog.setFieldNewValue(String.valueOf(value));
							}
							applyModifyLog.setFieldOldValue(String.valueOf(beanMap.get(key)));

						}
					}
					if (applyModifyLog != null) {
						// 根据appId去查主卡申请人姓、证件类型、证件号码
						Map<String, String> c1Map = infoCollectService.selectNameAndCardMap(appId);
						String c1Name = "";
						String c1IdType = "";
						String c1Idnbr = "";
						if (c1Map != null) {
							c1Name = c1Map.get("C1_CNAME");
							c1IdType = c1Map.get("C1_IDTYPE");
							c1Idnbr = c1Map.get("C1_IDNBR");
						}
						applyModifyLog.setFlag("2");
						applyModifyLog.setAppId(appId);
						applyModifyLog.setApplyName(c1Name);
						applyModifyLog.setCretifiType(c1IdType);
						applyModifyLog.setCretifiId(c1Idnbr);
						applyModifyLog.setCheckFlag("0");
						applyModifyLog.setIsKeyField("0");
						applyModifyLog.setCrtDate(date);
						applyModifyLog.setLstUpdDate(date);
						applyModifyLog.setCrtUser(userCode);
						applyModifyLog.setLstUpdUser(userCode);
						applyModifyLogList.add(applyModifyLog);
					}
					continue;
				}
				/*
				 * String currNode = (String) map.get("currNode"); if
				 * (("1".equals(commitFlag) || "02".equals(currNode)) && null !=
				 * applyModifyLogList && !applyModifyLogList.isEmpty()) {
				 * infoCollectService.insertApplyModifyLogList(
				 * applyModifyLogList); }
				 */
			}
		}

		if ("1".equals(commitFlag)) {
			int count = infoCollectService.queryCountByIndustryCode(industryCode);
			if (count > 0) {
				if (null != applyModifyLogList && !applyModifyLogList.isEmpty()) {
					infoCollectService.insertApplyModifyLogList(applyModifyLogList);
					ctx.setData("industryCodeFlag", true);
				}
			}
		} else {
			String currNode = (String) map.get("currNode");
			if ("02".equals(currNode) && null != applyModifyLogList && !applyModifyLogList.isEmpty()) {
				infoCollectService.insertApplyModifyLogList(applyModifyLogList);
			}
		}

		try {
			if (approvaInfoSupp2 == null) {
				infoCollectService.insertApprovaInfoSupp(approvaInfoSupp);
			} else {
				infoCollectService.updateApprovaInfoSupp(approvaInfoSupp);
			}
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	public Map<String, Object> getApplyModifyLogMap(Map<String, Object> newMap, Map<String, Object> beanMap) {
		Set<Entry<String, Object>> entrySet = newMap.entrySet();
		Map<String, Object> applyModifyLogMap = new HashMap<>();
		if (null != beanMap && !beanMap.isEmpty()) {
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object value = entry.getValue();
				Object beanValue = beanMap.get(key);
				if ((value != null && !"".equals(value)) && (beanValue != null && !"".equals(beanValue))
						&& value.equals(beanValue)) {
					continue;
				} else if ((value == null || "".equals(value)) && (beanValue == null || "".equals(beanValue))) {
					continue;
				}
				applyModifyLogMap.put(key, value);
			}
			return applyModifyLogMap;
		} else {
			return newMap;
		}
	}

	public Map<String, Object> getApplyModifyLogMap1(Map<String, Object> newMap, Map<String, Object> blazeMap) {
		Set<Entry<String, Object>> entrySet = newMap.entrySet();
		Map<String, Object> applyModifyLogMap = new HashMap<>();
		if (null != blazeMap && !blazeMap.isEmpty()) {
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object value = entry.getValue();
				Object blazeValue = blazeMap.get(key);
				
				if(key.equals("gjjPerPayrate") && value != null && value != ""){
					BigDecimal	value1 = new BigDecimal((short)value);
					value = value1;
				}
				if(key.equals("sbCurrCompanyMonth") && value != null && value != ""){
					BigDecimal	value1 = new BigDecimal((long) value);
					value = value1;
				}
				if(blazeValue instanceof Date){
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					try {
						blazeValue = f.parse(f.format(blazeValue));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(blazeValue != null && blazeValue != "" && key.equals("gjjEndMonth")){
					String blazeValue1 = blazeValue.toString();
					if(blazeValue1.length() == 6){
						String[] str = blazeValue1.split("\\.");
						if(str.length>1){
							blazeValue = str[0] + ".0" + str[1];
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
						try {
							blazeValue = sdf.parse((String)blazeValue);
						} catch (ParseException e1) {
							e1.printStackTrace();
							System.out.println("日期类型转换错误");
						}
					}else{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
						try {
							blazeValue = sdf.parse((String)blazeValue);
						} catch (ParseException e1) {
							e1.printStackTrace();
							System.out.println("日期类型转换错误");
						}
					}
				}
				if (blazeValue != null && ("houseCost".equals(key) || "perAssetBalance".equals(key)
						|| "perLoanpri".equals(key) || "perPayWage".equals(key) || "perFinAssetSum".equals(key)
						|| "sbMonthPaybase".equals(key) || "sbMonthPayment".equals(key)
						|| "gjjMonthPayment".equals(key))) {
					
					blazeValue = ((BigDecimal) blazeValue).divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
				}
				if ((value != null && !"".equals(value)) && (blazeValue != null && !"".equals(blazeValue))
						&& value.equals(blazeValue)) {
					continue;
				} else if ((value == null || "".equals(value)) && (blazeValue == null || "".equals(blazeValue))) {
					continue;
				}
				applyModifyLogMap.put(key, value);
			}
			return applyModifyLogMap;
		} else {
			return newMap;
		}
	}
}
