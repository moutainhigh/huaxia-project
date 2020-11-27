package com.huaxia.opas.service.compare.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.ApplyModifyLogDao;
import com.huaxia.opas.dao.collect.InfoCollectDao;
import com.huaxia.opas.dao.compare.RevCompInfoDao;
import com.huaxia.opas.dao.decision.KeyfiledMarchinfoDao;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.service.compare.RevCompInfoService;

/**
 * 审查录入比对信息
 * 添加查询从反欺诈返回的人行匹配结果的方法
 * @author wangtao
 * @version v1.1 初始版本v1.0
 * 2017-10-20
 */
public class RevCompInfoServiceImpl implements RevCompInfoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6639468274113006605L;

	@Resource(name = "revCompInfoDao")
	private RevCompInfoDao revCompInfoDao;
	
	@Resource(name="keyfiledMarchinfoDao")
	private KeyfiledMarchinfoDao keyfiledMarchinfoDao;

	@Resource(name = "infoCollectDao")
	private InfoCollectDao infoCollectDao;
	
	@Resource(name = "applyModifyLogDao")
	private ApplyModifyLogDao applyModifyLogDao;
	@Override
	public int deleteByPrimaryKey(String appId) throws CoreException {
		return getRevCompInfoDao().deleteByPrimaryKey(appId);
	}

	@Override
	public int insert(RevCompInfo record,Map<String, Object> newMap, Map<String, Object> beanMap, String appId, String userCode,String isWebBolt,String commitFlag) throws CoreException {
		if("1".equals(commitFlag)){
			getApplyModifyLogMap(newMap,beanMap,appId,userCode);
		}
		//强行通过的比对项信息保存后将信息存入全流程备注信息表
		Map<String, Object> remarkMap = new HashMap<>();
		String remarkInfo = "录入比对全流程备注:必选项未勾选项";
		if((record.getApplyInputfull()==null||"".equals(record.getApplyInputfull()))&&!"B".equals(isWebBolt)){
			remarkInfo = remarkInfo+"【抄录文字完整有效】";
		}
		if((record.getSignFull()==null||"".equals(record.getSignFull()))&&!"B".equals(isWebBolt)){
			remarkInfo = remarkInfo+"【签名完整有效】";
		}
		if(record.getNationalInutfull()==null||"".equals(record.getNationalInutfull())){
			remarkInfo = remarkInfo+"【国籍填写完整有效】";
		}
		if(record.getCertifiPeriodfull()==null||"".equals(record.getCertifiPeriodfull())){
			remarkInfo = remarkInfo+"【身份证有效期完整有效】";
		}
		if(!("录入比对全流程备注:必选项未勾选项").equals(remarkInfo)){
			remarkMap.put("remarkInfo", remarkInfo);
			remarkMap.put("remarkId",UUID.randomUUID().toString().replace("-", ""));
			remarkMap.put("remarkUser",userCode);
			remarkMap.put("remarkTime", new Date());
			remarkMap.put("appId", appId);
			remarkMap.put("currNode", "01");
			revCompInfoDao.insertRemark(remarkMap);
		}else{
			revCompInfoDao.deleteRemarkByAppId(appId);
		}
		return getRevCompInfoDao().insert(record);
	}

	@Override
	public int insertSelective(RevCompInfo record) throws CoreException {
		return getRevCompInfoDao().insertSelective(record);
	}

	@Override
	public RevCompInfo selectByPrimaryKey(String appId) throws CoreException {
		return getRevCompInfoDao().selectByPrimaryKey(appId);
	}

	@Override
	public int updateByPrimaryKeySelective(RevCompInfo record) throws CoreException {
		return getRevCompInfoDao().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RevCompInfo record,Map<String, Object> newMap, Map<String, Object> beanMap, String appId, String userCode,String isWebBolt,String commitFlag) throws CoreException {
		if("1".equals(commitFlag)){
			getApplyModifyLogMap(newMap,beanMap,appId,userCode);
		}
		//强行通过的比对项信息保存后将信息存入全流程备注信息表
		Map<String, Object> remarkMap = new HashMap<>();
		String remarkInfo = "录入比对全流程备注:必选项未勾选项";
		if((record.getApplyInputfull()==null||"".equals(record.getApplyInputfull()))&&!"B".equals(isWebBolt)){
			remarkInfo = remarkInfo+"【抄录文字完整有效】";
		}
		if((record.getSignFull()==null||"".equals(record.getSignFull()))&&!"B".equals(isWebBolt)){
			remarkInfo = remarkInfo+"【签名完整有效】";
		}
		if(record.getNationalInutfull()==null||"".equals(record.getNationalInutfull())){
			remarkInfo = remarkInfo+"【国籍填写完整有效】";
		}
		if(record.getCertifiPeriodfull()==null||"".equals(record.getCertifiPeriodfull())){
			remarkInfo = remarkInfo+"【身份证有效期完整有效】";
		}
		if(!("录入比对全流程备注:必选项未勾选项").equals(remarkInfo)){
			remarkMap.put("remarkInfo", remarkInfo);
			remarkMap.put("remarkId",UUID.randomUUID().toString().replace("-", ""));
			remarkMap.put("remarkUser",userCode);
			remarkMap.put("remarkTime", new Date());
			remarkMap.put("appId", appId);
			remarkMap.put("currNode", "01");
			revCompInfoDao.insertRemark(remarkMap);
		}else{
			revCompInfoDao.deleteRemarkByAppId(appId);
		}
		return getRevCompInfoDao().updateByPrimaryKey(record);
	}

	public RevCompInfoDao getRevCompInfoDao() {
		return revCompInfoDao;
	}

	public void setRevCompInfoDao(RevCompInfoDao revCompInfoDao) {
		this.revCompInfoDao = revCompInfoDao;
	}

	@Override
	public KeyfiledMarchinfo selectKeyfiledMarchinfoByAppId(Map<String, String> map) {
		return this.keyfiledMarchinfoDao.selectByAppId(map);
	}

	@Override
	public String selectMonByAppId(String appId) throws CoreException {
		return this.revCompInfoDao.selectMonByAppId(appId);
	}

	@Override
	public Map<String, Object> selectMonthsByAppId(String appId) throws CoreException {
		return this.revCompInfoDao.selectMonthsByAppId(appId);
	}
	@Override
	public Map<String, String> selectGongAnByAppId(Map map) throws CoreException {
		return this.revCompInfoDao.selectGongAnByAppId(map);
	}

	@Override
	public int insertRemark(Map<String, Object> remarkMap) {
		return this.revCompInfoDao.insertRemark(remarkMap);
	}

	@Override
	public Map<String, String> selectPoliceMapByAppId(String appId) {
		return this.revCompInfoDao.selectPoliceMapByAppId(appId);
	}

	@Override
	public Map<String, String> selectIvsMap(String appId) {
		return this.revCompInfoDao.selectIvsMap(appId);
	}

	@Override
	public int deleteRemarkByAppId(String appId) {
		return this.revCompInfoDao.deleteRemarkByAppId(appId);
	}

