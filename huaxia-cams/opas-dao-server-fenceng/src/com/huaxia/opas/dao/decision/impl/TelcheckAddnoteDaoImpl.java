package com.huaxia.opas.dao.decision.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.TelcheckAddnoteDao;
import com.huaxia.opas.domain.credit.T5eEsMiddle;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.TelcheckResult;

public class TelcheckAddnoteDaoImpl extends AbstractDAO implements TelcheckAddnoteDao {
	
	private static final long serialVersionUID = 2757582418545513991L;

	private static final String NAMESPACES = "TelcheckAddnote.";
	private static final String NAMESPACES_RESULT = "TelcheckResult.";
	
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	public int deleteByAppId(String appId) {
		return getSqlMap().delete(NAMESPACES + "deleteByAppId", appId);
	}

	@Override
	public int insert(TelcheckAddnote record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(TelcheckAddnote record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	public TelcheckAddnote selectByAppId(String appId) {
		return (TelcheckAddnote) (getSqlMap().queryForObject(NAMESPACES + "selectByAppId", appId));
	}

	@Override
	public TelcheckAddnote selectByPrimaryKey(String autoId) {
		return (TelcheckAddnote) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public TelcheckResult selectTelcheckResultByAppId(String appId) {
		return (TelcheckResult) (getSqlMap().queryForObject(NAMESPACES_RESULT + "selectByPrimaryKey", appId));
	}
	@Override
	public int updateByPrimaryKeySelective(TelcheckAddnote record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(TelcheckAddnote record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}
	//集体电核
	@Override
	public int insertTeamPhone(List<TelcheckAddnote> list) {
		return getSqlMap().insert(NAMESPACES + "insertTeamPhone", list);
	}
	
	@Override
	public int updateTeamPhone(List<TelcheckAddnote> list) {
		return getSqlMap().update(NAMESPACES + "updateTeamPhone", list);
	}
	
	@Override
	public int updateTeamPhoneByTabId(Map map) {
		return getSqlMap().update(NAMESPACES + "updateTeamPhoneByTabId", map);
	}
	
	@Override
	public int deleteByTabId(Map map) {
		return getSqlMap().delete(NAMESPACES + "deleteByTabId", map);
	}
	
	@Override
	public int deleteByWinId(Map map) {
		return getSqlMap().delete(NAMESPACES + "deleteByWinId", map);
	}
	
	@Override
	public int insertPhone(TelcheckAddnote note) {
		return getSqlMap().insert(NAMESPACES + "insertPhone", note);
	}
	
	@Override
	public List<TelcheckAddnote> selectTeamPhoneByList(List<String> list, int currentPage, int pageSize) {
		return getSqlMap().queryForList(NAMESPACES + "selectTeamPhoneByList", list, currentPage,  pageSize);
	}
	
	@Override
	public int selectCountByWinId(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCountByWinId",map);
	}
	
	@Override
	public int selectCountByTabId(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCountByTabId",map);
	}
	
	@Override
	public List<TelcheckAddnote> selectTeamPhoneByWinId(Map map) {
		return getSqlMap().queryForList(NAMESPACES + "selectTeamPhoneByWinId", map);
	}
	
	//
	@Override
	public List<TelcheckAddnote> queryNotesList(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryList", appId);
	}

	@Override
	public void saveTelcheckResult(TelcheckResult result) {
		 getSqlMap().insert(NAMESPACES_RESULT + "insertSelective", result);
	}

	@Override
	public List<Map<String, Object>> selectListPhoneSourceData(Map paramMap) {
	  return getSqlMap().queryForList(NAMESPACES + "selectListPhoneSourceData",paramMap);
	}

	@Override
	public TelcheckResult selectResultById(String appId) {
		return (TelcheckResult)getSqlMap().queryForObject(NAMESPACES_RESULT + "selectResultById", appId);
	}

	@Override
	public int updateTelcheckResult(TelcheckResult result) {
		return getSqlMap().update(NAMESPACES_RESULT + "updateTelcheckResult", result);
	}

	@Override
	public List<Map<String, Object>> selectListPhoneTypeData(Map paramMap) {
		return	getSqlMap().queryForList(NAMESPACES + "selectListPhoneTypeData",paramMap);
	}

	@Override
	public List<Map<String, Object>> selectListPhoneNoteObjectData(Map paramMap) {
		return	getSqlMap().queryForList(NAMESPACES + "selectListPhoneNoteObjectData",paramMap);
	}

	@Override
	public List<Map<String, Object>> selectListPhoneCheckResultData(Map paramMap) {
		return	getSqlMap().queryForList(NAMESPACES + "selectListPhoneCheckResultData",paramMap);
	}
   
	@Override
	public int updateByAppIdTelcheckResult(TelcheckResult result) {
		return getSqlMap().update(NAMESPACES_RESULT + "updateByAppIdTelcheckResult", result);
	}

	@Override
	public List<TelcheckAddnote> queryNodeByTimeStamp(Date time) {
		
		return getSqlMap().queryForList(NAMESPACES + "queryListByTime", time);
	}

	@Override
	public int updateByAutoIdTelcheckAddnote(TelcheckAddnote note) {
		return getSqlMap().update(NAMESPACES + "updateByAutoIdTelcheckAddnote", note);
	}

	@Override
	public List<Map<String, Object>> selectlistPassCodeResult(Map paramMap) {
		return	getSqlMap().queryForList(NAMESPACES + "selectlistPassCodeResult",paramMap);
	}

	@Override
	public void deleteTelcheckAddNoteByTalId(Map<String, Object> param) {
		getSqlMap().delete(NAMESPACES + "deleteTelcheckAddNoteByTalId", param);
		
	}

	@Override
	public void updateTelcheckAddNoteByTalId(TelcheckAddnote note) {
		getSqlMap().update(NAMESPACES + "updateTelcheckAddNoteByTalId", note);
	}

	@Override
	public void updateBizInpApplComporeByAppId(Map<Object, Object> map) {
	    getSqlMap().update(NAMESPACES_RESULT + "updateBizInpApplComporeByAppId", map);
	}

	@Override
	public T5eEsMiddle queryT5eEsMiddle(Map<String, Object> dataMap) {
		return (T5eEsMiddle)getSqlMap().queryForObject(NAMESPACES + "queryT5eEsMiddle", dataMap);
	}
}