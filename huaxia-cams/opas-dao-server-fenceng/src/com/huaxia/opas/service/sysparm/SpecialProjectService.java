package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.SpecialprojectList;
import com.huaxia.opas.domain.sysparm.SpecialprojectListHis;

public interface SpecialProjectService {
	int deleteByPrimaryKey(String autoId) throws CoreException;

    int insert(SpecialprojectList specialprojectlist) throws CoreException;

    int insertSelective(SpecialprojectList specialprojectlist) throws CoreException;

    SpecialprojectList selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(SpecialprojectList specialprojectlist) throws CoreException;

    int updateByPrimaryKey(SpecialprojectList specialprojectlist) throws CoreException;
    
    //shihuan 2017-03-16  特殊项目列表查询 
  	List<SpecialprojectList> querySpecialprojectList(SpecialprojectList specialprojectlist, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-16  特殊项目单列表查询条数 
  	int querySpecialprojectCount(SpecialprojectList specialprojectlist) throws CoreException;
  	
  	//shihuan 2017-03-16   特殊项目批量启动修改状态
  	int updateStartStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-16   特殊项目批量停用修改状态
  	int updateStopStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-16  添加历史修改记录表
  	int insertHisSelective(SpecialprojectListHis specialprojectlisthis) throws CoreException;
  	
  	//shihuan 2017-03-16  历史特殊项目列表查询 
  	List<SpecialprojectList> querySpecialprojectHistory(String autoId, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-16  历史特殊项目单列表查询条数 
  	int queryHistoryCount(String autoId) throws CoreException;
  	
  	int insertSpecProUpload(List<SpecialprojectList> obj, BatchfileInfo batchfileInfo) throws CoreException;

  	//shihuan 2017-04-24  校验项目代码不能重复 
  	int queryProjectCode(String projectCode) throws CoreException;
  	
}