//	@Override
//	public Map<String, String> selectFqzRiskDesc(Map<String, String> map2) {
//		return this.revCompInfoDao.selectFqzRiskDesc(map2);
//	}

	@Override
	public Map<String,String>  selectGjjMonth(String appId) {
		return this.revCompInfoDao.selectGjjMonth(appId);
	}

	public void getApplyModifyLogMap(Map<String, Object> newMap, Map<String, Object> beanMap,String appId,String userCode) {
		Set<Entry<String, Object>> entrySet = beanMap.entrySet();
		List<ApplyModifyLog> applyModifyLogList = new ArrayList<>();
		Date date = new Date();
		Map<String, Object> applyModifyLogMap = new HashMap<>();
		if (null != newMap && !newMap.isEmpty()) {
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object value = entry.getValue();
				Object newValue = newMap.get(key);
				if ((value != null && !"".equals(value)) && (newValue != null && !"".equals(newValue))
						&& value.equals(newValue)) {
					continue;
				} else if ((value == null ||  "".equals(value)) && (newValue == null ||  "".equals(newValue))) {
					continue;
				}
				applyModifyLogMap.put(key, value);
			}
		} else {
			applyModifyLogMap = beanMap;
		}
		if (null != applyModifyLogMap && !applyModifyLogMap.isEmpty()) {
			Set<Entry<String, Object>> entrySets = applyModifyLogMap.entrySet();
			for (Entry<String, Object> entry : entrySets) {
				ApplyModifyLog applyModifyLog = new ApplyModifyLog();
				String key = entry.getKey();
				Object value = entry.getValue();
				applyModifyLog.setAutoId(UUID.randomUUID().toString().replace("-", ""));
				applyModifyLog.setFieldName(key);
				applyModifyLog.setFieldOldValue(newMap.get(key)==null?"":newMap.get(key));
				applyModifyLog.setFieldNewValue(value==null?"":value);
				Map<String, String> c1Map = infoCollectDao.selectNameAndCardMap(appId);
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
		if(applyModifyLogList!=null&&applyModifyLogList.size()>0){
			applyModifyLogDao.insertinsertApplyModifyLogList(applyModifyLogList);
		}
	}

	@Override
	public Map<String, String> selectCurrentStatus(String appId) {
		return this.revCompInfoDao.selectCurrentStatus(appId);
	}

	@Override
	public Map<String, String> selectInsuredStatus(String appId) {
		return this.revCompInfoDao.selectInsuredStatus(appId);
	}

	@Override
	public Map<String, String> selectPayStatus(String appId) {
		return this.revCompInfoDao.selectPayStatus(appId);
	}

	@Override
	public Map<String, String> selectCurrNodeKey(String appId) {
		return this.revCompInfoDao.selectCurrNodeKey(appId);
	}

}