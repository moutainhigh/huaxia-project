package com.huaxia.opas.dao.decision;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.credit.T5eEsMiddle;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.TelcheckResult;

public interface TelcheckAddnoteDao {
    int deleteByPrimaryKey(String autoId);

    int insert(TelcheckAddnote record);

    int insertSelective(TelcheckAddnote record);

    TelcheckAddnote selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(TelcheckAddnote record);

    int updateByPrimaryKey(TelcheckAddnote record);

	List<TelcheckAddnote> queryNotesList(String appId);
	/**
	 *@Title:insertTeamPhone
	 *@Description:集体电核插入
	 *@return
	 *@author: wangdebin
	 *@Date:2017年3月28日
	 */
	//集体电话插入
	
	int insertTeamPhone(List<TelcheckAddnote> list);
	
	int insertPhone(TelcheckAddnote addnote);
	//更新
	int updateTeamPhone(List<TelcheckAddnote> list);
	
	int updateTeamPhoneByTabId(Map map);
	//
	void saveTelcheckResult(TelcheckResult result);
	
	//删除
	int deleteByTabId(Map map);
	
	int deleteByWinId(Map map);
	//集体电核查询
	
	int selectCountByWinId(Map map) throws CoreException;
	
	int selectCountByTabId(Map map) throws CoreException;
	
	List<TelcheckAddnote> selectTeamPhoneByWinId(Map map);
	
	List<TelcheckAddnote> selectTeamPhoneByList(List<String> list, int currentPage, int pageSize);
	/**
	 *@Title:selectListPhoneSourceData
	 *@Description:获取 电话来源参数表的 电话来源类型 。电话来源描述 数据
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月18日下午8:00:58
	 */
	public List<Map<String,Object>> selectListPhoneSourceData(Map paramMap);
	/**
	 *@Title:selectListPhoneTypeData
	 *@Description:获取电话类型  从电话号码参数表中
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月21日下午1:59:53
	 */
	public List<Map<String,Object>> selectListPhoneTypeData(Map paramMap);
	/**
	 *@Title:selectListPhoneNoteObjectData
	 *@Description:获取 照会对象  照会对象参数表
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月21日下午2:29:57
	 */
	public List<Map<String,Object>> selectListPhoneNoteObjectData(Map paramMap);
	/**
	 *@Title:selectListPhoneCheckResultData
	 *@Description:获取 电核结论  电核结论参数表
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月21日下午2:54:29
	 */
	public List<Map<String,Object>> selectListPhoneCheckResultData(Map paramMap);
	
	/**
	 *@Title:selectlistPassCodeResult
	 *@Description:获取过件码
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年7月7日下午11:33:21
	 */
	public List<Map<String, Object>> selectlistPassCodeResult(Map paramMap);
	
	TelcheckResult selectResultById(String appId);

	int updateTelcheckResult(TelcheckResult result);

	int updateByAppIdTelcheckResult(TelcheckResult result);

	List<TelcheckAddnote> queryNodeByTimeStamp(Date time);

	int updateByAutoIdTelcheckAddnote(TelcheckAddnote note);
    /**
     *@Title:deleteTelcheckAddNoteByTalId
     *@Description:根据talId删除信息 
     *@param talId
     *@author: gaohui
     *@Date:2017年8月12日下午3:27:18
     */
	void deleteTelcheckAddNoteByTalId(Map<String, Object> param);
	/**
	 *@Title:updateTelcheckAddNoteByTalId
	 *@Description:根据talId修改信息 
	 *@param note
	 *@author: gaohui
	 *@Date:2017年8月12日下午3:38:18
	 */
	void updateTelcheckAddNoteByTalId(TelcheckAddnote note);
	
	public TelcheckResult selectTelcheckResultByAppId(String appId);
	/**
	 *@Title:updateBizInpApplComporeByAppId
	 *@Description:根据AppId修改 opas_biz_inp_appl_compore 的住宅地址字段
	 *@param map
	 *@author: andong
	 *@Date:2017年9月15日下午9:52:35
	 */
	void updateBizInpApplComporeByAppId(Map<Object, Object> map);

	T5eEsMiddle queryT5eEsMiddle(Map<String, Object> dataMap);
	
}