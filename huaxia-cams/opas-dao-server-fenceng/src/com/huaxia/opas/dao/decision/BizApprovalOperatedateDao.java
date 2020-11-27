package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.input.BizApprovalOperatedate;

public interface BizApprovalOperatedateDao {
    int deleteByPrimaryKey(String id);

    int insert(BizApprovalOperatedate record);

    int insertSelective(BizApprovalOperatedate record);

    BizApprovalOperatedate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BizApprovalOperatedate record);

    int updateByPrimaryKey(BizApprovalOperatedate record);

	BizApprovalOperatedate selectByUserId(String userId);

	BizApprovalOperatedate selectByUserIdAndDT(String userId);
	
	int selectAllCount()throws CoreException;
	
	List<BizApprovalOperatedate> selectAll(int curNum ,int pageNum )throws CoreException;
	
	public List<BizApprovalOperatedate> queryByName(String userName);
}