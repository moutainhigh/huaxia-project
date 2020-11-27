package com.huaxia.opas.service.collect.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyModifyLogDao;
import com.huaxia.opas.dao.collect.InfoCollectDao;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.audit.ApprovaInfoSupp;
import com.huaxia.opas.domain.audit.FicoSdBlaze;
import com.huaxia.opas.domain.collect.InfoCollect;
import com.huaxia.opas.service.collect.InfoCollectService;
public class InfoCollectServiceImpl implements InfoCollectService , Serializable {
	
	private static final long serialVersionUID = 1L;
	@Resource(name = "infoCollectDao")
	private InfoCollectDao infoCollectDao;
	@Resource(name = "applyModifyLogDao")
	private ApplyModifyLogDao applyModifyLogDao;
	@Override
	public InfoCollect queryInfoCollect(Map map) throws CoreException {
		return getInfoCollectDao().queryInfoCollect(map);
	}
	@Override
	public ApprovaInfoSupp queryApprovaInfoSupp(Map s) throws CoreException {
		return getInfoCollectDao().queryApprovaInfoSupp(s);
	}

	@Override
	public int insertApprovaInfoSupp(ApprovaInfoSupp s) throws CoreException {
		return getInfoCollectDao().insertApprovaInfoSupp(s);
	}

	@Override
	public int updateApprovaInfoSupp(ApprovaInfoSupp s) throws CoreException {
		return getInfoCollectDao().updateApprovaInfoSupp(s);
	}

	@Override
	public int deleteApprovaInfoSupp(String s) throws CoreException {
		return getInfoCollectDao().deleteApprovaInfoSupp(s);
	}

	public InfoCollectDao getInfoCollectDao() {
		return infoCollectDao;
	}

	public void setInfoCollectDao(InfoCollectDao infoCollectDao) {
		this.infoCollectDao = infoCollectDao;
	}
	
	/**
	 * 查询plaze返回的信息回显到录入审查页面
	 */
	@Override
	public Map<String, String> queryEnteringPageNeedDataByAppId(Map<String,Object> paramMap) throws CoreException {
		Map<String,String> dataHtmlMap = infoCollectDao.querySdBlaze(paramMap);
		return dataHtmlMap;
//		Map<String,Object> custType=null;
//		ApprovaInfoSupp approvaInfoSupp=new ApprovaInfoSupp();
//		approvaInfoSupp=infoCollectDao.queryApprovaInfoSupp(paramMap);
//		if(approvaInfoSupp==null){
//			approvaInfoSupp=new ApprovaInfoSupp();
//		}
//
//		custType=infoCollectDao.selectStandardCardReturnTableDataByAppid(paramMap);//获取用户类型
//		String jsonapprovaInfoSupp=JSON.toJSONStringWithDateFormat(approvaInfoSupp, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
//		dataHtmlMap.put("jsonapprovaInfoSupp", jsonapprovaInfoSupp);
//		dataHtmlMap.put("approvaInfoSupp", approvaInfoSupp);
//		if(custType!=null){
//			dataHtmlMap.put("CUSTTYPE",custType);
//		}else{
//			dataHtmlMap.put("CUSTTYPE","");
//		}
	}
	@Override
	public FicoSdBlaze queryEnteringPageNeedDataByAppId1(Map<String,Object> paramMap) throws CoreException {
		FicoSdBlaze dataHtmlMap = infoCollectDao.querySdBlaze1(paramMap);
		return dataHtmlMap;
	}
	
	@Override
	public int insertApplyModifyLogList(List<ApplyModifyLog> applyModifyLogList) {
		return applyModifyLogDao.insertinsertApplyModifyLogList(applyModifyLogList);
	}
	@Override
	public Map<String, String> selectNameAndCardMap(String appId) {
		return infoCollectDao.selectNameAndCardMap(appId);
	}
	@Override
	public int queryCountByIndustryCode(String industryCode) {
		return infoCollectDao.queryCountByIndustryCode(industryCode);
	}
	@Override
	public Map<String, String> queryRegionalReserveFundByAppid(String appId) {
		
		return infoCollectDao.selectRegionalReserveFundByAppid(appId);
	}
}
