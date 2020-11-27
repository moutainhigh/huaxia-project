package com.huaxia.opas.service.credit;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpAppl;

public interface TeamPhoneService  {
	
	//save征信电核注记信息
	
	int saveTeamPhoneAddnote(List<TelcheckAddnote> list) throws CoreException;
	
	int savePhoneAddnote(TelcheckAddnote tel) throws CoreException;
	//查询
	int countAppList(BizInpAppl biz) throws CoreException ;
	
	List queryAppList(BizInpAppl biz,int currentPage, int pageSize) throws CoreException ;
	
	List<TelcheckAddnote> queryTeamPhoneByList(List<String> list,int currentPage, int pageSize) throws CoreException ;
	
	List<TelcheckAddnote> queryTeamPhoneByWinId(Map map) throws CoreException ;
	
	int queryCountByWinId(Map map) throws CoreException ;
	
	int queryCountByTabId(Map map) throws CoreException ;
	
	//更新
	int updateTeamPhoneByTabId(Map map) throws CoreException ;
	
	//删除  一批件集体电核
	int deleteByTabId(Map map) throws CoreException ;
	
	//一键取消全部集体电核
	int  deleteByWinId( Map map) throws CoreException ;
	/**
	 *@Title:findPhoneSourceData
	 *@Description:获取 电话来源参数表的 电话来源类型 。电话来源描述  数据
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月18日下午7:57:12
	 */
	public List<Map<String,Object>> findPhoneSourceData(Map paramMap);
	/**
	 *@Title:findPhoneTypeData
	 *@Description:获取电话类型  从电话号码参数表中
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月21日下午1:57:53
	 */
	public List<Map<String,Object>> findPhoneTypeData(Map paramMap);
	/**
	 *@Title:findPhoneNoteObjectData
	 *@Description:获取 照会对象  照会对象参数表
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月21日下午2:28:06
	 */
	public List<Map<String,Object>> findPhoneNoteObjectData(Map paramMap);
	/**
	 *@Title:findPhoneCheckResultData
	 *@Description:获取 电核结论  电核结论参数表
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月21日下午2:52:51
	 */
	public List<Map<String,Object>> findPhoneCheckResultData(Map paramMap);
	/**
	 *@Title:findPhoneSourceTypeComboboxData
	 *@Description:返回 电话类型、 照会对象等四个下拉框的内容
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月21日下午6:06:48
	 */
	public Map<String,Object> findPhoneSourceTypeComboboxData(Map paramMap);
	
	/**
	 *@queryNoteByTime
	 *@Description:返回纪要列表
	 *@param paramMap
	 *@return
	 *@author: shixiaoting
	 *@Date:2017年5月22日上午10:52:20
	 */
	public List<TelcheckAddnote> queryNoteByTime(Date date);
	/**
	 * 获取套卡标识
	 * @param String
	 * @return  wangdebin
	 */
	String selectMatchingCardFlag(String appId);
}
