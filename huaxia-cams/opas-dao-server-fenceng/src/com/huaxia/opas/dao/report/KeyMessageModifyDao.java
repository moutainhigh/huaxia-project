package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.report.TaskCallStatus;

public interface KeyMessageModifyDao {
	/**
	*@Title:getKeyMessageModifyCountByDate
	*@Description:通过日期区间获取关键信息表的总条数
	*@param paraMap
	*@return
	*@author: gaohui
	*@Date:2017年3月14日下午2:46:30
	 */
	public String getKeyMessageModifyCountByDate(Map<String, Object> paraMap);
	/**
	*@Title:findKeyMessageModifyListByDate
	*@Description:通过日期区间获取关键信息表的数据
	*@param paraMap
	*@param pageNum 第几页
	*@param pageRows 每页显示的条数
	*@return
	*@author: gaohui
	*@Date:2017年3月14日下午2:45:34
	 */
	public List<Map<String, Object>>findKeyMessageModifyListByDate(Map<String, Object> paraMap,int pageNum, int pageRows);
	/**
	 * 申请表信息关键信息修改 
	 * @return
	 * @author: jiangming.yang
	 */
	public int queryCount();
	public List<Map<Object, Object>> queryList(Map<String, Object> dataMap, int curNum, int pageNum);
	public List<Map<Object, Object>> findListKeyMessageByAppId(String appId);
	public Integer queryKeyParamsByProperty(String column);
	public List<Map<Object, Object>> findAllMesByAppId(String string);
	public void insertKeyMessage(List<KeyMessageModify> keyList);
	public void updateModifyLogFlag(String appId);
	List<KeyMessageModify> selectApplyLogByAppId(String appId,int curNum,int pageNum);
	public List<String>  selectColumnName(String columnName);
	public void deleteAllot(String substring);
	public String qeurUserNameByUserCode(String crtUser);
	public String qeurUserNameByUserCode1(String crtUser);
	public Map<Object, Object> queryKeyMessageInBizC1AndBizC2(String appId);
	public int queryCountModify(String appId);
	public List<Map<Object, Object>> queryDetialMessage(List<Map<Object, Object>> listAppId);
	public Map executeProceDeletePbocAndFico(Map param);
	public void executeProceDeletePboc(Map param);
	public String queryHisAllotByAppId(AllotApplyAllotHis query1);
	public void updateHisAllotByUuid(AllotApplyAllotHis aaaH2);
	public Integer queryLaojian_num(String appId);
	public void updateLaojian_num(Map<String, String> laojianNum);
	public void update_jd_appln_is_create_file_flag(String appId);
	public void ZSHY_INNER_CHECK(String appId);
	public void executeProceCLEAN_INTERFACE_UNION(Map param);
	public String queryDelStatus(String appId);
	//可信身份信息修改相关方法：只重新调可信流程
	public int selectKxTaskCount(String appId);
	public void deleteKxSFRZData(String appId);
	public void deleteKxTask(String appId);
	public void deleteKxSuccTask(String appId);
	public void insertkxTask(TaskCallStatus taskCallStatus); 
	
}