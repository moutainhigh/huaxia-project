package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.input.BizApprovalOperateexc;

public interface BizApprovalOperateexcDao {
    int deleteByPrimaryKey(String id);

    int insert(BizApprovalOperateexc record);

    int insertSelective(BizApprovalOperateexc record);

    BizApprovalOperateexc selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BizApprovalOperateexc record);

    int updateByPrimaryKey(BizApprovalOperateexc record);
    
    int queryExceptionDateLong(String operateCode) throws CoreException;
    //zlb
    List<BizApprovalOperateexc> memberJobEcxceptionDetail(String operateCode);

	List<BizApprovalOperateexc> selectJobEcxceptionDetail(
			Map<String, Object> map);

	Map<String, Object> selectMemberJobEcxception(Map<String, Object> map);

	List<Map<String, Object>> selectmemberJobEcxceptionDetailDataMap(
			Map<String, Object> dataMap);

	BizApprovalOperateexc selectByUserCode(String userCode);
}