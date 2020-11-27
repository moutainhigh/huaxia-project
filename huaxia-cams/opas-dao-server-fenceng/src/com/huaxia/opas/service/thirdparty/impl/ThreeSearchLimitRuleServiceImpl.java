package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.ThreeSearchLimitRuleDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.ThreeSearchLimitRuleService;

/**
 * 三方 查询限制规则
 * 
 * @author gaohui
 *
 */
public class ThreeSearchLimitRuleServiceImpl extends AbstractService implements ThreeSearchLimitRuleService , Serializable{
	private static final long serialVersionUID = 6208340463295016941L;
	
	@Resource(name = "threeSearchLimitRuleDao")
	private ThreeSearchLimitRuleDao threeSearchLimitRuleDao;
	
	public ThreeSearchLimitRuleDao getThreeSearchLimitRuleDao() {
		return threeSearchLimitRuleDao;
	}

	public void setThreeSearchLimitRuleDao(ThreeSearchLimitRuleDao threeSearchLimitRuleDao) {
		this.threeSearchLimitRuleDao = threeSearchLimitRuleDao;
	}

	@Override
	public void saveAddRule(Map<String, Object> paramMap) {
		threeSearchLimitRuleDao.saveAddRule(paramMap);
	}

	@Override
	public int queryCountByTypeUniqueCode(Map<String, Object> queryParamMap) {
		return threeSearchLimitRuleDao.selectCountByTypeUniqueCode(queryParamMap);
	}

	@Override
	public List<Map<String, Object>> queryExistedRuleByType(Map<String, Object> queryParamMap) {
		return threeSearchLimitRuleDao.selectExistedRuleByType(queryParamMap);
	}

	@Override
	public void deleteRulesByIdList(Map<String, Object> deleteParamMap) {
		 threeSearchLimitRuleDao.deleteRulesByIdList(deleteParamMap);
	}

	@Override
	public void saveOrUpdateSearchNumControl(Map<String, Object> saveOrUpdateParamMap) {
	
		Map<String, Object> queryParamMap=new HashMap<String,Object>();
		queryParamMap.put("limitModuleType", saveOrUpdateParamMap.get("limitModuleType"));
	    int typeCount=threeSearchLimitRuleDao.selectCountSearchNumLimitByType(queryParamMap);
		if(typeCount>0){//存在 则修改
			threeSearchLimitRuleDao.updateSearchNumControl(saveOrUpdateParamMap);
		}else{//不存在 插入
			threeSearchLimitRuleDao.saveSearchNumControl(saveOrUpdateParamMap);
		}
	}

