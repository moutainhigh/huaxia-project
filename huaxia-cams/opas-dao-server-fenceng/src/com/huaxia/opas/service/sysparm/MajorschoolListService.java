package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.MajorschoolList;
import com.huaxia.opas.domain.sysparm.MajorschoolListHistory;

public interface MajorschoolListService {
	
    int deleteByPrimaryKey(String autoId) throws CoreException;

    int insert(MajorschoolList majorschoollist) throws CoreException;

    int insertSelective(MajorschoolList majorschoollist) throws CoreException;

    MajorschoolList selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(MajorschoolList majorschoollist) throws CoreException;

    int updateByPrimaryKey(MajorschoolList majorschoollist) throws CoreException;
    
    //shihuan 2017-03-14 重点院校列表查询 
  	List<MajorschoolList> queryMajorSchooList(MajorschoolList majorschoollist, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-14   重点院校列表查询条数 
  	int queryMajorSchooCount(MajorschoolList majorschoollist) throws CoreException;
  	
  	//shihuan 2017-03-14   重点院校批量启动修改状态
  	int updateStartStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-14   重点院校批量停用修改状态
  	int updateStopStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-14  添加历史修改记录表
  	int insertHisSelective(MajorschoolListHistory majorschoollisthistory) throws CoreException;
  	
  	//shihuan 2017-03-14 历史重点院校列表查询 
  	List<MajorschoolList> queryMajorSchoolHistory(String autoId, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-14   历史重点院校列表查询条数 
  	int queryMajorSchoolHistoryCount(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-18 重点院校名单批导入文件
  	public int insertMajorSchUpload(List<MajorschoolList> obj, BatchfileInfo batchfileInfo) throws CoreException;

  	//shihuan 2017-04-24 校验重点院校名称不能重复 
  	public int queryByMajorschoolName(String majorschoolName) throws CoreException;
}