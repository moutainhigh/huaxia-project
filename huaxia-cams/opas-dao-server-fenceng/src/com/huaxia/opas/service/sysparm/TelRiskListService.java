package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;

public interface TelRiskListService {
	
    int deleteByPrimaryKey(String autoId) throws CoreException;

    int insertTelRistList(List<TelRiskList> resutTelAddrlist) throws CoreException;

    int insertSelective(TelRiskList telrisklist) throws CoreException;

    TelRiskList selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(TelRiskList telrisklist) throws CoreException;

    int updateByPrimaryKey(TelRiskList telrisklist) throws CoreException;
    
    //shihuan 2017-03-15 电话风险名单列表查询 
  	List<TelRiskList> queryTelRistList(TelRiskList telrisklist, int curNum, int pageNum);

  	//shihuan 2017-03-15 电话风险名单列表查询条数 
  	int queryTelRistCount(TelRiskList telrisklist);
  	
  	//shihuan 2017-03-15   电话风险名单批量启动修改状态
  	int updateStartStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-15   电话风险名单批量停用修改状态
  	int updateStopStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-18  电话风险名单批导入文件
  	int insertTelRistUpload(List<TelRiskList> obj,BatchfileInfo batchfileInfo) throws CoreException;
  	
  	//新增修改时校验手机号是否添加重复 
  	int queryByMobileId(String mobileId) throws CoreException;
  	
  	//新增修改时校验固话是否添加重复
  	int queryByCompanyTel(String companyTel) throws CoreException;
  	
  	//新增修改时校验固话是否添加重复
  	int queryByLivingTel(String livingTel) throws CoreException;
  	
  	//新增修改时校验固话是否添加重复
  	int queryByOtherTel(String otherTel) throws CoreException;
}