package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;

public interface AllotApplyAllotHisDao {
    int deleteByPrimaryKey(String id);

    int insert(AllotApplyAllotHis record);

    int insertSelective(AllotApplyAllotHis record);

    AllotApplyAllotHis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AllotApplyAllotHis record);

    int updateByPrimaryKey(AllotApplyAllotHis record);
    //zhanglibo  
    int queryBizInpApplHisCount(Map map) throws CoreException;
    //zhanglibo  
    List<AllotApplyAllotHis> queryDetailBizInpApplHisByCurrOptUser(Map map)throws CoreException;
    //zhanglibo  
    int selectByCurrNodeAndUserCode(Map map);
    //单条查询
    List<AllotApplyAllotHis> querySingleInfoHis(String appId) throws CoreException;

	AllotApplyAllotHis queryAllotApplyAllotHisByAppId(Map map) throws CoreException;

	AllotApplyAllotHis queryAllotApplyAllotHisNozjByAppId(Map map) throws CoreException;
	
	//zlb  根据环节查询
  	List<AllotApplyAllotHis> queryStaffJobDetailByCurrNodeHis(String currNode)throws CoreException;
    
  	//wdb
  	int insertCopyOpasAllotToAllotHis(Map map);
  	
  	int insertHisBatch(List<String> lifeList);
  	
  	int executeOpasPoAllot(Map map);
  	
  	Map<String, String> queryHisByMap(Map map);

	AllotApplyAllotHis selectLastOneByAppId(String appId);

	AllotApplyAllotHis queryAllotApplyAllotHisSPByAppId(Map map) throws CoreException;
	
	int insertAutoApply(Map map);
}