	@Override
	public Map<String, Object> querySearchNumControlTimeQuantum(Map<String, Object> queryParamMap) {
		Map<String, Object> queryMapOne=new HashMap<String,Object>();
		String limitModuleType= queryParamMap.get("limitModuleType").toString();
		queryMapOne.put("limitModuleType", limitModuleType);
		Map<String, Object> htmlMap=new HashMap<String,Object>();
		 Map<String, Object> numLimitObj=threeSearchLimitRuleDao.selectObjectSearchNumLimitByType(queryMapOne);
		if(numLimitObj!=null){
			Map<String, Object> queryMapTwo=new HashMap<String,Object>();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date limitStartDate = new Date();
			Date limitEndDate = new Date();
			try {
				limitStartDate = fmt.parse(numLimitObj.get("limitStartDate").toString());
				limitEndDate = fmt.parse(numLimitObj.get("limitEndDate").toString());
			} catch (ParseException e1) {
			}
			queryMapTwo.put("limitStartDate", limitStartDate);
			queryMapTwo.put("limitEndDate", limitEndDate);
			int successedSearchNum=0;
			if("0900".equals(limitModuleType)){//车辆
				successedSearchNum=threeSearchLimitRuleDao.selectCountVehicleTimeQuantumSucceed(queryMapTwo);
			}
			if("001400".equals(limitModuleType)){//fico
				successedSearchNum=threeSearchLimitRuleDao.selectCountFicoTimeQuantumSucceed(queryMapTwo);
			}	
			if("001000".equals(limitModuleType)){//腾讯天御分
				successedSearchNum=threeSearchLimitRuleDao.selectCountTyTimeQuantumSucceed(queryMapTwo);
			}
			if("001100".equals(limitModuleType)){//手机实名任务编号
				successedSearchNum=threeSearchLimitRuleDao.selectCountSjTimeQuantumSucceed(queryMapTwo);
			}
			if("001200".equals(limitModuleType)){//企业及行业信息
				successedSearchNum=threeSearchLimitRuleDao.selectCountQyTimeQuantumSucceed(queryMapTwo);
			}
			if("001101".equals(limitModuleType)){//通讯运营商
				successedSearchNum=threeSearchLimitRuleDao.selectCountTxyyTimeQuantumSucceed(queryMapTwo);
			}
			if("001500".equals(limitModuleType)){//多头借贷
				successedSearchNum=threeSearchLimitRuleDao.selectLongLoanCountSucceed(queryMapTwo);
				Integer successedRealSearchNum = threeSearchLimitRuleDao.selectRealLoanCount(queryMapTwo);
				if( successedRealSearchNum == null ){
					successedRealSearchNum = 0;
				}
				htmlMap.put("nowSearchNum", successedRealSearchNum);
			}
			if("000700".equals(limitModuleType)){//多头借贷
				successedSearchNum=threeSearchLimitRuleDao.selectNetDtjdCount(queryMapTwo);
			}
			if("001900".equals(limitModuleType)){ // 保信汽车
				successedSearchNum=threeSearchLimitRuleDao.selectBaoXinCount(queryMapTwo);
			}
			htmlMap.put("limitQueryCount", numLimitObj.get("limitQueryCount"));
			if(numLimitObj.get("limitStartDate")!=null){
				htmlMap.put("limitStartDate", numLimitObj.get("limitStartDate").toString());
			}
			if(numLimitObj.get("limitEndDate")!=null){
				htmlMap.put("limitEndDate", numLimitObj.get("limitEndDate").toString());
			}
			htmlMap.put("successedSearchNum", successedSearchNum);
		}
		return htmlMap;
	}

	@Override
	public void saveOrUpdateTypeLimitSearch(Map initialMap) {
		
		List<Map<String,Object>> listParamMap=(List<Map<String, Object>>) initialMap.get("typeObjList");
		String currentUser=initialMap.get("userId").toString();
		for (Map<String, Object> saveOrUpdateParamMap : listParamMap) {
			Map<String,Object> queryParamMap=new HashMap<String,Object>();
			queryParamMap.put("bRuleType",saveOrUpdateParamMap.get("bRuleType"));
			queryParamMap.put("sRuleType",saveOrUpdateParamMap.get("sRuleType"));
			queryParamMap.put("ruleSettingValue",saveOrUpdateParamMap.get("ruleSettingValue"));
			int countNum= threeSearchLimitRuleDao.selectCountTypeLimitSearch(queryParamMap);
			if(countNum>0){//修改
				saveOrUpdateParamMap.put("lstUpdUser", currentUser);
				threeSearchLimitRuleDao.updateTypeLimitSearch(saveOrUpdateParamMap);
			}else{//插入
				saveOrUpdateParamMap.put("crtUser", currentUser);
				saveOrUpdateParamMap.put("lstUpdUser", currentUser);
				threeSearchLimitRuleDao.saveTypeLimitSearch(saveOrUpdateParamMap);
			}
		}
	}

	@Override
	public List<Map<String, Object>> queryTypeLimitSearch(Map<String, Object> queryParamMap) {
		
		List<Map<String, Object>> list=threeSearchLimitRuleDao.selectTypeLimitSearch(queryParamMap);
		return list;
	}

