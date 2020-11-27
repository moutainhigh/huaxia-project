package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.thirdparty.BizApprovalHis;

public interface BizApprovalHisDao {
    int deleteByPrimaryKey(String autoId);

    int insert(BizApprovalHis record);

    int insertSelective(BizApprovalHis record);

    BizApprovalHis selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(BizApprovalHis record);

    int updateByPrimaryKey(BizApprovalHis record);

    List<BizApprovalHis> selectByAppId(String appId);
    /**
     * 根据appId与userId，查询出当天的审批过的距离此时最近的一条历史记录
     */
	BizApprovalHis selectByAppIdAndUserId(Map<String, String> map);
	/**
     * 根据appId 查询历史审批结果
     */
	Map<String, Object> selectHistoryAuditInfo(String appId);
	Map<String, Object> selectAuditInfo(String appId);

	List<BizApprovalHis> selectApprovalMemo(String appId);
	
	List<BizApprovalHis> selectDisApprovalMemo(String appId);
	
	int queryApprovalQzdcByAppId(String appId);

	List<BizApprovalHis> queryApprovalMsg(Map<String, Object> param);
	//单办附属卡情况查询
	List<BizApprovalHis> queryApprovalMsg_fsk(Map<String, Object> param);
}