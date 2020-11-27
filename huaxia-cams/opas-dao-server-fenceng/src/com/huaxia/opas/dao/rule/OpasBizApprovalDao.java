package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.rule.OpasBizApproval;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
public interface OpasBizApprovalDao {
    int deleteByPrimaryKey(String appId);

    int insert(OpasBizApproval record);
    List<OpasBizApproval> selectByExample(Opasbizinpapplc1 record);

    OpasBizApproval selectByPrimaryKey(String appId);
    //审批过件数 zlb
  	int querySPGJS(Map<String, Object> map)throws CoreException;
  	//审批拒件数 zlb
  	int querySPJJS(Map<String, Object> map)throws CoreException;

	List<Map<String, String>> selectByHisAppIds(Map hisAppIdArrayParams);
}