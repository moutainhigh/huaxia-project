package com.huaxia.opas.service.credit.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.dao.decision.TelcheckAddnoteDao;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.credit.TeamPhoneService;

public class TeamPhoneServiceImpl extends AbstractService implements TeamPhoneService, Serializable {

	private static final long serialVersionUID = -5286276550553333492L;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TeamPhoneServiceImpl.class);
	@Resource(name = "telcheckAddnoteDao")
	private TelcheckAddnoteDao telcheckAddnoteDao;
		
	@Resource(name = "bizInpApplDao")
	private BizInpApplDao bizInpApplDao;
	
	public TelcheckAddnoteDao getTelcheckAddnoteDao() {
		return telcheckAddnoteDao;
	}

	public void setTelcheckAddnoteDao(TelcheckAddnoteDao telcheckAddnoteDao) {
		this.telcheckAddnoteDao = telcheckAddnoteDao;
	}

	public BizInpApplDao getBizInpApplDao() {
		return bizInpApplDao;
	}

	public void setBizInpApplDao(BizInpApplDao bizInpApplDao) {
		this.bizInpApplDao = bizInpApplDao;
	}

	@Override
	public int saveTeamPhoneAddnote(List<TelcheckAddnote> list) throws CoreException{
		int num=0;
		num=telcheckAddnoteDao.insertTeamPhone(list);
		if(num!=0){
			logger.info("该业务的征信注纪【添加】成功");
		}else {
			logger.info("该业务的征信注纪【添加】失败");
		}
		return num;
	}
	@Override
	public int savePhoneAddnote(TelcheckAddnote tel) throws CoreException{
		int num=0;
		num=telcheckAddnoteDao.insertPhone(tel);
		if(num!=0){
			logger.info("该业务的征信注纪【添加】成功");
		}else {
			logger.info("该业务的征信注纪【添加】失败");
		}
		return num;
	} 
	@Override
	public List queryAppList(BizInpAppl biz,int currentPage, int pageSize) throws CoreException {
		return bizInpApplDao.selectAppList(biz, currentPage, pageSize);
	}
	
	@Override
	public List<TelcheckAddnote> queryTeamPhoneByList(List<String> list,int currentPage, int pageSize) throws CoreException {
		return telcheckAddnoteDao.selectTeamPhoneByList(list, currentPage, pageSize);
	}
	
	@Override
	public List<TelcheckAddnote> queryTeamPhoneByWinId(Map map) throws CoreException {
		return telcheckAddnoteDao.selectTeamPhoneByWinId(map);
	}
	
	@Override
	public int queryCountByWinId(Map map) throws CoreException {
		return getTelcheckAddnoteDao().selectCountByWinId(map);
	}
	
	@Override
	public int queryCountByTabId(Map map) throws CoreException {
		return getTelcheckAddnoteDao().selectCountByTabId(map);
	}
	
	@Override
	public int countAppList(BizInpAppl biz) throws CoreException {
		return bizInpApplDao.selectAppCount(biz);
	}
	
	@Override
	public int updateTeamPhoneByTabId(Map map) throws CoreException {
		return telcheckAddnoteDao.updateTeamPhoneByTabId(map);
	}
	
	@Override
	public int deleteByTabId(Map map) throws CoreException{
		return telcheckAddnoteDao.deleteByTabId(map);
	}
	
	@Override
	public int deleteByWinId(Map map) throws CoreException {
		return telcheckAddnoteDao.deleteByWinId(map);
	}
	//
	@Override
	public List<Map<String, Object>> findPhoneSourceData(Map paramMap) {
		return  telcheckAddnoteDao.selectListPhoneSourceData(paramMap);
		
	}

	@Override
	public List<Map<String, Object>> findPhoneTypeData(Map paramMap) {
		return  telcheckAddnoteDao.selectListPhoneTypeData(paramMap);
	}

	@Override
	public List<Map<String, Object>> findPhoneNoteObjectData(Map paramMap) {
		return  telcheckAddnoteDao.selectListPhoneNoteObjectData(paramMap);
	}

	@Override
	public List<Map<String, Object>> findPhoneCheckResultData(Map paramMap) {
		return  telcheckAddnoteDao.selectListPhoneCheckResultData(paramMap);
	}

	@Override
	public Map<String, Object> findPhoneSourceTypeComboboxData(Map paramMap) {
		Map<String, Object> htmlMap=new HashMap<String, Object>();
		Map<String, Object> map=new HashMap<String, Object>();
		//map.put("dictDetailCode", "");
		//map.put("dictDetailName", "请选择");
		
		List<Map<String, Object>>listPhoneSource=telcheckAddnoteDao.selectListPhoneSourceData(paramMap);
		
		if(!listPhoneSource.isEmpty()){
			List<Map<String,Object>>htmlListPhoneSourceMap=new ArrayList<Map<String,Object>>();
			//htmlListPhoneSourceMap.add(map);
			htmlListPhoneSourceMap.addAll(listPhoneSource);
			htmlMap.put("listPhoneSource", htmlListPhoneSourceMap);
		}
		
		List<Map<String, Object>>listPhoneType=	telcheckAddnoteDao.selectListPhoneTypeData(paramMap);
		if(!listPhoneType.isEmpty()){
			List<Map<String,Object>>htmlListPhoneSourceMap=new ArrayList<Map<String,Object>>();
			//htmlListPhoneSourceMap.add(map);
			htmlListPhoneSourceMap.addAll(listPhoneType);
			htmlMap.put("listPhoneType", htmlListPhoneSourceMap);
			
		}
		List<Map<String, Object>>listPhoneNoteObject=telcheckAddnoteDao.selectListPhoneNoteObjectData(paramMap);
		if(!listPhoneNoteObject.isEmpty()){
			List<Map<String,Object>>htmlListPhoneSourceMap=new ArrayList<Map<String,Object>>();
			//htmlListPhoneSourceMap.add(map);
			htmlListPhoneSourceMap.addAll(listPhoneNoteObject);
			htmlMap.put("listPhoneNoteObject", htmlListPhoneSourceMap);
			
		}
		List<Map<String, Object>>listPhoneCheckResult=	telcheckAddnoteDao.selectListPhoneCheckResultData(paramMap);
		if(!listPhoneCheckResult.isEmpty()){
			List<Map<String,Object>>htmlListPhoneSourceMap=new ArrayList<Map<String,Object>>();
			//htmlListPhoneSourceMap.add(map);
			htmlListPhoneSourceMap.addAll(listPhoneCheckResult);
			htmlMap.put("listPhoneCheckResult", htmlListPhoneSourceMap);
			
		}
		
		//取过件码
		List<Map<String, Object>>listPassCodeResult=telcheckAddnoteDao.selectlistPassCodeResult(paramMap);
		if(!listPassCodeResult.isEmpty()){
			List<Map<String,Object>>htmlListPhoneSourceMap=new ArrayList<Map<String,Object>>();
			for (int i = 0; i<listPassCodeResult.size(); i++){
				Object dictDetailCode = listPassCodeResult.get(i).get("dictDetailCode");
				listPassCodeResult.get(i).put("dictDetailCode", dictDetailCode);
				listPassCodeResult.get(i).put("dictDetailName", dictDetailCode);
			}
			//htmlListPhoneSourceMap.add(map);
			htmlListPhoneSourceMap.addAll(listPassCodeResult);
			htmlMap.put("listPassCodeResult", htmlListPhoneSourceMap);
		}
		return htmlMap;
	}
	@Override
	public List<TelcheckAddnote> queryNoteByTime(Date date){
		return telcheckAddnoteDao.queryNodeByTimeStamp(date);
	}

	@Override
	public String selectMatchingCardFlag(String appId) {
		return bizInpApplDao.selectMatchingCardFlag(appId);
	}

}
