package com.huaxia.opas.service.apply.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyInfoDao;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.collect.InfoCollectDao;
import com.huaxia.opas.dao.compare.EtcPadDao;
import com.huaxia.opas.dao.compare.RevCompInfoDao;
import com.huaxia.opas.dao.decision.KeyfiledMarchinfoDao;
import com.huaxia.opas.dao.thirdparty.QiYeDao;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheck;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.service.compare.EtcPadService;
import com.huaxia.opas.service.compare.RevCompInfoService;
import com.huaxia.opas.util.StringUtil;
import com.ibm.icu.util.Calendar;

import net.sf.json.JSONArray;

public class ApplyLifeCicleServiceImpl extends AbstractService implements ApplyLifeCicleService, Serializable {

	private static final long serialVersionUID = 7696400194059513085L;
	private static Logger logger = LoggerFactory.getLogger(ApplyLifeCicleServiceImpl.class);
	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;
	@Resource(name = "keyfiledMarchinfoDao")
	private KeyfiledMarchinfoDao keyfiledMarchinfoDao;
	@Resource(name = "revCompInfoDao")
	private RevCompInfoDao revCompInfoDao;
	@Resource(name = "applyInfoDao")
	private ApplyInfoDao applyInfoDao;
	@Resource(name = "infoCollectDao")
	private InfoCollectDao infoCollectDao;
	@Resource(name = "qiYeDao")
	private QiYeDao qiYeDao;
	@Resource(name = "revCompInfoService")
	private RevCompInfoService revCompInfoService;	
	@Resource(name = "etcPadDao")
	private EtcPadDao etcPadDao;


	public QiYeDao getQiYeDao() {
		return qiYeDao;
	}
	public void setQiYeDao(QiYeDao qiYeDao) {
		this.qiYeDao = qiYeDao;
	}
	
	
	@Override
	public int addApplyLifeCicle(ApplyLifeCicle a) throws Exception {
		return applyLifeCicleDao.addApplyLifeCicle(a);
	}

	@Override
	public int addApplyLifeBatch(List<ApplyLifeCicle> cicleList) throws Exception {
		return applyLifeCicleDao.insertApplyLifeBatch(cicleList);
	}

	@Override
	public int addInfoCompareToExamine(String appId, String currNode) throws Exception {
		//免录入落比对结果
		RevCompInfo revCompInfo = getRevCompInfo(appId);
		logger.info("service层开始插入比对信息" + appId);
		//根据appid查询审查录入比对信息表中是否已经存在比对信息记录
		RevCompInfo ifExistReverComInfo = revCompInfoDao.selectByPrimaryKey(appId);
		if(ifExistReverComInfo==null){
			revCompInfoDao.insertSelective(revCompInfo);
		}else{
			revCompInfoDao.updateByPrimaryKeySelective(revCompInfo);
		}
		logger.info("service层结束插入比对信息" + appId);
		//免录入录录入行职业代码
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("appId", appId);
		Map<String, String> blazeMap = infoCollectDao.querySdBlaze(paramMap);
		blazeMap.put("appId", appId);
		logger.info("service层开始插入录入信息" + appId);
		//根据appid查询基本信息补充表中是否已经存在录入信息
		int count = infoCollectDao.queryInfoCollectByAppid(appId);
		int i=0;
		if(count==0){
			 i = infoCollectDao.insertInfoCollect(blazeMap);
		}else{
			 i = infoCollectDao.updateInfoCollect(blazeMap);
		}
		logger.info("service层结束插入录入信息" + appId);
		return i;
	}

