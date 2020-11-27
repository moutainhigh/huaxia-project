package com.huaxia.opas.dao.report.impl;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.KeyMessageModifyDao;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.report.TaskCallStatus;


/**
 * @author gaohui(关键信息表)
 *
 */
public class KeyMessageModifyDaoImpl extends AbstractDAO implements KeyMessageModifyDao{

	private static final String NAMESPACES = "KeyMessageModify.";

	@Override
	public String getKeyMessageModifyCountByDate(Map<String, Object> paraMap) {
		return this.getSqlMap().queryForObject(NAMESPACES + "getKeyMessageModifyCountByDate", paraMap);
	}
	@Override
	public List<Map<String, Object>> findKeyMessageModifyListByDate(Map<String, Object> paraMap, int pageNum,int pageRows) {
		return  getSqlMap().queryForList(NAMESPACES + "findKeyMessageModifyListByDate", paraMap, pageNum,pageRows);
	}
	@Override
	public List<KeyMessageModify> selectApplyLogByAppId(String appId,int curNum, int pageNum) {
		List list = getSqlMap().queryForList(NAMESPACES + "selectApplyLogByAppId", appId,curNum,pageNum);
		return (List<KeyMessageModify>)list;
	}
	/**
	 * 申请件信息修改  关键信息保存
	 */
	@Override
	public int queryCount() {
		return getSqlMap().queryForObject(NAMESPACES + "queryCount");
	}

	@Override
	public List<Map<Object, Object>> queryList(Map<String, Object> dataMap, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryList", dataMap, curNum, pageNum);
	}

	@Override
	public List<Map<Object, Object>> findListKeyMessageByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "findListKeyMessageByAppId", appId);
	}

	@Override
	public Integer queryKeyParamsByProperty(String column) {
		return getSqlMap().queryForObject(NAMESPACES + "queryKeyParamsByProperty", column);
	}

	@Override
	public List<Map<Object, Object>> findAllMesByAppId(String string) {
		return getSqlMap().queryForList(NAMESPACES + "findAllMesByAppId", string);
	}

	@Override
	public void insertKeyMessage(List<KeyMessageModify> keyList) {
		for (KeyMessageModify keyMessage : keyList) {
			getSqlMap().insert(NAMESPACES + "insertKeyMessage", keyMessage);
		}
	}

	@Override
	public void updateModifyLogFlag(String appId) {
		getSqlMap().update(NAMESPACES + "updateModifyLogFlag", appId);
	}
	@Override
	public List<String> selectColumnName(String columnName) {
		return getSqlMap().queryForList(NAMESPACES + "selectColumnName", columnName);
	}
	@Override
	public void deleteAllot(String substring) {
		getSqlMap().delete(NAMESPACES+"deleteAllot", substring);		
	}
	@Override
	public String qeurUserNameByUserCode(String crtUser) {
		return getSqlMap().queryForObject(NAMESPACES + "qeurUserNameByUserCode", crtUser);
	}
	@Override
	public String qeurUserNameByUserCode1(String crtUser) {
		return getSqlMap().queryForObject(NAMESPACES + "qeurUserNameByUserCode1", crtUser);
	}
	@Override
	public Map<Object, Object> queryKeyMessageInBizC1AndBizC2(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryKeyMessageInBizC1AndBizC2", appId);
	}
	@Override
	public int queryCountModify(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryCountModify", appId);
	}
	@Override
	public List<Map<Object, Object>> queryDetialMessage(List<Map<Object, Object>> listAppId) {
		return getSqlMap().queryForList(NAMESPACES + "queryDetialMessage", listAppId);
	}
	
	@Override
	public Map  executeProceDeletePbocAndFico(Map param) {
		return getSqlMap().queryForObject(NAMESPACES + "executeProceDeletePbocAndFico",param);
	}
	
	@Override
	public void  executeProceDeletePboc(Map param) {
		getSqlMap().queryForObject(NAMESPACES + "executeProceDeletePboc",param);
	}
	@Override
	public String queryHisAllotByAppId(AllotApplyAllotHis query1) {
		return getSqlMap().queryForObject(NAMESPACES + "queryHisAllotByAppId",query1);
	}
	@Override
	public void updateHisAllotByUuid(AllotApplyAllotHis aaaH2) {
		getSqlMap().update(NAMESPACES + "updateHisAllotByUuid",aaaH2);
	}
	@Override
	public Integer queryLaojian_num(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryLaojian_num",appId);
	}
	@Override
	public void updateLaojian_num(Map<String, String> laojianNum) {
		getSqlMap().update(NAMESPACES + "updateLaojian_num",laojianNum);
	}
	@Override
	public void update_jd_appln_is_create_file_flag(String appId) {
		getSqlMap().update(NAMESPACES + "update_jd_appln_is_create_file_flag",appId);
	}
	@Override
	public void ZSHY_INNER_CHECK(String appId) {
		getSqlMap().update(NAMESPACES + "ZSHY_INNER_CHECK",appId);
	}
	@Override
	public void executeProceCLEAN_INTERFACE_UNION(Map param) {
		getSqlMap().queryForObject(NAMESPACES + "executeProceCLEAN_INTERFACE_UNION",param);
	}
	@Override
	public String queryDelStatus(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryDelStatus",appId);
	}
	@Override
	public int selectKxTaskCount(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectKxTaskCount",appId);
	}
	@Override
	public void deleteKxSFRZData(String appId) {
		getSqlMap().delete(NAMESPACES+"deleteKxSFRZData", appId);
	}
	@Override
	public void deleteKxTask(String appId) {
		getSqlMap().delete(NAMESPACES+"deleteKxTask", appId);
	}
	@Override
	public void deleteKxSuccTask(String appId) {
		getSqlMap().delete(NAMESPACES+"deleteKxSuccTask", appId);
	}
	@Override
	public void insertkxTask(TaskCallStatus taskCallStatus) {
		getSqlMap().insert(NAMESPACES+"insertkxTask", taskCallStatus);
	}
}