	@Override
	public Map<String, Object> queryVerhicleCheckInfo(Map<String, Object> queryParamMap) {
		
		Map<String, Object> revertMap=threeSearchLimitRuleDao.selectVerhicleCheckInfo(queryParamMap);
		
		if(revertMap!=null){
			
			String queryResult="";//查询结果
			if(revertMap.get("queryResult")!=null){
				queryResult=revertMap.get("queryResult").toString();
			}
			String errorCode="";
			if(revertMap.get("errorCode")!=null){
				errorCode=revertMap.get("errorCode").toString();
			}	
			String status="0";//有无车辆
			if(revertMap.get("status")!=null&&!"".equals(revertMap.get("status"))){
				status=revertMap.get("status").toString();
			}	
		
			// Error 0且result为1 查询成功 、 Error 0且result为-1 查询失败 、Error 40100 姓名和身份证号不匹配
			if ("0".equals(errorCode)&&"1".equals(queryResult)){
				revertMap.put("queryResultChinese", "查询成功");
				if("1".equals(status)){
					revertMap.put("quantityChinese", "是");
				}else{
					revertMap.put("quantityChinese", "否");
				}
			}else if("0".equals(errorCode)&&"-1".equals(queryResult)){
				revertMap.put("queryResultChinese", "查询失败");
				revertMap.put("quantity", "——");
				revertMap.put("price", "——");
				revertMap.put("months", "——");
			}else if("40100".equals(errorCode)){//"姓名和身份证号不匹配"
				revertMap.put("queryResultChinese", "姓名和身份证号不匹配");
				revertMap.put("quantity", "——");
				revertMap.put("price", "——");
				revertMap.put("months", "——");
			}else{//查询异常中 不包含40100 的 异常 也归为查询失败 
				revertMap.put("queryResultChinese", "查询失败");
				revertMap.put("quantity", "——");
				revertMap.put("price", "——");
				revertMap.put("months", "——");
			}
			
		}
		return revertMap;
	}
	@Override
	public Map<String, Object> queryBxVerhicleCheckInfo(String appId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> revertMap=threeSearchLimitRuleDao.selectBxVerhicleCheckInfo(appId.substring(0,15));
		if(revertMap!=null){
			String queryResult="";//查询结果
			String retCode="";//返回码 RET_CODE
			String vehicleAgeRange="";//车龄判定范围  CAR_AGE
			String vehicleValue="";//车辆风险价值 RISK_VALUE_RANGE
			if(revertMap.containsKey("RET_CODE")){
				retCode=revertMap.get("RET_CODE").toString();
			}
			if(revertMap.containsKey("CAR_AGE")){
				vehicleAgeRange=revertMap.get("CAR_AGE").toString();
			}
			if(revertMap.containsKey("RISK_VALUE_RANGE")){
				vehicleValue=revertMap.get("RISK_VALUE_RANGE").toString();
			}
			if("000000".equals(retCode)){//查询成功
				if(!"".equals(vehicleAgeRange)||!"".equals(vehicleValue)){
				    queryResult="查询成功有数据";
				}else{
					queryResult="查询成功无数据";
				}
				//映射原始值与实际范围
				//映射车龄范围
                switch(vehicleAgeRange){
	                case "z":  vehicleAgeRange = "空值" ;break;
	                case "-1": vehicleAgeRange = "异常" ;break;
	                case "01": vehicleAgeRange = "0~1年" ;break;
	                case "02": vehicleAgeRange = "1~5年" ;break;
	                case "03": vehicleAgeRange = "5~8年" ;break;
	                case "04": vehicleAgeRange = "8~15年" ;break;
	                case "05": vehicleAgeRange = "15+年" ;break;
	                default:vehicleAgeRange="无法匹配数据";
                }
				//映射车产价值
				if("z".equals(vehicleValue)){
					vehicleValue = "空值";			
				}else if("-1".equals(vehicleValue)){
					vehicleValue = "异常";
				}else{
					try{
						int tmp = Integer.valueOf(vehicleValue);
						if(tmp>=1&&tmp<=20){			
							vehicleValue = ""+(tmp-1)*5+"~"+tmp*5+"万";							
						}else if(tmp==21){
							vehicleValue = "100+万";
						}else{
							//数据超出范围
							vehicleValue = "无法匹配数据";
						}  
					}catch(Exception e){
						vehicleValue = "无法匹配数据";
					}
				}
			}else{
				queryResult="查询失败";
				vehicleAgeRange="";
				vehicleValue="";
			}		
			resultMap.put("queryResult", queryResult);
			resultMap.put("vehicleAgeRange", vehicleAgeRange);
			resultMap.put("vehicleValue", vehicleValue);
		}else{
			resultMap.put("queryResult", "未发起查询");
			resultMap.put("vehicleAgeRange", "-----");
			resultMap.put("vehicleValue", "-----");
		}
		return resultMap;		
	}
	/**
	 * 查询条数详细部分
	 */
	@Override
	public Map<String, String> queryLoanNumber(String yearNumber){
		return threeSearchLimitRuleDao.selectLoanNumber(yearNumber);
		
	}
	/**
	 * 查询条数详细部分
	 */
	@Override
	public Map<String, String> querySumLoanNumber(String yearNumber){
		return threeSearchLimitRuleDao.selectSumLoanNumber(yearNumber);
		
	}

