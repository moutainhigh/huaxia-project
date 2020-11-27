/**
 * 
 */
package com.huaxia.opas.service.report;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.report.KeyMessageModify;

/**
 * @author gaohui (关键信息修改表)
 *
 */
public interface KeyMessageModifyService {
	/**
	*@Title:findListKeyMessageModifyByDate
	*@Description:查询关键信息表数据 通过时间区间
	*@param paramsMap
	*@param pageNum
	*@param pageRows
	*@return
	*@author: gaohui
	*@Date:2017年3月14日下午2:35:43
	 */
	public Map<String, Object> findListKeyMessageModifyByDate(Map<String, Object> paramsMap,int pageNum, int pageRows);
	/**
	 * 关键信息修改
	 * @return
	 * @throws CoreException
	 *@author: jiangming.yang
	 */
	public int queryCount() throws CoreException;

	public List<Map<Object, Object>> queryList(Map<String, Object> dataMap, int curNum, int pageNum) throws CoreException;

	public List<Map<Object, Object>> findListKeyMessageByAppId(String appId) throws CoreException;

	public Integer queryKeyParamsByProperty(String column) throws CoreException;

	public List<Map<Object, Object>> findAllMesByAppId(String string) throws CoreException;

	public void insertKeyMessage(List<KeyMessageModify> keyList) throws CoreException;

	public void updateModifyLogFlag(String appId) throws CoreException;
	
	public void keyMessageRecheckPass(String appId, String jsonString,Map<String, String> paroperMap) throws CoreException;
	
	public Map<Object, Object> queryKeyMessageInBizC1AndBizC2(String appId);
	
	public String qeurUserNameByUserCode(String crtUser);
	
	public String qeurUserNameByUserCode1(String crtUser);
	
	public List<Map<Object, Object>> queryDetialMessage(List<Map<Object, Object>> listAppId);
	
	public void executeProceCLEAN_INTERFACE_UNION(Map param);
	
	// 2018/6/6 from wjf 查询是否已做过关键信息修改
	public String selectDelStatus(String appId);
	
	// 2020/08/26 from lhy 可信身份认证修改操作
	public void saveKxSFRZPass(String appId,ApplyLifeCicle alc);
}
