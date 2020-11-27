package com.huaxia.opas.dao.apply;

import java.util.List;

import com.huaxia.opas.domain.apply.ApplyModifyLog;

public interface ApplyModifyLogDao {
    int deleteByPrimaryKey(String autoId);

    int insert(ApplyModifyLog record);

    int insertSelective(ApplyModifyLog record);

    ApplyModifyLog selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(ApplyModifyLog record);

    int updateByPrimaryKey(ApplyModifyLog record);

	List<ApplyModifyLog> selectApplyLogByAppId(String appId);
	/**
	 * 征信环节修改录入页面信息(标卡增强信息采集)时插入申请信息修改日志表
	 * @param applyModifyLogList
	 * @return
	 */
	int insertinsertApplyModifyLogList(List<ApplyModifyLog> applyModifyLogList);
}