	@Override
	public Map<String, String> queryLoanNumberNet(String yearNumber) {
		return threeSearchLimitRuleDao.queryLoanNumberNet(yearNumber);
	}
	
	@Override
	public void saveBaoXinTypeLimitSearch(Map initialMap) {
		String currentUser=initialMap.get("userId").toString(); // userId
		int countNum= threeSearchLimitRuleDao.selectCountTypeLimitSearch(initialMap); // 查询是否为重复数据
		if(countNum>0){//修改
			initialMap.put("lstUpdUser", currentUser);
			threeSearchLimitRuleDao.updateTypeLimitSearch(initialMap);
		}else{//插入
			initialMap.put("crtUser", currentUser);
			initialMap.put("lstUpdUser", currentUser);
			threeSearchLimitRuleDao.saveTypeLimitSearch(initialMap);
		}
	}	
		
	@Override
	public List<Map<String, Object>> queryBaoXinRuleByType(Map<String, Object> queryMap) {
		return threeSearchLimitRuleDao.selectBaoXinRuleByType(queryMap);
	}
	
	@Override
	public void deleteBaoXinRulesByIdList(Map<String, Object> deleteParamMap) {
		threeSearchLimitRuleDao.deleteBaoXinRulesByIdList(deleteParamMap);	
	}

	@Override
	public Map<String, String> queryBaoXinNumberNet(String yearNumber) {
		return threeSearchLimitRuleDao.queryBaoXinNumberNet(yearNumber);
	}

	@Override
	public List<Map<String, Object>> queryPadPortraitComparisonRuleByType(Map<String, Object> queryParamMap) {
		return threeSearchLimitRuleDao.selectPadPortraitComparisonRuleByType(queryParamMap);
	}

	@Override
	public void savePadPortraitComparisonTypeLimitSearch(Map<String, Object> initialMap) {
		String currentUser=initialMap.get("userId").toString(); // userId
		int countNum= threeSearchLimitRuleDao.selectCountPadPortraitComparisonTypeLimitSearch(initialMap); // 查询是否为重复数据
		if(countNum>0){//修改
			initialMap.put("lstUpdUser", currentUser);
			threeSearchLimitRuleDao.updatePadPortraitComparisonTypeLimitSearch(initialMap);
		}else{//插入
			initialMap.put("crtUser", currentUser);
			initialMap.put("lstUpdUser", currentUser);
			threeSearchLimitRuleDao.savePadPortraitComparisonTypeLimitSearch(initialMap);
		}
		
	}

	@Override
	public void deletePadPortraitComparisonRulesByIdList(Map<String, Object> deleteParamMap) {
		threeSearchLimitRuleDao.deletePadPortraitComparisonRulesByIdList(deleteParamMap);	
		
	}

	@Override
	public List<String> queryAllTypeLimitSearch() {
		return threeSearchLimitRuleDao.queryAllTypeLimitSearch();
	}

}