	public RevCompInfo getRevCompInfo(String appId) throws Exception {
		Map<String, String> map = new HashMap<>();
		String isPad = appId.substring(6,7);
		map.put("appId", appId);
		BizInpApplC1 t = new BizInpApplC1();
		t.setAppId(appId);
		BizInpApplC1 bizInpApplC1 = applyInfoDao.queryBizInpApplC1ByAppId(appId);
		RevCompInfo revCompInfo = new RevCompInfo();
		revCompInfo.setAppId(appId);
		KeyfiledMarchinfo kfmi = keyfiledMarchinfoDao.selectByAppId(map);
		//==============================匹配结果的逻辑判断====================================
		if (kfmi != null) {
			revCompInfo = getRevCompInfo(revCompInfo,kfmi,bizInpApplC1);
		}
		//===============================非匹配结果判断逻辑=================================
		// (1)人行首张贷记卡发卡日期是否大于六个月
		String peobankFirstcard6months = getPeobankFirstcard6months(appId);
		if("1".equals(peobankFirstcard6months)){
			revCompInfo.setPeobankFirstcard6months(peobankFirstcard6months);
			revCompInfo.setApplyWorkinfoTure("1");//如果人行首张贷记卡发卡日期大于6个月=申请人工作信息真实
		}
		
		//(2)社保公积金信息近6个月缴交正常
		/*String sbandgjj6monthsStatus = getSbandgjj6monthsStatus(appId,bizInpApplC1);
		revCompInfo.setSbandgjj6monthsStatus(sbandgjj6monthsStatus);
		*/
		//(3)国籍填写是否有效
		String c1Nation = bizInpApplC1.getC1Nation();
		if("1".equals(c1Nation) || "5".equals(c1Nation)){
			revCompInfo.setNationalInutfull("1");
		}
		
		//经营状态非异常且企业网法人姓名=申请表申请人姓名，比对页面系统自动勾选“工商网法人”
		String businetworkLegalStatus = getBusinetworkLegalStatus(appId, bizInpApplC1);
		if ("1".equals(businetworkLegalStatus)) {
			revCompInfo.setBusinetworkLegal("1");
		}
		
		/*if("A".equals(isPad)){
	        revCompInfo.setApplyInputfull("1");
	        revCompInfo.setSignFull("1");
	        revCompInfo.setCertifiPeriodfull("1");
		}*/
		
		//ETC迅卡审批功能优化：审批系统录入环节“抄录文字完整有效、签名完整有效、身份证有效期填写完整有效”三个勾选项需根据PAD系统后送结果进行自动勾选判断。
				String isSignatureIntegrityValid = null;//申请人签名及抄录文字是否完整有效
				String isIdDateIntegrityValid = null;//申请人身份证明文件完成有效
				//根据appid查询  PAD进件追加信息表中的两个字段值 ： 申请人签名及抄录文字是否完整有效、申请人身份证明文件完成有效
				Map<String, String> signatureValidAndIdDateValid = etcPadDao.selectSignatureAndIdByAppId(appId);
				
				if(signatureValidAndIdDateValid!=null){
					isSignatureIntegrityValid = signatureValidAndIdDateValid.get("isSignatureIntegrityValid");
					isIdDateIntegrityValid = signatureValidAndIdDateValid.get("isIdDateIntegrityValid");
				}
				if("A".equals(isPad)){
					
					if("1".equals(isSignatureIntegrityValid)){
						revCompInfo.setApplyInputfull("1");//抄录文字完整有效
			        	revCompInfo.setSignFull("1");//签名完整有效
					}
					
					if("1".equals(isIdDateIntegrityValid)){
						revCompInfo.setCertifiPeriodfull("1");//身份证有效期填写完整有效
					}
					
				}
		
		return revCompInfo;
	}
	public RevCompInfo getRevCompInfo(RevCompInfo revCompInfo,KeyfiledMarchinfo kfmi,BizInpApplC1 bizInpApplC1) throws Exception{
		String matchResult=kfmi.getMarchResult();
		String company1="";//人行单位名称
		String compAddr1="";//人行单位地址
		String ccompPhone1="";//人行单位电话
		String cellPhoneCheck="";//人行手机
		String billAddressCheck="";//人行账单地址revCompInfo.getAppId()
		String officwebName="";//官方单名
		String officwebAddr="";//官方单址
		String officwebTel="";//官方单电
		String padPositionAddrFlag = "";//Pad定位地址标识
		String houseAddressCheck="";//人行住宅地址是否与申请表家庭地址是否匹配
		/*------------------------------------------------------------------------------------------*/
		String currentStatus = ""; // 当前参保状态
		String lastUnitName = "";
		String lastUnitName1 = "";
		String lastUnitName2 = "";
		String lastUnitName3 = "";
		String lastUnitName4 = "";
		String lastUnitName5 = "";  //温州公积金区域数据
		String lastUnitName6 = "";	//银川公积金区域数据
		String lastUnitName7 = "";

		String status = "";
		String status1 = "";
		String status2= "";
		String status3= "";
		String status4= "";
		String status5= "";
		String status6= "";
		String status7= "";

		String insuredStatus = "";//厦门参保状态
		/*------------------------------------------------------------------------------------------*/
		//深圳区域数据当前参保状态为正常时
		Map<String, String> sisz2Map = revCompInfoService.selectCurrentStatus(revCompInfo.getAppId());
		if(sisz2Map != null){
			currentStatus = sisz2Map.get("CURRENT_STATUS");// 当前参保状态
		}
		//厦门易联众
		Map<String, String> repDataMap = revCompInfoService.selectInsuredStatus(revCompInfo.getAppId());
		if(repDataMap != null){
			insuredStatus = repDataMap.get("INSURED_STATUS");//厦门参保状态
		}
		/*------------------------------------------------------------------------------------------*/
		if(matchResult!=null&&!"".equals(matchResult)){
			JSONArray ja=JSONArray.fromObject(matchResult);
			List<RiskCheck> listrisk=(List) JSONArray.toCollection(ja, RiskCheck.class);
			for (RiskCheck r : listrisk) {
				/*------------------------------------------------------------------------------------------*/
				//鹏元
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("PY_PCR_CRS_CRT_SISZ2_HI5Y".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("PY_PCR_CRS_CRT_SISZ2_HI5Y".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//易联众
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("YLZ_REP_DATA".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("YLZ_REP_DATA".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//人行
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("OPAS_PBOC_PUBLIC_ACCFUND".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName2 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("OPAS_PBOC_PUBLIC_ACCFUND".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status2 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//杭州区域数据
				if ((("DEPTNAME").equals(r.getFieldKey()))
						&& ("HZ_GJJXX".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName3 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("HZ_GJJXX".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status3 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//厦门公积金区域数据
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("XM_GJJ_JCXX".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName4 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("XM_GJJ_JCXX".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status4 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}

				//宁波社保
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("NB_BASE".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName7 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("NB_BASE".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status7 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}

				//温州公积金区域数据
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("WZ_GR_GJJZL".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName5 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("WZ_GR_GJJZL".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status5 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//银川公积金区域数据
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("YC_INFO".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName6 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("YC_INFO".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status6 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}

				/*------------------------------------------------------------------------------------------*/
				if ((("COMPANY").equals(r.getFieldKey())) && ("OPAS_PBOC_PROFESSION_INFO".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					company1 = "1".equals(r.getRiskResult())?r.getRiskResult():null;
				}
				if ((("COMP_ADDR").equals(r.getFieldKey()) && ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					compAddr1 = "1".equals(r.getRiskResult())?r.getRiskResult():null;
				}
				/*if ((("C_COMP_PHONE").equals(r.getFieldKey()) && ("OPAS_PBOC_PERSONAL_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					ccompPhone1 = "1".equals(r.getRiskResult())?r.getRiskResult():null;
				}
				if ((("CELL_PHONE").equals(r.getFieldKey()) && ("OPAS_PBOC_PERSONAL_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					cellPhoneCheck = "1".equals(r.getRiskResult())?r.getRiskResult():null;
				}*/
				
				if ((("UNIT_PHONE").equals(r.getFieldKey())
						&& ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					ccompPhone1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
		
				if ((("PHONE_NUM").equals(r.getFieldKey())
						&& ("PBOC_PHONE_NUMBER_DETAIL").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					cellPhoneCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				
				//新加官方单名+单址 官方单名+单电的判断 2018-05-22
				if(("ENTNAME".equals(r.getFieldKey())&&("TRD_QYHY_INFO_DATA").equals(r.getTableName())
						&&"2".equals(r.getRiskType()))){
					officwebName = "1".equals(r.getRiskResult())?r.getRiskResult():null;
				}
				if(("DOM".equals(r.getFieldKey())&&("TRD_QYHY_INFO_DATA").equals(r.getTableName())
						&&"2".equals(r.getRiskType()))){
					officwebAddr = "1".equals(r.getRiskResult())?r.getRiskResult():null;
				}
				if(("TEL".equals(r.getFieldKey())&&("TRD_QYHY_INFO_DATA").equals(r.getTableName())
						&&"2".equals(r.getRiskType()))){
					officwebTel = "1".equals(r.getRiskResult())?r.getRiskResult():null;
				}
				
				//新增  Pad定位地址的判断 2019-11-11
				if (("BASE_STATION".equals(r.getFieldKey()) && ("CCARD_APP_PAD_ADDITION").equals(r.getTableName())
						&& "2".equals(r.getRiskType()))) {
					padPositionAddrFlag = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
					
				if("B".equals(bizInpApplC1.getC4Cycadd1())){
					//如果主卡寄往的地址是单位地址,查询人行个人职业信息表获得人行单位地址proessionInfoList
					if ((("COMP_ADDR").equals(r.getFieldKey()) && ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
							&& "3".equals(r.getRiskType()))) {
						billAddressCheck = "1".equals(r.getRiskResult())?r.getRiskResult():null;
					}
				}else if("H".equals(bizInpApplC1.getC4Cycadd1())){
					//如果主卡寄往的地址是家庭地址
					if ((("RESIDENT_ADDR").equals(r.getFieldKey()) && ("OPAS_PBOC_RESIDENCE_INFO").equals(r.getTableName())
							&& "3".equals(r.getRiskType()))) {
						billAddressCheck = "1".equals(r.getRiskResult())?r.getRiskResult():null;
					}
				}else{
					billAddressCheck = null ;
				}
				
				if(("C5_IDTE1".equals(r.getFieldKey())&&("OPAS_BIZ_INP_APPL_C1").equals(r.getTableName())
					&&"2".equals(r.getRiskType()))){
						if("1".equals(r.getPriKeyValue())||"3".equals(r.getPriKeyValue())||"4".equals(r.getPriKeyValue())){
							revCompInfo.setCertifiPeriodfull("1");
						}
				}
				//根据申请表住宅地址和人行住宅地址是否匹配
				if ((("RESIDENT_ADDR").equals(r.getFieldKey()) && ("OPAS_PBOC_RESIDENCE_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					houseAddressCheck = "1".equals(r.getRiskResult())?r.getRiskResult():"";
				}
			}
		}
		/*------------------------------------------------------------------------------------------*/
		if ( "1".equals(status)|| "1".equals(status1)||"1".equals(status2)||"1".equals(status3)
				||"1".equals(status4)||"1".equals(status5)||"1".equals(status6)||"1".equals(status7)) {
			revCompInfo.setSbandgjj6monthsStatus("1");
		}

		if ("1".equals(lastUnitName)|| "1".equals(lastUnitName1)||"1".equals(lastUnitName3)
				||"1".equals(lastUnitName4)||"1".equals(lastUnitName5)||"1".equals(lastUnitName6)||"1".equals(lastUnitName7)) {
			revCompInfo.setRegionalDataListName("1");
		}
		
		/*if ("1".equals(lastUnitName)|| "1".equals(lastUnitName1)||"1".equals(lastUnitName2)) {
			revCompInfo.setRegionalDataListName("1");
		}*/
		
		/*if ("1".equals(status)) {
			revCompInfo.setSbandgjj6monthsStatus(status);
		}
		if ("1".equals(status1)) {
			revCompInfo.setSbandgjj6monthsStatus(status);
		}
		if ("1".equals(status2)) {
			revCompInfo.setSbandgjj6monthsStatus(status);
		}*/
		/*if ("1".equals(company1) && "正常参保".equals(currentStatus.trim()) && "参保缴费".equals(insuredStatus.trim())) {
			revCompInfo.setRegionalDataListName(company1);
		}*/
		
		/*if ("1".equals(company1)) {
			revCompInfo.setRegionalDataListName(company1);
		}*/
		
		/*------------------------------------------------------------------------------------------*/
		if("1".equals(company1)){
			//申请单位真实模块下 ：单位名称是否一致 取值 PEOBANK_COMPANY_NAME 人行单位名称，页面上用name="company" 赋值
			revCompInfo.setCompany(company1);
			//revCompInfo.setPeobankCompanyName("1");//人行单位名称
			if("1".equals(compAddr1)||"1".equals(ccompPhone1)){
				revCompInfo.setApplyWorkTure("1");//申请单位真实
			}
		}
		if("1".equals(compAddr1)){
			revCompInfo.setPeobankCompanyNameAddr(compAddr1);
		}
		if("1".equals(ccompPhone1)){
			revCompInfo.setPeobankCompanyNameTel(ccompPhone1);
		}
		if("1".equals(cellPhoneCheck)){
			revCompInfo.setPeobankPhone(cellPhoneCheck);
			revCompInfo.setApplyerTrue("1");//申请人真实
		}
		if("1".equals(billAddressCheck)){
			revCompInfo.setOrderAddress(billAddressCheck);//账单地址真实
		}
		if("1".equals(officwebName)&&"1".equals(officwebAddr)){
			revCompInfo.setOfficwebNameAddr("1");
		}
		if("1".equals(officwebName)&&"1".equals(officwebTel)){
			revCompInfo.setOfficwebNameTel("1");
		}
		
		//Pad定位地址     控制 比对页面Pad定位地址的勾选
		if ("1".equals(padPositionAddrFlag)) {
			revCompInfo.setPadPositionAddr("1");//Pad定位地址
			revCompInfo.setApplyWorkTure("1");//申请单位真实
		}
		
		//住宅地址
		if("1".equals(houseAddressCheck)){
			revCompInfo.setHomeAddress("1");
		}
		return revCompInfo;
	}
	
	/**
	 * 人行首张贷记卡发卡日期是否大于六个月
	 * @param appId
	 * @return
	 * @throws CoreException 
	 */
	public String getPeobankFirstcard6months(String appId) throws CoreException{
		// 人行首张贷记卡发卡日期大于六个月
		// 人行信用提示信息表--一代人行用的表
		//String mon = revCompInfoDao.selectMonByAppId(appId);
		//二代人行（目前使用的表）：信贷交易提示信息--PBOC_CREDIT_TRANSACTION_DEL，人行个人基本信息表--opas_pboc_personal_info;
		Map<String,Object> months = revCompInfoDao.selectMonthsByAppId(appId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
		int monret = 0;
		if (months != null && !"".equals(months)) {
			//首笔业务发放月份  --专门查人行首张贷记卡发卡日期
			String firstYwGrantMonth = (String) months.get("FIRST_YW_GRANT_MONTH");
			//报告时间
			String reportTime = (String) months.get("REPORT_TIME");
			Date d1;
			Date d2;
			try {
				/*if(firstYwGrantMonth != null && !firstYwGrantMonth.trim().isEmpty() && !"".equals(firstYwGrantMonth) 
						&& reportTime != null && !reportTime.trim().isEmpty() && !"".equals(reportTime)){
					d1 = sdf.parse(firstYwGrantMonth);
					d2 = sdf.parse(reportTime);
					monret = getMonth(d1, d2);
				}*/
				if(StringUtil.isNotBlank(firstYwGrantMonth) && StringUtil.isNotBlank(reportTime)){
					d1 = sdf.parse(firstYwGrantMonth);
					d2 = sdf.parse(reportTime);
					monret = getMonth(d1, d2);
				}
			} catch (ParseException e) {
				logger.error("人行首张贷记卡发卡日期是否大于六个月-日期格式解析异常：{}", new Object[] { e.getMessage() }, e);
			}
		}
		if (monret > 6) {
			return "1";
		}
		return null;
	}
	/**
	 * 社保公积金信息近6个月缴交正常
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	/*public String getSbandgjj6monthsStatus(String appId,BizInpApplC1 bizInpApplC1) throws Exception{
		Map<String,String> blazeMap = revCompInfoDao.selectGjjMonth(appId);
		String c1Coname = bizInpApplC1.getC1Coname();//申请表信息单位地址
		if(blazeMap!=null){
			String gjjPayStatus = blazeMap.get("RETURN_GJJ_STATUS");//公积金缴交状态
			String gjjEndMonth = blazeMap.get("RETURN_GJJ_TOMONTH");//公积金缴至月份
			String sbCurrPayStatus = blazeMap.get("RETURN_SOC_SAVE_TYPE");//社保缴交状态
			String sbCurrPaycompany = blazeMap.get("RETURN_SOC_UNIT");//社保缴交单位
			String subAppId = "20" + appId.substring(0,2) + "." + appId.substring(2,4);
			SimpleDateFormat sdfFormat=new SimpleDateFormat("yyyy.MM");
			Date applyDate = sdfFormat.parse(subAppId);
			int month = 10;
			if(gjjEndMonth!=null){
				Date gjjEndDate = sdfFormat.parse(gjjEndMonth);
			    month = getMonth(gjjEndDate,applyDate);
			}
			Boolean sflag = false;
			if(c1Coname!=null&&sbCurrPaycompany!=null&&c1Coname.equals(sbCurrPaycompany)){
				sflag = true;
			}
			if("1".equals(gjjPayStatus)||month<=6||"1".equals(sbCurrPayStatus)||sflag){
				return "1";
			}
		}
		return null;
	}*/
	
	/**
	 * 经营状态非异常且企业网法人姓名=申请表申请人姓名
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public String getBusinetworkLegalStatus(String appId, BizInpApplC1 bizInpApplC1) {
		Map<String, String> EetFrnameMap = qiYeDao.queryEetFrnameByAppId(appId);
		if (EetFrnameMap != null && EetFrnameMap.get("FRNAME") != null && EetFrnameMap.get("FRNAME") != "") {
			String entStatus = EetFrnameMap.get("ENTSTATUS");
			String frName = EetFrnameMap.get("FRNAME");
			String c1CName = bizInpApplC1.getC1Cname();
			if (("在营（开业）".equals(entStatus) || "开业".equals(entStatus) || "存续".equals(entStatus)
					|| "正常".equals(entStatus) || "正常执业".equals(entStatus) || "正常经营".equals(entStatus))
					&& frName.equals(c1CName)) {
				return "1";
			}
		}
		return null;
	}
	
	// 计算两个日期相差的月份
	private static int getMonth(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int m1 = c1.get(Calendar.MONTH);
		int m2 = c2.get(Calendar.MONTH);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int ret = (y2 - y1) * 12 + m2 - m1;
		return ret;
	}

}
